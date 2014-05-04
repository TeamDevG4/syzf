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
import java.util.Date;

import org.group4.datechooser.DatePanel;
import org.group4.util.Context;
import org.group4.util.FileUtil;

/**
 *
 * @author Kam
 */
public class MyStepsUI extends javax.swing.JPanel {

    private Date startDate, endDate;
    /**
     * Creates new form StepsChart
     */
    public MyStepsUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonPanel = new javax.swing.JPanel();
        chartPanel = new javax.swing.JPanel();

        setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(1,2));
        
        add(buttonPanel, BorderLayout.NORTH);
        
        sdp = new DatePanel(startDate = FileUtil.getFirstDate(Context.getUserID()));
        edp = new DatePanel(endDate = FileUtil.getLastDate(Context.getUserID()));
        buttonPanel.add(sdp);
        buttonPanel.add(edp);
        sdp.addChangeListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				renewChart();
			}
        });
        edp.addChangeListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				renewChart();
			}
        });
        
        chartPanel.setLayout(new GridLayout(1,1));
        add(chartPanel, BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public void renewChart(){
        chartPanel.removeAll();
        String[] ids = new String[1];
        ids[0] = Context.getUserID();
		chartPanel.add(BarGraph.createBarGraph(ids, startDate, endDate, "我的ACM STEPS"));
        chartPanel.validate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private DatePanel sdp, edp;
    private javax.swing.JPanel chartPanel;
    // End of variables declaration//GEN-END:variables
}