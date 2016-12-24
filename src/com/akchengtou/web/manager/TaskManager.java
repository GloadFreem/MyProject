package com.akchengtou.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.akchengtou.tools.DateUtils;
import com.akchengtou.web.entity.Attendance;
import com.akchengtou.web.entity.AttendanceDAO;
import com.akchengtou.web.entity.AttendancerecordDAO;
import com.akchengtou.web.entity.EventDAO;
import com.akchengtou.web.entity.EventimagesDAO;
import com.akchengtou.web.entity.TaskDAO;
import com.akchengtou.web.entity.User;

public class TaskManager {
	private TaskDAO taskDao;
	private AttendanceDAO attendanceDao;
	private AttendancerecordDAO attendanceRecordDao;
	private EventDAO eventDao;
	private EventimagesDAO eventImagesDao;

	/***
	 * 根据用户获取任务列表
	 * 
	 * @param user
	 * @param page
	 * @return
	 */
	public List findUserTask(User user, Integer page) {
		List list = new ArrayList();
		if (user != null && page != null) {
			list = getTaskDao().findByUser(user, page, 10);
		}
		return list;
	}

	/***
	 * 根据用户当天获取任务列表
	 * 
	 * @param user
	 * @param page
	 * @return
	 */
	public List findUserDayTask(User user) {
		List list = new ArrayList();
		if (user != null) {
			String dateString = DateUtil.formatDate(new Date(),"yyyy-MM-dd");
			dateString+=" 00:00:01";
			Date date;
			try {
				date = DateUtils.stringToDate(dateString, "yyyy-MM-dd HH:mm:ss");
				list = getTaskDao().findByDate(date);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	/***
	 * 根据用户及taskId查询
	 * 
	 * @param user
	 * @param page
	 * @return
	 */
	public List findUserDayTaskById(User user,Integer taskId) {
		List list = new ArrayList();
		if (user != null) {
			String dateString = DateUtil.formatDate(new Date(),"yyyy-MM-dd");
			dateString+=" 00:00:01";
			Date date;
			try {
				date = DateUtils.stringToDate(dateString, "yyyy-MM-dd HH:mm:ss");
				list = getTaskDao().findByTaskIdAndUser(user, taskId, date);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 根据考勤获取考勤记录
	 * @param attendance
	 * @return
	 */
	public List findHasAttendanced(User user,Attendance attendance)
	{
		List list = new ArrayList();
		if (user != null) {
			String dateString = DateUtil.formatDate(new Date(),"yyyy-MM-dd");
			dateString+=" 00:00:01";
			Date date;
			try {
				date = DateUtils.stringToDate(dateString, "yyyy-MM-dd HH:mm:ss");
				list = getAttendanceRecordDao().findByRecordIdAndUser(user, attendance.getAttendId(), date);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @return the taskDao
	 */
	public TaskDAO getTaskDao() {
		return taskDao;
	}

	/**
	 * @param taskDao
	 *            the taskDao to set
	 */
	@Autowired
	public void setTaskDao(TaskDAO taskDao) {
		this.taskDao = taskDao;
	}

	/**
	 * @return the attendanceDao
	 */
	public AttendanceDAO getAttendanceDao() {
		return attendanceDao;
	}

	/**
	 * @param attendanceDao the attendanceDao to set
	 */
	@Autowired
	public void setAttendanceDao(AttendanceDAO attendanceDao) {
		this.attendanceDao = attendanceDao;
	}

	/**
	 * @return the attendanceRecordDao
	 */
	public AttendancerecordDAO getAttendanceRecordDao() {
		return attendanceRecordDao;
	}

	/**
	 * @param attendanceRecordDao the attendanceRecordDao to set
	 */
	@Autowired
	public void setAttendanceRecordDao(AttendancerecordDAO attendanceRecordDao) {
		this.attendanceRecordDao = attendanceRecordDao;
	}

	/**
	 * @return the eventDao
	 */
	public EventDAO getEventDao() {
		return eventDao;
	}

	/**
	 * @param eventDao the eventDao to set
	 */
	
	@Autowired
	public void setEventDao(EventDAO eventDao) {
		this.eventDao = eventDao;
	}

	/**
	 * @return the eventImagesDao
	 */
	public EventimagesDAO getEventImagesDao() {
		return eventImagesDao;
	}

	/**
	 * @param eventImagesDao the eventImagesDao to set
	 */
	
	@Autowired
	public void setEventImagesDao(EventimagesDAO eventImagesDao) {
		this.eventImagesDao = eventImagesDao;
	}

}
