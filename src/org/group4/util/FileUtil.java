/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kam
 */
public class FileUtil {
	
    public static Vector<String> getAllTypes(String path){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String s;
            Vector<String> ret = new Vector<>();
            while((s = br.readLine()) != null){
            	int k = s.indexOf(":");
                ret.add(s.substring(0, k));
            }
            br.close();
            return ret;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
        }
        return null;
    }
    
    public static String[] getProbsOfType(String path, String type){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String s, probs[] = null;
            int k;
            while((s = br.readLine()) != null){
                k = s.indexOf(':');
                if(s.substring(0, k).equals(type)){
                    probs = s.substring(k + 1).split(",");
                    break;
                }
            }
            br.close();
            return probs;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
        }
        return null;
    }
    public static void appendProbs(Map<String, Vector<String> > map){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("problemType.txt"), "UTF-8"));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("temp.txt"), "UTF-8"));
            String s;
            int k;
            while((s = br.readLine()) != null){
                bw.write(s);
                k = s.indexOf(':');
                if(map.containsKey(s.substring(0, k))){
                    for(String prob : map.get(s.substring(0, k))){
                        bw.write("," + prob);
                    }
                    map.remove(s.substring(0, k));
                }
                bw.newLine();
            }
            for(String key : map.keySet()){
                bw.write(key + ":");
                boolean first = true;
                for(String prob : map.get(key)){
                    if(!first)
                        bw.write(",");
                    else
                        first = false;
                    bw.write(prob);
                }
            }
            br.close();
            bw.close();
            File oldfile = new File("problemType.txt");
            oldfile.delete();
            File newfile = new File("temp.txt");
            newfile.renameTo(new File("problemType.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
        }
    }

	public static int[] countUserSubmission(String id,
			Date startDate, Date endDate, String status, boolean onlyOnce) {
		// TODO Auto-generated method stub
        BufferedReader br = null;
        try {
        	boolean counted[] = new boolean[4000];
            br = new BufferedReader(new InputStreamReader(new FileInputStream(id + "_problems.txt"), "UTF-8"));
            String s, parts[];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int[] ret = new int[hash(endDate, startDate) + 1];
            Date dateOfSubmission;
            while((s = br.readLine()) != null){
                parts = s.split(" |\t");
				dateOfSubmission = sdf.parse(parts[1]);
				if(before(dateOfSubmission, startDate) && status.equals(parts[3])){
					if(!onlyOnce || !counted[Integer.valueOf(parts[0]) - 1000]){
						ret[0]++;
					}
					counted[Integer.valueOf(parts[0]) - 1000] = true;
				}
				if(before(dateOfSubmission, startDate) || after(dateOfSubmission, endDate) || !status.equals(parts[3]))continue;
				if(!onlyOnce || !counted[Integer.valueOf(parts[0]) - 1000]){
					ret[hash(dateOfSubmission, startDate)]++;
				}
				counted[Integer.valueOf(parts[0]) - 1000] = true;
            }
            br.close();
            for(int i = 1;i < ret.length;i++){
            	ret[i] += ret[i - 1];
            }
            return ret;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
            
        }
		return null;
	}

	private static boolean before(Date a, Date b){
		return a.getYear() < b.getYear() || (a.getYear() == b.getYear() && a.getMonth() < b.getMonth());
	}
	
	private static boolean after(Date a, Date b){
		return a.getYear() > b.getYear() || (a.getYear() == b.getYear() && a.getMonth() > b.getMonth());
	}
	
	public static Date getFirstDate(String id){
		try {
			String s = readLastLine(new File(id + "_problems.txt"), "UTF-8");
			String[] parts = s.split(" |\t");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(parts[1]);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getLastDate(String id){
		BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(id + "_problems.txt"), "UTF-8"));
            String s, parts[];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfSubmission;
            s = br.readLine();
            parts = s.split(" |\t");
            dateOfSubmission = sdf.parse(parts[1]);
            br.close();
            return dateOfSubmission;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
            
        }
		return null;
	}
	
	private static String readLastLine(File file, String charset) throws IOException {  
	  if (!file.exists() || file.isDirectory() || !file.canRead()) {  
	    return null;  
	  }  
	  RandomAccessFile raf = null;  
	  try {  
	    raf = new RandomAccessFile(file, "r");  
	    long len = raf.length();  
	    if (len == 0L) {  
	      return "";  
	    } else {  
	      long pos = len - 1;  
	      while (pos > 0) {  
	        pos--;  
	        raf.seek(pos);  
	        if (raf.readByte() == '\n') {  
	          break;  
	        }  
	      }  
	      if (pos == 0) {  
	        raf.seek(0);  
	      }  
	      byte[] bytes = new byte[(int) (len - pos)];  
	      raf.read(bytes);  
	      if (charset == null) {  
	        return new String(bytes);  
	      } else {  
	        return new String(bytes, charset);  
	      }  
	    }  
	  } catch (FileNotFoundException e) {  
	  } finally {  
	    if (raf != null) {  
	      try {  
	        raf.close();  
	      } catch (Exception e2) {  
	      }  
	    }  
	  }  
	  return null;  
	}  
	
	@SuppressWarnings("deprecation")
	private static int hash(Date endDate, Date startDate) {
		// TODO Auto-generated method stub
		return (endDate.getYear() - startDate.getYear()) * 12 + endDate.getMonth() - startDate.getMonth();
	}

	public static Map<String, Integer> countUserClassifiedSubmission(
			String id, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		Map<String, String> probType = getProbTypeMap();
		Map<String, Integer> typeNum = new TreeMap<String, Integer>();
		BufferedReader br = null;
        try {
        	boolean counted[] = new boolean[4000];
            br = new BufferedReader(new InputStreamReader(new FileInputStream(id + "_problems.txt"), "UTF-8"));
            String s, parts[];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfSubmission;
            while((s = br.readLine()) != null){
                parts = s.split(" |\t");
				dateOfSubmission = sdf.parse(parts[1]);
				if(dateOfSubmission.before(startDate) || dateOfSubmission.after(endDate) || !parts[3].equals("Accepted"))continue;
				if(!counted[Integer.valueOf(parts[0]) - 1000]){
					if(probType.containsKey(parts[0])){
						String type = probType.get(parts[0]);
						if(!typeNum.containsKey(type)){
							typeNum.put(type, 1);
						}else{
							Integer temp = typeNum.get(type);
							temp++;
							typeNum.remove(type);
							typeNum.put(type, temp);
						}
					}/*else{
						String type = "未分类";
						if(!typeNum.containsKey(type)){
							typeNum.put(type, 1);
						}else{
							Integer temp = typeNum.get(type);
							temp++;
							typeNum.remove(type);
							typeNum.put(type, temp);
						}
					}*/
				}
				counted[Integer.valueOf(parts[0]) - 1000] = true;
            }
            br.close();
            return typeNum;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
        }
		return null;
	}

	private static Map<String, String> getProbTypeMap() {
		// TODO Auto-generated method stub
		Map<String, String> probType = new TreeMap<>();
		BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("problemType.txt"), "UTF-8"));
            String s, parts[], type;
            while((s = br.readLine()) != null){
            	int k = s.indexOf(':');
            	type = s.substring(0, k);
            	parts = s.split(",");
            	for(int i = 0;i < parts.length;i++){
            		probType.put(parts[i], type);
            	}
            }
            br.close();
            return probType;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
		} finally{
        }
		return null;
	}

	public static boolean userFileExist(String oppID) {
		// TODO Auto-generated method stub
		File file = new File(oppID + "_problems.txt");
		return file.exists();
	}

	@SuppressWarnings("null")
	public static Vector<String> getUserDoneProbs(String userID) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		Vector<String> ret = new Vector<>();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(userID + "_proList.txt"), "UTF-8"));
            String s, parts[];
            while((s = br.readLine()) != null){
            	parts = s.split(" |\t");
            	if(!parts[1].equals("0")){
            		ret.add(parts[0]);
            	}
            }
            br.close();
            return ret;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
		} finally{
        }
		return null;
	}

	public static void copyProblemTypeFile(File dir) {
		// TODO Auto-generated method stub
		try {
			copyFile(new File("problemType.txt"), new File(dir + "/problemType.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 复制文件
    private static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
}
