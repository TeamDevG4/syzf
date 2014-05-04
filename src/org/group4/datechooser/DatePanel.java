package org.group4.datechooser;

import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DatePanel extends JPanel{
	JComboBox yCombo, mCombo;
	public DatePanel(final Date defaultDate){
		super();
		GridLayout layout = new GridLayout(1, 2);
		setLayout(layout);
		Vector<String> years = new Vector<>(), months = new Vector<>();
		for(int i = 2007;i <= 2020;i++){
			years.add(String.valueOf(i) + "年");
		}
		months.add("一月");
		months.add("二月");
		months.add("三月");
		months.add("四月");
		months.add("五月");
		months.add("六月");
		months.add("七月");
		months.add("八月");
		months.add("九月");
		months.add("十月");
		months.add("十一月");
		months.add("十二月");
		ComboBoxModel yModel = new DefaultComboBoxModel(years);
		ComboBoxModel mModel = new DefaultComboBoxModel(months);
		yCombo = new JComboBox(yModel);
		mCombo = new JComboBox(mModel);
		add(yCombo);
		add(mCombo);
		yCombo.setSelectedIndex(defaultDate.getYear() - 2007 + 1900);
		mCombo.setSelectedIndex(defaultDate.getMonth());
	}
	public void addChangeListener(ItemListener listener){
		yCombo.addItemListener(listener);
		mCombo.addItemListener(listener);
	}
}
