package com.akchengtou.mobile.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.type.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.akchengtou.web.entity.User;
import com.akchengtou.web.manager.UserManager;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserManager userManger;

	@RequestMapping(value="requestAllUsersList")
	@ResponseBody
	public Map requestAllUsersList()
	{
		this.result = new HashMap();
		
//		List list = this.getUserManger().findAllUsers();
		
//		this.result.put("data", list);
		
		return getResult();
	}
	

	/***
	 * 从当前session获取用户对象
	 * 
	 * @param session
	 * @return
	 */
	private User findUserInSession(HttpSession session) {
		User user = null;
		if (session.getAttribute("userId") != null) {
			Integer userId = (Integer) session.getAttribute("userId");
			user = this.getUserManger().findUserById(userId);
		}

		return user;
	}


	public UserManager getUserManger() {
		return userManger;
	}


	public void setUserManger(UserManager userManger) {
		this.userManger = userManger;
	}
}
