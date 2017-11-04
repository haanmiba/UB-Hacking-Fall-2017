package listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.Model;

public class ColorPickerListener implements MouseListener, MouseMotionListener {

	private Model _m;
	private int selectedX, selectedY;
	
	public ColorPickerListener (Model m) {
		_m = m;
		selectedX = -1;
		selectedY = -1;
	}
	
	public void mouseClicked (MouseEvent e) {
		selectedX = e.getX();
		selectedY = e.getY();
		// Send coordinates to model
	}
	
	public void mousePressed (MouseEvent e) { }
		
	public void mouseReleased (MouseEvent e) {
		selectedX = e.getX();
		selectedY = e.getY();
		// Send coordinates to model
		selectedX = -1;
		selectedY = -1;
	}

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

	public void mouseDragged(MouseEvent e) { }

	public void mouseMoved(MouseEvent e) { }
	
}