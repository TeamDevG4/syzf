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
 *  [RoundBox.java]
 *
 *  限定选择控件
 *
 * 创建日期：(2003-10-27)
 * @author：ONE_Fox
 * @author：ONE_Fox@163.com
 */
 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
 
 
 
class RoundBox extends JPanel {
    
    //数据配置----------------------//
    public int showMin = 0;  //最小显示值
    public int showMax = 0;  //最大显示值
    
    public int showNow = 0;  //默认首显值
    
    
    //界面组件/配置----------------------//
    public JLabel showing = new JLabel("", JLabel.RIGHT);
        private int showWidth = 35;
        private int showHeight = 15;
        
    public JButton bt_DOWN = new JButton("-");
    public JButton bt_UP = new JButton("+");
    
    
    
    
//------构造方法---------------------------------------------------//
 
 
/**
 * 构造方法
 */
    RoundBox(int showNow, int showMin, int showMax) {
        
        if(showNow >= showMin && showNow <= showMax) {
            
            this.showNow = showNow;
            this.showMin = showMin;
            this.showMax = showMax;
        }
        
        makeFace();  //界面制作
    }
    
    
    
    
//------方法/函数--------------------------------------------------//
 
 
 
/**
 * 方法：界面制作
 */
    private void makeFace() {
        
        Font txtFont = new Font("宋体", Font.PLAIN, 12);
        
        //自身配置----------------------------//
        this.setLayout(new FlowLayout(1, 2, 1));
        this.setBackground(Pallet.configLineColor);
        this.setBorder(null);
        
        
        //showing 配置------------------------//
        showing.setText("" + showNow);
        showing.setBorder(new LineBorder(Pallet.rbBorderColor, 1));
        showing.setForeground(Pallet.rbFontColor);
        showing.setPreferredSize(new Dimension(showWidth, showHeight));
        showing.setFont(txtFont);
        
        
        //bt_UP & bt_DOWN 配置----------------//
        bt_UP.setBorder(new LineBorder(Pallet.rbBorderColor, 1));
        bt_UP.setBackground(Pallet.rbButtonColor);
        bt_UP.setForeground(Pallet.rbBtFontColor);
        bt_UP.setPreferredSize(new Dimension(15, 7));
        bt_UP.setFont(txtFont);
        bt_UP.setFocusable(false);
        
        bt_DOWN.setBorder(new LineBorder(Pallet.rbBorderColor, 1));
        bt_DOWN.setBackground(Pallet.rbButtonColor);
        bt_DOWN.setForeground(Pallet.rbBtFontColor);
        bt_DOWN.setPreferredSize(new Dimension(15, 7));
        bt_DOWN.setFont(txtFont);
        bt_DOWN.setFocusable(false);
        
        
        //总体布局----------------------------//
        JPanel btPanel = new JPanel(new BorderLayout(0, 1));
        btPanel.setBorder(null);
        btPanel.setBackground(Pallet.configLineColor);
        
        btPanel.add(bt_UP, BorderLayout.NORTH);
        btPanel.add(bt_DOWN, BorderLayout.SOUTH);
        
        
        add(showing);
        add(btPanel);
    }
 
 
 
 
/**
 * 方法：设置显示标签宽度
 *
 * @param showWidth int
 */
    public void setShowWidth(int showWidth) {
        
        if(showWidth > 0)
            this.showWidth = showWidth;
            
        showing.setPreferredSize(new Dimension(showWidth, showHeight));
    }
    
}
