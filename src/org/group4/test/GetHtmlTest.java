package org.group4.test;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.group4.util.GetHtml;
import org.junit.Test;

public class GetHtmlTest {

	@Test
	public void testGetGetResponseWithHttpClient() {
		String url="http://acm.hdu.edu.cn/userstatus.php?user=stupidwho";
    	String inputHtml=GetHtml.getGetResponseWithHttpClient(url,"GBK");
    	String userRegex="registered on 2011-10-25";
    	Pattern userPattern=Pattern.compile(userRegex);
    	Matcher userMatcher=userPattern.matcher(inputHtml);	
    	assertTrue(userMatcher.find());
	}

}
