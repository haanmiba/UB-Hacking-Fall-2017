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
	public Dimension colorSpectrumDisplay;
	
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
		testLabel.setSize(appWidth/2, appWidth/2);
		testLabel.setIcon(new ImageIcon(image));
		
		
		ColorSpectrumPanel csp = new ColorSpectrumPanel(this);
		
		//mainPanel.add(testLabel);
		frame.add(csp);
		
		//DO NOT TOUCH
		frame.setSize(appWidth, appHeight);
		frame.setResizable(false);
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
		colorSpectrumDisplay = new Dimension(appWidth/2, appWidth/2);
	}
	
	private BufferedImage createColorSpectrum() {
				
		BufferedImage image = new BufferedImage(colorSpectrumDisplay.width, colorSpectrumDisplay.height, BufferedImage.TYPE_INT_ARGB);
		
		for (int x = 0; x < colorSpectrumDisplay.width; x++) {
			for (int y = 0; y < colorSpectrumDisplay.height; y++) {
				image.setRGB(x, y, Color.HSBtoRGB((float)(x)/colorSpectrumDisplay.width, 1, 1-(float)(y)/colorSpectrumDisplay.height));
			}
		}
		
		return image;
	}
	
	public Dimension getColorSpectrumSquare() {
		return colorSpectrumDisplay;
	}
	
}

class ColorSpectrumPanel extends JPanel {
	
	private MainWindow window;
	private BufferedImage image;
	
	public ColorSpectrumPanel(MainWindow mw) {
		window = mw;
		image = new BufferedImage(window.colorSpectrumDisplay.width, window.colorSpectrumDisplay.width, BufferedImage.TYPE_INT_ARGB);
		initialize();
	}
	
	public void initialize() {
		for (int x = 0; x < window.colorSpectrumDisplay.width; x++) {
			for (int y = 0; y < window.colorSpectrumDisplay.width; y++) {
				image.setRGB(x, y, Color.HSBtoRGB((float)(x)/window.colorSpectrumDisplay.width, 1, 1-(float)(y)/window.colorSpectrumDisplay.width));
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, null);
	}
	
}