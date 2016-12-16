package com.akchengtou.web.manager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.akchengtou.web.entity.Authentic;
import com.akchengtou.web.entity.Contentcomment;
import com.akchengtou.web.entity.ContentcommentDAO;
import com.akchengtou.web.entity.Contentprise;
import com.akchengtou.web.entity.ContentpriseDAO;
import com.akchengtou.web.entity.Publiccontent;
import com.akchengtou.web.entity.PubliccontentDAO;
import com.akchengtou.web.entity.PubliccontentimagesDAO;
import com.akchengtou.web.entity.User;

public class FeelingManager {

	private PubliccontentDAO publicContentDao;
	private ContentpriseDAO contentPriseDao;
	private ContentcommentDAO commentDao;
	private PubliccontentimagesDAO contentImagesDao;

	public Publiccontent findPublicContentById(Integer contentId) {
		return getPublicContentDao().findById(contentId);
	}

	/***
	 * 分页查询圈子信息
	 * 
	 * @param currentPage
	 *            当前页
	 * @return
	 */
	public List findFeelingByCursor(int currentPage, Integer userId,
			Integer platform) {
		List list = getPublicContentDao().findByCursor(currentPage);
		if (list != null && list.size() > 0) {
			Publiccontent content = null;
			for (int i = 0; i < list.size(); i++) {
				content = (Publiccontent) list.get(i);

				User userPublic = new User();

				User user = content.getUser();
				// 获取认证信息
				if (user.getAuthentics() != null
						&& user.getAuthentics().size() > 0) {
					Object[] authentices = user.getAuthentics().toArray();
					Authentic authentic = (Authentic) authentices[0];

					authentic.setAuthenticstatus(null);
					// authentic.setIdentiytype(null);
					authentic.setAuthId(null);

					if (authentic.getName() == null
							|| authentic.getName().equals("")) {
						if (user.getTelephone() != null
								&& !user.getTelephone().equals("")) {
							String telephone = user.getTelephone();
							Integer length = telephone.length();
							String name = "用户"
									+ telephone.substring(length - 4, length);
							authentic.setName(name);
						} else {
							String userIdStr = user.getUserId().toString();
							Integer length = userIdStr.length();
							String name = length > 4 ? userIdStr.substring(
									length - 4, length) : userIdStr;
							name = "用户" + name;
							authentic.setName(name);
						}

					}

					userPublic.setAuthentics(user.getAuthentics());
					userPublic.setUserId(user.getUserId());
					userPublic.setImage(user.getImage());
					userPublic.setName(authentic.getName());
				}

				content.setUser(userPublic);

				// 开始排除点赞中不需要字段
				if (content.getContentprises() != null
						&& content.getContentprises().size() > 0) {
					Iterator<Contentprise> iterator = content
							.getContentprises().iterator();
					while (iterator.hasNext()) {
						Contentprise contentprise = iterator.next();
						User temp = new User();
						user = contentprise.getUser();
						if (user.getAuthentics() != null) {
							Object[] l = user.getAuthentics().toArray();
							if (l.length > 0) {
								Authentic authentic = (Authentic) l[0];
								user.setName(authentic.getName());
							} else {
								user.setName("");
							}

						} else {
							user.setName("");
						}

						if (user.getUserId() == userId) {
							content.setFlag(true);
						}

						temp.setAuthentics(null);
						temp.setName(user.getName());
						temp.setUserId(user.getUserId());
						temp.setImage(user.getImage());

						contentprise.setUser(temp);

						Integer userInstanceId = contentprise.getUser()
								.getUserId();

						if (userInstanceId.equals(userId)) {
							content.setFlag(true);
						}
					}
				}

			}

		}

		return list;
	}

	/***
	 * 分页查询圈子信息
	 * 
	 * @param currentPage
	 *            当前页
	 * @return
	 */
	public List findFeelingByUser(User u, int currentPage) {
		List list = getPublicContentDao().findByUserAndCursor(u, currentPage);
		if (list != null && list.size() > 0) {
			Publiccontent content = null;
			for (int i = 0; i < list.size(); i++) {
				content = (Publiccontent) list.get(i);

				User userPublic = new User();

				User user = content.getUser();
				// 获取认证信息
				if (user.getAuthentics() != null
						&& user.getAuthentics().size() > 0) {
					Object[] authentices = user.getAuthentics().toArray();
					Authentic authentic = (Authentic) authentices[0];

					authentic.setAuthenticstatus(null);
					authentic.setAuthId(null);

					if (authentic.getName() == null
							|| authentic.getName().equals("")) {
						if (user.getTelephone() != null
								&& !user.getTelephone().equals("")) {
							String telephone = user.getTelephone();
							Integer length = telephone.length();
							String name = "用户"
									+ telephone.substring(length - 4, length);
							authentic.setName(name);
						} else {
							String userIdStr = user.getUserId().toString();
							Integer length = userIdStr.length();
							String name = length > 4 ? userIdStr.substring(
									length - 4, length) : userIdStr;
							name = "用户" + name;
							authentic.setName(name);
						}
					}

					userPublic.setAuthentics(user.getAuthentics());
					userPublic.setUserId(user.getUserId());
					userPublic.setImage(user.getImage());
					userPublic.setName(authentic.getName());
				}

				content.setUser(userPublic);

				// 开始排除点赞中不需要字段
				if (content.getContentprises() != null
						&& content.getContentprises().size() > 0) {
					Iterator<Contentprise> iterator = content
							.getContentprises().iterator();
					while (iterator.hasNext()) {
						Contentprise contentprise = iterator.next();
						User temp = new User();
						user = contentprise.getUser();
						if (user.getAuthentics() != null) {
							Object[] l = user.getAuthentics().toArray();
							if (l.length > 0) {
								Authentic authentic = (Authentic) l[0];
								user.setName(authentic.getName());
							} else {
								user.setName("");
							}

						} else {
							user.setName("");
						}

						if (user.getUserId() == u.getUserId()) {
							content.setFlag(true);
						}

						temp.setAuthentics(null);
						temp.setName(user.getName());
						temp.setUserId(user.getUserId());
						temp.setImage(user.getImage());

						contentprise.setUser(temp);

						Integer userInstanceId = contentprise.getUser()
								.getUserId();

						if (userInstanceId.equals(u.getUserId())) {
							content.setFlag(true);
						}
					}
				}

			}

		}

		return list;
	}

	/***
	 * 圈子
	 * 
	 * @param feelingId
	 * @return
	 */
	public Publiccontent findFeelingById(Integer feelingId, Integer userId,
			int platform) {
		Publiccontent content = getPublicContentDao().findById(feelingId);
		User userPublic = new User();

		User user = content.getUser();
		// 获取认证信息
		if (user.getAuthentics() != null && user.getAuthentics().size() > 0) {
			Object[] authentices = user.getAuthentics().toArray();
			Authentic authentic = (Authentic) authentices[0];

			authentic.setAuthenticstatus(null);
			authentic.setAuthId(null);

			userPublic.setAuthentics(user.getAuthentics());
			userPublic.setUserId(user.getUserId());
			userPublic.setImage(user.getImage());
			userPublic.setName(authentic.getName());

			if (authentic.getName() == null || authentic.getName().equals("")) {

				if (user.getTelephone() != null
						&& !user.getTelephone().equals("")) {
					String telephone = user.getTelephone();
					Integer length = telephone.length();
					String name = "用户"
							+ telephone.substring(length - 4, length);
					userPublic.setName(name);
				} else {
					String userIdStr = user.getUserId().toString();
					Integer length = userIdStr.length();
					String name = length > 4 ? userIdStr.substring(length - 4,
							length) : userIdStr;
					name = "用户" + name;
					userPublic.setName(name);
				}
			}
		}

		content.setUser(userPublic);

		// 开始排除评论中不需要字段
		if (content.getContentcomments() != null
				&& content.getContentcomments().size() > 0) {
			Iterator<Contentcomment> iterator = content.getContentcomments()
					.iterator();
			while (iterator.hasNext()) {
				Contentcomment comment = iterator.next();

				User temp = new User();
				user = comment.getUserByUserId();
				if (user.getAuthentics() != null) {
					Object[] l = user.getAuthentics().toArray();
					if (l.length > 0) {
						Authentic authentic = (Authentic) l[0];
						user.setName(authentic.getName());
					} else {
						user.setName("");
					}

				} else {
					user.setName("");
				}

				temp.setAuthentics(null);
				temp.setName(user.getName());
				temp.setUserId(user.getUserId());
				temp.setImage(user.getImage());

				if (temp.getName() == null || temp.getName().equals("")) {

					if (user.getTelephone() != null
							&& !user.getTelephone().equals("")) {
						String telephone = user.getTelephone();
						Integer length = telephone.length();
						String name = "用户"
								+ telephone.substring(length - 4, length);
						temp.setName(name);
					} else {
						String userIdStr = user.getUserId().toString();
						Integer length = userIdStr.length();
						String name = length > 4 ? userIdStr.substring(
								length - 4, length) : userIdStr;
						name = "用户" + name;
						temp.setName(name);
					}
				}

				comment.setUserByUserId(temp);

				temp = new User();
				user = comment.getUserByAtUserId();
				if (user != null && user.getAuthentics() != null) {
					Object[] l = user.getAuthentics().toArray();
					if (l.length > 0) {
						Authentic authentic = (Authentic) l[0];
						user.setName(authentic.getName());
					} else {
						user.setName("");
					}

					temp.setAuthentics(null);
					temp.setName(user.getName());
					temp.setUserId(user.getUserId());
					temp.setImage(user.getImage());
					if (temp.getName() == null || temp.getName().equals("")) {
						if (user.getTelephone() != null
								&& !user.getTelephone().equals("")) {
							String telephone = user.getTelephone();
							Integer length = telephone.length();
							String name = "用户"
									+ telephone.substring(length - 4, length);
							temp.setName(name);
						} else {
							String userIdStr = user.getUserId().toString();
							Integer length = userIdStr.length();
							String name = length > 4 ? userIdStr.substring(
									length - 4, length) : userIdStr;
							name = "用户" + name;
							temp.setName(name);
						}
					}

					comment.setUserByAtUserId(temp);
				}

			}
		}

		// 开始排除点赞中不需要字段
		if (content.getContentprises() != null
				&& content.getContentprises().size() > 0) {
			Iterator<Contentprise> iterator = content.getContentprises()
					.iterator();
			while (iterator.hasNext()) {
				Contentprise contentprise = iterator.next();
				User temp = new User();
				user = contentprise.getUser();

				if (user.getUserId().equals(userId)) {
					content.setFlag(true);
				}
				// if (user.getAuthentics() != null) {
				// Object[] l = user.getAuthentics().toArray();
				// if (l.length > 0) {
				// Authentic authentic = (Authentic) l[0];
				// user.setName(authentic.getName());
				// } else {
				// user.setName("");
				// }
				//
				// } else {
				// user.setName("");
				// }

				if (user.getUserId() == userId) {
					content.setFlag(true);
				}

				temp.setAuthentics(null);
				temp.setName(user.getName());
				temp.setUserId(user.getUserId());
				temp.setImage(user.getImage());

				if (temp.getName() == null || temp.getName().equals("")) {
					if (user.getTelephone() != null
							&& !user.getTelephone().equals("")) {
						String telephone = user.getTelephone();
						Integer length = telephone.length();
						String name = "用户"
								+ telephone.substring(length - 4, length);
						temp.setName(name);
					} else {
						String userIdStr = user.getUserId().toString();
						Integer length = userIdStr.length();
						String name = length > 4 ? userIdStr.substring(
								length - 4, length) : userIdStr;
						name = "用户" + name;
						temp.setName(name);
					}
				}

				//
				// // user.setAuthentics(null);
				// user.setUsertatus(null);
				// user.setTelephone(null);
				// user.setPassword(null);
				// user.setPlatform(null);
				// user.setLastLoginDate(null);
				contentprise.setUser(temp);
			}
		}

		// 转发量

		Map map = new HashMap();
		map.put("contentId", content.getContentId());

		return content;
	}

	/***
	 * 分页查询评论列表
	 * 
	 * @param page
	 * @param feelingId
	 * @return
	 */
	public List findFeelingCommentByPage(Integer page, Integer feelingId,
			int platform) {
		Publiccontent content = this.findPublicContentById(feelingId);

		List list = null;
		list = getCommentDao().findByPropertyByPage("publiccontent", content,
				page);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Contentcomment comment = (Contentcomment) list.get(i);

				User temp = new User();
				User user = comment.getUserByUserId();
				if (user.getAuthentics() != null) {
					Object[] l = user.getAuthentics().toArray();
					if (l.length > 0) {
						Authentic authentic = (Authentic) l[0];
						user.setName(authentic.getName());
					} else {
						user.setName("");
					}

				} else {
					user.setName("");
				}

				temp.setAuthentics(null);
				temp.setName(user.getName());
				temp.setUserId(user.getUserId());
				temp.setImage(user.getImage());

				if (temp.getName() == null || temp.getName().equals("")) {
					if (user.getTelephone() != null
							&& !user.getTelephone().equals("")) {
						String telephone = user.getTelephone();
						Integer length = telephone.length();
						String name = "用户"
								+ telephone.substring(length - 4, length);
						temp.setName(name);
					} else {
						String userIdStr = user.getUserId().toString();
						Integer length = userIdStr.length();
						String name = length > 4 ? userIdStr.substring(
								length - 4, length) : userIdStr;
						name = "用户" + name;
						temp.setName(name);
					}
				}

				comment.setUserByUserId(temp);

				temp = new User();
				user = comment.getUserByAtUserId();
				if (user != null && user.getAuthentics() != null) {
					Object[] l = user.getAuthentics().toArray();
					if (l.length > 0) {
						Authentic authentic = (Authentic) l[0];
						user.setName(authentic.getName());
					} else {
						user.setName("");
					}

					temp.setAuthentics(null);
					temp.setName(user.getName());
					temp.setUserId(user.getUserId());
					temp.setImage(user.getImage());

					if (temp.getName() == null || temp.getName().equals("")) {
						if (user.getTelephone() != null
								&& !user.getTelephone().equals("")) {
							String telephone = user.getTelephone();
							Integer length = telephone.length();
							String name = "用户"
									+ telephone.substring(length - 4, length);
							temp.setName(name);
						} else {
							String userIdStr = user.getUserId().toString();
							Integer length = userIdStr.length();
							String name = length > 4 ? userIdStr.substring(
									length - 4, length) : userIdStr;
							name = "用户" + name;
							temp.setName(name);
						}
					}

					comment.setUserByAtUserId(temp);
				}

				String c = comment.getContent();

				if (platform != 0) {

					try {
						c = URLEncoder.encode(c, "utf-8");
						comment.setContent(c);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return list;
	}

	

	/***
	 * 添加圈子记录
	 * 
	 * @param content
	 *            圈子记录
	 * @return
	 */
	public void addPublicContent(Publiccontent content) {
		getPublicContentDao().save(content);
	}

	/**
	 * 更新圈子
	 * 
	 * @param content
	 */
	public void saveOrUpdate(Publiccontent content) {
		getPublicContentDao().saveOrUpdate(content);
	}

	/***
	 * 取消点赞
	 * 
	 * @param prise
	 */
	public void cancelPrise(Contentprise prise) {
		getContentPriseDao().delete(prise);
	}

	/***
	 * 删除圈子
	 * 
	 * @param contentId
	 */
	public void deletePublicContent(Integer contentId) {
		Publiccontent content = getPublicContentDao().findById(contentId);

		// 删除
		getPublicContentDao().delete(content);
	}

	public void deletePublicContentComment(Integer commentId) {
		Contentcomment comment = getCommentDao().findById(commentId);
		getCommentDao().delete(comment);
	}

	public PubliccontentDAO getPublicContentDao() {
		return publicContentDao;
	}

	@Autowired
	public void setPublicContentDao(PubliccontentDAO publicContentDao) {
		this.publicContentDao = publicContentDao;
	}

	public ContentpriseDAO getContentPriseDao() {
		return contentPriseDao;
	}

	@Autowired
	public void setContentPriseDao(ContentpriseDAO contentPriseDao) {
		this.contentPriseDao = contentPriseDao;
	}

	public ContentcommentDAO getCommentDao() {
		return commentDao;
	}

	@Autowired
	public void setCommentDao(ContentcommentDAO commentDao) {
		this.commentDao = commentDao;
	}

	public PubliccontentimagesDAO getContentImagesDao() {
		return contentImagesDao;
	}

	@Autowired
	public void setContentImagesDao(PubliccontentimagesDAO contentImagesDao) {
		this.contentImagesDao = contentImagesDao;
	}

}
