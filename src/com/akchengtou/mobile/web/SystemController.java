package com.akchengtou.mobile.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.utils.URLEncodedUtils;
import org.opensaml.ws.wssecurity.EncodedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.akchengtou.tools.AKConfig;
import com.akchengtou.tools.FileUtil;
import com.akchengtou.tools.HttpUtil;
import com.akchengtou.tools.Tools;
import com.akchengtou.web.entity.Announcement;
import com.akchengtou.web.entity.Message;
import com.akchengtou.web.entity.User;
import com.akchengtou.web.entity.Versioncontroll;
import com.akchengtou.web.manager.SystemManager;
import com.akchengtou.web.manager.UserManager;
import com.tenpay.util.CommonUtil;
import com.weini.alipay.config.AlipayConfig;


@Controller
public class SystemController extends BaseController {

	@Autowired
	private SystemManager systemManger;
	@Autowired
	private UserManager userManager;

	
	@RequestMapping("/requestHomeInfo")
	@ResponseBody
	/***
	 * 获取首页信息
	 * @param mm
	 * @return Map 返回值
	 */
	public Map requestHomeInfo(
			ModelMap mm) {
		this.result = new HashMap();
		
		try {
			String str =URLEncoder.encode(AKConfig.WEATHER_LOCATION, "utf-8");
			String address =String.format(AKConfig.WEATHER_ADDRESS, str);
			String responseString = HttpUtil.getHtmlString(address);
			
			Map res =new HashMap();
			
			JSONObject result = JSONObject.fromObject(responseString);
			Map m = (Map) result.get("result");
			Map data = (Map) m.get("data");
			Map realtime = (Map) data.get("realtime");
			Map weather = (Map) realtime.get("weather");
			res.put("weather", weather);
			
			
			//巡检记录
			List list = new ArrayList();
			Map record = new HashMap();
			record.put("name", "张师傅");
			record.put("img", "http://www.poluoluo.com/qq/UploadFiles_7828/201611/2016110420035680.jpg");
			record.put("time", "2016-12-02 10:00:00");
			list.add(record);
			
			record = new HashMap();
			record.put("name", "王师傅");
			record.put("img", "http://files.jb51.net/file_images/game/201611/2016111219035515.jpg");
			record.put("time", "2016-12-02 10:00:00");
			list.add(record);
			
			record = new HashMap();
			record.put("name", "李师傅");
			record.put("img", "http://www.poluoluo.com/qq/UploadFiles_7828/201611/2016110420035680.jpg");
			record.put("time", "2016-12-02 10:00:00");
			list.add(record);
			
			res.put("record", list);
			
			this.result.put("data", res);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		this.status = 200;
		this.message = "";
		return getResult();
	}
	@RequestMapping("/announcementSystem")
	@ResponseBody
	/***
	 * 获取系统公告
	 * @param platform 客户端设备类型 0:Android,1:iOS
	 * @param mm
	 * @return Map 返回值
	 */
	public Map announcementSystem(
			ModelMap mm) {
		this.result = new HashMap();
		List notice = this.systemManger.findNoticeByPlatform();
		this.status = 200;
		this.result.put("data", notice);
		this.message = "";
		return getResult();
	}
	@RequestMapping("/requestPublishAnnouncement")
	@ResponseBody
	/***
	 * 发布系统公告
	 * @param mm
	 * @return Map 返回值
	 */
	public Map requestPublishAnnouncement(
			@RequestParam(value="content")String content,
			ModelMap mm) {
		this.result = new HashMap();
		Announcement announce = new Announcement();
		announce.setReaded(false);
		announce.setContent(content);
		announce.setPublicDate(new Date());
		
		//保存
		this.systemManger.getNoticeDao().save(announce);
		
		this.status = 200;
		this.result.put("data", "");
		this.message = "公告发布成功!";
		return getResult();
	}


	@RequestMapping("/versionInfoSystem")
	@ResponseBody
	/***
	 * 版本信息
	 * @param platform 客户端设备类型 0:Android,1:iOS
	 * @param mm
	 * @return Map 返回值
	 */
	public Map versionInfoSystem(
			@RequestParam(value = "platform", required = false) short platform,
			ModelMap mm) {
		this.result = new HashMap();
		Versioncontroll versionInfo = this.systemManger
				.findVersionInfoByPlatform(platform);
		this.status = 200;
		this.result.put("data", versionInfo);
		this.message = "";
		return getResult();
	}

	@RequestMapping("/requestInnerMessageList")
	@ResponseBody
	/***
	 * 站内信列表
	 * @param mm
	 * @return Map 返回值
	 */
	public Map requestInnerMessageList(
			@RequestParam(value = "page", required = false) Integer page,
			HttpSession session) {
		this.result = new HashMap();
		// 获取用户
		User user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = AKConfig.STRING_LOGING_STATUS_OFFLINE;
		} else {
			List list = this.systemManger.findSystemMessageListByUser(user,
					page);
			if (list != null && list.size() > 0) {
				this.status = 200;
				this.result.put("data", list);
			} else {
				this.status = 200;
				this.result.put("data", new ArrayList());
			}
			this.message = "";
		}

		return getResult();
	}

	@RequestMapping("/requestInnermessageDetail")
	@ResponseBody
	/***
	 * 站内信详情
	 * @param messageId 站内信id
	 * @return Map 返回值
	 */
	public Map requestInnermessageDetail(
			@RequestParam(value = "messageId", required = false) Integer messageId,
			HttpSession session) {
		this.result = new HashMap();

		// 获取站内信
		Message message = this.systemManger.findMessageById(messageId);
		this.status = 200;
		this.result.put("data", message);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestDeleteＡｎｎｏｃｕｍｅｎｔ")
	@ResponseBody
	/***
	 * 删除公告
	 * @param messageId
	 * @return Map 返回值
	 */
	public Map requestDeleteＡｎｎｏｃｕｍｅｎｔ(
			@RequestParam(value = "messageId", required = false) String messageId,
			HttpSession session) {
		this.result = new HashMap();
		
		Object[] objs = messageId.split(",");
		if (objs != null && objs.length > 0) {
			for (Object obj : objs) {
				// 获取站内信
				obj = obj.toString().trim();
				Integer msgId = Integer.parseInt(obj.toString());
				if (msgId != null) {
					Message message = this.systemManger
							.findMessageById(msgId);
					
					if (message != null) {
						// 删除
						this.systemManger.deleteSystemMessage(message);
						this.status = 200;
						this.result.put("data", "");
						this.message = AKConfig.STRING_SYSTEM_MESSAGE_DELETE_SUCCESS;
					}
				} else {
					this.status = 400;
					this.result.put("data", "");
					this.message = AKConfig.STRING_SYSTEM_MESSAGE_DELETE_FAIL;
				}
				
			}
			
			this.status = 200;
			this.result.put("data", "");
			this.message = AKConfig.STRING_SYSTEM_MESSAGE_DELETE_SUCCESS;
		}
		
		return getResult();
	}
	@RequestMapping("/requestDeleteInnerMessage")
	@ResponseBody
	/***
	 * 删除站内信
	 * @param messageId
	 * @return Map 返回值
	 */
	public Map requestDeleteInnerMessage(
			@RequestParam(value = "messageId", required = false) String messageId,
			HttpSession session) {
		this.result = new HashMap();

		Object[] objs = messageId.split(",");
		if (objs != null && objs.length > 0) {
			for (Object obj : objs) {
				// 获取站内信
				obj = obj.toString().trim();
				Integer msgId = Integer.parseInt(obj.toString());
				if (msgId != null) {
					Message message = this.systemManger
							.findMessageById(msgId);

					if (message != null) {
						// 删除
						this.systemManger.deleteSystemMessage(message);
						this.status = 200;
						this.result.put("data", "");
						this.message = AKConfig.STRING_SYSTEM_MESSAGE_DELETE_SUCCESS;
					}
				} else {
					this.status = 400;
					this.result.put("data", "");
					this.message = AKConfig.STRING_SYSTEM_MESSAGE_DELETE_FAIL;
				}

			}

			this.status = 200;
			this.result.put("data", "");
			this.message = AKConfig.STRING_SYSTEM_MESSAGE_DELETE_SUCCESS;
		}

		return getResult();
	}
	@RequestMapping("/requestDeleteAnnouncement")
	@ResponseBody
	/***
	 * 删除站内信
	 * @param contentId
	 * @return Map 返回值
	 */
	public Map requestDeleteAnnouncement(
			@RequestParam(value = "contentId", required = false) String messageId,
			HttpSession session) {
		this.result = new HashMap();
		
		Object[] objs = messageId.split(",");
		if (objs != null && objs.length > 0) {
			for (Object obj : objs) {
				// 获取站内信
				obj = obj.toString().trim();
				Integer msgId = Integer.parseInt(obj.toString());
				if (msgId != null) {
					Announcement message = this.systemManger.getNoticeDao().findById(msgId);
					
					if (message != null) {
						// 删除
						this.systemManger.getNoticeDao().delete(message);
						this.status = 200;
						this.result.put("data", "");
						this.message = AKConfig.STRING_SYSTEM_MESSAGE_DELETE_SUCCESS;
					}
				} else {
					this.status = 400;
					this.result.put("data", "");
					this.message = AKConfig.STRING_SYSTEM_MESSAGE_DELETE_FAIL;
				}
				
			}
			
			this.status = 200;
			this.result.put("data", "");
			this.message = AKConfig.STRING_SYSTEM_MESSAGE_DELETE_SUCCESS;
		}
		
		return getResult();
	}

	@RequestMapping("/requestHasReadMessage")
	@ResponseBody
	/***
	 * 将信息标为已读状态
	 * @param messageId
	 * @return Map 返回值
	 */
	public Map requestHasReadMessage(
			@RequestParam(value = "messageId", required = false) Integer messageId,
			HttpSession session) {
		this.result = new HashMap();

		// 获取站内信
		Message message = this.systemManger.findMessageById(messageId);
		if (message != null) {
			// 更改状态
			short flag = 1;
			message.setReaded(true);

			this.systemManger.saveOrUpdate(message);
			this.status = 200;
			this.result.put("data", "");
			this.message = AKConfig.STRING_SYSTEM_MESSAGE_READ_SUCCESS;
		} else {
			// 删除
			this.status = 400;
			this.result.put("data", "");
			this.message = AKConfig.STRING_SYSTEM_MESSAGE_READ_FAIL;
		}

		return getResult();
	}



	@RequestMapping("/requestNewUseIntroduce")
	@ResponseBody
	/***
	 * 新手指南
	 * @return Map 返回值
	 */
	public Map requestNewUseIntroduce(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url", Tools.generateWebUrl(AKConfig.STRING_SYSTEM_INTRODUCE));

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestUserProtocol")
	@ResponseBody
	/***
	 * 用户协议
	 * @return Map 返回值
	 */
	public Map requestUserProtocol(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url",
				Tools.generateWebUrl(AKConfig.STRING_SYSTEM_SHARE_USER_PROCTOL)
						+ "?contentId=3");

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestFeedBack")
	@ResponseBody
	/***
	 * 意见反馈
	 * @param content 内容
	 * @param files 图片
	 * @param session
	 * @return
	 */
	public Map requestFeedBack(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		User user = this.userManager.findUserById(userId);
		if (user != null) {
			this.status = 200;
			
			// 保存图片
			List images = new ArrayList();
			for(int i=0;i<files.length;i++)
			{
				MultipartFile file = files[i];
				
				String fileName = String.format(
						AKConfig.STRING_USER_HEADER_PICTURE_FORMAT, new Date().getTime());
				String result = FileUtil.savePicture(file, fileName,
						"upload/files/");
				if (!result.equals("")) {
					fileName = AKConfig.STRING_SYSTEM_ADDRESS + "upload/headerImages/"
							+ result;
					images.add(fileName);
					
				} else {
					fileName = "";
				}
				
			}
			
			this.systemManger
					.addFeedback(user, content,images);
			this.message = "反馈成功！";
		} else {
			this.status = 400;
			this.message = AKConfig.STRING_LOGING_TIP;
		}

		return getResult();
	}

	@RequestMapping("/requestLawerIntroduce")
	@ResponseBody
	/***
	 * 免责声明
	 * @return Map 返回值
	 */
	public Map requestLawerIntroduce(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url",
				Tools.generateWebUrl(AKConfig.STRING_SYSTEM_SHARE_USER_PROCTOL)
						+ "?contentId=4");

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestGoldGetRule")
	@ResponseBody
	/***
	 * 金条积累规则
	 * @return Map 返回值
	 */
	public Map requestGoldGetRule(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url",
				Tools.generateWebUrl(AKConfig.STRING_SYSTEM_SHARE_USER_PROCTOL)
						+ "?contentId=5");

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestInviteFriends")
	@ResponseBody
	/***
	 * 邀请好友
	 * @return Map 返回值
	 */
	public Map requestInviteFriends(HttpSession session) {
		this.result = new HashMap();

		Integer userId = 0;
		if (session.getAttribute("userId") != null) {
			userId = (Integer) session.getAttribute("userId");
		}
		String invitcode = Tools.generateInviteCode(userId, false);

		int count = AKConfig.STRING_SHARE_INVITE.size();

		int radomIndex = (int) (0 + Math.random() * (count));

		String content = AKConfig.STRING_SHARE_INVITE.get(radomIndex).toString();

		Map map = new HashMap();
		map.put("url", Tools.generateWebUrl(AKConfig.STRING_SYSTEM_SHARE_CODE)
				+ "?inviteCode=" + invitcode);
		map.put("image", AKConfig.STRING_SYSTEM_INTRODUCE_IMAGE);
		map.put("title", "邀请好友--" + AKConfig.STRING_APPP_SHARE_TITLE);
		map.put("content", content);
		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestGoldUseRule")
	@ResponseBody
	/***
	 * 金条使用规则
	 * @return Map 返回值
	 */
	public Map requestGoldUseRule(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url",
				Tools.generateWebUrl(AKConfig.STRING_SYSTEM_SHARE_USER_PROCTOL)
						+ "?contentId=7");

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestInviteCode")
	@ResponseBody
	/***
	 * 邀请码获取
	 * @return Map 返回值
	 */
	public Map requestInviteCode(HttpSession session) {
		this.result = new HashMap();
		// 获取用户
		User user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = AKConfig.STRING_LOGING_STATUS_OFFLINE;
		} else {
			Map map = new HashMap();

			// Object[] objs = user.getSystemcodes().toArray();
			String code = Tools.generateInviteCode(user.getUserId(), false);

			this.status = 200;
			this.result.put("data", code);
			this.message = "";
		}

		return getResult();
	}

	@RequestMapping("/customServiceSystem")
	@ResponseBody
	/***
	 * 上传项目信息获取
	 * @return Map 返回值
	 */
	public Map customServiceSystem(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("tel", AKConfig.STRING_SYSTEM_SERVICE_PROJECT_UPLOAD_TEL);

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestHasMessageInfo")
	@ResponseBody
	/***
	 * 站内信提醒数据获取
	 * @return Map 返回值
	 */
	public Map requestHasMessageInfo(HttpSession session) {
		this.result = new HashMap();

		User user = this.findUserInSession(session);

		if (user == null) {
			this.status = 401;
			this.message = AKConfig.STRING_LOGING_STATUS_OFFLINE;
		} else {

			// 获取未读
			boolean flag = this.systemManger.findUserNotReadMessageFlag(user);

			Map map = new HashMap();
			map.put("flag", flag);

			this.status = 200;
			this.result.put("data", map);
			this.message = "";
		}
		return getResult();
	}

	@RequestMapping("/androidTest")
	public String androidTest(HttpSession session) {
		return "download";
	}

	// 新手指南
	@RequestMapping(value = "/UserGuide")
	public String UserGuide(ModelMap model) {
		return "user_guide";
	}


	// 关于我们
	@RequestMapping(value = "/abountUs")
	public String abountUs(ModelMap model) {
		return "abount_me";
	}

	// 用户协议
	@RequestMapping(value = "/UserProctol")
	public String UserProctol(ModelMap model) {
		return "UserProctol";
	}

	// 投资人详情
	@RequestMapping(value = "/H5UserInfo")
	public String H5UserInfo(ModelMap model) {
		return "UserInfo";
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
			user = this.userManager.findUserById(userId);
		}

		return user;
	}
}
