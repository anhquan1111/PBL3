package Test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import View.Overall.MainView;
import View.Overall.RegisterView;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		new MainView();
		new RegisterView();
	}
}
