package org.group4.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.group4.util.HttpUtil;
import org.group4.util.User;
import org.junit.Test;

public class HttpUtilTest {	
	@Test
	public void testJudgeID(){
		HttpUtil.setUsername("stupidwhos");//stupidwhos是一个不存在的账号
		assertFalse(HttpUtil.judgeID());
		HttpUtil.setUsername("stupidwho");//stupidwho是一个存在的账号
		assertTrue(HttpUtil.judgeID());
		
	}
	@Test
	public void testAnalyseUserBaseInfo(){
		try {
			User user=HttpUtil.analyseUserBaseInfo("tomorrows");
			assertEquals(user.submitted,62);
			assertEquals(user.solved,62);
			assertEquals(user.submissions,127);
			assertEquals(user.accepted,70);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	@Test
	public void testAnalyseUserProByID(){
		try {
			HttpUtil.analyseUserProByID("tomorrows");
			BufferedReader bufR=new BufferedReader(
					new InputStreamReader(
							new FileInputStream(
									new File("tomorrows_proList.txt"))));
			String line=bufR.readLine();
			assertEquals(line,"1000	1	2");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	@Test
	public void testAnalyseUserProByTime(){
		try {
			HttpUtil.analyseUserProByTime("tomorrows");
			BufferedReader bufR=new BufferedReader(
					new InputStreamReader(
							new FileInputStream(
									new File("tomorrows_problems.txt"))));
			String line=bufR.readLine();
			assertEquals(line,"1096	2013-03-19 00:05:03	Accepted");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
