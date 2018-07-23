package com.donglam.webhoconline.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.donglam.webhoconline.model.Bai;
import com.donglam.webhoconline.model.BaiDto;
import com.donglam.webhoconline.model.Chuong;
import com.donglam.webhoconline.model.ChuongDto;
import com.donglam.webhoconline.model.GVKH;
import com.donglam.webhoconline.model.GiaoTrinh;
import com.donglam.webhoconline.model.GiaoTrinhDto;
import com.donglam.webhoconline.model.NguoiDung;
import com.donglam.webhoconline.service.BaiService;
import com.donglam.webhoconline.service.ChuongService;
import com.donglam.webhoconline.service.GiaoTrinhService;
import com.donglam.webhoconline.service.KhoaHocService;
import com.donglam.webhoconline.service.NguoiDungService;
import com.donglam.webhoconline.util.Util;

@Controller
public class PostController {
	// exception khi gv go khoa hoc ko phai cua minh thi lam sao?
	// xoa cac bien message trong javascrit table vi co trong footer roi
	@Autowired
	private KhoaHocService khs;
	@Autowired
	private GiaoTrinhService gts;
	@Autowired
	private ChuongService cs;
	@Autowired
	private BaiService bs;
	@Autowired
	private NguoiDungService nds;

	NguoiDung nd = new NguoiDung();

	// ql bai hoc
	@GetMapping("/adlesson")
	public String adLesson(Model model,Authentication auth) {
		if (nd.getMand() < 1)
			nd = Util.getUserLogin(nds,auth);
		//model.addAttribute("dsgt", nd.getGiaotrinhs());
		List<GiaoTrinh> listgt=new ArrayList<>();
		
		for(GVKH gvkh: nd.getGvkhs()) {
			listgt.add(gvkh.getKhoahoc().getGiaotrinh());
		}
		model.addAttribute("dsgt", listgt);
		
		return "adlesson";
	}

	/* GiaoTrinh */
	// load giao trinh
	@GetMapping("/adlesson/gt/{makh}")
	public String loadCurriculum(@PathVariable String makh, Model model) {
		model.addAttribute("dsgt", gts.get(khs.get(makh).getGiaotrinh().getMagt()));
		return "adlessoncomp::fgt";
	}

	// create
	@GetMapping("/giaotrinh/create")
	public String createCurriculum(Model model) {
		model.addAttribute("giaotrinh", new GiaoTrinh());
		return "comp/giaotrinh";
	}

	// edit
	@GetMapping("/giaotrinh/{id}/edit")
	public String editCurriculum(@PathVariable String id, Model model) {
		model.addAttribute("giaotrinh", gts.get(id));
		return "comp/giaotrinh";
	}

	// delete
	@GetMapping("/giaotrinh/{id}/delete")
	public String deleteCurriculum(@PathVariable String id, RedirectAttributes redirect) {
		GiaoTrinh gt = gts.get(id);
		if (gt == null) {
			redirect.addFlashAttribute("success", "m.deletefail");
			return "redirect:/adlesson";
		}

		try {
			gt.setIsdeleted(true);
			gts.saveOrUpdate(gt);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/adlesson";
		}

		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/adlesson";
	}

	// save
	@PostMapping("/giaotrinh/save")
	public String saveCurriculum(@Valid GiaoTrinhDto dto, BindingResult result, RedirectAttributes redirect,Authentication auth) {
		if (nd.getMand() < 1)
			nd = Util.getUserLogin(nds,auth);

		if (dto.getMagt() != "") { // edit
			if (result.hasErrors()) {
				return "comp/giaotrinh";
			}

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			try {
				gts.saveOrUpdate(new GiaoTrinh(dto.getMagt(), dto.getTengt(), df.format(new Date()), nd));
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/adlesson";
			}

		} else { // add
			if (result.hasErrors()) {
				return "comp/giaotrinh";
			}

			String idmagt = "gt_" + gts.getNextMaGT();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			try {
				gts.saveOrUpdate(new GiaoTrinh(idmagt, dto.getTengt(), df.format(new Date()), nd));
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/adlesson";
			}
		}

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/adlesson";
	}

	/* ========================== Chuong ======================= */
	// load giao trinh
	@GetMapping("/adlesson/chuong/{magt}")
	public String loadChapter(@PathVariable String magt, Model model) {
		model.addAttribute("dschuong", cs.getListByGT(magt));
		return "adlessoncomp::fchuong";
	}

	// create
	@GetMapping("/chuong/create")
	public String createChapter(Model model) {
		model.addAttribute("chuong", new Chuong());
		return "comp/chuong";
	}

	// edit
	@GetMapping("/chuong/{id}/edit")
	public String editChapter(@PathVariable String id, Model model) {
		model.addAttribute("chuong", cs.get(id));
		return "comp/chuong";
	}

	// delete
	@GetMapping("/chuong/{id}/delete")
	public String deleteChapter(@PathVariable String id, RedirectAttributes redirect) {
		Chuong ch = cs.get(id);
		if (ch == null) {
			redirect.addFlashAttribute("success", "m.deletefail");
			return "redirect:/adlesson";
		}

		try {
			ch.setIsdeleted(true);
			cs.saveOrUpdate(ch);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/adlesson";
		}

		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/adlesson";
	}

	// save
	@PostMapping("/chuong/save")
	public String saveChapter(@Valid ChuongDto dto, BindingResult result, RedirectAttributes redirect) {
		String magt = dto.getGiaotrinh().getMagt();

		if (dto.getMachuong() != "") {
			if (result.hasErrors()) {
				return "comp/chuong";
			}

			try {
				cs.saveOrUpdate(new Chuong(dto.getMachuong(), dto.getTenchuong(), dto.getGiaotrinh(), null));

				GiaoTrinh gt = gts.get(magt);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				gts.saveOrUpdate(new GiaoTrinh(null, gt.getMagt(), gt.getTengt(), df.format(new Date()), null,
						gt.getNguoidung()));
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/adlesson";
			}

		} else {
			if (result.hasErrors()) {
				return "comp/chuong";
			}

			try {
				String idmachuong = magt + "_ch_" + cs.getNextMaChuong(magt);
				cs.saveOrUpdate(new Chuong(idmachuong, dto.getTenchuong(), dto.getGiaotrinh(), null));

				GiaoTrinh gt = gts.get(magt);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				gts.saveOrUpdate(new GiaoTrinh(null, gt.getMagt(), gt.getTengt(), df.format(new Date()), null,
						gt.getNguoidung()));
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/adlesson";
			}
		}

		redirect.addFlashAttribute("magt", magt);
		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/adlesson";
	}
	/* Chuong */

	/* ======================= Bai ======================= */
	// load
	@GetMapping("/adlesson/bai/{machuong}")
	public String loadLesson(@PathVariable String machuong, Model model) {
		model.addAttribute("dsbai", bs.getListByChuong(machuong));
		return "adlessoncomp::fbai";
	}

	// create
	@GetMapping("/bai/create")
	public String createLesson(Model model) {
		model.addAttribute("bai", new Bai());
		return "comp/bai";
	}

	// edit
	@GetMapping("/bai/{id}/edit")
	public String editLesson(@PathVariable String id, Model model) {
		model.addAttribute("bai", bs.get(id));
		return "comp/bai";
	}

	// delete
	@GetMapping("/bai/{id}/delete")
	public String deleteLesson(@PathVariable String id, RedirectAttributes redirect) {
		Bai bai = bs.get(id);
		if (bai == null) {
			redirect.addFlashAttribute("success", "m.deletefail");
			return "redirect:/adlesson";
		}

		try {
			bai.setIsdeleted(true);
			bs.saveOrUpdate(bai);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/adlesson";
		}

		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/adlesson";
	}

	// save
	@PostMapping("/bai/save")
	public String saveLesson(@Valid BaiDto dto, BindingResult result, RedirectAttributes redirect) {
		String magt = dto.getChuong().getGiaotrinh().getMagt();

		if (dto.getMabai() != "") {
			if (result.hasErrors()) {
				return "comp/bai";
			}
			try {
				bs.saveOrUpdate(
						new Bai(dto.getMabai(), dto.getTenbai(), dto.getChuong(), HtmlUtils.htmlEscape(dto.getNoidung()), dto.isTrangthai()));

				GiaoTrinh gt = gts.get(magt);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				gts.saveOrUpdate(new GiaoTrinh(null, gt.getMagt(), gt.getTengt(), df.format(new Date()), null,
						gt.getNguoidung()));
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/adlesson";
			}

		} else {
			if (result.hasErrors()) {
				return "comp/bai";
			}

			try {
				String machuong = dto.getChuong().getMachuong();

				String idmabai = machuong + "_b_" + bs.getNextMaBai(machuong);
				bs.saveOrUpdate(
						new Bai(idmabai, dto.getTenbai(), dto.getChuong(), dto.getNoidung(), dto.isTrangthai()));

				GiaoTrinh gt = gts.get(magt);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				gts.saveOrUpdate(new GiaoTrinh(null, gt.getMagt(), gt.getTengt(), df.format(new Date()), null,
						gt.getNguoidung()));
			} catch (Exception ee) {
				redirect.addFlashAttribute("magt", magt);
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/adlesson";
			}
		}

		redirect.addFlashAttribute("magt", magt);
		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/adlesson";
	}

	/* Bai */
	@GetMapping("/post")
	public String post(Model model) {

		return "adpost";
	}
}
