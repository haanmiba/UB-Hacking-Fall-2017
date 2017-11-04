package model;

import user_interface.*;

public class Model {

	private MainWindow _ui;
	//Score of the player
	private int _points, _lives;
	//Expected color values
	private int _red, _green, _blue;
	
	public Model (MainWindow ui) {
		_ui = ui;
		_points = 0;
		_lives = 3;
	}
	
	public void setColorVals (int r, int g, int b) {
		_red = r;
		_green = g;
		_blue = b;
	}
}
