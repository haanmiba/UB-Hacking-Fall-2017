package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import user_interface.MainWindow;

public class ButtonListener implements ActionListener {

	private MainWindow _window;
	
	public ButtonListener (MainWindow mw) {
		_window = mw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		_window.nextPage();
	}

}
