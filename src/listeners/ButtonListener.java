package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import user_interface.MainWindow;

public class ButtonListener implements ActionListener {

	private MainWindow _window;
	private int _selection;
	
	public ButtonListener (MainWindow mw, int s) {
		_window = mw;
		_selection = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(_selection) {
		
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			_window.nextPage();
			break;

		}
	}

}
