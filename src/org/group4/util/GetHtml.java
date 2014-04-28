package org.group4.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

public class GetHtml{
	//获得ConnectionManager，设置相关参数
	private static MultiThreadedHttpConnectionManager manager=new MultiThreadedHttpConnectionManager();
	private static int connectionTimeOut = 20000;
	private static int socketTimeOut = 10000;
	private static int maxConnectionPerHost = 5;
	private static int maxTotalConnections = 40;
	//标志初始化是否完成的flag
	private static boolean initialed = false;
	//初始化ConnectionManger的方法
	public static void SetPara() {
		manager.getParams().setConnectionTimeout(connectionTimeOut);
		manager.getParams().setSoTimeout(socketTimeOut);
		manager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
		manager.getParams().setMaxTotalConnections(maxTotalConnections);
		initialed = true;
	}
	//通过get方法获取网页内容
	@SuppressWarnings("finally")
	public static String getGetResponseWithHttpClient(String url, String encode) {
		HttpClient client = new HttpClient(manager);
		if (initialed) {
			GetHtml.SetPara();
		}
		GetMethod get = new GetMethod(url);
		get.setFollowRedirects(true);
		String result = null;
		StringBuffer resultBuffer = new StringBuffer();
		try {
			client.executeMethod(get);
			//在目标页面情况未知的条件下，不推荐使用getResponseBodyAsString()方法
			//String strGetResponseBody = post.getResponseBodyAsString();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							get.getResponseBodyAsStream(),
							get.getResponseCharSet()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				resultBuffer.append(inputLine);
				resultBuffer.append("\n");
			}
			in.close();
			result = resultBuffer.toString();
			//iso-8859-1 is the default reading encode
			result = GetHtml.ConverterStringCode(resultBuffer.toString(),
					get.getResponseCharSet(),
					encode);
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		} finally {
			get.releaseConnection();
			return result;
		}
	}
	private static String ConverterStringCode(String src, String srcEncode, String destEncode) {
		if (src != null) {
			try {
				return new String(src.getBytes(srcEncode), destEncode);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}
}



