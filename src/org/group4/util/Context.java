/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.util;

/**
 *
 * @author Kam
 */
public class Context {
    private static String userID = null;
    public static void setUserID(String id){
        userID = id;
    }
    public static String getUserID(){
        return userID;
    }
}
