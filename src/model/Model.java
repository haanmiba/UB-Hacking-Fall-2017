package model;

import user_interface.*;

import java.awt.Color;
import java.util.Random;

public class Model {

	private Random _random;
	private MainWindow _ui;
	//Score of the player
	private int _points, _lives;
	//Color values to find
	private int[] _rgbColor;
	private float[] _hsbColor;
	
	public Model (MainWindow ui) {
		_ui = ui;
		_points = 0;
		_lives = 3;
		_rgbColor = new int[] {0, 0, 0};
		_hsbColor = new float[] {0, 1, 0};
	}
	
	/**
	 * Creates a randomly generated RGB values
	 */
	public void newColorToFind () {
		_hsbColor[0] = _random.nextFloat();
		_hsbColor[2] = _random.nextFloat();
		int rgb = Color.HSBtoRGB(_hsbColor[0], _hsbColor[1], _hsbColor[2]);
		_rgbColor[0] = (rgb>>16)&0xFF;
		_rgbColor[1] = (rgb>>8)&0xFF;
		_rgbColor[2] = rgb&0xFF;
		_ui.setToFind(new Color(_rgbColor[0], _rgbColor[1],_rgbColor[2]));
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
