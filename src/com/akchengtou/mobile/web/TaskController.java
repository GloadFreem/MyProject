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

import com.akchengtou.tools.AKConfig;
import com.akchengtou.tools.DateUtils;
import com.akchengtou.tools.FileUtil;
import com.akchengtou.tools.MatrixToImageWriter;
import com.akchengtou.tools.MessageType;
import com.akchengtou.tools.MsgUtil;
import com.akchengtou.tools.Tools;
import com.akchengtou.web.entity.Attendance;
import com.akchengtou.web.entity.Attendancerecord;
import com.akchengtou.web.entity.Authentic;
import com.akchengtou.web.entity.Event;
import com.akchengtou.web.entity.Eventimages;
import com.akchengtou.web.entity.Identiytype;
import com.akchengtou.web.entity.MessageBean;
import com.akchengtou.web.entity.Publiccontentimages;
import com.akchengtou.web.entity.Task;
import com.akchengtou.web.entity.User;
import com.akchengtou.web.manager.AuthenticManager;
import com.akchengtou.web.manager.TaskManager;
import com.akchengtou.web.manager.UserManager;

@Controller
public class TaskController extends BaseController {

	@Autowired
	private UserManager userManger;
	@Autowired
	private TaskManager taskMananager;

	@RequestMapping("/requestUserTaskList")
	@ResponseBody
	/***
	 * 任务列表
	 * @return Map 返回值
	 */
	public Map requestUserTaskList(@RequestParam(value = "page") Integer page,
			HttpSession session) {
		this.result = new HashMap();
		User user = this.findUserInSession(session);
		List list = new ArrayList();
		if (user != null) {
			list = this.taskMananager.findUserTask(user, page);
		}
		this.status = 200;
		this.result.put("data", list);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestSubmitask")
	@ResponseBody
	/***
	 * 任务列表
	 * @return Map 返回值
	 */
	public Map requestSubmitask(
			@RequestParam(value = "taskTime") String taskTime,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "employerId") Integer employerId,
			HttpSession session) {
		this.result = new HashMap();

		Date date;
		try {
			date = DateUtils.stringToDate(taskTime, "yyyy-MM-dd HH:mm:ss");
			User user = this.getUserManger().findUserById(employerId);
			List list = new ArrayList();
			if (user != null) {
				Task task = new Task();
				task.setContent(content);
				task.setTaskDate(date);
				task.setUser(user);

				// 保存
				this.taskMananager.getTaskDao().save(task);
			}
			this.status = 200;
			this.result.put("data", "");
			this.message = "任务指派成功！";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getResult();
	}

	@RequestMapping("/newSystem/genErCode")
	public void gen(String url, HttpServletResponse response, Integer width,
			Integer height) {

		try {

			int iWidth = (width == null ? 200 : width);
			int iHeight = (height == null ? 200 : height);

			MatrixToImageWriter.createRqCode(url, iWidth, iHeight,
					response.getOutputStream());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@RequestMapping("/requestCompleteTask")
	@ResponseBody
	/***
	 * 打卡
	 * @return Map 返回值
	 */
	public Map requestCompleteTask(
			@RequestParam(value = "taskId",required=false) Integer taskId, HttpSession session) {
		this.result = new HashMap();

		User user = this.findUserInSession(session);
		if (user != null) {
			List<Task> tasks = this.taskMananager.findUserDayTaskById(user,
					taskId);
			if (tasks != null && tasks.size() > 0) {
				Task task = tasks.get(0);

				// 设置已完成
				task.setComplete(true);
				// 保存
				this.taskMananager.getTaskDao().saveOrUpdate(task);
				this.status = 200;
				this.result.put("data", "");
				this.message = "完成打卡！";

				return getResult();
			}

		}

		this.status = 400;
		this.result.put("data", "");
		this.message = "任务不属于该用户";

		return getResult();
	}

	@RequestMapping("/requestUserTodayTaskList")
	@ResponseBody
	/***
	 * 当日任务列表
	 * @return Map 返回值
	 */
	public Map requestUserTodayTaskList(
			@RequestParam(value = "page") Integer page, HttpSession session) {
		this.result = new HashMap();
		User user = this.findUserInSession(session);
		List list = new ArrayList();
		if (user != null) {
			list = this.taskMananager.findUserDayTask(user);
		}
		this.status = 200;
		this.result.put("data", list);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestEventListByType")
	@ResponseBody
	/***
	 * 当日任务列表
	 * @return Map 返回值
	 */
	public Map requestEventListByType(
			@RequestParam(value = "page") Integer page,
			@RequestParam(value = "type") Integer type, HttpSession session) {
		this.result = new HashMap();
		List list = this.taskMananager.getEventDao().findAll();
		if (list == null) {
			list = new ArrayList();
		}
		this.status = 200;
		this.result.put("data", list);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestUserAttendWork")
	@ResponseBody
	/***
	 * 员工考勤
	 * @return Map 返回值
	 */
	public Map requestUserAttendWork(HttpSession session) {
		this.result = new HashMap();
		User user = this.findUserInSession(session);
		if (user != null) {
			List<Attendance> attendances = this.taskMananager
					.getAttendanceDao().findByProperty("user", user);
			if (attendances != null && attendances.size() > 0) {
				Attendance attendance = attendances.get(0);
				if (attendance != null) {
					// 查看用户是否已经执行
					List list = this.taskMananager
							.findHasAttendanced(user,attendance);
					if (list != null && list.size() > 0) {
						this.status = 400;
						this.message = "您已执行该任务，无需重复执行";

						return getResult();
					}

					Attendancerecord record = new Attendancerecord();
					record.setAttendance(attendance);
					record.setAttendDate(new Date());
					record.setUser(user);

					// 保存
					this.taskMananager.getAttendanceRecordDao().save(record);
					
					this.status = 200;
					this.result.put("data", "");
					this.message = "信息提交成功!";
					
					return getResult();
				}
			} else {

			}

		}
		this.status = 400;
		this.result.put("data", "");
		this.message = "暂时无法进行打卡!";

		return getResult();
	}

	@RequestMapping("/requestAttendanceList")
	@ResponseBody
	/***
	 * 员工考勤列表
	 * @param page
	 * @param session
	 * @return
	 */
	public Map requestAttendanceList(
			@RequestParam(value = "page") Integer page, HttpSession session) {
		this.result = new HashMap();

		List list = this.taskMananager.getAttendanceRecordDao().findAll();

		List results = new ArrayList();
		Map map = null;
		for (int i = 0; i < list.size(); i++) {
			map = new HashMap();
			Attendancerecord record = (Attendancerecord) list.get(i);
			Integer userId = record.getUser().getUserId();
			User user = this.getUserManger().findUserById(userId);
			map.put("user", user);
			map.put("ateendance", record);

			results.add(map);
		}

		this.status = 200;
		this.result.put("data", results);
		this.message = "信息提交成功!";

		return getResult();
	}

	@RequestMapping("/requestSubmitEvent")
	@ResponseBody
	/***
	 * 上报事件
	 * @return Map 返回值
	 */
	public Map requestSubmitEvent(
			@RequestParam(value = "images", required = false) MultipartFile[] images,
			@RequestParam(value = "content") String content, HttpSession session) {
		this.result = new HashMap();
		User user = this.findUserInSession(session);
		if (user != null) {
			Event event = new Event();
			event.setUser(user);
			event.setContent(content);
			// 保存
			this.taskMananager.getEventDao().save(event);

			// 保存图片
			String fileName = "";
			String result = "";
			if (images != null && images.length > 0) {

				MultipartFile file = null;
				for (int i = 0; i < images.length; i++) {
					if (images[i] != null) {
						file = images[i];
						fileName = String.format(
								AKConfig.STRING_USER_FEELING_PICTUREA_FORMAT,
								new Date().getTime(), i);
						result = FileUtil.savePicture(file, fileName,
								"upload/feelingImages/");
						if (!result.equals("")) {
							fileName = AKConfig.STRING_SYSTEM_ADDRESS
									+ "upload/feelingImages/" + result;

							Eventimages image = new Eventimages();
							image.setEvent(event);
							image.setUrl(fileName);

							this.taskMananager.getEventImagesDao().save(image);

						}

					}
				}
				// 设置状态图片
			}

		}
		this.status = 200;
		this.result.put("data", "");
		this.message = "信息提交成功!";

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
