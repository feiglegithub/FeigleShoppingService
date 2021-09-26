package com.feigle.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

//	public static final String SERVER_ADRRESS = "http://n2571455a8.wicp.vip";
	public static final String SERVER_ADRRESS = "http://localhost:8088";
	public Utils() {
		// TODO Auto-generated constructor stub
	}

	public static String getTimeStamp() {// 获取时间戳
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
}
