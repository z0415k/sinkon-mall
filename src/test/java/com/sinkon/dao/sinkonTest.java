package com.sinkon.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sinkon.entity.User;


@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试
@ContextConfiguration(locations = "classpath*:/*/spring*.xml")
public class sinkonTest {
	@Autowired
	private UserDao userDao=null;
	@Test
	public void test() {
		User user=new User("111", "1");
		userDao.addUser(user);
	}

}
