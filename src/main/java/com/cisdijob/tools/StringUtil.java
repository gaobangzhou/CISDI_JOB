package com.cisdijob.tools;

public class StringUtil {
	/**
	 * 将Object转换成String，同时去掉空格
	 * @param obj
	 * @return
	 */
	public static String formatString(Object obj){
		String result="";
		if(obj!=null){
			result=obj.toString().trim();	
		}
		return result;
	}

}
