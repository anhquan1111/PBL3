package Test;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class View extends JFrame{
	public View(){
		Show();
	}
	public void Show() {
		this.setTitle("Test giao dien");
		this.setSize(500,500);
		this.setLocation(500,100);
		this.setLayout(new GridLayout(2,1));
		this.add(new Button("OK"));
		this.add(new Button("Hello"));
		this.setVisible(true);
	}
}
