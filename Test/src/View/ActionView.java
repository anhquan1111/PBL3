package View;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ActionView extends JFrame{
	private JPanel panel_1 = new JPanel();
	private JPanel panel_2 = new JPanel();
	private JPanel panel_3 = new JPanel();
	private JPanel panel_4 = new JPanel();
	private JLabel label_1 = new JLabel("minh hoa cac phep toann");
	private JLabel label_tmp = new JLabel();
	private JLabel label_2 = new JLabel("Nhap a:");
	private JLabel label_3 = new JLabel("Nhap b:");
	private JLabel label_4 = new JLabel("Ket qua:");
	public JTextField tx_1 = new JTextField();
	public JTextField tx_2 = new JTextField();
	public JTextField tx_3 = new JTextField();
	public JButton button_1 = new JButton("Cong");
	public JButton button_2 = new JButton("Tru");
	public JButton button_3 = new JButton("Nhan");
	public JButton button_4 = new JButton("Chia");
	public JButton button_5 = new JButton("Exit");
	public JButton button_6 = new JButton("Reset");
	public ActionView(){
		Show();
		this.setVisible(true);
	}
	void Show() {
		this.setTitle("Minh hoa cac phep toan");
		this.setSize(500,400);
		this.setLocation(500,100);
		this.setLayout(new GridLayout(4,1));
		this.add(panel_1);
		this.add(panel_2);
		this.add(panel_3);
		this.add(panel_4);
		panel_1.setLayout(new GridLayout(1,2));
		panel_1.add(label_1);
		panel_1.add(label_tmp);
		panel_2.setLayout(new GridLayout(3,2));
		panel_2.add(label_2);
		panel_2.add(tx_1);
		panel_2.add(label_3);
		panel_2.add(tx_2);
		panel_2.add(label_4);
		panel_2.add(tx_3);
		panel_3.setLayout(new FlowLayout());
		panel_3.add(button_1);
		panel_3.add(button_2);
		panel_3.add(button_3);
		panel_3.add(button_4);
		panel_4.setLayout(new FlowLayout());
		panel_4.add(button_5);
		panel_4.add(button_6);
		tx_1.setActionCommand("text1");
		tx_2.setActionCommand("text2");
		tx_3.setActionCommand("text3");
	}
	public void Gan(ActionListener AL) {
//		tx_1.addActionListener(AL);
//		tx_2.addActionListener(AL);
//		tx_3.addActionListener(AL);
		button_1.addActionListener(AL);
		button_2.addActionListener(AL);
		button_3.addActionListener(AL);
		button_4.addActionListener(AL);
		button_5.addActionListener(AL);
		button_6.addActionListener(AL);
	}
}
