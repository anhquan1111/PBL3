package Test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import View.CustomerView.RegisterView;
import View.Overall.MainView;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		new MainView();
		new RegisterView();
	}
}
