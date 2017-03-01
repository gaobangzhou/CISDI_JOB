package com.cisdijob.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.controller.common.BaseController;

@Controller
@RequestMapping(value = "rectmanage")
public class RecruitmentController extends BaseController {

	@RequestMapping(value = "recruitment.html")
	public ModelAndView recruitmentPage() {
		ModelAndView view = this.createAdminView("admin/rect-manage.html");
		return view;
	}

}
