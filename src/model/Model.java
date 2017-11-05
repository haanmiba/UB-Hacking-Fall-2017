package model;

import user_interface.*;

import java.awt.Color;
import java.awt.Point;

public class Model {

	// The UI that will be displaying this game
	private MainWindow _ui;
	
	// The data of the game
	private int _gameMode;
	private int _difficulty;
	private int _guesses;
	private int _rounds;
	private int _multiplier;
	
	// The tolerance of the distance to the point to be found;
	private double _tolerance;
	
	// The color spectrum of possible colors
	private int[][] _colorSpectrum;

	// Score of the player
	private int _points, _tries;
	
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
		
		initializeAllValues();
		
		_gameMode = -1;
		_difficulty = -1;
		
		_tolerance = .1;
		
		_points = 0;
		_tries = 3;
		_colorToFind = -1;
		_selectedColor = -1;
		_colorToFindPoint = new Point(-1, -1);
		_selectedColorPoint = new Point(-1, -1);
		_accuracy = -1;
		//generateColorToFind();
	}
	
	private void initializeAllValues() {
		_gameMode = -1;
		_difficulty = -1;
		_guesses = 0;
		_rounds = 0;
		_multiplier = 0;
		_tolerance = .1;
		_points = 0;
		_tries = 0;
		_colorToFind = 0;
		_colorToFindPoint = new Point(-1, -1);
		_selectedColorPoint = new Point(-1, -1);
		_accuracy = -1;
	}

	public void startGame() {
		generateColorToFind();
		_points = 0;
		_guesses = 0;
		_rounds = 3;
	}
	
	public void resetGame() {
		_points = 0;
	}
	
	public void endGame() {
		_ui.setResultsPageData(_points, _guesses);
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
			_tolerance = .05;
			_multiplier = 2;
			break;
		case 2:
			_tolerance = .025;
			_multiplier = 4;
			break;
		case 3:
			_tolerance = .01;
			_multiplier = 8;
			break;
		
		}
		
	}
	
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

	public void setSelectedColor(int x, int y) {
		
		_guesses++;
		
		_selectedColor = _colorSpectrum[x][y];
		_ui.setLastPickedSwatch(_selectedColor);
		_selectedColorPoint.setLocation(x, y);
		
		calculateDistance();
		
		if(successfulSelection()) {
			_points += (_multiplier * 1000) / _guesses;
			_rounds--;
			if (_rounds == 0) {
				System.out.println("Ending game: ");
				endGame();
			} else {
				generateColorToFind();
			}
		} else {
			
		}
	}
	
	private void calculateDistance() {
		Point negativePoint = new Point((-1 * _ui._colorSpectrumResolution.width) + _colorToFindPoint.x, _colorToFindPoint.y);
		_accuracy = Math.min(_selectedColorPoint.distance(_colorToFindPoint) / (new Point(0, 0).distance(new Point(_ui._colorSpectrumResolution.height, _ui._colorSpectrumResolution.width))),
				_selectedColorPoint.distance(negativePoint) / (new Point(0, 0).distance(new Point(_ui._colorSpectrumResolution.height, _ui._colorSpectrumResolution.width))));
	}
	
	private boolean successfulSelection() {
		if (_accuracy < _tolerance) {
			return true;
		}
		return false;
	}
	
	
}
