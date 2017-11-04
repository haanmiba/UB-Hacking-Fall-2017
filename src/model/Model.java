package model;

import user_interface.*;

public class Model {

	private MainWindow _ui;
	private int _points, _lives;
	
	public Model (MainWindow ui) {
		_ui = ui;
		_points = 0;
		_lives = 3;
	}
}
