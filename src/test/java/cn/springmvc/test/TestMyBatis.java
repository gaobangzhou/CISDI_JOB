package cn.springmvc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cisdi.client.bean.Form;
import com.cisdi.client.bean.ResponseBean;
import com.cisdi.client.core.BaseUriUtil;
import com.cisdi.client.datamng.DataMng;
import com.cisdijob.config.JobAppConfig;
import com.cisdijob.config.WeixinEventType;
import com.cisdijob.model.base.AccessToken;
import com.cisdijob.model.entity.LookUpValue;
import com.cisdijob.model.entity.Resume;
import com.cisdijob.model.entity.ResumeResult;
import com.cisdijob.model.entity.SearchPosition;
import com.cisdijob.model.weixin.Article;
import com.cisdijob.model.weixin.SubButton;
import com.cisdijob.model.weixin.WxButton;
import com.cisdijob.model.weixin.WxMenu;
import com.cisdijob.service.common.AccessTokenService;
import com.cisdijob.service.common.CompanyService;
import com.cisdijob.service.pages.AdminUserService;
import com.cisdijob.service.pages.JobService;
import com.cisdijob.service.pages.LookUpValueService;
import com.cisdijob.service.pages.ResumeService;
import com.cisdijob.service.pages.SummaryService;
import com.cisdijob.tools.WeixinUtil;

public class TestMyBatis {
	private AdminUserService userService;
	private AccessTokenService accessTokenService;
	private ResumeService resumeService;
	// private MyApplyService myapplyService;
	private CompanyService companyService;

	private JobService jobService;

	private SummaryService summaryService;
	private LookUpValueService lookUpValueService;

	@Before
	public void before() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:conf/spring.xml",
						"classpath:conf/spring-mybatis.xml" });

		accessTokenService = (AccessTokenService) context
				.getBean("accessTokenService");
		resumeService = (ResumeService) context.getBean("resumeService");
		// myapplyService = (MyApplyService)context.getBean("myapplyService");
		companyService = (CompanyService) context.getBean("companyService");
		jobService = (JobService) context.getBean("jobService");

		summaryService = (SummaryService) context.getBean("summaryService");
	}

	@Test
	public void addAccessToken() {
		AccessToken at = WeixinUtil.getNewAccessToken(JobAppConfig.APP_ID,
				JobAppConfig.APP_SECRET);
		if (at != null) {
			accessTokenService.addAccessToken(at);
		}

	}

	public void updateAccessToken() {
		AccessToken at = WeixinUtil.getNewAccessToken(JobAppConfig.APP_ID,
				JobAppConfig.APP_SECRET);
		if (at != null) {
			accessTokenService.updateAccessToken(at);
		}
	}

	public void getAccessToken() {
		AccessToken at = accessTokenService.getAccessToken(JobAppConfig.APP_ID);
		System.out.println(at);
	}

	/*
	 * @Test public void insertResume(){ Resume resume = new Resume();
	 * resume.setRNAME("gaobangzhou"); resume.setRGENDER("M");
	 * resume.setROPENID("wrwer212312"); resume.setREMAIL("23422342");
	 * resume.setRNATIVE_PLACE("242342"); resumeService.insertResume(resume); }
	 */
	@Test
	public void getRuseme() {

		Resume resume = resumeService
				.getResumeByOpenId("oxSyKwTtvEyDDoBnxLsfpWnK7kCY");
		if (resume != null) {
			System.out.println("success");
		}
	}

	@Test
	public void getType() {
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
				"http://job.cisdi.com.cn/CISDI_JOB/jobinfo/screenings.html");
		SubButton sbt22 = new SubButton(WeixinEventType.EVENT_VIEW, "社会招聘",
				"http://job.cisdi.com.cn/CISDI_JOB/jobinfo/socialRecruitment/position.html");
		List<SubButton> subList2 = new ArrayList<SubButton>();
		subList2.add(sbt21);
		subList2.add(sbt22);

		bt2.setSubButtons(subList2);

		WxButton bt3 = new WxButton();
		bt3.setName("我要招聘");
		SubButton sbt31 = new SubButton(WeixinEventType.EVENT_VIEW, "微简历",
				"http://job.cisdi.com.cn/CISDI_JOB/resume/little_resume.html");
		SubButton sbt32 = new SubButton(WeixinEventType.EVENT_VIEW, "公司文化",
				"http://job.cisdi.com.cn/CISDI_JOB/jobinfo/apply-record.html");

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

		System.out.println(wm.toString());

	}

	@Test
	public void getResumeInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company", "82");
		map.put("status", "");
		List<ResumeResult> jobList = resumeService.getResumeResultByMap(map);
	}

	@Test
	public void getJobs() {
		SearchPosition searchPosition = new SearchPosition();
		searchPosition.setRecType("campus");
		searchPosition.setKeyWord("赛迪");
		// List<Job> jobList = jobService.searchSocietyJobs(searchPosition);
		// List<Job> jobList = jobService.searchCampusJobs(searchPosition);
		// System.out.println(jobList.get(0).getJNAME());
	}

	@Test
	public void insertJob() throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "UI工程师");
		map.put("type", "RESEARCH");
		map.put("place", "CQ");
		map.put("payment", "面议");
		map.put("create_com", "82");
		String d1 = "2015-9-21";
		String d2 = d1.replace("-", "/");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = sf.parse(d2);
		map.put("create_time", date);
		map.put("create_person", "gjp");
		map.put("description", "");
		map.put("capacity", "");
		map.put("recruitment_type", "society");
		map.put("professional", "SOFTWARE");

		jobService.insertJob(map);
	}

	/*
	 * @Test public void updateJob(){
	 * //jobService.updateJob("203CB7579D8EC17FE050007F01007E0E"); }
	 */
	@Test
	public void selectCampusJobs() {
		jobService.selectCampusJobs("jkdkhkkuek");
	}

	@Test
	public void getSummary() {
		List<Map<String, Object>> lm = summaryService.getTopHotJob();
		if (lm != null) {
			System.out.println("23423");
		}
	}

	@Test
	public void getLookUpValue() {
		List<LookUpValue> workPlaceList = lookUpValueService
				.getLookUpValues("1FDA916F215AC48FE050007F01001073");
		// LookUpValue lookupValue = new LookUpValue();
		// lookupValue.setLpv_createPerson("gjp");
		// lookUpValueService.insertWorkPlace(lookupValue);
	}

	@Test
	public void uploadMedia() throws Exception {
		AccessToken accessToken = accessTokenService.getAccessToken(
				JobAppConfig.APP_ID_TEST, JobAppConfig.APP_SECRET_TEST);
		String fileType = "thumb";
		String filePath = "E:\\weixin_image\\test.jpg";
		JSONObject result = WeixinUtil.upload(accessToken.getToken(), fileType,
				filePath);

	}

	@Test
	public void uploadnews() {
		AccessToken accessToken = accessTokenService.getAccessToken(
				JobAppConfig.APP_ID_TEST, JobAppConfig.APP_SECRET_TEST);
		String urlString = "http://mmbiz.qpic.cn/mmbiz_jpg/XdTkwF1zpXMqzDWTRFR3UNKqsL786eu4DTnO4ZdDrv3dL1KJbwibnkibkhDJ5Sztia4wtTcVDh0jAKV93GfGmga8A/0?wx_fmt=jpeg";
		String media_id = "9wVjfszF6RJEPAzVZA9Q7NEPqj4mVTzPwAu9mDXb2VwjbwSMCHLeZtalDtUSzGlL";
		Article article = new Article();
		article.setAuthor("测数数据哦");
		article.setTitle("测试图文消息标题");
		article.setThumb_media_id(media_id);
		article.setContent("测试图文消息内容");
		article.setContent_source_url("http://www.qq.com");
		article.setDigest("测试图文消息的描述信息");
		article.setShow_cover_pic("0");
		JSONArray jsonObject = JSONArray.fromObject(article);
		String ouptString = "{\"articles\":" + jsonObject.toString() + "}";
		System.out.println(ouptString);
		String result = WeixinUtil.uploadNews(ouptString, accessToken);
	}

	@Test
	public void sendNews() {
		AccessToken accessToken = accessTokenService.getAccessToken(
				JobAppConfig.APP_ID_TEST, JobAppConfig.APP_SECRET_TEST);
		String newsIdString = "kFf4djaHwlEEmWLhquLcfLh8JX3ZyUOjdyCnBeBLfr-DfBLr7Z-0wd3rO5PKtABr";
		// String messageStr =
		// "{\"filter\":{\"is_to_all\":true},\"mpnews\":{\"media_id\":\""
		// + newsIdString + "\"},\"msgtype\":\"mpnews\"}";

		String messageStr = "{\"filter\":{\"is_to_all\":true},\"text\":{ \"content\":\"测试消息\"}, \"msgtype\":\"text\"}";
		// JSONObject jsonObject = JSONObject.fromObject(news);
		System.out.println(messageStr);
		String result = WeixinUtil.SendAllMessage(messageStr, accessToken);
		System.out.println(result);
	}

	@Test
	public void sendUserMessage() {
		AccessToken accessToken = accessTokenService.getAccessToken(
				JobAppConfig.APP_ID_TEST, JobAppConfig.APP_SECRET_TEST);
		String newsIdString = "kFf4djaHwlEEmWLhquLcfLh8JX3ZyUOjdyCnBeBLfr-DfBLr7Z-0wd3rO5PKtABr";
		String messageStr = "{\"touser\":[\"oxSyKwTtvEyDDoBnxLsfpWnK7kCY\"],\"mpnews\":{\"media_id\":\""
				+ newsIdString + "\"},\"msgtype\":\"mpnews\"}";
		String result = WeixinUtil.SendMessage(messageStr, accessToken);
	}

	@Test
	public void testEcmAPI() {
		String APIUrl = "http://localhost:8080/APIServer/";
		BaseUriUtil.init(APIUrl);
		Form form = new Form();
		form.add("docNum", "48910001CD7201GE001");
		ResponseBean responseBean = DataMng.getResBean(
				"api/autorun/autoSyncCD", form);
		if (responseBean != null) {
			Map<String, Object> loginResult = responseBean.getData();
		}

		System.out.println(responseBean.toString());
	}
}
