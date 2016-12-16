package com.akchengtou.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.akchengtou.web.entity.Announcement;
import com.akchengtou.web.entity.AnnouncementDAO;
import com.akchengtou.web.entity.Feedback;
import com.akchengtou.web.entity.FeedbackDAO;
import com.akchengtou.web.entity.Feedbackimages;
import com.akchengtou.web.entity.FeedbackimagesDAO;
import com.akchengtou.web.entity.Message;
import com.akchengtou.web.entity.MessageDAO;
import com.akchengtou.web.entity.User;
import com.akchengtou.web.entity.Versioncontroll;
import com.akchengtou.web.entity.VersioncontrollDAO;

public class SystemManager {

	private AnnouncementDAO noticeDao; // 系统公告
	private VersioncontrollDAO versioncontrollDao; // 版本更新信息
	private MessageDAO systemMessageDao;
	private FeedbackDAO feedbackDao;
	private FeedbackimagesDAO feedBackImagesDAO;

	/***
	 * 根据设备类型获取系统公告信息
	 * 
	 * @param platform
	 *            设备类型 0:Android,1:iOS
	 * @return 最近系统公告
	 */
	public List findNoticeByPlatform() {
		List<Announcement> list = getNoticeDao().findAll();
		if (list != null && list.size() > 0) {
			return list;
		}
		return new ArrayList();
	}

	/***
	 * 根据设备类型获取版本更新信息
	 * 
	 * @param platform
	 *            设备类型 0:Android,1:iOS
	 * @return 最新启动页面信息
	 */
	public Versioncontroll findVersionInfoByPlatform(short platform) {
		List<Versioncontroll> list = getVersioncontrollDao().findByPlatform(
				platform);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}


	/***
	 * 获取用户站内信list
	 * 
	 * @param user
	 * @param page
	 * @return
	 */
	public List findSystemMessageListByUser(User user, Integer page) {
		List list = new ArrayList();
		list = getSystemMessageDao()
				.findByPropertyWithPage("user", user, page);
		return list;
	}

	/***
	 * 根据id获取站内信
	 * 
	 * @param messageId
	 * @return
	 */
	public Message findMessageById(Integer messageId) {
		return getSystemMessageDao().findById(messageId);
	}

	/***
	 * 删除站内信
	 * 
	 * @param message
	 *            站内信
	 */
	public void deleteSystemMessage(Message message) {
		getSystemMessageDao().delete(message);
	}

	/***
	 * 更新信息
	 * 
	 * @param message
	 */
	public void saveOrUpdate(Message message) {
		getSystemMessageDao().saveOrUpdate(message);
	}

	/***
	 * 获取用户未读信息条数
	 * 
	 * @param user
	 * @return
	 */
	public boolean findUserNotReadMessageFlag(User user) {
		boolean flag = false;
		boolean read = false;
		Map map = new HashMap();
		map.put("users", user);
		map.put("isRead", read);
		map.put("valid", true);
		Integer count = getSystemMessageDao().counterByProperties(map);
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	/***
	 * 添加反馈
	 * 
	 * @param userId
	 *            用户id
	 * @param content
	 *            内容
	 */
	public void addFeedback(User user, String content, List images) {
		Feedback feed = new Feedback();
		feed.setContent(content);
		feed.setUser(user);
		feed.setFeedDate(new Date());

		getFeedbackDao().save(feed);
		
		//保存图片
		for(int i = 0;i<images.size();i++)
		{
			String fileName = (String) images.get(i);
			Feedbackimages img = new Feedbackimages();
			img.setUrl(fileName);
			img.setFeedback(feed);
			
			getFeedBackImagesDAO().save(img);
		}
	}

	public AnnouncementDAO getNoticeDao() {
		return noticeDao;
	}

	@Autowired
	public void setNoticeDao(AnnouncementDAO noticeDao) {
		this.noticeDao = noticeDao;
	}

	public MessageDAO getSystemMessageDao() {
		return systemMessageDao;
	}

	@Autowired
	public void setSystemMessageDao(MessageDAO systemMessageDao) {
		this.systemMessageDao = systemMessageDao;
	}

	public FeedbackDAO getFeedbackDao() {
		return feedbackDao;
	}

	@Autowired
	public void setFeedbackDao(FeedbackDAO feedbackDao) {
		this.feedbackDao = feedbackDao;
	}

	public VersioncontrollDAO getVersioncontrollDao() {
		return versioncontrollDao;
	}

	@Autowired
	public void setVersioncontrollDao(VersioncontrollDAO versioncontrollDao) {
		this.versioncontrollDao = versioncontrollDao;
	}

	/**
	 * @return the feedBackImagesDAO
	 */
	public FeedbackimagesDAO getFeedBackImagesDAO() {
		return feedBackImagesDAO;
	}

	/**
	 * @param feedBackImagesDAO the feedBackImagesDAO to set
	 */
	public void setFeedBackImagesDAO(FeedbackimagesDAO feedBackImagesDAO) {
		this.feedBackImagesDAO = feedBackImagesDAO;
	}

}
