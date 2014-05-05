package org.group4.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.group4.util.FileUtil;
import org.junit.Test;

public class FileUtilTest {
	private static FileUtil fileUtil = new FileUtil();
	
	@Test
	public void testGetAllTypes() {
		Vector<String> typeTemp = new Vector<String>(); 
		typeTemp = fileUtil.getAllTypes("testTemp.txt");
		Vector<String> typeFlat = new Vector<String>(); 
		typeFlat.add("type1");
		typeFlat.add("type2");
		assertEquals(typeFlat,typeTemp);
	}
	
	@Test
	public void testgetProbsOfType() {
		String[] probs = new String[2];
		probs = fileUtil.getProbsOfType("testTemp.txt", "type1");
		String[] probTemp = new String[2];
		probTemp[0] = "1000";
		probTemp[1] = "1001";
		assertArrayEquals(probTemp, probs);
	}
	@Test
	public void testappendProbs() {
		Vector<String> probs = new Vector<String>();
		probs.add("1004");
		probs.add("1005");
		Map<String, Vector<String> > map = new HashMap<String, Vector<String> >();
		map.put("type3", probs);
		fileUtil.appendProbs(map);
		String[] probsTemp = new String[2];
		String[] probsSemp = new String[2];
		probsTemp = fileUtil.getProbsOfType("problemType.txt","type3");
		probsSemp[0] = "1004";
		probsSemp[1] = "1005";
		assertArrayEquals(probsSemp,probsTemp);
	}
	@Test
	public void testgetFirstDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals("2011-11-19",sdf.format(fileUtil.getFirstDate("doubleganger")));
		
	}
	@Test
	public void testgetLastDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals("2014-05-05",sdf.format(fileUtil.getLastDate("doubleganger")));
		
	}
}


