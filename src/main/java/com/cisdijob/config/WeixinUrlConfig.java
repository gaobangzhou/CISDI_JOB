package com.cisdijob.config;

public class WeixinUrlConfig {

	// 菜单创建（POST） 限100（次/天）
	public static String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	// 菜单创建（POST） 限100（次/天）
	public static String QRCODE_GETTICKET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";

	// 网站授权
	public static String OAUTH2_GETWEBCODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=success#wechat_redirect";

	// wap网站获取用户openid信息
	public static String OAUTH2_GETOPENIDINFO = "https://api.weixin.qq.com/sns/oauth2/"
			+ "access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";

	// 发送客户消息服务
	public static String SEND_CUSTOMEMESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	// 获取用户基本信息
	public static String GET_USERINFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	// 发送消息
	public static String SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";

	// 全部人员发送消息
	public static String SEND_ALL_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

	// 菜单获取服务
	public static String GET_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 发送模板消息接口
	public static String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	public static String UPLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	// 上传图片
	public static String UPLOAD_IMAGE = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";

	// 上传永久的文件
	public static String UPLOAD_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";

	// 上传图文素材
	public static String SEND_ARTICLE = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";

	// 向一个用户列表发送信息
	public static String SEND_NEWS2USERS = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";

	// 获取 公共号关注 的增减数
	public static String GET_USER_SUMMARY = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN ";

	// 获取公共号关注的总人数
	// public static String GET_USER_CUMULATE
	// ="https://api.weixin.qq.com/datacube/getusersummary?access_token=ACCESS_TOKEN";
}
