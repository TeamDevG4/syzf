package org.group4.ui;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UserFunctionPanel extends BackgroundPanel{

	private static final long serialVersionUID = 6229930488409728941L;
	private String userID;
	private JPanel history, achievement, steps, recommend, submit;
	
	public UserFunctionPanel(String id) {
		super();
		userID = id;
		initTabbedPane();
    }
	
	private void initTabbedPane(){
        setLayout(new CardLayout());
        history = new MyHistoryUI(userID);
        add("我的奋斗史", history);
        steps = new MyStepsUI(userID);
        add("我的ACM Steps", steps);
        achievement = new MyAchievementUI(userID);
        add("我的亮点", achievement);
        recommend = new RecommendUI(userID);
        add("题目推荐", recommend);
        submit = new SubOnlineUI();
        add("在线提交",submit);
	}
	
	public void showCard(String card){
		CardLayout layout = (CardLayout)getLayout();
		layout.show(this, card);
	}
}
