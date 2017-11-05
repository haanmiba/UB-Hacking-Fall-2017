package user_interface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
		
		BufferedImage image = createColorSpectrum();
		
		_model = new Model(this);
		frame = new JFrame("Project");
		mainPanel = new JPanel();
		JLabel testLabel = new JLabel();
		testLabel.setIcon(new ImageIcon(image));
		
		
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
	
	public BufferedImage createColorSpectrum() {
		BufferedImage image = new BufferedImage(appWidth, appHeight, BufferedImage.TYPE_INT_ARGB);
		
		for (int x = 0; x < appWidth/2; x++) {
			for (int y = 0; y < appHeight; y++) {
				image.setRGB(x, y, Color.HSBtoRGB((float)(x)/(appWidth/2), 1, (float)(y)/appHeight));
			}
		}
		
		return image;
	}
	
}
