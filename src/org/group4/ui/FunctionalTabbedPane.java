package org.group4.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FunctionalTabbedPane extends BackgroundPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6229930488409728941L;
	private String userID;
	private JPanel history, achievement, steps, recommend, submit;
	private JTabbedPane tp;
	
	public FunctionalTabbedPane(String id) {
		super();
		userID = id;
		initTabbedPane();
    }
	
	private void initTabbedPane(){
        tp = new JTabbedPane();
        history = new MyHistoryUI(userID);
        tp.addTab("我的奋斗史", history);
        steps = new MyStepsUI(userID);
        tp.addTab("我的ACM Steps", steps);
        achievement = new MyAchievementUI(userID);
        tp.addTab("我的亮点", achievement);
        recommend = new RecommendUI(userID);
        tp.addTab("题目推荐", recommend);
        submit = new SubOnlineUI();
        tp.addTab("在线提交",submit);
        tp.setTabPlacement(JTabbedPane.LEFT);
        tp.addChangeListener(new MyTabChangedListener());
        add(tp);
    }
	
	private boolean changed[] = new boolean[4];
    private class MyTabChangedListener implements ChangeListener{
        private static final int MY_HISTORY = 0, MY_STEPS = 1, MY_ACHIEVEMENT = 2, RECOMMEND = 3, SUBONLINE = 4;
        @Override
        public void stateChanged(ChangeEvent e) {
            switch(((JTabbedPane)e.getSource()).getSelectedIndex()){
                case MY_HISTORY:
                    if(!changed[MY_HISTORY]){
                        changed[MY_HISTORY] = true;
                    }
                    break;
                case MY_STEPS:
                	if(!changed[MY_STEPS]){
                		changed[MY_STEPS] = true;
                		((MyStepsUI)steps).renewChart();
                	}
                    break;
                case MY_ACHIEVEMENT:
                    if(!changed[MY_ACHIEVEMENT]){
                        changed[MY_ACHIEVEMENT] = true;
                        ((MyAchievementUI)achievement).update();
                    }
                    break;
                case SUBONLINE:
                    ((SubOnlineUI)submit).askForProID();
                    break;
                default:
                    break;
            }
        }
    }
}
