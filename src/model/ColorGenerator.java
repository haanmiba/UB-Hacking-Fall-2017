package model;

public class ColorGenerator {

	Model _m;
	
	public ColorGenerator (Model m) {
		_m = m;
	}
	
	public void generateColors () {
		int red = 0 + (int)(Math.random() * 255); 
		int green = 0 + (int)(Math.random() * 255); 
		int blue = 0 + (int)(Math.random() * 255); 
		_m.setColorValsToFind(red, green, blue);
	}
}
