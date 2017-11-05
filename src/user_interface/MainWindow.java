package user_interface;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import images.ImgResourceLoader;
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
	private JLabel[][] _scoreInfo;
	private JLabel[] _resultInfo;
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
		_mainMenuPanel.add(new JLabel(new ImageIcon(ImgResourceLoader.getImage("Huedunit Logo.png"))));

		JLabel description = new JLabel("Can you survive 10 rounds of testing your color guessing skills? Let's find out!");
		description.setFont(new Font("Helvetica", Font.PLAIN, 15));
		description.setHorizontalAlignment(JLabel.CENTER);
		_mainMenuPanel.add(description);
		
		JPanel middleOptions = new JPanel(new GridLayout(1, 3));		
		
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
		startGameButton.setIcon(new ImageIcon(ImgResourceLoader.getImage("Start Button.png")));
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
	
	/**
	 * Initializes the info panel
	 */
	private void initializeInfoPanel() {
		
		_infoPanel = new JPanel();
		_infoPanel.setLayout(new GridLayout(4,1));
		
		//Sets up the "to find" row
		JPanel toFindPanel = new JPanel();
		toFindPanel.setLayout(new GridLayout(1,2));
		JLabel findText = new JLabel("Find This Color:");
		findText.setFont(new Font("Helvetica", Font.PLAIN, 16));
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
		lastPickedText.setFont(new Font("Helvetica", Font.PLAIN, 16));
		lastPickedText.setHorizontalAlignment(JLabel.CENTER);
		lastPickedText.setVerticalAlignment(JLabel.CENTER);
		_lastSelectedSwatch = new JPanel();
		lastPicked.add(lastPickedText);
		lastPicked.add(_lastSelectedSwatch);
		_lastSelectedSwatch.setBackground(new Color(0,0,0));

		//Sets up the scoring panel
		JPanel scoreBoard = new JPanel();
		scoreBoard.setLayout(new GridLayout(2,4));
		_scoreInfo = new JLabel[2][4];
		_scoreInfo[0][0] = new JLabel("Rounds Left:");
		_scoreInfo[0][1] = new JLabel();
		_scoreInfo[0][2] = new JLabel("Score:");
		_scoreInfo[0][3] = new JLabel();
		_scoreInfo[1][0] = new JLabel("Clicks Used:");
		_scoreInfo[1][1] = new JLabel();
		_scoreInfo[1][2] = new JLabel("Your Accuracy:");
		_scoreInfo[1][3] = new JLabel();
		for (int r=0; r<2; r+=1) {
			for (int c=0; c<4; c+=1) {
				_scoreInfo[r][c].setFont(new Font("Helvetica", Font.PLAIN, 16));
				_scoreInfo[r][c].setHorizontalAlignment(JLabel.RIGHT);
				_scoreInfo[r][c].setVerticalAlignment(JLabel.CENTER);
				scoreBoard.add(_scoreInfo[r][c]);
			}
		}
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.add(createButton("Quit", 10));
		
		//add components to the info panel
		_infoPanel.add(toFindPanel);
		_infoPanel.add(lastPicked);
		_infoPanel.add(scoreBoard);
		_infoPanel.add(buttons);
				
	}
	
	/**
	 * Updates the rounds
	 * @param r - number of round
	 */
	public void updateInfoPanel(int rounds, int points, int clicks, int accuracy) {
		_scoreInfo[0][1].setText(rounds+"");
		_scoreInfo[0][3].setText(points+"");
		_scoreInfo[1][1].setText(clicks+"");
		_scoreInfo[1][3].setText(accuracy + " %");
	}
	
	/**
	 * Initializes the result/score panel
	 */
	private void initializeResultPanel() {
		_resultPanel = new JPanel();
		_resultPanel.setLayout(new GridLayout(6, 1));
		
		_resultInfo = new JLabel[5];
		
		String[] sectionTitles = new String[]{"Points:", "Rounds passed:", "Clicks Done", "Number of Misses:", "Accuracy:"};
		for (int i=0; i<5; i+=1) {
			JPanel section = new JPanel();
			section.setLayout(new GridLayout(1,2));
			JLabel text = new JLabel(sectionTitles[i]);
			text.setFont(new Font("Arial", Font.PLAIN, 36));
			section.add(text);
			_resultInfo[i] = new JLabel();
			_resultInfo[i].setFont(new Font("Arial", Font.PLAIN, 36));
			section.add(_resultInfo[i]);
			_resultPanel.add(section);
		}
		_resultPanel.add(createButton("Back to Main Menu", 9));
	}
	
	private JButton createButton(String s, int select) {
		JButton button = new JButton(s);
		button.addActionListener(new ButtonListener(this, select));
		button.setFont(new Font("Helvetica", Font.PLAIN, 20));
		return button;
	}
	
	/**
	 * Sets the "to find" swatch to the color passed
	 * 
	 * @param c - the color as an int obtained through HSBtoRGB
	 */
	public void setToFindSwatch(int c) {
		_toFindSwatch.setBackground(new Color(c));
	}
	
	/**
	 * Sets the "last picked" swatch to the color passed
	 * 
	 * @param c - the color as an int obtained through HSBtoRGB
	 */
	public void setLastPickedSwatch(int c) {
		_lastSelectedSwatch.setBackground(new Color(c));
	}
	
	public void setResultsPageData(int points, int passedRounds, int clicks, int misses, int cumulativeAccuracy) {
		_resultInfo[0].setText(points+"");
		_resultInfo[1].setText(passedRounds+"");
		_resultInfo[2].setText(clicks+"");
		_resultInfo[3].setText(misses+"");
		_resultInfo[4].setText(cumulativeAccuracy+"");
	}
	
	public void switchPage(String s) {
		_cardLayout.show(_frameLayers, s);
	}
}

class ColorSpectrumPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
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

	

