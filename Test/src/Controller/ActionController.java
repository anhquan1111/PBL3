package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ActionModel;
import View.ActionView;

public class ActionController {
	private ActionView view;
	private ActionModel model;

	public ActionController(ActionModel AM, ActionView AV) {
		this.view = AV;
		this.model = AM;
		this.view.Gan(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				int so1 = Integer.parseInt(view.tx_1.getText());
				int so2 = Integer.parseInt(view.tx_2.getText());
				if(src.equals("Cong")) {
					view.tx_3.setText(Integer.toString(so1+so2));
				}
				if(src.equals("Tru")) {
					view.tx_3.setText(Integer.toString(so1-so2));
				}
				if(src.equals("Nhan")) {
					view.tx_3.setText(Integer.toString(so1*so2));
				}
				if(src.equals("Chia")) {
					view.tx_3.setText(Integer.toString(so1/so2));
				}
				if(src.equals("Exit")) {
					System.exit(0);
				}
				if(src.equals("Reset")) {
					view.tx_1.setText(null);
					view.tx_2.setText(null);
					view.tx_3.setText(null);
				}
			}
		});
	}
}
