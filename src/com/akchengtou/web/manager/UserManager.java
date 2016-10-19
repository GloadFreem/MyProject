package com.akchengtou.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.akchengtou.web.entity.User;
import com.akchengtou.web.entity.UserDAO;


public class UserManager {
	
//	private static UsersDAO userDao = (UsersDAO)context.getBean("UsersDAO");
	private UserDAO userDao;
	
	
	/***
	 * @return 用户列表
	 */
	public List<User> findAllUsers()
	{ 	
		return getUserDao().findAll();
	}

	
	public User findUserById(Integer userId)
	{
		return getUserDao().findById(userId);
	}

	public UserDAO getUserDao() {
		return userDao;
	}


	@Autowired
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}


}
