import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;




public class Main {
	int selectedHeight = 2;
		public int getSelectedHeight() {
			return selectedHeight;
		}
		public void setSelectedHeight(int selectedHeight) {
			this.selectedHeight = selectedHeight;
		}



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
					System.out.println("Height has been changed to: "+getSelectedHeight());
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
		ImageIcon tempImg3 = new ImageIcon("media/TimeSeriesGraph.jpg");
		JLabel label3 = new JLabel("", tempImg3, JLabel.CENTER);
		area3.add(label3);

		//create area 4 for plot 4 second pane
		JPanel area4 = new JPanel();
		ImageIcon tempImg4 = new ImageIcon("media/tsplot.jpg");
		JLabel label4 = new JLabel("", tempImg4, JLabel.CENTER);
		area4.add(label4);
		
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
