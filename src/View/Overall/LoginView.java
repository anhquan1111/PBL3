//package View.Overall;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//public class LoginView extends JFrame {
//
//	private static final long serialVersionUID = 1L;
//	private JPanel contentPane;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginView frame = new LoginView();
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
//	public LoginView() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//	}
//
//}

package View.Overall;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame {
    private JTextField phoneField;           
    private JPasswordField passwordField;    
    private JButton loginButton;             
    private JLabel registerLabel;            

    public LoginView() {
        super("Đăng nhập hệ thống / Login");
        // Kích hoạt Nimbus L&F (nếu có) / Enable Nimbus Look & Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Nimbus không khả dụng, dùng mặc định / fallback
        }

        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);   
        setVisible(true);
    }

    private void initComponents() {
        // ROOT PANEL với BoxLayout dọc / vertical BoxLayout
        JPanel root = new JPanel();
        root.setBackground(Color.WHITE);
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Tiêu đề / Title
        JLabel title = new JLabel("Đăng nhập hệ thống");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(33, 33, 33));
        root.add(title);
        root.add(Box.createRigidArea(new Dimension(0, 20)));

        // FORM PANEL với GridBagLayout
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Số điện thoại / Phone
        phoneField = new JTextField(15);
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridx = 1;
        form.add(phoneField, gbc);

        // Mật khẩu / Password
        passwordField = new JPasswordField(15);
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx = 1;
        form.add(passwordField, gbc);

        root.add(form);
        root.add(Box.createRigidArea(new Dimension(0, 20)));

        // Nút Đăng nhập / Login Button
        loginButton = new JButton("Đăng nhập  ⭢");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBackground(new Color(76, 175, 80));      // xanh lá tươi
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        root.add(loginButton);
        root.add(Box.createRigidArea(new Dimension(0, 15)));

        // Link Đăng ký / Register Link
        registerLabel = new JLabel(
            "<HTML><U>Chưa có tài khoản? Đăng ký ngay</U></HTML>"
        );
        registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        registerLabel.setForeground(new Color(33, 150, 243));    // xanh dương
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: chuyển sang RegisterView / navigate to RegisterView
                JOptionPane.showMessageDialog(
                    LoginView.this,
                    "Đi tới giao diện Đăng ký / Go to RegisterView"
                );
            }
        });
        root.add(registerLabel);

        // Gắn root panel vào JFrame
        getContentPane().add(root);
    }

    // Getters cho Controller
    public String getPhone() {
        return phoneField.getText().trim();
    }
    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    public JButton getLoginButton() {
        return loginButton;
    }
    public JLabel getRegisterLabel() {
        return registerLabel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginView::new);
    }
}
