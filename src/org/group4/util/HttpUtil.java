package org.group4.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtil extends Thread{
	//读取用户数据时，主要时间花费在读取用户提交每道题的提交时间
	public static int count;//当前读取记录数
	public static int up;//等于Submissions
	
    static String username;
    public static void setUsername(String user){
	username=user;
    }
    public static boolean judgeID(){
    	String url="http://acm.hdu.edu.cn/userstatus.php?user="+username;
    	String inputHtml=GetHtml.getGetResponseWithHttpClient(url,"GBK");
    	String userRegex="<DIV>No such user.";
    	Pattern userPattern=Pattern.compile(userRegex);
    	Matcher userMatcher=userPattern.matcher(inputHtml);	
    	if(userMatcher.find())
    	{
    		return false;
    	}else{
    		return true;
    	}

	  
	}		
    public void run(){		
        try {
            analyseUserInfoFromHtml();
            sleep(1000);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }	
    }
	
	//获取用户的所有信息
	public static void analyseUserInfoFromHtml() throws IOException{
		analyseUserBaseInfo(username);//获取用户的rank以及submitted, solved, submissions, accepted的题目数
		analyseUserProByTime(username);//获取用户按时间顺序做题的信息，包括题目ID，提交时间，提交状态
		analyseUserProByID(username);//获取用户按题目ID顺序做题的信息，包括题目ID，提交次数，Accepted次数	
	}
	//获取用户按时间顺序做题的信息，包括题目ID，提交时间，提交状态
	public static void analyseUserProByTime(String username) throws IOException{
		
		BufferedWriter bufWrite=new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream(
								new File(username+"_problems.txt"))));
	
		String timeRegex="(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})";//匹配提交时间
		String statusRegex="<font color=.{3,8}>(.{5,50})</font>";//匹配题目状态
		String proIDRegex="pid=(\\d{4})";//匹配题目ID
		String nextRegex="href=\"(.{0,200})\">Next Page";//匹配下一页的URL
		
		Pattern timePattern=Pattern.compile(timeRegex);
		Pattern statusPattern=Pattern.compile(statusRegex);
		Pattern proIDPattern=Pattern.compile(proIDRegex);
		Pattern nextPattern=Pattern.compile(nextRegex);
		
		Matcher timeMatcher=null;
		Matcher statusMatcher=null;
		Matcher proIDMatcher=null;
		Matcher nextMatcher=null;
		
		count=0;//提交题目记录数
		String inputHtml=null;//存放html网页的内容
		String nextUrl=null;//下一页的URL
		String urlForLoop=null;
		boolean nextflag=true;//是否有下一页
		boolean firstflag=true;//是否是第一页，因为第一页的URL和其他页的不一样	
		
		while(nextflag){
			nextflag=false;
			if(firstflag){
				urlForLoop="http://acm.hdu.edu.cn/status.php?user="+username;
				firstflag=false;
			}else{
				urlForLoop="http://acm.hdu.edu.cn"+nextUrl;
			}
			inputHtml=GetHtml.getGetResponseWithHttpClient(urlForLoop,"GBK");

			timeMatcher=timePattern.matcher(inputHtml);
			statusMatcher=statusPattern.matcher(inputHtml);
			proIDMatcher=proIDPattern.matcher(inputHtml);
			nextMatcher=nextPattern.matcher(inputHtml);
			
			while(timeMatcher.find()&&proIDMatcher.find()&&statusMatcher.find()){
				count++;
				bufWrite.write(proIDMatcher.group(1) +"\t"+timeMatcher.group(1)+"\t"+statusMatcher.group(1));
				bufWrite.newLine();
			}
			while(nextMatcher.find()){
				nextUrl=nextMatcher.group(1);
				nextflag=true;
			}
			bufWrite.flush();
		}		
		bufWrite.close();
	}
	//获取用户按题目ID顺序做题的信息，包括题目ID，提交次数，Accepted次数
	public static void analyseUserProByID(String username) throws IOException{
		BufferedWriter bufWrite=new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream(
								new File(username+"_proList.txt"))));
		String url="http://acm.hdu.edu.cn/userstatus.php?user="+username;
		String inputHtml=GetHtml.getGetResponseWithHttpClient(url,"GBK");
		String proRegex="(\\d+),(\\d+),(\\d+)";
		Pattern proPattern=Pattern.compile(proRegex);
		Matcher proMatcher=proPattern.matcher(inputHtml);
		while(proMatcher.find()){
			bufWrite.write(proMatcher.group(1)+"\t"+proMatcher.group(2)+"\t"+proMatcher.group(3));
			bufWrite.newLine();		
		}
		bufWrite.close();
	}
	//获取用户的rank以及submitted, solved, submissions, accepted的题目数
	public static User analyseUserBaseInfo(String username) throws IOException{
		String url="http://acm.hdu.edu.cn/userstatus.php?user="+username;
		String inputHtml=GetHtml.getGetResponseWithHttpClient(url,"GBK");
		String userInfoRegex="<tr><td>Rank</td><td align=center>(\\d+)</td></tr>\\s+<tr><td>Problems Submitted</td><td align=center>(\\d+)</td></tr>\\s+<tr><td>Problems Solved</td><td align=center>(\\d+)</td></tr>\\s+<tr><td>Submissions</td><td align=center>(\\d+)</td></tr>\\s+<tr><td>Accepted</td><td align=center>(\\d+)</td></tr>";
		Pattern userInfoPattern=Pattern.compile(userInfoRegex);
		Matcher userInfoMatcher=userInfoPattern.matcher(inputHtml);
		User user=new User();
		if(userInfoMatcher.find())
		{
			int rank=Integer.parseInt(userInfoMatcher.group(1));
			int submitted=Integer.parseInt(userInfoMatcher.group(2));
			int solved=Integer.parseInt(userInfoMatcher.group(3));
			int submissions=Integer.parseInt(userInfoMatcher.group(4));
			int accepted=Integer.parseInt(userInfoMatcher.group(5));
			user.setBaseInfo(username, rank, submitted, solved, submissions, accepted);
			up=user.submissions;
			
		}
		return user;
		
	}

}

