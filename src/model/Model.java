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
	private double _selectedColorDistanceToExpected;
	
	public Model (MainWindow ui) {
		
		// Initializes the UI that will display this game and generates the color spectrum
		_ui = ui;
		_colorSpectrum = new int[_ui._colorSpectrumResolution.width][_ui._colorSpectrumResolution.width];
		generateColorSpectrum();
		
		_gameMode = -1;
		_difficulty = -1;
		
		_tolerance = .1;
		
		_points = 0;
		_tries = 3;
		_colorToFind = -1;
		_selectedColor = -1;
		_colorToFindPoint = new Point(-1, -1);
		_selectedColorPoint = new Point(-1, -1);
		_selectedColorDistanceToExpected = -1;
		//generateColorToFind();
	}
	
	private void initializeAllValues() {
		
	}
	
	
	
	private void generateColorSpectrum() {
		for (int x = 0; x < _ui._colorSpectrumResolution.width; x++) {
			for (int y = 0; y < _ui._colorSpectrumResolution.width; y++) {
				_colorSpectrum[x][y] = Color.HSBtoRGB((float)(x)/_ui._colorSpectrumResolution.width, 1, 1-(float)(y)/_ui._colorSpectrumResolution.width);
			}
		}
	}
	
	public void generateColorToFind() {
		int x = (int) (Math.random() * _ui._colorSpectrumResolution.width);
		int y = (int) (Math.random() * _ui._colorSpectrumResolution.width);
		while (y > 220) {
			y = (int) (Math.random() * _ui._colorSpectrumResolution.width);
		}
		_colorToFind = _colorSpectrum[x][y];
		_colorToFindPoint.setLocation(x, y);
		System.out.println("Find this color: " + _colorToFind);
		System.out.println("At this index: [" + x + "][" + y + "]");
		_ui.setToFindSwatch(_colorToFind);
	}

	public void setSelectedColor(int x, int y) {
		_selectedColor = _colorSpectrum[x][y];
		_selectedColorPoint.setLocation(x, y);
		calculateDistance();
		System.out.println("You selected this color: " + _selectedColor);
		System.out.println("At this index: [" + x + "][" + y + "]");
		System.out.println("You were this close: " + _selectedColorDistanceToExpected);
		if(successfulSelection()) {
			System.out.println("You found it!");
			generateColorToFind();
		} else {
			System.out.println("Keep trying!");
		}
		_ui.setLastPickedSwatch(_selectedColor);
	}
	
	public void calculateDistance() {
		_selectedColorDistanceToExpected = _selectedColorPoint.distance(_colorToFindPoint) / (new Point(0, 0).distance(new Point(_ui._colorSpectrumResolution.height, _ui._colorSpectrumResolution.width)));
	}
	
	public boolean successfulSelection() {
		if (_selectedColorDistanceToExpected < _tolerance) {
			return true;
		}
		return false;
	}
	
	
}
