package org.group4.ui;

import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.group4.util.FileUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class BarGraph {
	public  static ChartPanel createBarGraph(String[] ids,Date startDate, Date endDate, String title){
		DefaultCategoryDataset dataset = createDataSet(ids, startDate, endDate);
		return setUpChartPanel(title, dataset);
	}

	public static ChartPanel createDifferenceBarGraph(String ids[], String title){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate, endDate;
		try {
			startDate = sdf.parse("2000-01-01");
			endDate = sdf.parse("2100-12-31");
			DefaultCategoryDataset dataset = createDataSet(ids, startDate, endDate);
			return setUpChartPanel(title, dataset);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	private static ChartPanel setUpChartPanel(String title, DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart3D(title,
			"题目分类", "数量", dataset, PlotOrientation.VERTICAL,
			true,           
			true,         
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
		//显示每个柱的数值，并修改该数值的字体属性  
        BarRenderer3D renderer = new BarRenderer3D();  
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
        renderer.setBaseItemLabelsVisible(true);  
        //默认的数字显示在柱子中，通过如下两句可调整数字的显示  
        //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题  
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(  
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));  
        renderer.setItemLabelAnchorOffset(10D);  
        renderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 12));  
        renderer.setItemLabelsVisible(true);  
        //设置每个地区所包含的平行柱的之间距离  
        //renderer.setItemMargin(0.3);  
        plot.setRenderer(renderer);
		return new ChartPanel(chart, true);
	}

	private static DefaultCategoryDataset createDataSet(String[] ids,
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