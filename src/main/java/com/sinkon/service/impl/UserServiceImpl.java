package com.sinkon.service.impl;



import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinkon.dao.UserDao;
import com.sinkon.dto.Response;
import com.sinkon.entity.User;
import com.sinkon.service.UserService;
import com.sinkon.utils.CheckUtils;
import com.sinkon.utils.ConstantUtil;
import com.sinkon.utils.Redis;

@Service
public class UserServiceImpl implements UserService {

	private Logger log = Logger.getLogger(this.getClass());
	private static ObjectMapper mapper = new ObjectMapper();
	private Redis redis = new Redis();

	// 注入Service依赖
	@Autowired
	private UserDao userDao;
	
	@Override
	public Object getUserByOpenId(String openId) throws Exception{
		Object userRedis = redis.get(openId);
		if (CheckUtils.isEmpty(userRedis)) {
			userRedis = userDao.getUserByOpenId(openId);
		}
		if(!CheckUtils.isEmpty(userRedis)){
			redis.set(openId, userRedis.toString());
		}
		return userRedis;
	}

	@Override
	public void addUser(User user) throws Exception {
		userDao.addUser(user);
		redis.set(user.getOpenId(), mapper.writeValueAsString(user));
	}

}
