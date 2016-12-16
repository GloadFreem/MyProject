package com.jinzht.web.filter;

import javax.interceptor.Interceptor;
import javax.jws.soap.InitParam;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LogInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest hrequest,
			HttpServletResponse hresponse, Object arg2) throws Exception {
		// 获取session会话
		HttpSession session = hrequest.getSession(true);
		
		//如果带有直连接口属性，直接通过
		if(hrequest.getParameter("requestType")!=null)
		{
			String typeString = hrequest.getParameter("requestType").toString();
			if(typeString!=null && typeString.equals("webRequest")){
				session.setAttribute("userId", 5333);
				return true;
			}
		}
		
		// 输出请求信息
		System.out.println("Filter 截获到用户倾听求地址:  " + hrequest.getServletPath());
		long after = System.currentTimeMillis();

		System.out.println("过滤结束...");
		// 获取客户请求页面路径
		String requestPath = hrequest.getServletPath();
		

		return true;
	}

}
