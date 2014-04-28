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
 *  [Pallet.java]
 *
 *  调色板，统一配色类
 *
 * 创建日期：(2003-10-29)
 * @author：ONE_Fox
 * @author：ONE_Fox@163.com
 */
 
import java.awt.Color;
 
 
 
public class Pallet {
    
    public static Color backGroundColor = Color.gray;    //底色
    
    
    //月历表格配色----------------//
    public static Color palletTableColor = Color.white;  //日历表底色
    public static Color todayBackColor = Color.orange;     //今天背景色
    
    public static Color weekFontColor = Color.white;     //星期文字色
    public static Color dateFontColor = Color.black;     //日期文字色
    public static Color weekendFontColor = Color.red;    //周末文字色
    
    
    //控制条配色------------------//
    public static Color configLineColor = Color.gray;    //控制条底色
    public static Color cfgTextColor = Color.white;      //控制条标签文字色
    
    public static Color rbFontColor = Color.white;       //RoundBox文字色
    public static Color rbBorderColor = Color.gray;       //RoundBox边框色
    public static Color rbButtonColor = Color.gray;      //RoundBox按钮色
    public static Color rbBtFontColor = Color.gray;       //RoundBox按钮文字色
}
