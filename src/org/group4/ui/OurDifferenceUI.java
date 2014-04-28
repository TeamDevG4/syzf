/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JOptionPane;

import org.group4.util.Context;
import org.group4.util.FileUtil;
import org.group4.util.HttpUtil;
import org.group4.util.ProgressBar;

/**
 *
 * @author Kam
 */
public class OurDifferenceUI extends javax.swing.JPanel {

    private String oppID = null, hint[] = {"按题量", "按类型"};
    private int index = 0;
    private Date startDate, endDate, s1, s2, e1, e2;
    private String[] users;
    
    /**
     * Creates new form OurDifferenceUI
     */
    public OurDifferenceUI() {
        initComponents();
    }
    
    public String getOppID(){
        return oppID;
    }
    
    public void askForUserID(){
        oppID = JOptionPane.showInputDialog(OurDifferenceUI.this, "请输入牛人ID");
        if(oppID == null)return;
        if(oppID.equals(""))
            oppID = null;
        else{
            HttpUtil.setUsername(oppID);
            new ProgressBar();
        }
        renewChart();
    }
    
    private void renewChart(){
        chartPanel.removeAll();
        users = new String[2];
        users[0] = Context.getUserID();
        users[1] = oppID;
        s1 = FileUtil.getFirstDate(users[0]);
        s2 = FileUtil.getFirstDate(users[1]);
        e1 = FileUtil.getLastDate(users[0]);
        e2 = FileUtil.getLastDate(users[1]);
        if(s1.after(s2)){
        	startDate = s2;
        }else{
        	startDate = s1;
        }
        if(e1.before(e2)){
        	endDate = e2;
        }else{
        	endDate = e1;
        }
        System.out.println("start = " + startDate.toLocaleString() + ", end = " + endDate.toLocaleString());
        chartPanel.add(LineChart.createAcceptedLineChart(users, startDate, endDate, "我们解决问题数的差距"));
    }
    
    private void flip(){
        chartPanel.removeAll();
        index = 1 - index;
        if(index == 0){
            chartPanel.add(LineChart.createAcceptedLineChart(users, startDate, endDate, "我们解决问题数的差距"));
        }else{
            chartPanel.add(BarGraph.createBarGraph(users, startDate, endDate, "我们各类题目解题数的差距"));
        }
        switchChart.setText(hint[index]);
    }
    
    private void initComponents() {
        buttonPanel = new javax.swing.JPanel();
        chartPanel = new javax.swing.JPanel();
        switchOpponent = new javax.swing.JButton();
        switchChart = new javax.swing.JButton();

        switchOpponent.setText("换一个大牛");
        switchOpponent.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                askForUserID();
            }
        });

        switchChart.setText("按题量");
        switchChart.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flip();
            }
        });

        setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(switchOpponent);
        buttonPanel.add(switchChart);
        add(buttonPanel, BorderLayout.NORTH);
        chartPanel.setLayout(new GridLayout(1,1));
        add(chartPanel, BorderLayout.CENTER);
    }

    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JButton switchChart;
    private javax.swing.JButton switchOpponent;
}