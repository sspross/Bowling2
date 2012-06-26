package ch.hsz.bowling.util;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jCharts.axisChart.AxisChart;
import org.jCharts.chartData.AxisChartDataSet;
import org.jCharts.chartData.ChartDataException;
import org.jCharts.chartData.DataSeries;
import org.jCharts.encoders.PNGEncoder;
import org.jCharts.properties.AxisProperties;
import org.jCharts.properties.ChartProperties;
import org.jCharts.properties.LegendProperties;
import org.jCharts.properties.LineChartProperties;
import org.jCharts.properties.PointChartProperties;
import org.jCharts.properties.PropertyException;
import org.jCharts.test.TestDataGenerator;
import org.jCharts.types.ChartType;

/**
 * Chart
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.3
 */
public class Chart extends JFrame {
	private static final long serialVersionUID = -8083235578865937307L;

	private JPanel panel;

//	public Chart(String title, String xAxisTitle, String yAxisTitle, ArrayList dataList) throws ChartDataException, PropertyException	{
//		this.setSize(500, 500);
//		this.panel = new JPanel(true);
//		this.panel.setSize(500, 500);
//		this.getContentPane().add(this.panel);
//		this.setVisible(true);
//		
//		//Line
//		String[] xAxisLabels = {"250", "45", "-36", "66", "145", "80", "55", "250", "45", "-36", "66", "145", "80", "55"};
////		String[] xAxisLabels = {"250", "45", "-36", "66", "145", "80", "55", "250"};
//		double[][] data = new double[][]{{250, 45, -36, 66, 145, 80, 55, 250, 45, -36, 66, 145, 80, 55}, {1250, 145, -136, 66, 145, 80, 55, 250, 45, -36, 66, 145, 80, 55}};
//		
////		String[] xAxisLabels = new String[dataList.size()];
////		double[][] data = new double[dataList.size()][1];
////		
////		for (int ii = 0; ii < dataList.size(); ii++) {
////			xAxisLabels[ii] = Double.toString(((Vector) dataList.get(ii)).getX());
////
////			data [ii][0] = ((Vector) dataList.get(ii)).getY();
//////			data [ii][1] = ((Vector) dataList.get(ii)).getY();
////		}
//		
//		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );
//		
//		String[] legendLabels= {"Bugs", "MUH"};
//		Paint[] paints= TestDataGenerator.getRandomPaints(2);
//
//		Stroke[] strokes = {LineChartProperties.DEFAULT_LINE_STROKE, LineChartProperties.DEFAULT_LINE_STROKE};
//		Shape[] shapes = {PointChartProperties.SHAPE_CIRCLE, PointChartProperties.SHAPE_SQUARE};
//		LineChartProperties lineChartProperties = new LineChartProperties(strokes, shapes);
//
//		AxisChartDataSet axisChartDataSet = new AxisChartDataSet(data, legendLabels, paints, ChartType.LINE, lineChartProperties);
//		dataSeries.addIAxisPlotDataSet(axisChartDataSet);
//
//		ChartProperties chartProperties = new ChartProperties();
//		AxisProperties axisProperties = new AxisProperties();
//		LegendProperties legendProperties = new LegendProperties();
//
//		AxisChart axisChart = new AxisChart(dataSeries, chartProperties, axisProperties, legendProperties, 450, 450);
//
//		axisChart.setGraphics2D((Graphics2D) this.panel.getGraphics());
//		axisChart.render();
//		
//		try {
//			PNGEncoder.encode( axisChart, new FileOutputStream("C:/Temp/Bowling.png", false));
//		} catch (Exception e) {
//			FileWriter.writeException("Chart::<constructor>", e.getMessage());
//			e.printStackTrace();
//			System.exit(666);
//		}
//		
//		
//		//this.panel.getGraphics().drawImage( bufferedImage, 0, 0, this );
//
//
//		addWindowListener( new java.awt.event.WindowAdapter()
//		{
//			public void windowClosing( WindowEvent windowEvent )
//			{
//				exitForm( windowEvent );
//			}
//		}
//		);
//	}

	public Chart(String title, String xAxisTitle, String yAxisTitle, ArrayList dataList) {
		this.setSize(500, 500);
		this.panel = new JPanel(true);
		this.panel.setSize(500, 500);
		this.getContentPane().add(new ConnectionPanel());
		this.setVisible(true);

		addWindowListener( new java.awt.event.WindowAdapter()
		{
			public void windowClosing( WindowEvent windowEvent )
			{
				exitForm( windowEvent );
			}
		}
		);
	}
	
	class ConnectionPanel extends JPanel
	{
	    public ConnectionPanel()
	    {
	        setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.weightx = 1.0;
	        gbc.weighty = 1.0;
	        add(new JButton("Button 1"), gbc);
	        gbc.gridwidth = gbc.REMAINDER;
	        add(new JButton("Button 2"), gbc);
	        gbc.gridwidth = 1;
	        add(new JButton("Button 3"), gbc);
	        gbc.gridwidth = gbc.REMAINDER;
	        add(new JButton("Button 4"), gbc);
	        add(new JButton("Button 5"), gbc);
	    }
	 
	    public void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D)g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                            RenderingHints.VALUE_ANTIALIAS_ON);
	        int width = getWidth();
	        int height = getHeight();
	        Point[] p = getCenterPoints();
	        int drawTo = 1;
	        for(int i = 0; i < p.length; i++)
	            for(int j = drawTo++; j < p.length; j++)
	                g2.draw(new Line2D.Double(p[i].x, p[i].y, p[j].x, p[j].y));
	    }
	 
	    private Point[] getCenterPoints()
	    {
	        Component[] c = getComponents();
	        Point[] p = new Point[c.length];
	        Rectangle r;
	        for(int i = 0; i < c.length; i++)
	        {
	            r = c[i].getBounds();
	            p[i] = new Point();
	            p[i].x = (int)r.getCenterX();
	            p[i].y = (int)r.getCenterY();
	        }
	        return p;
	    }
	}
	
	/*********************************************************************************
	 * Exit the Application
	 *
	 * @param windowEvent
	 ***********************************************************************************/
	void exitForm( WindowEvent windowEvent )
	{
		System.exit( 0 );
	}
}
