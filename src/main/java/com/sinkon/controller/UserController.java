package com.sinkon.controller;


import javax.ws.rs.FormParam;

import com.base.utils.BaseResponse;
import com.sinkon.dto.Response;
import com.sinkon.utils.CheckUtils;
import com.sinkon.utils.ConstantUtil;
import com.sinkon.utils.Redis;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinkon.entity.User;
import com.sinkon.service.UserService;

@Controller
@RequestMapping(value="/User")
public class UserController {
	@Autowired
	private UserService userService;
	private Redis redis=new Redis();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	@ResponseBody
	public Response getUserInfo(@FormParam("openId") String openId) {
		Response resp = new Response();
		Object userRedis = null;
		if (org.springframework.util.StringUtils.isEmpty(openId)) {
			return new Response(ConstantUtil.CODE_PARAM_ERROR, ConstantUtil.MSG_PARAM_ERROR);
		}
		try {
			userRedis = userService.getUserByOpenId(openId);
			if (CheckUtils.isEmpty(userRedis)) {
				return new Response(ConstantUtil.CODE_DATA_NULL, ConstantUtil.MSG_DATA_NULL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ConstantUtil.CODE_ERROR, ConstantUtil.MSG_ERROR);
		}
		resp.setData(userRedis);
		return resp;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Response addUser(@RequestBody User user) {
		Response resp = new Response();
		if(CheckUtils.isEmpty(user)){
			return new Response(ConstantUtil.CODE_PARAM_ERROR, ConstantUtil.MSG_PARAM_ERROR);
		}
		try {
			userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ConstantUtil.CODE_ERROR, ConstantUtil.MSG_ERROR);
		}
		return resp;
	}
}
