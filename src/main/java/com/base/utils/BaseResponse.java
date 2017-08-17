package com.base.utils;

import com.sinkon.utils.ConstantUtil;

public class BaseResponse  {
	private String code;//返回代码
	private String msg;//返回接口状态信息
	public BaseResponse(){//默认设置返回类型正确
		this.setCode(ConstantUtil.CODE_SUCCESS);
		this.setMsg(ConstantUtil.MSG_SUCCESS);
	}
	
	public BaseResponse(String code, String msg) {
		this.setCode(code);
		this.setMsg(msg);
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
