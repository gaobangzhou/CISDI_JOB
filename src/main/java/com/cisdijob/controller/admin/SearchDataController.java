package com.cisdijob.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.model.entity.Job;
import com.cisdijob.model.entity.ResumeInfo;
import com.cisdijob.service.pages.JobService;
import com.cisdijob.service.pages.ResumeService;
import com.cisdijob.tools.PaginationUtil;

@Controller
@RequestMapping(value = "SearchData")
public class SearchDataController {
	@Resource
	private ResumeService resumeService;
	@Resource
	private JobService jobService;

	@RequestMapping(value = "ResumeInfoSearch")
	public ModelAndView ResumeSearchPage(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		Map<String, Object> map = params;
		String recruit = (String) params.get("recruitment");
		if (recruit.equals("all")) {
			map.remove("recruitment");
		}
		Integer totalNum = resumeService.getApplyCount(params);
		Integer startNumber = (currPage - 1) * perPageNum + 1;
		if (startNumber > totalNum & currPage > 1) {
			startNumber = (currPage - 2) * perPageNum + 1;
		}
	
		map.put("startNumber", startNumber);
		map.put("perNumber", perPageNum);

		List<ResumeInfo> listResume = resumeService.getResumeInfoByMap(map);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/resume-table");
		mv.addObject("ResumeList", listResume);

		return mv;
	}

	@RequestMapping(value = "ResumeInfoPagination")
	public ModelAndView ResumePagePagination(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		String recruit = (String) params.get("recruitment");
		if (recruit.equals("all")) {
			params.remove("recruitment");
		}
		Map<String, Object> map = PaginationUtil.getPaginationMap(currPage,
				perPageNum, resumeService.getApplyCount(params));
		if ((Integer) map.get("pages") < currPage && currPage > 1) {
			map.put("pageNum", currPage - 1);
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/pagination");
		mv.addObject("pagination", map);

		return mv;
	}

	@RequestMapping(value = "JobInfoSearch")
	public ModelAndView JobSearchPage(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		Integer totalNum = jobService.getJobCountByMap(params);
		Integer startNumber = (currPage - 1) * perPageNum + 1;
		if (startNumber > totalNum & currPage > 1) {
			startNumber = (currPage - 2) * perPageNum + 1;
		}
		Map<String, Object> map = params;

		map.put("startNumber", startNumber);
		map.put("perNumber", perPageNum);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/job-table");
		List<Job> jobList = jobService.getJobListByMap(map);
		mv.addObject("JobList", jobList);
		return mv;
	}

	@RequestMapping(value = "JobInfoPagination")
	public ModelAndView JobPagePagination(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		Map<String, Object> map = PaginationUtil.getPaginationMap(currPage,
				perPageNum, jobService.getJobCountByMap(params));
		if ((Integer) map.get("pages") < currPage && currPage > 1) {
			map.put("pageNum", currPage - 1);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/pagination");
		mv.addObject("pagination", map);
		return mv;
	}

	@RequestMapping(value = "JobListSearch")
	public ModelAndView JobInfoSearch(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/job-list");
		if (params.get("RecType").equals("campus")) {
			mv.addObject("JOBLIST", jobService.searchCampusJobs(params));
			mv.addObject("recType", "campus");
		} else {
			mv.addObject("JOBLIST", jobService.searchSocietyJobs(params));
			mv.addObject("recType", "society");
		}
		return mv;
	}
}
