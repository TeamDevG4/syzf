/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Kam
 */
public class OurDifferenceUI extends JDialog {

    private Vector<String> users;
    private JPanel buttonPanel;
    private JPanel chartPanel;
    private JButton switchBarChartButton, switchLineChartButton;
    /**
     * Creates new form OurDifferenceUI
     */
    public OurDifferenceUI() {
    	super(MainFrame.getInstance());
        initComponents();
    }
    
    private void renewChart(){
        chartPanel.removeAll();
        chartPanel.add(LineChart.createDifferenceLineChart((String[])users.toArray(), "解决问题数的对比"));
    }
    
    @SuppressWarnings("deprecation")
	public void show(){
    	super.show();
    	renewChart();
    }
    
    public void addUser(String id){
    	if(!users.contains(id)){
    		users.add(id);
    		renewChart();
    	}
    }
    
    public void delUser(String id){
    	if(users.contains(id)){
    		users.remove(id);
    		renewChart();
    	}
    }
    
    private void initComponents() {
        buttonPanel = new JPanel();
        chartPanel = new JPanel();
        switchLineChartButton = new JButton("按题量");
        switchBarChartButton = new JButton("按类型");
        
        switchLineChartButton.addActionListener(new SwitchChartActionListener());
        switchBarChartButton.addActionListener(new SwitchChartActionListener());
        
        Container container = getContentPane();
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(switchLineChartButton);
        buttonPanel.add(switchBarChartButton);
        container.add(buttonPanel, BorderLayout.NORTH);
        chartPanel.setLayout(new GridLayout(1,1));
        container.add(chartPanel, BorderLayout.CENTER);
    }

    private class SwitchChartActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			chartPanel.removeAll();
	        if(evt.getSource() == switchLineChartButton){
	            chartPanel.add(LineChart.createDifferenceLineChart((String[])users.toArray(), "解决问题数的对比"));
	        }else{
	            chartPanel.add(BarGraph.createDifferenceBarGraph((String[])users.toArray(), "我们各类题目解题数的差距"));
	        }
		}
    }
    
}