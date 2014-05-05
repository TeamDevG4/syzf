package org.group4.util;

import java.awt.BorderLayout;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProgressBarDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProgressingObject po;
	private Window parent;
	private JProgressBar bar;
	private JLabel label;
	private String tip;
	public ProgressBarDialog(Window parent, ProgressingObject pobj){
		po = pobj;
		this.parent = parent;
		setResizable(false);
		this.setUndecorated(true);
		this.setSize(390, 100);
		this.setLocationRelativeTo(parent);
		this.setVisible(true);
		tip = "正在拼了命为您获取用户信息,请稍候……";
	}
	public void show(){
		super.show();
		bar = new JProgressBar();
		bar.setMaximum(po.getTotalProgress());
		label = new JLabel();
		final JPanel mainPane = new JPanel(null);
		mainPane.setLayout(new BorderLayout());
        mainPane.add(bar, BorderLayout.NORTH);
        mainPane.add(label, BorderLayout.CENTER);
        this.getContentPane().add(mainPane);
		new Thread(){
			@Override
			public void run(){
				do{
					bar.setValue(po.getCurrentProgress());
					label.setText(tip + po.getCurrentProgress() + "/" + po.getTotalProgress());
					ProgressBarDialog.this.getContentPane().validate();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}while(po.getCurrentProgress() < po.getTotalProgress());
				label.setText("全部完成!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ProgressBarDialog.this.dispose();
			}
		}.start();
	}
	public static void showProgressBarDialog(Window parent, ProgressingObject pobj){
		new ProgressBarDialog(parent, pobj).show();
	}
}
