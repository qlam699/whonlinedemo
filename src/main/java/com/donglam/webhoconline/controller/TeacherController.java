package com.donglam.webhoconline.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donglam.webhoconline.model.KhoaHoc;
import com.donglam.webhoconline.model.NguoiDung;
import com.donglam.webhoconline.model.NguoiDungDto;
import com.donglam.webhoconline.service.KhoaHocService;
import com.donglam.webhoconline.service.NguoiDungService;
import com.donglam.webhoconline.service.QuyenService;

@Controller
public class TeacherController {

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private NguoiDungService gvs;
	@Autowired
	private KhoaHocService khs;
	@Autowired
	private QuyenService qs;
	@Autowired
	private NguoiDungService nds;

	/* Paging */
	@GetMapping("/teachers")
	public String teacher(HttpServletRequest request) {
		request.getSession().setAttribute("teachers", null);
		return "redirect:/teachers/page/1";
	}

	@GetMapping("/teachers/page/{pageNumber}")
	public String showCoursesPage(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("teachers");
		int pagesize = 3;

		/* List<NguoiDung> list = qs.get(3).getNguoidungs(); */
		List<NguoiDung> list = nds.getListTea();
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("teachers", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/teachers/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("dsgv", pages);

		return "teachers";
	}

	// ------------------------------- adteachers

	@Autowired
	public TeacherController(BCryptPasswordEncoder bCryptPasswordEncoder, NguoiDungService nds) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.gvs = nds;
	}

	/* Paging */
	@GetMapping("/adteachers")
	public String adTeachers(Model model, Authentication auth) {
		model.addAttribute("dsgv", gvs.getListTea());
		return "adteachers";
	}

	// create
	@GetMapping("/giangvien/create")
	public String createTeacher(Model model) {
		model.addAttribute("giangvien", new NguoiDung());
		return "comp/giangvien";
	}

	// edit
	@GetMapping("/giangvien/{id}/edit")
	public String editTeacher(@PathVariable int id, Model model) {
		model.addAttribute("giangvien", gvs.get(id));
		return "comp/giangvien";
	}

	// view
	@GetMapping("/giangvien/{id}/view")
	public String viewTeacher(@PathVariable int id, Model model) {
		model.addAttribute("giangvien", gvs.get(id));
		List<KhoaHoc> list = new ArrayList<KhoaHoc>();
		list = khs.getListGVKH(id);
		model.addAttribute("gvkh", list);
		model.addAttribute("listsize", list == null ? 0 : list.size());
		return "comp/viewgiangvien";
	}

	// delete
	@GetMapping("/giangvien/{id}/delete")
	public String deleteTeacher(@PathVariable int id, RedirectAttributes redirect) {
		NguoiDung gv = gvs.get(id);
		if (gv == null) {
			redirect.addFlashAttribute("success", "m.deletefail");
			return "redirect:/adteachers";
		}

		try {
			gv.setIsdeleted(true);
			gvs.saveOrUpdate(gv);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/adteachers";
		}

		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/adteachers";
	}

	// save
	@PostMapping("/giangvien/save")
	public String saveTeacher(@Valid NguoiDungDto nddto, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "comp/giangvien";
		}

		NguoiDung nd = new NguoiDung();

		if (nddto.getMand() > 0) { // edit
			nd = new NguoiDung(nddto.getMand(), nddto.getHovaten(), nddto.isPhai(), nddto.getNgaysinh(),
					nddto.getDiachi(), nddto.getCmnd(), nddto.getEmail(), nddto.getHinh(), nddto.getMota(),
					nddto.getQuyen());

			nd.setNgaycap(nddto.getNgaycap());
			nd.setLoaitaikhoan(nddto.getLoaitaikhoan());
			nd.setMatkhau(nddto.getMatkhau());

		} else { // add
			nd = new NguoiDung(nddto.getHovaten(), nddto.isPhai(), nddto.getNgaysinh(), nddto.getDiachi(),
					nddto.getCmnd(), nddto.getEmail(), nddto.getHinh(), nddto.getMota(), nddto.getQuyen());

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			nd.setNgaycap(df.format(new Date()));

			nd.setLoaitaikhoan("local");
			nd.setMatkhau(bCryptPasswordEncoder.encode("123123123"));
			nd.setQuyen(qs.get(3));
		}

		try {
			gvs.saveOrUpdate(nd);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.savefail");
			return "redirect:/adteachers";
		}

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/adteachers";
	}

	// ------------------------------- detail teacher
	@GetMapping("/detailteacher/{id}")
	public String detailTeacher(@PathVariable int id, Model model) {
		model.addAttribute("gv", gvs.get(id));
		model.addAttribute("dsgvkh", khs.getListGVKH(id));
		return "detailteacher";
	}
}