package org.group4.ui;

import java.awt.Font;
import java.util.Date;
import java.util.Map;

import org.group4.util.FileUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarGraph {
	public  static ChartPanel createBarGraph(String[] ids,Date startDate, Date endDate, String title){
		DefaultCategoryDataset dataset = createDateSet(ids, startDate, endDate);
                JFreeChart chart = ChartFactory.createBarChart3D(title,
			"题目分类", "数量", dataset, PlotOrientation.VERTICAL,
			true,           
			false,         
			false
		);
		CategoryPlot plot=chart.getCategoryPlot();
		CategoryAxis domainAxis=plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));
		domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));
		ValueAxis rangeAxis=plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));
		return new ChartPanel(chart, true);
	}

	private static DefaultCategoryDataset createDateSet(String[] ids,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i = 0;i < ids.length;i++){
			Map<String, Integer> map = FileUtil.countUserClassifiedSubmission(ids[i], startDate, endDate);
			for(String key : map.keySet()){
				dataset.addValue(map.get(key), ids[i], key);
			}
		}
		return dataset;
	}
}