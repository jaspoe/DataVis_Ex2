import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

public class Main {
	int selectedHeight = 50;
		public int getSelectedHeight() {
			return selectedHeight;
		}
		public void setSelectedHeight(int selectedHeight) {
			this.selectedHeight = selectedHeight;
		}
		
	//create UI and insert all the plots
	public void display(){
		//create main window
		JFrame mainWindow = new JFrame("TabChart");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JTabbedPane jtp = new JTabbedPane();
		
		//create pane 1
		JPanel jPanel1 = new JPanel();
		jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
		
		//create slider
		final int HIGHT_MIN = 0;
		final int HIGHT_MAX = 100;
		final int HIGHT_INIT = 50;
		
		JSlider hightSelect = new JSlider(JSlider.HORIZONTAL,
											HIGHT_MIN, HIGHT_MAX, HIGHT_INIT);
		ChangeListener hightListener = new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				JSlider source = (JSlider)e.getSource();
				int value = source.getValue();
				if(value != getSelectedHeight()){
					setSelectedHeight(value);
					updatePlot();
				}
				
			}
			public void updatePlot(){
				jPanel1.remove(1);
				jPanel1.remove(1);
				jPanel1.add(createChart1(createDatasetPlot1(getSelectedHeight())));
				jPanel1.add(createChart2(createDatasetPlot2(getSelectedHeight())));
				jPanel1.updateUI();
			}
		};
		hightSelect.addChangeListener(hightListener);
		hightSelect.setMajorTickSpacing(20);
		hightSelect.setMinorTickSpacing(2);
		hightSelect.setPaintTicks(true);
		hightSelect.setPaintLabels(true);
		jPanel1.add(hightSelect);
		
		//create plot 1

//		ImageIcon tempImg1 = new ImageIcon("media/XJYnz.png");
//		JLabel label1 = new JLabel("", tempImg1, JLabel.CENTER);
//		area1.add(label1);
		jPanel1.add(createChart1(createDatasetPlot1(getSelectedHeight())));
		
		//create plot 2
//		ImageIcon tempImg2 = new ImageIcon("media/tjOQZ.png");
//		JLabel label2 = new JLabel("", tempImg2, JLabel.CENTER);
//		area2.add(label2);
		jPanel1.add(createChart2(createDatasetPlot2(getSelectedHeight())));
		

		//create pane 2
		JPanel jPanel2 = new JPanel();
		jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
		
		//create area 3 for plot 3 second pane
		JPanel area3 = new JPanel();
		area3.add(createChart3());

		//create area 4 for plot 4 second pane
		JPanel area4 = new JPanel();
		area4.add(createChart4());
		
		//add areas to panel 2
		jPanel2.add(area3);
		jPanel2.add(area4);
		
		//add Panels to TabbedPane
		jtp.add("Plot 1 and 2", jPanel1);
		jtp.add("Plot 3", jPanel2);
		
		//finish UI
		mainWindow.add(jtp);
		mainWindow.pack();
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}
	
    //create Sample Data set for plot2
    private static XYZDataset createDatasetPlot1(int height) { 
    	int temp = height;
        return new XYZDataset() { 
            public int getSeriesCount() { 
                return 1; 
            } 
            public int getItemCount(int series) { 
                return 10000; 
            } 
            public Number getX(int series, int item) { 
                return new Double(getXValue(series, item)); 
            } 
            public double getXValue(int series, int item) { 
                return item / 100 - 50; 
            } 
            public Number getY(int series, int item) { 
                return new Double(getYValue(series, item)); 
            } 
            public double getYValue(int series, int item) { 
                return item - (item / 100) * 100 - 50; 
            } 
            public Number getZ(int series, int item) { 
                return new Double(getZValue(series, item)); 
            } 
            public double getZValue(int series, int item) { 
                double x = getXValue(series, item); 
                double y = getYValue(series, item); 
                return Math.sin(Math.sqrt(x * x + y * y + x * y) / temp); 
            } 
            public void addChangeListener(DatasetChangeListener listener) { 
                // ignore - this dataset never changes 
            } 
            public void removeChangeListener(DatasetChangeListener listener) { 
                // ignore 
            } 
            public DatasetGroup getGroup() { 
                return null; 
            } 
            public void setGroup(DatasetGroup group) { 
                // ignore 
            } 
            public Comparable getSeriesKey(int series) { 
                return "sin(sqrt(x + y))"; 
            } 
            public int indexOf(Comparable seriesKey) { 
                return 0; 
            } 
            public DomainOrder getDomainOrder() { 
                return DomainOrder.ASCENDING; 
            }         
        }; 
    } 
    
    //create Sample Data set for plot2
    private static XYZDataset createDatasetPlot2(int height) { 
        return new XYZDataset() { 
            public int getSeriesCount() { 
                return 1; 
            } 
            public int getItemCount(int series) { 
                return 10000; 
            } 
            public Number getX(int series, int item) { 
                return new Double(getXValue(series, item)); 
            } 
            public double getXValue(int series, int item) { 
                return item / 100 - 50; 
            } 
            public Number getY(int series, int item) { 
                return new Double(getYValue(series, item)); 
            } 
            public double getYValue(int series, int item) { 
                return item - (item / 100) * 100 - 50; 
            } 
            public Number getZ(int series, int item) { 
                return new Double(getZValue(series, item)); 
            } 
            public double getZValue(int series, int item) { 
                double x = getXValue(series, item); 
                double y = getYValue(series, item); 
                return Math.sin(Math.sqrt(x * x + y * y) / height); 
            } 
            public void addChangeListener(DatasetChangeListener listener) { 
                // ignore - this dataset never changes 
            } 
            public void removeChangeListener(DatasetChangeListener listener) { 
                // ignore 
            } 
            public DatasetGroup getGroup() { 
                return null; 
            } 
            public void setGroup(DatasetGroup group) { 
                // ignore 
            } 
            public Comparable getSeriesKey(int series) { 
                return "sin(sqrt(x + y))"; 
            } 
            public int indexOf(Comparable seriesKey) { 
                return 0; 
            } 
            public DomainOrder getDomainOrder() { 
                return DomainOrder.ASCENDING; 
            }         
        }; 
    }
	
    //create Chart 1
    private static ChartPanel createChart1(XYZDataset dataset) { 
        NumberAxis xAxis = new NumberAxis("X"); 
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); 
        xAxis.setLowerMargin(0.0); 
        xAxis.setUpperMargin(0.0); 
        NumberAxis yAxis = new NumberAxis("Y"); 
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); 
        yAxis.setLowerMargin(0.0); 
        yAxis.setUpperMargin(0.0); 
        XYBlockRenderer renderer = new XYBlockRenderer(); 
        PaintScale scale = new GrayPaintScale(-2.0, 1.0);
        renderer.setPaintScale(scale); 
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer); 
        plot.setBackgroundPaint(Color.lightGray); 
        plot.setDomainGridlinesVisible(false); 
        plot.setRangeGridlinePaint(Color.white); 
        JFreeChart chart = new JFreeChart("Temperature Contour Plot", plot); 
        chart.removeLegend(); 
        //chart.setBackgroundPaint(Color.white); 
        NumberAxis scaleAxis = new NumberAxis("Scale");
        scaleAxis.setAxisLinePaint(Color.white);
        scaleAxis.setTickMarkPaint(Color.white);
        scaleAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 7));
        PaintScaleLegend legend = new PaintScaleLegend(new GrayPaintScale(),
                scaleAxis);
        legend.setStripOutlineVisible(false);
        legend.setSubdivisionCount(20);
        legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        legend.setAxisOffset(5.0);
        legend.setMargin(new RectangleInsets(5, 5, 5, 5));
        legend.setFrame(new BlockBorder(Color.black));
        legend.setPadding(new RectangleInsets(10, 10, 10, 10));
        legend.setStripWidth(10);
        legend.setPosition(RectangleEdge.RIGHT);
        //legend.setBackgroundPaint(new Color(120, 120, 180));
        chart.addSubtitle(legend);
        //chart.setBackgroundPaint(new Color(180, 180, 250));
        ChartUtilities.applyCurrentTheme(chart);
        return new ChartPanel(chart); 
    }
    
    //create Chart 2
    private static ChartPanel createChart2(XYZDataset dataset) { 
        NumberAxis xAxis = new NumberAxis("X"); 
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); 
        xAxis.setLowerMargin(0.0); 
        xAxis.setUpperMargin(0.0); 
        NumberAxis yAxis = new NumberAxis("Y"); 
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); 
        yAxis.setLowerMargin(0.0); 
        yAxis.setUpperMargin(0.0); 
        XYBlockRenderer renderer = new XYBlockRenderer(); 
        PaintScale scale = new GrayPaintScale(-2.0, 1.0); 
        renderer.setPaintScale(scale); 
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer); 
        plot.setBackgroundPaint(Color.lightGray); 
        plot.setDomainGridlinesVisible(false); 
        plot.setRangeGridlinePaint(Color.white); 
        JFreeChart chart = new JFreeChart("Pressure Contour Plot", plot); 
        chart.removeLegend(); 
        //chart.setBackgroundPaint(Color.white); 
        NumberAxis scaleAxis = new NumberAxis("Scale");
        scaleAxis.setAxisLinePaint(Color.white);
        scaleAxis.setTickMarkPaint(Color.white);
        scaleAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 7));
        PaintScaleLegend legend = new PaintScaleLegend(new GrayPaintScale(),
                scaleAxis);
        legend.setStripOutlineVisible(false);
        legend.setSubdivisionCount(20);
        legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        legend.setAxisOffset(5.0);
        legend.setMargin(new RectangleInsets(5, 5, 5, 5));
        legend.setFrame(new BlockBorder(Color.black));
        legend.setPadding(new RectangleInsets(10, 10, 10, 10));
        legend.setStripWidth(10);
        legend.setPosition(RectangleEdge.RIGHT);
        //legend.setBackgroundPaint(new Color(120, 120, 180));
        chart.addSubtitle(legend);
        //chart.setBackgroundPaint(new Color(180, 180, 250));
        ChartUtilities.applyCurrentTheme(chart);
        return new ChartPanel(chart);
    }
	
    //create sample dataset for plot 3
    private static XYDataset createDatasetPlot3() {   
    	   
        // create pressure dataset  
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries pressureSeries = new XYSeries("Pressure");
        Random randomGenerator = new Random();
        double pressure;
        int START = 960;
        int END = 1030;
        //create a datapoint for each minute
        for(int i = 0; i<480; i++){
        	pressure = showRandomInteger(START, END, randomGenerator);
        	pressure = pressure / 100;
        	pressureSeries.add(i, pressure);
        }

        dataset.addSeries(pressureSeries);
        return dataset;   
    }
    
    //create sample dataset for plot4
    private static XYDataset createDatasetPlot4() {   
 	   
        // create temperature dataset  
  
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries temperatureSeries = new XYSeries("Temperature");
        Random randomGenerator = new Random();
        double temperature;
        int START = 10;
        int END = 35;
        //create a datapoint for each minute
        for(int i = 0; i<480; i++){
        	temperature = showRandomInteger(START, END, randomGenerator);
        	temperatureSeries.add(i, temperature);
        }

        dataset.addSeries(temperatureSeries);
        return dataset;   
    }
    
    //creating random integer in a set range
    private static int showRandomInteger(int aStart, int aEnd, Random aRandom){
        if (aStart > aEnd) {
          throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        int randomNumber =  (int)(fraction + aStart);
        return randomNumber;
      }
    
    //create Chart 3
    private static ChartPanel createChart3(){
    	JFreeChart chart3 = ChartFactory.createXYLineChart(
    			"Pressure over 48 hours", // title,
                "Minutes", // xAxisLabel,
                "Pressure (dBar)", // yAxisLabel,
                createDatasetPlot3(), // dataset,
                PlotOrientation.VERTICAL, // orientation,
                true, // legend,
                true, // tooltips,
                false // urls
                );
    	XYPlot xyPlot = (XYPlot) chart3.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(0, 480);
        domain.setTickUnit(new NumberTickUnit(60));
        domain.setVerticalTickLabels(true);
        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(9.4, 10.5);
        range.setTickUnit(new NumberTickUnit(0.1));
      
    	return new ChartPanel(chart3);
    }
    
    //create Chart 4
    private static ChartPanel createChart4(){
    	JFreeChart chart4 = ChartFactory.createXYLineChart(
    			"Temperature over 48 hours", // title,
                "Minutes", // xAxisLabel,
                "Temperature (°C)", // yAxisLabel,
                createDatasetPlot4(), // dataset,
                PlotOrientation.VERTICAL, // orientation,
                true, // legend,
                true, // tooltips,
                false // urls
                );
    	XYPlot xyPlot = (XYPlot) chart4.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.red);
        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(0, 480);
        domain.setTickUnit(new NumberTickUnit(60));
        domain.setVerticalTickLabels(true);
        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(0, 41);
        range.setTickUnit(new NumberTickUnit(10));
      
    	return new ChartPanel(chart4);
    }
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				new Main().display();
			}
		});

	}
}
