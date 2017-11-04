package user_interface;

import javax.swing.*;

public class ProjectUI implements Runnable {

	private JFrame frame;
	
	private JPanel mainPanel;
	
	public void run() {
		
		frame = new JFrame("Project");
		mainPanel = new JPanel();
		JLabel testLabel = new JLabel("My Label");
		
		mainPanel.add(testLabel);
		frame.add(mainPanel);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
