package com.base.utils;

import java.util.Random;

public class FormatString {
	/**
	 * 处理字符串为NULL
	 * @param str	待处理字符串内容
	 * @return		 处理后字符串内容
	 */
	public static String formatNull(String str){
		return str=str==null?"":str;
	}
	/**
	 * 处理字符串两端空格
	 * @param str  待处理字符串内容
	 * @return	        处理后字符串内容
	 */
	public static String formatSpace(String str){
		if(str == null){
			return "null";
		}
		else if(str.equals("\u0000")){
			return "null";
		}
		else{
			return str.trim();
		}
	}
	/**
	 * 处理XML元素中关键字
	 * @param str XML元素内容
	 * @return	     处理后XML元素内容
	 */
	public static String formatXml(String str){
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("’", "&apos;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
	/**
	 * 处理SQL中特殊字符
	 * @param str sql语句
	 * @return	     处理后sql语句
	 */
	public static String formatSql(String str){
		str = str.replaceAll("&", "'||chr(38)||'");
		str = str.replaceAll("'", "''");
		return str;
	}
	
	
	/**
	 * 产生随机数
	 */
	private static final Random random = new Random();
	public static int getRandom() {
		int theResult = 0;
		while (theResult <= 0) {
			theResult = random.nextInt();
		}
		return theResult;
	}
	
	public static String getRandom(int len) {
		String strResult = "";
		while (strResult.length() < len) {
			int intTemp = random.nextInt();
			strResult += intTemp;
		}
		return strResult.substring(strResult.length() - len);
	}
	

}
