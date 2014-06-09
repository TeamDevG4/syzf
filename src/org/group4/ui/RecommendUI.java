/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.group4.util.FileUtil;


/**
 *
 * @author Kam
 */
public class RecommendUI extends JPanel {

    private final String user;
    private JComboBox<String> typeComboBox;
    private JPanel recommendPanel;
	/**
     * Creates new form RecommendUI
     */
    public RecommendUI(String id) {
    	user = id;
    	setOpaque(false);
        initComponents();
    }

    private void initComponents() {
    	GridBagConstraints constraints = new GridBagConstraints();
    	setLayout(new GridBagLayout());
    	constraints.weightx = 1.0;
    	constraints.fill = GridBagConstraints.HORIZONTAL;
    	constraints.gridx = constraints.gridy = 0;
    	JPanel topPanel = new JPanel();
    	initTopPanel(topPanel);
    	add(topPanel, constraints);
    	constraints.weighty = 1.0;
    	constraints.fill = GridBagConstraints.BOTH;
    	constraints.gridy = 1;
    	initRecommendPanel();
    	add(recommendPanel, constraints);
    }

    
    private class ProblemTag extends JLabel{
		private static final long serialVersionUID = 1L;

		public ProblemTag(final String txt){
    		super(txt);
    		addMouseListener(new MouseAdapter(){
    			public void mouseClicked(MouseEvent evt){
    				SubOnlineUI subOnline = new SubOnlineUI(user);
    				subOnline.setProID(txt);
    				subOnline.setVisible(true);
    			}
    		});
    	}
    	
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		Dimension size = this.getSize();
    		g.setColor(Color.CYAN);
    		g.fillRect(0, 0, size.width, size.height);
    		g.setColor(Color.BLACK);
    		g.setFont(new Font("宋体", Font.BOLD, 30));
    		FontMetrics fm = g.getFontMetrics();
    		String str = getText(); 
    		int stringWidth = fm.stringWidth(str); 
    		int stringAscent = fm.getAscent(); 
    		int stringDescent = fm.getDescent (); 
    		int x = this.getWidth() / 2 - stringWidth / 2; 
    		int y = this.getHeight() / 2 + (stringAscent - stringDescent) / 2; 
    		g.drawString(str, x, y);
    	}
    }
    
    private void initRecommendPanel() {
    	recommendPanel = new JPanel();
		recommendPanel.setLayout(new GridLayout(3, 3, 5, 5));
	}

    
    private void addProblem(String num){
    	ProblemTag label = new ProblemTag(num);
    	recommendPanel.add(label);
    }
    
	private void initTopPanel(JPanel panel){
    	JLabel typeTip = new JLabel("请选择题目的类型");
    	panel.add(typeTip);
    	typeComboBox = new JComboBox<String>();
    	typeComboBox.setModel(new DefaultComboBoxModel<String>(FileUtil.getAllTypes("problemType.txt")));
    	panel.add(typeComboBox);
    	typeComboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent evt) {
				if(evt.getStateChange() == ItemEvent.SELECTED){
					String[] problems = getRecommendedProbs((String)typeComboBox.getSelectedItem());
					recommendPanel.removeAll();
					for(String p : problems){
						addProblem(p);
					}
					recommendPanel.validate();
				}
			}
        });
    }
    
    private String[] getRecommendedProbs(String type){
        Vector<String> doneProbs = FileUtil.getUserDoneProbs(user);
        boolean done[] = new boolean[4000];
        for(int i = 0;i < doneProbs.size();i++){
        	done[Integer.valueOf(doneProbs.get(i)) - 1000] = true;
        }
        String[] probs = FileUtil.getProbsOfType("problemType.txt", type);
        int counter = 0;
        Vector<String> recommended = new Vector<String>();
        for(int i = 0;i < probs.length;i++){
        	if(!done[Integer.valueOf(probs[i]) - 1000]){
        		recommended.add(probs[i]);
        		counter++;
        		if(counter >= 9)break;
        	}
        }
        String ret[] = new String[recommended.size()];
        for(int i = 0;i < recommended.size();i++){
        	ret[i] = recommended.get(i);
        }
        return ret;
    }

}
