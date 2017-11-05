package user_interface;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import listeners.*;
import model.Model;

public class MainWindow implements Runnable {

	public int _appWidth;
	public int _appHeight;
	public Dimension _colorSpectrumResolution;
	
	private JFrame _frame;
	public Model _model;
	private CardLayout _cardLayout;
	private JPanel _frameLayers, _mainMenuPanel, _gamePanel, _toFindSwatch, _lastSelectedSwatch, _resultPanel;
	private ColorSpectrumPanel _csp;
	
	@Override
	public void run() {
		
		// Sets the window to 1/4 of the entire screen
		setAppHeightAndWidth();
		
		// Initializes our Model
		_model = new Model(this);
		
		// Initialize our main frame
		_frame = new JFrame("Project");
		_frame.setLayout(new GridLayout(1,1));
		
		// Initializes our JPanel with a CardLayout that displays the different screens
		_cardLayout = new CardLayout();
		_frameLayers = new JPanel(_cardLayout);
		
		// Iinitialize our main menu;
		initializeMainMenu();
		
		_gamePanel = new JPanel();
		_frameLayers.add(_mainMenuPanel, "MAIN MENU");
		_frameLayers.add(_gamePanel, "MAIN PANEL");
		_frame.add(_frameLayers);
		_gamePanel.setLayout(new GridLayout(1,2));
		
		//Set up the color picker on the game board
		_csp = new ColorSpectrumPanel(this);
		_gamePanel.add(_csp);
		ColorPickerListener pickerListener = new ColorPickerListener(_model, this);
		_csp.addMouseListener(pickerListener);
		_csp.addMouseMotionListener(pickerListener);
		
		//Sets up the info panel 
		JPanel infoPanel = new JPanel();
		_gamePanel.add(infoPanel);
		infoPanel.setLayout(new GridLayout(4,1));
		
		//Sets up the "to find" row
		JPanel toFindPanel = new JPanel();
		toFindPanel.setLayout(new GridLayout(1,2));
		JLabel findText = new JLabel("Find This Color:");
		findText.setHorizontalAlignment(JLabel.CENTER);
		findText.setVerticalAlignment(JLabel.CENTER);
		_toFindSwatch = new JPanel();
		toFindPanel.add(findText);
		toFindPanel.add(_toFindSwatch);
		_toFindSwatch.setBackground(new Color(0,0,0));
		
		//Sets up the "last picked" row
		JPanel lastPicked = new JPanel();
		lastPicked.setLayout(new GridLayout(1,2));
		JLabel lastPickedText = new JLabel("Color You Last Selected:");
		lastPickedText.setHorizontalAlignment(JLabel.CENTER);
		lastPickedText.setVerticalAlignment(JLabel.CENTER);
		_lastSelectedSwatch = new JPanel();
		lastPicked.add(lastPickedText);
		lastPicked.add(_lastSelectedSwatch);
		_lastSelectedSwatch.setBackground(new Color(0,0,0));

		//add components to the info panel
		infoPanel.add(toFindPanel);
		infoPanel.add(lastPicked);
		infoPanel.add(new JLabel());
		infoPanel.add(new JButton("Main"));
		
		_model.generateColorToFind();
		
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
	
	private void initializeMainMenu() {
		
		_mainMenuPanel = new JPanel(new GridLayout(3, 1));
		
		JPanel middleOptions = new JPanel(new GridLayout(1, 2));
		
		JPanel modeButtons = new JPanel(new GridLayout(5, 1));
		modeButtons.add(new JLabel("Mode:"));
		modeButtons.add(createButton("Guess", 0));
		modeButtons.add(createButton("Limited Guess", 1));
		modeButtons.add(createButton("Timed", 2));
		modeButtons.add(createButton("Quick Guess", 3));
		middleOptions.add(modeButtons);
		
		JPanel difficultyButtons = new JPanel(new GridLayout(5, 1));
		difficultyButtons.add(new JLabel("Difficulty:"));
		difficultyButtons.add(createButton("Easy", 4));
		difficultyButtons.add(createButton("Medium", 5));
		difficultyButtons.add(createButton("Hard", 6));
		difficultyButtons.add(createButton("Extreme", 7));
		middleOptions.add(difficultyButtons);

		_mainMenuPanel.add(new JLabel(new ImageIcon()));
		_mainMenuPanel.add(middleOptions);
		_mainMenuPanel.add(createButton("Start Game!", 8));
	}
	
	private JButton createButton(String s, int select) {
		JButton button = new JButton(s);
		button.addActionListener(new ButtonListener(this, select));
		return button;
	}
	
	public void setToFindSwatch(int c) {
		_toFindSwatch.setBackground(new Color(c));
	}
	
	public void setLastPickedSwatch(int c) {
		_lastSelectedSwatch.setBackground(new Color(c));
	}
	
	public void nextPage() {
		_cardLayout.show(_frameLayers, "MAIN PANEL");
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

	

