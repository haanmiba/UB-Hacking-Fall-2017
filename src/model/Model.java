package model;

import user_interface.*;

import java.awt.Color;
import java.awt.Point;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Model {

	// The UI that will be displaying this game
	private MainWindow _ui;
	
	// The data of the game
	private int _gameMode;
	private int _difficulty;
	private int _misses;
	private int _rounds;
	private int _multiplier;
	private int _highScore;
	
	// The tolerance of the distance to the point to be found;
	private double _tolerance;
	
	// The color spectrum of possible colors
	private int[][] _colorSpectrum;

	// Score of the player
	private int _points, _clicks, _cumulativeAccuracy, _passedRounds;
	
	// Color values to find
	private int _colorToFind;
	private Point _colorToFindPoint;
	
	// Color values that the player selected
	private int _selectedColor;
	private Point _selectedColorPoint;
	private double _accuracy;
	
	public Model (MainWindow ui) {
		// Initializes the UI that will display this game and generates the color spectrum
		_ui = ui;
		// Initialize our color spectrum
		generateColorSpectrum();
		// Initialize values required to run the game
		initializeAllValues();
	}
	
	/**
	 * Initialize values required to run the game
	 */
	private void initializeAllValues() {
		_gameMode = -1;
		_difficulty = -1;
		_misses = 0;
		_rounds = 0;
		_multiplier = 0;
		_tolerance = .1;
		_points = 0;
		_clicks = 0;
		_colorToFind = 0;
		_colorToFindPoint = new Point(-1, -1);
		_selectedColorPoint = new Point(-1, -1);
		_accuracy = -1;
		_passedRounds=0;
		_highScore = getHighScore();
	}

	public int getHighScore() {
		int hs = -1;
		try {
			for (String line: Files.readAllLines(Paths.get("high_score.txt"))) {
				hs = new Integer(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(hs);
		return hs;
	}
	
	public int returnHighScore() {
		return _highScore;
	}
	
	public void setHighScore() {
		_highScore = _points;
		try {
			String highScoreString = Integer.toString(_points);
			File file = new File("high_score.txt");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(highScoreString);
			bw.close();
			_ui.displayNewHighScore(_highScore);
		} catch (IOException e) {
			
		}
	}
	
	public void startGame() {
		generateColorToFind();
		_points = 0;
		_misses = 0;
		_rounds = 10;
		_accuracy = 0;
		_clicks = 0;
		_cumulativeAccuracy = 0;
		_passedRounds = 0;
		System.out.println(_highScore);
		updateUIinfo();
	}
	
	public void resetGame() {
		_points = 0;
		_misses = 0;
		_clicks = 0;
		_rounds = 10;
		_accuracy = 0;
		_cumulativeAccuracy = 0;
		_passedRounds = 0;
	}
	
	/**
	 * Switches the menu to the results screen
	 */
	public void endGame() {
		System.out.println("Points is greater than high score: " + (_points > _highScore));
		if (_points > _highScore) {
			setHighScore();
		}
		_ui.setResultsPageData(_points, _passedRounds, _clicks, _misses, _cumulativeAccuracy);
		_ui.switchPage("RESULTS");
	}
	
	private void generateColorSpectrum() {
		
		_colorSpectrum = new int[_ui._colorSpectrumResolution.width][_ui._colorSpectrumResolution.width];
		
		for (int x = 0; x < _ui._colorSpectrumResolution.width; x++) {
			for (int y = 0; y < _ui._colorSpectrumResolution.width; y++) {
				_colorSpectrum[x][y] = Color.HSBtoRGB((float)(x)/_ui._colorSpectrumResolution.width, 1, 1-(float)(y)/_ui._colorSpectrumResolution.width);
			}
		}
	
	}
	
	public void setGameDifficulty(int select) {
		switch (select) {
			case 0:
				_tolerance = .1;
				_multiplier = 1;
				break;
			case 1:
				_tolerance = .075;
				_multiplier = 2;
				break;
			case 2:
				_tolerance = .05;
				_multiplier = 8;
				break;
			case 3:
				_tolerance = .025;
				_multiplier = 16;
				break;
		}
	}
	
	/**
	 * Since all the colors possibly selected are stored in a 2D array and are represented in a square,
	 * the color to be found can be represented as both the x- and y-coordinate and the RGB int value
	 */
	public void generateColorToFind() {
		int x = (int) (Math.random() * _ui._colorSpectrumResolution.width);
		int y = (int) (Math.random() * _ui._colorSpectrumResolution.width);
		while (y > 220) {
			y = (int) (Math.random() * _ui._colorSpectrumResolution.width);
		}
		_colorToFind = _colorSpectrum[x][y];
		_ui.setToFindSwatch(_colorToFind);
		_colorToFindPoint.setLocation(x, y);		
	}

	/**
	 * Takes the x- and y-coordinates of the previously selected color to find the matching color in the 
	 * stored array. This color is then used to score and is displayed.
	 * 
	 * @param x - x-coordinate of the selection
	 * @param y - y-coordinate of the selection
	 */
	public void setSelectedColor(int x, int y) {
		_clicks+=1;
		_cumulativeAccuracy = (int)(_passedRounds*100.0f)/_clicks;
		updateUIinfo();
		
		_selectedColor = _colorSpectrum[x][y];
		_ui.setLastPickedSwatch(_selectedColor);
		
		_selectedColorPoint.setLocation(x, y);
		calculateAccuracy();
		
		if(successfulSelection()) {
			
			_points += (_multiplier * 1000) / ((_misses == 0) ? 1 : _misses);
			_passedRounds++;
			_rounds--;
			updateUIinfo();
			if (_rounds == 0) {
				endGame();
			} else {
				generateColorToFind();
			}
		} else {
			_misses++;
		}
		
	}
	
	/**
	 * << TO BE FILLED BY HANS >>
	 */
	private void calculateAccuracy() {
		Point negativePoint = new Point((-1 * _ui._colorSpectrumResolution.width) + _colorToFindPoint.x, _colorToFindPoint.y);
		_accuracy = Math.min(_selectedColorPoint.distance(_colorToFindPoint) / (new Point(0, 0).distance(new Point(_ui._colorSpectrumResolution.height, _ui._colorSpectrumResolution.width))),
				_selectedColorPoint.distance(negativePoint) / (new Point(0, 0).distance(new Point(_ui._colorSpectrumResolution.height, _ui._colorSpectrumResolution.width))));
	}
	
	/**
	 * Tests to see if the color selected is acceptable
	 * 
	 * @return true - if the selection is acceptable
	 * 		   false - if the selection is unacceptable
	 */
	private boolean successfulSelection() {
		return _accuracy < _tolerance;
	}
	
	public void updateUIinfo () {
		_ui.updateInfoPanel(_rounds, _points, _clicks, _cumulativeAccuracy);
	}
}
