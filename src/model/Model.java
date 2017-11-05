package model;

import user_interface.*;

import java.awt.Color;
import java.awt.Point;

public class Model {

	private MainWindow _ui;
	//Score of the player
	private int _points, _lives;
	//Color values to find
	private int _colorToFind;
	private Point _colorToFindPoint;
	private int _selectedColor;
	private Point _selectedColorPoint;
	private int[][] _colorSpectrum;
	
	public Model (MainWindow ui) {
		_ui = ui;
		_points = 0;
		_lives = 3;
		_colorToFind = 0;
		_selectedColor = 0;
		_colorToFindPoint = new Point(-1, -1);
		_selectedColorPoint = new Point(-1, -1);
		_colorSpectrum = new int[_ui._colorSpectrumResolution.width][_ui._colorSpectrumResolution.width];
		generateExpectedColors();
		generateColorToFind();
	}
	
	private void generateExpectedColors() {
		for (int x = 0; x < _ui._colorSpectrumResolution.width; x++) {
			for (int y = 0; y < _ui._colorSpectrumResolution.width; y++) {
				_colorSpectrum[x][y] = Color.HSBtoRGB((float)(x)/_ui._colorSpectrumResolution.width, 1, 1-(float)(y)/_ui._colorSpectrumResolution.width);
			}
		}
	}
	
	public void generateColorToFind() {
		int x = (int) (Math.random() * _ui._colorSpectrumResolution.width);
		int y = (int) (Math.random() * _ui._colorSpectrumResolution.width);
		_colorToFindPoint.setLocation(x, y);
		_colorToFind = _colorSpectrum[x][y];
		System.out.println("Find this color: " + _colorToFind);
		System.out.println("At this index: [" + x + "][" + y + "]");
	}

	public void setSelectedColor(int x, int y) {
		_selectedColor = _colorSpectrum[x][y];
		_selectedColorPoint.setLocation(x, y);
		System.out.println("You selected this color: " + _selectedColor);
		System.out.println("At this index: [" + x + "][" + y + "]");
		System.out.println("You were this close: " + _selectedColorPoint.distance(_colorToFindPoint) / (new Point(0, 0).distance(new Point(_ui._colorSpectrumResolution.height, _ui._colorSpectrumResolution.width))));
	}
	
}
