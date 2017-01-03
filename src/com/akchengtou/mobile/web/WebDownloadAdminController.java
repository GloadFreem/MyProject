package com.akchengtou.mobile.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;

import com.akchengtou.web.entity.Propertycharges;
import com.akchengtou.web.manager.ServiceManager;

//import com.jinzht.tools.ExcelUtil;
//import com.jinzht.tools.Project;
//import com.jinzht.web.entity.BusinessInvitationCode;
//import com.jinzht.web.manager.CourseManager;

@Controller
public class WebDownloadAdminController  extends BaseController{
	// @Autowired
	// private CourseManager curseManager;
	@Autowired
	private ServiceManager serviceManager;

	@RequestMapping(value = "/importPropertycharges", method = RequestMethod.POST)
	@ResponseBody
	public Map importBrandSort(
			@RequestParam("filename") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String temp = request.getSession().getServletContext()
				.getRealPath(File.separator)
				+ "temp"; // 临时目录
		File tempFile = new File(temp);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		DiskFileUpload fu = new DiskFileUpload();
		fu.setSizeMax(10 * 1024 * 1024); // 设置允许用户上传文件大小,单位:位
		fu.setSizeThreshold(4096); // 设置最多只允许在内存中存储的数据,单位:位
		fu.setRepositoryPath(temp); // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
		// 开始读取上传信息
		// int index = 0;
		/*
		 * List fileItems = null; try { fileItems = fu.parseRequest(request); }
		 * catch (Exception e) { e.printStackTrace(); } Iterator iter =
		 * fileItems.iterator(); // 依次处理每个上传的文件 FileItem fileItem = null; while
		 * (iter.hasNext()) { FileItem item = (FileItem) iter.next();//
		 * 忽略其他不是文件域的所有表单信息 if (!item.isFormField()) { fileItem = item; //
		 * index++; } }
		 * 
		 * if (fileItem == null) return null;
		 */
		if (file == null)
			return null;

		String name = file.getOriginalFilename();// 获取上传文件名,包括路径
		// name = name.substring(name.lastIndexOf("\\") + 1);// 从全路径中提取文件名
		long size = file.getSize();
		if ((name == null || name.equals("")) && size == 0)
			return null;
		InputStream in = file.getInputStream();
		List<Propertycharges> BrandMobileInfos = this.serviceManager
				.importBrandPeriodSort(in);

		// 改为人工刷新缓存KeyContextManager.clearPeriodCacheData(new
		// PeriodDimensions());// 清理所有缓存
		int count = BrandMobileInfos.size();
		String strAlertMsg = "";
		if (count != 0) {
			strAlertMsg = "成功导入" + count + "条！";
		} else {
			strAlertMsg = "导入失败！";
		}
		// request.setAttribute("brandPeriodSortList", BrandMobileInfos);
		// request.setAttribute("strAlertMsg", strAlertMsg);

		request.getSession().setAttribute("msg", strAlertMsg);
		// return null;
		
		return getResult();
	}
	//
	//
	// @RequestMapping(value = "newSystem/downloadInviteCode")
	// public String downloadInviteCode(HttpServletRequest request,
	// HttpServletResponse response) throws IOException {
	// String fileName = "excel文件";
	// // 填充projects数据
	// List<BusinessInvitationCode> codes = createInviteCodeData();
	// List<Map<String, Object>> list = createExcelInviteCodeRecord(codes);
	// String columnNames[] = { "ID", "所属课程", "邀请码", "是否已过期"};// 列名
	// String keys[] = { "cid", "bname", "ccode", "cvalid"};// map中的key
	// ByteArrayOutputStream os = new ByteArrayOutputStream();
	// try {
	// ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// byte[] content = os.toByteArray();
	// InputStream is = new ByteArrayInputStream(content);
	// // 设置response参数，可以打开下载页面
	// response.reset();
	// response.setContentType("application/vnd.ms-excel;charset=utf-8");
	// response.setHeader("Content-Disposition", "attachment;filename="
	// + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
	// ServletOutputStream out = response.getOutputStream();
	// BufferedInputStream bis = null;
	// BufferedOutputStream bos = null;
	// try {
	// bis = new BufferedInputStream(is);
	// bos = new BufferedOutputStream(out);
	// byte[] buff = new byte[2048];
	// int bytesRead;
	// // Simple read/write loop.
	// while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	// bos.write(buff, 0, bytesRead);
	// }
	// } catch (final IOException e) {
	// throw e;
	// } finally {
	// if (bis != null)
	// bis.close();
	// if (bos != null)
	// bos.close();
	// }
	// return null;
	// }
	//
	// private List<BusinessInvitationCode> createInviteCodeData() {
	// // TODO Auto-generated method stub
	// // 自己实现
	// List list = this.curseManager.getBusinessInvitationCodeDao().findAll();
	//
	// return list;
	// }
	//
	// private List<Map<String, Object>>
	// createExcelInviteCodeRecord(List<BusinessInvitationCode> codes) {
	// List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("sheetName", "sheet1");
	// listmap.add(map);
	// BusinessInvitationCode project = null;
	// for (int j = 0; j < codes.size(); j++) {
	// project = codes.get(j);
	// Map<String, Object> mapValue = new HashMap<String, Object>();
	// mapValue.put("cid", project.getCid());
	// mapValue.put("bname", project.getBusinessSchool().getBname());
	// mapValue.put("ccode", project.getCcode());
	//
	// String valid = "已失效";
	// if(project.getCvalid()!=null)
	// {
	// if(project.getCvalid().equals("1"))
	// {
	// valid= "有效";
	// }
	//
	// }
	// mapValue.put("cvalid", valid);
	//
	// listmap.add(mapValue);
	// }
	// return listmap;
	// }
	// @RequestMapping(value = "newSystem/downloadproject")
	// public String download(HttpServletRequest request,
	// HttpServletResponse response) throws IOException {
	// String fileName = "excel文件";
	// // 填充projects数据
	// List<Project> projects = createData();
	// List<Map<String, Object>> list = createExcelRecord(projects);
	// String columnNames[] = { "ID", "项目名", "销售人", "负责人", "所用技术", "备注" };// 列名
	// String keys[] = { "id", "name", "saler", "principal", "technology",
	// "remarks" };// map中的key
	// ByteArrayOutputStream os = new ByteArrayOutputStream();
	// try {
	// ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// byte[] content = os.toByteArray();
	// InputStream is = new ByteArrayInputStream(content);
	// // 设置response参数，可以打开下载页面
	// response.reset();
	// response.setContentType("application/vnd.ms-excel;charset=utf-8");
	// response.setHeader("Content-Disposition", "attachment;filename="
	// + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
	// ServletOutputStream out = response.getOutputStream();
	// BufferedInputStream bis = null;
	// BufferedOutputStream bos = null;
	// try {
	// bis = new BufferedInputStream(is);
	// bos = new BufferedOutputStream(out);
	// byte[] buff = new byte[2048];
	// int bytesRead;
	// // Simple read/write loop.
	// while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	// bos.write(buff, 0, bytesRead);
	// }
	// } catch (final IOException e) {
	// throw e;
	// } finally {
	// if (bis != null)
	// bis.close();
	// if (bos != null)
	// bos.close();
	// }
	// return null;
	// }
	//
	// private List<Project> createData() {
	// // TODO Auto-generated method stub
	// // 自己实现
	// Project pro = new Project();
	// pro.setName("科技");
	// pro.setRemarks("ss");
	// pro.setTechnology("高科技");
	//
	// List list = new ArrayList();
	//
	// list.add(pro);
	// return list;
	// }
	//
	// private List<Map<String, Object>> createExcelRecord(List<Project>
	// projects) {
	// List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("sheetName", "sheet1");
	// listmap.add(map);
	// Project project = null;
	// for (int j = 0; j < projects.size(); j++) {
	// project = projects.get(j);
	// Map<String, Object> mapValue = new HashMap<String, Object>();
	// mapValue.put("id", project.getId());
	// mapValue.put("name", project.getName());
	// mapValue.put("technology", project.getTechnology());
	// mapValue.put("remarks", project.getRemarks());
	// listmap.add(mapValue);
	// }
	// return listmap;
	// }
}
