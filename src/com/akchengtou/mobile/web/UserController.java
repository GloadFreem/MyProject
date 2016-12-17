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
import javax.ws.rs.core.HttpHeaders;

import org.hibernate.type.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
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

import com.akchengtou.tools.AKConfig;
import com.akchengtou.tools.FileUtil;
import com.akchengtou.tools.MessageType;
import com.akchengtou.tools.MsgUtil;
import com.akchengtou.tools.Tools;
import com.akchengtou.web.entity.Authentic;
import com.akchengtou.web.entity.Authenticstatus;
import com.akchengtou.web.entity.Identity;
import com.akchengtou.web.entity.Identiytype;
import com.akchengtou.web.entity.MessageBean;
import com.akchengtou.web.entity.User;
import com.akchengtou.web.manager.AuthenticManager;
import com.akchengtou.web.manager.UserManager;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserManager userManger;
	@Autowired
	private AuthenticManager authenticManager;

	@RequestMapping("/verifyCode")
	@ResponseBody
	/***
	 * 发送验证码
	 * @param message MessageBean数据校验bean
	 * @param bindingResult 校验绑定结果
	 * @param session HttpSession
	 * @return
	 */
	public Map verifyCode(
			@Valid @ModelAttribute("messagebean") MessageBean message,
			BindingResult bindingResult, Model model, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.message = "";
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			String content = AKConfig.STRING_SMS_REGISTE;
			MsgUtil SMS = new MsgUtil(message.getTelephone(), content,
					MessageType.VerifyCode);

			// // 获取用户输入手机号码是否已经存在
			// User user = this.userManger.findUserByTelephone(message
			// .getTelephone());
			// // 根据不同状态
			// // message.type 0:表示用户注册时发送验证码
			// if (message.getType() == 0) {
			// // 注册时，判用户是否已注册，已注册用户无需发送此验证码
			// if (message.getTelephone() != null
			// && !message.getTelephone().equals("")) {
			// if (user != null) {
			// // 已注册用户无需发送，直接返回结果
			// this.status = 400;
			// this.result.put("data", "");
			// this.message = AKConfig.SMS_USERS_HAVE_REGISTED;
			//
			// // 返回结果
			// return getResult();
			// }
			// }
			// } else if (message.getType() == 1) {
			// // message.type 1:表示用户忘记密码时发送验证码
			// // 如果用户输入手机号码未注册时，提示用户注册
			// if (user == null) {
			// this.status = 400;
			// this.result.put("data", "");
			// this.message = AKConfig.SMS_USERS_NOT_REGISTED;
			// // 返回结果
			// return getResult();
			// }
			// } else if (message.getType() == 2) {
			// // message.type 2:表示用户认证时发送验证码
			// if (user != null) {
			// this.status = 400;
			// this.message = AKConfig.SMS_USERS_HAVE_BIND;
			//
			// Map map = new HashMap(0);
			// map.put("isRelogin", true);
			// this.result.put("data", map);
			// return getResult();
			// }
			// } else {
			// // message.type 3:表示用户更换绑定手机号码
			// // 判断用户是否输入重复手机号码
			// if (user != null) {
			// User u = this.findUserInSession(session);
			// if (message.getTelephone().equals(u.getTelephone())) {
			// this.status = 400;
			// this.message = AKConfig.STRING_LOGING_TEL_NOT_REPEAT;
			// return getResult();
			// }
			//
			// u = this.userManger.findUserByTelephone(message
			// .getTelephone());
			// if (u != null) {
			// this.status = 400;
			// this.message = AKConfig.SMS_USERS_HAVE_BIND;
			//
			// return getResult();
			// }
			//
			// this.message = "";
			//
			// }
			// }

			// 发送验证码
//			Integer code = MsgUtil.send();
			Integer code = 1234;

			//测试 1234
			if (code != 0) {
				this.status = 200;
				if (this.message.equals("")) {
					this.message = AKConfig.SMS_HAVE_SEND_STRING;
				}

				session.setAttribute("code", code);
				System.out.println(session.getAttribute("code"));
			} else {
				this.status = 400;
				this.message = AKConfig.SMS_FAIL_SEND_STRING;
			}
		}
		this.result.put("data", "JSESSIONID=" + session.getId());
		return getResult();
	}

	@RequestMapping("/loginUser")
	@ResponseBody
	/***
	 * 用户登录
	 * @param userInstance 传参
	 * @param bindingResult 校验结果绑定
	 * @param session HttpSession
	 * @return 返回值
	 */
	public Map loginUser(@RequestParam(value = "telephone") String telePhone,
			@RequestParam(value = "verifyCode") String verifyCode,
			@RequestParam(value = "platform") short platform,
			@RequestParam(value = "regid", required = false) String regid,
			HttpServletRequest request, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		if (telePhone.equals(null) || telePhone.equals("")) {
			this.status = 400;
			this.message = AKConfig.STRING_LOGING_TEL_NOT_NULL;
		} else {
			// 开始校验密码
			User user = this.userManger.findUserByTelephone(telePhone);
			if (user != null) {
				session.setAttribute("userId", user.getUserId());
			}

			if (session.getAttribute("code") != null) {
				String code = session.getAttribute("code").toString();

				if (code.equalsIgnoreCase(verifyCode)) {
					// User user =
					// this.userManger.findUserByTelephone(telePhone);
					if (user == null) {
						user = new User();
						short gender = 1;
						user.setScore(100);
						user.setGender(gender);
						this.userManger.getUserDao().save(user);
					}

					user.setTelephone(telePhone);
					user.setPlatform(platform);
					user.setLoginTime(new Date());
					if (regid != null && !regid.equals("")) {
						user.setRegId(regid);
					}
					
					if(user.getImage()==null)
					{
						user.setImage("http://59.110.11.95:8080/akchengtou/newSystem/images/avatar_default.jpg");
					}

					this.userManger.getUserDao().saveOrUpdate(user);

					// 返回结果
					Map map = new HashMap();
					map.put("userId", user.getUserId());

					Authentic authentic = this.authenticManager
							.findAuthenticByUserId(user.getUserId());
					if (authentic != null) {
						if (authentic.getIdentity() == null) {
							Identity identity = new Identity();
							identity.setIdentiyTypeId(1);
							authentic.setIdentity(identity);
							this.authenticManager.getAuthenticDao().save(
									authentic);
						}
						map.put("identityType", authentic.getIdentity());
					} else {
						authentic = new Authentic();
						authentic.setUser(user);
						Identity identity = new Identity();
						identity.setIdentiyTypeId(-1);
						authentic.setIdentity(identity);
						Authenticstatus status = new Authenticstatus();
						status.setStatusId(1);
						authentic.setAuthenticstatus(status);

						Identiytype type = new Identiytype();
						short index = -1;
						type.setIdentiyTypeId(index);
						type.setName("业主");
						map.put("identityType", type);

						this.authenticManager.getAuthenticDao().save(authentic);
					}

					this.status = 200;
					this.result.put("data", map);
					this.message = AKConfig.STRING_LOGING_SUCCESS;

					session.setAttribute("userId", user.getUserId());

					// 金条奖励
					// checkUserLoginRecord(user, session);
				} else {
					// 更新登录失败信息
					this.status = 400;
					this.message = AKConfig.STRING_LOGING_CODE_ERROR;
				}

			} else {
				// 登录失败记录添加
				this.status = 400;
				this.result.put("data", "");
				this.message = AKConfig.STRING_LOGING_CODE_NOT_GET;
			}

		}
		return getResult();
	}

	@RequestMapping("/isLoginUser")
	@ResponseBody
	/***
	 * 检查用户是否已登录
	 * @param mm
	 * @param session
	 * @return
	 */
	public Map isLoginUser(ModelMap mm, HttpSession session) {
		this.result = new HashMap();

		this.status = 200;
		this.result.put("data", "");
		this.message = AKConfig.STRING_LOGING_STATUS_ONLINE;

		// 检测用户是否已登录
		System.out.println("获取到Session :UserId："
				+ session.getAttribute("userId"));

		if (session.getAttribute("userId") == null) {
			this.status = 400;
			this.message = AKConfig.STRING_LOGING_STATUS_OFFLINE;
		} else {
			User user = this.findUserInSession(session);
			Authentic authentic = this.authenticManager
					.findAuthenticByUserId(user.getUserId());
			Map map = new HashMap();
			if (authentic != null) {
				map.put("identityType", authentic.getIdentity());
			} else {
				Identiytype type = new Identiytype();
				short index = -1;
				type.setIdentiyTypeId(index);
				type.setName("无身份");
				map.put("identityType", type);
			}

			this.result.put("data", map);
		}

		return getResult();
	}

	@RequestMapping("/requestUserRankingList")
	@ResponseBody
	/***
	 * 获取排行榜
	 * @param page
	 * @param session
	 * @return
	 */
	public Map requestUserRankingList(
			@RequestParam(value = "page") Integer page, HttpSession session) {
		this.result = new HashMap();

		// 获取用户
		User user = this.findUserInSession(session);

		List list = null;
		if (user != null) {
			Authentic authentic = this.authenticManager
					.findAuthenticByUserId(user.getUserId());

			if (authentic.getIdentity() == null) {
				Identity identity = new Identity();
				identity.setIdentiyTypeId(1);

				authentic.setIdentity(identity);
			}
			// 根据用户身份类型获取排行榜
			list = this.authenticManager.findRankingByIdentitype(authentic
					.getIdentity());
		} else {
			list = new ArrayList();
		}

		this.status = 200;
		this.result.put("data", list);
		this.message = "";
		return getResult();
	}

	@RequestMapping("/requestMembersRankingList")
	@ResponseBody
	/***
	 * 员工排行榜
	 * @param page
	 * @param session
	 * @return
	 */
	public Map requestMembersRankingList(
			@RequestParam(value = "page") Integer page, HttpSession session) {
		this.result = new HashMap();

		// 获取用户
		User user = this.findUserInSession(session);

		List list = null;
		if (user != null) {
			Authentic authentic = this.authenticManager
					.findAuthenticByUserId(user.getUserId());

			// 根据用户身份类型获取排行榜
			list = this.authenticManager.findRankingByIdentitype(authentic
					.getIdentity());
		} else {
			list = new ArrayList();
		}

		this.status = 200;
		this.result.put("data", list);
		this.message = "";
		return getResult();
	}

	@RequestMapping("/requestMembersList")
	@ResponseBody
	/***
	 * 员工排行榜
	 * @param page
	 * @param session
	 * @return
	 */
	public Map requestMembersList(@RequestParam(value = "page") Integer page,
			HttpSession session) {
		this.result = new HashMap();

		// 获取用户
		List list = null;
		Identity type = new Identity();
		type.setIdentiyTypeId(2);
		// 根据用户身份类型获取排行榜
		list = this.authenticManager.findRankingByIdentitype(type);
		if (list == null) {
			list = new ArrayList();
		}

		this.status = 200;
		this.result.put("data", list);
		this.message = "";
		return getResult();
	}

	@RequestMapping("/requestUserInfo")
	@ResponseBody
	/***
	 * 获取用户信息
	 * @param session
	 * @return
	 */
	public Map requestUserInfo(HttpSession session) {
		this.result = new HashMap();

		// 获取用户
		User user = this.findUserInSession(session);
		// user = this.userManger.findUserById(2);

		this.status = 200;
		this.result.put("data", user);
		this.message = "";
		return getResult();
	}

	@RequestMapping("/requestChangeHeaderPicture")
	@ResponseBody
	/***
	 * 更新用户头像
	 * @param file 图片文件
	 * @param session
	 * @return
	 */
	public Map requestChangeHeaderPicture(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session) {
		this.result = new HashMap();

		this.status = 200;
		this.result.put("data", "");
		this.message = AKConfig.STRING_LOGING_STATUS_ONLINE;

		// 获取用户
		User user = this.userManger.findUserById(userId);

		// 保存图片
		String fileName = String.format(
				AKConfig.STRING_USER_HEADER_PICTURE_FORMAT,
				new Date().getTime());
		String result = FileUtil.savePicture(file, fileName,
				"upload/headerImages/");
		if (!result.equals("")) {
			fileName = AKConfig.STRING_SYSTEM_ADDRESS + "upload/headerImages/"
					+ result;
			user.setImage(fileName);

			// 更新信息
			this.userManger.getUserDao().saveOrUpdate(user);
			// 返回信息
			this.status = 200;
			this.message = AKConfig.STRING_USER_HEADER_PICTURE_UPDATE_SUCCESS;
		} else {
			this.status = 200;
			this.message = AKConfig.STRING_USER_HEADER_PICTURE_UPDATE_FAIL;
		}

		return getResult();
	}

	@RequestMapping("/requestChangeBindTelephone")
	@ResponseBody
	/***
	 * 重新绑定手机号码
	 * @param telephone 手机号码
	 * @param code 短信验证码
	 * @param session
	 * @return
	 */
	public Map requestChangeBindTelephone(
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "code", required = false) int code,
			HttpSession session) {
		this.result = new HashMap();

		this.status = 200;
		this.result.put("data", "");
		this.message = AKConfig.STRING_LOGING_STATUS_ONLINE;

		// 检验验证码
		if (session.getAttribute("code") != null) {
			// 比对验证码
			int codeSession = (int) session.getAttribute("code");
			if (codeSession == code) {
				// 获取用户
				User user = this.findUserInSession(session);

				if (user == null) {
					this.status = 400;
					this.message = AKConfig.STRING_LOGING_STATUS_OFFLINE;
				} else {
					if (telephone != null) {
						User u = this.userManger.findUserByTelephone(telephone);
						if (u == null) {
							// 设置手机号码
							user.setTelephone(telephone);
							// 保存信息
							this.userManger.getUserDao().saveOrUpdate(user);

							// 返回信息
							this.status = 200;
							this.message = AKConfig.STRING_USER_TELEPHONE_UPDATE_SUCCESS;
						} else {
							// 返回信息
							this.status = 400;
							this.message = AKConfig.STRING_USER_TELEPHONE_EQUAL_FAIL;
						}

					} else {
						// 返回信息
						this.status = 400;
						this.message = AKConfig.STRING_LOGING_TEL_NOT_NULL;
					}

				}
			} else {
				this.status = 400;
				this.message = AKConfig.STRING_LOGING_CODE_ERROR;
			}
		} else {
			this.status = 400;
			this.message = AKConfig.STRING_LOGING_CODE_NOT_GET;
		}

		return getResult();
	}

	@RequestMapping("/requestChangeUserIntroduce")
	@ResponseBody
	/***
	 * 更新签名
	 * @param introduce
	 * @param session
	 * @returnhengma 
	 */
	public Map requestChangeUserIntroduce(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "introduce", required = false) String introduce,
			HttpSession session) {
		this.result = new HashMap();

		User user = this.findUserInSession(session);
		if (user == null) {
			user = this.userManger.findUserById(userId);
		}
		if (!introduce.equals(null) && !introduce.equals("")) {
			user.setIntro(introduce);

			this.userManger.getUserDao().saveOrUpdate(user);
		}
		this.status = 200;
		this.result.put("data", "");
		this.message = AKConfig.STRING_LOGING_STATUS_ONLINE;

		return getResult();
	}

	@RequestMapping("/requestPlatformIntroduce")
	@ResponseBody
	/***
	 * 平台介绍
	 * @return Map 返回值
	 */
	public Map requestPlatformIntroduce(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url",
				Tools.generateWebUrl(AKConfig.STRING_SYSTEM_SHARE_ABOUNT_US));

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestAbountMe")
	@ResponseBody
	/***
	 * 平台介绍
	 * @return Map 返回值
	 */
	public Map requestAbountMe(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url",
				Tools.generateWebUrl(AKConfig.STRING_SYSTEM_SHARE_ABOUNT_US));

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

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
			Integer userId = Tools.findUserBySession(session);
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
