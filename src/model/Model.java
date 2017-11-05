package model;

import user_interface.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

public class Model {

	private Random _random;
	private MainWindow _ui;
	//Score of the player
	private int _points, _lives;
	//Color values to find
	private int colorToFind;
	private Point colorToFindPoint;
	private int selectedColor;
	private Point selectedColorPoint;
	private int[][] expectedColors;
	
	public Model (MainWindow ui) {
		_ui = ui;
		_points = 0;
		_lives = 3;
		colorToFind = 0;
		selectedColor = 0;
		colorToFindPoint = new Point(-1, -1);
		selectedColorPoint = new Point(-1, -1);
		expectedColors = new int[_ui.colorSpectrumResolution.width][_ui.colorSpectrumResolution.width];
		generateExpectedColors();
		generateColorToFind();
	}
	
	private void generateExpectedColors() {
		for (int x = 0; x < _ui.colorSpectrumResolution.width; x++) {
			for (int y = 0; y < _ui.colorSpectrumResolution.width; y++) {
				expectedColors[x][y] = Color.HSBtoRGB((float)(x)/_ui.colorSpectrumResolution.width, 1, 1-(float)(y)/_ui.colorSpectrumResolution.width);
			}
		}
	}
	
	public void generateColorToFind() {
		int x = (int) (Math.random() * _ui.colorSpectrumResolution.width);
		int y = (int) (Math.random() * _ui.colorSpectrumResolution.width);
		colorToFindPoint.setLocation(x, y);
		colorToFind = expectedColors[x][y];
		System.out.println("Find this color: " + colorToFind);
		System.out.println("At this index: [" + x + "][" + y + "]");
	}
		
	/**
	 * Called by ColorPickerListener to get the values of the color chosen by the player
	 * 
	 * @param A - 
	 * @param B -
	 */
	public void setPickedColorValues (int A, int B) {
		
	}

	public void setSelectedColor(int x, int y) {
		selectedColor = expectedColors[x][y];
		selectedColorPoint.setLocation(x, y);
		System.out.println("You selected this color: " + selectedColor);
		System.out.println("At this index: [" + x + "][" + y + "]");
		System.out.println("You were this close: " + selectedColorPoint.distance(colorToFindPoint));
	}
	
}
