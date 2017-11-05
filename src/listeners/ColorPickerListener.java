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
	private int _selectedX, _selectedY;
	
	public ColorPickerListener (Model m, MainWindow mw) {
		_m = m;
		_window = mw;
		_selectedX = -1;
		_selectedY = -1;
	}
	
	public void mouseClicked (MouseEvent e) { }
	
	public void mousePressed (MouseEvent e) { }
		
	public void mouseReleased (MouseEvent e) {
		_selectedX = e.getX();
		_selectedY = e.getY();
		// Send coordinates to model
		
		if ((_selectedX >= 0 && _selectedX < _window._colorSpectrumResolution.width) && 
				(_selectedY >= 0 && _selectedY < _window._colorSpectrumResolution.width)) {
			_m.setSelectedColor(_selectedX, _selectedY);
		}
		
		
		// Reset selected coordinates
		_selectedX = -1;
		_selectedY = -1;
	}

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

	public void mouseDragged(MouseEvent e) { }

	public void mouseMoved(MouseEvent e) { }
	
}
