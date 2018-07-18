package com.donglam.webhoconline.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.donglam.webhoconline.model.NDTN;
import com.donglam.webhoconline.model.NDTNDto;
import com.donglam.webhoconline.model.NguoiDung;
import com.donglam.webhoconline.model.TinNhan;
import com.donglam.webhoconline.model.TinNhanDto;
import com.donglam.webhoconline.service.NDTNService;
import com.donglam.webhoconline.service.NguoiDungService;
import com.donglam.webhoconline.service.TinNhanService;
import com.donglam.webhoconline.util.Util;

@Controller
public class MessageController {

	@Autowired
	private TinNhanService tns;

	@Autowired
	private NDTNService ndtns;
	@Autowired
	private NguoiDungService nds;

	NguoiDung nd = new NguoiDung();

	@GetMapping("/message")
	public String message(Model model, Authentication auth) {
		nd = Util.getUserLogin(nds, auth);
		if (nd.getQuyen().getMaquyen() == 2) // role nhan vien
		{
			model.addAttribute("dstn", ndtns.getListByNguoiGui(nd.getMand()));
		} else if (nd.getQuyen().getMaquyen() == 3) // role giang vien
		{
			model.addAttribute("dstn", ndtns.getListByNguoiNhan(nd.getMand()));
		}
		return "admessage";
	}

	// save
	@PostMapping("/ndtn/save")
	public String saveNDTN(@Valid NDTNDto ndtndto, BindingResult result, RedirectAttributes redirect,
			Authentication auth) {
		if (result.hasErrors()) {
			return "comp/tinnhan";
		}

		if (nd.getMand() < 1)
			nd = Util.getUserLogin(nds, auth);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int matn = ndtndto.getNdtnid().getTinnhan().getMatn();
		ndtndto.getNdtnid().setNguoigui(nd);
		ndtndto.getNdtnid().getTinnhan().setTggui(df.format(new Date()));

		try {
			tns.saveOrUpdate(ndtndto.getNdtnid().getTinnhan());
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.savefail");
			return "redirect:/message";
		}
		if (matn == 0) // add
			try {
				ndtns.saveOrUpdate(new NDTN(ndtndto.getNdtnid()));
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/message";
			}

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/message";
	}

	// delete
	@GetMapping("/tinnhan/{id}/delete")
	public String deleteNDTN(@PathVariable String id, RedirectAttributes redirect, Authentication auth) {
		if (nd.getMand() < 1)
			nd = Util.getUserLogin(nds, auth);

		String[] tam = id.split("z");
		int matn = Integer.parseInt(tam[1]);
		NDTN ndtn = ndtns.getNDTNId(nd.getMand(), Integer.parseInt(tam[0]), matn);

		if (ndtn == null) {
			redirect.addFlashAttribute("success", "m.deletefail");
			return "redirect:/message";
		}

		try {
			ndtns.delete(ndtn);
			TinNhan tn = tns.get(matn);
			tns.delete(tn);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/message";
		}

		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/message";
	}

	// Tin Nhan =====================
	// create
	@GetMapping("/tinnhan/create")
	public String createMessage(Model model) {
		model.addAttribute("ndtn", new NDTN());
		model.addAttribute("listnd", nds.getListTea());
		return "comp/tinnhan";
	}

	// view
	@GetMapping("/tinnhan/{id}/view")
	public String viewMessage(@PathVariable int id, Model model, Authentication auth) {
		if (nd.getMand() < 1)
			nd = Util.getUserLogin(nds, auth);

		model.addAttribute("ndtn", tns.get(id));
		return "comp/viewtinnhan";
	}

	// edit
	@GetMapping("/tinnhan/{id}/edit")
	public String editMessage(@PathVariable String id, Model model, Authentication auth) {
		if (nd.getMand() < 1)
			nd = Util.getUserLogin(nds, auth);

		String[] tam = id.split("z");
		NDTN ta = ndtns.getNDTNId(nd.getMand(), Integer.parseInt(tam[0]), Integer.parseInt(tam[1]));
		model.addAttribute("ndtn", ta);
		model.addAttribute("listnd", nds.getListTea());
		return "comp/tinnhan";
	}

	// delete
	/*
	 * @GetMapping("/tinnhan/{id}/delete") public String
	 * deletekhoa(@PathVariable Integer id, RedirectAttributes redirect) {
	 * TinNhan tn = tns.get(id); if (tn == null) {
	 * redirect.addFlashAttribute("success", "m.deletefail"); return
	 * "redirect:/message"; } tns.delete(tn);
	 * redirect.addFlashAttribute("success", "m.deletesuccess"); return
	 * "redirect:/message"; }
	 */

	// save
	@PostMapping("/tinnhan/save")
	public String saveMessage(@Valid TinNhanDto tndto, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "comp/tinnhan";
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TinNhan tn = new TinNhan();
		if (tndto.getMatn() == 0) // add
			tn = new TinNhan(tndto.getTieude(), df.format(new Date()), tndto.getNoidung());
		else { // edit
			tn = new TinNhan(tndto.getTieude(), df.format(new Date()), tndto.getNoidung());
			tn.setMatn(tndto.getMatn());
		}

		try {
			tns.saveOrUpdate(tn);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.savefail");
			return "redirect:/message";
		}

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/message";
	}

}