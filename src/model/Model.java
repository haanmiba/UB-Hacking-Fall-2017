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
	private int[][] expectedColors;
	
	public Model (MainWindow ui) {
		_ui = ui;
		_points = 0;
		_lives = 3;
		_rgbColor = new int[] {0, 0, 0};
		_hsbColor = new float[] {0, 1, 0};
		expectedColors = new int[_ui.colorSpectrumResolution.width][_ui.colorSpectrumResolution.width];
		generateExpectedColors();
	}
	
	private void generateExpectedColors() {
		for (int x = 0; x < _ui.colorSpectrumResolution.width; x++) {
			for (int y = 0; y < _ui.colorSpectrumResolution.width; y++) {
				expectedColors[x][y] = Color.HSBtoRGB((float)(x)/_ui.colorSpectrumResolution.width, 1, 1-(float)(y)/_ui.colorSpectrumResolution.width);
			}
		}
	}
	
	public void generateColorToFind() {
		int colorValue = expectedColors[(int) Math.random() * _ui.colorSpectrumResolution.width][(int) Math.random() * _ui.colorSpectrumResolution.width];
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
