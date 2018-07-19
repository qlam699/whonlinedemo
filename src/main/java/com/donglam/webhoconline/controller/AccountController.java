package com.donglam.webhoconline.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donglam.webhoconline.model.NguoiDung;
import com.donglam.webhoconline.model.NguoiDungDto;
import com.donglam.webhoconline.model.Quyen;
import com.donglam.webhoconline.model.QuyenDto;
import com.donglam.webhoconline.model.UserDto;
import com.donglam.webhoconline.service.EmailService;
import com.donglam.webhoconline.service.NguoiDungService;
import com.donglam.webhoconline.service.QuyenService;
import com.donglam.webhoconline.util.Util;

@Controller
public class AccountController {

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private NguoiDungService nds;
	@Autowired
	private QuyenService qs;
	@Autowired
	private EmailService emailService;

	@GetMapping("/account")
	public String account(Model model) {
		model.addAttribute("dsq", qs.getList());
		model.addAttribute("ds", nds.getList());
		return "adaccount";
	}

	///////////////// QUYEN ///////////////////////

	// create
	@GetMapping("/quyen/create")
	public String createRole(Model model) {
		model.addAttribute("quyen", new Quyen());
		return "comp/quyen";
	}

	// edit
	@GetMapping("/quyen/{id}/edit")
	public String editRole(@PathVariable Integer id, Model model) {
		model.addAttribute("quyen", qs.get(id));
		return "comp/quyen";
	}

	// delete
	@GetMapping("/quyen/{id}/delete")
	public String deleteRole(@PathVariable Integer id, RedirectAttributes redirect) {
		Quyen q = qs.get(id);
		if (q == null) {
			redirect.addFlashAttribute("success", "m.deletefail");
			return "redirect:/account";
		}

		try {
			q.setIsdeleted(true);
			qs.saveOrUpdate(q);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/account";
		}

		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/account";
	}

	// save
	@PostMapping("/quyen/save")
	public String saveRole(@Valid QuyenDto qdto, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "comp/quyen";
		}

		Quyen q = new Quyen();
		if (qdto.getMaquyen() == 0)
			q = new Quyen(qdto.getTenquyen());
		else {
			q = new Quyen(qdto.getTenquyen());
			q.setMaquyen(qdto.getMaquyen());
		}

		try {
			qs.saveOrUpdate(q);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.savefail");
			return "redirect:/account";
		}

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/account";
	}

	///////////////// NGUOI DUNG ///////////////////////

	// create
	@GetMapping("/nguoidung/create")
	public String createUser(Model model) {
		model.addAttribute("nd", new NguoiDungDto());
		model.addAttribute("listquyen", qs.getListQuyen());

		return "comp/nguoidung";
	}

	// edit
	@GetMapping("/nguoidung/{id}/edit")
	public String editUser(@PathVariable int id, Model model) {
		model.addAttribute("nd", nds.get(id));
		model.addAttribute("listquyen", qs.getListQuyen());

		return "comp/nguoidung";
	}

	// delete
	@GetMapping("/nguoidung/{id}/delete")
	public String deleteUser(@PathVariable int id, RedirectAttributes redirect) {
		NguoiDung nd = nds.get(id);
		if (nd == null) {
			redirect.addFlashAttribute("success", "m.deletesuccess");
			return "redirect:/account";
		}

		try {
			nd.setIsdeleted(true);
			nds.saveOrUpdate(nd);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.deletefail");
			return "redirect:/account";
		}

		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/account";
	}

	// save
	@PostMapping("/nguoidung/save")
	public String saveUser(@Valid NguoiDungDto nddto, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "comp/nguoidung";
		}

		NguoiDung nd = new NguoiDung();

		if (nddto.getMand() > 0) { // edit
			nd = new NguoiDung(nddto.getMand(), nddto.getHovaten(), nddto.isPhai(), nddto.getNgaysinh(),
					nddto.getDiachi(), nddto.getCmnd(), nddto.getEmail(), nddto.getHinh(), nddto.getMota(),
					nddto.getQuyen());

			nd.setNgaycap(nddto.getNgaycap());
			nd.setLoaitaikhoan(nddto.getLoaitaikhoan());
			nd.setMatkhau(nddto.getMatkhau());
			nd.setKichhoat(nddto.isKichhoat());

		} else { // add
			nd = new NguoiDung(nddto.getHovaten(), nddto.isPhai(), nddto.getNgaysinh(), nddto.getDiachi(),
					nddto.getCmnd(), nddto.getEmail(), nddto.getHinh(), nddto.getMota(), nddto.getQuyen());

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			nd.setNgaycap(df.format(new Date()));
			nd.setKichhoat(nddto.isKichhoat());
			nd.setLoaitaikhoan("local");
			nd.setMatkhau(bCryptPasswordEncoder.encode("123123123"));
		}

		try {
			nds.saveOrUpdate(nd);
		} catch (Exception ee) {
			redirect.addFlashAttribute("error", "m.savefail");
			return "redirect:/account";
		}

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/account";
	}

	// ===================================

	@Autowired
	public AccountController(BCryptPasswordEncoder bCryptPasswordEncoder, NguoiDungService nds,
			EmailService emailService) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.nds = nds;
		this.emailService = emailService;
	}

	// Return registration form template
	@GetMapping("/register")
	public String showRegistrationPage(Model model) {
		model.addAttribute("nguoidung", new NguoiDung());
		return "register";
	}

	@GetMapping("/forgetpassword")
	public String forgetPassword(Model model) {
		model.addAttribute("nguoidung", new NguoiDung());
		return "forgetpassword";
	}

	@GetMapping("/changepassword")
	public String changePassword(Model model) {
		model.addAttribute("nguoidung", new UserDto());
		return "changepassword";
	}

	@PostMapping("/forgetpassword")
	public @ResponseBody String processForgetPassword(Model model, @Valid NguoiDung nd, BindingResult bindingResult,
			HttpServletRequest request) {
		// Lookup NguoiDung in database by e-mail
		NguoiDung NguoiDungExists = nds.findByEmail(nd.getEmail());
		// neu email da ton tai trong local
		if (NguoiDungExists == null || !NguoiDungExists.getLoaitaikhoan().equals("local")) {
			model.addAttribute("error", "m.registerduplicate");
			return "exist";
		}

		String alphabet = "1234567980ABCDEFGHJKLMNOPQXYZ";
		String passnew = RandomStringUtils.random(8, alphabet);

		NguoiDungExists.setKhoabimat(UUID.randomUUID().toString());
		NguoiDungExists.setMatkhau(bCryptPasswordEncoder.encode(passnew));
		NguoiDungExists.setKichhoat(false);

		try {
			nds.saveOrUpdate(NguoiDungExists);
		} catch (Exception ee) {
			return "error";
		}

		String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

		try {
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(NguoiDungExists.getEmail());
			registrationEmail.setSubject("Reset Password Confirmation");

			registrationEmail.setText("New password: " + passnew
					+ "\nPlease login with new password and then change password! \nTo confirm reset your password, please click the link below:\n"
					+ appUrl + "/confirm?token=" + NguoiDungExists.getKhoabimat());
			registrationEmail.setFrom("noreply@domain.com");

			emailService.sendEmail(registrationEmail);
			model.addAttribute("ok", "A confirmation e-mail has been sent to " + NguoiDungExists.getEmail());
		} catch (Exception ee) {
			return "error";
		}
		return nd.getEmail();

	}

	@PostMapping("/changepassword")
	public String processChangePassword(@Valid UserDto nd, RedirectAttributes re, Model model,Authentication auth) {
		NguoiDung nguoidung = Util.getUserLogin(nds,auth);
		if (nguoidung.getLoaitaikhoan().equals("local")
				&& bCryptPasswordEncoder.matches(nd.getPasswordold(), nguoidung.getMatkhau())) {
			if (nd.getPasswordnew().equals(nd.getPasswordconfirm())) {
				nguoidung.setMatkhau(bCryptPasswordEncoder.encode(nd.getPasswordnew()));

				try {
					nds.saveOrUpdate(nguoidung);
				} catch (Exception ee) {
					re.addFlashAttribute("error", "m.changepwfail");
					return "redirect:/";
				}

				re.addFlashAttribute("success", "m.changepwsuccess");
			}
		} else {
			re.addFlashAttribute("error", "Change password Fail ");
		}
		return "redirect:/";
	}

	// Process form input data
	@PostMapping("/register")
	public @ResponseBody String processRegistrationForm(Model model, @Valid NguoiDung nd, BindingResult bindingResult,
			HttpServletRequest request) {
		// Lookup NguoiDung in database by e-mail
		NguoiDung NguoiDungExists = nds.findByEmail(nd.getEmail());

		// neu email da ton tai trong local
		if (NguoiDungExists != null && NguoiDungExists.getLoaitaikhoan().equals("local")) {
			model.addAttribute("error", "m.registerduplicate");
			return "exist";
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "m.registerfail");
			return "error";
		} else {
			// Disable NguoiDung until they click on confirmation link in email
			nd.setKichhoat(false);
			nd.setLoaitaikhoan("local");
			nd.setMatkhau(bCryptPasswordEncoder.encode(nd.getMatkhau()));

			// Generate random 36-character string token for confirmation link
			nd.setKhoabimat(UUID.randomUUID().toString());
			nd.setQuyen(qs.get(4));
			try {
				nds.saveOrUpdate(nd);
			} catch (Exception ee) {
				return "error";
			}

			String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			try {
				SimpleMailMessage registrationEmail = new SimpleMailMessage();
				registrationEmail.setTo(nd.getEmail());
				registrationEmail.setSubject("Registration Confirmation");
				registrationEmail.setText("To confirm your e-mail address, please click the link below:\n" + appUrl
						+ "/confirm?token=" + nd.getKhoabimat());
				registrationEmail.setFrom("noreply@domain.com");

				emailService.sendEmail(registrationEmail);
				model.addAttribute("ok", "A confirmation e-mail has been sent to " + nd.getEmail());
			} catch (Exception ee) {
				return "error";
			}
		}
		return nd.getEmail();
	}

	// Process confirmation link
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirmRegistration(Model model, @RequestParam("token") String token, RedirectAttributes re) {
		NguoiDung nd = nds.findByConfirmationToken(token);
		if (nd == null) { // No token found in DB
			re.addFlashAttribute("error", "m.activefail");
		} else if (nd.isKichhoat()) {
			re.addFlashAttribute("success", "m.activesuccess");
		} else { // Token found
			nd.setKichhoat(true);

			try {
				nds.saveOrUpdate(nd);
			} catch (Exception ee) {
				re.addFlashAttribute("error", "m.activefail");
				return "redirect:/";
			}

			re.addFlashAttribute("success", "m.activesuccess");
		}

		return "redirect:/";
	}

	@GetMapping("/login")
	public String getLogin(@RequestParam("st") String st, RedirectAttributes re,Authentication auth) {
		NguoiDung nd = Util.getUserLogin(nds,auth);
		if (st.equals("error")) {
			re.addFlashAttribute("error", "m.loginfail");
			return "redirect:/";
		} else if (st.equals("success")) {
			if (nd != null && !nd.isKichhoat()) {
				re.addFlashAttribute("error", "m.notyetactive");
				SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
				return "redirect:/";
			}

			re.addFlashAttribute("success", "m.loginsuccess");
			return "redirect:/";
		}

		return "loginregister";
	}
	/*
	 * @PostMapping("/login") public String login() {
	 * System.out.println("login ne em"); return "redirect:/"; }
	 */
}
