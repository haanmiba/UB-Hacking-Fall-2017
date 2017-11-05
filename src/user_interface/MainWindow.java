package user_interface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import listeners.ColorPickerListener;
import model.Model;

public class MainWindow implements Runnable {

	private JFrame frame;
	public int appWidth;
	public int appHeight;
	public Dimension colorSpectrumResolution;
	
	private Model _model;
	private JPanel mainPanel;
	private ColorSpectrumPanel csp;
	
	@Override
	public void run() {
		
		setAppHeightAndWidth();
				
		_model = new Model(this);
		frame = new JFrame("Project");
		mainPanel = new JPanel();		
		csp = new ColorSpectrumPanel(this);
		
		initializeColorPicker();
		
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
		colorSpectrumResolution = new Dimension(appWidth/2, appWidth/2);
	}
	
	private void initializeColorPicker() {
		ColorPickerListener m = new ColorPickerListener(_model, this);
		csp.addMouseListener(m);
		csp.addMouseMotionListener(m);
	}
		
}

class ColorSpectrumPanel extends JPanel {
	
	private MainWindow window;
	private BufferedImage image;
	
	public ColorSpectrumPanel(MainWindow mw) {
		window = mw;
		image = new BufferedImage(window.colorSpectrumResolution.width, window.colorSpectrumResolution.width, BufferedImage.TYPE_INT_ARGB);
		initialize();
	}
	
	public void initialize() {
		for (int x = 0; x < window.colorSpectrumResolution.width; x++) {
			for (int y = 0; y < window.colorSpectrumResolution.width; y++) {
				image.setRGB(x, y, Color.HSBtoRGB((float)(x)/window.colorSpectrumResolution.width, 1, 1-(float)(y)/window.colorSpectrumResolution.width));
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, null);
	}
	
}