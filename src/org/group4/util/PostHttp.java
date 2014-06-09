package org.group4.util;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class PostHttp {
	public static PostMethod post;
	public static Cookie[] cookies;
	static HttpClient client;

	// 登录，返回是否登录
	public static boolean loginAcm(String user, String pass) {
		final String LOGON_SITE = "http://acm.hdu.edu.cn/";
		final int LOGON_PORT = 80;
		client = new HttpClient();
		client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);

		post = new PostMethod(
				"http://acm.hdu.edu.cn/userloginex.php?action=login");
		NameValuePair username = new NameValuePair("username", user);
		NameValuePair password = new NameValuePair("userpass", pass);
		post.setRequestBody(new NameValuePair[] { username, password });
		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cookies = client.getState().getCookies();
		client.getState().addCookies(cookies);
		post.releaseConnection();

		// 验证是否登录成功
		String newUrl = "http://acm.hdu.edu.cn/";
		GetMethod get = new GetMethod(newUrl);
		get.setRequestHeader("Cookie", cookies.toString());
		get.setFollowRedirects(true);
		String result = null;
		StringBuffer resultBuffer = new StringBuffer();
		try {
			client.executeMethod(get);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					get.getResponseBodyAsStream(), get.getResponseCharSet()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				resultBuffer.append(inputLine);
				resultBuffer.append("\n");
			}
			in.close();
			result = resultBuffer.toString();
			result = GetHtml.ConverterStringCode(resultBuffer.toString(),
					get.getResponseCharSet(), "GBK");
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		} finally {
			get.releaseConnection();
		}
		String userRegex = "<img alt=\"Author\" src=\"/images/user.png\" border=0 height=18 width=18>(.{1,10})</a>";
		Pattern userPattern = Pattern.compile(userRegex);
		Matcher userMatcher = userPattern.matcher(result);
		if (userMatcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	// 提交，返回提交结果
	public static String submit(String username, String check,
			String problemid, String language, String usercode) {
		if (usercode.length() <= 50)
			return "submit fail";
		post = new PostMethod("http://acm.hdu.edu.cn/submit.php?action=submit");
		post.setRequestHeader("Cookie", cookies.toString());
		NameValuePair checkForm = new NameValuePair("check", check);
		NameValuePair problemidForm = new NameValuePair("problemid", problemid);
		NameValuePair languageForm = new NameValuePair("language", language);
		NameValuePair codeForm = new NameValuePair("usercode", usercode);
		post.setRequestBody(new NameValuePair[] { checkForm, problemidForm,
				languageForm, codeForm });
		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String newUrl = "http://acm.hdu.edu.cn/status.php?user=" + username;
		String status = null;
		String statusRegex = "<font color=.{3,8}>(.{5,50})</font>";
		Pattern statusPattern = Pattern.compile(statusRegex);
		Matcher statusMatcher = null;
		do {
			// 延时2秒
			try {
				Thread.currentThread().sleep(1000);
			} catch (Exception e) {
			}
			GetMethod get = new GetMethod(newUrl);
			get.setRequestHeader("Cookie", cookies.toString());
			get.setFollowRedirects(true);
			String result = null;
			StringBuffer resultBuffer = new StringBuffer();
			try {
				client.executeMethod(get);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(get.getResponseBodyAsStream(),
								get.getResponseCharSet()));
				String inputLine = null;
				while ((inputLine = in.readLine()) != null) {
					resultBuffer.append(inputLine);
					resultBuffer.append("\n");
				}
				in.close();
				result = resultBuffer.toString();
				result = GetHtml.ConverterStringCode(resultBuffer.toString(),
						get.getResponseCharSet(), "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
				result = "";
			} finally {
				get.releaseConnection();
			}

			statusMatcher = statusPattern.matcher(result);

			if (statusMatcher.find()) {
				status = statusMatcher.group(1);
			} else {
				status = "submit fail";
				
			}
			if(!status.equals("Queuing"))
                        {
                            System.out.println(status);
                            System.out.println("no");
                        }
		}while (status.equals("Queuing")||status.equals("Running")||status.equals("Compiling"));
		return status;
	}

}