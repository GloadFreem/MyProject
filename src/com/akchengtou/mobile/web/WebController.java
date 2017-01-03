package com.akchengtou.mobile.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import com.akchengtou.web.entity.Announcement;
import com.akchengtou.web.entity.Attendance;
import com.akchengtou.web.entity.Attendancerecord;
import com.akchengtou.web.entity.Authentic;
import com.akchengtou.web.entity.Authenticstatus;
import com.akchengtou.web.entity.Event;
import com.akchengtou.web.entity.Identity;
import com.akchengtou.web.entity.Identiytype;
import com.akchengtou.web.entity.Member;
import com.akchengtou.web.entity.Message;
import com.akchengtou.web.entity.MessageBean;
import com.akchengtou.web.entity.Orderservice;
import com.akchengtou.web.entity.Paytype;
import com.akchengtou.web.entity.Propertycharges;
import com.akchengtou.web.entity.Publiccontent;
import com.akchengtou.web.entity.Servicetype;
import com.akchengtou.web.entity.Systemuser;
import com.akchengtou.web.entity.Task;
import com.akchengtou.web.entity.User;
import com.akchengtou.web.manager.AuthenticManager;
import com.akchengtou.web.manager.FeelingManager;
import com.akchengtou.web.manager.ServiceManager;
import com.akchengtou.web.manager.SystemManager;
import com.akchengtou.web.manager.TaskManager;
import com.akchengtou.web.manager.UserManager;

@Controller
public class WebController extends BaseController {

	@Autowired
	private UserManager userManger;
	@Autowired
	private AuthenticManager authenticManager;
	@Autowired
	private ServiceManager serviceManager;
	@Autowired
	private FeelingManager feelingManager;
	@Autowired
	private TaskManager taskManager;
	@Autowired
	private SystemManager systemManager;

	/***
	 * ---------------------------------------------后端管理系统升级--------------------
	 * -----------------------
	 ***/

	@RequestMapping(value = "/newSystem/adminLogin")
	public String loginAction(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password,
			HttpSession session, ModelMap map) {
		if (name == null || password == null) {
			map.put("tip", "账号或密码不能为空!");
			return "/new_admin/login";
		}

		Object userId = session.getAttribute("userId");
		// password = MD5.GetMD5Code(password);

		List list = this.userManger.getSystemUserDao().findByAccount(name);
		if (list != null && list.size() > 0) {
			Systemuser user = (Systemuser) list.get(0);
			if (user.getAccount().equals(name)
					&& user.getPassword().equals(password)) {
				session.setAttribute("userId", user.getUserId());
			} else {
				map.put("tip", "账号或密码错误，请检查后重试！");
				return "/new_admin/login";
			}
		} else {
			map.put("tip", "该账号不存在，请联系管理人员!");
			return "/new_admin/login";
		}

		map.put("tip", "");
		// session.setAttribute("userId", null);

		map.put("account", name);
		map.put("content", "content");
		map.put("menu", 0);
		map.put("submenu", 0);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	/***
	 * 首页
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/index")
	public String requestIndexPage(ModelMap map, HttpSession session) {
		map.put("content", "content");
		map.put("menu", 0);
		map.put("submenu", 0);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// ---------------用户------------------------//
	// 用户列表
	@RequestMapping(value = "newSystem/userList")
	public String userList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}
		

		List list = this.userManger.getUserDao().findByPage(size, page);

		Integer count = this.userManger.getUserDao().countOfInstance();

		List pages = new ArrayList();
		Integer num = count / size;
		if (page < num) {
			if (page < 5) {
				if(num-page>5)
				{
					for (int i = 0; i < 5; i++) {
						pages.add(i);
					}
				}else{
					for (int i = 0; i <= num; i++) {
						pages.add(i);
					}
				}
			} else {
				for (int i = page; i < page + 5; i++) {
					pages.add(i);
				}
			}
		} else {
			page = num;

			for (int i = 0; i <= page; i++) {
				pages.add(i);
			}
		}

		if (sortmenu == null) {
			sortmenu = 0;
		}

		map.put("result", list);
		map.put("content", "table-user-list");

		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);

		map.put("sizes", AKConfig.SIZES);
		map.put("size", size);
		map.put("page", page);
		map.put("count", count/size);
		map.put("pages", pages);

		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 认证审核
	@RequestMapping(value = "newSystem/authenticList")
	public String authenticList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		
		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.userManger.getAuthenticDao().findByPage(size, page);
		Integer count = this.userManger.getAuthenticDao().countOfInstance();

		List pages = new ArrayList();
		Integer num = count / size;
		if (page < num) {
			if (page < 5) {
				if(num-page>5)
				{
					for (int i = 0; i < 5; i++) {
						pages.add(i);
					}
				}else{
					for (int i = 0; i <= num; i++) {
						pages.add(i);
					}
				}
			} else {
				for (int i = page; i < page + 5; i++) {
					pages.add(i);
				}
			}
		} else {
			page = num;

			for (int i = 0; i <= page; i++) {
				pages.add(i);
			}
		}

		if (sortmenu == null) {
			sortmenu = 0;
		}
		
		map.put("sizes", AKConfig.SIZES);
		map.put("size", size);
		map.put("page", page);
		map.put("count", count/size);
		map.put("pages", pages);
		
		
		map.put("result", list);
		map.put("content", "table-authentic-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 员工
	@RequestMapping(value = "newSystem/memberList")
	public String memberList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}
		
		List list = this.userManger.getMemberDao().findByPage(size, page);
		Integer count = this.userManger.getMemberDao().countOfInstance();

		List pages = new ArrayList();
		Integer num = count / size;
		if (page < num) {
			if (page < 5) {
				if(num-page>5)
				{
					for (int i = 0; i < 5; i++) {
						pages.add(i);
					}
				}else{
					for (int i = 0; i <= num; i++) {
						pages.add(i);
					}
				}
			} else {
				for (int i = page; i < page + 5; i++) {
					pages.add(i);
				}
			}
		} else {
			page = num;

			for (int i = 0; i <= page; i++) {
				pages.add(i);
			}
		}

		if (sortmenu == null) {
			sortmenu = 0;
		}
		
		map.put("sizes", AKConfig.SIZES);
		map.put("size", size);
		map.put("page", page);
		map.put("count", count/size);
		map.put("pages", pages);
		map.put("result", list);
		map.put("content", "table-member-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 排行榜
	@RequestMapping(value = "newSystem/userRankList")
	public String userRankList(
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		
		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}
		
		if (type == null) {
			type = 1;
		}
		Authentic authentic = new Authentic();

		Identity identity = new Identity();
		identity.setIdentiyTypeId(type);

		authentic.setIdentity(identity);

		// 根据用户身份类型获取排行榜
		List list = this.authenticManager.findRankingByIdentitype(
				authentic.getIdentity(), page,size);
		Integer count = this.authenticManager.getAuthenticDao().countOfInstance();

		List pages = new ArrayList();
		Integer num = count / size;
		if (page < num) {
			if (page < 5) {
				if(num-page>5)
				{
					for (int i = 0; i < 5; i++) {
						pages.add(i);
					}
				}else{
					for (int i = 0; i <= num; i++) {
						pages.add(i);
					}
				}
			} else {
				for (int i = page; i < page + 5; i++) {
					pages.add(i);
				}
			}
		} else {
			page = num;

			for (int i = 0; i <= page; i++) {
				pages.add(i);
			}
		}

		if (sortmenu == null) {
			sortmenu = 0;
		}
		
		map.put("sizes", AKConfig.SIZES);
		map.put("size", size);
		map.put("page", page);
		map.put("count", count/size);
		map.put("pages", pages);
		
		

		map.put("result", list);
		map.put("content", "table-user-rank-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/serviceList")
	public String serviceList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}
		
		List list = this.serviceManager.getServiceTypeDao().findByPage(size, page);
		Integer count = this.serviceManager.getServiceTypeDao().countOfInstance();

		List pages = new ArrayList();
		Integer num = count / size;
		if (page < num) {
			if (page < 5) {
				if(num-page>5)
				{
					for (int i = 0; i < 5; i++) {
						pages.add(i);
					}
				}else{
					for (int i = 0; i <= num; i++) {
						pages.add(i);
					}
				}
			} else {
				for (int i = page; i < page + 5; i++) {
					pages.add(i);
				}
			}
		} else {
			page = num;

			for (int i = 0; i <= page; i++) {
				pages.add(i);
			}
		}

		if (sortmenu == null) {
			sortmenu = 0;
		}
		
		map.put("sizes", AKConfig.SIZES);
		map.put("size", size);
		map.put("page", page);
		map.put("count", count/size);
		map.put("pages", pages);
		
		map.put("result", list);
		map.put("content", "table-service-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 物业费订单
	@RequestMapping(value = "newSystem/propertyChargesList")
	public String propertyChargesList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {


		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}
		
		
		List list = this.serviceManager.getPropertychargesDao().findByPage(size, page);
		Integer count = this.serviceManager.getPropertychargesDao().countOfInstance();

		List pages = new ArrayList();
		Integer num = count / size;
		if (page < num) {
			if (page < 5) {
				if(num-page>5)
				{
					for (int i = 0; i < 5; i++) {
						pages.add(i);
					}
				}else{
					for (int i = 0; i <= num; i++) {
						pages.add(i);
					}
				}
			} else {
				for (int i = page; i < page + 5; i++) {
					pages.add(i);
				}
			}
		} else {
			page = num;

			for (int i = 0; i <= page; i++) {
				pages.add(i);
			}
		}

		if (sortmenu == null) {
			sortmenu = 0;
		}
		
		map.put("sizes", AKConfig.SIZES);
		map.put("size", size);
		map.put("page", page);
		map.put("count", count/size);
		map.put("pages", pages);
		
		map.put("result", list);
		map.put("content", "table-charges-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 预约订单
	@RequestMapping(value = "newSystem/orderList")
	public String orderList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		List list = this.serviceManager.getOrderServiceDao().findAll();
		map.put("result", list);
		map.put("content", "table-order-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 员工订单
	@RequestMapping(value = "newSystem/memberOrderList")
	public String memberOrderList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		List<Member> list = this.serviceManager.getMemberDao().findAll();

		// ser
		List result = new ArrayList();
		Map data;
		for (int i = 0; i < list.size(); i++) {
			data = new HashMap();

			Member member = list.get(i);
			List l = this.serviceManager.getServiceDao().findByProperty(
					"member", member);

			data.put("member", member);
			data.put("orders", l);

			result.add(data);

		}
		map.put("result", result);
		map.put("content", "table-member-order-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 事件列表
	@RequestMapping(value = "newSystem/eventList")
	public String eventList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		List list = this.serviceManager.getEventDao().findAll();
		map.put("result", list);
		map.put("content", "table-event-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 圈子列表
	@RequestMapping(value = "newSystem/feelingList")
	public String feelingList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		List list = this.feelingManager.getPublicContentDao().findAll();
		map.put("result", list);
		map.put("content", "table-feeling-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 任务列表
	@RequestMapping(value = "newSystem/taskList")
	public String taskList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		List list = this.taskManager.getTaskDao().findAll();
		map.put("result", list);
		map.put("content", "table-task-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 公告列表
	@RequestMapping(value = "newSystem/announceList")
	public String announceList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		List list = this.systemManager.getNoticeDao().findAll();
		map.put("result", list);
		map.put("content", "table-announce-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 消息列表
	@RequestMapping(value = "newSystem/messageList")
	public String messageList(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		List list = this.systemManager.getSystemMessageDao().findAll();
		map.put("result", list);
		map.put("content", "table-message-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/workResult")
	public String workResult(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		List list = this.taskManager.getAttendanceRecordDao().findAll();

		map.put("result", list);
		map.put("content", "table-workResult-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	// 消息列表
	@RequestMapping(value = "newSystem/employerTask")
	public String employerTask(
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		List list = this.taskManager.getAttendanceDao().findAll();
		map.put("result", list);
		map.put("content", "table-attendance-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/userDetail")
	public String userDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		User user = this.userManger.getUserDao().findById(contentId);

		map.put("result", user);
		map.put("content", "userDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/authenticDetail")
	public String authenticDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		Authentic authentic = this.authenticManager.getAuthenticDao().findById(
				contentId);

		map.put("result", authentic);
		map.put("content", "authenticDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/memberDetail")
	public String memberDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		Member member = this.userManger.getMemberDao().findById(contentId);

		Integer userId = this.userManger.getMemberDao().findByMemberIdSQL(
				member.getMemberId());
		User user = this.userManger.findUserById(userId);

		List<Servicetype> types = this.serviceManager.getServiceTypeDao()
				.findAll();

		map.put("types", types);
		map.put("user", user);
		map.put("result", member);
		map.put("content", "memberDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/serviceDetail")
	public String serviceDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (contentId != null) {
			Servicetype type = this.serviceManager.getServiceTypeDao()
					.findById(contentId);
			map.put("result", type);
		}
		map.put("content", "serviceDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/chargeDetail")
	public String chargeDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (contentId != null) {
			Propertycharges charges = this.serviceManager
					.getPropertychargesDao().findById(contentId);
			map.put("result", charges);
		}
		map.put("content", "chargeDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/orderDetail")
	public String orderDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (contentId != null) {
			Orderservice order = this.serviceManager.getOrderServiceDao()
					.findById(contentId);
			map.put("result", order);
		}
		map.put("content", "orderDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/eventDetail")
	public String eventDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (contentId != null) {
			Event event = this.serviceManager.getEventDao().findById(contentId);
			map.put("result", event);
		}
		map.put("content", "eventDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/taskDetail")
	public String TaskDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (contentId != null) {
			Task task = this.taskManager.getTaskDao().findById(contentId);
			map.put("result", task);
		}
		map.put("content", "TaskDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/feelingDetail")
	public String feelingDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (contentId != null) {
			Publiccontent content = this.feelingManager.getPublicContentDao()
					.findById(contentId);
			map.put("result", content);
		}
		map.put("content", "feelingDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/attendanceDetail")
	public String attendanceDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (contentId != null) {
			Attendance content = this.taskManager.getAttendanceDao().findById(
					contentId);
			map.put("result", content);
		}
		map.put("content", "attendanceDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/recordDetail")
	public String recordDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (contentId != null) {
			Attendancerecord content = this.taskManager
					.getAttendanceRecordDao().findById(contentId);
			map.put("result", content);
		}
		map.put("content", "recordDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/announceDetail")
	public String announceDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (contentId != null) {
			Announcement content = this.systemManager.getNoticeDao().findById(
					contentId);
			map.put("result", content);
		}
		map.put("content", "announceDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/messageDetail")
	public String messageDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (contentId != null) {
			Message message = this.systemManager.getSystemMessageDao()
					.findById(contentId);
			map.put("result", message);
		}
		map.put("content", "messageDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/PushMessage")
	public String PushMessage(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {
		if (contentId != null) {
			Message message = this.systemManager.getSystemMessageDao()
					.findById(contentId);
			map.put("result", message);
		}
		map.put("content", "PushMessage");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/editUser")
	public String editUser(
			@RequestParam(value = "platform", required = false) short platform,
			@RequestParam(value = "intro", required = false) String intro,
			@RequestParam(value = "regId", required = false) String regId,
			@RequestParam(value = "gender", required = false) short gender,
			@RequestParam(value = "score", required = false) Integer score,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		User user = this.userManger.getUserDao().findById(contentId);
		if (user != null) {
			user.setName(name);
			user.setTelephone(telephone);
			user.setPlatform(platform);
			user.setScore(score);
			user.setGender(gender);
			user.setRegId(regId);
			user.setIntro(intro);

			// 保存头像
			if (image != null) {
				user.setImage(image);
			}

			if (file != null) {
				// 保存图片
				String fileName = String.format(
						AKConfig.STRING_USER_HEADER_PICTURE_FORMAT,
						new Date().getTime());
				String result = FileUtil.savePicture(file, fileName,
						"upload/headerImages/");
				if (!result.equals("")) {
					fileName = AKConfig.STRING_SYSTEM_ADDRESS
							+ "upload/headerImages/" + result;
					user.setImage(fileName);
				}
			}

			this.userManger.getUserDao().saveOrUpdate(user);
		}

		map.put("result", user);
		map.put("content", "userDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/editCharge")
	public String editCharge(
			@RequestParam(value = "amount", required = false) double price,
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		Propertycharges charge;
		if (contentId != null) {

			charge = this.serviceManager.getPropertychargesDao().findById(
					contentId);
		} else {
			charge = new Propertycharges();
		}

		if (charge != null) {
			charge.setPrice(price);
			serviceManager.getPropertychargesDao().saveOrUpdate(charge);
		}

		map.put("result", charge);
		map.put("content", "chargeDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/editTask")
	public String editTask(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "time", required = false) String time,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		Task task;
		User user = null;
		if (userId != null) {
			user = this.userManger.findUserById(userId);
		}

		if (contentId != null) {

			task = this.taskManager.getTaskDao().findById(contentId);
		} else {
			task = new Task();
		}

		if (task != null) {
			task.setContent(content);
			task.setComplete(false);

			if (user != null) {
				task.setUser(user);
			}
			this.taskManager.getTaskDao().saveOrUpdate(task);
		}

		map.put("result", task);
		map.put("content", "TaskDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/editAnnounce")
	public String editAnnounce(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		Announcement task;
		User user = null;

		if (contentId != null) {

			task = this.systemManager.getNoticeDao().findById(contentId);
		} else {
			task = new Announcement();
		}

		if (task != null) {
			task.setContent(content);
			task.setTitle(title);

			if (contentId != null) {

				this.systemManager.getNoticeDao().saveOrUpdate(task);
			} else {
				this.systemManager.getNoticeDao().save(task);
			}

		}

		map.put("result", task);
		map.put("content", "announceDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/editAuthentic")
	public String editAuthentic(
			@RequestParam(value = "identity", required = false) Integer identity,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		Authentic authentic = this.userManger.getAuthenticDao().findById(
				contentId);
		if (authentic != null) {
			authentic.setName(name);
			authentic.getIdentity().setIdentiyTypeId(identity);
			authentic.getAuthenticstatus().setStatusId(status);
			// 保存头像
			if (image != null) {
				authentic.setIdCard(image);
			}

			if (file != null) {
				// 保存图片
				String fileName = String.format(
						AKConfig.STRING_USER_HEADER_PICTURE_FORMAT,
						new Date().getTime());
				String result = FileUtil.savePicture(file, fileName,
						"upload/headerImages/");
				if (!result.equals("")) {
					fileName = AKConfig.STRING_SYSTEM_ADDRESS
							+ "upload/headerImages/" + result;
					authentic.setIdCard(fileName);
				}
			}

			this.userManger.getAuthenticDao().saveOrUpdate(authentic);
		}

		map.put("result", authentic);
		map.put("content", "authenticDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/editMember")
	public String editMember(
			@RequestParam(value = "gender", required = false) double gender,
			@RequestParam(value = "position", required = false) Integer position,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		Member member = this.userManger.getMemberDao().findById(contentId);
		if (member != null) {
			member.setName(name);
			member.setTelephone(telephone);
			member.setGender(gender);
			member.getServicetype().setTypeId(position);
			// 保存头像
			if (image != null) {
				member.setImage(image);
			}

			if (file != null) {
				// 保存图片
				String fileName = String.format(
						AKConfig.STRING_USER_HEADER_PICTURE_FORMAT,
						new Date().getTime());
				String result = FileUtil.savePicture(file, fileName,
						"upload/headerImages/");
				if (!result.equals("")) {
					fileName = AKConfig.STRING_SYSTEM_ADDRESS
							+ "upload/headerImages/" + result;
					member.setImage(fileName);
				}
			}

			this.userManger.getMemberDao().saveOrUpdate(member);
		}

		List<Servicetype> types = this.serviceManager.getServiceTypeDao()
				.findAll();

		map.put("types", types);
		map.put("result", member);
		map.put("content", "memberDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/editService")
	public String editService(
			@RequestParam(value = "price", required = false) float price,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		Servicetype type;
		if (contentId != null) {
			type = this.serviceManager.getServiceTypeDao().findById(contentId);
		} else {
			type = new Servicetype();
			Paytype pay = new Paytype();
			pay.setTypeId(1);
			type.setPaytype(pay);

		}
		if (type != null) {
			type.setName(name);
			type.setContent(content);
			type.setPrice(price);
			// 保存头像
			if (image != null) {
				type.setImage(image);
			}

			if (file != null) {
				// 保存图片
				String fileName = String.format(
						AKConfig.STRING_USER_HEADER_PICTURE_FORMAT,
						new Date().getTime());
				String result = FileUtil.savePicture(file, fileName,
						"upload/headerImages/");
				if (!result.equals("")) {
					fileName = AKConfig.STRING_SYSTEM_ADDRESS
							+ "upload/headerImages/" + result;
					type.setImage(fileName);
				}
			}
			if (contentId != null) {
				this.serviceManager.getServiceTypeDao().saveOrUpdate(type);
			} else {
				this.serviceManager.getServiceTypeDao().save(type);
			}
		}

		map.put("result", type);
		map.put("content", "serviceDetail");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteUser")
	public String deleteUser(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		User user = this.userManger.getUserDao().findById(contentId);

		if (user != null) {
			this.userManger.getUserDao().delete(user);
		}

		List list = this.userManger.getUserDao().findAll();
		map.put("result", list);
		map.put("content", "table-user-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteAuthentic")
	public String deleteAuthentic(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		Authentic authentic = this.userManger.getAuthenticDao().findById(
				contentId);
		if (authentic != null) {
			this.userManger.getAuthenticDao().delete(authentic);
		}
		List list = this.userManger.getAuthenticDao().findAll();
		map.put("result", list);
		map.put("content", "table-authentic-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteMember")
	public String deleteMember(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		Member member = this.userManger.getMemberDao().findById(contentId);
		if (member != null) {
			this.userManger.getMemberDao().delete(member);
		}

		List list = this.userManger.getMemberDao().findAll();
		map.put("result", list);
		map.put("content", "table-member-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteOrder")
	public String deleteOrder(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		Orderservice service = this.serviceManager.getOrderServiceDao()
				.findById(contentId);

		if (service != null) {
			this.serviceManager.getOrderServiceDao().delete(service);
		}
		List list = this.serviceManager.getOrderServiceDao().findAll();
		map.put("result", list);
		map.put("content", "table-order-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteCharge")
	public String deleteCharge(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		Propertycharges charge = this.serviceManager.getPropertychargesDao()
				.findById(contentId);

		if (charge != null) {
			this.serviceManager.getPropertychargesDao().delete(charge);
		}
		List list = this.serviceManager.getPropertychargesDao().findAll();
		map.put("result", list);
		map.put("content", "table-charges-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteService")
	public String deleteService(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		Servicetype type = this.serviceManager.getServiceTypeDao().findById(
				contentId);
		if (type != null) {
			this.serviceManager.getServiceTypeDao().delete(type);
		}
		List list = this.serviceManager.getServiceTypeDao().findAll();
		map.put("result", list);
		map.put("content", "table-service-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteEvent")
	public String deleteEvent(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		Event event = this.serviceManager.getEventDao().findById(contentId);
		if (event != null) {
			this.serviceManager.getEventDao().delete(event);
		}
		List list = this.serviceManager.getEventDao().findAll();
		map.put("result", list);
		map.put("content", "table-event-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteFeeling")
	public String deleteFeeling(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		Publiccontent content = this.feelingManager.getPublicContentDao()
				.findById(contentId);

		if (content != null) {
			this.feelingManager.getPublicContentDao().delete(content);
		}
		List list = this.feelingManager.getPublicContentDao().findAll();
		map.put("result", list);
		map.put("content", "table-feeling-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteTask")
	public String deleteTask(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		Task task = this.taskManager.getTaskDao().findById(contentId);

		if (task != null) {
			this.taskManager.getTaskDao().delete(task);
		}
		List list = this.taskManager.getTaskDao().findAll();
		map.put("result", list);
		map.put("content", "table-task-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteAttendance")
	public String deleteAttendance(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		Attendance attendance = this.taskManager.getAttendanceDao().findById(
				contentId);

		if (attendance != null) {
			this.taskManager.getAttendanceDao().delete(attendance);
		}
		List list = this.taskManager.getAttendanceDao().findAll();
		map.put("result", list);
		map.put("content", "table-attendance-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteAnnounce")
	public String deleteAnnounce(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		Announcement announcement = this.systemManager.getNoticeDao().findById(
				contentId);

		if (announcement != null) {
			this.systemManager.getNoticeDao().delete(announcement);
		}
		List list = this.systemManager.getNoticeDao().findAll();
		map.put("result", list);
		map.put("content", "table-announce-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteMessage")
	public String deleteMessage(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		Message message = this.systemManager.getSystemMessageDao().findById(
				contentId);
		if (message != null) {
			this.systemManager.getSystemMessageDao().delete(message);
		}
		List list = this.systemManager.getSystemMessageDao().findAll();
		map.put("result", list);
		map.put("content", "table-message-list");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	/***
	 * 一键导入物业费
	 * 
	 * @param contentId
	 * @param menu
	 * @param submenu
	 * @param sortmenu
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "newSystem/importChargeOrder")
	public String importChargeOrder(
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			ModelMap map) {

		map.put("tip", "订单上传完毕!");
		map.put("content", "importChargeOrder");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/submitImportCharges")
	public String submitImportCharges(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			HttpSession session, ModelMap map) throws FileNotFoundException,
			Exception {

		if (file != null) {
			// 保存图片
			String fileName = String.format(
					AKConfig.STRING_USER_HEADER_PICTURE_FORMAT,
					new Date().getTime());
			String result = FileUtil.savePicture(file, fileName,
					"upload/headerImages/");
			if (!result.equals("")) {
				fileName = AKConfig.STRING_SYSTEM_ADDRESS
						+ "upload/headerImages/" + result;

				String path = session.getServletContext().getRealPath("upload");

				List list = this.serviceManager
						.importBrandPeriodSort(new FileInputStream(path
								+ "/headerImages/" + result));

				map.put("data", list);

			}
		}

		map.put("tip", "订单上传完毕!");
		map.put("content", "importChargeOrder");
		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);
		return AKConfig.NEW_SERVER_CONTROL;
	}

	/***
	 * ---------------------------------------------后端管理系统升级--------------------
	 * -----------------------
	 ***/

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
