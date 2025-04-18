package View.ManagerView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.text.*;
import java.util.*;

public class ManageTripView extends JFrame {
    private JPanel headerPanel, sidePanel;
    private JLabel systemLabel, userLabel;

    private JButton addTripButton;
    private JComboBox<String> routeCombo;
    private JFormattedTextField dateField;
    private JComboBox<String> statusCombo;
    private JButton filterButton;

    private JTable tripTable;
    private DefaultTableModel tableModel;
    private JPanel paginationPanel;

    public ManageTripView(String userName, ArrayList<String> routes) {
        super("Hệ thống xe buýt");
        initLookAndFeel();
        initComponents(userName, routes);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 650);
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
        } catch (Exception e) {
            // ignore
        }
    }

    private void initComponents(String userName, ArrayList<String> routes) {
        setLayout(new BorderLayout());

        // Header
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

        // Sidebar
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(33, 37, 41));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        sidePanel.setPreferredSize(new Dimension(200, 0));
        sidePanel.add(makeSideButton("Bảng điều khiển", false));
        sidePanel.add(makeSideButton("Quản lý chuyến đi", true));
        sidePanel.add(makeSideButton("Quản lý xe", false));
        sidePanel.add(makeSideButton("Báo cáo doanh thu", false));
        sidePanel.add(makeSideButton("Danh sách vé", false));
        sidePanel.add(makeSideButton("Tài khoản người dùng", false));
        sidePanel.add(makeSideButton("Tạo tài khoản", false));
        sidePanel.add(Box.createVerticalGlue());
        sidePanel.add(makeSideButton("Đăng xuất", false));
        add(sidePanel, BorderLayout.WEST);

        // Main
        JPanel main = new JPanel(new BorderLayout(20, 20));
        main.setBackground(Color.WHITE);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(main, BorderLayout.CENTER);

        // Title & Add
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Quản lý chuyến đi");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        addTripButton = new JButton("Thêm chuyến đi mới");
        addTripButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addTripButton.setBackground(new Color(76, 175, 80));
        addTripButton.setForeground(Color.WHITE);
        addTripButton.setFocusPainted(false);
        titlePanel.add(addTripButton, BorderLayout.EAST);
        main.add(titlePanel, BorderLayout.NORTH);

        // Filter container
        JPanel filterContainer = new JPanel(new GridBagLayout());
        filterContainer.setBackground(Color.WHITE);
        filterContainer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            new EmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        filterContainer.add(new JLabel("Tuyến đường:"), gbc);
        routeCombo = new JComboBox<>();
        routeCombo.addItem("Tất cả");
        for (String r : routes) routeCombo.addItem(r);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        filterContainer.add(routeCombo, gbc);

        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE;
        filterContainer.add(new JLabel("Ngày đi:"), gbc);
        try {
            MaskFormatter mf = new MaskFormatter("##/##/####");
            mf.setPlaceholderCharacter('_');
            dateField = new JFormattedTextField(mf);
        } catch (ParseException e) {
            dateField = new JFormattedTextField();
        }
        dateField.setColumns(10);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        filterContainer.add(dateField, gbc);

        gbc.gridx = 4; gbc.fill = GridBagConstraints.NONE;
        filterContainer.add(new JLabel("Trạng thái:"), gbc);
        statusCombo = new JComboBox<>(new String[]{"Tất cả", "Hoạt động", "Đã hủy"});
        gbc.gridx = 5; gbc.fill = GridBagConstraints.HORIZONTAL;
        filterContainer.add(statusCombo, gbc);

        filterButton = new JButton("Lọc");
        filterButton.setBackground(new Color(33, 150, 243));
        filterButton.setForeground(Color.WHITE);
        filterButton.setFocusPainted(false);
        gbc.gridx = 6; gbc.fill = GridBagConstraints.NONE;
        filterContainer.add(filterButton, gbc);

        // Center panel for filter + table + pagination
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(filterContainer, BorderLayout.NORTH);

        // Table
        String[] cols = {"Mã chuyến","Tuyến đường","Ngày đi","Giờ khởi hành","Xe","Ghế trống","Trạng thái","Hành động"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int row, int col) {
                return col == 7;
            }
        };
        tableModel.addRow(new Object[]{"T001","Hà Nội - Hải Phòng","15/06/2023","07:00","29A-12345","5/40","Hoạt động",null});
        tableModel.addRow(new Object[]{"T002","Hà Nội - Đà Nẵng","20/06/2023","07:00","29A-12346","12/40","Hoạt động",null});
        tableModel.addRow(new Object[]{"T003","Hà Nội - Nha Trang","25/06/2023","08:00","29A-12347","20/40","Hoạt động",null});
        tableModel.addRow(new Object[]{"T004","Hà Nội - Hải Phòng","18/06/2023","07:00","29A-12348","0/40","Đã hủy",null});
        tripTable = new JTable(tableModel);
        tripTable.setRowHeight(30);
        tripTable.getColumn("Hành động").setCellRenderer(new ButtonRenderer());
        tripTable.getColumn("Hành động").setCellEditor(new ButtonEditor());
        centerPanel.add(new JScrollPane(tripTable), BorderLayout.CENTER);

        // Pagination
        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        paginationPanel.setBackground(Color.WHITE);
        paginationPanel.add(makePageButton("1", false));
        paginationPanel.add(makePageButton("2", true));
        paginationPanel.add(makePageButton("3", true));
        paginationPanel.add(new JLabel("."));
        paginationPanel.add(new JLabel("."));
        paginationPanel.add(new JLabel("."));
        paginationPanel.add(makePageButton("10", true));
        centerPanel.add(paginationPanel, BorderLayout.SOUTH);

        main.add(centerPanel, BorderLayout.CENTER);
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

    private JButton makePageButton(String text, boolean enabled) {
        JButton btn = new JButton(text);
        btn.setEnabled(enabled);
        btn.setPreferredSize(new Dimension(40, 30));
        if (!enabled) {
            btn.setBackground(new Color(76, 175, 80));
            btn.setForeground(Color.WHITE);
        }
        return btn;
    }

    private class ButtonRenderer extends JPanel implements TableCellRenderer {
        public ButtonRenderer() { setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0)); }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int col) {
            removeAll();
            JButton edit = new JButton("Sửa");
            edit.setBackground(new Color(255, 193, 7)); edit.setForeground(Color.WHITE);
            JButton cancel = new JButton("Hủy");
            cancel.setBackground(new Color(220, 50, 50)); cancel.setForeground(Color.WHITE);
            add(edit); add(cancel);
            return this;
        }
    }

    private class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        public ButtonEditor() {
            JButton edit = new JButton("Sửa");
            edit.setBackground(new Color(255, 193, 7)); edit.setForeground(Color.WHITE);
            JButton cancel = new JButton("Hủy");
            cancel.setBackground(new Color(220, 50, 50)); cancel.setForeground(Color.WHITE);
            panel.add(edit); panel.add(cancel);
        }
        @Override public Component getTableCellEditorComponent(JTable t, Object v,
                                                               boolean isSelected, int r, int c) {
            return panel;
        }
        @Override public Object getCellEditorValue() { return null; }
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new ManageTripView(
  	    "Admin Quản lý",
  	    new ArrayList<>(Arrays.asList(
  	        "Hà Nội - Hải Phòng",
  	        "Hà Nội - Đà Nẵng",
  	        "Hà Nội - Nha Trang"
  	    ))
  	));
  }
}