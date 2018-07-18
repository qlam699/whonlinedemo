package com.donglam.webhoconline.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.donglam.webhoconline.model.GVKH;
import com.donglam.webhoconline.model.HVKH;
import com.donglam.webhoconline.model.NguoiDung;
import com.donglam.webhoconline.service.NguoiDungService;

public class Util {
	private static final Logger logger = LoggerFactory.getLogger(Util.class);
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static String changeDateTime(String oldDateString, boolean btSangLa) throws ParseException {
		// mac dinh la bt sang la
		String OLD_FORMAT = "dd-MM-yyyy HH:mm:ss";
		String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss";
		if (!btSangLa) {
			NEW_FORMAT = "dd-MM-yyyy HH:mm:ss";
			OLD_FORMAT = "yyyy-MM-dd HH:mm:ss";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d = sdf.parse(oldDateString);
		sdf.applyPattern(NEW_FORMAT);
		return sdf.format(d);
	}
	public static NguoiDung getUserLogin(NguoiDungService nds,Authentication auth) {
		
		if(auth == null) //not login
			return null;
		NguoiDung nd=new NguoiDung();
		if(auth instanceof OAuth2Authentication || auth.getPrincipal() instanceof OAuth2Authentication) { //social account
			System.out.println("Social user "); 
			OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) auth.getPrincipal(); 
			Authentication authentication = oAuth2Authentication.getUserAuthentication();
			Map<String, String> details = new LinkedHashMap<>();
			details = (Map<String, String>) authentication.getDetails();
			nd= nds.findByConfirmationToken( details.get("id")==null?details.get("sub"):details.get("id") );
		}else { //local account
			System.out.println("Local user ");
			nd=nds.findByEmail(auth.getName());
		}
		return nd;
		
	}
	
	public static boolean registered(NguoiDung nd,String makh) {
		for (HVKH a : nd.getHvkhs()) {
			if (a.getHvkhid().getKhoahoc().getMakh().equals(makh)) {
				return true;
			}
		}
		
		for (GVKH a : nd.getGvkhs()) {
			if (a.getGvkhid().getKhoahoc().getMakh().equals(makh)) {
				return true;
			}
		}
		return false;
	}
	public static String getBaseURl(HttpServletRequest request) {
	    String scheme = request.getScheme();
	    String serverName = request.getServerName();
	    int serverPort = request.getServerPort();
	    String contextPath = request.getContextPath();
	    StringBuffer url =  new StringBuffer();
	    url.append(scheme).append("://").append(serverName);
	    if ((serverPort != 80) && (serverPort != 443)) {
	        url.append(":").append(serverPort);
	    }
	    url.append(contextPath);
	    if(url.toString().endsWith("/")){
	    	url.append("/");
	    }
	    return url.toString();
	}
	public static List<String> getListDate(String fromDate, String toDate) {
	     
        Date fdate = convertDate(fromDate, "yyyyMMdd");
        Date tdate = convertDate(toDate, "yyyyMMdd");

        List<String> ymdList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fdate);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        while (calendar.getTime().before(tdate)) {
            Date result = calendar.getTime();
            ymdList.add(dateFormat.format(result));
            calendar.add(Calendar.DATE, 1);
        }
        ymdList.add(dateFormat.format(tdate));
        return ymdList;
    }
	
	public static String addDate(String strDate, String format, int num) {

		if (strDate == null || strDate.isEmpty()) {
			logger.error("Add Date faild: date=" + strDate);
		}
		Date date = convertDate(strDate, format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, num);
		return convertStr(cal.getTime(), format);

	}

	public static Date convertDate(String dateStr, String format) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			if (format != null) {
				dateFormat = new SimpleDateFormat(format);
			}
			return dateFormat.parse(dateStr);
		} catch (Exception ex) {
			logger.error("Cant not convert date= " + dateStr + ", format=" + format, ex);
		}
		return null;
	}

	public static String convertStr(Date date, String format) {
		try {
			DateFormat dateFormat;
			if (format == null) {
				dateFormat = new SimpleDateFormat(DATE_FORMAT);
			} else {
				dateFormat = new SimpleDateFormat(format);
			}
			return dateFormat.format(date);
		} catch (Exception ex) {
			logger.error("Cant convert: date=" + date + ", format=" + format, ex);
		}
		return null;

	}
}