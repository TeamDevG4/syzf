package org.group4.ui;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.group4.util.FileUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart{

    @SuppressWarnings("deprecation")
	private static CategoryDataset  createDataset(String[] ids,Date startDate,Date endDate) {
    	DefaultCategoryDataset data = new DefaultCategoryDataset();
    	Date xDate;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    	int totalMonths = (endDate.getYear() - startDate.getYear()) * 12 + endDate.getMonth() - startDate.getMonth() + 1;
        for(int i = 0;i < ids.length;i++){
        	int[] vec = FileUtil.countUserSubmission(ids[i], startDate, endDate, "Accepted", true);
        	xDate = (Date) startDate.clone();
        	for(int j = 0;j < totalMonths;j++){
        		data.addValue(vec[j], ids[i], sdf.format(xDate));
        		if(xDate.getMonth() == 11){
        			xDate.setYear(xDate.getYear() + 1);
        			xDate.setMonth(0);
        		}else{
        			xDate.setMonth(xDate.getMonth() + 1);
        		}
        	}
        }
        return data;
    }

	public static ChartPanel createAcceptedLineChart(String[] ids,Date startDate,Date endDate,String title){
    	CategoryDataset  dataset = createDataset(ids, startDate, endDate);
        JFreeChart jfreechart = ChartFactory.createLineChart(title, "日期", "数量", dataset, PlotOrientation.VERTICAL, true, true, true);
        CategoryPlot  plot = (CategoryPlot) jfreechart.getPlot();
        CategoryAxis dateaxis = plot.getDomainAxis();
        dateaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12)); 
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));
        return new ChartPanel(jfreechart, true);
    }
}