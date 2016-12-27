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
import com.akchengtou.web.entity.Propertycharges;
import com.akchengtou.web.entity.Publiccontent;
import com.akchengtou.web.entity.Servicetype;
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

		List list = this.userManger.getUserDao().findAll();
		map.put("result", list);
		map.put("content", "table-user-list");

		map.put("menu", menu);
		map.put("sortmenu", sortmenu);
		map.put("submenu", submenu);

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

		List list = this.userManger.getAuthenticDao().findAll();
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

		List list = this.userManger.getMemberDao().findAll();
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
		if (type == null) {
			type = 1;
		}
		Authentic authentic = new Authentic();

		Identity identity = new Identity();
		identity.setIdentiyTypeId(type);

		authentic.setIdentity(identity);

		// 根据用户身份类型获取排行榜
		List list = this.authenticManager.findRankingByIdentitype(authentic
				.getIdentity(),page);

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

		List list = this.serviceManager.getServiceTypeDao().findAll();
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

		List list = this.serviceManager.getPropertychargesDao().findAll();
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

	@RequestMapping(value = "newSystem/deleteUser")
	public String deleteUser(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,

			ModelMap map) {

		User user = this.userManger.getUserDao().findById(contentId);

		this.userManger.getUserDao().delete(user);

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

		this.userManger.getAuthenticDao().delete(authentic);

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

		this.userManger.getMemberDao().delete(member);

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

		this.serviceManager.getOrderServiceDao().delete(service);

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

		this.serviceManager.getPropertychargesDao().delete(charge);

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

		this.serviceManager.getServiceTypeDao().delete(type);

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

		this.serviceManager.getEventDao().delete(event);

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

		this.feelingManager.getPublicContentDao().delete(content);

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

		this.taskManager.getTaskDao().delete(task);

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

		this.taskManager.getAttendanceDao().delete(attendance);

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
		
		this.systemManager.getNoticeDao().delete(announcement);
		
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
		
		this.systemManager.getSystemMessageDao().delete(message);
		
		List list = this.systemManager.getSystemMessageDao().findAll();
		map.put("result", list);
		map.put("content", "table-message-list");
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
