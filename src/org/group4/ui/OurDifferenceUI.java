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
    	super(MainFrame.getInstance(), true);
    	users = new Vector<String>();
        initComponents();
        setSize(800, 600);
        setLocationRelativeTo(MainFrame.getInstance());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    
    private void renewChart(){
    	if(chartPanel.getComponentCount() != 0){
    		chartPanel.removeAll();
    		System.out.println("remove");
    	}
        String ids[] = new String[users.size()];
        chartPanel.add(LineChart.createDifferenceLineChart(users.toArray(ids), "解决问题数的对比"));
    }
    
    public void setVisible(boolean visible){
    	if(visible){
    		renewChart();
    		validate();
    	}
    	super.setVisible(visible);
    }
    
    public void addUser(String id){
    	if(!users.contains(id)){
    		users.add(id);
    	}
    }
    
    public boolean contains(String id){
    	return users.contains(id);
    }
    
    public void delUser(String id){
    	if(users.contains(id)){
    		users.remove(id);
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
			String ids[] = new String[users.size()];
	        if(evt.getSource() == switchLineChartButton){
	            chartPanel.add(LineChart.createDifferenceLineChart(users.toArray(ids), "解决问题数的对比"));
	        }else{
	            chartPanel.add(BarGraph.createDifferenceBarGraph(users.toArray(ids), "我们各类题目解题数的差距"));
	        }
	        validate();
		}
    }
    
}