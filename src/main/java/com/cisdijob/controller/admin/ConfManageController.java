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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.controller.common.BaseController;
import com.cisdijob.model.entity.LookUpValue;
import com.cisdijob.service.pages.LookUpValueService;
import com.cisdijob.tools.ConfigurationManageAPI;

@Controller
@RequestMapping(value = "confmanage")
public class ConfManageController extends BaseController{
	@Resource
	private LookUpValueService lookupValueService;
	@RequestMapping(value = "/info-manage.html") 
	public ModelAndView InfoPage() {
		
		//logger.debug("TEST");
		 ModelAndView mv = this.createAdminView("admin/info-manage.html");
		// userService.getUserById(id);
		//mv.addObject("user",  userService.getUserById("011914"));
		//User user =userService.getUserById("011914");
		 List<LookUpValue> WorkPlaceList = lookupValueService.getLookUpValues(ConfigurationManageAPI.WORK_PLACE);
		 List<LookUpValue> WorkProfessional = lookupValueService.getLookUpValues(ConfigurationManageAPI.WORK_PROFESSIONAL);
		 List<LookUpValue> WorkType = lookupValueService.getLookUpValues(ConfigurationManageAPI.WORK_TYPE);
		 List<LookUpValue> English_Level = lookupValueService.getLookUpValues(ConfigurationManageAPI.ENGLISH_LEVEL);
		 mv.addObject("WorkPlaceList", WorkPlaceList);
		 mv.addObject("WorkProfessionalList", WorkProfessional);
		 mv.addObject("WorkTypeList", WorkType);
		 mv.addObject("EnglishLevelList", English_Level);
		return mv;

	}
	@RequestMapping(value = "/juris-manage.html") 
	public ModelAndView JurisPage() {
		
		 ModelAndView mv = this.createAdminView("admin/juris-manage.html");
		
		return mv;

	}
	@RequestMapping(value = "/insertConfiguration")
	@ResponseBody
	public ModelAndView getWorkPlaces(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String,Object> parmas){
		String type = parmas.get("type").toString();
		String code = parmas.get("code").toString();
		String name = parmas.get("lupname").toString();	
		LookUpValue lookupValue = new LookUpValue();
		lookupValue.setLpv_code(code);
		lookupValue.setLpv_meaning(name);
		lookupValue.setLpv_createPerson("gjp");
		String lpt_type = "";
		List<LookUpValue> lookupValueList=null;
		if(type.equals(ConfigurationManageAPI.WORK_PLACE)){
			lpt_type = ConfigurationManageAPI.WORK_PLACE;
			lookupValue.setLpt_type(lpt_type);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("lpt_type", lpt_type);
			map.put("lpv_code", code);
			LookUpValue lookupValueTemp = lookupValueService.selectLookupValue(map);
			if(lookupValueTemp==null){
				lookupValueService.insertLookupValue(lookupValue);
				 lookupValueList=lookupValueService.getLookUpValues(ConfigurationManageAPI.WORK_PLACE);
			}	
		}
		/*if(type.equals(ConfigurationManageAPI.WORK_PROFESSIONAL)){
			lpt_type = ConfigurationManageAPI.WORK_PROFESSIONAL;
			lookupValue.setLpt_type(lpt_type);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("lpt_type", lpt_type);
			map.put("lpv_code", code);
			LookUpValue lookupValueTemp = lookupValueService.selectLookupValue(map);
			if(lookupValueTemp==null){
				lookupValueService.insertLookupValue(lookupValue);
				 lookupValueList=lookupValueService.getLookUpValues(ConfigurationManageAPI.WORK_PROFESSIONAL);
			}	
		}*/
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/configuration-manage");
		mv.addObject("ListConf", lookupValueList);
		return mv;
	}
	/**
	 * 专业
	 * @param request
	 * @param response
	 * @param parmas
	 * @return
	 */
	@RequestMapping(value = "/insertProfessional")
	@ResponseBody
	public ModelAndView getProfessional(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String,Object> parmas){
		String type = parmas.get("type").toString();
		String code = parmas.get("code").toString();
		String name = parmas.get("lupname").toString();	
		LookUpValue lookupValue = new LookUpValue();
		lookupValue.setLpv_code(code);
		lookupValue.setLpv_meaning(name);
		lookupValue.setLpv_createPerson("gjp");
		String lpt_type = "";
		List<LookUpValue> lookupValueList=null;
		if(type.equals(ConfigurationManageAPI.WORK_PROFESSIONAL)){
			lpt_type = ConfigurationManageAPI.WORK_PROFESSIONAL;
			lookupValue.setLpt_type(lpt_type);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("lpt_type", lpt_type);
			map.put("lpv_code", code);
			LookUpValue lookupValueTemp = lookupValueService.selectLookupValue(map);
			if(lookupValueTemp==null){
				lookupValueService.insertLookupValue(lookupValue);
				 lookupValueList=lookupValueService.getLookUpValues(ConfigurationManageAPI.WORK_PROFESSIONAL);
			}	
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/configuration-manage");
		mv.addObject("ListConf", lookupValueList);
		return mv;
	}
	//类别
	@RequestMapping(value = "/insertWorkType")
	@ResponseBody
	public ModelAndView getWorkType(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String,Object> parmas){
		String type = parmas.get("type").toString();
		String code = parmas.get("code").toString();
		String name = parmas.get("lupname").toString();	
		LookUpValue lookupValue = new LookUpValue();
		lookupValue.setLpv_code(code);
		lookupValue.setLpv_meaning(name);
		lookupValue.setLpv_createPerson("gjp");
		String lpt_type = "";
		List<LookUpValue> lookupValueList=null;
		if(type.equals(ConfigurationManageAPI.WORK_TYPE)){
			lpt_type = ConfigurationManageAPI.WORK_TYPE;
			lookupValue.setLpt_type(lpt_type);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("lpt_type", lpt_type);
			map.put("lpv_code", code);
			LookUpValue lookupValueTemp = lookupValueService.selectLookupValue(map);
			if(lookupValueTemp==null){
				lookupValueService.insertLookupValue(lookupValue);
				 lookupValueList=lookupValueService.getLookUpValues(ConfigurationManageAPI.WORK_TYPE);
			}	
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/configuration-manage");
		mv.addObject("ListConf", lookupValueList);
		return mv;
	}
	//英语水平
	
	@RequestMapping(value = "/insertEnglishLevel")
	@ResponseBody
	public ModelAndView getEnglishLevel(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String,Object> parmas){
		String type = parmas.get("type").toString();
		String code = parmas.get("code").toString();
		String name = parmas.get("lupname").toString();	
		LookUpValue lookupValue = new LookUpValue();
		lookupValue.setLpv_code(code);
		lookupValue.setLpv_meaning(name);
		lookupValue.setLpv_createPerson("gjp");
		String lpt_type = "";
		List<LookUpValue> lookupValueList=null;
		if(type.equals(ConfigurationManageAPI.ENGLISH_LEVEL)){
			lpt_type = ConfigurationManageAPI.ENGLISH_LEVEL;
			lookupValue.setLpt_type(lpt_type);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("lpt_type", lpt_type);
			map.put("lpv_code", code);
			LookUpValue lookupValueTemp = lookupValueService.selectLookupValue(map);
			if(lookupValueTemp==null){
				lookupValueService.insertLookupValue(lookupValue);
				 lookupValueList=lookupValueService.getLookUpValues(ConfigurationManageAPI.ENGLISH_LEVEL);
			}	
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/configuration-manage");
		mv.addObject("ListConf", lookupValueList);
		return mv;
	}
}
