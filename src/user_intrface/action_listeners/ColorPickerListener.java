package user_intrface.action_listeners;

import java.awt.event.MouseAdapter;
import model.Model;

public class ColorPickerListener extends MouseAdapter {

	private Model _m;
	
	public ColorPicker (Model m) {
		_m = m;
	}
	
	public void mouseClicked (MouseEvent e) {
		
	}

}
