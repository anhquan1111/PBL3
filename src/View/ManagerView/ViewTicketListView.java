package View.ManagerView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class ViewTicketListView extends JFrame {
    // Header & Sidebar
    private JPanel headerPanel, sidePanel;
    private JLabel systemLabel, userLabel;

    // Filter
    private JComboBox<String> tripFilter;
    private JTextField customerFilter;
    private JButton filterButton;

    // Table & pagination
    private JTable ticketTable;
    private DefaultTableModel tableModel;
    private JPanel paginationPanel;

    // Constructor
    public ViewTicketListView(String userName, ArrayList<String> tripIDs) {
        super("Hệ thống xe buýt");
        initLookAndFeel();
        initComponents(userName, tripIDs);
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
        } catch (Exception ignored) {}
    }

    private void initComponents(String userName, ArrayList<String> tripIDs) {
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
        String[] menu = {"Bảng điều khiển","Quản lý chuyến đi","Quản lý xe","Báo cáo doanh thu","Danh sách vé","Tài khoản người dùng","Tạo tài khoản","Đăng xuất"};
        for (String m : menu) {
            boolean sel = m.equals("Danh sách vé");
            sidePanel.add(makeSideButton(m, sel));
        }
        add(sidePanel, BorderLayout.WEST);

        // ===== MAIN CONTENT =====
        JPanel main = new JPanel(new BorderLayout(20,20));
        main.setBackground(Color.WHITE);
        main.setBorder(new EmptyBorder(20,20,20,20));
        add(main, BorderLayout.CENTER);

        // Title
        JPanel titlePanel = new JPanel(new BorderLayout()); titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Danh sách vé");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        main.add(titlePanel, BorderLayout.NORTH);

        // Center: filter + table + pagination
        JPanel center = new JPanel(new BorderLayout(10,10)); center.setBackground(Color.WHITE);

        // Filter panel on top
        JPanel filter = new JPanel(new FlowLayout(FlowLayout.LEFT,15,5)); filter.setBackground(Color.WHITE);
        filter.add(new JLabel("Mã chuyến:"));
        tripFilter = new JComboBox<>(); tripFilter.addItem("Tất cả");
        for(String t: tripIDs) tripFilter.addItem(t);
        filter.add(tripFilter);
        filter.add(new JLabel("Khách hàng:"));
        customerFilter = new JTextField(12); filter.add(customerFilter);
        filterButton = new JButton("Lọc");
        filterButton.setBackground(new Color(33,150,243)); filterButton.setForeground(Color.WHITE); filterButton.setFocusPainted(false);
        filter.add(filterButton);
        center.add(filter, BorderLayout.NORTH);

        // Table
        String[] cols = {"Mã vé","Mã chuyến","Ghế số","Khách hàng","Thanh toán","Hành động"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r,int c){ return c==5; }
        };
        // Sample data
        tableModel.addRow(new Object[]{"TK001","T001","A3","CUST01","Completed",null});
        tableModel.addRow(new Object[]{"TK002","T002","B2","CUST02","Pending", null});
        tableModel.addRow(new Object[]{"TK003","T001","C1","CUST03","Completed",null});
        ticketTable = new JTable(tableModel); ticketTable.setRowHeight(30);
        ticketTable.getColumn("Hành động").setCellRenderer(new ActionRenderer());
        ticketTable.getColumn("Hành động").setCellEditor(new ActionEditor());
        center.add(new JScrollPane(ticketTable), BorderLayout.CENTER);

        // Pagination
        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0)); paginationPanel.setBackground(Color.WHITE);
        paginationPanel.add(makePageButton("1",false)); paginationPanel.add(makePageButton("2",true));
        paginationPanel.add(makePageButton("3",true)); paginationPanel.add(new JLabel("...")); paginationPanel.add(makePageButton("10",true));
        center.add(paginationPanel, BorderLayout.SOUTH);
        main.add(center, BorderLayout.CENTER);

        // TODO: event listeners for filter, actions
    }

    private JButton makeSideButton(String text, boolean selected){
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", selected?Font.BOLD:Font.PLAIN,14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(selected?new Color(76,175,80):new Color(33,37,41));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE,40)); btn.setFocusPainted(false);
        return btn;
    }

    private JButton makePageButton(String text, boolean enabled){
        JButton btn = new JButton(text); btn.setEnabled(enabled);
        if(!enabled){ btn.setBackground(new Color(76,175,80)); btn.setForeground(Color.WHITE);} return btn;
    }

    // Action buttons in table
    private class ActionRenderer extends JPanel implements TableCellRenderer {
        public ActionRenderer(){setLayout(new FlowLayout(FlowLayout.CENTER,5,0));}
        @Override
        public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c){
            removeAll();
            JButton view = new JButton("Xem"); view.setBackground(new Color(33,150,243)); view.setForeground(Color.WHITE);
            JButton cancel = new JButton("Hủy"); cancel.setBackground(new Color(220,50,50)); cancel.setForeground(Color.WHITE);
            add(view); add(cancel); return this;
        }
    }
    private class ActionEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
        public ActionEditor(){
            JButton view = new JButton("Xem"); view.setBackground(new Color(33,150,243)); view.setForeground(Color.WHITE);
            JButton cancel = new JButton("Hủy"); cancel.setBackground(new Color(220,50,50)); cancel.setForeground(Color.WHITE);
            panel.add(view); panel.add(cancel);
        }
        @Override public Component getTableCellEditorComponent(JTable t, Object v, boolean s, int r, int c){ return panel; }
        @Override public Object getCellEditorValue(){ return null; }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewTicketListView(
            "Admin Quản lý",
            new ArrayList<> (Arrays.asList("T001","T002","T003"))
        ));
    }
}
