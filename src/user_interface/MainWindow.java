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
		//frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Sets the app height and width (it will be a quarter of the user's screen size).
	 */
	private void setAppHeightAndWidth() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		appWidth = screenSize.width / 2;
		appHeight = screenSize.height / 2;
	}
	
	private BufferedImage createColorSpectrum() {
		
		int squareDimension = appWidth/2;
		
		BufferedImage image = new BufferedImage(squareDimension, squareDimension, BufferedImage.TYPE_INT_ARGB);
		
		for (int x = 0; x < squareDimension; x++) {
			for (int y = 0; y < squareDimension; y++) {
				image.setRGB(x, y, Color.HSBtoRGB((float)(x)/squareDimension, 1, 1-(float)(y)/squareDimension));
			}
		}
		
		return image;
	}
	
}
