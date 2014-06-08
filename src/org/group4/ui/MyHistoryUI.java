/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.group4.datechooser.DatePanel;
import org.group4.util.FileUtil;

/**
 *
 * @author Kam
 */
public class MyHistoryUI extends javax.swing.JPanel {

	private Date startDate, endDate;
    private final String user;
    private JPanel chartPanel;
    private DatePanel sdp, edp;
    private JPanel buttonPanel;
    
    public MyHistoryUI(String id) {
    	user = id;
        initComponents();
        renewChart();
    }
    
    public void renewChart(){
    	if(startDate.after(endDate)){
    		JOptionPane.showMessageDialog(this, "结束时间早于开始时间？你确定？", "日期设置错误", JOptionPane.WARNING_MESSAGE);
    	}else{
    		chartPanel.removeAll();
            chartPanel.add(LineChart.createAcceptedLineChart(user, startDate, endDate, "我的ACM之路"));
            validate();
    	}
    }

    private void initComponents() {
    	setOpaque(false);
        buttonPanel = new JPanel();
        chartPanel = new JPanel();
        setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(1,2));
		sdp = new DatePanel(startDate = FileUtil.getFirstDate(user));
        edp = new DatePanel(endDate = FileUtil.getLastDate(user));
        buttonPanel.add(sdp);
        buttonPanel.add(edp);
        add(buttonPanel, BorderLayout.NORTH);
        
        chartPanel.setLayout(new GridLayout(1,1));
        add(chartPanel, BorderLayout.CENTER);
        
        sdp.addChangeListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				sdp.updateDate();
				startDate = sdp.getDate();
				renewChart();
			}
        });
        
        edp.addChangeListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				edp.updateDate();
				endDate = edp.getDate();
				renewChart();
			}
        });
    }

}
