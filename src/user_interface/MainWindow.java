package user_interface;

import java.awt.*;

import javax.swing.*;

import model.Model;

public class MainWindow implements Runnable {

	private JFrame frame;
	private int appWidth;
	private int appHeight;
	
	private JPanel mainPanel;
	private Model _model;
	
	@Override
	public void run() {
		
		setAppHeightAndWidth();
		
		_model = new Model();
		_model.setUI(this);
		frame = new JFrame("Project");
		mainPanel = new JPanel();
		JLabel testLabel = new JLabel("My Label");
					
		mainPanel.add(testLabel);
		frame.add(mainPanel);
		
		//DO NOT TOUCH
		frame.setSize(appWidth, appHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void setAppHeightAndWidth() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		appWidth = screenSize.width / 2;
		appHeight = screenSize.height / 2;
	}
	
}
