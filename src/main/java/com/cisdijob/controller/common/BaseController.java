package com.cisdijob.controller.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.config.JobAppConfig;
import com.cisdijob.model.base.AccessToken;
import com.cisdijob.model.base.User;
import com.cisdijob.service.common.AccessTokenService;
import com.cisdijob.tools.HttpServletUtil;
import com.cisdijob.tools.WeixinUtil;

@Controller
@RequestMapping(value = "view")
public class BaseController {
	@Resource
	private AccessTokenService accessTokenService;

	public ModelAndView createLayoutView(String path) {
		ModelAndView view = new ModelAndView();
		view.setViewName("common/layout");
		view.addObject("content_path", path);

		return view;
	}

	public ModelAndView createAdminView(String path) {
		ModelAndView view = new ModelAndView();
		view.setViewName("common/admin_layout");
		view.addObject("header_path", "common/admin-header.html");
		view.addObject("content_path", path);

		User user = (User) HttpServletUtil.getInstance().getSession()
				.getAttribute("currentUser");
		view.addObject("user", user);

		return view;
	}

	/**
	 * 获取access_token 先从数据库中获取access_token 然后判断该access_token是否过期，如果过期就
	 * 重新从微信获取access_token并更新数据库中的access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public AccessToken getAccessToken() {
		AccessToken accessToken = accessTokenService.getAccessToken(
				JobAppConfig.APP_ID, JobAppConfig.APP_SECRET);
		return accessToken;
	}

	public Map<String, Object> getTotalNumToken() {
		Map<String, Object> map = new HashMap<String, Object>();
		AccessToken accessToken = accessTokenService
				.getAccessToken(JobAppConfig.TOTAL_NUMBER);
		map.put("totalNum", accessToken.getToken());
		map.put("type", "draw");
		map.put("number", "0");
		String token = getAccessToken().getToken();
		Date curDate = new Date();
		long time = curDate.getTime() - accessToken.getCreateTime();
		if (time >= (long) (accessToken.getExpiresIn() * 1000)) {
			Integer total = WeixinUtil.getUserSummary(token);
			if (total == 0) {
				return map;
			}
			map.put("totalNum", total);
			Integer temp = total - Integer.parseInt(accessToken.getToken());
			if (temp > 0) {
				map.put("type", "up");
				map.put("number", temp.toString());
			} else if (temp == 0) {
				map.put("type", "draw");
				map.put("number", temp.toString());
			} else if (temp < 0) {
				map.put("type", "down");
				map.put("number", temp.toString());
			}
			accessToken.setToken(total.toString());
			accessToken.setCreateTime(curDate.getTime());

			try {
				accessTokenService.updateAccessToken(accessToken);
			} catch (Exception e) {
				System.out.println("更新总数失败");
			}

		}

		return map;
	}

	@RequestMapping(value = "index.html")
	public ModelAndView indexPage(HttpServletRequest request) {

		ModelAndView mv = this.createLayoutView("index.html");
		HttpSession session = request.getSession();
		String currentOpenId = "ol6AuwXQbRsABgAXaA4tSIkpG-xo";
		session.setAttribute("currentOpenId", currentOpenId);
		return mv;

	}

	@RequestMapping(value = "/{error}.html")
	public ModelAndView errorpage(@PathVariable("error") String error) {

		ModelAndView view = new ModelAndView();
		view.setViewName("errorpage/error-" + error);
		return view;

	}
}
