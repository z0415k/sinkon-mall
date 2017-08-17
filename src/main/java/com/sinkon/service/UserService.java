package com.sinkon.service;

import com.sinkon.entity.User;

/**
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface UserService {
	/**
	 * 查询用户信息
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	Object getUserByOpenId(String openId) throws Exception;
	
	/**
	 * 添加用户
	 * @param user
	 * @throws Exception
	 */
	void addUser(User user) throws Exception;
}
