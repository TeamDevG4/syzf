/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import org.group4.closabletabbed.TabbedPane;
import org.group4.util.URLOpener;

/**
 *
 * @author Kam
 */
public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8754196724432614525L;
	private static final MainFrame instance;
	private JMenuBar menuBar;
	private JMenu accountMenu, toolMenu;
	private JMenuItem addNewAccount, addTypes, exploreHDU;
	private TabbedPane closableTabbedPane;
	
	static{
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;
            }
        }
		instance = new MainFrame();
		BufferedImage icon;
        try {
            icon = ImageIO.read(new File("res/tocas.png"));
            instance.setIconImage(icon);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public static MainFrame getInstance(){
		return instance;
	}
	
	private MainFrame(){
		super("谁与争锋");
		initMenus();
		initContents();
	}
	
	private void initMenus(){
		menuBar = new JMenuBar();
		accountMenu = new JMenu("账号");
		addNewAccount = new JMenuItem("添加账号");
		addNewAccount.addActionListener(new AddNewAccountListener());
		accountMenu.add(addNewAccount);
		menuBar.add(accountMenu);
		toolMenu = new JMenu("工具");
		addTypes = new JMenuItem("添加类型");
		addTypes.addActionListener(new AddTypesListener());
		exploreHDU = new JMenuItem("刷题去");
		exploreHDU.addActionListener(new ExploreHDUListener());
		toolMenu.add(addTypes);
		toolMenu.add(exploreHDU);
		menuBar.add(toolMenu);
		setJMenuBar(menuBar);
	}
	
	private void initContents(){
		closableTabbedPane = new TabbedPane();
		this.getContentPane().add(closableTabbedPane);
		closableTabbedPane.setCloseButtonEnabled(true);
	}
	
	public static void main(String[] args){
		MainFrame.getInstance().setVisible(true);
    	MainFrame.getInstance().setDefaultCloseOperation(EXIT_ON_CLOSE);
		MainFrame.getInstance().setSize(800, 600);
		MainFrame.getInstance().setVisible(true);
		MainFrame.getInstance().setLocationRelativeTo(null);
	}
	
	private class ExploreHDUListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			URLOpener opener = new URLOpener();
	        opener.openURL("http://acm.hdu.edu.cn");
		}
	}
	
	private class AddTypesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new AddTypesDialog().show();
		}
	}
	
	private class AddNewAccountListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			LoginForm login = new LoginForm(MainFrame.this);
			login.show();
			if(login.getUserID() != null){
				closableTabbedPane.addTab(login.getUserID(), null, 
						new FunctionalTabbedPane(login.getUserID()));
			}
		}
	}
}
