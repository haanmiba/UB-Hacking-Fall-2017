package user_interface.action_listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Model;

public class ColorPickerListener extends MouseAdapter {

	private Model _m;
	
	public ColorPickerListener (Model m) {
		_m = m;
	}
	
	@Override
	public void MouseClicked (MouseEvent e) {
		
	}
}
