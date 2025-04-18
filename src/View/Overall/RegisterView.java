//package View.Overall;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JLabel;
//import java.awt.Font;
//import javax.swing.JSeparator;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import javax.swing.JTextField;
//import javax.swing.border.LineBorder;
//import java.awt.Color;
//
//public class RegisterView extends JFrame {
//
//	private static final long serialVersionUID = 1L;
//	private JPanel contentPane;
//	private JTextField textField_2;
//	private JTextField textField_3;
//	private JTextField textField;
//	private JTextField textField_1;
//	private JButton btnNewButton;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegisterView frame = new RegisterView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public RegisterView() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 685, 463);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
//		this.setSize(600,330);
//		this.setLocation(500,200);
//		this.setResizable(false);
//	
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JLabel lblNewLabel = new JLabel("Đăng Ký");
//		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
//		lblNewLabel.setBounds(234, 0, 352, 68);
//		contentPane.add(lblNewLabel);
//		
//		btnNewButton = new JButton("Hoàn Thành");
//		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnNewButton.setBounds(198, 233, 181, 47);
//		contentPane.add(btnNewButton);
//		
//		JPanel panel = new JPanel();
//		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel.setBounds(0, 67, 586, 156);
//		contentPane.add(panel);
//		panel.setLayout(null);
//		
//		JLabel lblNewLabel_1_2 = new JLabel("Mật Khẩu");
//		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblNewLabel_1_2.setBounds(303, 29, 95, 22);
//		panel.add(lblNewLabel_1_2);
//		
//		textField_2 = new JTextField();
//		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		textField_2.setColumns(10);
//		textField_2.setBounds(408, 20, 157, 37);
//		panel.add(textField_2);
//		
//		JLabel lblNewLabel_1_2_1 = new JLabel("Họ Tên");
//		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblNewLabel_1_2_1.setBounds(10, 27, 95, 22);
//		panel.add(lblNewLabel_1_2_1);
//		
//		JLabel lblNewLabel_1_2_2 = new JLabel("Ngày Sinh");
//		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblNewLabel_1_2_2.setBounds(10, 100, 95, 28);
//		panel.add(lblNewLabel_1_2_2);
//		
//		JLabel lblNewLabel_1_2_3 = new JLabel("SĐT");
//		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblNewLabel_1_2_3.setBounds(303, 103, 95, 22);
//		panel.add(lblNewLabel_1_2_3);
//		
//		textField_3 = new JTextField();
//		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		textField_3.setColumns(10);
//		textField_3.setBounds(408, 94, 157, 37);
//		panel.add(textField_3);
//		
//		textField = new JTextField();
//		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		textField.setColumns(10);
//		textField.setBounds(115, 20, 157, 37);
//		panel.add(textField);
//		
//		textField_1 = new JTextField();
//		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		textField_1.setColumns(10);
//		textField_1.setBounds(115, 94, 157, 37);
//		panel.add(textField_1);
//		this.setVisible(true);
//		
//	}
//	public void SetActionListener(ActionListener al) {
//		textField.addActionListener(al);
//		textField_1.addActionListener(al);
//		textField_2.addActionListener(al);
//		textField_3.addActionListener(al);
//		btnNewButton.addActionListener(al);
//	}
//}

package View.Overall;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterView extends JFrame {
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField phoneField;
    private JTextField dobField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JLabel loginLabel;

    public RegisterView() {
        super("Đăng ký tài khoản / Register");
        // Kích hoạt Nimbus L&F nếu có
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // fallback L&F mặc định
        }

        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        // Root panel với BoxLayout theo chiều dọc
        JPanel root = new JPanel();
        root.setBackground(Color.WHITE);
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Tiêu đề
        JLabel title = new JLabel("Đăng ký tài khoản");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(33, 33, 33));
        root.add(title);
        root.add(Box.createRigidArea(new Dimension(0, 20)));

        // Form panel
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Họ
        lastNameField = new JTextField(12);
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Họ:"), gbc);
        gbc.gridx = 1;
        form.add(lastNameField, gbc);

        // Tên
        firstNameField = new JTextField(12);
        gbc.gridx = 2; gbc.gridy = 0;
        form.add(new JLabel("Tên:"), gbc);
        gbc.gridx = 3;
        form.add(firstNameField, gbc);

        // Số điện thoại
        phoneField = new JTextField(20);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 4;
        form.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridy = 2;
        form.add(phoneField, gbc);

        // Ngày sinh
        dobField = new JTextField(20);
        gbc.gridy = 3;
        form.add(new JLabel("Ngày sinh (dd/MM/yyyy):"), gbc);
        gbc.gridy = 4;
        form.add(dobField, gbc);

        // Mật khẩu
        passwordField = new JPasswordField(20);
        gbc.gridy = 5;
        form.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridy = 6;
        form.add(passwordField, gbc);

        // Xác nhận mật khẩu
        confirmPasswordField = new JPasswordField(20);
        gbc.gridy = 7;
        form.add(new JLabel("Xác nhận mật khẩu:"), gbc);
        gbc.gridy = 8;
        form.add(confirmPasswordField, gbc);

        root.add(form);
        root.add(Box.createRigidArea(new Dimension(0, 20)));

        // Nút Đăng ký
        registerButton = new JButton("Đăng ký  ✓");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setBackground(new Color(76, 175, 80));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        root.add(registerButton);
        root.add(Box.createRigidArea(new Dimension(0, 15)));

        // Link Đăng nhập
        loginLabel = new JLabel(
            "<HTML><U>Đã có tài khoản? Đăng nhập ngay</U></HTML>"
        );
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginLabel.setForeground(new Color(33, 150, 243));
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: điều hướng về LoginView
                JOptionPane.showMessageDialog(
                    RegisterView.this,
                    "Chuyển sang giao diện Đăng nhập"
                );
            }
        });
        root.add(loginLabel);

        // Thêm root vào Frame
        getContentPane().add(root);
    }

    // Getters cho Controller
    public String getLastName() {
        return lastNameField.getText().trim();
    }
    public String getFirstName() {
        return firstNameField.getText().trim();
    }
    public String getPhone() {
        return phoneField.getText().trim();
    }
    public String getDob() {
        return dobField.getText().trim();
    }
    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    public String getConfirmPassword() {
        return new String(confirmPasswordField.getPassword());
    }
    public JButton getRegisterButton() {
        return registerButton;
    }
    public JLabel getLoginLabel() {
        return loginLabel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterView::new);
    }
}
