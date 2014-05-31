package org.group4.datechooser;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DatePanel extends JPanel{
	JComboBox<String> yCombo, mCombo;
	private Date date;
	public DatePanel(final Date defaultDate){
		super();
		date = defaultDate;
		GridLayout layout = new GridLayout(1, 2);
		setLayout(layout);
		Vector<String> years = new Vector<>(), months = new Vector<>();
		for(int i = 2007;i <= 2020;i++){
			years.add(String.valueOf(i) + "年");
		}
		for(int i = 1;i <= 12;i++){
			months.add(i + "月");
		}
		ComboBoxModel<String> yModel = new DefaultComboBoxModel<String>(years);
		ComboBoxModel<String> mModel = new DefaultComboBoxModel<String>(months);
		yCombo = new JComboBox<String>(yModel);
		mCombo = new JComboBox<String>(mModel);
		add(yCombo);
		add(mCombo);
		SimpleDateFormat yy = new SimpleDateFormat("yyyy年"), MM = new SimpleDateFormat("M月");
		yCombo.setSelectedItem(yy.format(defaultDate));
		mCombo.setSelectedItem(MM.format(defaultDate));
	}
	public void addChangeListener(ItemListener listener){
		yCombo.addItemListener(listener);
		mCombo.addItemListener(listener);
	}
	
	public void updateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月");
		try {
			date = sdf.parse((String)yCombo.getSelectedItem() + (String)mCombo.getSelectedItem());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}
	
	public Date getDate(){
		return date;
	}
}
