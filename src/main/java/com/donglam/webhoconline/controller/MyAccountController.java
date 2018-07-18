package com.donglam.webhoconline.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donglam.webhoconline.model.HVKH;
import com.donglam.webhoconline.model.HVKHId;
import com.donglam.webhoconline.model.KhoaHoc;
import com.donglam.webhoconline.model.NguoiDung;
import com.donglam.webhoconline.model.NguoiDungDto;
import com.donglam.webhoconline.service.HVKHService;
import com.donglam.webhoconline.service.KhoaHocService;
import com.donglam.webhoconline.service.NDTNService;
import com.donglam.webhoconline.service.NguoiDungService;
import com.donglam.webhoconline.util.Util;

@Controller
public class MyAccountController {

	@Autowired
	private NguoiDungService nds;
	@Autowired
	private NDTNService ndtns;
	@Autowired
	private HVKHService hvkhs;
	@Autowired
	private KhoaHocService khs;

	NguoiDung nd = new NguoiDung();

	@GetMapping("/myaccount")
	public String myAccount(Model model,Authentication auth) {
		nd = Util.getUserLogin(nds,auth);
		model.addAttribute("nd", nd);
		model.addAttribute("dstn", ndtns.getListByNguoiNhan(nd.getMand()));
		model.addAttribute("dskh", nd.getHvkhs());
		model.addAttribute("hvkh", new HVKH());
		return "myaccount";
	}

	/*
	 * @GetMapping("/myaccount/{id}/edit") public String edit(@PathVariable int
	 * id, Model model) throws ParseException {
	 * System.out.println(nd.getNgaysinh()); nd.setNgaysinh(
	 * Util.changeDateTime(nd.getNgaysinh(),false) );
	 * System.out.println(nd.getNgaysinh()); model.addAttribute("nd", nd);
	 * return "myaccount"; }
	 */

	@PostMapping("/myaccount/save")
	public String editMyAccount(@Valid NguoiDungDto nddto, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "myaccount";
		}

		nd.setHovaten(nddto.getHovaten());
		nd.setPhai(nddto.isPhai());
		nd.setDiachi(nddto.getDiachi());
		nd.setCmnd(nddto.getCmnd());
		nd.setMota(nddto.getMota());
		nd.setNgaysinh(nddto.getNgaysinh());
		nd.setHinh(nddto.getHinh());

		try {
			nds.saveOrUpdate(nd);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.savefail");
			return "redirect:/myaccount";
		}

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/myaccount";
	}

	@PostMapping("/danhgia/save")
	public String saveEvaluation(@Valid HVKH hvkh, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "myaccount";
		}

		NguoiDung hv = hvkh.getHvkhid().getNguoidung();
		KhoaHoc kh = hvkh.getHvkhid().getKhoahoc();

		String makh = kh.getMakh();
		kh = khs.get(makh);

		double sodiem = kh.getSodiem();
		int soluot = kh.getSoluot();
		double sao = hvkh.getSao();

		sodiem = sodiem * (soluot / (soluot + 1.0)) + (sao / (soluot + 1.0));
		sodiem = (double) Math.round(sodiem * 10) / 10;
		soluot = soluot + 1;

		kh.setSodiem(sodiem);
		kh.setSoluot(soluot);
		khs.saveOrUpdate(kh);

		HVKH hva = hvkhs.get(new HVKHId(hv, kh));
		hva.setSao(sao);
		hva.setNoidung(hvkh.getNoidung());
		hvkhs.saveOrUpdate(hva);

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/myaccount";
	}
}
