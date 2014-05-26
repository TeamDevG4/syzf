/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.group4.util.FileUtil;

/**
 *
 * @author Kam
 */
public class OurDifferenceUI extends JDialog {

	private String hint[] = {"按题量", "按类型"};
    private int index = 0;
    private Date startDate, endDate, s1, s2, e1, e2;
    private Vector<String> users;
    
    /**
     * Creates new form OurDifferenceUI
     */
    public OurDifferenceUI() {
    	super(MainFrame.getInstance());
        initComponents();
    }
    
    private void renewChart(){
    //    chartPanel.removeAll();
    //    chartPanel.add(LineChart.createAcceptedLineChart(((String[])users.toArray()), startDate, endDate, "我们解决问题数的差距"));
    }
    
    @SuppressWarnings("deprecation")
	public void show(){
    	super.show();
    	renewChart();
    }
    
    private void initComponents() {
        buttonPanel = new JPanel();
        chartPanel = new JPanel();
        switchLineChartButton = new JButton("按题量");
        switchBarChartButton = new JButton("按类型");
        
        switchLineChartButton.addActionListener(new SwitchChartActionListener());
        switchBarChartButton.addActionListener(new SwitchChartActionListener());
        
        setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(switchLineChartButton);
        buttonPanel.add(switchBarChartButton);
        add(buttonPanel, BorderLayout.NORTH);
        chartPanel.setLayout(new GridLayout(1,1));
        add(chartPanel, BorderLayout.CENTER);
    }

    private class SwitchChartActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			// TODO Auto-generated method stub
			chartPanel.removeAll();
	        if(evt.getSource() == switchLineChartButton){
	            chartPanel.add(LineChart.createAcceptedLineChart(((String[])users.toArray()), startDate, endDate, "我们解决问题数的差距"));
	        }else{
	            chartPanel.add(BarGraph.createBarGraph(((String[])users.toArray()), startDate, endDate, "我们各类题目解题数的差距"));
	        }
		}
    }
    
    private JPanel buttonPanel;
    private JPanel chartPanel;
    private JButton switchBarChartButton, switchLineChartButton;
    
    /*
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
    }*/
}