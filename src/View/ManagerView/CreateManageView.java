package View.ManagerView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class CreateManageView extends JFrame {
    private JPanel headerPanel, sidePanel;
    private JLabel systemLabel, userLabel;

    private JFormattedTextField idField;
    private JTextField nameField;
    private JFormattedTextField dobField;
    private JFormattedTextField phoneField;

    private JButton createButton, cancelButton;

    public CreateManageView(String userName) {
        super("Hệ thống xe buýt");
        initLookAndFeel();
        initComponents(userName);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    private void initComponents(String userName) {
        setLayout(new BorderLayout());

        // HEADER
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(76, 175, 80));
        headerPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
        headerPanel.setPreferredSize(new Dimension(0, 60));
        systemLabel = new JLabel("Hệ thống xe buýt");
        systemLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        systemLabel.setForeground(Color.WHITE);
        headerPanel.add(systemLabel, BorderLayout.WEST);
        userLabel = new JLabel(userName + "  User");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setForeground(Color.WHITE);
        headerPanel.add(userLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // SIDEBAR
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(33, 37, 41));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        sidePanel.setPreferredSize(new Dimension(200, 0));
        String[] menu = {"Bảng điều khiển","Quản lý chuyến đi","Quản lý xe","Báo cáo doanh thu",
                         "Danh sách vé","Tài khoản người dùng","Tạo tài khoản","Đăng xuất"};
        for (String m : menu) {
            boolean sel = m.equals("Tạo tài khoản");
            sidePanel.add(makeSideButton(m, sel));
        }
        add(sidePanel, BorderLayout.WEST);

        // MAIN CONTENT
        JPanel main = new JPanel(new GridBagLayout());
        main.setBackground(Color.WHITE);
        main.setBorder(new EmptyBorder(40, 40, 40, 40));
        add(main, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        // Title
        JLabel title = new JLabel("Tạo tài khoản quản lý");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        main.add(title, gbc);

        // Manager ID
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        main.add(new JLabel("Manager ID:"), gbc);
        gbc.gridx = 1;
        idField = new JFormattedTextField();
        idField.setColumns(12);
        main.add(idField, gbc);

        // Name
        gbc.gridx = 0; gbc.gridy = 2;
        main.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        main.add(nameField, gbc);

        // Date of Birth
        gbc.gridx = 0; gbc.gridy = 3;
        main.add(new JLabel("Ngày sinh (dd/MM/yyyy):"), gbc);
        gbc.gridx = 1;
        try {
            MaskFormatter mf = new MaskFormatter("##/##/####");
            mf.setPlaceholderCharacter('_');
            dobField = new JFormattedTextField(mf);
        } catch (ParseException e) {
            dobField = new JFormattedTextField();
        }
        dobField.setColumns(10);
        main.add(dobField, gbc);

        // Phone
        gbc.gridx = 0; gbc.gridy = 4;
        main.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridx = 1;
        try {
            MaskFormatter mfp = new MaskFormatter("###########");
            mfp.setPlaceholderCharacter('_');
            phoneField = new JFormattedTextField(mfp);
        } catch (ParseException e) {
            phoneField = new JFormattedTextField();
        }
        phoneField.setColumns(12);
        main.add(phoneField, gbc);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        btnPanel.setBackground(Color.WHITE);
        createButton = new JButton("Tạo");
        createButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        createButton.setBackground(new Color(76, 175, 80));
        createButton.setForeground(Color.WHITE);
        createButton.setFocusPainted(false);

        cancelButton = new JButton("Hủy");
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setBackground(new Color(220, 50, 50));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);

        btnPanel.add(cancelButton);
        btnPanel.add(createButton);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.EAST;
        main.add(btnPanel, gbc);

        // Action listeners
        cancelButton.addActionListener(e -> dispose());
        createButton.addActionListener(e -> {
            // TODO: create manager logic
            JOptionPane.showMessageDialog(this, "Đã tạo tài khoản quản lý: " + idField.getText());
            dispose();
        });
    }

    private JButton makeSideButton(String text, boolean selected) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", selected ? Font.BOLD : Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(selected ? new Color(76, 175, 80) : new Color(33, 37, 41));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setFocusPainted(false);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateManageView("Admin Quản lý"));
    }
}