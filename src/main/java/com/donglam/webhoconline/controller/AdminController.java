package com.donglam.webhoconline.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import com.donglam.webhoconline.model.KhoaHoc;
import com.donglam.webhoconline.model.LoaiKhoaHoc;
import com.donglam.webhoconline.service.BaiService;
import com.donglam.webhoconline.service.ChuongService;
import com.donglam.webhoconline.service.GVKHService;
import com.donglam.webhoconline.service.GiaoTrinhService;
import com.donglam.webhoconline.service.HVKHService;
import com.donglam.webhoconline.service.KhoaHocService;
import com.donglam.webhoconline.service.KhoaService;
import com.donglam.webhoconline.service.LoaiKhoaHocService;
import com.donglam.webhoconline.service.NguoiDungService;
import com.donglam.webhoconline.util.Util;

@Controller
public class AdminController {
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
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private int[] mangngay = { -30, -7, -5, -3, -1 };
	List<Double> mangdoanhthu = new ArrayList<>();
	List<Double> mangtonghop7ngay = new ArrayList<>();
	List<Double> mangtonghophomqua = new ArrayList<>();
	List<Integer> manglkh = new ArrayList<>();
	List<String> mangtenlkh = new ArrayList<>();
	
	@GetMapping("/index")
	public String adIndex(Model model, @RequestAttribute("visitorCounter") Integer counter) {
		model.addAttribute("gvsize", nds.getListTea().size());
		model.addAttribute("hvsize", nds.getListStu().size());
		model.addAttribute("ndsize", nds.getList().size());
		model.addAttribute("khsize", khs.getList().size());

		model.addAttribute("counter", counter);
		// Thong ke doanh thu theo thang truoc, tuan truoc, 5 ngay truoc, 3 ngay truoc,
		// hom qua
		
		if(mangdoanhthu.size()<1) { // chua thong ke
			for (int i = 0; i < mangngay.length; i++) {
				mangdoanhthu.add(read(mangngay[i]).size()>0? read(mangngay[i]).get(5):null);
			
				if(mangngay[i]==-7)
					mangtonghop7ngay.addAll(read(mangngay[i]));
				if(mangngay[i]==-1)
					mangtonghophomqua.addAll(read(mangngay[i]));
			}
		}
		if(mangtenlkh.size()<1) {
			for(LoaiKhoaHoc lkh:lkhs.getList()) {
				mangtenlkh.add(lkh.getTenloaikh());
				manglkh.add(lkh.getKhoahocs().size());
			}
		}
		model.addAttribute("mangdoanhthu",mangdoanhthu);
		model.addAttribute("mangtonghop7ngay",mangtonghop7ngay);
		model.addAttribute("mangtonghophomqua",mangtonghophomqua);
		
		model.addAttribute("mangtenlkh", mangtenlkh);
		model.addAttribute("manglkh",manglkh);
		return "adindex";
	}

	// @Scheduled(cron = "*/10 * * ? * *")
	public void scheduleTaskRead() {
		// read(-2); // -1 is yesterday. return sum range date

	}

	// @Scheduled(cron = "*/5 * * ? * *")
	public void scheduleTaskWrite() {
		double sotien = 0;
		for (KhoaHoc kh : khs.getList()) {
			sotien += kh.getHocphi() * kh.getSoluonght();
		}
		String str = nds.getListStu().size() + " " + nds.getListTea().size() + " " + nds.getList().size() + " "
				+ gts.getList().size() + " " + khs.getList() + " " + sotien;
		write(str);
	}

	private List<Double> read(int num) { // return total last days
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String curDate = dateFormat.format(new Date());
		String lastday = Util.addDate(curDate, "yyyyMMdd", num);
		List<Double> mangso = new ArrayList<>(); // mang result
		// int cHocVien=0,cGV=0,cNguoiDung=0,cGiaoTrinh=0,cDoanhThu=0;
		for (String ht : Util.getListDate(lastday, curDate)) {
			String path = System.getProperty("user.dir") + "/src/main/resources/static/log/" + ht;
			File file = new File(path);
			if (!file.exists()) {
				continue;
			}
			logger.info("Read file " + path);
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				for (String line; (line = br.readLine()) != null;) {
					String[] mangtam = line.split(" "); // mang ki tu
					logger.info(line);
					for (int i = 0; i < mangtam.length; i++) {
						if (mangso.size() == mangtam.length) // da co truoc do
							mangso.set(i, mangso.get(i) + Double.parseDouble(mangtam[i]));
						else
							mangso.add(Double.parseDouble(mangtam[i]));
					}
				}

			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return mangso;
	}

	private void write(String content) {
		try {
			// countHocVien GV Nguoidung KhoaHoc GiaoTrinh DoanhThu
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String path = System.getProperty("user.dir") + "/src/main/resources/static/log/" + df.format(new Date());
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			logger.info("Write file " + path);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

}
