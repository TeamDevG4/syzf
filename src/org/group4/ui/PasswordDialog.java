package org.group4.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPasswordField;

public class PasswordDialog extends JDialog{
	private JPasswordField pf;
	private JButton confirmButton;
	private PasswordDialog(){
		super(MainFrame.getInstance(), "输入密码", true);
		initComponents();
		setSize(300, 100);
		setLocationRelativeTo(MainFrame.getInstance());
	}
	
	private void initComponents(){
		getContentPane().setLayout(new FlowLayout());
		pf = new JPasswordField(30);
		confirmButton = new JButton("确定");
		getContentPane().add(pf);
		getContentPane().add(confirmButton);
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				PasswordDialog.this.setVisible(false);
			}
		});
	}
	
	private String getPassword(){
		return new String(pf.getPassword());
	}
	
	public static String showPasswordDialog(){
		PasswordDialog dialog = new PasswordDialog();
		dialog.setVisible(true);
		return dialog.getPassword();
	}
}
