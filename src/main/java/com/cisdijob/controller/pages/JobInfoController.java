package com.cisdijob.controller.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.config.JobAppConfig;
import com.cisdijob.controller.common.BaseController;
import com.cisdijob.model.base.Selection;
import com.cisdijob.model.entity.Company;
import com.cisdijob.model.entity.Job;
import com.cisdijob.model.entity.MyApply;
import com.cisdijob.model.entity.Recruitment;
import com.cisdijob.model.entity.Resume;
import com.cisdijob.model.entity.SearchPosition;
import com.cisdijob.service.common.CompanyService;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.JobService;
import com.cisdijob.service.pages.MyApplyService;
import com.cisdijob.service.pages.RecruitmentService;
import com.cisdijob.service.pages.ResumeService;
import com.cisdijob.tools.WeixinUtil;

@Controller
@RequestMapping(value = "jobinfo")
public class JobInfoController extends BaseController {

	@Resource
	private JobService jobService;
	@Resource
	private RecruitmentService recruitmentService;
	@Resource
	private MyApplyService myApplyService;
	@Resource
	private DropdownListService dropdownListService;
	@Resource
	private ResumeService resumeService;
	@Resource
	private CompanyService companyService;

	/*
	 * RecType值：campus 和 society
	 */
	/*
	 * @RequestMapping(value = "/{RecType}/position.html") public ModelAndView
	 * positionPage(HttpServletRequest request,
	 * 
	 * @PathVariable("RecType") String RecType) { ModelAndView mv = new
	 * ModelAndView(); String state = (String) request.getParameter("state");
	 * String code = (String) request.getParameter("code");
	 * System.out.println("-------------code--------" + code); HttpSession
	 * session = request.getSession(); String currentOpenId = (String)
	 * session.getAttribute("currentOpenId"); if (currentOpenId == null ||
	 * currentOpenId.equals("")) { currentOpenId =
	 * WeixinUtil.getOpenidByOauth(JobAppConfig.APP_ID, JobAppConfig.APP_SECRET,
	 * code); System.out.println("----------------OpenId--------" +
	 * currentOpenId); session.setAttribute("currentOpenId", currentOpenId); }
	 * 
	 * mv.setViewName("jobs/jobinfo"); List<Job> jobList = null;
	 * 
	 * if (RecType.equals("society")) { mv.addObject("RECRUITMENT_TYPE",
	 * "社会招聘"); jobList = jobService.selectSocietyJobs();
	 * mv.addObject("JOBLIST", jobList); } else { String rguid =
	 * request.getParameter("rguid").toString(); //这里的session给campus
	 * recuritment搜索时用 session.setAttribute("rguid", rguid); jobList =
	 * jobService.selectCampusJobs(rguid); mv.addObject("RECRUITMENT_TYPE",
	 * "校园招聘"); mv.addObject("JOBLIST", jobList); } // DropdownList
	 * List<Company> listCompanySelection = companyService.getAllCompany();
	 * 
	 * mv.addObject("COMPANYS", listCompanySelection); List<Selection>
	 * listSubjectSelection = dropdownListService .getDropListByType("SUBJECT");
	 * mv.addObject("SUBJECTS", listSubjectSelection); List<Selection>
	 * listJobTypeSelection = dropdownListService
	 * .getDropListByType("JOB_TYPE"); mv.addObject("JOBTYPS",
	 * listJobTypeSelection); return mv; }
	 */

/*	// 搜索
	@RequestMapping(value = "/searchPosition.html")
	public ModelAndView searchPositionPage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jobs/position");
		String companyCode = request.getParameter("companyCode");
		String typeCode = request.getParameter("typeCode");
		String objectCode = request.getParameter("objectCode").trim();
		String RecType = request.getParameter("recruitmentType");
		String keyWord = request.getParameter("keyWord");
		try {
			keyWord = URLDecoder.decode(keyWord, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SearchPosition searchPosition = new SearchPosition();
		searchPosition.setRecType(RecType);
		searchPosition.setCompany(companyCode);
		searchPosition.setType(typeCode);
		searchPosition.setProject(objectCode);
		searchPosition.setKeyWord(keyWord);
		List<Job> jobList = null;
		mv.addObject("companyCode", companyCode);
		mv.addObject("typeCode", typeCode);
		mv.addObject("objectCode", objectCode);
		if (RecType.equals("society")) {
			mv.addObject("RECRUITMENT_TYPE", "社会招聘");
			jobList = jobService.searchSocietyJobs(searchPosition);
			mv.addObject("JOBLIST", jobList);
		} else {
			HttpSession session = request.getSession();
			String rguid = (String) session.getAttribute("rguid");
			searchPosition.setRguid(rguid);
			mv.addObject("RECRUITMENT_TYPE", "校园招聘");
			jobList = jobService.searchCampusJobs(searchPosition);
			mv.addObject("JOBLIST", jobList);
		}
		// DropdownList
		List<Company> listCompanySelection = companyService.getAllCompany();
		mv.addObject("COMPANYS", listCompanySelection);
		List<Selection> listSubjectSelection = dropdownListService
				.getDropListByType("SUBJECT");
		mv.addObject("SUBJECTS", listSubjectSelection);
		List<Selection> listJobTypeSelection = dropdownListService
				.getDropListByType("JOB_TYPE");
		mv.addObject("JOBTYPS", listJobTypeSelection);
		return mv;
	}*/

	// 职位详情
	@RequestMapping(value = "{recType}/position-detail/{guid}.html")
	public ModelAndView positionDetilInfo(
			HttpServletRequest request,@PathVariable("recType") String recType,@PathVariable("guid") String guid) {
		System.out.println("guid=" + guid);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jobs/position-detail");
		Job job =null;
		if(recType.equals("campus")){
		
		job = jobService.getCampusJobById(guid);//用关联guid获取校园招聘job信息	
	
		}else{
			job = jobService.getJobById(guid);	//用jobguid获取job信息	
		}
		mv.addObject("JOB", job);
		return mv;
	}

	@RequestMapping(value = "/apply-record.html")
	public ModelAndView applyRecord(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jobs/apply-record");
		// 1.获取用户的openId
		HttpSession session = request.getSession();
		String code = (String) request.getParameter("code");
		String currentOpenId = (String) session.getAttribute("currentOpenId");
		if (currentOpenId == null || currentOpenId.equals("")) {
			currentOpenId = WeixinUtil.getOpenidByOauth(JobAppConfig.APP_ID,
					JobAppConfig.APP_SECRET, code);
			session.setAttribute("currentOpenId", currentOpenId);
		}
		List<MyApply> listMyApply = myApplyService
				.selectMyApplyByOpenId(currentOpenId);
		int count = listMyApply.size();
		mv.addObject("CONTENT", count);
		mv.addObject("MYAPPLYS", listMyApply);
		return mv;
	}

	// 判断简历是否存在和有没有志愿
	@RequestMapping(value = "resumeVerification")
	@ResponseBody
	public Map<String, Object> apply(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		HttpSession session = request.getSession();

		String jobGuid = (String) params.get("jobGuid");
		String currentOpenId = (String) session.getAttribute("currentOpenId");

		if (currentOpenId == null) {
			result.put("success", false);
			result.put("type", "openId");
			result.put("message", "openId 为空");
			return result;
		}

		Resume resumeRow = resumeService.getResumeByOpenId(currentOpenId);
		if (resumeRow == null) {
			result.put("success", false);
			result.put("type", "reusumenull");
			result.put("message", "你还未填写简历！");
			return result;
		}

		List<MyApply> listMyApply = myApplyService
				.selectMyApplyByOpenId(currentOpenId);
		// 判断是否已经申请了2个职位
		if (listMyApply != null && listMyApply.size() == 2) {
			result.put("success", false);
			result.put("type", "MyApply");
			result.put("message", "您已经申请了2个职位，不能再申请");
			return result;
		}

		// 判断是否已经申请过该职位
		Boolean existApply = false;
		for (MyApply ma : listMyApply) {
			if (jobGuid.equals(ma.getJobguid())) {
				existApply = true;
			}
		}
		if (existApply) {
			result.put("success", false);
			result.put("type", "existAppy");
			result.put("message", "您已经申请了该职位");
			return result;
		}
		return result;

	}

	@RequestMapping(value = "volunter")
	@ResponseBody
	public Map<String, Object> selectVolunteer(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		String choiceType = (String) params.get("choice");
		String jobGuid = (String) params.get("jobGuid");
		String jrGuid = (String) params.get("jrGuid");
		HttpSession session = request.getSession();
		String currentOpenId = (String) session.getAttribute("currentOpenId");
		// currentOpenId = "oxSyKwTtvEyDDoBnxLsfpWnK7kCY";
		List<MyApply> listMyApply = myApplyService
				.selectMyApplyByOpenId(currentOpenId);
		if (currentOpenId != null && currentOpenId != "") {

			MyApply nMyApply = new MyApply();
			nMyApply.setJobguid(jobGuid);
			nMyApply.setOpenid(currentOpenId);
			nMyApply.setType(choiceType);
			nMyApply.setJrGuid(jrGuid);
			if (listMyApply == null || listMyApply.size() == 0) {
				myApplyService.insertMyApply(nMyApply);
			} else {
				MyApply ma = listMyApply.get(0);
				String type = ma.getType();
				if (type.equals(choiceType) && type.equals("FC")) {
					ma.setType("SC");
					myApplyService.updateMyApply(ma);

				}
				if (type.equals(choiceType) && type.equals("SC")) {
					ma.setType("FC");
					myApplyService.updateMyApply(ma);
				}
				myApplyService.insertMyApply(nMyApply);
			}
		}
		return map;
	}

	@RequestMapping(value = "/screenings.html")
	public ModelAndView screenings(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String code = (String) request.getParameter("code");

		// HttpSession session = request.getSession();
		String currentOpenId = (String) session.getAttribute("currentOpenId");
		System.out.println("----------session OpenId---------------"
				+ currentOpenId);
		if (currentOpenId == null || currentOpenId.equals("")) {
			currentOpenId = WeixinUtil.getOpenidByOauth(JobAppConfig.APP_ID,
					JobAppConfig.APP_SECRET, code);
			System.out.println("----------------OpenId --------"
					+ currentOpenId);
			session.setAttribute("currentOpenId", currentOpenId);
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("jobs/screenings");
		List<Recruitment> listRecruitment = recruitmentService
				.selectRecruitments();
		mv.addObject("LISTRECRUITMENT", listRecruitment);
		return mv;
	}

	// 修改志愿
	@RequestMapping(value = "/updateMyApply")
	public ModelAndView updateMyApply(HttpServletRequest request,
			HttpServletResponse response) {
		// String choiceType = (String) request.getParameter("choose");
		String myGuidFirst = (String) request.getParameter("myGuid").trim();
		String myGuidSecond = (String) request.getParameter("myGuid1").trim();
		String type = (String) request.getParameter("type");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forward:/jobinfo/apply-record.html");
		MyApply myapplyFirst = new MyApply();
		MyApply myapplySecond = new MyApply();
		HttpSession session = request.getSession();
		String currentOpenId = (String) session.getAttribute("currentOpenId");
		// currentOpenId = "oxSyKwTtvEyDDoBnxLsfpWnK7kCY";
		List<MyApply> listMyApply = myApplyService
				.selectMyApplyByOpenId(currentOpenId);
		if (type.equals("FC")) {
			myapplyFirst.setMYGUID(myGuidFirst);
			myapplyFirst.setType("SC");
			myApplyService.updateMyApply(myapplyFirst);
			// 判断第二志愿存不存在
			if (listMyApply.size() == 2) {
				myapplySecond.setMYGUID(myGuidSecond);
				myapplySecond.setType("FC");
				myApplyService.updateMyApply(myapplySecond);
			}

		}

		if (type.equals("SC")) {
			myapplyFirst.setMYGUID(myGuidFirst);
			myapplyFirst.setType("FC");
			myApplyService.updateMyApply(myapplyFirst);
			// 判断第二志愿存不存在
			if (listMyApply.size() == 2) {
				myapplySecond.setMYGUID(myGuidSecond);
				myapplySecond.setType("SC");
				myApplyService.updateMyApply(myapplySecond);
			}

		}

		return mv;
	}

	// 删除志愿
	@RequestMapping(value = "/deleteMyApply")
	public ModelAndView deleteMyApply(HttpServletRequest request) {
		String myGuid = (String) request.getParameter("myGuid").trim();
		ModelAndView mv = new ModelAndView();
		MyApply myapply = new MyApply();
		myapply.setMYGUID(myGuid);
		myApplyService.deleteApplyByGuid(myGuid);
		mv.setViewName("forward:/jobinfo/apply-record.html");
		return mv;
	}

	@RequestMapping(value = "/campus/{rlGuid}.html")
	public ModelAndView campusPage(HttpServletRequest request,
			@PathVariable("rlGuid") String rlGuid) {
		ModelAndView mv = new ModelAndView();
	
		String code = (String) request.getParameter("code");
		System.out.println("-------------code--------" + code);
		HttpSession session = request.getSession();
		String currentOpenId = (String) session.getAttribute("currentOpenId");
		if (currentOpenId == null || currentOpenId.equals("")) {
			currentOpenId = WeixinUtil.getOpenidByOauth(JobAppConfig.APP_ID,
					JobAppConfig.APP_SECRET, code);
			System.out
					.println("----------------OpenId--------" + currentOpenId);
			session.setAttribute("currentOpenId", currentOpenId);
		}

		mv.setViewName("jobs/jobinfo");
		
		List<Job> jobList = jobService.selectCampusJobs(rlGuid);
		mv.addObject("RECRUITMENT_TYPE", "校园招聘");
		mv.addObject("recType","campus");
		mv.addObject("JOBLIST", jobList);

		// DropdownList
		mv.addObject("COMPANYS", companyService.getAllCompany());
		mv.addObject("SUBJECTS",
				dropdownListService.getDropListByType("EDUCATION"));
		mv.addObject("JOBTYPS",
				dropdownListService.getDropListByType("JOB_TYPE"));
		mv.addObject("recGuid",rlGuid);
		return mv;
	}
	
	@RequestMapping(value = "/society/position.html")
	public ModelAndView societyPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		String code = (String) request.getParameter("code");
		System.out.println("-------------code--------" + code);
		HttpSession session = request.getSession();
		String currentOpenId = (String) session.getAttribute("currentOpenId");
		if (currentOpenId == null || currentOpenId.equals("")) {
			currentOpenId = WeixinUtil.getOpenidByOauth(JobAppConfig.APP_ID,
					JobAppConfig.APP_SECRET, code);
			System.out
					.println("----------------OpenId--------" + currentOpenId);
			session.setAttribute("currentOpenId", currentOpenId);
		}

		mv.setViewName("jobs/jobinfo");
		
		List<Job> jobList = jobService.selectSocietyJobs();
		mv.addObject("RECRUITMENT_TYPE", "社会招聘");
		mv.addObject("recType","society");
		mv.addObject("JOBLIST", jobList);

		// DropdownList
		mv.addObject("COMPANYS", companyService.getAllCompany());
		mv.addObject("SUBJECTS",
				dropdownListService.getDropListByType("EDUCATION"));
		mv.addObject("JOBTYPS",
				dropdownListService.getDropListByType("JOB_TYPE"));
		return mv;
	}
}
