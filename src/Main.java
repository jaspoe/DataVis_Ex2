import java.awt.EventQueue;

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

public class Main {
	int selectedHight = 0;
		public int getSelectedHight() {
			return selectedHight;
		}
		public void setSelectedHight(int selectedHight) {
			this.selectedHight = selectedHight;
		}




	public void display(){
		//create main window
		JFrame mainWindow = new JFrame("TabChart");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JTabbedPane jtp = new JTabbedPane();
		
		//create pane 1
		JPanel jPanel1 = new JPanel();
		jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
		
		//create area 0 with the slider
		JPanel area0 = new JPanel();
		final int HIGHT_MIN = 0;
		final int HIGHT_MAX = 100;
		final int HIGHT_INIT = 50;
		
		JSlider hightSelect = new JSlider(JSlider.HORIZONTAL,
											HIGHT_MIN, HIGHT_MAX, HIGHT_INIT);
		ChangeListener hightListener = new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				JSlider source = (JSlider)e.getSource();
				setSelectedHight(source.getValue());
				System.out.println("Hight has been changed to: "+getSelectedHight());
			}
		};
		hightSelect.addChangeListener(hightListener);
		area0.add(hightSelect);
		
		//create area 1 with a temp image for plot 1
		JPanel area1 = new JPanel();
		ImageIcon tempImg1 = new ImageIcon("media/XJYnz.png");
		JLabel label1 = new JLabel("", tempImg1, JLabel.CENTER);
		area1.add(label1);
		
		//create area 2 with a temp image for plot 2
		JPanel area2 = new JPanel();
		ImageIcon tempImg2 = new ImageIcon("media/tjOQZ.png");
		JLabel label2 = new JLabel("", tempImg2, JLabel.CENTER);
		area2.add(label2);
		
		//add Areas to Panel 1
		jPanel1.add(area0);
		jPanel1.add(area1);
		jPanel1.add(area2);
		
		//add Panels to TabbedPane
		jtp.add("Plot 1 and 2", jPanel1);
		//jtp.add("Plot 3", jPanel2);
		
		//finish UI
		mainWindow.add(jtp);
		mainWindow.pack();
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
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
