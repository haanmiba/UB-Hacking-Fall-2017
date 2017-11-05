package listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import user_interface.MainWindow;
import model.Model;

public class ColorPickerListener implements MouseListener, MouseMotionListener {

	private Model _m;
	private MainWindow _window;
	private int selectedX, selectedY;
	
	public ColorPickerListener (Model m, MainWindow mw) {
		_m = m;
		_window = mw;
		selectedX = -1;
		selectedY = -1;
	}
	
	public void mouseClicked (MouseEvent e) { }
	
	public void mousePressed (MouseEvent e) { }
		
	public void mouseReleased (MouseEvent e) {
		selectedX = e.getX();
		selectedY = e.getY();
		// Send coordinates to model
		
		if ((selectedX > 0 && selectedX < _window.colorSpectrumResolution.width) && 
				(selectedY > 0 && selectedY < _window.colorSpectrumResolution.width)) {
			_m.setSelectedColor(selectedX, selectedY);
		}
		
		
		// Reset selected coordinates
		selectedX = -1;
		selectedY = -1;
	}

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

	public void mouseDragged(MouseEvent e) { }

	public void mouseMoved(MouseEvent e) { }
	
}
