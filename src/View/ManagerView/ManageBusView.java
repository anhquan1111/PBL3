//package View.ManagerView;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.*;
//import java.awt.*;
//import java.util.*;
//
//public class ManageBusView extends JFrame {
//    private JPanel headerPanel, sidePanel;
//    private JLabel systemLabel, userLabel;
//
//    private JButton addBusButton;
//    private JComboBox<String> routeFilter;
//    private JTextField brandFilter;
//    private JButton filterButton;
//
//    private JTable busTable;
//    private DefaultTableModel tableModel;
//    private JPanel paginationPanel;
//
//    public ManageBusView(String userName, ArrayList<String> routes) {
//        super("Hệ thống xe buýt");
//        initLookAndFeel();
//        initComponents(userName, routes);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setSize(1000, 650);
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
//        } catch (Exception e) {
//            // ignore
//        }
//    }
//
//    private void initComponents(String userName, ArrayList<String> routes) {
//        setLayout(new BorderLayout());
//
//        // Header
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
//        // Sidebar
//        sidePanel = new JPanel();
//        sidePanel.setBackground(new Color(33, 37, 41));
//        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
//        sidePanel.setBorder(new EmptyBorder(20, 10, 10, 10));
//        sidePanel.setPreferredSize(new Dimension(200, 0));
//        String[] menu = {"Bảng điều khiển","Quản lý chuyến đi","Quản lý xe","Báo cáo doanh thu","Danh sách vé","Tài khoản người dùng","Tạo tài khoản","Đăng xuất"};
//        for (String m : menu) {
//            boolean selected = m.equals("Quản lý xe");
//            sidePanel.add(makeSideButton(m, selected));
//        }
//        add(sidePanel, BorderLayout.WEST);
//
//        // Main
//        JPanel main = new JPanel(new BorderLayout(20, 20));
//        main.setBackground(Color.WHITE);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
//        add(main, BorderLayout.CENTER);
//
//        // Title & Add
//        JPanel titlePanel = new JPanel(new BorderLayout());
//        titlePanel.setBackground(Color.WHITE);
//        JLabel titleLabel = new JLabel("Quản lý xe");
//        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
//        titlePanel.add(titleLabel, BorderLayout.WEST);
//        addBusButton = new JButton("Thêm xe mới");
//        addBusButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        addBusButton.setBackground(new Color(76, 175, 80));
//        addBusButton.setForeground(Color.WHITE);
//        addBusButton.setFocusPainted(false);
//        titlePanel.add(addBusButton, BorderLayout.EAST);
//        main.add(titlePanel, BorderLayout.NORTH);
//
//        // Filter panel
//        JPanel filterPanel = new JPanel(new GridBagLayout());
//        filterPanel.setBackground(Color.WHITE);
//        filterPanel.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(220,220,220)),
//            new EmptyBorder(10,10,10,10)
//        ));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5,10,5,10);
//        gbc.anchor = GridBagConstraints.WEST;
//        // Route filter
//        gbc.gridx=0; gbc.gridy=0; filterPanel.add(new JLabel("Tuyến đường:"),gbc);
//        gbc.gridx=1; gbc.fill=GridBagConstraints.HORIZONTAL;
//        routeFilter = new JComboBox<>(); routeFilter.addItem("Tất cả");
//        for(String r:routes) routeFilter.addItem(r);
//        filterPanel.add(routeFilter,gbc);
//        // Brand filter
//        gbc.gridx=2; gbc.fill=GridBagConstraints.NONE; filterPanel.add(new JLabel("Hãng xe:"),gbc);
//        gbc.gridx=3; gbc.fill=GridBagConstraints.HORIZONTAL;
//        brandFilter = new JTextField(10);
//        filterPanel.add(brandFilter,gbc);
//        // Filter button
//        filterButton = new JButton("Lọc");
//        filterButton.setBackground(new Color(33,150,243)); filterButton.setForeground(Color.WHITE);
//        filterButton.setFocusPainted(false);
//        gbc.gridx=4; gbc.fill=GridBagConstraints.NONE;
//        filterPanel.add(filterButton,gbc);
//        main.add(filterPanel,BorderLayout.AFTER_LINE_ENDS);
//
//        // Table
//        String[] cols = {"Mã xe","Hãng","Tuyến đường","Sức chứa","Hành động"};
//        tableModel = new DefaultTableModel(cols,0){
//            public boolean isCellEditable(int r,int c){return c==4;}
//        };
//        // Demo rows
//        tableModel.addRow(new Object[]{"B001","Thaco","Hà Nội - Hải Phòng",40,null});
//        tableModel.addRow(new Object[]{"B002","Samco","Hà Nội - Đà Nẵng",45,null});
//        tableModel.addRow(new Object[]{"B003","Hyundai","Hà Nội - Nha Trang",50,null});
//        busTable=new JTable(tableModel);
//        busTable.setRowHeight(30);
//        busTable.getColumn("Hành động").setCellRenderer(new ActionRenderer());
//        busTable.getColumn("Hành động").setCellEditor(new ActionEditor());
//        main.add(new JScrollPane(busTable),BorderLayout.CENTER);
//
//        // Pagination
//        paginationPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
//        paginationPanel.setBackground(Color.WHITE);
//        paginationPanel.add(makePageButton("1",false));
//        paginationPanel.add(makePageButton("2",true));
//        paginationPanel.add(makePageButton("3",true));
//        paginationPanel.add(new JLabel("..."));
//        paginationPanel.add(makePageButton("10",true));
//        main.add(paginationPanel,BorderLayout.SOUTH);
//    }
//
//    private JButton makeSideButton(String text, boolean selected){
//        JButton btn=new JButton(text);
//        btn.setFont(new Font("Segoe UI",selected?Font.BOLD:Font.PLAIN,14));
//        btn.setForeground(Color.WHITE);
//        btn.setBackground(selected?new Color(76,175,80):new Color(33,37,41));
//        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));
//        btn.setFocusPainted(false);
//        return btn;
//    }
//    private JButton makePageButton(String text,boolean enabled){
//        JButton btn=new JButton(text);
//        btn.setEnabled(enabled);
//        if(!enabled){btn.setBackground(new Color(76,175,80));btn.setForeground(Color.WHITE);}        
//        return btn;
//    }
//    // Actions
//    private class ActionRenderer extends JPanel implements TableCellRenderer {
//        public ActionRenderer(){setLayout(new FlowLayout(FlowLayout.CENTER,5,0));}
//        public Component getTableCellRendererComponent(JTable t,Object v,boolean s,boolean f,int r,int c){
//            removeAll(); JButton edit=new JButton("Sửa");edit.setBackground(new Color(255,193,7));edit.setForeground(Color.WHITE);
//            JButton del=new JButton("Xóa");del.setBackground(new Color(220,50,50));del.setForeground(Color.WHITE);
//            add(edit);add(del);return this;
//        }
//    }
//    private class ActionEditor extends AbstractCellEditor implements TableCellEditor {
//        JPanel pnl=new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
//        public ActionEditor(){
//            JButton edit=new JButton("Sửa");edit.setBackground(new Color(255,193,7));edit.setForeground(Color.WHITE);
//            JButton del=new JButton("Xóa");del.setBackground(new Color(220,50,50));del.setForeground(Color.WHITE);
//            pnl.add(edit);pnl.add(del);
//        }
//        public Component getTableCellEditorComponent(JTable t,Object v,boolean s,int r,int c){return pnl;}
//        public Object getCellEditorValue(){return null;}
//    }
//
////    public static void main(String[] args) {
////        SwingUtilities.invokeLater(() -> 
////            new ManageBusView(
////                "Admin Quản lý",
////                Arrays.asList("Hà Nội - Hải Phòng","Hà Nội - Đà Nẵng","Hà Nội - Nha Trang")
////            )
////        );
////    }
//    public static void main(String[] args) {
//    	SwingUtilities.invokeLater(() -> new ManageBusView(
//  	    "Admin Quản lý",
//  	    new ArrayList<>(Arrays.asList(
//  	        "Hà Nội - Hải Phòng",
//  	        "Hà Nội - Đà Nẵng",
//  	        "Hà Nội - Nha Trang"
//  	    ))
//  	));
//  }
//}


package View.ManagerView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class ManageBusView extends JFrame {
    private JPanel headerPanel, sidePanel;
    private JLabel systemLabel, userLabel;

    private JButton addBusButton;
    private JComboBox<String> routeFilter;
    private JTextField brandFilter;
    private JButton filterButton;

    private JTable busTable;
    private DefaultTableModel tableModel;
    private JPanel paginationPanel;

    public ManageBusView(String userName, ArrayList<String> routes) {
        super("Hệ thống xe buýt");
        initLookAndFeel();
        initComponents(userName, routes);
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
        String[] menu = {"Bảng điều khiển","Quản lý chuyến đi","Quản lý xe","Báo cáo doanh thu","Danh sách vé","Tài khoản người dùng","Tạo tài khoản","Đăng xuất"};
        for (String m : menu) {
            boolean sel = m.equals("Quản lý xe");
            sidePanel.add(makeSideButton(m, sel));
        }
        add(sidePanel, BorderLayout.WEST);

        // Main area split: table + right filter
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBackground(Color.WHITE);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(main, BorderLayout.CENTER);

        // Top panel: title & add
        JPanel top = new JPanel(new BorderLayout()); top.setBackground(Color.WHITE);
        JLabel title = new JLabel("Quản lý xe");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22)); top.add(title, BorderLayout.WEST);
        addBusButton = new JButton("Thêm xe mới");
        addBusButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addBusButton.setBackground(new Color(76,175,80)); addBusButton.setForeground(Color.WHITE);
        addBusButton.setFocusPainted(false);
        top.add(addBusButton, BorderLayout.EAST);
        main.add(top, BorderLayout.NORTH);

        // Center: table + pagination
        JPanel center = new JPanel(new BorderLayout(5,5)); center.setBackground(Color.WHITE);
        String[] cols = {"Mã xe","Hãng","Tuyến đường","Sức chứa","Hành động"};
        tableModel = new DefaultTableModel(cols,0) {
            @Override public boolean isCellEditable(int r,int c){ return c==4; }
        };
        tableModel.addRow(new Object[]{"B001","Thaco","Hà Nội - Hải Phòng",40,null});
        tableModel.addRow(new Object[]{"B002","Samco","Hà Nội - Đà Nẵng",45,null});
        tableModel.addRow(new Object[]{"B003","Hyundai","Hà Nội - Nha Trang",50,null});
        busTable = new JTable(tableModel); busTable.setRowHeight(30);
        busTable.getColumn("Hành động").setCellRenderer(new ActionRenderer());
        busTable.getColumn("Hành động").setCellEditor(new ActionEditor());
        center.add(new JScrollPane(busTable), BorderLayout.CENTER);
        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0)); paginationPanel.setBackground(Color.WHITE);
        paginationPanel.add(makePageButton("1",false)); paginationPanel.add(makePageButton("2",true));
        paginationPanel.add(makePageButton("3",true)); paginationPanel.add(new JLabel("...")); paginationPanel.add(makePageButton("10",true));
        center.add(paginationPanel, BorderLayout.SOUTH);
        main.add(center, BorderLayout.CENTER);

        // Right: filter panel
        JPanel filterPanel = new JPanel(new GridBagLayout()); filterPanel.setBackground(Color.WHITE);
        filterPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)), new EmptyBorder(10,10,10,10)));
        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets=new Insets(5,5,5,5);
        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridx=0; gbc.gridy=0; filterPanel.add(new JLabel("Tuyến đường:"),gbc);
        gbc.gridx=1; gbc.fill=GridBagConstraints.HORIZONTAL;
        routeFilter = new JComboBox<>(); routeFilter.addItem("Tất cả");
        for(String r:routes) routeFilter.addItem(r);
        filterPanel.add(routeFilter,gbc);
        gbc.gridx=0; gbc.gridy=1; gbc.fill=GridBagConstraints.NONE; filterPanel.add(new JLabel("Hãng xe:"),gbc);
        gbc.gridx=1; gbc.fill=GridBagConstraints.HORIZONTAL;
        brandFilter = new JTextField(12); filterPanel.add(brandFilter,gbc);
        gbc.gridx=0; gbc.gridy=2; gbc.gridwidth=2; gbc.fill=GridBagConstraints.NONE;
        filterButton = new JButton("Lọc"); filterButton.setBackground(new Color(33,150,243));
        filterButton.setForeground(Color.WHITE); filterButton.setFocusPainted(false);
        filterPanel.add(filterButton,gbc);
        main.add(filterPanel, BorderLayout.EAST);

        // Action: open AddBus dialog
        addBusButton.addActionListener(e -> new AddBusView(this, routes));
    }

    private JButton makeSideButton(String text, boolean selected){
        JButton btn=new JButton(text);
        btn.setFont(new Font("Segoe UI", selected?Font.BOLD:Font.PLAIN,14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(selected?new Color(76,175,80):new Color(33,37,41));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE,40)); btn.setFocusPainted(false);
        return btn;
    }
    private JButton makePageButton(String text, boolean enabled){
        JButton btn=new JButton(text);
        btn.setEnabled(enabled);
        if(!enabled){btn.setBackground(new Color(76,175,80));btn.setForeground(Color.WHITE);}        
        return btn;
    }

    // Renderer & Editor for actions
    private class ActionRenderer extends JPanel implements TableCellRenderer {
        public ActionRenderer(){setLayout(new FlowLayout(FlowLayout.CENTER,5,0));}
        public Component getTableCellRendererComponent(JTable t,Object v,boolean s,boolean f,int r,int c){
            removeAll();
            JButton edit=new JButton("Sửa"); edit.setBackground(new Color(255,193,7)); edit.setForeground(Color.WHITE);
            JButton del =new JButton("Xóa"); del.setBackground(new Color(220,50,50)); del.setForeground(Color.WHITE);
            add(edit); add(del); return this;
        }
    }
    private class ActionEditor extends AbstractCellEditor implements TableCellEditor {
        JPanel pnl=new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
        public ActionEditor(){
            JButton edit=new JButton("Sửa"); edit.setBackground(new Color(255,193,7)); edit.setForeground(Color.WHITE);
            JButton del =new JButton("Xóa"); del.setBackground(new Color(220,50,50)); del.setForeground(Color.WHITE);
            pnl.add(edit); pnl.add(del);
        }
        public Component getTableCellEditorComponent(JTable t,Object v,boolean s,int r,int c){ return pnl; }
        public Object getCellEditorValue(){ return null; }
    }

    // Sub-view for adding new bus
    private static class AddBusView extends JDialog {
        public AddBusView(JFrame parent, ArrayList<String> routes) {
            super(parent, "Thêm xe mới", true);
            initComponents(routes);
            pack(); setLocationRelativeTo(parent); setVisible(true);
        }
        private void initComponents(ArrayList<String> routes) {
            JPanel p=new JPanel(new GridBagLayout()); p.setBorder(new EmptyBorder(15,15,15,15));
            GridBagConstraints gbc=new GridBagConstraints(); gbc.insets=new Insets(5,5,5,5); gbc.anchor=GridBagConstraints.WEST;
            gbc.gridx=0; gbc.gridy=0; p.add(new JLabel("Mã xe:"),gbc);
            gbc.gridx=1; JTextField idField=new JTextField(10); p.add(idField,gbc);
            gbc.gridx=0; gbc.gridy=1; p.add(new JLabel("Hãng xe:"),gbc);
            gbc.gridx=1; JTextField brandField=new JTextField(12); p.add(brandField,gbc);
            gbc.gridx=0; gbc.gridy=2; p.add(new JLabel("Tuyến đường:"),gbc);
            gbc.gridx=1; JComboBox<String> routeBox=new JComboBox<>(); for(String r:routes) routeBox.addItem(r); p.add(routeBox,gbc);
            gbc.gridx=0; gbc.gridy=3; p.add(new JLabel("Sức chứa:"),gbc);
            gbc.gridx=1; JSpinner capSpinner=new JSpinner(new SpinnerNumberModel(40,1,100,1)); p.add(capSpinner,gbc);
            // Buttons
            JPanel btnPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton save=new JButton("Lưu"); save.setBackground(new Color(76,175,80)); save.setForeground(Color.WHITE);
            JButton cancel=new JButton("Hủy"); cancel.setBackground(new Color(220,50,50)); cancel.setForeground(Color.WHITE);
            btnPanel.add(save); btnPanel.add(cancel);
            gbc.gridx=0; gbc.gridy=4; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.EAST; p.add(btnPanel,gbc);
            setContentPane(p);
            cancel.addActionListener(e -> dispose());
            save.addActionListener(e -> {
                // TODO: xử lý lưu
                dispose();
            });
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new ManageBusView(
//            "Admin Quản lý",
//            Arrays.asList("Hà Nội - Hải Phòng","Hà Nội - Đà Nẵng","Hà Nội - Nha Trang")
//        ));
//    }
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new ManageBusView(
  	    "Admin Quản lý",
  	    new ArrayList<>(Arrays.asList(
  	        "Hà Nội - Hải Phòng",
  	        "Hà Nội - Đà Nẵng",
  	        "Hà Nội - Nha Trang"
  	    ))
  	));
  }
}
