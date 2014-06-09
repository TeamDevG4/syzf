package org.group4.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.group4.util.HttpUtil;
import org.group4.util.PostHttp;

public class SubOnlineUI extends javax.swing.JDialog {
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JEditorPane problemEditorPane;
    private javax.swing.JTextArea jTextArea2;
    private String userID =null;
    static String userPassword;
    private String proID;
    private String language;
    String problem;
    private String usercode;
    private static Map<String, String> passwordMem = new HashMap<String, String>();
    private GridBagLayout layout;
    
    public SubOnlineUI(String user) {
    	super(MainFrame.getInstance(), "在线提交", true);
    	setSize(800,600);
    	setLocationRelativeTo(MainFrame.getInstance());
        userID = user;
    	initComponents();
    }
    public void setProID(String id){
    	proID = id;
        problem = HttpUtil.getProInfo(proID);
        jLabel1.setText("Problem" + id);
        renewPanel();
    }
    private void renewPanel(){
		problemEditorPane.setText(problem);;
    }
    
    public void initComponents() {
        
		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		problemEditorPane = new javax.swing.JEditorPane();
		problemEditorPane.setContentType("text/html");
		problemEditorPane.setEditable(false);
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jComboBox1 = new javax.swing.JComboBox();
		jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("微软雅黑", 1, 24)); // NOI18N
        jLabel1.setText("Problem");

        jScrollPane1.setViewportView(problemEditorPane);
        
        jTextArea2.setLineWrap(true);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);
        
        jComboBox1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "G++", "GCC", "C++", "C", "Pascal", "Java" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setLanguage((String)jComboBox1.getSelectedItem());
            }

            
        });

        jButton1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jButton1.setText("提交");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        

    }
    private void submitActionPerformed(java.awt.event.ActionEvent evt) { 
        usercode=jTextArea2.getText();
        if(passwordMem.containsKey(userID)){
        	userPassword = passwordMem.get(userID);
        }
        boolean loginStatus = PostHttp.loginAcm(userID, userPassword);
        if(!loginStatus){
            userPassword = PasswordDialog.showPasswordDialog();
            loginStatus = PostHttp.loginAcm(userID, userPassword);
            if(!loginStatus){
                JOptionPane.showMessageDialog(SubOnlineUI.this, "密码错误");
                userPassword = null;
            }else{
            	if(!passwordMem.containsKey(userID)){
                	passwordMem.put(userID, userPassword);
                }
            	String str = PostHttp.submit(userID, "0", proID, language, usercode);
            
            	JOptionPane.showMessageDialog(SubOnlineUI.this, str);
            }
        }else{     
            if(!loginStatus){
                JOptionPane.showMessageDialog(SubOnlineUI.this, "密码错误");
                userPassword = PasswordDialog.showPasswordDialog();
            }else{            
            	String str = PostHttp.submit(userID, "0", proID, language, usercode);
            	JOptionPane.showMessageDialog(SubOnlineUI.this, str);
            }
        }
    }
    private void setLanguage(String str){
        switch(str){
            case "G++":
                language = "0";
                break;
            case "GCC":
                language = "1";
                break;
            case "C++":
                language = "2";
                break;
            case "C":
                language = "3";
                break;
            case "Pascal":
                language = "4";
                break;
            case "Java":
                language = "5";
                break;           
        }
    }
}