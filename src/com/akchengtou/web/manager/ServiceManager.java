package com.akchengtou.web.manager;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;
import org.jdom.JDOMException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.akchengtou.tools.AKConfig;
import com.akchengtou.tools.DateUtils;
import com.akchengtou.web.entity.EventDAO;
import com.akchengtou.web.entity.MemberDAO;
import com.akchengtou.web.entity.OrdercommentDAO;
import com.akchengtou.web.entity.Orderservice;
import com.akchengtou.web.entity.OrderserviceDAO;
import com.akchengtou.web.entity.OrderstatusDAO;
import com.akchengtou.web.entity.OrdertypeDAO;
import com.akchengtou.web.entity.PriceDAO;
import com.akchengtou.web.entity.Propertycharges;
import com.akchengtou.web.entity.PropertychargesDAO;
import com.akchengtou.web.entity.Service;
import com.akchengtou.web.entity.ServiceDAO;
import com.akchengtou.web.entity.ServiceimagesDAO;
import com.akchengtou.web.entity.Servicetype;
import com.akchengtou.web.entity.ServicetypeDAO;
import com.akchengtou.web.entity.User;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpay.util.CommonUtil;
import com.tenpay.util.WXUtil;
import com.tenpay.util.XMLUtil;
import com.weini.alipay.config.AlipayConfig;
import com.weini.alipay.sign.RSA;
import com.weini.alipay.util.AlipayCore;
import com.weini.alipay.util.MD5Util;

public class ServiceManager {

	private ServiceDAO serviceDao;
	private OrdertypeDAO orderTypeDao;
	private ServicetypeDAO serviceTypeDao;
	private MemberDAO memberDao;
	private ServiceimagesDAO serviceImageDao;
	private OrderserviceDAO orderServiceDao;
	private OrdercommentDAO orderCommentDao;
	private PropertychargesDAO propertychargesDao;
	private PriceDAO priceDao;
	private OrderstatusDAO orderStatusDao;
	private EventDAO eventDao;

	/***
	 * 获取分页服务列表
	 * 
	 * @param page
	 * @return
	 */
	public List findUserOrder(User user, Integer page) {
		List list = null;

		list = getServiceDao().findAll();
		return list;
	}

	public List findAllServies(Integer page) {
		List list = null;

		list = getServiceDao().findAllByPage(page);
		return list;
	}

	public List findAllOrders(User user, Integer page) {
		List list = new ArrayList();
		List<Service> services = this.getServiceDao().findByPropertyPage("user",
				user,page);
		if (services != null && services.size() > 0) {
			for (int i = 0; i < services.size(); i++) {
				Service service = services.get(i);
				List<Orderservice> l = getOrderServiceDao().findAllByUserPage(
						service, page);

				for (Orderservice s : l) {
					list.add(s);
				}
			}
		}
		return list;
	}

	public List findUserPropertyChargesOrders(User user, Integer page) {
		List<Propertycharges> services = this.getPropertychargesDao()
				.findByUser(user, page);
		return services;
	}

	public List findAllServiesType(Integer page) {
		List list = null;

		list = getServiceDao().findAllByPage(page);
		return list;
	}

	public List findMembersByType(Integer serviceTypeId, Integer page) {
		List list = null;
		if (serviceTypeId != null) {
			Servicetype type = this.getServiceTypeDao().findById(serviceTypeId);
			list = getMemberDao().findByType(type, page);
		}
		return list;
	}

	/**
	 * 支付组装接口
	 * 
	 * @param TOrder
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 * @throws HttpException
	 * @throws JDOMException
	 */
	public String orderPayDescription(Orderservice order, String timeLetf,
			int payType, String osType, boolean flag) throws JSONException,
			HttpException, IOException, JDOMException {

		// 日志测试Log4
		// ================支付宝支付业务开始=============================
		Map<String, String> map = new HashMap();
		String retmsg = "";
		// 返回字符串
		String orderDesc = "";
		if (payType == 1) {

			// 创建传参
			JSONObject jsonObject = new JSONObject();

			// 支付宝支付参数封装
			String service_url = AKConfig.STRING_SYSTEM_ADDRESS
					+ AlipayConfig.notify_url;
			jsonObject.put(AlipayConfig.seller_id_str, AlipayConfig.partner);// 卖家支付宝账号
			jsonObject.put(AlipayConfig.out_trade_no_str, order.getOrderCode()); // 商家订单编号
			jsonObject.put(AlipayConfig.subject_str, AlipayConfig.subject); // 商品名称
			jsonObject.put(AlipayConfig.body_str, AlipayConfig.body); // 商品描述
			jsonObject.put("product_code", "QUICK_MSECURITY_PAY"); // 商品描述
			jsonObject.put("goods_type", "1"); // 商品描述

			if (flag) {
				jsonObject.put(AlipayConfig.total_fee_str,
						String.valueOf(order.getPrice())); // 商品价格
			} else {
				jsonObject.put(
						AlipayConfig.total_fee_str,
						String.valueOf(order.getService().getServicetype()
								.getPrice())); // 商品价格
			}

			// 转换格式
			ObjectMapper mapper = new ObjectMapper();

			String json = mapper.writeValueAsString(jsonObject);
			// json = URLEncoder.encode(json, "utf-8");

			map.put("biz_content", json); // 商品描述
			map.put("timestamp",
					DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss")); // 商品描述
			map.put("format", "json"); // 商品描述
			map.put("app_id", AlipayConfig.app_id); // 商品描述
			map.put("method", "alipay.trade.app.pay"); // 商品描述
			map.put("version", "1.0"); // 商品描述
			map.put(AlipayConfig.notify_url_str, service_url);// 回调链接
			map.put(AlipayConfig._input_charset_str, AlipayConfig.input_charset); // 参数编码字符集
			map.put(AlipayConfig.sign_type_str, AlipayConfig.sign_type); // 签名类型

			// ----------------------------开始签名--------------------------------
			// 开始签名
			// String orderSign = AlipayCore.createLinkString(map);
			// String sign = RSA.sign(orderSign, AlipayConfig.private_key,
			// AlipayConfig.input_charset);
			//

			String sign;
			try {
				sign = AlipaySignature.rsaSign(map, AlipayConfig.private_key,
						"utf-8");
				sign = URLEncoder.encode(sign);

				// sign = sign.replace("+", "%2B");
				// sign = sign.replace("/", "%2F");
				// sign = sign.replace("=", "");

				// ----------------------------签名结束--------------------------------
				// /* Map checkMap = map;
				// checkMap.put("sign", sign);
				// if(AlipaySignature.rsaCheckV2(checkMap,
				// AlipayConfig.ali_public_key, "utf-8"))
				// {
				// System.out.println("验签成功！");
				// }*/
				// ----------------------------签名验证结束--------------------------------

				// ----------------------------重新组装参数--------------------------------
				map.put(AlipayConfig.sign_str, sign);
				// ----------------------------重新组装参数结束--------------------------------
				// ================支付宝支付业务结束=============================
				orderDesc = AlipayCore.createLinkString(map);
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (payType == 2) {
			System.out.print("微信支付业务开始");
			// ================微信支付业务开始=============================
			// 设置package订单参数
			// 生成随即唯一标识
			String noncestr = WXUtil.getNonceStr();

			// 设置获取prepayid支付参数
			// ---------------获取订单号 开始------------------------
			String out_trade_no = order.getOrderCode();
			// ---------------获取订单号 结束------------------------

			// ---------------设置订单预支付参数开始------------------------
			float price;
			if (flag) {
				// 价格
				price = order.getPrice();
			} else {
				price = order.getService().getServicetype().getPrice();
			}

			String priceStr = String.valueOf(price * 100);

			priceStr = priceStr.replace(".0", "");

			// 请求参数封装
			if (osType.equalsIgnoreCase("0")) {
				map.put(AlipayConfig.APP_ID_STR, AlipayConfig.APP_ID_ANDROID);
				map.put(AlipayConfig.MCH_ID_STR, AlipayConfig.MCH_ID_ANDROID);
			} else {
				map.put(AlipayConfig.APP_ID_STR, AlipayConfig.APP_ID);
				map.put(AlipayConfig.MCH_ID_STR, AlipayConfig.MCH_ID);
			}
			String service_url = AKConfig.STRING_SYSTEM_ADDRESS
					+ AlipayConfig.notify_wx_url;
			map.put(AlipayConfig.NONCE_STR, noncestr);
			map.put(AlipayConfig.NOTIFY_URL_STR, service_url);
			map.put(AlipayConfig.OUT_TRADE_NO_STR, out_trade_no);
			map.put(AlipayConfig.SPBILL_CREATE_IP_STR, AlipayConfig.SERVER_IP);
			map.put("total_fee", priceStr);
			map.put(AlipayConfig.TRADE_TYPE_STR, "APP");

			if (order.getService().getContent().equals("")) {
				order.getService().setContent("城投逸园");
			}
			map.put(AlipayConfig.BODY_STR, new String(order.getService()
					.getContent()));
			// ---------------设置订单预支付参数结束------------------------

			// ---------------生成加密字符串（MD5）开始------------------------
			// 生成MD5签名字符串
			String orderSign = AlipayCore.createWXLinkString(map);
			orderSign += "&key=" + AlipayConfig.PARTNER_ID;
			// MD5签名
			// String sign = MD5Util.MD5(orderSign);
			String sign = MD5Util.MD5Encode(orderSign, "UTF-8").toUpperCase();
			// ---------------生成加密字符串（MD5）结束------------------------

			// ---------------生成预支付请求xml格式字符串------------------------
			String xmlStr = XMLUtil.getXMLWithMap(map, sign);
			// ---------------生成预支付请求xml格式字符串结束------------------------

			// ---------------请求业务逻辑获取预支付------------------------
			// String responseString = new
			// TenpayHttpClient().getPrepayId(xmlStr);
			String responseString = CommonUtil.httpsRequest(
					AlipayConfig.API_CHECK, "POST", xmlStr);
			// 解析服务器返回的xml格式字符串
			String prepayid = XMLUtil.getPrepayIdWithXML(responseString);

			// 返回给客户端的参数
			if (null != prepayid && !"".equals(prepayid)) {
				// 获取 prepayid之后进行第二次签名
				String package_str;
				// 设置支付参数
				// 设置package
				package_str = "Sign=WXPay";
				// 第二次签名参数列表
				map = new HashMap();
				String timestamp = WXUtil.getTimeStamp();
				noncestr = MD5Util.MD5(timestamp);
				if (osType.equalsIgnoreCase("0")) {
					map.put(AlipayConfig.APP_ID_STR,
							AlipayConfig.APP_ID_ANDROID);
				} else {
					map.put(AlipayConfig.APP_ID_STR, AlipayConfig.APP_ID);
				}
				map.put("package", package_str);
				map.put("partnerid", AlipayConfig.MCH_ID);
				map.put("timestamp", timestamp);
				map.put("prepayid", prepayid);
				map.put("noncestr", noncestr);
				String signStr = AlipayCore.createWXLinkString(map);
				signStr += "&key=" + AlipayConfig.PARTNER_ID;
				// 生成签名
				String signSecond = MD5Util.MD5(signStr);
				// 加入参数
				map.put("sign", signSecond);
				orderDesc = AlipayCore.createLinkString(map);
			} else {
				map = null;
				retmsg = "错误：获取prepayId失败";
			}
			// ================微信支付业务结束=============================

		}

		return orderDesc;
	}

	/**
	 * 支付组装接口
	 * 
	 * @param TOrder
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 * @throws HttpException
	 * @throws JDOMException
	 */
	public String orderPayChargesDescription(Propertycharges order,
			String timeLetf, int payType, String osType) throws JSONException,
			HttpException, IOException, JDOMException {

		// 日志测试Log4
		// ================支付宝支付业务开始=============================
		Map<String, String> map = new HashMap();
		String retmsg = "";
		// 返回字符串
		String orderDesc = "";
		if (payType == 1) {

			// 创建传参
			JSONObject jsonObject = new JSONObject();

			// 支付宝支付参数封装
			String service_url = AKConfig.STRING_SYSTEM_ADDRESS
					+ AlipayConfig.notify_url;
			jsonObject.put(AlipayConfig.seller_id_str, AlipayConfig.partner);// 卖家支付宝账号
			jsonObject.put(AlipayConfig.out_trade_no_str,
					"akct_" + order.getChargeId()); // 商家订单编号
			jsonObject.put(AlipayConfig.subject_str, AlipayConfig.subject); // 商品名称
			jsonObject.put(AlipayConfig.body_str, AlipayConfig.body); // 商品描述
			jsonObject.put("product_code", "QUICK_MSECURITY_PAY"); // 商品描述
			jsonObject.put("goods_type", "1"); // 商品描述
			jsonObject.put(AlipayConfig.total_fee_str,
					String.valueOf(order.getPrice())); // 商品价格

			// 转换格式
			ObjectMapper mapper = new ObjectMapper();

			String json = mapper.writeValueAsString(jsonObject);
			// json = URLEncoder.encode(json, "utf-8");

			map.put("biz_content", json); // 商品描述
			map.put("timestamp",
					DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss")); // 商品描述
			map.put("format", "json"); // 商品描述
			map.put("app_id", AlipayConfig.app_id); // 商品描述
			map.put("method", "alipay.trade.app.pay"); // 商品描述
			map.put("version", "1.0"); // 商品描述
			map.put(AlipayConfig.notify_url_str, service_url);// 回调链接
			map.put(AlipayConfig._input_charset_str, AlipayConfig.input_charset); // 参数编码字符集
			map.put(AlipayConfig.sign_type_str, AlipayConfig.sign_type); // 签名类型

			// ----------------------------开始签名--------------------------------
			// 开始签名
			// String orderSign = AlipayCore.createLinkString(map);
			// String sign = RSA.sign(orderSign, AlipayConfig.private_key,
			// AlipayConfig.input_charset);
			//

			String sign;
			try {
				sign = AlipaySignature.rsaSign(map, AlipayConfig.private_key,
						"utf-8");
				sign = URLEncoder.encode(sign);

				// sign = sign.replace("+", "%2B");
				// sign = sign.replace("/", "%2F");
				// sign = sign.replace("=", "");

				// ----------------------------签名结束--------------------------------
				// /* Map checkMap = map;
				// checkMap.put("sign", sign);
				// if(AlipaySignature.rsaCheckV2(checkMap,
				// AlipayConfig.ali_public_key, "utf-8"))
				// {
				// System.out.println("验签成功！");
				// }*/
				// ----------------------------签名验证结束--------------------------------

				// ----------------------------重新组装参数--------------------------------
				map.put(AlipayConfig.sign_str, sign);
				// ----------------------------重新组装参数结束--------------------------------
				// ================支付宝支付业务结束=============================
				orderDesc = AlipayCore.createLinkString(map);
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (payType == 2) {
			System.out.print("微信支付业务开始");
			// ================微信支付业务开始=============================
			// 设置package订单参数
			// 生成随即唯一标识
			String noncestr = WXUtil.getNonceStr();

			// 设置获取prepayid支付参数
			// ---------------获取订单号 开始------------------------
			String out_trade_no = "akct_" + order.getChargeId();
			// ---------------获取订单号 结束------------------------

			// ---------------设置订单预支付参数开始------------------------
			// 价格
			Double price = order.getPrice();
			String priceStr = String.valueOf(price * 100);

			priceStr = priceStr.replace(".0", "");

			// 请求参数封装
			if (osType.equalsIgnoreCase("0")) {
				map.put(AlipayConfig.APP_ID_STR, AlipayConfig.APP_ID_ANDROID);
				map.put(AlipayConfig.MCH_ID_STR, AlipayConfig.MCH_ID_ANDROID);
			} else {
				map.put(AlipayConfig.APP_ID_STR, AlipayConfig.APP_ID);
				map.put(AlipayConfig.MCH_ID_STR, AlipayConfig.MCH_ID);
			}
			String service_url = AKConfig.STRING_SYSTEM_ADDRESS
					+ AlipayConfig.notify_wx_url;
			map.put(AlipayConfig.NONCE_STR, noncestr);
			map.put(AlipayConfig.NOTIFY_URL_STR, service_url);
			map.put(AlipayConfig.OUT_TRADE_NO_STR, out_trade_no);
			map.put(AlipayConfig.SPBILL_CREATE_IP_STR, AlipayConfig.SERVER_IP);
			map.put("total_fee", priceStr);
			map.put(AlipayConfig.TRADE_TYPE_STR, "APP");

			map.put(AlipayConfig.BODY_STR, new String(order.getName()));
			// ---------------设置订单预支付参数结束------------------------

			// ---------------生成加密字符串（MD5）开始------------------------
			// 生成MD5签名字符串
			String orderSign = AlipayCore.createWXLinkString(map);
			orderSign += "&key=" + AlipayConfig.PARTNER_ID;
			// MD5签名
			// String sign = MD5Util.MD5(orderSign);
			String sign = MD5Util.MD5Encode(orderSign, "UTF-8").toUpperCase();
			// ---------------生成加密字符串（MD5）结束------------------------

			// ---------------生成预支付请求xml格式字符串------------------------
			String xmlStr = XMLUtil.getXMLWithMap(map, sign);
			// ---------------生成预支付请求xml格式字符串结束------------------------

			// ---------------请求业务逻辑获取预支付------------------------
			// String responseString = new
			// TenpayHttpClient().getPrepayId(xmlStr);
			String responseString = CommonUtil.httpsRequest(
					AlipayConfig.API_CHECK, "POST", xmlStr);
			// 解析服务器返回的xml格式字符串
			String prepayid = XMLUtil.getPrepayIdWithXML(responseString);

			// 返回给客户端的参数
			if (null != prepayid && !"".equals(prepayid)) {
				// 获取 prepayid之后进行第二次签名
				String package_str;
				// 设置支付参数
				// 设置package
				package_str = "Sign=WXPay";
				// 第二次签名参数列表
				map = new HashMap();
				String timestamp = WXUtil.getTimeStamp();
				noncestr = MD5Util.MD5(timestamp);
				if (osType.equalsIgnoreCase("0")) {
					map.put(AlipayConfig.APP_ID_STR,
							AlipayConfig.APP_ID_ANDROID);
				} else {
					map.put(AlipayConfig.APP_ID_STR, AlipayConfig.APP_ID);
				}
				map.put("package", package_str);
				map.put("partnerid", AlipayConfig.MCH_ID);
				map.put("timestamp", timestamp);
				map.put("prepayid", prepayid);
				map.put("noncestr", noncestr);
				String signStr = AlipayCore.createWXLinkString(map);
				signStr += "&key=" + AlipayConfig.PARTNER_ID;
				// 生成签名
				String signSecond = MD5Util.MD5(signStr);
				// 加入参数
				map.put("sign", signSecond);
				orderDesc = AlipayCore.createLinkString(map);
			} else {
				map = null;
				retmsg = "错误：获取prepayId失败";
			}
			// ================微信支付业务结束=============================

		}

		return orderDesc;
	}

	public ServiceDAO getServiceDao() {
		return serviceDao;
	}

	@Autowired
	public void setServiceDao(ServiceDAO serviceDao) {
		this.serviceDao = serviceDao;
	}

	public OrdertypeDAO getOrderTypeDao() {
		return orderTypeDao;
	}

	@Autowired
	public void setOrderTypeDao(OrdertypeDAO orderTypeDao) {
		this.orderTypeDao = orderTypeDao;
	}

	public ServicetypeDAO getServiceTypeDao() {
		return serviceTypeDao;
	}

	@Autowired
	public void setServiceTypeDao(ServicetypeDAO serviceTypeDao) {
		this.serviceTypeDao = serviceTypeDao;
	}

	public MemberDAO getMemberDao() {
		return memberDao;
	}

	@Autowired
	public void setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}

	public ServiceimagesDAO getServiceImageDao() {
		return serviceImageDao;
	}

	@Autowired
	public void setServiceImageDao(ServiceimagesDAO serviceImageDao) {
		this.serviceImageDao = serviceImageDao;
	}

	public OrderserviceDAO getOrderServiceDao() {
		return orderServiceDao;
	}

	@Autowired
	public void setOrderServiceDao(OrderserviceDAO orderServiceDao) {
		this.orderServiceDao = orderServiceDao;
	}

	public OrdercommentDAO getOrderCommentDao() {
		return orderCommentDao;
	}

	@Autowired
	public void setOrderCommentDao(OrdercommentDAO orderCommentDao) {
		this.orderCommentDao = orderCommentDao;
	}

	/**
	 * @return the propertychargesDao
	 */
	public PropertychargesDAO getPropertychargesDao() {
		return propertychargesDao;
	}

	/**
	 * @param propertychargesDao
	 *            the propertychargesDao to set
	 */
	@Autowired
	public void setPropertychargesDao(PropertychargesDAO propertychargesDao) {
		this.propertychargesDao = propertychargesDao;
	}

	/**
	 * @return the priceDao
	 */
	public PriceDAO getPriceDao() {
		return priceDao;
	}

	/**
	 * @param priceDao
	 *            the priceDao to set
	 */
	@Autowired
	public void setPriceDao(PriceDAO priceDao) {
		this.priceDao = priceDao;
	}

	/**
	 * @return the orderStatusDao
	 */
	public OrderstatusDAO getOrderStatusDao() {
		return orderStatusDao;
	}

	/**
	 * @param orderStatusDao
	 *            the orderStatusDao to set
	 */
	@Autowired
	public void setOrderStatusDao(OrderstatusDAO orderStatusDao) {
		this.orderStatusDao = orderStatusDao;
	}

	public EventDAO getEventDao() {
		return eventDao;
	}

	@Autowired
	public void setEventDao(EventDAO eventDao) {
		this.eventDao = eventDao;
	}

}
