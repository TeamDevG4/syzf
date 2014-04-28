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
 *  [DateChooser.java]
 *
 *  Java 日期选择控件(主体界面)
 *
 *
 * 创建日期：(2003-10-25)
 * @author：ONE_Fox
 * @author：ONE_Fox@163.com
 */
 
 
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Point;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
 
 
 
 
public class DateChooser extends JDialog {
    
    //状态配置-----------------------//
    
    private static boolean isShow = false;  //是否正在显示
    
    
    //数据配置-----------------------//
    private static Calendar showMonth = new GregorianCalendar(); //默认首显今天
    
    private int startYear = 1950; //默认【最小】显示年份
    private int lastYear = 2050;  //默认【最大】显示年份
    
 
    //界面配置-----------------------//
    
    //放TablePanel与 ConfigLine 中间层
    JPanel rootPanel = new JPanel(new BorderLayout(), true);
    
    //TablePanel 日历显示表格
    private TablePanel tablePanel = null;
 
    //ConfigLine 控制条
    private ConfigLine configLine = null;
    
    //界面 大小/位置
    /**
     * 公开的界面大小属性
     */
    public static final int width = 250;  //界面宽度
    public static final int height = 200; //界面高度
    
    private int local_X = 0;  //显示位置 X 坐标
    private int local_Y = 0;  //显示位置 Y 坐标
    
 
 
 
 
//------构造方法--------------------------------------------------//

/**
 * 构造方法1
 */
    public DateChooser() {
        
        makeFace(); //界面制作
    }
    
    
    
    
/**
 * 构造方法2
 *
 * @param owner java.awt.Frame
 */
    public DateChooser(Frame owner) {
        
        super(owner, "DateChooser", true); //设置父窗口
        makeFace(); //界面制作
    }
    
 
 
 
/**
 * 构造方法3
 *
 * @param owner java.awt.Frame
 * @param showMonth java.util.Calendar
 * @param startYear int
 * @param lastYear int
 */
    public DateChooser(Frame owner, Calendar showMonth, int startYear, 
                                                         int lastYear) {
        super(owner, "DateChooser", true);
        
        this.showMonth = showMonth;
        this.startYear = startYear;
        this.lastYear = lastYear;
        
        makeFace(); //界面制作
    }
 
 
 
/**
 * 构造方法4
 *
 * @param showMonth java.util.Calendar
 * @param startYear int
 * @param lastYear int
 */
    public DateChooser(Calendar showMonth, int startYear, int lastYear) {
        
        super((Frame)null, "DateChooser", true);
        
        this.showMonth = showMonth;
        this.startYear = startYear;
        this.lastYear = lastYear;
        
        makeFace(); //界面制作
    }
    
    
    
    
//------方法函数---------------------------------------------------//
 
/**
 * 方法:界面制作
 */
    private void makeFace() {
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        setResizable(false);  //界面大小无法改变
        
        //界面构建---------------------------//
        tablePanel = new TablePanel(this, showMonth);
        configLine = new ConfigLine(tablePanel, showMonth, 
                                    startYear, lastYear); 
        
        
        //界面属性设置-----------------------//
        setSize(width, height);
        
        rootPanel.setBorder(new LineBorder(Pallet.backGroundColor, 2));
        rootPanel.setBackground(Pallet.backGroundColor);
        
        
        
        //总体布局---------------------------//
        rootPanel.add(tablePanel, BorderLayout.CENTER);
        rootPanel.add(configLine, BorderLayout.SOUTH);
        
        getContentPane().add(rootPanel, BorderLayout.CENTER);
    }
 
 
 
/**
 * 方法：显示界面
 *
 * @param invoker javax.swing.Component
 * @param x int
 * @param y int
 *
 * @return Date
 */
    public Date showChooser(JComponent invoker, int x, int y) {
        
        Point invokerOrigin;
        
        if (invoker != null) {
            
            if(isShow == true)
                setVisible(false);
                
            invokerOrigin = invoker.getLocationOnScreen();
                
            setLocation(invokerOrigin.x + x, invokerOrigin.y + y);
            
        } else {
            
            if(isShow == true)
                setVisible(false);
                
            setLocation(x, y);
        }
        
        setVisible(true);
        isShow = true;
        
        
        return tablePanel.getDate();
    }
    
    
    
/**
 * 方法：界面隐藏
 */
    public void hideChooser() {
        setVisible(false);
    }
    
    
    
/**
 * 方法：取得选择日期
 */
    public Date getDate() {
        return tablePanel.getDate();
    }
}