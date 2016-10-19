package com.akchengtou.mobile.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


public class BaseController  {
	protected Map result ;
	protected String message = "";
	protected int status = 401; // 状态码
	
	
	public Map getResult() {
//		this.result = new HashMap();
		this.result.put("status", this.status);
		this.result.put("message", this.message);
		return result;
	}
	
	public void setResult(Map result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
