package model_tests;

import java.awt.Color;
import static org.junit.Assert.*;

import model.Model;

public class ColorGeneratorTest {
	
	Model _model;
	
	@Before
	public void setUP() {
		_model = new Model();
	}
	
	@Test
	public void testColors () {
		_model.newColorToFind();
		Color generated = _model.getColorToFind();
		int r = generated.getRed();
		int g = generated.getGreen();
		int b = generated.getBlue();
		assertTrue("The red value generated was" + r + ".", r>-1 && r<256);
		assertTrue("The green value generated was" + g + ".", g>-1 && g<256);
		assertTrue("The blue value generated was" + b + ".", b>-1 && b<256);
	}
	
}
