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
	private JPanel _frameLayers, _mainMenuPanel, _gamePanel, _infoPanel, _toFindSwatch, _lastSelectedSwatch, _resultPanel;
	private JLabel _roundsLabel, _scoreLabel, _guessesLabel;
	private ColorSpectrumPanel _csp;
	
	@Override
	public void run() {
		
		// Sets the window to 1/4 of the entire screen
		setAppHeightAndWidth();
		
		// Initializes our Model
		_model = new Model(this);
		
		// Initialize our main frame
		_frame = new JFrame("Huedunit");
		_frame.setLayout(new GridLayout(1,1));
		
		// Initializes our JPanel with a CardLayout that displays the different screens
		_cardLayout = new CardLayout();
		_frameLayers = new JPanel(_cardLayout);
		
		// Initialize our main menu panel
		initializeMainMenuPanel();
		_frameLayers.add(_mainMenuPanel, "MAIN MENU");

		// Initialize our game panel
		initializeGamePanel();
		_frameLayers.add(_gamePanel, "GAME");

		// Initialize our result panel
		initializeResultPanel();
		_frameLayers.add(_resultPanel, "RESULTS");

		_frame.add(_frameLayers);
		
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
	
	/**
	 * Initializes the main menu panel.
	 */
	private void initializeMainMenuPanel() {
		
		_mainMenuPanel = new JPanel(new GridLayout(4, 1));
		_mainMenuPanel.add(new JLabel(new ImageIcon("src/images/Huedunit Logo.png")));

		JLabel description = new JLabel("Can you survive 10 rounds of testing your color guessing skills? Let's find out!");
		description.setFont(new Font("Helvetica", Font.PLAIN, 15));
		description.setHorizontalAlignment(JLabel.CENTER);
		_mainMenuPanel.add(description);
		
		JPanel middleOptions = new JPanel(new GridLayout(1, 3));		
		
		/*
		JPanel modeButtons = new JPanel(new GridLayout(5, 1));
		modeButtons.add(new JLabel("  Mode:"));
		modeButtons.add(createButton("Guess", 0));
		modeButtons.add(createButton("Limited Guess", 1));
		modeButtons.add(createButton("Timed", 2));
		modeButtons.add(createButton("Quick Guess", 3));
		middleOptions.add(modeButtons);*/
		
		middleOptions.add(new JLabel());
		
		JPanel difficultyButtons = new JPanel(new GridLayout(5, 1));
		difficultyButtons.add(new JLabel("  Difficulty:"));
		difficultyButtons.add(createButton("Easy", 4));
		difficultyButtons.add(createButton("Medium", 5));
		difficultyButtons.add(createButton("Hard", 6));
		difficultyButtons.add(createButton("Extreme", 7));
		middleOptions.add(difficultyButtons);
		middleOptions.add(new JLabel());

		_mainMenuPanel.add(middleOptions);
		
		
		JButton startGameButton = createButton("", 8);
		startGameButton.setIcon(new ImageIcon("src/images/Start Button.png"));
		_mainMenuPanel.add(startGameButton);
	}
	
	/**
	 * Initializes the game panel.
	 */
	private void initializeGamePanel() {
		
		_gamePanel = new JPanel();
		_gamePanel.setLayout(new GridLayout(1,2));
		
		initializeColorSpectrum();
		_gamePanel.add(_csp);
		
		initializeInfoPanel();
		_gamePanel.add(_infoPanel);
	}
	
	/**
	 * Initializes the color spectrum to be displayed.
	 */
	private void initializeColorSpectrum() {
		_csp = new ColorSpectrumPanel(this);
		_csp.addMouseListener(new ColorPickerListener(this));
	}
	
	private void initializeInfoPanel() {
		
		_infoPanel = new JPanel();
		_infoPanel.setLayout(new GridLayout(4,1));
		
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

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.add(createButton("Back to Main Menu", 9));
		
		//add components to the info panel
		_infoPanel.add(toFindPanel);
		_infoPanel.add(lastPicked);
		
		_roundsLabel = new JLabel("  Rounds: ");
		_roundsLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
		
		_infoPanel.add(_roundsLabel);
		_infoPanel.add(buttons);
				
	}
	
	public void updateInfoPanel(int r) {
		_roundsLabel.setText("  Rounds: " + r);
	}
	
	private void initializeResultPanel() {
		_resultPanel = new JPanel();
		_resultPanel.setLayout(new GridLayout(3, 0));
		
		_scoreLabel = new JLabel();
		_scoreLabel.setFont(new Font("Arial", Font.PLAIN, 36));
		_scoreLabel.setForeground(Color.RED);

		_guessesLabel = new JLabel();
		_guessesLabel.setFont(new Font("Arial", Font.PLAIN, 36));
		_guessesLabel.setForeground(Color.RED);

		_resultPanel.add(_scoreLabel);
		_resultPanel.add(_guessesLabel);
		_resultPanel.add(createButton("Back to Main Menu", 9));
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
	
	public void setResultsPageData(int p, int m) {
		_scoreLabel.setText("  Points: " + p);
		_guessesLabel.setText("  Number of misses: " + m);
	}
	
	public void switchPage(String s) {
		_cardLayout.show(_frameLayers, s);
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

	

