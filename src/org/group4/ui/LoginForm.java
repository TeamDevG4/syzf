/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.group4.util.HttpUtil;
import org.group4.util.ProgressBar;
import org.group4.util.URLOpener;


/**
 *
 * @author Kam
 */
public class LoginForm extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6153217815851146510L;
	private String userID;
	private JTextField usernameTextField;
	private JLabel tip;
	
	public LoginForm(JFrame frame){
		super(frame, "输入该账号的ID", true);
		setSize(400, 300);
		this.setLocationRelativeTo(frame);
		initComponents();
		setResizable(false);
	}
	
	private void initComponents() {
		BackgroundPanel panel = new BackgroundPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		tip = new JLabel();
		tip.setFont(new Font("黑体", Font.BOLD, 28));
		tip.setForeground(Color.RED);
		constraints.weightx = 6.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.gridx = constraints.gridy = 0;
		panel.add(tip, constraints);
		usernameTextField = new JTextField();
		usernameTextField.addActionListener(new ConfirmActionListener());
		constraints.weightx = 6.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.gridy = 1;
		panel.add(usernameTextField, constraints);
		JButton confirmButton = new JButton("确定");
		confirmButton.addActionListener(new ConfirmActionListener());
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 3.0;
		constraints.gridwidth = 1;
		panel.add(confirmButton, constraints);
		JButton registerButton = new JButton("注册");
		registerButton.addActionListener(new RegisterActionListener());
		constraints.gridx = 1;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(registerButton, constraints);
		add(panel);
	}
	
	public String getUserID() {
		return userID;
	}

	private class ConfirmActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			userID = usernameTextField.getText();
			HttpUtil.setUsername(getUserID());
			if(!HttpUtil.judgeID()){
				userID = null;
				tip.setText("错误：不存在此账号!");
			}else{
	            new ProgressBar();
	            dispose();
			}
		}
	}
	
	private class RegisterActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			URLOpener opener = new URLOpener();
	        opener.openURL("http://acm.hdu.edu.cn/register.php");
	        dispose();
		}
	}
}
