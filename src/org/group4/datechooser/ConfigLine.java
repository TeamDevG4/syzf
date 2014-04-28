/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.datechooser;

/**
 *
 * @author Kam
 */
/**
 *  [ConfigLine.java]
 *
 *  控制条类
 *
 * 创建日期：(2003-10-25)
 * @author：ONE_Fox
 * @author：ONE_Fox@163.com
 */
 
 
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
 
 
class ConfigLine extends JPanel {
    
    //数据配置--------------------//
    private TablePanel tablePanel = null;
    
    private Calendar showMonth = null; //当前显示的月份
    private int startYear = 0; //【最小】显示年份
    private int lastYear = 0;  //【最大】显示年份
    
    private int nowYear = 0;   //当前年份
    private int nowMonth = 0;  //当前月份
    
    /**
     * 计时器，RoundBox快速翻动
     */
    Timer timer = new Timer(true);
    
    
    //界面组件------------------//
    private RoundBox yearBox = null;
    private RoundBox monthBox = null;
    
    private JLabel txtYear = new JLabel("年");
    private JLabel txtMonth = new JLabel("月");
 
 
 
 
//------构造方法/函数-----------------------------------------------//
 
 
/**
 * 构造方法
 *
 * @param tablePanel TablePanel
 * @param showMonth java.util.Calendar
 * @param startYear int
 * @param lastYear int
 */
    ConfigLine(TablePanel tablePanel, Calendar showMonth, 
                                      int startYear, int lastYear) {
                                        
        this.tablePanel = tablePanel;
        
        this.showMonth = showMonth;
        this.startYear = startYear;
        this.lastYear = lastYear;
        
        nowYear = Integer.valueOf(new SimpleDateFormat("yyyy")
                                    .format(showMonth.getTime())).intValue();
        nowMonth = Integer.valueOf(new SimpleDateFormat("M")
                                    .format(showMonth.getTime())).intValue();
        
        yearBox = new RoundBox(nowYear, startYear, lastYear);
        monthBox = new RoundBox(nowMonth, 1, 12);
                                             
        makeFace();    //界面制作
        addListener(); //添加事件监听
    }
    
    
    
    
//------方法/函数---------------------------------------------------//
 
/**
 * 方法：界面制作
 */
    private void makeFace() {
        
        Font txtFont = new Font("宋体", Font.PLAIN, 12);
        
        //自身配置-----------------------//
        this.setBorder(null);
        this.setBackground(Pallet.configLineColor);
        this.setLayout(new FlowLayout(1, 7, 1));
        this.setPreferredSize(new Dimension(50, 19));
        
        //标签配置---------------//
        txtYear.setForeground(Pallet.cfgTextColor);
        txtYear.setPreferredSize(new Dimension(14, 14));
        txtYear.setFont(txtFont);
        
        txtMonth.setForeground(Pallet.cfgTextColor);
        txtMonth.setPreferredSize(new Dimension(14, 14));
        txtMonth.setFont(txtFont);
        
        monthBox.setShowWidth(17);
        
        //总体布局-----------------------//
        add(yearBox);
        add(txtYear);
        add(monthBox);
        add(txtMonth);
    }
 
 
 
 
/**
 * 方法：添加事件监听
 */
    private void addListener() {
        
        //yearBox 事件配置-------------------------//
        
        yearBox.bt_UP.addMouseListener(new MouseAdapter() {
            
            //yearBox.bt_UP 按下
            public void mousePressed(MouseEvent e) {
                btPressed(yearBox, 1);
            }
            
            //yearBox.bt_UP 弹起
            public void mouseReleased(MouseEvent e) {
                btReleased(yearBox, 1);
                nowYear = yearBox.showNow;
                
                tablePanel.setMonth(nowYear, nowMonth);
            }
        });
        
        yearBox.bt_DOWN.addMouseListener(new MouseAdapter() {
            
            //yearBox.bt_DOWN 按下
            public void mousePressed(MouseEvent e) {
                btPressed(yearBox, 2);
            }
            
            //yearBox.bt_DOWN 弹起
            public void mouseReleased(MouseEvent e) {
                btReleased(yearBox, 2);
                nowYear = yearBox.showNow;
                
                tablePanel.setMonth(nowYear, nowMonth);
            }
        });
        
        
        
        //monthBox 事件配置------------------------//
        
        monthBox.bt_UP.addMouseListener(new MouseAdapter() {
            
            //monthBox.bt_UP 按下
            public void mousePressed(MouseEvent e) {
                btPressed(monthBox, 1);
            }
            
            //monthBox.bt_UP 弹起
            public void mouseReleased(MouseEvent e) {
                btReleased(monthBox, 1);
                nowMonth = monthBox.showNow;
                
                tablePanel.setMonth(nowYear, nowMonth);
            }
        });
        
        monthBox.bt_DOWN.addMouseListener(new MouseAdapter() {
            
            //monthBox.bt_DOWN 按下
            public void mousePressed(MouseEvent e) {
                btPressed(monthBox, 2);
            }
            
            //monthBox.bt_DOWN 弹起
            public void mouseReleased(MouseEvent e) {
                btReleased(monthBox, 2);
                nowMonth = monthBox.showNow;
                
                tablePanel.setMonth(nowYear, nowMonth);
            }
        });
    }
    
    
    
    
/**
 * RoundBox 统一按钮按下事务
 *
 * @param box RoundBox
 * @param theBT int
 */
    private void btPressed(RoundBox box, int theBT) {
        final RoundBox theBox = box;
        
        if(theBT == 1) {  //"+"按钮
            timer = new Timer(true);
            timer.schedule(new TimerTask() {
                public void run() {
                    if(theBox.showNow < theBox.showMax) {
                        theBox.showing.setText("" + (theBox.showNow+1));
                        
                        theBox.showNow++;
                    }
                }
            }, 500, 100);
        }
        else if(theBT == 2) {  //"-"按钮
            timer = new Timer(true);
            timer.schedule(new TimerTask() {
                public void run() {
                    if(theBox.showNow > theBox.showMin) {
                        theBox.showing.setText("" + (theBox.showNow-1));
                        
                        theBox.showNow--;
                    }
                }
            }, 500, 100);
        }
    }
 
 
 
 
/**
 * RoundBox 统一按钮弹起事务
 *
 * @param box RoundBox
 * @param theBT int
 */
    private void btReleased(RoundBox box, int theBT) {
        
        final RoundBox theBox = box;
        
        timer.cancel();
        
        if(theBT == 1) {  //"+"按钮
            if(theBox.showNow < theBox.showMax) {
                theBox.showing.setText("" + (theBox.showNow+1));
                        
                theBox.showNow++;
            }
        }
        
        else if(theBT == 2) {  //"-"按钮
            if(theBox.showNow > theBox.showMin) {
                theBox.showing.setText("" + (theBox.showNow-1));
                        
                theBox.showNow--;
            }
        }
    }
}