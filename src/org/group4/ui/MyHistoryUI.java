/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.group4.datechooser.DatePanel;
import org.group4.util.Context;

/**
 *
 * @author Kam
 */
public class MyHistoryUI extends javax.swing.JPanel {

    private Date startDate, endDate;
    /**
     * Creates new form MyHistoryUI
     */
    public MyHistoryUI() {
        initComponents();
    }
    
    public void renewChart(){
        chartPanel.removeAll();
        String[] users = new String[1];
        users[0] = Context.getUserID();
        chartPanel.add(LineChart.createAcceptedLineChart(users, startDate, endDate, "我的ACM之路"));
        chartPanel.validate();
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
			sdp = new DatePanel(startDate = sdf.parse("2007-05"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        edp = new DatePanel(endDate = Calendar.getInstance().getTime());
        buttonPanel.add(sdp);
        buttonPanel.add(edp);
        
        add(buttonPanel, BorderLayout.NORTH);
        
        chartPanel.setLayout(new GridLayout(1,1));
        add(chartPanel, BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel;
    private DatePanel sdp, edp;
    private javax.swing.JPanel buttonPanel;
    // End of variables declaration//GEN-END:variables
}
