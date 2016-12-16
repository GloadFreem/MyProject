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
public class WebController extends BaseController {

	@Autowired
	private UserManager userManger;
	
	/***    ---------------------------------------------后端管理系统升级-------------------------------------------***/
	/***
	 * 首页
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="newSystem/index")
	public String requestIndexPage(
			ModelMap map,
			HttpSession session)
	{
		map.put("content","content");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	
	//---------------用户------------------------//
	//聊天室列表
	@RequestMapping(value="newSystem/userList")
	public String userList(
			@RequestParam(value="size",required=false)Integer  size,
			@RequestParam(value="page",required=false)Integer  page,
			ModelMap map)
	{
		
		List list = this.userManger.getUserDao().findAll();
		map.put("result", list);
		map.put("content", "table-user-list");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	//---------------用户------------------------//
	
	/***
	 * 资讯内容
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="newSystem/news")
	public String requestNewsPage(
			ModelMap map,
			HttpSession session)
	{
		map.put("content","buttons");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	
	/***
	 * 资讯内容
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="newSystem/notebook")
	public String requestNotebook(
			ModelMap map,
			HttpSession session)
	{
		map.put("content","notebook");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	
	/***
	 * 资讯Banner
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="newSystem/newsBanner")
	public String requestNewsBanner(
			@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value="size",required=false)Integer size,
			ModelMap map,
			HttpSession session)
	{
		
//		List list = this.messageManager.getNewsbannerDAO().findAll();
		
		List list = new ArrayList();
		map.put("content","table-news-banner");
		map.put("result",list);
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	
	/***
	 * 删除资讯Banner
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="newSystem/delNewsBanner")
	public String requestDelNewsBanner(
			@RequestParam(value="contentId",required=false)Integer contentId,
			ModelMap map,
			HttpSession session)
	{
		
//		if(contentId!=null)
//		{
//			Newsbanner banner = this.messageManager.getNewsbannerDAO().findById(contentId);
//			if(banner!=null)
//			{
//				this.messageManager.getNewsbannerDAO().delete(banner);
//			}
//			
//		}
//		
//		
//		List list = this.messageManager.getNewsbannerDAO().findAll();
		
		List list = new ArrayList();
		map.put("content","table-news-banner");
		map.put("result",list);
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	/***
	 * 资讯Banner详情
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="newSystem/newBannerDetail")
	public String newBannerDetail(
			@RequestParam(value="contentId",required=false)Integer contentId,
			ModelMap map,
			HttpSession session)
	{
		
//		if(contentId!=null)
//		{
//			Newsbanner banner = this.messageManager.getNewsbannerDAO().findById(contentId);
//			if(banner!=null)
//			{
//				map.put("data", banner);
//			}
//		}
		
		map.put("content","newBannerContent");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	/***
	 * 编辑Banner详情
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="newSystem/editNewBanner")
	public String editNewBanner(
			@RequestParam(value="contentId",required=false)Integer contentId,
			@RequestParam(value="desc",required=false)String desc,
			@RequestParam(value="image",required=false)String image,
			@RequestParam(value="url",required=false)String url,
			@RequestParam(value="file",required=false)MultipartFile[] images,
			ModelMap map,
			HttpSession session)
	{
		
//		if(contentId!=null)
//		{
//			Newsbanner banner = this.messageManager.getNewsbannerDAO().findById(contentId);
//			if(banner!=null)
//			{
//				//更新
//				if(desc!=null)
//				{
//					banner.setDesc(desc);
//				}
//				
//				if(image!=null)
//				{
//					banner.setImage(image);
//				}
//				
//				if(url!=null)
//				{
//					banner.setUrl(url);
//				}
//				
//				// 保存图片
//				String fileName = "";
//				String result = "";
//				if (images != null && images.length > 0) {
//
//					MultipartFile file = null;
//					Set items = new HashSet();
//					for (int i = 0; i < images.length; i++) {
//						if (images[i] != null) {
//							file = images[i];
//							fileName = String.format(
//									AKConfig.STRING_USER_FEELING_PICTUREA_FORMAT,
//									new Date().getTime(), i);
//							result = FileUtil.savePicture(file, fileName,
//									"upload/uploadImages/");
//							if (!result.equals("")) {
//								fileName = AKConfig.STRING_SYSTEM_ADDRESS
//										+ "upload/uploadImages/" + result;
//							} else {
//								fileName = "";
//							}
//
//						}
//					}
//					
//					if(fileName!=null && fileName!="")
//					{
//						banner.setImage(fileName);
//					}else{
//						if(image!=null && image!="")
//						{
//							banner.setImage(image);
//						}
//					}
//				}
//				
//				//保存
//				this.messageManager.getNewsbannerDAO().saveOrUpdate(banner);
//				map.put("data", banner);
//			}
//		}
//		
		map.put("content","newBannerContent");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	
	/***
	 * 原创Banner
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="newSystem/originalBanner")
	public String originalNewsBanner(
			@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value="size",required=false)Integer size,
			ModelMap map,
			HttpSession session)
	{
		
//		List list = this.messageManager.getOriginalbannerDAO().findAll();
		
		List list = new ArrayList();
		map.put("content","table-original-banner");
		map.put("result",list);
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	
	/***
	 * 删除原创Banner
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="newSystem/delOriginalBanner")
	public String requestDelOriginalBanner(
			@RequestParam(value="contentId",required=false)Integer contentId,
			ModelMap map,
			HttpSession session)
	{
		
//		if(contentId!=null)
//		{
//			Originalbanner banner = this.messageManager.getOriginalbannerDAO().findById(contentId);
//			if(banner!=null)
//			{
//				this.messageManager.getOriginalbannerDAO().delete(banner);
//			}
//			
//		}
//		
//		
//		List list = this.messageManager.getOriginalbannerDAO().findAll();
		List list = new ArrayList();
		map.put("content","table-original-banner");
		map.put("result",list);
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	/***
	 * 原创Banner详情
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="newSystem/originalBannerDetail")
	public String originalBannerDetail(
			@RequestParam(value="contentId",required=false)Integer contentId,
			ModelMap map,
			HttpSession session)
	{
		
//		if(contentId!=null)
//		{
//			Originalbanner banner = this.messageManager.getOriginalbannerDAO().findById(contentId);
//			if(banner!=null)
//			{
//				map.put("data", banner);
//			}
//		}
		
		map.put("content","originalBannerContent");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	/***
	 * 编辑Banner详情
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="newSystem/editOriginalBanner")
	public String editOriginalBanner(
			@RequestParam(value="contentId",required=false)Integer contentId,
			@RequestParam(value="desc",required=false)String desc,
			@RequestParam(value="image",required=false)String image,
			@RequestParam(value="url",required=false)String url,
			@RequestParam(value="file",required=false)MultipartFile[] images,
			ModelMap map,
			HttpSession session)
	{
		
//		if(contentId!=null)
//		{
//			Originalbanner banner = this.messageManager.getOriginalbannerDAO().findById(contentId);
//			if(banner!=null)
//			{
//				//更新
//				if(desc!=null)
//				{
//					banner.setDesc(desc);
//				}
//				
//				if(image!=null)
//				{
//					banner.setImage(image);
//				}
//				
//				if(url!=null)
//				{
//					banner.setUrl(url);
//				}
//				
//				// 保存图片
//				String fileName = "";
//				String result = "";
//				if (images != null && images.length > 0) {
//					
//					MultipartFile file = null;
//					Set items = new HashSet();
//					for (int i = 0; i < images.length; i++) {
//						if (images[i] != null) {
//							file = images[i];
//							fileName = String.format(
//									AKConfig.STRING_USER_FEELING_PICTUREA_FORMAT,
//									new Date().getTime(), i);
//							result = FileUtil.savePicture(file, fileName,
//									"upload/uploadImages/");
//							if (!result.equals("")) {
//								fileName = AKConfig.STRING_SYSTEM_ADDRESS
//										+ "upload/uploadImages/" + result;
//							} else {
//								fileName = "";
//							}
//							
//						}
//					}
//					
//					if(fileName!=null && fileName!="")
//					{
//						banner.setImage(fileName);
//					}else{
//						if(image!=null && image!="")
//						{
//							banner.setImage(image);
//						}
//					}
//				}
//				
//				//保存
//				this.messageManager.getOriginalbannerDAO().saveOrUpdate(banner);
//				map.put("data", banner);
//			}
//		}
//		
		map.put("content","originalBannerContent");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	
	
	//聊天室页面
	@RequestMapping(value="newSystem/createChatRoomPage")
	public String createChatRoomPage(
			@RequestParam(value="contentId",required=false)Integer contentId,
			ModelMap map)
	{
//		if(contentId!=null)
//		{
//			Chatroom room = this.imManager.getChatRoomDao().findById(contentId);
//			map.put("data", room);
//			Integer userId =Integer.parseInt(room.getOwner()) ;
//			Users u=this.userManager.findUserById(userId);
//			map.put("img", u.getHeadSculpture());
//		}
		
		map.put("content", "createChatRoom");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	
	//聊天室添加
	@RequestMapping(value="newSystem/createChatRoom")
	public String createChatRoom(
			@RequestParam(value="name")String  name,
			@RequestParam(value="desc")String  desc,
			@RequestParam(value="maxusers")Integer  maxusers,
			@RequestParam(value="owner")Integer  userId,
			@RequestParam(value="projectId")Integer  projectId,
			@RequestParam(value="contentId")Integer  contentId,
			ModelMap map)
	{
//		Users u = this.userManager.findUserById(userId);
//		Chatroom room ;
//		if(contentId!=null)
//		{
//			room=this.imManager.getChatRoomDao().findById(contentId);
//		}else{
//			room=new Chatroom();
//		}
//		
//		if(contentId==null)
//		{
//			Map result = this.imManager.createChatRoom(name, desc, maxusers, u);
//			if(result.get("data")!=null)
//			{
//				Map m  = (Map) result.get("data");
//				String id =m.get("id").toString();
//				room.setCode(id);
//						
//			}
//			map.put("result", result);
//		}
//		
//		
//		room.setName(name);
//		room.setDescription(desc);
//		room.setMaxusers(maxusers);
//		room.setAffiliationsCount(0);
//		room.setCreateDate(new Date());
//		room.setOwnerName(u.getName());
//		room.setOwner(String.valueOf(userId));
//		room.setExt(String.valueOf(projectId));
//		
//		if(contentId!=null)
//		{
//			this.imManager.getChatRoomDao().saveOrUpdate(room);
//		}else{
//			this.imManager.getChatRoomDao().save(room);
//		}
//		
		
//		map.put("img", u.getHeadSculpture());
//		map.put("data", room);
		map.put("content", "createChatRoom");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	//聊天室列表
	@RequestMapping(value="newSystem/chatRoomList")
	public String chatRoomList(
			@RequestParam(value="size",required=false)Integer  size,
			@RequestParam(value="page",required=false)Integer  page,
			ModelMap map)
	{
		
//		List list = this.imManager.getChatRoomDao().findAll();
		List list = new ArrayList();
		map.put("result", list);
		map.put("content", "table-chatroom-banner");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	
	//删除聊天室
	@RequestMapping(value="newSystem/deleteChatRoom")
	public String deleteChatRoom(
			@RequestParam(value="contentId",required=false)Integer  contentId,
			ModelMap map)
	{
		
//		if(contentId!=null)
//		{
//			Chatroom room = this.imManager.getChatRoomDao().findById(contentId);
//			
//			if(room!=null){
//				this.imManager.getChatRoomDao().delete(room);
//			}
//		}
//		
//		List list = this.imManager.getChatRoomDao().findAll();
		List list = new ArrayList();
		map.put("result", list);
		map.put("content", "table-chatroom-banner");
		return AKConfig.NEW_SERVER_CONTROL;
	}
	
	/***    ---------------------------------------------后端管理系统升级-------------------------------------------***/
	

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
