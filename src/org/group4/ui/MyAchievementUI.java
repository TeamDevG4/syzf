/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

/**
 *
 * @author Kam
 */
public class MyAchievementUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2466619796268050680L;
	private final String user;
    /**
     * Creates new form MyAchievementUI
     */
    public MyAchievementUI(String id) {
        user = id;
    	initComponents();
    	setLayout(new GridLayout(5, 1));//行数调大了有问题，会令其他页面变形
    	update();
    }

    //添加成就条目
    public void update(){
    	int h = AchievementAccessment.MyHighligthOfAC(user) / 100;
    	if(h != 0)
    		add(new AchievementItem("A题大师","累计已解决超过" + h + "百题"));
    	Vector<String> vec = AchievementAccessment.MyHighligthOfUnremitting(user);
    	if(vec != null){
    		add(new AchievementItem("屡败屡战","总有一些题，你刷，还是不刷，它就在那里，不能解决"));
    	}
    	vec = AchievementAccessment.MyHighligthOfACFirsttime(user);
    	if(vec != null){
    		add(new AchievementItem("一次通过","1A有时很简单，你也试过"));
    	}
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        setOpaque(false);
    }// </editor-fold>//GEN-END:initComponents

    private static class AchievementAccessment{
    	//AC的题目达到一定数量
    	public static int MyHighligthOfAC(String id){
    		BufferedReader br = null;
    		try {			
                br = new BufferedReader(new InputStreamReader(new FileInputStream(id + "_problems.txt")));
                String s,parts[];
    			int retAC = 0;
                while((s = br.readLine()) != null){
                    parts = s.split(" |\t");
    				if(parts[3].equals("Accepted")){
    					retAC++;
    				}
                }
    			br.close();
    			return retAC;
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AchievementAccessment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AchievementAccessment.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
            	
            }
    		return -1;
    	}
    	//屡战屡败的题目（同一道题，提交数量超过10次，仍然未AC）
    	public static Vector<String> MyHighligthOfUnremitting(String id){
    		BufferedReader br = null;
    		try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(id + "_problems.txt")));
                String s,parts[];
    			Vector<String> probID = new Vector<>();
                while((s = br.readLine()) != null){
                    parts = s.split(" |\t");
    				probID.add(parts[0]);
                }
    			br.close();
    			Vector<String> probsUnremitting = new Vector<>();
    			for(int i=0;i<probID.size();i++){
    				int count=1;
    				for(int j=i+1;j<probID.size();j++){
    					if(probID.get(i).equals(probID.get(j)))
    						count++;
    				}
    				if(count>10){
    					probsUnremitting.add(probID.get(i));
    				}
    			}
    			//去除重复的题号
    			TreeSet<String> tr = new TreeSet<String>();
    			for(int i=0;i<probsUnremitting.size();i++){
    				tr.add(probsUnremitting.get(i));
    			}
    			Vector<String> probUnremitting = new Vector<>();
    			for(int i=0;i<tr.size();i++){
    				probUnremitting.add(tr.pollFirst());
    			}
    			return probUnremitting;
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AchievementAccessment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AchievementAccessment.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                
            }
    		return null;
    	}
    	//第一次提交AC的题目
    	public static Vector<String> MyHighligthOfACFirsttime(String id){
    		BufferedReader br = null;
    		try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(id + "_problems.txt")));
                String s,parts[];
    			Vector<String> probs = new Vector<>();
    			Vector<String> probsACFirsttime = new Vector<>();
    			Vector<String> status = new Vector<>();
                while((s = br.readLine()) != null){
                    parts = s.split(" |\t");
    				probs.add(parts[0]);
    				status.add(parts[3]);
                }
    			for(int i=0;i<probs.size();i++){
    				int count=1;
    				for(int j=i+1;j<probs.size();j++){
    					if(probs.get(i).equals(probs.get(j))){
    						count++;
    					}
    				}
    				if(count==1&&status.get(i)=="Accept"){
    					probsACFirsttime.add(probs.get(i));		
    				}
    			}
    			br.close();
    			
    			return probsACFirsttime;
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AchievementAccessment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AchievementAccessment.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                
            }
    		return null;
    	}
    }
}