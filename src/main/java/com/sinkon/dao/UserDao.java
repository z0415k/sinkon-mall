package com.sinkon.dao;


import org.springframework.stereotype.Repository;

import com.sinkon.entity.User;

@Repository
public interface UserDao {
	User getUserByOpenId(String openId);
	void addUser(User user);

}
