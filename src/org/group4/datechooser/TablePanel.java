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
 *  [TablePanel.java]
 *
 *  日历表格面板
 *
 *
 * 创建日期：(2003-10-25)
 * @author：ONE_Fox
 * @author：ONE_Fox@163.com
 */
 
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
 
 
 
 
class TablePanel extends JPanel {
    
 
    //数据配置------------------------//
    /**
     * 主界面引用，只做表格双击后界面隐藏用
     */
    DateChooser dateChooser = null;
    
    //当前显示的月份
    private Calendar showMonth = null;
    
    //被选择的日期
    private Date selectedDate = null;
    
    //表头信息
    private String[] colname = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
    
    //表内容，第一行显示星期文字
    //其余 6 行显示月历表内容
    private String[][] date = new String[7][7];
    
    
    //界面组件-----------------------//
    
    private DefaultTableModel model; //默认 表格模型
    private JTable table; //具体显示月历表的 表格
 
 
 
 
 
//------构造方法/函数-----------------------------------------------//
 

 
/**
 * 构造方法
 *
 * @param dateChooser DateChooser
 * @param showMonth java.util.Calendar
 */
    TablePanel(DateChooser dateChooser, Calendar showMonth) {
        
        this.dateChooser = dateChooser;
        this.showMonth = showMonth;
        
        makeFace(); //界面制作
        addListener(); //添加事件监听
    }
    
    
    
    
    
//------方法/函数--------------------------------------------------//
 
/**
 * 方法：界面制作
 */
    private void makeFace() {
        
        //添加星期文字------------------------//
        date[0][0] = "日";  
        date[0][1] = "一";
        date[0][2] = "二";  
        date[0][3] = "三";
        date[0][4] = "四";  
        date[0][5] = "五";
        date[0][6] = "六";
        
        
        //构建 Table -------------------------//
        
        table = new JTable(model = new DefaultTableModel(date,colname) {
                    public boolean isCellEditable(int rowIndex, int mColIndex) {
                        return false;
                    }
                });
        
        //设置固定字体，以免调用环境改变影响界面美观
        table.setFont(new Font("宋体", Font.PLAIN, 12));
        
        //table 表现器构造--------------------//
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, 
                          Object value, boolean isSelected, boolean hasFocus, 
                                                     int row, int column) {
                
                //单元格文字右对齐
                setHorizontalAlignment(JLabel.RIGHT);
                
                //设置 背景 颜色-----------//
                if(row == 0)  //星期文字行背景色
                    setBackground(Pallet.backGroundColor);
                else if((""+new GregorianCalendar().get(Calendar.DAY_OF_MONTH))
                                              .equals(date[row][column])) {
                    setBackground(Pallet.todayBackColor); //今天格背景色
                }
                else
                    setBackground(Pallet.palletTableColor); //普通格背景色
                    
                
                //设置 前景 颜色-----------//
                if((column == 0 && row != 0)||(column == 6 && row != 0))
                    setForeground(Pallet.weekendFontColor); //周末格字体色
                else if(row != 0 && column != 0 && column != 6)
                    setForeground(Pallet.dateFontColor); //普通格字体色
                else
                    setForeground(Pallet.weekFontColor); //星期文字格字体色
                
                return super.getTableCellRendererComponent(table, value, 
                                          isSelected, hasFocus, row, column);
                                          
            }
        };
        
        
        
        //设置列表现器------------------------//
        
        for(int i = 0; i < colname.length; i++) {
            table.getColumn(colname[i]).setCellRenderer(tcr);
        }
        
        
        //table 配置--------------------------//
        
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        
        table.setIntercellSpacing(new Dimension(0, 0));
        
        
        //设置月历表数值----------------------//
        
        setMonth(showMonth);
        
        
        //总体布局---------------------------//
        setLayout(new BorderLayout());
        add(table, BorderLayout.CENTER);
    }
    
    
    
/**
 * 方法：添加事件监听
 */
    private void addListener() {
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                
                //双击表单元格，保存有意义的 Date
                if(e.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    int selectedColumn = table.getSelectedColumn();
                    
                    if(selectedRow > 0 && 
                              !date[selectedRow][selectedColumn].equals("")) {
                        
                        showMonth.set(Calendar.DAY_OF_MONTH, Integer.valueOf(
                                date[selectedRow][selectedColumn]).intValue());
                        
                        selectedDate = showMonth.getTime();
                        
                        dateChooser.hideChooser();
                    }
                } //end if
            }
        });
    }
    
 
 
/**
 * 方法：设置月历的显示月份1
 *
 * @param showMonth java.util.Calendar
 */
    private void setMonth(Calendar showMonth) {
        
        this.showMonth = showMonth;
        
        //月份表取得--------------------------//
        
        String[][] tmpDate = MonthMaker.makeMonth(showMonth);
        
        for(int i = 1; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                date[i][j] = tmpDate[i-1][j];
                table.setValueAt(""+tmpDate[i-1][j], i, j);
            }
        }
    }
    
    
    
    
/**
 * 方法：设置月历的显示月份2
 *
 * @param year int
 * @param month int
 */
    public void setMonth(int year, int month) {
        
        showMonth.set(year, month-1, 1);
        
        setMonth(showMonth);
    }
    
    
    
/**
 * 方法：取得选择日期
 */
    public Date getDate() {
        return selectedDate;
    }
}