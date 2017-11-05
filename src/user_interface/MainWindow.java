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

	public int _appWidth;
	public int _appHeight;
	public Dimension _colorSpectrumResolution;
	
	private JFrame _frame;
	private Model _model;
	private JPanel _mainPanel, _toFindSwatch, _lastSelectedSwatch;
	private ColorSpectrumPanel _csp;
	
	@Override
	public void run() {
		//Sets the window to 1/4 of the entire screen
		setAppHeightAndWidth();
		
		_model = new Model(this);
		_frame = new JFrame("Project");
		_frame.setLayout(new GridLayout(1,1));
		_mainPanel = new JPanel();
		_frame.add(_mainPanel);
		_mainPanel.setLayout(new GridLayout(1,2));
		
		//Set up the color picker on the game board
		_csp = new ColorSpectrumPanel(this);
		_mainPanel.add(_csp);
		ColorPickerListener pickerListener = new ColorPickerListener(_model, this);
		_csp.addMouseListener(pickerListener);
		_csp.addMouseMotionListener(pickerListener);
		
		//Sets up the info panel 
		JPanel infoPanel = new JPanel();
		_mainPanel.add(infoPanel);
		infoPanel.setLayout(new GridLayout(4,1));
		
		//Sets up the "to find" row
		JPanel toFindPanel = new JPanel();
		toFindPanel.setLayout(new GridLayout(1,2));
		JLabel findText = new JLabel("Find This Color");
		findText.setHorizontalAlignment(JLabel.CENTER);
		findText.setVerticalAlignment(JLabel.CENTER);
		_toFindSwatch = new JPanel();
		toFindPanel.add(findText);
		toFindPanel.add(_toFindSwatch);
		_toFindSwatch.setBackground(new Color(0,0,0));
		//Sets up the "last picked" row
		JPanel lastPicked = new JPanel();
		lastPicked.setLayout(new GridLayout(1,2));
		JLabel lastPickedText = new JLabel("Color You Last Selected");
		lastPickedText.setHorizontalAlignment(JLabel.CENTER);
		lastPickedText.setVerticalAlignment(JLabel.CENTER);
		_lastSelectedSwatch = new JPanel();
		lastPicked.add(lastPickedText);
		lastPicked.add(_lastSelectedSwatch);
		_lastSelectedSwatch.setBackground(new Color(0,0,0));

		//add components to the info panel
		infoPanel.add(toFindPanel);
		infoPanel.add(lastPicked);
		infoPanel.add(new JLabel("hi"));
		
		//DO NOT TOUCH
		_frame.setSize(_appWidth, _appHeight);
		_frame.setResizable(false);
		_frame.setVisible(true);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Sets the app height and width (it will be a quarter of the user's screen size).
	 */
	private void setAppHeightAndWidth() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		_appWidth = screenSize.width / 2;
		_appHeight = screenSize.height / 2;
		_colorSpectrumResolution = new Dimension(_appWidth/2, _appWidth/2);
	}
	
	public void setToFindSwatch (Color c) {
		_toFindSwatch.setBackground(c);
	}
	
	public void setLastPickedSwatch (Color c) {
		_lastSelectedSwatch.setBackground(c);
	}
		
}

class ColorSpectrumPanel extends JPanel {
	
	private MainWindow window;
	private BufferedImage image;
	
	public ColorSpectrumPanel(MainWindow mw) {
		window = mw;
		image = new BufferedImage(window._colorSpectrumResolution.width, window._colorSpectrumResolution.width, BufferedImage.TYPE_INT_ARGB);
		initialize();
	}
	
	public void initialize() {
		for (int x = 0; x < window._colorSpectrumResolution.width; x++) {
			for (int y = 0; y < window._colorSpectrumResolution.width; y++) {
				image.setRGB(x, y, Color.HSBtoRGB((float)(x)/window._colorSpectrumResolution.width, 1, 1-(float)(y)/window._colorSpectrumResolution.width));
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, null);
	}
}

	

