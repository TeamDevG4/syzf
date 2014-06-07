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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	private JMenuItem addNewAccount, addTypes, exploreHDU, switchDiffBox, showDiffBox;
	private TabbedPane closableTabbedPane;
	private OurDifferenceUI diffBox;
	
	static{
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Windows".equals(info.getName())) {
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
		diffBox = new OurDifferenceUI();
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
		toolMenu.addChangeListener(new DetectUserStatusListener());
		addTypes = new JMenuItem("添加类型");
		addTypes.addActionListener(new AddTypesListener());
		exploreHDU = new JMenuItem("刷题去");
		exploreHDU.addActionListener(new ExploreHDUListener());
		switchDiffBox = new JMenuItem("在对比盒子中添加");
		switchDiffBox.addActionListener(new SwitchDiffBoxListener());
		showDiffBox = new JMenuItem("显示对比盒子");
		showDiffBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				diffBox.setVisible(true);
			}
		});
		toolMenu.add(addTypes);
		toolMenu.add(exploreHDU);
		toolMenu.add(switchDiffBox);
		toolMenu.add(showDiffBox);
		menuBar.add(toolMenu);
		setJMenuBar(menuBar);
	}
	
	private void initContents(){
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		JPanel buttonPanel = new JPanel();
		layout.setConstraints(buttonPanel, constraints);
		getContentPane().setLayout(layout);
		getContentPane().add(buttonPanel);
		closableTabbedPane = new TabbedPane();
		constraints.weighty = 1.0;
		constraints.gridy = 1;
		layout.setConstraints(closableTabbedPane, constraints);
		getContentPane().add(closableTabbedPane);
		closableTabbedPane.setCloseButtonEnabled(true);
		GridBagLayout buttonLayout = new GridBagLayout();
		buttonPanel.setLayout(buttonLayout);
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		JLabel historyLabel = new JLabel("我的奋斗史", JLabel.CENTER);
		LabelMouseListener lml = new LabelMouseListener();
		historyLabel.addMouseListener(lml);
		historyLabel.setFont(new Font("黑体", Font.BOLD, 18));
		buttonLayout.setConstraints(historyLabel, constraints);
		buttonPanel.add(historyLabel);
		constraints.gridx = 1;
		JLabel acmstepsLabel = new JLabel("我的ACM Steps", JLabel.CENTER);
		acmstepsLabel.addMouseListener(lml);
		acmstepsLabel.setFont(new Font("黑体", Font.BOLD, 18));
		buttonLayout.setConstraints(acmstepsLabel, constraints);
		buttonPanel.add(acmstepsLabel);
		constraints.gridx = 2;
		JLabel achievementLabel = new JLabel("我的亮点", JLabel.CENTER);
		achievementLabel.addMouseListener(lml);
		achievementLabel.setFont(new Font("黑体", Font.BOLD, 18));
		buttonLayout.setConstraints(achievementLabel, constraints);
		buttonPanel.add(achievementLabel);
		constraints.gridx = 3;
		JLabel recommendLabel = new JLabel("题目推荐", JLabel.CENTER);
		recommendLabel.addMouseListener(lml);
		recommendLabel.setFont(new Font("黑体", Font.BOLD, 18));
		buttonLayout.setConstraints(recommendLabel, constraints);
		buttonPanel.add(recommendLabel);
	}
	
	public static void main(String[] args){
		MainFrame.getInstance().setVisible(true);
    	MainFrame.getInstance().setDefaultCloseOperation(EXIT_ON_CLOSE);
		MainFrame.getInstance().setSize(920, 700);
		MainFrame.getInstance().setResizable(false);
		MainFrame.getInstance().setVisible(true);
		MainFrame.getInstance().setLocationRelativeTo(null);
		MainFrame.getInstance().showLoginDialog();
	}
	
	private void showLoginDialog(){
		LoginForm login = new LoginForm(MainFrame.this);
		login.show();
		if(login.getUserID() != null){
			closableTabbedPane.addTab(login.getUserID(), null, 
					new UserFunctionPanel(login.getUserID()));
		}
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
			new AddTypesDialog().setVisible(true);
		}
	}
	
	private class AddNewAccountListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			showLoginDialog();
		}
	}
	
	private class LabelMouseListener extends MouseAdapter{
		public void mouseEntered(MouseEvent evt){
			((JLabel)evt.getSource()).setForeground(Color.RED);
		}
		public void mouseExited(MouseEvent evt){
			((JLabel)evt.getSource()).setForeground(Color.BLACK);
		}
		public void mouseClicked(MouseEvent evt){
			UserFunctionPanel panel = (UserFunctionPanel)closableTabbedPane.getSelectedComponent();
			if(panel != null){
				panel.showCard(((JLabel)evt.getSource()).getText());
			}
		}
	}
	
	private class SwitchDiffBoxListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			UserFunctionPanel panel = (UserFunctionPanel)closableTabbedPane.getSelectedComponent();
			if(panel != null){
				if(diffBox.contains(panel.getUserID())){
					diffBox.delUser(panel.getUserID());
				}else{
					System.out.println("getUser = " + panel.getUserID());
					diffBox.addUser(panel.getUserID());
				}
			}
		}
	}
	
    private class DetectUserStatusListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent evt) {
			UserFunctionPanel panel = (UserFunctionPanel)closableTabbedPane.getSelectedComponent();
    		if(panel != null){
    			switchDiffBox.setEnabled(true);
    			if(diffBox.contains(panel.getUserID())){
    				switchDiffBox.setText("在对比盒子中删除");
    			}else{
    				switchDiffBox.setText("在对比盒子中添加");
    			}
    		}else{
    			switchDiffBox.setEnabled(false);
    		}
		}
    }
}
