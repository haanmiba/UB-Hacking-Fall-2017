package model;

public class ColorGenerator {

	Model _m;
	
	public ColorGenerator (Model m) {
	}
	
	public void generateColors () {
		int red = 0 + (int)(Math.random() * 255); 
		int green = 0 + (int)(Math.random() * 255); 
		int blue = 0 + (int)(Math.random() * 255); 
		_m.setColorVals(red, green, blue);
	}
}
