package com.akchengtou.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.akchengtou.web.entity.Authentic;
import com.akchengtou.web.entity.AuthenticDAO;
import com.akchengtou.web.entity.MemberDAO;
import com.akchengtou.web.entity.User;
import com.akchengtou.web.entity.UserDAO;

public class UserManager {

	// private static UsersDAO userDao = (UsersDAO)context.getBean("UsersDAO");
	private UserDAO userDao;
	private AuthenticDAO authenticDao;
	private MemberDAO memberDao;

	/***
	 * @return 用户列表
	 */
	public List<User> findAllUsers() {
		return getUserDao().findAll();
	}

	/***
	 * 根据用户手机号码获取用户对象
	 * 
	 * @param telephone
	 *            手机号码
	 * @return Users 对象
	 */
	public User findUserByTelephone(String telephone) {
		List list = getUserDao().findByTelephone(telephone);
		if (list != null && list.size() > 0) {
			User user = getUserDao().findByTelephone(telephone).get(0);
			user.setAuthentics(null);
			return user;
		}

		return null;
	}

	/***
	 * 获取用户姓名
	 * 
	 * @param userId
	 * @return
	 */
	public String findUserNameByUserId(User user) {
		List list = getAuthenticDao().findByProperty("user", user);
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				Authentic authentic = (Authentic) obj;
				String name = authentic.getName();
				if (name != null && !name.equals("")) {
					return name;
				}
			}
		}
		return "";
	}

	public User findUserById(Integer userId) {
		return getUserDao().findById(userId);
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public AuthenticDAO getAuthenticDao() {
		return authenticDao;
	}

	@Autowired
	public void setAuthenticDao(AuthenticDAO authenticDao) {
		this.authenticDao = authenticDao;
	}

	public MemberDAO getMemberDao() {
		return memberDao;
	}

	@Autowired
	public void setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}

}
