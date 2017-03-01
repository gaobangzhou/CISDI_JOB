package com.cisdijob.tools;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisdijob.config.WeixinUrlConfig;
import com.cisdijob.model.base.AccessToken;

public abstract class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	/**
	 * 获取菜单信息
	 * 
	 * @param messagestr
	 *            消息信息
	 * @return
	 */
	public static String getWeChatMenuinfo(String openid, AccessToken at) {
		// AccessToken at = getAccessToken(JobAppConfig.APP_ID,
		// JobAppConfig.APP_SECRET);
		// System.out.println("Token："+at.getToken());
		String requestUrl = WeixinUrlConfig.GET_MENU.replace("ACCESS_TOKEN",
				at.getToken()).replace("OPENID", openid);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", "");
		return jsonObject.toString();
	}

	/**
	 * 获取用户微信昵称
	 * 
	 * @param messagestr
	 *            消息信息
	 * @return
	 */
	public static String getWeChatUserinfo(String openid, AccessToken at) {
		// AccessToken at = getAccessToken(JobAppConfig.APP_ID,
		// JobAppConfig.APP_SECRET);
		// System.out.println("Token："+at.getToken());
		String requestUrl = WeixinUrlConfig.GET_USERINFO.replace(
				"ACCESS_TOKEN", at.getToken()).replace("OPENID", openid);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", "");
		String message = "";
		// 如果请求成功
		if (null != jsonObject) {
			try {
				message = jsonObject.getString("nickname");// 获取昵称名称
			} catch (JSONException e) {
			}
		}
		return message;
	}

	/**
	 * 对用户发送模板消息
	 * 
	 * @param messagestr
	 *            消息信息
	 * @return
	 */
	public static String SendTempleatMessage(String messagestr, AccessToken at) {
		// 以前的刷新方案
		// AccessToken at = getAccessToken(JobAppConfig.APP_ID,
		// JobAppConfig.APP_SECRET);
		System.out.println("Token：" + at.getToken());
		String requestUrl = WeixinUrlConfig.SEND_TEMPLATE_MESSAGE.replace(
				"ACCESS_TOKEN", at.getToken());

		// String requestUrl = send_templeat_message_url.replace("ACCESS_TOKEN",
		// WeChatConfig.getToken());
		// String requestUrl = send_templeat_message_url.replace("ACCESS_TOKEN",
		// "y0I6icxPxfCePRjrgig2S-FzdJ9HAwVBJom3nkCMNLSEWjn6-xBnBrA8rKrRUTIp6Z55OatKCc44-s5-uUxzqNf0UCeM8flulJ0lUEmJzCI");
		JSONObject jsonObject = httpRequest(requestUrl, "POST", messagestr);
		String errcode = "";
		// 40001
		// 如果请求成功
		if (null != jsonObject) {
			try {
				errcode = jsonObject.getString("errcode");
				System.out.println(errcode);
			} catch (JSONException e) {
			}
		}
		return errcode;
	}

	/**
	 * 对用户发送客户消息
	 * 
	 * @param messagestr
	 *            消息信息
	 * @return
	 */
	public static String SendCustomMessage(String messagestr, AccessToken at) {
		// AccessToken at = getAccessToken(JobAppConfig.APP_ID,
		// JobAppConfig.APP_SECRET);
		System.out.println("Token：" + at.getToken());
		String requestUrl = WeixinUrlConfig.SEND_CUSTOMEMESSAGE.replace(
				"ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = httpRequest(requestUrl, "POST", messagestr);
		String errcode = "";
		// 如果请求成功
		if (null != jsonObject) {
			try {
				errcode = jsonObject.getString("errcode");
			} catch (JSONException e) {
			}
		}
		return errcode;
	}

	/**
	 * 对用户发送消息
	 * 
	 * @param messagestr
	 *            消息信息
	 * @return
	 */
	public static String SendMessage(String messagestr, AccessToken at) {
		// AccessToken at = getAccessToken(JobAppConfig.APP_ID,
		// JobAppConfig.APP_SECRET);
		String requestUrl = WeixinUrlConfig.SEND_MESSAGE.replace(
				"ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = httpRequest(requestUrl, "POST", messagestr);
		String errcode = "";
		// 如果请求成功
		if (null != jsonObject) {
			try {
				errcode = jsonObject.getString("errcode");
				System.out.println("腾讯返回的代码：" + errcode);
			} catch (JSONException e) {
			}
		}
		return errcode;
	}

	/**
	 * 对用户发送消息
	 * 
	 * @param messagestr
	 *            消息信息
	 * @return
	 */
	public static String SendAllMessage(String messagestr, AccessToken at) {
		// AccessToken at = getAccessToken(JobAppConfig.APP_ID,
		// JobAppConfig.APP_SECRET);
		String requestUrl = WeixinUrlConfig.SEND_ALL_MESSAGE.replace(
				"ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = httpRequest(requestUrl, "POST", messagestr);
		String errcode = "";
		// 如果请求成功
		if (null != jsonObject) {
			try {
				errcode = jsonObject.getString("errcode");
				System.out.println("腾讯返回的代码：" + errcode);
			} catch (JSONException e) {
			}
		}
		return errcode;
	}

	/**
	 * 通过WAP网获取用户openid
	 * 
	 * @param qrcodeStr
	 *            二维码参数值
	 * @param access_token
	 *            access_token
	 * @return
	 */
	public static String getOpenidByOauth(String appid, String secret,
			String code) {
		String requestUrl = WeixinUrlConfig.OAUTH2_GETOPENIDINFO
				.replace("APPID", appid).replace("APPSECRET", secret)
				.replace("CODE", code);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", "");
		String openid = "";
		// 如果请求成功
		if (null != jsonObject) {
			try {
				openid = jsonObject.getString("openid");
			} catch (JSONException e) {

			}
		}
		return openid;
	}

	/**
	 * 创建二维码ticket
	 * 
	 * @param qrcodeStr
	 *            二维码参数值
	 * @param access_token
	 *            access_token
	 * @return
	 */
	public static String Qrcode_getticket(String qrcodeStr, String access_token) {
		String requestUrl = WeixinUrlConfig.QRCODE_GETTICKET.replace(
				"ACCESS_TOKEN", access_token);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", qrcodeStr);
		String ticket = "";
		// 如果请求成功
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
			} catch (JSONException e) {
				// 获取token失败
				log.error("创建二维码ticket errcode:{} errmsg:{}",
						jsonObject.getString("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return ticket;
	}

	/**
	 * 从微信获取新的access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 */
	public static AccessToken getNewAccessToken(String appid, String appsecret) {
		AccessToken accessToken = new AccessToken();
		Date date = new Date();
		String url = WeixinUrlConfig.ACCESS_TOKEN.replace("APPID", appid)
				.replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(url, "GET", "");
		String token = jsonObject.getString("access_token");
		int expiresin = jsonObject.getInt("expires_in");
		if (token != null) {
			accessToken.setAppId(appid);
			accessToken.setToken(token);
			accessToken.setCreateTime(date.getTime());
			accessToken.setExpiresIn(expiresin);
		}

		return accessToken;
	}

	/**
	 * 创建菜单服务
	 * 
	 * @param jsonstr
	 *            菜单str
	 * @param access_token
	 *            access_token
	 * @return
	 */
	public static String createMenu(String jsonstr, String access_token) {
		String requestUrl = WeixinUrlConfig.MENU_CREATE.replace("ACCESS_TOKEN",
				access_token);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonstr);
		String errmsg = "";
		// 如果请求成功
		if (null != jsonObject) {
			try {
				errmsg = jsonObject.getString("errcode");
				System.out.println(errmsg);
			} catch (JSONException e) {
				// 获取token失败
				log.error("创建菜单失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return errmsg;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			System.out.println("output:  " + buffer.toString());
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println(ce.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return jsonObject;
	}

	/**
	 * 上传图文消息到微信服务器
	 * 
	 * @param messagestr
	 *            消息信息
	 * @return
	 */
	public static String uploadNews(String newsStr, AccessToken at) {
		// AccessToken at = getAccessToken(JobAppConfig.APP_ID,
		// JobAppConfig.APP_SECRET);
		// System.out.println("Token："+at.getToken());
		String requestUrl = WeixinUrlConfig.SEND_ARTICLE.replace(
				"ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = httpRequest(requestUrl, "POST", newsStr);
		return jsonObject.toString();
	}

	/**
	 * 文件上传到微信服务器
	 * 
	 * @param fileType
	 *            文件类型
	 * @param filePath
	 *            文件路径
	 * @return JSONObject
	 * @throws Exception
	 */
	public static JSONObject uploadMedia(String token, String fileType,
			String filePath) throws Exception {
		String result = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		String url = WeixinUrlConfig.UPLOAD_MATERIAL.replace("ACCESS_TOKEN",
				token).replace("TYPE", fileType);
		// URL urlObj = new
		// URL("<A href=\"http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+Access_token+" target=_blank>http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+
		// Access_token+"</A>" + "&type="+fileType");

		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);
		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"media\";filename=\""
				+ file.getName() + "\";filelength=\"" + file.getTotalSpace()
				+ "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);
		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(result);
		return jsonObj;
	}

	public static JSONObject upload(String token, String fileType,
			String filePath) throws Exception {
		String result = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		String url = WeixinUrlConfig.UPLOAD_MEDIA
				.replace("ACCESS_TOKEN", token).replace("TYPE", fileType);
		// URL urlObj = new
		// URL("<A href=\"http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+Access_token+" target=_blank>http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+
		// Access_token+"</A>" + "&type="+fileType");

		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);
		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"media\";filename=\""
				+ file.getName() + "\";filelength=\"" + file.getTotalSpace()
				+ "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);
		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(result);
		return jsonObj;
	}

	private static String getFileEndWitsh(String contentType) {

		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		return fileEndWitsh;
	}

	public static void main(String[] args) {

		String testAppId = "wxf3599c120642cf27";
		String testSecret = "f61e3afb9b840d498ee41c3d3f33539b";
		// String testOpenId = "oxSyKwTtvEyDDoBnxLsfpWnK7kCY";
		String token = "pR82Kr10liw72IN7_4knh4Ced2jQOUAd7QhwFs7TEPBX9VTOrDDZ-EVIqv9ZLH76Xe4gt8oNdCqf5bVQB5M7848Ou2jS36cArY2lKMmEAYOGqCp_J21bGlcR6YV6dxSaVJLdAAAYMO";
		// String media_id = "L4nvGhvEz1zfe5vFGOKaZAvi_qGV0xPc0N7pBWmHVrU";
		// String media_url =
		// "https://mmbiz.qlogo.cn/mmbiz/XdTkwF1zpXMPiaoTNQ3c6nXRpMtrClWpBBXVBibYuib1ddZEvjtqE2hk3648vHIcvtHapIs8ygL1UWL0d1lQ9bgTA/0?wx_fmt=jpeg";
		// String url = WeixinUrlConfig.ACCESS_TOKEN.replace("APPID", testAppId)
		// .replace("APPSECRET", testSecret);
		// JSONObject jsonObject = httpRequest(url, "GET", "");
		//
		// String token = jsonObject.getString("access_token");
		//
		// // String url = WeixinUrlConfig.GET_USERINFO.replace("ACCESS_TOKEN",
		// //token).replace("OPENID",testOpenId);
		// // JSONObject jsonObject =httpRequest(url, "GET", "");

		// String filePath = "d:/ecm_temp/micronews1.jpg";
		// try {
		// uploadMedia(token, "image", filePath);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out
		// .println(String
		// .format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n",
		// ".jpg"));
		//
		// String articles = "{\"articles\": [{"
		// +
		// "\"thumb_media_id\":\"gZ0Bze6d1PBQdq6vRNdKnC560Z1TQKxiKetuZ_cXRtQ\","
		// + "\"author\":\"gbz\","
		// + "\"title\":\"我只是一个乞求者\","
		// + " \"content_source_url\":\"\","
		// + " \"content\":\"28394ihoirhtierg24324\","
		// + "\"digest\":\"digest\","
		// + " \"show_cover_pic\":\"1\""
		// + "},"
		// + "{"
		// +
		// "\"thumb_media_id\":\"gZ0Bze6d1PBQdq6vRNdKnC560Z1TQKxiKetuZ_cXRtQ\","
		// + "\"author\":\"gbz\"," + " \"title\":\"Happy Day\","
		// + " \"content_source_url\":\"\","
		// + " \"content\":\"12334546tutyuywerew\","
		// + " \"digest\":\"digest\"," + "\"show_cover_pic\":\"0\""
		// + " }]}";
		// String ul = WeixinUrlConfig.SEND_ARTICLE.replace("ACCESS_TOKEN",
		// token);
		// JSONObject jsonObject1 = httpRequest(ul, "POST", articles);
		// // String newsMediaId =
		// "gZ0Bze6d1PBQdq6vRNdKnDYlrXv2zPLi-a7OgJ9wR0U";

		// String ul = WeixinUrlConfig.SEND_NEWS2USERS.replace("ACCESS_TOKEN",
		// token);
		// String users =
		// "{ \"touser\":[\"oxSyKwa8LQgu8s1it4hePlvnQtWw\",\"oxSyKwTtvEyDDoBnxLsfpWnK7kCY\"],"
		// +
		// "\"mpnews\":{\"media_id\":\"gZ0Bze6d1PBQdq6vRNdKnDYlrXv2zPLi-a7OgJ9wR0U\""
		// + " },\"msgtype\":\"mpnews\"}";
		// JSONObject jsonObject2 = httpRequest(ul, "POST", users);

		String ul = WeixinUrlConfig.SEND_NEWS2USERS.replace("ACCESS_TOKEN",
				token);
		String txtMessage = "{\"touser\":[\"oxSyKwTtvEyDDoBnxLsfpWnK7kCY\", \"oxSyKwa8LQgu8s1it4hePlvnQtWw\"], \"msgtype\": \"text\",\"text\": { \"content\": \"helloworld!\"}}";

		JSONObject jsonObject2 = httpRequest(ul, "POST", txtMessage);
		// String menuUrl =WeixinUrlConfig.MENU_CREATE.replace("ACCESS_TOKEN",
		// token);
		// String menuStr="";
		// try {
		// menuStr = WeixinMenuUtil.initMenuStr();
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(menuStr);
		// JSONObject jsonObject1 = httpRequest(menuUrl, "POST", menuStr);
		// getUserSummary("2015-09-01","2015-09-05",token);

	}

	public static Integer getUserSummary(String token) {
		String SummaryUrl = WeixinUrlConfig.GET_USER_SUMMARY.replace(
				"ACCESS_TOKEN", token);
		JSONObject jsonObject1;
		Integer totalNumber = 0;
		try {
			jsonObject1 = httpRequest(SummaryUrl, "GET", "");
			totalNumber = jsonObject1.getInt("total");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return totalNumber;
	}
}