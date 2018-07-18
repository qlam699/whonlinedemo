package com.donglam.webhoconline.controller;

import java.text.DateFormat;
import java.text.ParseException;
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

import com.donglam.webhoconline.model.HVKHId;
import com.donglam.webhoconline.model.KhoaHoc;
import com.donglam.webhoconline.model.NguoiDung;
import com.donglam.webhoconline.model.ThaoLuan;
import com.donglam.webhoconline.model.ThaoLuanDto;
import com.donglam.webhoconline.model.ThaoLuanId;
import com.donglam.webhoconline.service.HVKHService;
import com.donglam.webhoconline.service.KhoaHocService;
import com.donglam.webhoconline.service.NguoiDungService;
import com.donglam.webhoconline.service.ThaoLuanService;
import com.donglam.webhoconline.util.Util;

@Controller
public class DiscussionController {

	@Autowired
	private ThaoLuanService tls;
	@Autowired
	private KhoaHocService khs;
	@Autowired
	private NguoiDungService nds;
	@Autowired
	private HVKHService hvkhs;
	
	NguoiDung nd = new NguoiDung();

	@GetMapping("/discussion")
	public String discussion(Model model, Authentication auth) {
		nd = Util.getUserLogin(nds, auth);

		model.addAttribute("dstl", tls.getList());
		model.addAttribute("listkh", khs.getListGVKH(nd.getMand()));
		model.addAttribute("khoahoc", new KhoaHoc());

		return "addiscussion";
	}

	int idmatl = 0;
	String idmakh;

	@GetMapping("/forum/{id}")
	public String forum(@PathVariable String id, Model model, Authentication auth) {
		nd = Util.getUserLogin(nds, auth);

		if (hvkhs.get(new HVKHId(nd, khs.get(id))) ==null )
			return "redirect:/";
		
		idmakh = id;
		model.addAttribute("thaoluan", new ThaoLuan());
		model.addAttribute("ftl", tls.getForumKH(id));
		model.addAttribute("mand", nd.getMand());

		return "forumlist";
	}

	@GetMapping("/forum/{id1}/{id2}")
	public String forumDetail(@PathVariable String id1, @PathVariable int id2, Model model, Authentication auth) {
		if (nd.getMand() < 1)
			nd = Util.getUserLogin(nds, auth);
		if (!Util.registered(nd, id1))
			return "redirect:/";
		model.addAttribute("thaoluan", new ThaoLuan());
		model.addAttribute("qtl", tls.getQuestion(id2));
		model.addAttribute("rtl", tls.getReply(id2));
		model.addAttribute("crtl", tls.getCountReply(id2));
		model.addAttribute("user", nd);

		idmatl = id2;
		idmakh = id1;
		/* idmakh = tls.getQuestion(id).getKhoahoc().getMakh(); */

		return "forum";
	}

	// =========================== thao luan question
	// create
	@GetMapping("/diendan/create")
	public String createForum(Model model) {
		model.addAttribute("thaoluan", new ThaoLuan());
		return "comp/diendan";
	}

	// edit
	@GetMapping("/diendan/{id}/edit")
	public String editForum(@PathVariable String id, Model model) throws ParseException {
		String[] tam = id.split("z");
		String dt = tam[1] + " " + tam[2];
		model.addAttribute("thaoluan", tls.get(new ThaoLuanId(nds.get(Integer.parseInt(tam[0])), dt)));
		return "comp/diendan";
	}

	// ================= thao luan admin ====================
	/*
	 * // create
	 * 
	 * @GetMapping("/thaoluan/create") public String createDiscussion(Model
	 * model) { model.addAttribute("thaoluan", new ThaoLuan()); //
	 * model.addAttribute("listnd", nds.getListTeaStu()); //
	 * model.addAttribute("listkh", khs.getList());
	 * 
	 * return "comp/thaoluan"; }
	 */

	// view
	@GetMapping("/thaoluan/{id}/view")
	public String viewDiscussion(@PathVariable String id, Model model) throws ParseException {
		String[] tam = id.split("z");
		String dt = tam[1] + " " + tam[2];

		model.addAttribute("thaoluan", tls.get(new ThaoLuanId(nds.get(Integer.parseInt(tam[0])), dt)));
		return "comp/viewthaoluan";
	}

	/*
	 * // edit
	 * 
	 * @GetMapping("/thaoluan/{id}/edit") public String
	 * editDiscussion(@PathVariable String id, Model model) throws
	 * ParseException { String[] tam = id.split("z"); String dt = tam[1] + " " +
	 * tam[2];
	 * 
	 * model.addAttribute("thaoluan", tls.get(new
	 * ThaoLuanId(nds.get(Integer.parseInt(tam[0])), dt)));
	 * model.addAttribute("listnd", nds.getListTeaStu());
	 * model.addAttribute("listkh", khs.getList()); return "comp/thaoluan"; }
	 */

	// ======================================================================================

	// delete
	@GetMapping("/thaoluan/{type}/{id}/delete")
	public String deleteDiscussion(@PathVariable String type, @PathVariable String id, RedirectAttributes redirect,
			Authentication auth) {
		if (nd.getMand() < 1)
			nd = Util.getUserLogin(nds, auth);
		String[] tam = id.split("z");
		String dt = tam[1] + " " + tam[2];
		int mand = Integer.parseInt(tam[0]);

		NguoiDung ng = nds.get(mand);
		ThaoLuan tl = tls.get(new ThaoLuanId(ng, dt));

		boolean isAd = false;

		if (tl == null || ((mand != nd.getMand()) && !isAd)) { // dang nhap
			// quyen
			if (type.equals("r")) {
				redirect.addFlashAttribute("error", "m.deletefail");
				return "redirect:/forum/" + idmakh + "/" + idmatl;
			} else if (type.equals("a")) {
				// admin
				redirect.addFlashAttribute("error", "m.deletefail");
				return "redirect:/discussion";
			}
		}

		if (type.equals("r")) {
			try {
				tls.removeTL(Integer.parseInt(tam[0]), dt);
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.deletefail");
				return "redirect:/forum/" + idmakh + "/" + idmatl;
			}

			redirect.addFlashAttribute("success", "m.deletesuccess");
			return "redirect:/forum/" + idmakh + "/" + idmatl;
		} else if (type.equals("q")) {
			int matl = tl.getMatl();
			for (ThaoLuan a : tls.getListByTopic(matl)) {
				try {
					tls.removeTL(a.getThaoluanid().getNguoidung().getMand(), a.getThaoluanid().getTgdang());
				} catch (Exception ee) {
					redirect.addFlashAttribute("error", "m.deletefail");
					return "redirect:/forum/" + idmakh;
				}
			}

			redirect.addFlashAttribute("success", "m.deletesuccess");
			return "redirect:/forum/" + idmakh;
		}

		/*
		 * // admin try { tls.removeTL(Integer.parseInt(tam[0]), dt); } catch
		 * (Exception ee) { redirect.addFlashAttribute("error", "m.deletefail");
		 * return "redirect:/discussion"; }
		 */

		redirect.addFlashAttribute("success", "m.deletesuccess");
		return "redirect:/discussion";
	}

	// save
	@PostMapping("/thaoluan/{type}/save")
	public String saveDiscussion(@PathVariable String type, @Valid ThaoLuanDto tldto, BindingResult result,
			RedirectAttributes redirect, Authentication auth) {
		// tldto.setNoidung(HtmlUtils.htmlEscape(tldto.getNoidung(),"UTF-8"));
		// reply
		if (nd.getMand() < 1)
			nd = Util.getUserLogin(nds, auth);
		if (type.equals("r")) {
			if (result.hasErrors()) {
				String url = "forum/" + idmakh + "/" + idmatl;
				return url;
			}
			if (tldto.getThaoluanid().getTgdang() != "") { // edit save reply
				try {
					tls.saveOrUpdate(
							new ThaoLuan(khs.get(idmakh), tldto.getThaoluanid(), "", tldto.getNoidung(), idmatl));
				} catch (Exception ee) {
					redirect.addFlashAttribute("error", "m.savefail");
					return "redirect:/forum/" + idmakh + "/" + idmatl;
				}
			} else { // add save reply
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				NguoiDung t = nds.get(nd.getMand());
				ThaoLuanId tlid = new ThaoLuanId(t, df.format(new Date()));

				try {
					tls.saveOrUpdate(new ThaoLuan(khs.get(idmakh), tlid, "", tldto.getNoidung(), idmatl));
				} catch (Exception ee) {
					redirect.addFlashAttribute("error", "m.savefail");
					return "redirect:/forum/" + idmakh + "/" + idmatl;
				}
			}

			redirect.addFlashAttribute("success", "m.savesuccess");
			return "redirect:/forum/" + idmakh + "/" + idmatl;
		}

		// question
		if (type.equals("q")) {
			if (result.hasErrors()) {
				return "comp/diendan";
			}

			idmatl = tls.getNextMatl();
			tldto.getThaoluanid().setNguoidung(nd);
			if (tldto.getThaoluanid().getTgdang() != "") { // edit
				try {
					tls.saveOrUpdate(new ThaoLuan(khs.get(idmakh), tldto.getThaoluanid(), tldto.getTieude(),
							tldto.getNoidung(), idmatl));
				} catch (Exception ee) {
					redirect.addFlashAttribute("error", "m.savefail");
					return "redirect:/forum/" + idmakh;
				}

			} else { // add
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ThaoLuanId tlid = new ThaoLuanId(nd, df.format(new Date()));

				try {
					tls.saveOrUpdate(
							new ThaoLuan(khs.get(idmakh), tlid, tldto.getTieude(), tldto.getNoidung(), idmatl));
				} catch (Exception ee) {
					redirect.addFlashAttribute("error", "m.savefail");
					return "redirect:/forum/" + idmakh;
				}
			}

			redirect.addFlashAttribute("success", "m.savesuccess");
			return "redirect:/forum/" + idmakh;
		}

		// admin
		if (result.hasErrors()) {
			return "comp/thaoluan";
		}

		if (tldto.getThaoluanid().getTgdang() != "") { // edit
			try {
				tls.saveOrUpdate(new ThaoLuan(tldto.getKhoahoc(), tldto.getThaoluanid(), tldto.getTieude(),
						tldto.getNoidung(), tldto.getMatl()));
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/discussion";
			}

		} else { // add
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ThaoLuanId tlid = new ThaoLuanId(tldto.getThaoluanid().getNguoidung(), df.format(new Date()));

			try {
				tls.saveOrUpdate(
						new ThaoLuan(tldto.getKhoahoc(), tlid, tldto.getTieude(), tldto.getNoidung(), tldto.getMatl()));
			} catch (Exception ee) {
				redirect.addFlashAttribute("error", "m.savefail");
				return "redirect:/discussion";
			}
		}

		redirect.addFlashAttribute("success", "m.savesuccess");
		return "redirect:/discussion";
	}

}