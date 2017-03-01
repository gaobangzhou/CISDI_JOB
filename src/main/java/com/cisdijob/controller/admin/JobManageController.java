package com.cisdijob.controller.admin;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.controller.common.BaseController;
import com.cisdijob.model.base.User;
import com.cisdijob.model.entity.Job;
import com.cisdijob.service.common.CompanyService;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.JobService;
import com.cisdijob.service.pages.RecruitmentService;
import com.cisdijob.tools.PaginationUtil;

@Controller
@RequestMapping(value = "jobmanage")
public class JobManageController extends BaseController {
	@Resource
	private JobService jobService;
	@Resource
	private CompanyService companyService;

	@Resource
	private DropdownListService dropListService;

	@Resource
	private RecruitmentService recruitmentService;

	@RequestMapping(value = "job-manage.html")
	public ModelAndView JobPage(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		ModelAndView mv = this.createAdminView("admin/job-manage.html");
		mv.addObject("CompanyList", companyService.getAllCompany());
		mv.addObject("EducationList",
				dropListService.getDropListByType("EDUCATION"));
		mv.addObject("JobTypeList",
				dropListService.getDropListByType("JOB_TYPE"));
		mv.addObject("SubjectList",
				dropListService.getDropListByType("SUBJECT"));
		mv.addObject("WorkPlaceList",
				dropListService.getDropListByType("WORK_PALCE"));
		mv.addObject("RecruitList", recruitmentService.selectRecruitments());
		mv.addObject("user", user);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNumber", 1);
		map.put("perNumber", 10);
		List<Job> jobList = jobService.getJobListByMap(map);
		mv.addObject("JobList", jobList);
		mv.addObject(
				"pagination",
				PaginationUtil.getPaginationMap(1, 10,
						jobService.getJobCountByMap(map)));
		return mv;

	}

	@RequestMapping(value = "screen-manage.html")
	public ModelAndView ScreenPage() {

		ModelAndView mv = this.createAdminView("admin/screen-manage.html");

		return mv;

	}

	@RequestMapping(value = "insertJob")
	@ResponseBody
	public Map<String, Object> insertJob(HttpServletRequest request,
			@RequestBody Map<String, Object> params) throws ParseException {
		System.out.println("---------" + params.toString() + "---------");
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = params;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		UUID uuid = UUID.randomUUID();
		String jobGuid = uuid.toString();
		String recType = (String) params.get("recruitment_type");
		String rec = (String) params.get("recruitment");
		String[] recList = null;
		if (recType.equals("campus")) {
			recList = rec.split(";");
		}
		map.put("guid", jobGuid);
		map.put("create_time", new Date());
		map.put("create_person", user.getCode());

		try {
			jobService.insertJob(map);

			if (recType.equals("campus")) {// 循环添加场次和职位的关联
				for (int i = 0; i < recList.length; i++) {
					if (!recList[i].equals("")) {
						Map<String, Object> raMap = new HashMap<String, Object>();
						raMap.put("recGuid", recList[i]);
						raMap.put("jobGuid", jobGuid);
						raMap.put("createPerson", user.getCode());
						recruitmentService.insertJobAndRecruit(raMap);
					}

				}
			}
			result.put("success", true);
			result.put("successMessage", "提交成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("failureMessage", "提交数据失败！");
		}

		return result;

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return job的一个modal框
	 */

	@RequestMapping(value = "jobModal")
	public ModelAndView jobModal(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Map<String, Object> parmas) {
		// String jobGuid = request.getParameter("jobGuid");
		String jobGuid = parmas.get("jobGuid").toString();
		ModelAndView mv = new ModelAndView();
		Job job = null;
		try {
			job = jobService.getJobById(jobGuid);
			mv.addObject("success", true);
		} catch (Exception e) {
			// TODO: handle exception
			mv.addObject("success", false);
		}
	
			String recruitValueString = "";
			List<Map<String, Object>> lm = recruitmentService
					.getRecruitByJobguid(jobGuid);
			if (lm != null) {
				for (Map<String, Object> map : lm) {
					recruitValueString += (String) map.get("RECNAME") + "-";
				}
				mv.addObject("RecruitString", recruitValueString);
			}


		mv.setViewName("fragment/job-Modal");

		mv.addObject("JobInfo", job);
		mv.addObject("CompanyList", companyService.getAllCompany());
		mv.addObject("EducationList",
				dropListService.getDropListByType("EDUCATION"));
		mv.addObject("JobTypeList",
				dropListService.getDropListByType("JOB_TYPE"));
		mv.addObject("SubjectList",
				dropListService.getDropListByType("SUBJECT"));
		mv.addObject("WorkPlaceList",
				dropListService.getDropListByType("WORK_PALCE"));
		mv.addObject("RecruitList", recruitmentService.selectRecruitments());
		return mv;
	}

	
	@RequestMapping(value = "editJobStatus")
	@ResponseBody
	public Map<String, Object> editJobStatus(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Map<String, Object> params) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			jobService.updateJob(params);
			map.put("success", true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("success", false);
			map.put("failureMessage", "操作失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "updateJob")
	@ResponseBody
	public Map<String, Object> updateJob(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Map<String, Object> params) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		Map<String,Object> jobMap =params;
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			jobMap.put("updatePerson", user.getCode());
			jobService.updateJobByMap(jobMap);
			map.put("success", true);
			map.put("Message", "更新成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("success", false);
			map.put("Message", "操作失败！");
		}
		return map;
	}
}
