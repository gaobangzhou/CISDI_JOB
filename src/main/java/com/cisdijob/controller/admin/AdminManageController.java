package com.cisdijob.controller.admin;

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

import com.cisdijob.model.base.User;

import com.cisdijob.service.common.LoginService;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.JobService;

import com.cisdijob.service.pages.ResumeService;
import com.cisdijob.service.pages.SummaryService;
import com.cisdijob.tools.HttpServletUtil;


@Controller
@RequestMapping(value = "admin")
public class AdminManageController extends BaseController {
	@Resource
	private LoginService loginService;
	
    @Resource
	private ResumeService resumeService;
	
    @Resource
  	private DropdownListService drpService;
    
    @Resource
	private JobService jobService;
    
    @Resource
  	private SummaryService summaryService;
    
	@RequestMapping(value = "/admin-home.html")
	public ModelAndView indexPage(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		// logger.debug("TEST");
		ModelAndView mv = this.createAdminView("admin/admin-home.html");
		mv.addObject("totalMap", getTotalNumToken());
		mv.addObject("resumeNumber", resumeService.getResumeCount());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "used");
		mv.addObject("applyNumber", resumeService.getApplyCount(map));
		
		int jobTotal =summaryService.getJobCountByStatus("used");
		int jobApplied =summaryService.getApplyJobCount();
		mv.addObject("jobNumber", jobTotal);
		mv.addObject("appliedJobNum", jobApplied);
		if(jobTotal==0){
			mv.addObject("jobPercent", 0);
		}else{
			mv.addObject("jobPercent", jobApplied*100/jobTotal);
		}
		mv.addObject("rejectApply", summaryService.getApplyCountByStatus("reject"));
		mv.addObject("usedApply", summaryService.getApplyCountByStatus("used"));
		mv.addObject("unusedApply", summaryService.getApplyCountByStatus("unused"));
		mv.addObject("adoptApply", summaryService.getApplyCountByStatus("adopt"));
		mv.addObject("adminNum", summaryService.getAdminCount());
		mv.addObject("jobType", summaryService.getLookupCountByType("JOB_TYPE"));
		mv.addObject("workPlace", summaryService.getLookupCountByType("WORK_PLACESUBJECT"));
		mv.addObject("subject", summaryService.getLookupCountByType("SUBJECT"));
		
		mv.addObject("jobCompanyCount", jobService.getJobCompanyCount());
		List<Map<String,Object>> lm =summaryService.getTopHotJob();
		if(lm!=null){
		  mv.addObject("topHotJops", lm);
		}
		
		mv.addObject("totalPlace", drpService.getSelectionCount("WORK_PALCE"));
		mv.addObject("totalJobType", drpService.getSelectionCount("JOB_TYPE"));
		mv.addObject("totalSubject", drpService.getSelectionCount("SUBJECT"));
		return mv;

	}

	@RequestMapping(value = "/login.html")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;

	}

	@RequestMapping(value = "innerLogin")
	@ResponseBody
	public Map<String, Object> innerLogin(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {

		Map<String, Object> result = loginService.login(params);
		if ((Boolean) result.get("success")) {
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", (User) result.get("user"));
			result.remove("user");
		}
		return result;

	}

	@RequestMapping(value = "logout")
	public ModelAndView logoutPage() {
		HttpServletUtil.getInstance().getSession()
				.removeAttribute("currentUser");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/login.html");
		return mv;

	}
	
	
}
