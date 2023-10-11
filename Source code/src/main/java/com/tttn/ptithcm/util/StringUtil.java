package com.tttn.ptithcm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class StringUtil {
	
	

	public static String getFormatterDate(Date date,String formatter){
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}
	

	public static boolean isAjax(HttpServletRequest request){
		String header = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(header))return true;
		return false;
	}
	
	public static boolean isEmpty(String str)
	{
		if(str == null || "".equals(str))
		{
			return true; //Trở lại đúng, cho biết trống
		}else {
			return false; //Trở lại sai
		}
	}
	
	
}
