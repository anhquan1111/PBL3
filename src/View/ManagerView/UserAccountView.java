//package View.ManagerView;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.text.*;
//import java.util.*;
//
//public class UserAccountView extends JFrame {
//    // Header & sidebar
//    private JPanel headerPanel, sidePanel;
//    private JLabel systemLabel, userLabel;
//
//    // Buttons
//    private JButton addAccountButton;
//
//    // Filter fields
//    private JComboBox<String> roleFilter;
//    private JTextField nameFilter;
//    private JButton filterButton;
//
//    // Table and pagination
//    private JTable userTable;
//    private DefaultTableModel tableModel;
//    private JPanel paginationPanel;
//
//    public UserAccountView(String userName) {
//        super("Hệ thống xe buýt");
//        initLookAndFeel();
//        initComponents(userName);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setSize(1100, 650);
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    private void initLookAndFeel() {
//        try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (Exception ignored) {}
//    }
//
//    private void initComponents(String userName) {
//        setLayout(new BorderLayout());
//
//        // ===== HEADER =====
//        headerPanel = new JPanel(new BorderLayout());
//        headerPanel.setBackground(new Color(76, 175, 80));
//        headerPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
//        headerPanel.setPreferredSize(new Dimension(0, 60));
//        systemLabel = new JLabel("Hệ thống xe buýt");
//        systemLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
//        systemLabel.setForeground(Color.WHITE);
//        headerPanel.add(systemLabel, BorderLayout.WEST);
//        userLabel = new JLabel(userName + "  User");
//        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        userLabel.setForeground(Color.WHITE);
//        headerPanel.add(userLabel, BorderLayout.EAST);
//        add(headerPanel, BorderLayout.NORTH);
//
//        // ===== SIDEBAR =====
//        sidePanel = new JPanel();
//        sidePanel.setBackground(new Color(33, 37, 41));
//        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
//        sidePanel.setBorder(new EmptyBorder(20, 10, 10, 10));
//        sidePanel.setPreferredSize(new Dimension(200, 0));
//        String[] menu = {"Bảng điều khiển","Quản lý chuyến đi","Quản lý xe","Báo cáo doanh thu",
//                         "Danh sách vé","Tài khoản người dùng","Tạo tài khoản","Đăng xuất"};
//        for (String m : menu) {
//            boolean sel = m.equals("Tài khoản người dùng");
//            sidePanel.add(makeSideButton(m, sel));
//        }
//        add(sidePanel, BorderLayout.WEST);
//
//        // ===== MAIN CONTENT =====
//        JPanel main = new JPanel(new BorderLayout(10,10));
//        main.setBackground(Color.WHITE);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
//        add(main, BorderLayout.CENTER);
//
//        // Title + Add
//        JPanel topPanel = new JPanel(new BorderLayout()); topPanel.setBackground(Color.WHITE);
//        JLabel titleLabel = new JLabel("Tài khoản người dùng");
//        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
//        topPanel.add(titleLabel, BorderLayout.WEST);
//        addAccountButton = new JButton("Tạo tài khoản");
//        addAccountButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        addAccountButton.setBackground(new Color(76,175,80)); addAccountButton.setForeground(Color.WHITE);
//        addAccountButton.setFocusPainted(false);
//        topPanel.add(addAccountButton, BorderLayout.EAST);
//        main.add(topPanel, BorderLayout.NORTH);
//
//        // Center split: table center + filter east
//        JPanel centerPanel = new JPanel(new BorderLayout(10,10)); centerPanel.setBackground(Color.WHITE);
//
//        // Table area
//        String[] cols = {"User ID","Họ tên","Ngày sinh","Số điện thoại","Vai trò","Hành động"};
//        tableModel = new DefaultTableModel(cols, 0) {
//            @Override public boolean isCellEditable(int row, int col) { return col == 5; }
//        };
//        // Sample data
//        tableModel.addRow(new Object[]{"U001","Nguyễn Văn A","01/01/1990","0123456789","Customer", null});
//        tableModel.addRow(new Object[]{"U002","Trần Thị B","15/05/1985","0987654321","Manager", null});
//        userTable = new JTable(tableModel);
//        userTable.setRowHeight(30);
//        userTable.getColumn("Hành động").setCellRenderer(new ActionRenderer());
//        userTable.getColumn("Hành động").setCellEditor(new ActionEditor());
//        centerPanel.add(new JScrollPane(userTable), BorderLayout.CENTER);
//
//        // Pagination
//        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0)); paginationPanel.setBackground(Color.WHITE);
//        paginationPanel.add(makePageButton("1", false));
//        paginationPanel.add(makePageButton("2", true));
//        paginationPanel.add(makePageButton("3", true));
//        paginationPanel.add(new JLabel("..."));
//        paginationPanel.add(makePageButton("10", true));
//        centerPanel.add(paginationPanel, BorderLayout.SOUTH);
//
//        main.add(centerPanel, BorderLayout.CENTER);
//
//        // Filter panel on right
//        JPanel filterPanel = new JPanel(new GridBagLayout()); filterPanel.setBackground(Color.WHITE);
//        filterPanel.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(220,220,220)), new EmptyBorder(10,10,10,10)));
//        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(5,5,5,5);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridx=0; gbc.gridy=0;
//        filterPanel.add(new JLabel("Vai trò:"), gbc);
//        gbc.gridx=1;
//        roleFilter = new JComboBox<>(new String[]{"Tất cả","Customer","Manager"});
//        filterPanel.add(roleFilter, gbc);
//        gbc.gridx=0; gbc.gridy=1;
//        filterPanel.add(new JLabel("Họ tên:"), gbc);
//        gbc.gridx=1;
//        nameFilter = new JTextField(12);
//        filterPanel.add(nameFilter, gbc);
//        gbc.gridx=0; gbc.gridy=2; gbc.gridwidth=2;
//        filterButton = new JButton("Lọc");
//        filterButton.setBackground(new Color(33,150,243)); filterButton.setForeground(Color.WHITE);
//        filterButton.setFocusPainted(false);
//        filterPanel.add(filterButton, gbc);
//        main.add(filterPanel, BorderLayout.EAST);
//
//        // Add account dialog
//        addAccountButton.addActionListener(e -> new AddAccountView(this));
//    }
//
//    private JButton makeSideButton(String text, boolean selected) {
//        JButton btn = new JButton(text);
//        btn.setFont(new Font("Segoe UI", selected?Font.BOLD:Font.PLAIN,14));
//        btn.setForeground(Color.WHITE);
//        btn.setBackground(selected?new Color(76,175,80):new Color(33,37,41));
//        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE,40)); btn.setFocusPainted(false);
//        return btn;
//    }
//
//    private JButton makePageButton(String text, boolean enabled) {
//        JButton btn = new JButton(text); btn.setEnabled(enabled);
//        if (!enabled) { btn.setBackground(new Color(76,175,80)); btn.setForeground(Color.WHITE); }
//        return btn;
//    }
//
//    // Renderer & Editor for action column
//    private class ActionRenderer extends JPanel implements TableCellRenderer {
//        public ActionRenderer() { setLayout(new FlowLayout(FlowLayout.CENTER,5,0)); }
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value,
//                                                       boolean isSelected, boolean hasFocus,
//                                                       int row, int col) {
//            removeAll();
//            JButton edit = new JButton("Sửa"); edit.setBackground(new Color(255,193,7)); edit.setForeground(Color.WHITE);
//            JButton del  = new JButton("Xóa"); del.setBackground(new Color(220,50,50)); del.setForeground(Color.WHITE);
//            add(edit); add(del);
//            return this;
//        }
//    }
//
//    private class ActionEditor extends AbstractCellEditor implements TableCellEditor {
//        private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
//        public ActionEditor() {
//            JButton edit = new JButton("Sửa"); edit.setBackground(new Color(255,193,7)); edit.setForeground(Color.WHITE);
//            JButton del  = new JButton("Xóa"); del.setBackground(new Color(220,50,50)); del.setForeground(Color.WHITE);
//            panel.add(edit); panel.add(del);
//        }
//        @Override public Component getTableCellEditorComponent(JTable table, Object value,
//                                                                 boolean isSelected, int row, int column) {
//            return panel;
//        }
//        @Override public Object getCellEditorValue() {
//            return null;
//        }
//    }
//
//    // Dialog for adding/editing user account
//    private static class AddAccountView extends JDialog {
//        public AddAccountView(Frame owner) {
//            super(owner, "Tạo tài khoản", true);
//            initComponents();
//            pack(); setLocationRelativeTo(owner); setVisible(true);
//        }
//        private void initComponents() {
//            JPanel p = new JPanel(new GridBagLayout()); p.setBorder(new EmptyBorder(15,15,15,15));
//            GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(5,5,5,5); gbc.anchor = GridBagConstraints.WEST;
//            gbc.gridx=0; gbc.gridy=0; p.add(new JLabel("User ID:"), gbc);
//            gbc.gridx=1; JTextField idField = new JTextField(10); p.add(idField, gbc);
//            gbc.gridx=0; gbc.gridy=1; p.add(new JLabel("Họ tên:"), gbc);
//            gbc.gridx=1; JTextField nameField = new JTextField(12); p.add(nameField, gbc);
//            gbc.gridx=0; gbc.gridy=2; p.add(new JLabel("Ngày sinh (dd/MM/yyyy):"), gbc);
//            gbc.gridx=1; try {
//                MaskFormatter mf = new MaskFormatter("##/##/####"); mf.setPlaceholderCharacter('_');
//                JFormattedTextField dobField = new JFormattedTextField(mf);
//                dobField.setColumns(10); p.add(dobField, gbc);
//            } catch (ParseException e) {
//                JTextField dobField = new JTextField(10); p.add(dobField, gbc);
//            }
//            gbc.gridx=0; gbc.gridy=3; p.add(new JLabel("Số điện thoại:"), gbc);
//            gbc.gridx=1; JTextField phoneField = new JTextField(12); p.add(phoneField, gbc);
//            gbc.gridx=0; gbc.gridy=4; p.add(new JLabel("Vai trò:"), gbc);
//            gbc.gridx=1; JComboBox<String> roleBox = new JComboBox<>(new String[]{"Customer","Manager"}); p.add(roleBox, gbc);
//            // Buttons
//            JPanel btnP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//            JButton save = new JButton("Lưu"); save.setBackground(new Color(76,175,80)); save.setForeground(Color.WHITE);
//            JButton cancel = new JButton("Hủy"); cancel.setBackground(new Color(220,50,50)); cancel.setForeground(Color.WHITE);
//            btnP.add(save); btnP.add(cancel);
//            gbc.gridx=0; gbc.gridy=5; gbc.gridwidth=2; gbc.anchor = GridBagConstraints.EAST; p.add(btnP, gbc);
//            setContentPane(p);
//            cancel.addActionListener(e -> dispose());
//            save.addActionListener(e -> {
//                // TODO: save logic
//                dispose();
//            });
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new UserAccountView("Admin Quản lý"));
//    }
//}

package View.ManagerView;

//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.text.*;
//import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.text.MaskFormatter;

public class UserAccountView extends JFrame {
    // Header & sidebar
    private JPanel headerPanel, sidePanel;
    private JLabel systemLabel, userLabel;

    // Filter fields
    private JComboBox<String> roleFilter;
    private JTextField nameFilter;
    private JButton filterButton;

    // Table and pagination
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JPanel paginationPanel;

    public UserAccountView(String userName) {
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

        // ===== HEADER =====
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

        // ===== SIDEBAR =====
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(33, 37, 41));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        sidePanel.setPreferredSize(new Dimension(200, 0));
        String[] menu = {"Bảng điều khiển","Quản lý chuyến đi","Quản lý xe","Báo cáo doanh thu",
                         "Danh sách vé","Tài khoản người dùng","Tạo tài khoản","Đăng xuất"};
        for (String m : menu) {
            boolean sel = m.equals("Tài khoản người dùng");
            sidePanel.add(makeSideButton(m, sel));
        }
        add(sidePanel, BorderLayout.WEST);

        // ===== MAIN CONTENT =====
        JPanel main = new JPanel(new BorderLayout(10,10));
        main.setBackground(Color.WHITE);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(main, BorderLayout.CENTER);

        // Title
        JPanel topPanel = new JPanel(new BorderLayout()); topPanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Tài khoản người dùng");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        topPanel.add(titleLabel, BorderLayout.WEST);
        main.add(topPanel, BorderLayout.NORTH);

        // Center: table + pagination
        JPanel centerPanel = new JPanel(new BorderLayout(10,10)); centerPanel.setBackground(Color.WHITE);

        // Table
        String[] cols = {"User ID","Họ tên","Ngày sinh","Số điện thoại","Vai trò","Hành động"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int row, int col) { return col == 5; }
        };
        // Sample data
        tableModel.addRow(new Object[]{"U001","Nguyễn Văn A","01/01/1990","0123456789","Customer", null});
        tableModel.addRow(new Object[]{"U002","Trần Thị B","15/05/1985","0987654321","Manager", null});
        tableModel.addRow(new Object[]{"U003","Lê Văn C","20/08/1992","0912345678","Customer", null});
        userTable = new JTable(tableModel);
        userTable.setRowHeight(30);
        userTable.getColumn("Hành động").setCellRenderer(new ActionRenderer());
        userTable.getColumn("Hành động").setCellEditor(new ActionEditor());
        centerPanel.add(new JScrollPane(userTable), BorderLayout.CENTER);

        // Pagination
        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0)); paginationPanel.setBackground(Color.WHITE);
        paginationPanel.add(makePageButton("1", false));
        paginationPanel.add(makePageButton("2", true));
        paginationPanel.add(makePageButton("3", true));
        paginationPanel.add(new JLabel("..."));
        paginationPanel.add(makePageButton("10", true));
        centerPanel.add(paginationPanel, BorderLayout.SOUTH);

        main.add(centerPanel, BorderLayout.CENTER);

        // Filter panel
        JPanel filterPanel = new JPanel(new GridBagLayout()); filterPanel.setBackground(Color.WHITE);
        filterPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)), new EmptyBorder(10,10,10,10)));
        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(5,5,5,5); gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx=0; gbc.gridy=0; filterPanel.add(new JLabel("Vai trò:"), gbc);
        gbc.gridx=1; roleFilter = new JComboBox<>(new String[]{"Tất cả","Customer","Manager"}); filterPanel.add(roleFilter, gbc);
        gbc.gridx=0; gbc.gridy=1; filterPanel.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx=1; nameFilter = new JTextField(12); filterPanel.add(nameFilter, gbc);
        gbc.gridx=0; gbc.gridy=2; gbc.gridwidth=2; filterButton = new JButton("Lọc");
        filterButton.setBackground(new Color(33,150,243)); filterButton.setForeground(Color.WHITE); filterButton.setFocusPainted(false);
        filterPanel.add(filterButton, gbc);
        main.add(filterPanel, BorderLayout.EAST);
    }

    private JButton makeSideButton(String text, boolean selected) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", selected?Font.BOLD:Font.PLAIN,14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(selected?new Color(76,175,80):new Color(33,37,41));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE,40)); btn.setFocusPainted(false);
        return btn;
    }

    private JButton makePageButton(String text, boolean enabled) {
        JButton btn = new JButton(text); btn.setEnabled(enabled);
        if (!enabled) { btn.setBackground(new Color(76,175,80)); btn.setForeground(Color.WHITE); }
        return btn;
    }

    // Renderer & Editor for action column
    private class ActionRenderer extends JPanel implements TableCellRenderer {
        public ActionRenderer() { setLayout(new FlowLayout(FlowLayout.CENTER,5,0)); }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int col) {
            removeAll();
            JButton edit = new JButton("Sửa"); edit.setBackground(new Color(255,193,7)); edit.setForeground(Color.WHITE);
            JButton del  = new JButton("Xóa"); del.setBackground(new Color(220,50,50)); del.setForeground(Color.WHITE);
            add(edit); add(del);
            return this;
        }
    }

    private class ActionEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
        public ActionEditor() {
            JButton edit = new JButton("Sửa"); edit.setBackground(new Color(255,193,7)); edit.setForeground(Color.WHITE);
            JButton del  = new JButton("Xóa"); del.setBackground(new Color(220,50,50)); del.setForeground(Color.WHITE);
            panel.add(edit); panel.add(del);
            edit.addActionListener(e -> {
                int row = userTable.getSelectedRow();
                if (row >= 0) {
                    String id     = (String) tableModel.getValueAt(row, 0);
                    String name   = (String) tableModel.getValueAt(row, 1);
                    String dob    = (String) tableModel.getValueAt(row, 2);
                    String phone  = (String) tableModel.getValueAt(row, 3);
                    String role   = (String) tableModel.getValueAt(row, 4);
                    new EditAccountView(UserAccountView.this, id, name, dob, phone, role);
                }
            });
        }
        @Override public Component getTableCellEditorComponent(JTable table, Object value,
                                                                 boolean isSelected, int row, int column) {
            return panel;
        }
        @Override public Object getCellEditorValue() {
            return null;
        }
    }

    // Dialog for editing user account
    private static class EditAccountView extends JDialog {
        public EditAccountView(Frame owner, String id, String name, String dob, String phone, String role) {
            super(owner, "Sửa tài khoản", true);
            initComponents(id, name, dob, phone, role);
            pack(); setLocationRelativeTo(owner); setVisible(true);
        }
        private void initComponents(String id, String name, String dob, String phone, String role) {
            JPanel p = new JPanel(new GridBagLayout()); p.setBorder(new EmptyBorder(15,15,15,15));
            GridBagConstraints gbc=new GridBagConstraints(); gbc.insets=new Insets(5,5,5,5); gbc.anchor=GridBagConstraints.WEST;
            gbc.gridx=0; gbc.gridy=0; p.add(new JLabel("User ID:"), gbc);
            gbc.gridx=1; JTextField idField=new JTextField(id,10); idField.setEditable(false); p.add(idField,gbc);
            gbc.gridx=0; gbc.gridy=1; p.add(new JLabel("Họ tên:"), gbc);
            gbc.gridx=1; JTextField nameField=new JTextField(name,12); p.add(nameField,gbc);
            gbc.gridx=0; gbc.gridy=2; p.add(new JLabel("Ngày sinh (dd/MM/yyyy):"), gbc);
            gbc.gridx=1; try {
                MaskFormatter mf=new MaskFormatter("##/##/####"); mf.setPlaceholderCharacter('_');
                JFormattedTextField dobField=new JFormattedTextField(mf); dobField.setText(dob); dobField.setColumns(10);
                p.add(dobField,gbc);
            }catch(ParseException e){ JTextField dobField=new JTextField(dob,10); p.add(dobField,gbc);}
            gbc.gridx=0; gbc.gridy=3; p.add(new JLabel("Số điện thoại:"), gbc);
            gbc.gridx=1; JTextField phoneField=new JTextField(phone,12); p.add(phoneField,gbc);
            gbc.gridx=0; gbc.gridy=4; p.add(new JLabel("Vai trò:"), gbc);
            gbc.gridx=1; JComboBox<String> roleBox=new JComboBox<>(new String[]{"Customer","Manager"}); roleBox.setSelectedItem(role); p.add(roleBox,gbc);
            gbc.gridx=0; gbc.gridy=5; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.EAST;
            JPanel btnP=new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton save=new JButton("Lưu"); save.setBackground(new Color(76,175,80)); save.setForeground(Color.WHITE);
            JButton cancel=new JButton("Hủy"); cancel.setBackground(new Color(220,50,50)); cancel.setForeground(Color.WHITE);
            btnP.add(save); btnP.add(cancel); p.add(btnP,gbc);
            setContentPane(p);
            cancel.addActionListener(e->dispose());
            save.addActionListener(e->{
                // TODO: update logic
                dispose();
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserAccountView("Admin Quản lý"));
    }
}
