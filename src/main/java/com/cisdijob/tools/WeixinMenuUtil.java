package com.cisdijob.tools;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.cisdijob.config.JobAppConfig;
import com.cisdijob.config.WeixinEventType;
import com.cisdijob.config.WeixinUrlConfig;
import com.cisdijob.model.weixin.SubButton;
import com.cisdijob.model.weixin.WxButton;
import com.cisdijob.model.weixin.WxMenu;



public class WeixinMenuUtil {
	private static String SOCIAL_RECRUITMENT = "http://job.cisdi.com.cn/CISDI_JOB/jobinfo/society/position.html";
	private static String SCREENINGS = "http://job.cisdi.com.cn/CISDI_JOB/jobinfo/screenings.html";
	private static String RESUME = "http://job.cisdi.com.cn/CISDI_JOB/resume/micro-resume.html";
	private static String APPLY ="http://job.cisdi.com.cn/CISDI_JOB/jobinfo/apply-record.html";

	public static String initMenuStr() throws UnsupportedEncodingException {
		String socialUri = java.net.URLEncoder.encode(SOCIAL_RECRUITMENT,
				"UTF-8");
		String screenUri = java.net.URLEncoder.encode(SCREENINGS, "UTF-8");
		String resumeUri = java.net.URLEncoder.encode(RESUME, "UTF-8");
		String applyUri =java.net.URLEncoder.encode(APPLY, "UTF-8");

		WxButton bt1 = new WxButton();
		bt1.setName("公司信息");
		SubButton sbt11 = new SubButton(WeixinEventType.EVENT_VIEW, "公司简介",
				"http://job.cisdi.com.cn/CISDI_JOB/cominfo/comintr.html");
		SubButton sbt12 = new SubButton(WeixinEventType.EVENT_VIEW, "企业文化",
				"http://job.cisdi.com.cn/CISDI_JOB/cominfo/comcult.html");
		SubButton sbt13 = new SubButton(WeixinEventType.EVENT_VIEW, "公司环境",
				"http://job.cisdi.com.cn/CISDI_JOB/cominfo/comenvir.html");
		SubButton sbt14 = new SubButton(WeixinEventType.EVENT_VIEW, "业务技术",
				"http://job.cisdi.com.cn/CISDI_JOB/cominfo/combat.html");
		List<SubButton> subList = new ArrayList<SubButton>();
		subList.add(sbt11);
		subList.add(sbt12);
		subList.add(sbt13);
		subList.add(sbt14);
		bt1.setSubButtons(subList);

		WxButton bt2 = new WxButton();
		bt2.setName("招聘信息");
		SubButton sbt21 = new SubButton(WeixinEventType.EVENT_VIEW, "校园招聘",
				WeixinUrlConfig.OAUTH2_GETWEBCODE.replace("APPID",
						JobAppConfig.APP_ID).replace("REDIRECT_URI", screenUri));
		SubButton sbt22 = new SubButton(WeixinEventType.EVENT_VIEW, "社会招聘", WeixinUrlConfig.OAUTH2_GETWEBCODE.replace("APPID",
				JobAppConfig.APP_ID).replace("REDIRECT_URI", socialUri));
		List<SubButton> subList2 = new ArrayList<SubButton>();
		subList2.add(sbt21);
		subList2.add(sbt22);

		bt2.setSubButtons(subList2);

		WxButton bt3 = new WxButton();
		bt3.setName("我要应聘");
		SubButton sbt31 = new SubButton(WeixinEventType.EVENT_VIEW, "微简历", WeixinUrlConfig.OAUTH2_GETWEBCODE.replace("APPID",
				JobAppConfig.APP_ID).replace("REDIRECT_URI", resumeUri));
		SubButton sbt32 = new SubButton(WeixinEventType.EVENT_VIEW, "应聘记录",
				WeixinUrlConfig.OAUTH2_GETWEBCODE.replace("APPID",
						JobAppConfig.APP_ID).replace("REDIRECT_URI", applyUri));

		List<SubButton> subList3 = new ArrayList<SubButton>();
		subList3.add(sbt31);
		subList3.add(sbt32);

		bt3.setSubButtons(subList3);

		WxMenu wm = new WxMenu();
		List<WxButton> wb = new ArrayList<WxButton>();
		wb.add(bt1);
		wb.add(bt2);
		wb.add(bt3);
		wm.setButtonList(wb);
		return wm.toString();
	}
}
