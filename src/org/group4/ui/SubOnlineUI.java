/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.group4.util.HttpUtil;


/**
 *
 * @author Kam
 */
public class SubOnlineUI extends JDialog {
    private javax.swing.JLabel input;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel jTextArea1;
    private javax.swing.JLabel jTextArea2;
    private javax.swing.JLabel jTextArea3;
    private javax.swing.JLabel jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JLabel language;
    private javax.swing.JComboBox languageComboBox;
    private javax.swing.JLabel output;
    private javax.swing.JLabel proDescribe;
    private javax.swing.JLabel proName;
    private javax.swing.JButton submit;
    private String userID =null;
    private String userPassword;
    private String proID;
    Map<String,String> map;
    private JTextField idTextField;
    private JLabel passLabel;
    private JTextField passTextField;
    private JLabel idLabel;
    private JLabel proLabel;
    private JTextField proTextField;
    private JButton okButton;
    private JButton changePro;
    private JPanel jPanel2;

    /**
     * Creates new form RecommendUI
     */
    public SubOnlineUI() {
    	super(MainFrame.getInstance(), "在线提交");
    	setSize(700, 800);
    	initComponents();
    }
    
    public String getProID(){
        return proID;
    }
    public void setProID(String id){
    	proID = id;
        System.out.println(proID);
        try {
            map = HttpUtil.getProInfo(proID);
            } 
        catch (IOException ex) {
            Logger.getLogger(SubOnlineUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        renewPanel();
    }
   
    

    private void renewPanel(){    
        jTextArea1.removeAll();
        jTextArea2.removeAll();
        jTextArea3.removeAll();
        jTextArea4.removeAll();
        System.out.println(map.get("Problem"));
        jTextArea1.setText(map.get("Problem"));
        System.out.println(map.get("ProblemDescription"));
        jTextArea2.setText(map.get("ProblemDescription"));
        System.out.println(map.get("Input"));
        jTextArea3.setText(map.get("Input"));
        System.out.println(map.get("Output"));
        jTextArea4.setText(map.get("Output"));
        
    }
    
    public void initComponents() {
        
        jPanel1 = new javax.swing.JPanel();
        proName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JLabel();
        proDescribe = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JLabel();
        input = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JLabel();
        output = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JLabel();
        language = new javax.swing.JLabel();
        languageComboBox = new javax.swing.JComboBox();
        submit = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        changePro = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();


        proName.setFont(new java.awt.Font("微软雅黑", 1, 14)); // NOI18N
        proName.setText("题目：");
        

        jScrollPane1.setViewportView(jTextArea1);

        proDescribe.setFont(new java.awt.Font("微软雅黑", 1, 14)); // NOI18N
        proDescribe.setText("题目描述：");
        

        jScrollPane2.setViewportView(jTextArea2);

        input.setFont(new java.awt.Font("微软雅黑", 1, 14)); // NOI18N
        input.setText("输入：");



        jScrollPane3.setViewportView(jTextArea3);

        output.setFont(new java.awt.Font("微软雅黑", 1, 14)); // NOI18N
        output.setText("输出：");

        jScrollPane4.setViewportView(jTextArea4);

  

        language.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        language.setText("语言");
        

        languageComboBox.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        languageComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "G++", "GCC", "C++", "C", "Pascal", "Java" }));
        languageComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getLanguage((String)languageComboBox.getSelectedItem());
            }

            
        });
        
        submit.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        submit.setText("提交");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        changePro.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        changePro.setText("换题");
        changePro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
       //         askForProID();
            }

           
        });

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane2.setViewportView(jTextArea5);

       
        

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(proName)
                                    .addComponent(input)
                                    .addComponent(output)
                                    .addComponent(language)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(proDescribe)))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(languageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(148, 148, 148)
                                .addComponent(changePro)
                                .addGap(164, 164, 164)
                                .addComponent(submit))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextArea2, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextArea3, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextArea4, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(proName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextArea2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proDescribe))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(input)
                    .addComponent(jTextArea3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(output)
                    .addComponent(jTextArea4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(language)
                    .addComponent(languageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submit)
                    .addComponent(changePro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        

    }
    private void submitActionPerformed(java.awt.event.ActionEvent evt) {   
        userID = JOptionPane.showInputDialog(SubOnlineUI.this, "请输入用户ID");
        userPassword = JOptionPane.showInputDialog(SubOnlineUI.this, "请输入密码");
    }
    private String getLanguage(String language) {
            return language;    
    }
}