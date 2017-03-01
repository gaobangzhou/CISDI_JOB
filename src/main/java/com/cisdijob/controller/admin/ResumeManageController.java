package com.cisdijob.controller.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.controller.common.BaseController;
import com.cisdijob.model.base.ExportExcel;
import com.cisdijob.model.base.User;
import com.cisdijob.model.entity.ResumeInfo;
import com.cisdijob.model.entity.ResumeResult;
import com.cisdijob.model.entity.ResumeRow;
import com.cisdijob.service.common.CompanyService;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.MyApplyService;
import com.cisdijob.service.pages.RecruitmentService;
import com.cisdijob.service.pages.ResumeService;
//import com.cisdijob.tools.PaginationUtil;
import com.cisdijob.tools.PaginationUtil;

@Controller
@RequestMapping(value = "resumemanage")
public class ResumeManageController extends BaseController {

	@Resource
	private ResumeService resumeService;
	@Resource
	private CompanyService companyService;
	@Resource
	private MyApplyService applyService;
	@Resource
	private DropdownListService dropListService;

	@Resource
	private RecruitmentService recruitmentService;

	@RequestMapping(value = "/resume-manage.html")
	public ModelAndView ResumePage(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mv = this.createAdminView("admin/resume-manage.html");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		mv.addObject("CompanyList", companyService.getAllCompany());
		mv.addObject("ChoiseList", dropListService.getDropListByType("CHOISE"));
		mv.addObject("RecruitList", recruitmentService.selectRecruitments());
		mv.addObject("user", user);
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("company", user.getOuId());
		map.put("status", "used");
		map.put("startNumber", 1);
		map.put("perNumber", 10);
		List<ResumeInfo> resumeList = resumeService.getResumeInfoByMap(map);
		mv.addObject("ResumeList", resumeList);
		mv.addObject("CompanyList", companyService.getAllCompany());
		mv.addObject(
				"pagination",
				PaginationUtil.getPaginationMap(1, 10,
						resumeService.getApplyCount(map)));
		return mv;

	}

	// 表格中的登记按钮的mapping
	@RequestMapping(value = "manageApply")
	@ResponseBody
	public Map<String, Object> manageApply(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		// String applyGuid =(String) params.get("guid");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		try {
			applyService.updateApply(params);
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", "接受申请失败");
		}
		return result;
	}

	@RequestMapping(value = "resumeinfo")
	public ModelAndView ResumeInfo(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		String openId = (String) params.get("OpenId");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/resume-info");
		mv.addObject("Resume", resumeService.getResumeByOpenId(openId));
		mv.addObject("ScoreList",
				dropListService.getDropListByType("SCHOOL_SCORE"));
		return mv;
	}

	@RequestMapping(value = "download")
	public void download(HttpServletRequest request,
			HttpServletResponse response) {

		String path = request.getSession().getServletContext().getRealPath("/");
		String fileName = "所有简历报表.xls";
		path = path + "filesfolder/" + fileName; // 获取报表文件，如果不存在，新建该文件

		ExportExcel<ResumeRow> ex = new ExportExcel<ResumeRow>();
		List<ResumeRow> dataset = resumeService.getAllResume();
		try {
			OutputStream out = new FileOutputStream(path);
			ex.exAllResumeExcel(dataset, out);
			out.close();

			System.out.println("excel导出成功！");
			File file = new File(path);
			// 取得文件名。
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "downloadResult")
	public void downloadResult(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		String company = (String) request.getParameter("company");
		String recruitment = (String) request.getParameter("recruitment");
		String status = (String) request.getParameter("status");
		String choiceType = (String) request.getParameter("choiceType");
		map.put("company", company);
		if (!recruitment.equals("all")) {
			map.put("recruitment", recruitment);
		}
		map.put("status", status);
		map.put("choiceType", choiceType);
		String path = request.getSession().getServletContext().getRealPath("/");
		String fileName = "搜索结果报表.xls";
		path = path + "filesfolder/" + fileName; // 获取报表文件，如果不存在，新建该文件

		ExportExcel<ResumeResult> ex = new ExportExcel<ResumeResult>();
		List<ResumeResult> dataset = resumeService.getResumeResultByMap(map);
		try {
			OutputStream out = new FileOutputStream(path);
			ex.exResultResumeExcel(dataset, out);
			out.close();

			System.out.println("excel导出成功！");
			File file = new File(path);
			// 取得文件名。
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}

	}
}
