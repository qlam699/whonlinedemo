package com.donglam.webhoconline.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donglam.webhoconline.model.GVKH;
import com.donglam.webhoconline.model.HVKH;
import com.donglam.webhoconline.model.KhoaHoc;
import com.donglam.webhoconline.model.NguoiDung;
import com.donglam.webhoconline.model.NguoiDungDto;
import com.donglam.webhoconline.service.HVKHService;
import com.donglam.webhoconline.service.KhoaHocService;
import com.donglam.webhoconline.service.NguoiDungService;
import com.donglam.webhoconline.service.QuyenService;
import com.donglam.webhoconline.util.Util;

@Controller
public class StudentController {

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private NguoiDungService hvs;

	@Autowired
	private QuyenService qs;

	@Autowired
	private NguoiDungService nds;

	@Autowired
	private KhoaHocService khs;

	@Autowired
	private HVKHService hvkhs;

	NguoiDung nd = new NguoiDung();

	@Autowired
	public StudentController(BCryptPasswordEncoder bCryptPasswordEncoder, NguoiDungService nds) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.hvs = nds;
	}

	@GetMapping("/adstudents")
	public String adStudentKH(Model model, Authentication auth) {
		nd = Util.getUserLogin(nds, auth);

		if (nd.getQuyen().getMaquyen() == 2) // role nhan vien
		{
			model.addAttribute("dshv", hvkhs.getList());
			model.addAttribute("listkh", khs.getListKHDaDuyet(true));
		} else if (nd.getQuyen().getMaquyen() == 3) // role giang vien
		{
			List<HVKH> listhvkh = new ArrayList<HVKH>();

			for (GVKH x : Util.getUserLogin(nds, auth).getGvkhs()) {
				listhvkh.addAll(x.getKhoahoc().getHvkhs());
			}
			model.addAttribute("dshv", listhvkh);
			model.addAttribute("listkh", khs.getListGVKH(nd.getMand()));
		}
		model.addAttribute("khoahoc", new KhoaHoc());

		return "adstudents";
	}

	// create
	@GetMapping("/hocvien/create")
	public String createStudent(Model model) {
		model.addAttribute("hocvien", new NguoiDung());
		return "comp/hocvien";
	}

	// edit
	@GetMapping("/hocvien/{id}/edit")
	public String editStudent(@PathVariable int id, Model model) {
		model.addAttribute("hocvien", hvs.get(id));
		return "comp/hocvien";
	}

	// view
	@GetMapping("/hocvien/{id}/view")
	public String viewStudent(@PathVariable int id, Model model) {
		model.addAttribute("hocvien", hvs.get(id));
		List<KhoaHoc> list = new ArrayList<KhoaHoc>();
		list = khs.getListHVKH(id);
		model.addAttribute("hvkh", list);
		model.addAttribute("listsize", list == null ? 0 : list.size());
		return "comp/viewhocvien";
	}

	// delete
	@GetMapping("/hocvien/{id}/delete")
	public String deleteStudent(@PathVariable int id, RedirectAttributes redirect) {
		NguoiDung hv = hvs.get(id);
		if (hv == null) {
			redirect.addFlashAttribute("success", "m.deletefail");
			return "redirect:/adstudents";
		}

		try {
			hv.setIsdeleted(true);
			hvs.saveOrUpdate(hv);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/adstudents";
		}

		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/adstudents";
	}

	// save
	@PostMapping("/hocvien/save")
	public String saveStudent(@Valid NguoiDungDto nddto, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "comp/hocvien";
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
			nd.setQuyen(qs.get(4));
		}

		try {
			hvs.saveOrUpdate(nd);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.savefail");
			return "redirect:/adstudents";
		}

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/adstudents";
	}

}