//package View.ManagerView;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.util.*;
//
//public class ManagerDashboardView extends JFrame {
//    // Header and sidebar components
//    private JPanel headerPanel;
//    private JLabel systemLabel, userLabel;
//    private JPanel sidePanel;
//
//    // Main panels
//    private JPanel cardsPanel, quickActionPanel, activityPanel;
//
//    public ManagerDashboardView(String userName) {
//        super("Hệ thống xe buýt");
//        initLookAndFeel();
//        initComponents(userName);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(1100, 700);
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
//        // Menu items
//        sidePanel.add(makeSideButton("Bảng điều khiển", true));
//        sidePanel.add(makeSideButton("Quản lý chuyến đi", false));
//        sidePanel.add(makeSideButton("Quản lý xe", false));
//        sidePanel.add(makeSideButton("Báo cáo doanh thu", false));
//        sidePanel.add(makeSideButton("Danh sách vé", false));
//        sidePanel.add(makeSideButton("Tài khoản người dùng", false));
//        sidePanel.add(makeSideButton("Tạo tài khoản", false));
//        sidePanel.add(Box.createVerticalGlue());
//        sidePanel.add(makeSideButton("Đăng xuất", false));
//        add(sidePanel, BorderLayout.WEST);
//
//        // ===== MAIN CONTENT =====
//        JPanel main = new JPanel(new BorderLayout(20, 20));
//        main.setBackground(Color.WHITE);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
//        add(main, BorderLayout.CENTER);
//
//        // --- Cards panel ---
//        cardsPanel = new JPanel(new GridLayout(1, 4, 20, 0));
//        cardsPanel.setBackground(Color.WHITE);
//        cardsPanel.add(makeStatCard("Tổng số chuyến đi", "42"));
//        cardsPanel.add(makeStatCard("Tổng số xe", "15"));
//        cardsPanel.add(makeStatCard("Tổng số vé bán", "256"));
//        cardsPanel.add(makeStatCard("Tổng doanh thu", "85,200,000 VND"));
//        main.add(cardsPanel, BorderLayout.NORTH);
//
//        // --- Quick action panel ---
//        quickActionPanel = new JPanel(new GridLayout(1, 4, 20, 0));
//        quickActionPanel.setBackground(Color.WHITE);
//        quickActionPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
//        quickActionPanel.add(makeActionButton("Thêm chuyến đi mới"));
//        quickActionPanel.add(makeActionButton("Thêm xe mới"));
//        quickActionPanel.add(makeActionButton("Tạo tài khoản"));
//        quickActionPanel.add(makeActionButton("Xem báo cáo"));
//        main.add(quickActionPanel, BorderLayout.CENTER);
//
//        // --- Recent activities ---
//        JPanel recentWrapper = new JPanel(new BorderLayout(0, 10));
//        recentWrapper.setBackground(Color.WHITE);
//        JLabel recentLabel = new JLabel("Hoạt động gần đây");
//        recentLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
//        recentWrapper.add(recentLabel, BorderLayout.NORTH);
//
//        activityPanel = new JPanel();
//        activityPanel.setBackground(Color.WHITE);
//        activityPanel.setLayout(new BoxLayout(activityPanel, BoxLayout.Y_AXIS));
//
//        // Sample activities
//        addActivity("Đã thêm chuyến đi mới Hà Nội - Hải Phòng", "10 phút trước");
//        addActivity("Nguyễn Văn B đã đặt vé #B12348", "25 phút trước");
//
//        JScrollPane scroll = new JScrollPane(activityPanel,
//            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scroll.setBorder(null);
//        recentWrapper.add(scroll, BorderLayout.CENTER);
//
//        main.add(recentWrapper, BorderLayout.SOUTH);
//    }
//
//    private JButton makeSideButton(String text, boolean selected) {
//        JButton btn = new JButton(text);
//        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
//        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
//        btn.setFont(new Font("Segoe UI", selected ? Font.BOLD : Font.PLAIN, 14));
//        btn.setForeground(Color.WHITE);
//        btn.setBackground(new Color(33, 37, 41));
//        btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
//        btn.setFocusPainted(false);
//        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        return btn;
//    }
//
//    private JPanel makeStatCard(String title, String value) {
//        JPanel card = new JPanel();
//        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
//        card.setBackground(Color.WHITE);
//        card.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(220,220,220)),
//            new EmptyBorder(15, 15, 15, 15)
//        ));
//        JLabel t = new JLabel(title);
//        t.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        JLabel v = new JLabel(value);
//        v.setFont(new Font("Segoe UI", Font.BOLD, 28));
//        v.setForeground(new Color(76, 175, 80));
//        card.add(t);
//        card.add(Box.createRigidArea(new Dimension(0, 10)));
//        card.add(v);
//        return card;
//    }
//
//    private JButton makeActionButton(String text) {
//        JButton btn = new JButton(text);
//        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//        btn.setBackground(new Color(76, 175, 80));
//        btn.setForeground(Color.WHITE);
//        btn.setFocusPainted(false);
//        return btn;
//    }
//
//    private void addActivity(String desc, String time) {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(new EmptyBorder(8, 8, 8, 8));
//        JLabel icon = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
//        panel.add(icon, BorderLayout.WEST);
//        JPanel textPanel = new JPanel();
//        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
//        textPanel.setBackground(Color.WHITE);
//        JLabel d = new JLabel(desc);
//        d.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        JLabel t = new JLabel(time);
//        t.setFont(new Font("Segoe UI", Font.ITALIC, 12));
//        t.setForeground(Color.GRAY);
//        textPanel.add(d);
//        textPanel.add(t);
//        panel.add(textPanel, BorderLayout.CENTER);
//        activityPanel.add(panel);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new ManagerDashboardView("Admin Quản lý"));
//    }
//}

package View.ManagerView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ManagerDashboardView extends JFrame {
    private JPanel headerPanel, sidePanel;
    private JLabel systemLabel, userLabel;
    private JPanel cardsPanel, quickActionPanel, activityPanel;

    public ManagerDashboardView(String userName) {
        super("Hệ thống xe buýt");
        initLookAndFeel();
        initComponents(userName);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
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
        sidePanel.add(makeSideButton("Bảng điều khiển", true));
        sidePanel.add(makeSideButton("Quản lý chuyến đi", false));
        sidePanel.add(makeSideButton("Quản lý xe", false));
//        sidePanel.add(makeSideButton("Báo cáo doanh thu", false));
        sidePanel.add(makeSideButton("Danh sách vé", false));
        sidePanel.add(makeSideButton("Tài khoản người dùng", false));
        sidePanel.add(makeSideButton("Tạo tài khoản", false));
        sidePanel.add(Box.createVerticalGlue());
        sidePanel.add(makeSideButton("Đăng xuất", false));
        add(sidePanel, BorderLayout.WEST);

        // Main Content
        JPanel main = new JPanel(new BorderLayout(20, 20));
        main.setBackground(Color.WHITE);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(main, BorderLayout.CENTER);

        // Cards panel
        cardsPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.add(makeStatCard("Tổng số chuyến đi", "42"));
        cardsPanel.add(makeStatCard("Tổng số xe", "15"));
        cardsPanel.add(makeStatCard("Tổng số vé bán", "256"));
        cardsPanel.add(makeStatCard("Tổng doanh thu", "85,200,000 VND"));
        main.add(cardsPanel, BorderLayout.NORTH);

        // Quick action: 2x2 grid like sample
        quickActionPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        quickActionPanel.setBackground(Color.WHITE);
        quickActionPanel.setBorder(BorderFactory.createTitledBorder("Hành động nhanh"));
        quickActionPanel.add(makeBigActionButton("Thêm chuyến đi mới"));
        quickActionPanel.add(makeBigActionButton("Thêm xe mới"));
        quickActionPanel.add(makeBigActionButton("Tạo tài khoản"));
        quickActionPanel.add(makeBigActionButton("Xem báo cáo"));
        main.add(quickActionPanel, BorderLayout.CENTER);

        // Recent activities
        JPanel recentWrapper = new JPanel(new BorderLayout(0, 10));
        recentWrapper.setBackground(Color.WHITE);
        recentWrapper.setBorder(BorderFactory.createTitledBorder("Hoạt động gần đây"));
        activityPanel = new JPanel();
        activityPanel.setBackground(Color.WHITE);
        activityPanel.setLayout(new BoxLayout(activityPanel, BoxLayout.Y_AXIS));
        addActivity("Đã thêm chuyến đi mới Hà Nội - Hải Phòng", "10 phút trước");
        addActivity("Nguyễn Văn B đã đặt vé #B12348", "25 phút trước");
        JScrollPane scroll = new JScrollPane(activityPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        recentWrapper.add(scroll, BorderLayout.CENTER);
        main.add(recentWrapper, BorderLayout.SOUTH);
    }

    private JButton makeSideButton(String text, boolean selected) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFont(new Font("Segoe UI", selected ? Font.BOLD : Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(33, 37, 41));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel makeStatCard(String title, String value) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)),
            new EmptyBorder(15, 15, 15, 15)
        ));
        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLabel v = new JLabel(value);
        v.setFont(new Font("Segoe UI", Font.BOLD, 28));
        v.setForeground(new Color(76, 175, 80));
        card.add(t);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(v);
        return card;
    }

    private JButton makeBigActionButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(new Color(76, 175, 80));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        btn.setFocusPainted(false);
        return btn;
    }

    private void addActivity(String desc, String time) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(8, 8, 8, 8));
        JLabel icon = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
        panel.add(icon, BorderLayout.WEST);
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);
        JLabel d = new JLabel(desc);
        d.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLabel t = new JLabel(time);
        t.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        t.setForeground(Color.GRAY);
        textPanel.add(d);
        textPanel.add(t);
        panel.add(textPanel, BorderLayout.CENTER);
        activityPanel.add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManagerDashboardView("Admin Quản lý"));
    }
}
