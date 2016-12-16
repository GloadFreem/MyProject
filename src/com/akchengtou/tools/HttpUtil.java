package com.akchengtou.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 网络请求工具类
 * 
 * @author EasonYang
 *
 */
public class HttpUtil {

	public final static String LAG = "Message System:";

	/**
	 * 获取网页Html元素
	 * 
	 * @param url
	 * @return
	 */
	public final static String getHtmlString(String url) {
		if (url == null || url.equals("")) {
			return "";
		}
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		// System.out.println(LAG + httpget.getURI());

		String httpResult = "";

		try {
			HttpResponse res = httpClient.execute(httpget);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				httpResult = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
//			throw new RuntimeException(e);

		} finally {
			// 关闭连接 ,释放资源
			httpClient.getConnectionManager().shutdown();
		}

		return httpResult;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getDateTime() {
		// TODO Auto-generated method stub
		Date date = new Date();
		String createTime = "";
		java.text.DateFormat format1 = new java.text.SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		createTime = format1.format(new Date());
		return createTime;
	}

	/**
	 * 传入“XX分种前”、“XX小时前”，转化为具体的时间，格式为：2016-10-17 10:12:34
	 * @param time
	 * @return
	 */
	public static String timeUtil(String time) {
		String DateString = "";
        time = time.trim();
        SimpleDateFormat newdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (time != null && !time.equals("")) {
            if (time.contains("秒前")) {
                int min = Integer.valueOf(time.replace("秒前","").trim());
                Long inputTime = System.currentTimeMillis()- Long.valueOf(min)*1000;
                DateString = newdateFormat.format(new Date(inputTime));
            } else if (time.contains("分钟前")) {
                int min = Integer.valueOf(time.replace("分钟前","").trim());
                Long inputTime = System.currentTimeMillis()- Long.valueOf(min)*1000*60;
                DateString = newdateFormat.format(new Date(inputTime));
            } else if (time.contains("小时前")) {
                int min = Integer.valueOf(time.replace("小时前","").trim());
                Long inputTime = System.currentTimeMillis()- Long.valueOf(min)*1000*60*60;
                DateString = newdateFormat.format(new Date(inputTime));
            }else if (time.contains("周前")) {
                int min = Integer.valueOf(time.replace("周前","").trim());
                Long inputTime = System.currentTimeMillis()- Long.valueOf(min)*1000*60*60*24*7;
                DateString = newdateFormat.format(new Date(inputTime));
            }
            else if (time.contains("天前")) {
                int min = Integer.valueOf(time.replace("天前","").trim());
//                System.out.println("min:" + Long.valueOf(min*1000*60*60*24));
                Long inputTime = new Date().getTime()- Long.valueOf(min)*1000*60*60*24;

                DateString = newdateFormat.format(new Date(inputTime));
            } else if (time.contains("月前")) {
                int min = Integer.valueOf(time.replace("月前","").trim());
                Long inputTime = System.currentTimeMillis()- Long.valueOf(min)*1000*60*60*24*30;
                DateString = newdateFormat.format(new Date(inputTime));
            }  else if (time.contains("刚刚")) {
            	  DateString = newdateFormat.format(new Date());
            } else {
                DateString = time;
            }
        } else {
            DateString = newdateFormat.format(new Date());
        }
    
        return DateString;
	}

}
