package com.akchengtou.mobile.web;

import java.io.IOException;
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

import org.apache.commons.httpclient.HttpException;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.hibernate.type.IdentifierType;
import org.jdom.JDOMException;
import org.json.JSONException;
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
import com.akchengtou.tools.FileUtil;
import com.akchengtou.tools.MessageType;
import com.akchengtou.tools.MsgUtil;
import com.akchengtou.tools.Tools;
import com.akchengtou.web.entity.Authentic;
import com.akchengtou.web.entity.Identiytype;
import com.akchengtou.web.entity.Member;
import com.akchengtou.web.entity.MessageBean;
import com.akchengtou.web.entity.Ordercomment;
import com.akchengtou.web.entity.Orderservice;
import com.akchengtou.web.entity.Orderstatus;
import com.akchengtou.web.entity.Ordertype;
import com.akchengtou.web.entity.Price;
import com.akchengtou.web.entity.Propertycharges;
import com.akchengtou.web.entity.Service;
import com.akchengtou.web.entity.Serviceimages;
import com.akchengtou.web.entity.Servicetype;
import com.akchengtou.web.entity.User;
import com.akchengtou.web.manager.ServiceManager;
import com.akchengtou.web.manager.UserManager;

@Controller
public class ServiceController extends BaseController {

	@Autowired
	private ServiceManager serviceManager;
	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/serviceTypeList")
	@ResponseBody
	/***
	 * 
	 * @param page
	 * @param session
	 * @return
	 */
	public Map serviceTypeList(@RequestParam(value = "page") Integer page,
			HttpSession session) {
		this.result = new HashMap();
		User user = this.findUserInSession(session);

		List list = this.getServiceManager().getServiceTypeDao()
				.findAllByPage(page);

		this.status = 200;
		this.result.put("data", list);
		this.message = "服务列表获取成功!";

		return getResult();
	}

	@RequestMapping(value = "/allServiesList")
	@ResponseBody
	public Map findAllServiceList(@RequestParam(value = "page") Integer page,
			HttpSession session) {
		this.result = new HashMap();
		User user = this.findUserInSession(session);

		List<Orderservice> list = this.getServiceManager().findAllOrders(user, page);
		
		for(Orderservice s : list)
		{
			Service service = s.getService();
			
			if(s.getPrice()!=0)
			{
				service.getServicetype().setPrice((float)s.getPrice());
			}
		}
		
		
		this.status = 200;
		this.result.put("data", list);
		this.message = "服务列表获取成功!";

		return getResult();
	}

	@RequestMapping(value = "/allServiceTypeList")
	@ResponseBody
	public Map allServiceTypeList(@RequestParam(value = "page") Integer page,
			HttpSession session) {
		this.result = new HashMap();

		List list = this.getServiceManager().findAllServiesType(page);

		this.status = 200;
		this.result.put("data", list);
		this.message = "服务列表获取成功!";

		return getResult();
	}

	@RequestMapping(value = "/requestServiceMemberList")
	@ResponseBody
	/***
	 * 员工列表
	 * @param serviceTypeId
	 * @param page
	 * @param session
	 * @return
	 */
	public Map requestServiceMemberList(
			@RequestParam(value = "serviceTypeId") Integer serviceTypeId,
			@RequestParam(value = "page") Integer page, HttpSession session) {
		this.result = new HashMap();

		List list = this.getServiceManager().findMembersByType(serviceTypeId,
				page);

		this.status = 200;
		this.result.put("data", list);
		this.message = "服务列表获取成功!";

		return getResult();
	}

	@RequestMapping(value = "/submitServiceConfirm")
	@ResponseBody
	public Map submitServiceConfirm(
			@RequestParam(value = "serviceTypeId") Integer serviceTypeId,
			@RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "memberId") Integer memberId,
			@RequestParam(value = "serviceTime") String serviceTime,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "images", required = false) MultipartFile[] images,
			HttpSession session) {
		this.result = new HashMap();

		User user = this.userManager.findUserById(userId);
		Member member = this.serviceManager.getMemberDao().findById(memberId);
		member.setMemberId(memberId);

		// 创建服务
		Service service = new Service();
		service.setUser(user);
		// 创建服务类型
		Servicetype serviceType = getServiceManager().getServiceTypeDao()
				.findById(serviceTypeId);

		// 设置订单类型
		service.setServicetype(serviceType);
		// 设置服务员工
		service.setMember(member);
		// 设置预约时间
		service.setServiceDate(new Date());
		/*
		 * try { // date = DateUtils.parseDate(serviceTime);
		 * service.setServiceDate(new Date()); } catch (DateParseException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		// 设置备注
		service.setContent(content);
		// 设置名称
		service.setName(serviceType.getName());

		// 保存
		getServiceManager().getServiceDao().save(service);

		// 保存图片
		Set set = new HashSet();
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
					result = FileUtil.savePicture(file, fileName, "upload/");
					if (!result.equals("")) {
						fileName = AKConfig.STRING_SYSTEM_ADDRESS + "upload/"
								+ result;
						Serviceimages image = new Serviceimages();
						image.setUrl(fileName);
						image.setService(service);
						// 保存
						getServiceManager().getServiceImageDao().save(image);
						set.add(image);
					}

				}
			}
			// 设置状态图片
		}

		// 生成订单
		Orderservice orderService = new Orderservice();
		orderService.setService(service);

		Ordertype orderType = this.getServiceManager().getOrderTypeDao().findById(serviceTypeId);
		orderType.setTypeId(serviceTypeId);

		Orderstatus orderStatus = this.getServiceManager().getOrderStatusDao().findById(1);
		orderStatus.setStatusId(1);

		orderService.setOrdertype(orderType);
		orderService.setOrderCode("jinzht" + (new Date().getTime()));
		orderService.setOrderstatus(orderStatus);
		orderService.setOrderDate(new Date());

		this.getServiceManager().getOrderServiceDao().save(orderService);

		this.status = 200;
		this.result.put("data", orderService);
		this.message = "服务提交成功!";

		return getResult();
	}

	@RequestMapping(value = "/requestOrderPayConfirm")
	@ResponseBody
	/***
	 * 评价确认
	 * @param orderId
	 * @param session
	 * @return
	 */
	public Map requestOrderPayConfirm(
			@RequestParam(value = "orderId") Integer orderId,
			HttpSession session) {
		this.result = new HashMap();
		// 创建订单
		Orderservice order = this.getServiceManager().getOrderServiceDao()
				.findById(orderId);
		if (order != null) {
			// 创建评分
			Orderstatus status = new Orderstatus();
			status.setStatusId(2);
			order.setOrderstatus(status);
			// 保存评分
			this.getServiceManager().getOrderServiceDao().saveOrUpdate(order);

			this.status = 200;
			this.result.put("data", "");
			this.message = "信息提交成功!";

		} else {
			this.status = 400;
			this.result.put("data", "");
			this.message = "信息提交失败!";
		}

		return getResult();
	}

	@RequestMapping(value = "/requestPriseOrder")
	@ResponseBody
	/***
	 * 订单评价
	 * @param orderId
	 * @param score
	 * @param session
	 * @return
	 */
	public Map requestPriseOrder(
			@RequestParam(value = "orderId") Integer orderId,
			@RequestParam(value = "score") float score, HttpSession session) {
		this.result = new HashMap();
		// 创建订单
		Orderservice order = this.getServiceManager().getOrderServiceDao()
				.findById(orderId);
		if (order != null) {
			// 创建评分
			Ordercomment comment = new Ordercomment();
			comment.setCommentDate(new Date());
			comment.setScore(score);
			comment.setOrderservice(order);

			// 保存评分
			this.getServiceManager().getOrderCommentDao().save(comment);

			this.status = 200;
			this.result.put("data", "");
			this.message = "信息提交成功!";

		} else {
			this.status = 400;
			this.result.put("data", "");
			this.message = "信息提交失败!";
		}

		return getResult();
	}

	@RequestMapping(value = "/requestOrderDetail")
	@ResponseBody
	/***
	 * 订单详情
	 * @param orderId
	 * @param session
	 * @return
	 */
	public Map requestOrderDetail(
			@RequestParam(value = "orderId") Integer orderId,
			HttpSession session) {
		this.result = new HashMap();
		// 创建订单
		Orderservice order = getServiceManager().getOrderServiceDao().findById(
				orderId);
		
		Service service = order.getService();
		
		if(order.getPrice()!=0)
		{
			service.getServicetype().setPrice((float)order.getPrice());
		}

		this.status = 200;
		this.result.put("data", order);
		this.message = "服务提交成功!";

		return getResult();
	}

	@RequestMapping(value = "/requestOrderSign")
	@ResponseBody
	/***
	 * 订单详情
	 * @param orderId
	 * @param session
	 * @return
	 */
	public Map requestOrderSign(
			@RequestParam(value = "flag",required=false) boolean flag,
			@RequestParam(value = "orderId") Integer orderId,
			@RequestParam(value = "type") Integer type, HttpSession session)
			throws JSONException, HttpException, IOException, JDOMException {
		this.result = new HashMap();
		Orderservice order = this.getServiceManager().getOrderServiceDao()
				.findById(orderId);
		// 创建订单
		String result = getServiceManager().orderPayDescription(order, "24000",
				type, "1",flag);

		if (result.equals(null) || result.equals("")) {
			this.status = 400;
			this.result.put("data", "");
			this.message = "订单支付信息获取失败!";
		} else {
			this.status = 200;
			this.result.put("data", result);
			this.message = "信息获取成功!";
		}

		return getResult();
	}
	
	
	@RequestMapping(value = "/requestPropertyChargesSign")
	@ResponseBody
	/***
	 * 物业费缴纳
	 * @param orderId
	 * @param session
	 * @return
	 */
	public Map requestPropertyChargesSign(
			@RequestParam(value = "orderId") Integer orderId,
			@RequestParam(value = "type") Integer type, HttpSession session)
					throws JSONException, HttpException, IOException, JDOMException {
		this.result = new HashMap();
		Propertycharges order = this.getServiceManager().getPropertychargesDao()
				.findById(orderId);
		// 创建订单
		String result = getServiceManager().orderPayChargesDescription(order, "24000",
				type, "1");
		
		if (result.equals(null) || result.equals("")) {
			this.status = 400;
			this.result.put("data", "");
			this.message = "订单支付信息获取失败!";
		} else {
			this.status = 200;
			this.result.put("data", result);
			this.message = "信息获取成功!";
		}
		
		return getResult();
	}
	@RequestMapping(value = "/requestPropertyChargesConfirm")
	@ResponseBody
	/***
	 * 确认物业费缴纳
	 * @param orderId
	 * @param session
	 * @return
	 */
	public Map requestPropertyChargesConfirm(
			@RequestParam(value = "orderId") Integer orderId,
			@RequestParam(value = "type") Integer type, HttpSession session)
					throws JSONException, HttpException, IOException, JDOMException {
		this.result = new HashMap();
		Propertycharges order = this.getServiceManager().getPropertychargesDao()
				.findById(orderId);
		// 创建订单
		order.setStatus("2");
		
		this.getServiceManager().getPropertychargesDao().saveOrUpdate(order);
		
		this.status = 200;
		this.result.put("data", "");
		this.message = "信息获取成功!";
		
		return getResult();
	}
	
	@RequestMapping(value = "/requestPrePayInfo")
	@ResponseBody
	/***
	 * 确认物业费缴纳
	 * @param orderId
	 * @param session
	 * @return
	 */
	public Map requestPrePayInfo(
			@RequestParam(value = "type") Integer type, HttpSession session)
					throws JSONException, HttpException, IOException, JDOMException {
		this.result = new HashMap();
		
		List list = this.getServiceManager().findMembersByType(7,
				0);
		List l = this.getServiceManager().getPriceDao().findAll();
		
		
		Map map = new HashMap();
		map.put("member", list.get(0));
		map.put("content", "请于2016-12-17日将缴费卡提交至物业服务中心");
		map.put("prices", l);
		
		
		this.status = 200;
		this.result.put("data", map);
		this.message = "信息获取成功!";
		
		return getResult();
	}
	
	
	@RequestMapping(value="findUserPropertyCharges")
	@ResponseBody
	public Map findUserPropertyCharges(
			@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="page",required=false)Integer page,
			HttpSession session
			) {
		this.result = new HashMap();
		
		//获取用户
		User user = this.findUserInSession(session);
		if(user==null)
		{
			user = this.userManager.findUserById(userId);
		}
		
//		List list = this.serviceManager.findUserPropertyChargesOrders(user, page);
		List list = this.serviceManager.getPropertychargesDao().findAll();
		
		//配置
		this.status=200;
		this.result.put("data", list);
		return getResult();
	}
	
	@RequestMapping(value = "/submitPrePayConfirm")
	@ResponseBody
	public Map submitPrePayConfirm(
			@RequestParam(value = "serviceTypeId") Integer serviceTypeId,
			@RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "memberId") Integer memberId,
			@RequestParam(value = "priceId") Integer priceId,
			HttpSession session) {
		this.result = new HashMap();

		User user = this.userManager.findUserById(userId);
		// 创建订单类型
		Ordertype type =this.getServiceManager().getOrderTypeDao().findById(serviceTypeId);
		
		// 创建员工
		Member member = this.serviceManager.getMemberDao().findById(memberId);
		member.setMemberId(memberId);

		// 创建服务
		Service service = new Service();
		service.setUser(user);
		// 创建服务类型
		Servicetype serviceType = getServiceManager().getServiceTypeDao()
				.findById(serviceTypeId);

		// 设置订单类型
		service.setServicetype(serviceType);
		// 设置服务员工
		service.setMember(member);
		// 设置预约时间
		service.setServiceDate(new Date());
		/*
		 * try { // date = DateUtils.parseDate(serviceTime);
		 * service.setServiceDate(new Date()); } catch (DateParseException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		// 设置备注
		service.setContent("");
		// 设置名称
		service.setName(serviceType.getName());

		// 保存
		getServiceManager().getServiceDao().save(service);

		// 生成订单
		Orderservice orderService = new Orderservice();
		orderService.setService(service);


		Orderstatus orderStatus = this.getServiceManager().getOrderStatusDao().findById(1);
		orderService.setOrdertype(type);
		orderService.setOrderCode("jinzht" + (new Date().getTime()));
		orderService.setOrderstatus(orderStatus);
		orderService.setOrderDate(new Date());
		
		//价格
		Price price = this.getServiceManager().getPriceDao().findById(priceId);
	    
		orderService.setPrice(price.getPrice());

		this.getServiceManager().getOrderServiceDao().save(orderService);

		this.status = 200;
		this.result.put("data", orderService);
		this.message = "服务提交成功!";

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
			user = this.getUserManager().findUserById(userId);
		}

		return user;
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}
