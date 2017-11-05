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
		ImageIcon i = new ImageIcon(image);
		
		_model = new Model();
		_model.setUI(this);
		frame = new JFrame("Project");
		mainPanel = new JPanel();
		JLabel testLabel = new JLabel();
		testLabel.setIcon(i);
		
		/*
		mainPanel.add(testLabel);
		frame.add(mainPanel);*/
		
		//ColorSpectrumPanel csp = new ColorSpectrumPanel(image);
		
		//frame.getContentPane().add(new JLabel(new ImageIcon("src/images/color-spectrum.jpg")));
		
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
			for (int y = 0; y < appHeight/2; y++) {
				image.setRGB(x, y, (new Color(x % 256, y % 256, 0).getRGB()));
			}
		}
		
		return image;
	}
	
}

class ColorSpectrumPanel extends JPanel {
	
	BufferedImage image;
	
	public ColorSpectrumPanel (BufferedImage i) {
		image = i;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0 ,this);
	}
	
}