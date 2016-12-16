package com.akchengtou.mobile.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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
import com.akchengtou.web.entity.Authentic;
import com.akchengtou.web.entity.Contentcomment;
import com.akchengtou.web.entity.Contentprise;
import com.akchengtou.web.entity.Contenttype;
import com.akchengtou.web.entity.Publiccontent;
import com.akchengtou.web.entity.Publiccontentimages;
import com.akchengtou.web.entity.User;
import com.akchengtou.web.manager.FeelingManager;
import com.akchengtou.web.manager.UserManager;
import com.sun.mail.imap.protocol.Item;

@Controller
public class FeelingController extends BaseController {

	@Autowired
	private FeelingManager feelingManager;
	@Autowired
	private UserManager userManager;


	@RequestMapping(value = "/requestFeelingList")
	@ResponseBody
	/***
	 * 获取圈子列表
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestFeelingList(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "platform", required = false) Integer platform,
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		User user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = AKConfig.STRING_LOGING_FAIL_NO_USER;
		} else {
			if(platform==null)
			{
				platform=0;
			}
			List list = this.feelingManager.findFeelingByCursor(page,
					user.getUserId(),platform);
			if (list != null && list.size() > 0) {
				this.status = 200;
				this.message = "";
				this.result.put("data", list);
			} else {
				this.status = 201;
				this.result.put("data", new ArrayList());
				this.message = AKConfig.STRING_FEELING_NO_DATA;
			}
		}

		return getResult();
	}
	@RequestMapping(value = "/requestUserFeelingList")
	@ResponseBody
	/***
	 * 获取用户圈子列表
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestUserFeelingList(
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "page", required = true) Integer page,
			HttpSession session) {
		this.result = new HashMap();
		
		// 获取当前发布内容用户
		User user = this.userManager.findUserById(userId);
		
		if (user == null) {
			this.status = 400;
			this.message = AKConfig.STRING_LOGING_FAIL_NO_USER;
		} else {
			List list = this.feelingManager.findFeelingByUser(user,page);
			if (list != null && list.size() > 0) {
				this.status = 200;
				this.message = "";
				this.result.put("data", list);
			} else {
				this.status = 200;
				this.result.put("data", new ArrayList());
				this.message = AKConfig.STRING_FEELING_NO_DATA;
			}
		}
		
		return getResult();
	}

	@RequestMapping(value = "/requestFeelingDetail")
	@ResponseBody
	/***
	 * 圈子详情
	 * @param page
	 * @param session
	 * @return
	 */
	public Map requestFeelingDetail(
			@RequestParam(value = "feelingId", required = true) Integer feelingId, 
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "platform", required = false) Integer platform, 
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		User user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = AKConfig.STRING_LOGING_FAIL_NO_USER;
		} else {
			if(page !=0){
				List list = this.feelingManager.findFeelingCommentByPage(page, feelingId,platform);
				if(list!=null && list.size()>0)
				{
					this.status = 200;
					this.result.put("data", list);
				}else{
					this.status = 201;
					this.result.put("data", new ArrayList());
				}
			}else{
				if(platform==null)
				{
					platform=0;
				}
				Publiccontent content = this.feelingManager.findFeelingById(
						feelingId, user.getUserId(),platform);
				
				
				this.status = 200;
				this.result.put("data", content);
			}
			
			this.message = "";
			
		}

		return getResult();
	}
	

	@RequestMapping(value = "/requestPublicFeeling")
	@ResponseBody
	/***
	 * 获取圈子列表
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestPublicFeeling(
			
			@Valid @ModelAttribute(value = "publiccontent") Publiccontent content,
			@RequestParam(value = "userId",required = false) Integer userId,
			@RequestParam(value = "images",required = false) MultipartFile[] images,
			BindingResult bindingResult, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			// 获取当前发布内容用户
			User user = this.userManager.findUserById(userId);

			if (user == null) {
				this.status = 400;
				this.message = AKConfig.STRING_LOGING_FAIL_NO_USER;
			} else {
				// 关联用户
				content.setUser(user);
				content.setPublicDate(new Date());
				
				// 保存圈子内容
				this.feelingManager.addPublicContent(content);
				
				// 保存图片
				String fileName ="";
				String result="";
				if(images!=null && images.length>0){
					
					MultipartFile file = null;
					for(int i =0;i<images.length;i++){
						if (images[i]!= null) {
							file = images[i];
							fileName = String.format(
									AKConfig.STRING_USER_FEELING_PICTUREA_FORMAT,
									new Date().getTime(),i);
							result = FileUtil.savePicture(
									file, fileName,
									"upload/feelingImages/");
							if (!result.equals("")) {
								fileName = AKConfig.STRING_SYSTEM_ADDRESS
										+ "upload/feelingImages/" + result;
								
								Publiccontentimages contentImages = new Publiccontentimages();
								contentImages.setUrl(fileName);
								contentImages.setPubliccontent(content);
								contentImages.setPubliccontent(content);
								
								this.feelingManager.getContentImagesDao().save(contentImages);
								
							}
							
						}
					}
					//设置状态图片
				}
				

				// 封装返回结果
				this.status = 200;
				this.result.put("data", content);
				this.message = AKConfig.STRING_FEELING_ADD_SUCCESS;
			}
		}
		return getResult();
	}

	@RequestMapping(value = "/requestPriseFeeling")
	@ResponseBody
	/***
	 * 点赞/取消点赞
	 * @param contentId
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map requestPriseFeeling(
			@RequestParam(value = "contentId") Integer contentId,
			@RequestParam(value = "flag") short flag, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		User user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = AKConfig.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 查看当前操作状态，1:点赞,2:取消点赞
			Publiccontent content = this.feelingManager
					.findPublicContentById(contentId);

			if (flag == 1) {
				
				content.getContentprises().add(new Contentprise(user, content));

				// 保存圈子内容
				this.feelingManager.saveOrUpdate(content);
				flag = 2;
			} else {
				Object[] list = content.getContentprises().toArray();

				Set set = new HashSet();
				for (int i = 0; i < list.length; i++) {
					Contentprise contentPrise = (Contentprise) list[i];
					Integer userId = contentPrise.getUser().getUserId();
					Integer selfId = user.getUserId();
					if (userId.equals(selfId)) {
						user.setName(contentPrise.getUser().getName());
						this.feelingManager.cancelPrise(contentPrise);
					}

				}

				flag = 1;
			}

			// 封装返回结果
			this.status = 200;
			String name = this.userManager.findUserNameByUserId(user);
			
			if(name==null || name.equals(""))
			{
				if(user.getTelephone()!=null &&!user.getTelephone().equals(""))
				{
					String telephone = user.getTelephone();
					Integer length = telephone.length();
					name = "用户" + telephone.substring(length - 4, length);
				}else{
					String userIdStr = user.getUserId().toString();
					Integer length = userIdStr.length();
					name =length>4?userIdStr.substring(length-4, length):userIdStr;
					name = "用户"+name;
				}
				
			}
			
			Map map = new HashMap();
			map.put("flag", flag); 
			map.put("name", name); 

			this.result.put("data", map);
			if (flag == 2) {
				this.message = AKConfig.STRING_FEELING_PRISE_ADD_SUCCESS;
			} else {
				this.message = AKConfig.STRING_FEELING_PRISE_EREASE_SUCCESS;
			}
		}

		return getResult();
	}

	@RequestMapping(value = "/requestCommentFeeling")
	@ResponseBody
	/***
	 * 点赞/取消点赞
	 * @param contentId
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map requestCommentFeeling(
			@RequestParam(value = "contentId") Integer contentId,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "atUserId") Integer userId,
			@RequestParam(value = "flag") short flag, HttpSession session) {

		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		User user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = AKConfig.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 查看当前操作状态，1:评论,2:回复
			Publiccontent publicContent = this.feelingManager
					.findPublicContentById(contentId);

			Contentcomment comment = new Contentcomment();
			comment.setContent(content);
			comment.setPubliccontent(publicContent);
			comment.setUserByUserId(user);
			if (flag != 1) {
				User atUser = this.userManager.findUserById(userId);
				comment.setUserByAtUserId(atUser);
			}
			// 保存回复
			publicContent.getContentcomments().add(comment);

			this.feelingManager.saveOrUpdate(publicContent);

			// 处理返回值
			User user1 = comment.getUserByUserId();
			if (user1.getAuthentics() != null) {
				Object[] l = user.getAuthentics().toArray();
				if (l.length > 0) {
					Authentic authentic = (Authentic) l[0];
					user1.setName(authentic.getName());
				} else {
					user1.setName("");
				}

			} else {
				user1.setName("");
			}
			user1.setAuthentics(null);
			user1.setTelephone(null);
			user1.setPlatform(null);
			user1.setLoginTime(null);
			comment.setUserByUserId(user1);

			user1 = comment.getUserByAtUserId();
			if (user1 != null) {
				if (user.getAuthentics() != null) {
					Object[] l = user.getAuthentics().toArray();
					if (l.length > 0) {
						Authentic authentic = (Authentic) l[0];
						user1.setName(authentic.getName());
					} else {
						user1.setName("");
					}

				} else {
					user1.setName("");
				}
				user1.setAuthentics(null);
				user1.setTelephone(null);
				user1.setPlatform(null);
				user1.setLoginTime(null);
				comment.setUserByAtUserId(user1);
			}

			// 封装返回结果
			this.status = 200;
			this.result.put("data", comment);
			if (flag != 1) {
				this.message = AKConfig.STRING_FEELING_REPLY_SUCCESS;
			} else {
				this.message = AKConfig.STRING_FEELING_COMMENT_SUCCESS;
			}
		}

		return getResult();
	}

	
	@RequestMapping(value = "/requestPublicContentDelete")
	@ResponseBody
	/***
	 * 状态分享
	 * @param contentId
	 * @param session
	 * @return
	 */
	public Map requestPublicContentDelete(
			@RequestParam(value = "contentId") Integer contentId,
			HttpSession session) {
		
		this.result = new HashMap();
		this.status = 200;
		this.result.put("data", "");
		
		//删除圈子
		this.feelingManager.deletePublicContent(contentId);
		
		this.message = AKConfig.STRING_PROJECT_DELETE_SUCCESS;
		return getResult();
	}
	@RequestMapping(value = "/requestContentCommentDelete")
	@ResponseBody
	/***
	 * 状态分享
	 * @param commentId
	 * @param session
	 * @return
	 */
	public Map requestContentCommentDelete(
			@RequestParam(value = "commentId") Integer commentId,
			HttpSession session) {
		
		this.result = new HashMap();
		this.status = 200;
		this.result.put("data", "");
		
		//删除圈子
		this.feelingManager.deletePublicContentComment(commentId);
		
		this.message = AKConfig.STRING_PROJECT_DELETE_SUCCESS;
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
			user = this.userManager.findUserById(userId);
		}
		return user;
	}

}
