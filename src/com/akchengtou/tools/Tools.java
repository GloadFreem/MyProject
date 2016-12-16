package com.akchengtou.tools;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.akchengtou.web.entity.User;

public class Tools {

	public static Integer findUserBySession(HttpSession session) {
		User user = null;
		if (session.getAttribute("userId") != null) {
			Integer userId = (Integer) session.getAttribute("userId");
			return userId;
		}
		return null;
	}

	public static String generateInviteCode(Integer id, boolean isWebchatLogin) {
		String code = "";
		String flag = "T";
		if (!isWebchatLogin) {
			flag = "F";
		}

		String temp = String.valueOf(id);
		int length = temp.length();

		// 填充中间位数
		for (int i = length; i <= AKConfig.INTEGER_SYSTEM_CODE_GENERATE_MAX_VALUE; i++) {
			code += "0";
		}

		// 按照生成统一生成
		return String.format(AKConfig.STRING_SYSTEM_CODE_GENERATE_FORMAT, code,
				id, flag);
	}

	
	public static String objToStr(String obj)
	{
		if(obj!=null && obj!="")
		{
			return obj.trim();
		}
		return "";
		
	}
	
	/***
	 * 生成请求连接
	 * @param actionUrl
	 * @return
	 */
	public static String generateWebUrl(String actionUrl)
	{
		return String.format(AKConfig.STRING_SYSTEM_ADDRESS+"%s.action", actionUrl);
	}
	
	/***
	 * 根据内容id生成网页查看链接
	 * @param contentId
	 * @return
	 */
	public static String  generateWebRecordUrl(Integer contentId)
	{
		String path = String.format("%swebUrlLooker.action?contentId=%d",
				AKConfig.STRING_SYSTEM_ADDRESS, contentId);
		return path;
	}
	
	
	/***
	 * 按照密码生成规则生成密码
	 * @param password
	 * @return
	 */
	public static String generatePassword(String password,String telephone)
	{
		String str = String.format(AKConfig.STRING_PASWWORD_RULE, password,telephone);
		
		return MD5.GetMD5Code(str);
	}

}
