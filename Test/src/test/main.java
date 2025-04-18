package test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Controller.ActionController;
import Model.ActionModel;
import View.ActionView;

public class main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		ActionView view = new ActionView();
		ActionModel model = new ActionModel();
		ActionController controller = new ActionController(model, view);
	}
}
