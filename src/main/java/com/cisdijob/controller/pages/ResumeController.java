package com.cisdijob.controller.pages;

import java.util.HashMap;
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

import com.cisdijob.config.JobAppConfig;
import com.cisdijob.controller.common.BaseController;
import com.cisdijob.model.entity.Resume;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.ResumeService;
import com.cisdijob.tools.StringUtil;
import com.cisdijob.tools.WeixinUtil;

@Controller
@RequestMapping(value = "resume")
public class ResumeController extends BaseController {
	@Resource
	private ResumeService resumeService;

	@Resource
	private DropdownListService dropdownListService;

	@RequestMapping(value = "/new_resume.html")
	public ModelAndView indexPage() {

		// logger.debug("TEST");
		ModelAndView mv = this.createLayoutView("resume/new_resume.html");
		// userService.getUserById(id);
		// mv.addObject("user", userService.getUserById("011914"));
		// User user =userService.getUserById("011914");
		return mv;

	}

	@RequestMapping(value = "micro-resume.html")
	public ModelAndView initresumeNull(HttpServletRequest request,
			HttpServletResponse response) {
		String state = (String) request.getParameter("state");
		String code = (String) request.getParameter("code");
		System.out.println("-------------code--------" + code);
		HttpSession session = request.getSession();
		String currentOpenId = (String) session.getAttribute("currentOpenId");
		ModelAndView mv = new ModelAndView();
		System.out.println("----------session OpenId---------------"
				+ currentOpenId);
		if (currentOpenId == null || currentOpenId.equals("")) {
			currentOpenId = WeixinUtil.getOpenidByOauth(JobAppConfig.APP_ID,
					JobAppConfig.APP_SECRET, code);
			System.out.println("----------------OpenId --------"
					+ currentOpenId);
			session.setAttribute("currentOpenId", currentOpenId);
		}

		if (currentOpenId != null && currentOpenId != "") {
			mv = this.createLayoutView("resume/micro-resume.html");
			mv.addObject("ScoreList",
					dropdownListService.getDropListByType("SCHOOL_SCORE"));
			mv.addObject("EducationList",
					dropdownListService.getDropListByType("EDUCATION"));
			mv.addObject("EgScoreList",
					dropdownListService.getDropListByType("ENGLISHLVL"));
			Resume resume = resumeService.getResumeByOpenId(currentOpenId);
			if (resume != null) {
				mv.addObject("PAGETYPE", "EDIT");
				mv.addObject("RESUME", resume);
				mv.addObject("READONLY", " readonly='true' ");
				mv.addObject("DISABLED", " disabled='true' ");
				mv.addObject("HEADER", "我的简历");

			} else {
				// mv.addObject("CODE", code);
				mv.addObject("PAGETYPE", "CREATE");
				// mv.addObject("RESUME", resume);
				mv.addObject("HEADER", "新建简历");
			}
		}
		return mv;
	}

	@RequestMapping(value = "submitResume")
	@ResponseBody
	public Map<String, Object> initresume(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		System.out.print(params.toString());
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String currentOpenId = StringUtil.formatString(session
				.getAttribute("currentOpenId"));

		if (currentOpenId == null) {
			result.put("success", false);
			result.put("message", "currentOpenId in session is null");
			return result;
		}
		String pageType = StringUtil.formatString(params.get("pagetype"));
		Resume resume = new Resume();
		resume.setrOpenId(currentOpenId);
		resume.setrName(StringUtil.formatString(params.get("name")));
		resume.setrGender(StringUtil.formatString(params.get("gender")));
		resume.setrNativePlace(StringUtil.formatString(params
				.get("native_place")));
		resume.setrPhoneNum(StringUtil.formatString(params.get("phone_num")));
		resume.setrEmail(StringUtil.formatString(params.get("email")));
		resume.setrEducation(StringUtil.formatString(params.get("education")));
		resume.setrBachelorSchool(StringUtil.formatString(params
				.get("bachelor_school")));
		resume.setrBachelorSubject(StringUtil.formatString(params
				.get("bachelor_subject")));
		resume.setrMasterSchool(StringUtil.formatString(params
				.get("master_school")));
		resume.setrMasterSubject(StringUtil.formatString(params
				.get("master_subject")));
		resume.setrDoctorSchool(StringUtil.formatString(params
				.get("doctor_school")));
		resume.setrDoctorSubject(StringUtil.formatString(params
				.get("doctor_subject")));
		resume.setrEnglishLVL(StringUtil.formatString(params.get("english_lvl")));
		resume.setrEnglishScore(StringUtil.formatString(params
				.get("english_score")));
		resume.setrScore(StringUtil.formatString(params.get("score")));
		resume.setrOthers(StringUtil.formatString(params.get("others")));

		if (pageType.equals("create")) {
			resumeService.insertResume(resume);
		} else if (pageType.equals("edit")) {
			resumeService.updateResume(resume);
		}
		result.put("success", true);
		result.put("message", "提交成功");
		return result;
	}
}
