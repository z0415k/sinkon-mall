package com.sinkon.dto;

import com.sinkon.utils.ConstantUtil;

/**
 * 封装json对象，所有返回结果都使用它
 */
public class Response<T> {
	private String msg;// 错误信息
	private String code;
	private T data;// 成功时返回的数据

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Response(){//默认设置返回类型正确
		this.setCode(ConstantUtil.CODE_SUCCESS);
		this.setMsg(ConstantUtil.MSG_SUCCESS);
	}
	
	public Response(String code, String msg) {
		this.setCode(code);
		this.setMsg(msg);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", msg=" + msg + ", data= + data + ]";
	}

}
