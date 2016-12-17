package com.akchengtou.mobile.web;

import java.io.File;
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
import org.hibernate.validator.internal.util.privilegedactions.GetAnnotationParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.akchengtou.tools.AKConfig;
import com.akchengtou.tools.FileUtil;
import com.akchengtou.tools.MailUtil;
import com.akchengtou.web.entity.Authentic;
import com.akchengtou.web.entity.AuthenticEntity;
import com.akchengtou.web.entity.Authenticstatus;
import com.akchengtou.web.entity.Homehouse;
import com.akchengtou.web.entity.House;
import com.akchengtou.web.entity.Identity;
import com.akchengtou.web.entity.Identiytype;
import com.akchengtou.web.entity.User;
import com.akchengtou.web.manager.AuthenticManager;
import com.akchengtou.web.manager.UserManager;

@Controller
public class AuthenticController extends BaseController {

	@Autowired
	private AuthenticManager authenticManager;
	@Autowired
	private UserManager userManager;
	
	@RequestMapping("/getProtocolAuthentic")
	@ResponseBody
	/***
	 * 忘记密码
	 * @param userInstance
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map getProtocolAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.result.put("data", AKConfig.STRING_AUTH_QUALIFICATION);

		return getResult();
	}
	
	@RequestMapping("/getHomeHouseListAuthentic")
	@ResponseBody
	/***
	 * 获取小区
	 * @param session
	 * @return
	 */
	public Map getHomeHouseListAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.message = "";

		List list = this.authenticManager.getHomeHouseDao().findAll();
		this.result.put("data", list);

		return getResult();
	}
	
	
	@RequestMapping("/getBuildingByHouseId")
	@ResponseBody
	/***
	 * 根据小区获取楼栋列表
	 * @param session
	 * @return
	 */
	public Map getBuildingByHouseId(
			@RequestParam(value="houseId")Integer houseId,
			HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.message = "";

		List list = this.authenticManager.findBuildingByHouseId(houseId);
		this.result.put("data", list);

		return getResult();
	}
	
	
	@RequestMapping("/getUnitByBuildingId")
	@ResponseBody
	/***
	 * 根据楼栋获取单元
	 * @param session
	 * @return
	 */
	public Map getUnitByBuildingId(
			@RequestParam(value = "buildId", required = false) Integer buildId,
			HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.message = "";

		List list = this.authenticManager.findUnitByBuildId(buildId);
		this.result.put("data", list);

		return getResult();
	}
	
	
	@RequestMapping("/getHouseByUnitId")
	@ResponseBody
	/***
	 * 根据单元获取室
	 * @param session
	 * @return
	 */
	public Map getHouseByUnitId(
			@RequestParam(value="unitId")Integer unitId,
			HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.message = "";

		List list = this.authenticManager.findHouseByUnitId(unitId);
		this.result.put("data", list);

		return getResult();
	}
	
	@RequestMapping("/checkAuthenticStatusUser")
	@ResponseBody
	/***
	 * 检查身份认证状态
	 * @param session
	 * @return
	 */
	public Map checkAuthenticStatusUser(HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.status = 200;

		User user = this.findUserInSession(session);
		if (user != null) {
			Object[] list = user.getAuthentics().toArray();

			Map map = new HashMap();
			map.put("status", "0");
			map.put("name", "未认证");

			// 获取认证状态
			Authenticstatus status = null;
			Authentic authentic = null;
			if (list != null && list.length > 0) {
				if (list.length > 1) {
					status = new Authenticstatus();
					status.setStatusId(3);
					status.setName("认证通过");
				} else {
					authentic = (Authentic) list[0];
					status = authentic.getAuthenticstatus();
					status.setStatusId(AKConfig.STRING_AUTH_STATUS.get(status
							.getName()));
				}
			}

			this.status = 200;
			this.result.put("data", status);
			this.message = "";
		} else {
			this.message = AKConfig.STRING_LOGING_TIP;
		}

		return getResult();
	}


	

//	@RequestMapping(value = "/updateIdentiyTypeUser")
//	@ResponseBody
//	/***
//	 * 忘记密码
//	 * @param userInstance
//	 * @param bindingResult
//	 * @param session
//	 * @return
//	 */
//	public Map updateIdentiyTypeUser(
//			@RequestParam(value = "ideniyType") short ideniyType,
//			@RequestParam(value = "isWechatLogin") boolean isWechatLogin,
//			@RequestParam(value = "file", required = false) MultipartFile file,
//			HttpSession session) {
//		this.result = new HashMap();
//		this.result.put("data", "");
//
//		// 获取用户
//		User user = this.findUserInSession(session);
//
//		// 身份类型
//		if (ideniyType == 0) {
//			this.status = 400;
//			this.message = AKAKConfig.STRING_AUTH_IDENTIY_TYPE_NOT_NULL;
//
//			return getResult();
//		}
//
//		// 保存图片
//		String fileName = String.format(
//				AKConfig.STRING_USER_HEADER_PICTURE_FORMAT, user.getUserId());
//		String result = FileUtil.savePicture(file, fileName,
//				"upload/headerImages/");
//		if (!result.equals("")) {
//			fileName = AKConfig.STRING_SYSTEM_ADDRESS + "upload/headerImages/"
//					+ result;
//		} else {
//			fileName = "";
//		}
//
//		if (user != null) {
//			// 获取身份类型
//			Identiytype identityType = this.authenticManager
//					.findIdentityTypeById(ideniyType);
//
//			Authenticstatus status = new Authenticstatus();
//			status.setName("未认证");
//			status.setStatusId(6);
//
//			// 生成认证记录
//			Authentic authentic = new Authentic();
//			authentic.setIdentiytype(identityType);
//			authentic.setUsers(user);
//			authentic.setAuthenticstatus(status);
//			
//			if(user.getTelephone()!=null)
//			{
//				String telephone = user.getTelephone();
//				Integer length = telephone.length();
//				String name = "用户"+user.getTelephone().substring(length-4, length);
//				authentic.setName(name);
//			}
//
//			// 保存
//			this.authenticManager.saveAuthentic(authentic);
//
//			// 更新用户登录信息
//			Set authenticSet = new HashSet();
//			authenticSet.add(authentic);
//
//			user.setAuthentics(authenticSet);
//
//			// 头像
//			if (!fileName.equals("")) {
//				user.setHeadSculpture(fileName);
//			}
//
//			// 更新用户登录信息
//			this.userManager.saveOrUpdateUser(user);
//
//			// 生成邀请码
//			Systemcode systemCode = this.userManager.findSystemCodeByUser(user);
//			if (systemCode == null) {
//				String code = Tools.generateInviteCode(user.getUserId(),
//						isWechatLogin);
//
//				systemCode = new Systemcode();
//				systemCode.setCode(code);
//				systemCode.setUsers(user);
//
//				// 保存邀请码
//				this.userManager.saveSystemCode(systemCode);
//			}
//			// 封装返回数据
//			Map map = new HashMap();
//			map.put("inviteCode", systemCode.getCode());
//
//			// 返回状态
//			this.status = 200;
//			this.result.put("data", map);
//			this.message = AKConfig.STRING_AUTH_IDENTIY_SUCCESS;
//		} else {
//			this.status = 400;
//			this.message = AKConfig.STRING_AUTH_IDENTIY_FAIL;
//		}
//
//		return getResult();
//	}
//
//
//



	@RequestMapping("/requestAuthentic")
	@ResponseBody
	/***
	 * 获取省份列表
	 * @param session
	 * @return
	 */
	public Map requestAuthentic(@Valid AuthenticEntity entity,
			BindingResult bindingResult, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.status = 200;
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			final User user = this.findUserInSession(session);
			if (user != null) {
				// 获取身份类型
				Identiytype identiytype = null;
				// 获取城市地址
				House house = this.authenticManager.findHouseById(entity.getHouseId());

				// 获取认证状态
				Object[] list = user.getAuthentics().toArray();
				Authenticstatus status = null;
				Authentic authentic = null;
				if (list != null && list.length > 0) {
					authentic = (Authentic) list[0];
					status = authentic.getAuthenticstatus();

					// 获取用户身份认证状态
					int authStatus = AKConfig.STRING_AUTH_STATUS.get(status
							.getName());
					if (authStatus != 3) {
						authentic.setName(entity.getName());						
						authentic.setUser(user);
						authentic.setHouse(house);
						authentic.setIdCard(entity.getIdCard());
						authentic.setAuthDate(new Date());

					} else {
						this.status = 400;
						this.message = AKConfig.STRING_AUTH_HAS;
						return getResult();
					}
				} else {
					authentic = new Authentic();
					authentic.setName(entity.getName());						
					authentic.setUser(user);
					authentic.setHouse(house);
					authentic.setIdCard(entity.getIdCard());
					authentic.setAuthDate(new Date());
					
					this.authenticManager.getAuthenticDao().save(
							authentic);
				}

				// 保存更新用户的身份认证信息
				status = new Authenticstatus();
				status.setStatusId(2);
				authentic.setAuthenticstatus(status);
				user.getAuthentics().add(authentic);
				user.setName(entity.getName());
				
				authentic.setUser(user);
				Identity identity = new Identity();
				identity.setIdentiyTypeId(1);
				authentic.setIdentity(identity);

				this.authenticManager.getAuthenticDao().saveOrUpdate(
						authentic);

				this.userManager.getUserDao().saveOrUpdate(user);
				this.message = AKConfig.STRING_AUTH_SUBMMIT_SUCCESS;
			} else {
				this.message = AKConfig.STRING_LOGING_TIP;
			}

			// 发送注册成功邮件
			new Thread() {
				public void run() {
					MailUtil mu = new MailUtil();
					try {
						mu.sendUserAuthentic(mu, user.getTelephone());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		}

		return getResult();
	}

	@RequestMapping("/authenticInfoUser")
	@ResponseBody
	/***
	 * 身份认证信息
	 * @param session
	 * @return
	 */
	public Map authenticInfoUser(HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.status = 200;

		User user = this.findUserInSession(session);
		if (user != null) {
			this.status = 200;
			
			Map map = new HashMap();
			Object[] authentics = user.getAuthentics().toArray();
	
					
			map.put("authentics", user.getAuthentics());
			map.put("headSculpture", user.getImage());
			map.put("userId", user.getUserId());
			map.put("telephone", user.getTelephone());
			
			
			this.result.put("data", map);
			this.message = "";
		} else {
			this.message = AKConfig.STRING_LOGING_TIP;
		}

		return getResult();
	}
	@RequestMapping("/userInfo")
	@ResponseBody
	/***
	 * 身份认证信息
	 * @param session
	 * @return
	 */
	public Map userInfo(HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.status = 200;
		
		User user = this.findUserInSession(session);
		if (user != null) {
			this.status = 200;
			this.result.put("data", user);
			this.message = "";
		} else {
			this.message = AKConfig.STRING_LOGING_TIP;
		}
		
		return getResult();
	}


//	@RequestMapping("/requestAuthenticQuick")
//	@ResponseBody
//	/***
//	 * 催一催
//	 * @param session
//	 * @return
//	 */
//	public Map requestAuthenticQuick(
//			@RequestParam(value = "authId", required = false) Integer authId,
//			HttpSession session) {
//		this.result = new HashMap();
//		this.result.put("data", "");
//		this.status = 200;
//		
//		final Users user = this.findUserInSession(session);
//		if (user != null) {
//			final Authentic  authentic = this.authenticManager.findAuthenticById(authId);
//			if(authentic!= null)
//			{
//				//发送催一催
//				new Thread(){
//					public void run()
//					{
//						MailUtil mu = new MailUtil();
//						try {
//							try {
//								mu.sendUserAuthenticQuick(mu,user.getTelephone(),authentic.getName());
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}.start();
//				
//				this.status = 200;
//				this.message = AKConfig.STRING_AUTH_SPEED_SUCCESS;
//			}else{
//				this.status = 400;
//				this.message = "";
//				this.message = AKConfig.STRING_AUTH_IDENTIY_FAIL;
//			}
//			
//		} else {
//			this.message = AKConfig.STRING_LOGING_TIP;
//		}
//		
//		return getResult();
//	}

	/***
	 * 从当前session获取用户对象
	 * 
	 * @param session
	 * @return
	 */
	private User findUserInSession(
			HttpSession session) {
		User user = null;
		if (session.getAttribute("userId") != null) {
			Integer userId = (Integer) session.getAttribute("userId");
			user = this.userManager.findUserById(userId);
		}
		return user;
	}

}
