package com.locus.jlo.web.util;

import javax.servlet.http.HttpServletRequest;

public class UserAgentUtil {
	public static String getBrowserVersion(HttpServletRequest request) {
		String userAgent =request.getHeader("User-Agent");
		String user = userAgent.toLowerCase();
		if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE"))
					.split(";")[0];
			return substring.split(" ")[0].replace("MSIE", "IE") + "-"
					+ substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			return (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0])
					.split("/")[0]
					+ "-"
					+ (userAgent.substring(userAgent.indexOf("Version")).split(
							" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				return (userAgent.substring(userAgent.indexOf("Opera")).split(
						" ")[0]).split("/")[0]
						+ "-"
						+ (userAgent.substring(userAgent.indexOf("Version"))
								.split(" ")[0]).split("/")[1];
			else if (user.contains("opr"))
				return ((userAgent.substring(userAgent.indexOf("OPR")).split(
						" ")[0]).replace("/", "-")).replace("OPR", "Opera");
		} else if (user.contains("chrome")) {
			return (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0])
					.replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1)
				|| (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1)
				|| (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1)
				|| (user.indexOf("mozilla/3") != -1)) {
			// browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/",
			// "-");
			return "Netscape-?";

		} else if (user.contains("firefox")) {
			return (userAgent.substring(userAgent.indexOf("Firefox"))
					.split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			return "IE";
		} else {
			return "UnKnown, More-Info: " + userAgent;
		}
		return "UnKnown, More-Info: " + userAgent;
	}

	public static String getOsVersion(HttpServletRequest request) {
		String userAgent =request.getHeader("User-Agent");
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			return "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			return "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			return "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			return "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			return "IPhone";
		} else {
			return "UnKnown, More-Info: " + userAgent;
		}
	}

	public static String getClientIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		
		return ipAddress;
	}
}
