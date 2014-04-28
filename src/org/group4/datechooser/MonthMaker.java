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
 *  [MonthMaker.java]
 *
 *  月份表算法类
 *
 * 创建日期：(2003-10-25)
 * @author：ONE_Fox
 * @author：ONE_Fox@163.com
 */
 
 
import java.util.Calendar;
 
 
 
class MonthMaker {
    
    private static Calendar maker = null;
 
 
/**
 * (隐藏)构造函数，本类无需创建实例
 */
    private MonthMaker () {}
    
    
    
    
//----------------------------------------------------------------//
 
 
 
/**
 * 方法：计算月份表
 *
 * @param showMonth java.util.Calendar
 *
 * @retuen String[][]
 */
    public static String[][] makeMonth(Calendar showMonth) {
        
        maker = showMonth; //更新 maker
        
        int dayCount = 1; //天数计数器
        
        
        //保存月历表的数组----------------//
        String[][] date = new String[6][7];
        for(int f = 0; f < 6; f++)  //填充初始数据
            java.util.Arrays.fill(date[f], "");
        
        
        maker.set(Calendar.DATE, dayCount);
        
        //制作月份表第 1 行---------------//
        for(int i = maker.get(Calendar.DAY_OF_WEEK) -1; i < 7; i++) {
            date[0][i] = "" + dayCount;
            dayCount++;
        }
        
        //制作月份表 2～4 行--------------//
        for(int i = 1; i < 4; i++) {
            for(int j = 0; j < 7; j++) {
                date[i][j] = "" + dayCount;
                dayCount++;
            }
        }
        
        //制作月份表第 5 行---------------//
        for(int i = dayCount, j = 0; 
              i <= maker.getActualMaximum(Calendar.DAY_OF_MONTH) && j < 7;
                                                                   i++,j++) {
                                                                    
            maker.set(Calendar.DATE, i);
            date[4][maker.get(Calendar.DAY_OF_WEEK)-1] = "" + dayCount;
            
            dayCount++;
        }
        
        //制作月份表第 6 行--------------//
        for(int i = dayCount; i <= maker.getActualMaximum(
                                               Calendar.DAY_OF_MONTH); i++) {
                                                                    
            maker.set(Calendar.DATE, i);
            date[5][maker.get(Calendar.DAY_OF_WEEK)-1] = "" + dayCount;
            
            dayCount++;
        }
        
        return date;
    }
}