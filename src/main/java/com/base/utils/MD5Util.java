package com.base.utils;

import java.security.MessageDigest;

/**
 * MD5 工具类
 */
public class MD5Util {
	/**
	 * 根据传入的字符串返回md5字符串 
	 * @param source 源字符串
	 * @return MD5 密文
	 */
	public final static String MD5(String source){
		return MD5(source,false);
	}
	
	/**
	 * 根据传入字符串 返回Md5字符串
	 * @param source	源字符串
	 * @param lowercase 是否以小写字母的形式返回
	 * @return
	 */
	public final static String MD5(String source,boolean lowercase) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        try {
            byte[] btInput = source.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            String res = new String(str);
            return lowercase?res.toLowerCase():res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
