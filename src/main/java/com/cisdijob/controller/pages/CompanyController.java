package com.cisdijob.controller.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.cisdijob.controller.common.BaseController;

@Controller
@RequestMapping(value = "cominfo")
public class CompanyController  extends BaseController{

	@RequestMapping(value = "comintr.html")
	public ModelAndView comintrcominfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cominfo/comintr");
		return mv;
	}
	
	@RequestMapping(value = "combat.html")
	public ModelAndView combatcominfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cominfo/combat");
		return mv;
	}
	
	@RequestMapping(value = "comcult.html")
	public ModelAndView comcultcominfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cominfo/comcult");
		return mv;
	}
	
	@RequestMapping(value = "comenvir.html")
	public ModelAndView comenvircominfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cominfo/comenvir");
		return mv;
	}
}


