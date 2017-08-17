package com.base.utils;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

/**
 * 
 * 功能描述: 对象工具类
 */
public final class ObjectUtils {
	/**
	 * 
	 * 把对象转成XML格式字符串
	 */
	public static String toXml(Object o) {
		JSON jsonObject = JSONSerializer.toJSON(o);
		XMLSerializer xmlSerializer = new XMLSerializer();
		return xmlSerializer.write(jsonObject);
	}
	/**
	 * 
	 * 把对象转成json数据
	 */
	public static String toJSON(Object o) {
		JSON jsonObject = JSONSerializer.toJSON(o);
		return jsonObject.toString();
	}
}
