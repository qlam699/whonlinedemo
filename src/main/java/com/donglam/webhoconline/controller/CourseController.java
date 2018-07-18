package com.donglam.webhoconline.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.donglam.webhoconline.config.PaypalPaymentIntent;
import com.donglam.webhoconline.config.PaypalPaymentMethod;
import com.donglam.webhoconline.model.Bai;
import com.donglam.webhoconline.model.GVKH;
import com.donglam.webhoconline.model.GVKHId;
import com.donglam.webhoconline.model.GiaoTrinh;
import com.donglam.webhoconline.model.HVKH;
import com.donglam.webhoconline.model.HVKHId;
import com.donglam.webhoconline.model.Khoa;
import com.donglam.webhoconline.model.KhoaDto;
import com.donglam.webhoconline.model.KhoaHoc;
import com.donglam.webhoconline.model.KhoaHocDto;
import com.donglam.webhoconline.model.LoaiKhoaHoc;
import com.donglam.webhoconline.model.LoaiKhoaHocDto;
import com.donglam.webhoconline.model.NguoiDung;
import com.donglam.webhoconline.service.BaiService;
import com.donglam.webhoconline.service.ChuongService;
import com.donglam.webhoconline.service.GVKHService;
import com.donglam.webhoconline.service.GiaoTrinhService;
import com.donglam.webhoconline.service.HVKHService;
import com.donglam.webhoconline.service.KhoaHocService;
import com.donglam.webhoconline.service.KhoaService;
import com.donglam.webhoconline.service.LoaiKhoaHocService;
import com.donglam.webhoconline.service.NguoiDungService;
import com.donglam.webhoconline.service.PaypalService;
import com.donglam.webhoconline.util.Util;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class CourseController {

	@Autowired
	private KhoaHocService khs;
	@Autowired
	private KhoaService ks;
	@Autowired
	private LoaiKhoaHocService lkhs;
	@Autowired
	private NguoiDungService nds;
	@Autowired
	private GiaoTrinhService gts;
	@Autowired
	private ChuongService cs;
	@Autowired
	private BaiService bs;
	@Autowired
	private HVKHService hvkhs;
	@Autowired
	private GVKHService gvkhs;

	NguoiDung nd = new NguoiDung();

	public static final String PAYPAL_SUCCESS_URL = "pay/success";
	public static final String PAYPAL_CANCEL_URL = "pay/cancel";
	public static final String DETAILCOURSE_URL = "detailcourse/";
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaypalService paypalService;

	/* Paging */
	@GetMapping("/courses")
	public String courses(HttpServletRequest request) {
		request.getSession().setAttribute("courses", null);
		return "redirect:/courses/page/1";
	}

	@GetMapping("/courses/page/{pageNumber}")
	public String showCoursesPage(@RequestParam(value = "k", required = false) String keyword,
			HttpServletRequest request, @PathVariable int pageNumber, Model model, Authentication auth) {
		if (keyword != null)
			request.getSession().setAttribute("courses", null);
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("courses");
		int pagesize = 3;
		List<KhoaHoc> list = null;
		if (keyword == null) {
			list = khs.getListKHDaDuyet(true);
		}
		// tìm kiếm theo loại khóa học
		else if (keyword.contains("category_")) {
			String[] temp = keyword.split("_", 2);
			if (temp.length > 2) {
				list = khs.getListByName(keyword);
			} else {
				String maloaikh = temp[1];
				list = khs.getListKH(maloaikh);
			}
		} else {
			list = khs.getListByName(keyword);
		}

		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}

		request.getSession().setAttribute("courses", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/courses/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("dskh", pages);

		return "courses";
	}

	// message is delete
	@GetMapping("/isdelete")
	public String isDelete(Model model) {
		// model.addAttribute("id", id);
		return "comp/isdelete";
	}

	// manager courses
	@GetMapping("/adcourse")
	public String adCourse(Model model, Authentication auth) {
		nd = Util.getUserLogin(nds, auth);

		model.addAttribute("dskh", khs.getList());
		model.addAttribute("dskhdaduyet", khs.getListKHDaDuyet(true));
		model.addAttribute("dskhoa", ks.getList());
		model.addAttribute("dsloaikh", lkhs.getList());
		model.addAttribute("dsgvkh", gvkhs.getListGVKHChuaDuyet());
		model.addAttribute("mand", nd.getMand());

		return "adcourse";
	}

	// -------------------------------- KHOA

	// create
	@GetMapping("/khoa/create")
	public String createKhoa(Model model) {
		model.addAttribute("khoa", new Khoa());
		return "comp/khoa";
	}

	// edit
	@GetMapping("/khoa/{id}/edit")
	public String editKhoa(@PathVariable String id, Model model) {
		model.addAttribute("khoa", ks.get(id));
		return "comp/khoa";
	}

	// delete
	@GetMapping("/khoa/{id}/delete")
	public String deleteKhoa(@PathVariable String id, RedirectAttributes redirect) {
		Khoa k = ks.get(id);
		if (k == null) {
			redirect.addFlashAttribute("success", "m.deletefail");
			return "redirect:/adcourse";
		}

		try {
			k.setIsdeleted(true);
			ks.saveOrUpdate(k);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/adcourse";
		}

		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/adcourse";
	}

	// save
	@PostMapping("/khoa/save")
	public String saveKhoa(@Valid KhoaDto kdto, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "comp/khoa";
		}

		try {
			ks.saveOrUpdate(new Khoa(null, kdto.getMakhoa(), kdto.getTenkhoa()));
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.savefail");
			return "redirect:/adcourse";
		}

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/adcourse";
	}

	// ------------------------------ LOAI KH

	// create
	@GetMapping("/loaikh/create")
	public String createLoaiKH(Model model) {
		model.addAttribute("loaikh", new LoaiKhoaHoc());
		model.addAttribute("listkhoa", ks.getListKhoa());

		return "comp/loaikh";
	}

	// edit
	@GetMapping("/loaikh/{id}/edit")
	public String editLoaiKH(@PathVariable String id, Model model) {
		model.addAttribute("loaikh", lkhs.get(id));
		model.addAttribute("listkhoa", ks.getListKhoa());
		return "comp/loaikh";
	}

	// delete
	@GetMapping("/loaikh/{id}/delete")
	public String deleteLoaiKH(@PathVariable String id, RedirectAttributes redirect) {
		LoaiKhoaHoc lkh = lkhs.get(id);
		if (lkh == null) {
			redirect.addFlashAttribute("success", "m.deletefail");
			return "redirect:/adcourse";
		}

		try {
			lkh.setIsdeleted(true);
			lkhs.saveOrUpdate(lkh);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/adcourse";
		}

		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/adcourse";
	}

	// save
	@PostMapping("/loaikh/save")
	public String saveLoaiKH(@Valid LoaiKhoaHocDto lkhdto, BindingResult result, RedirectAttributes redirect) {
		if (lkhdto.getMaloaikh() != "") {
			if (result.hasErrors()) {
				return "comp/loaikh";
			}

			try {
				lkhs.saveOrUpdate(new LoaiKhoaHoc(lkhdto.getKhoa(), lkhdto.getKhoahocs(), lkhdto.getMaloaikh(),
						lkhdto.getTenloaikh()));
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/adcourse";
			}

		} else {
			if (result.hasErrors()) {
				return "comp/loaikh";
			}

			String idkhoa = lkhdto.getKhoa().getMakhoa();
			String idlkh = idkhoa + "_" + lkhs.getNextMalkh(idkhoa);

			try {
				lkhs.saveOrUpdate(
						new LoaiKhoaHoc(lkhdto.getKhoa(), lkhdto.getKhoahocs(), idlkh, lkhdto.getTenloaikh()));
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/adcourse";
			}
		}

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/adcourse";
	}

	// -------------------------------- KHOA HOC

	// create
	@GetMapping("/khoahoc/create")
	public String createKhoaHoc(Model model, Authentication auth) {
		model.addAttribute("khoahoc", new KhoaHoc());
		model.addAttribute("listloaikh", lkhs.getListLoaiKH());
		model.addAttribute("listgiaotrinh", gts.getListGiaoTrinh());

		return "comp/khoahoc";
	}

	// edit
	@GetMapping("/khoahoc/{id}/edit")
	public String editKhoaHoc(@PathVariable String id, Model model) {
		model.addAttribute("khoahoc", khs.get(id));
		model.addAttribute("listloaikh", lkhs.getListLoaiKH());
		model.addAttribute("listgiaotrinh", gts.getListGiaoTrinh());

		return "comp/khoahoc";
	}

	// delete
	@GetMapping("/khoahoc/{id}/delete")
	public String deleteKhoaHoc(@PathVariable String id, RedirectAttributes redirect, Authentication auth) {
		KhoaHoc kh = khs.get(id);
		GVKH gvkh = gvkhs.get(new GVKHId(nd, kh));

		if (kh == null || gvkh == null) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/adcourse";
		}
		try {
			kh.setIsdeleted(true);
			khs.saveOrUpdate(kh);
			gvkhs.delete(gvkh);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/adcourse";
		}
		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/adcourse";
	}

	// save
	@PostMapping("/khoahoc/save")
	public String saveKhoaHoc(@Valid KhoaHocDto khdto, BindingResult result, RedirectAttributes redirect,
			Authentication auth) {
		KhoaHoc kh = new KhoaHoc();
		if (khdto.getMakh() != "") {// edit
			if (result.hasErrors()) {
				return "comp/khoahoc";
			}

			kh = new KhoaHoc(khdto.getLoaikhoahoc(), khdto.getMakh(), khdto.getTenkh(), khdto.getTgbdhoc(),
					khdto.getTgkthoc(), khdto.getTgbddk(), khdto.getTgktdk(), khdto.getSoluong(), khdto.getSoluonght(),
					khdto.getHocphi(), khdto.getMota(), khdto.getHinh(), khdto.isDaduyet(), khdto.getGiaotrinh());

			try {
				khs.saveOrUpdate(kh);
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/adcourse";
			}

		} else { // add
			if (result.hasErrors()) {
				return "comp/khoahoc";
			}

			String idlkh = khdto.getLoaikhoahoc().getMaloaikh();
			String idkh = idlkh + "_" + khs.getNextMakh(idlkh);

			kh = new KhoaHoc(khdto.getLoaikhoahoc(), idkh, khdto.getTenkh(), khdto.getTgbdhoc(), khdto.getTgkthoc(),
					khdto.getTgbddk(), khdto.getTgktdk(), khdto.getSoluong(), khdto.getSoluonght(), khdto.getHocphi(),
					khdto.getMota(), khdto.getHinh(), khdto.isDaduyet(), khdto.getGiaotrinh());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			kh.getGvkhs().add(new GVKH(new GVKHId(nd, kh), df.format(new Date())));

			try {
				khs.saveOrUpdate(kh);
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/adcourse";
			}
		}
		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/adcourse";
	}

	// -------------------------------- Detail course

	String magt = "";
	String machuong = "";

	@GetMapping("/detailcourse/{id}")
	public String detailCourse(@PathVariable String id, Model model, RedirectAttributes redirect, Authentication auth,
			@RequestParam(value = "paymentId", required = false) String paymentId,
			@RequestParam(value = "PayerID", required = false) String payerId) {
		try {
			/*
			 * if(nd == null) {
			 * redirect.addFlashAttribute("error","Vui long dang nhap!"); return
			 * "redirect:/"; }
			 */
			
			nd = Util.getUserLogin(nds, auth);

			if (paymentId != null && payerId != null) {
				Payment payment = paypalService.executePayment(paymentId, payerId);
				if (payment.getState().equals("approved")) {
					KhoaHoc kh = khs.get(id);
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					kh.setSoluonght(kh.getSoluonght() + 1);
					try {
						khs.saveOrUpdate(kh);
						hvkhs.saveOrUpdate(new HVKH(new HVKHId(nd, kh), df.format(new Date()), 4, ""));
					} catch (Exception ee) {
						redirect.addFlashAttribute("error", "m.courseregfail");
						return "redirect:/" + DETAILCOURSE_URL + id;
					}
					redirect.addFlashAttribute("success", "m.courseregsuccess");
					return "redirect:/" + DETAILCOURSE_URL + id;
				}
			}

		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		} catch (Exception ee) {
			log.error(ee.getMessage());
		}
		KhoaHoc kh = khs.get(id);
		model.addAttribute("khoahoc", kh);
		model.addAttribute("listgv", nds.getListCouTea(id));
		NguoiDung n = nd;
		model.addAttribute("dadk", hvkhs.get(new HVKHId(n, kh)) == null ? "false" : "true");
		magt = khs.get(id).getGiaotrinh().getMagt();

		return "detailcourse";
	}

	@GetMapping("/detailpost")
	public String forum(Model model) {
		model.addAttribute("chuong", cs.getListByGT(magt));

		return "detailpost";
	}

	/**
	 * POST /uploadFile -> receive and locally save a file.
	 * 
	 * @param uploadfile
	 *            The uploaded file as Multipart file parameter in the HTTP
	 *            request. The RequestParam name must be the same of the
	 *            attribute "name" in the input tag with type file.
	 * 
	 * @return An http OK status in case of success, an http 4xx status in case
	 *         of errors.
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile,
			HttpServletRequest request, @RequestParam("filename") String filename, RedirectAttributes redirect) {
		try {
			// Get the filename and build the local file path (be sure that the
			// application have write permissions on such directory)
			String directory = System.getProperty("user.dir") + "/src/main/resources/static/uploadmedia/videos";
			String filepath = Paths.get(directory, filename).toString();

			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
			// Add lesson
			String idmabai = machuong + "_b_" + bs.getNextMaBai(machuong);
			GiaoTrinh gt = gts.get(magt);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nd = "Link video: <br/><video width='500px' controls='controls' src='" + Util.getBaseURl(request)
					+ "/public/videos/" + filename + "' ></video>";
			bs.saveOrUpdate(new Bai(idmabai, idmabai + df.format(new Date()), cs.get(machuong),
					HtmlUtils.htmlEscape(nd), false));
			gts.saveOrUpdate(
					new GiaoTrinh(null, gt.getMagt(), gt.getTengt(), df.format(new Date()), null, gt.getNguoidung()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	} // method uploadFile

	@GetMapping("/stream/{magt}/{machuong}")
	public String streamlesson(Model model, RedirectAttributes redirect, @PathVariable String magt,
			@PathVariable String machuong, Authentication auth) {
	//	if (nd.getMand() < 1) // login
			nd = Util.getUserLogin(nds, auth);
		if (gts.get(magt).equals(null) || cs.get(machuong).equals(null)) {
			redirect.addFlashAttribute("error", "Khong tim thay ma chuong or ma gt");
			return "redirect:/404";
		}
		this.magt = magt;
		this.machuong = machuong;
		model.addAttribute("nd", nd);
		model.addAttribute("makh",khs.getKHGT(magt));
		return "streamlesson";
	}

	@GetMapping("/detaillesson/{id}")
	public String detailLesson(@PathVariable String id, Model model, Authentication auth) {
		if (bs.get(id) != null)
			model.addAttribute("bai", bs.get(id));
		if (nd.getMand() < 1)
			nd = Util.getUserLogin(nds, auth);
		return "comp/detaillesson";
	}

	@RequestMapping(method = RequestMethod.POST, value = "pay")
	public String pay(HttpServletRequest request, @RequestParam("makh") String makh, RedirectAttributes redirect) {
		String cancelUrl = Util.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
		String successUrl = Util.getBaseURl(request) + "/" + DETAILCOURSE_URL + makh;
		KhoaHoc kh = khs.get(makh);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		try {
			if (hvkhs.get(new HVKHId(nd, kh)) != null) {
				redirect.addFlashAttribute("error", "m.courseregdup");
				return "redirect:/" + DETAILCOURSE_URL + makh;
			} else if (kh.getSoluonght() >= kh.getSoluong()) {
				redirect.addFlashAttribute("error", "m.fullslot");
				return "redirect:/" + DETAILCOURSE_URL + makh;
			} else
				try {
					if (now.before(df.parse(kh.getTgbddk())) || now.after(df.parse(kh.getTgktdk()))) {
						redirect.addFlashAttribute("error", "m.timecoursewrong");
						return "redirect:/" + DETAILCOURSE_URL + makh;
					}
				} catch (ParseException e) {
					redirect.addFlashAttribute("error", "m.courseregfail");
					return "redirect:/" + DETAILCOURSE_URL + makh;
				}

			Payment payment = paypalService.createPayment(kh.getHocphi(), "USD", PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale, kh.getTenkh(), cancelUrl, successUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/" + DETAILCOURSE_URL + makh;
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
	public String cancelPay(String message, RedirectAttributes redirect) {
		redirect.addFlashAttribute("error", message);
		return "redirect:/";
	}

}