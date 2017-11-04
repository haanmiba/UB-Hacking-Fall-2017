package model;

import user_interface.*;

import java.awt.Color;

public class Model {

	private MainWindow _ui;
	private ColorGenerator _colorGen; 
	//Score of the player
	private int _points, _lives;
	//Color values to find
	private int[] _rgbColor;
	
	public Model () {
		_colorGen = new ColorGenerator(this);
		_points = 0;
		_lives = 3;
		_rgbColor = new int[] {0, 0, 0};
	}
	
	public void setUI (MainWindow ui) {
		_ui = ui;
	}
	
	/**
	 * Creates a randomly generated RGB values
	 */
	public void newColorToFind () {
		for (int i = 0; i<3; i+=1) {
			_rgbColor[i] = 0 + (int)(Math.random() * 255); 
		}
	}
	
	public Color getColorToFind() {
		return new Color(_rgbColor[0], _rgbColor[1],_rgbColor[2]);
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
