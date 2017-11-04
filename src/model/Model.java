package model;

import user_interface.*;

import java.awt.Color;

import model.*;

public class Model {

	private MainWindow _ui;
	private ColorGenerator _colorGen; 
	//Score of the player
	private int _points, _lives;
	//Color values to find
	private int _toFindRed, _toFindGreen, _toFindBlue;
	
	public Model (MainWindow ui) {
		_ui = ui;
		_colorGen = new ColorGenerator(this);
		_points = 0;
		_lives = 3;
		_toFindRed = 0;
		_toFindGreen = 0;
		_toFindBlue = 0;
	}
	
	/**
	 * Called by ColorGenerator to set a new color
	 * 
	 * @param r - randomly generated red value
	 * @param g - randomly generated blue value
	 * @param b - randomly generated green value
	 */
	public void setColorValsToFind (int r, int g, int b) {
		_toFindRed = r;
		_toFindGreen = g;
		_toFindBlue = b;
	}
	
	public Color getColorToFind() {
		return new Color(_toFindRed, _toFindGreen, _toFindBlue);
	}
	
	/**
	 * Called by ColorPickerListener to get the values of the color chosen by the player
	 * 
	 * @param A - 
	 * @param B -
	 */
	public void setPickedColorValues (int A, int B) {
		
	}
}
