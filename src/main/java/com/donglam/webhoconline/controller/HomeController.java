package com.donglam.webhoconline.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.donglam.webhoconline.model.KhoaHoc;
import com.donglam.webhoconline.model.NguoiDung;
import com.donglam.webhoconline.service.KhoaHocService;
import com.donglam.webhoconline.service.LoaiKhoaHocService;
import com.donglam.webhoconline.service.NguoiDungService;

@Controller
public class HomeController {

	@Autowired
	private LoaiKhoaHocService lkhs;

	@Autowired
	private KhoaHocService khs;

	@Autowired
	private NguoiDungService nds;

	@GetMapping("/")
	public String index(Model model, @AuthenticationPrincipal UserDetails user) {
		model.addAttribute("listcate", lkhs.getList());

		List<KhoaHoc> listkh = khs.getListKHDaDuyet(true);
		model.addAttribute("listkh", listkh.subList(0, listkh.size() > 3 ? 3 : listkh.size()));
		if (listkh.size() > 3)
			model.addAttribute("listkh2", listkh.subList(3, listkh.size() > 6 ? 6 : listkh.size()));

		List<NguoiDung> listgv = nds.getListTea();
		model.addAttribute("listgv", listgv.subList(0, listgv.size() > 3 ? 3 : listgv.size()));
		if (listgv.size() > 3)
			model.addAttribute("listgv2", listgv.subList(3, listgv.size() > 6 ? 6 : listgv.size()));

		if (user != null)
			System.out.println(user.getUsername());
		return "index";
	}

	@RequestMapping("/home")
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
        Enumeration e = (Enumeration) (session.getAttributeNames());

        while ( e.hasMoreElements())
        {
            Object tring;
            if((tring = e.nextElement())!=null)
            {
                System.out.println(session.getValue((String) tring));
                System.out.println("<br/>");
            }

        }
		return "home";
	}
	// modal login
		@GetMapping("/term")
		public String term() {
			return "term";
		}
		
	// modal login
	@GetMapping("/help")
	public String help() {
		return "help";
	}

	@GetMapping("/loginregister")
	public String login(Model model) {
		return "loginregister";
	}
	
	@GetMapping("/error")
	public String logion(Model model) {
		return "error";
	}

	// modal login
	/*
	 * @GetMapping("/register") public String register(Model model) { return
	 * "register"; }
	 */

	// some other pages
	@GetMapping("/contact")
	public String contact(Model model) {
		return "contact";
	}
	
	
}
