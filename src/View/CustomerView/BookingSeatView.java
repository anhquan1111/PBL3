package View.CustomerView;

//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.*;
//import java.util.List;
//
//public class BookingSeatView extends JFrame {
//    // Thông tin chuyến
//    private JLabel routeLabel;
//    private JLabel dateLabel;
//    private JLabel busLabel;
//
//    // Panel chứa ghế
//    private JPanel seatGridPanel;
//    private JScrollPane seatScrollPane;
//
//    // Nút xác nhận và quay lại
//    private JButton confirmButton;
//    private JButton backButton;
//
//    // Lưu giữ trạng thái ghế đã chọn
//    private Set<String> selectedSeats = new LinkedHashSet<>();
//
//    public BookingSeatView(String route, String date, String busNumber, List<String> reservedSeats) {
//        super("Chọn ghế & Đặt vé");  
//        initLookAndFeel();
//        initComponents(route, date, busNumber, reservedSeats);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(800, 600);
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
//    private void initComponents(String route, String date, String busNumber, List<String> reservedSeats) {
//        // Root panel
//        JPanel root = new JPanel();
//        root.setBackground(Color.WHITE);
//        root.setLayout(new BorderLayout(10, 10));
//        root.setBorder(new EmptyBorder(15, 20, 15, 20));
//        getContentPane().add(root);
//
//        // ---- Header thông tin chuyến ----
//        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
//        infoPanel.setBackground(Color.WHITE);
//        routeLabel = new JLabel("Tuyến: " + route);
//        dateLabel  = new JLabel("Ngày: " + date);
//        busLabel   = new JLabel("Xe số: " + busNumber);
//
//        for (JLabel lbl : Arrays.asList(routeLabel, dateLabel, busLabel)) {
//            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//            infoPanel.add(lbl);
//        }
//        root.add(infoPanel, BorderLayout.NORTH);
//
//        // ---- Grid ghế ngồi ----
//        seatGridPanel = new JPanel(new GridLayout(8, 4, 10, 10)); // 8 hàng x 4 cột
//        seatGridPanel.setBackground(Color.WHITE);
//        seatGridPanel.setBorder(new EmptyBorder(10,10,10,10));
//
//        // Tạo các ghế A1..H4
//        char row = 'A';
//        for (int i = 0; i < 8; i++, row++) {
//            for (int col = 1; col <= 4; col++) {
//                String seatId = row + String.valueOf(col);
//                JToggleButton seatBtn = new JToggleButton(seatId);
//                seatBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
//                seatBtn.setFocusPainted(false);
//                seatBtn.setBackground(Color.LIGHT_GRAY);
//                seatBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//
//                // Nếu ghế đã đặt trước đó -> disabled đỏ
//                if (reservedSeats.contains(seatId)) {
//                    seatBtn.setEnabled(false);
//                    seatBtn.setBackground(new Color(220, 50, 50));
//                }
//
//                // Thêm listener để quản lý chọn ghế
//                seatBtn.addItemListener(e -> {
//                    if (seatBtn.isSelected()) {
//                        selectedSeats.add(seatId);
//                        seatBtn.setBackground(new Color(76, 175, 80)); // xanh khi chọn
//                    } else {
//                        selectedSeats.remove(seatId);
//                        seatBtn.setBackground(Color.LIGHT_GRAY);
//                    }
//                });
//
//                seatGridPanel.add(seatBtn);
//            }
//        }
//
//        seatScrollPane = new JScrollPane(seatGridPanel,
//            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
//        );
//        root.add(seatScrollPane, BorderLayout.CENTER);
//
//        // ---- Footer nút hành động ----
//        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
//        actionPanel.setBackground(Color.WHITE);
//
//        backButton = new JButton("← Quay lại");
//        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//
//        confirmButton = new JButton("Xác nhận đặt vé");
//        confirmButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
//        confirmButton.setBackground(new Color(33, 150, 243));
//        confirmButton.setForeground(Color.WHITE);
//        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        confirmButton.setFocusPainted(false);
//        confirmButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
//
//        actionPanel.add(backButton);
//        actionPanel.add(confirmButton);
//        root.add(actionPanel, BorderLayout.SOUTH);
//    }
//
//    // Public getters cho Controller
//    public Set<String> getSelectedSeats() {
//        return Collections.unmodifiableSet(selectedSeats);
//    }
//    public JButton getConfirmButton() {
//        return confirmButton;
//    }
//    public JButton getBackButton() {
//        return backButton;
//    }
//
//    // Demo khởi chạy
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            // Giả lập chuyến và ghế đã đặt
//            List<String> reserved = Arrays.asList("A1","A2","B3","C1");
//            new BookingSeatView("Hà Nội - Đà Nẵng", "20/06/2023", "XN-1234", reserved);
//        });
//    }
//}


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BookingSeatView extends JFrame {
    // Header
    private JPanel headerPanel;
    private JLabel systemLabel, userLabel;

    // Sidebar
    private JPanel sidePanel;
    private JButton dashBtn, bookBtn, historyBtn, paymentBtn, logoutBtn;

    // Main content
    private JLabel titleLabel;
    private JLabel routeLabel, dateLabel, departTimeLabel, durationLabel;
    private JLabel plateLabel, typeLabel, priceLabel;

    private JPanel seatGridPanel;
    private Map<String, JToggleButton> seatButtons = new LinkedHashMap<>();

    private JButton cancelButton, continueButton;

    public BookingSeatView(
            String userName,
            String route, String date, String departTime, String duration,
            String plate, String type, String price,
            Set<String> reservedSeats
    ) {
        super("Hệ thống xe buýt");
        initLookAndFeel();
        initComponents(userName, route, date, departTime, duration, plate, type, price, reservedSeats);
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

    private void initComponents(
            String userName,
            String route, String date, String departTime, String duration,
            String plate, String type, String price,
            Set<String> reservedSeats
    ) {
        setLayout(new BorderLayout());

        // ===== HEADER =====
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(76, 175, 80)); // xanh lá
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
        sidePanel.setBackground(new Color(33, 37, 41)); // xám đậm
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        sidePanel.setPreferredSize(new Dimension(180, 0));

        dashBtn    = makeSideButton("Bảng điều khiển", false);
        bookBtn    = makeSideButton("Đặt vé", true);
        historyBtn = makeSideButton("Lịch sử đặt vé", false);
        paymentBtn = makeSideButton("Thanh toán", false);
        logoutBtn  = makeSideButton("Đăng xuất", false);

        sidePanel.add(dashBtn);
        sidePanel.add(bookBtn);
        sidePanel.add(historyBtn);
        sidePanel.add(paymentBtn);
        sidePanel.add(Box.createVerticalGlue());
        sidePanel.add(logoutBtn);

        add(sidePanel, BorderLayout.WEST);

        // ===== MAIN CONTENT =====
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        root.setBackground(Color.WHITE);
        add(root, BorderLayout.CENTER);

        // Tiêu đề
        titleLabel = new JLabel("Đặt chỗ ngồi");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        root.add(titleLabel, BorderLayout.NORTH);

        // Nội dung chính: 2 cột
        JPanel content = new JPanel(new GridLayout(1, 2, 20, 0));
        content.setBackground(Color.WHITE);
        root.add(content, BorderLayout.CENTER);

        // ----- THÔNG TIN CHUYẾN ĐI -----
        JPanel infoPanel = new JPanel(new GridLayout(7, 2, 8, 8));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)),
            new EmptyBorder(15,15,15,15)
        ));

        infoPanel.add(new JLabel("Tuyến đường:"));   routeLabel       = new JLabel(route);       infoPanel.add(routeLabel);
        infoPanel.add(new JLabel("Ngày đi:"));       dateLabel        = new JLabel(date);        infoPanel.add(dateLabel);
        infoPanel.add(new JLabel("Giờ khởi hành:")); departTimeLabel  = new JLabel(departTime);  infoPanel.add(departTimeLabel);
        infoPanel.add(new JLabel("Thời gian:"));     durationLabel    = new JLabel(duration);    infoPanel.add(durationLabel);
        infoPanel.add(new JLabel("Biển số xe:"));    plateLabel       = new JLabel(plate);       infoPanel.add(plateLabel);
        infoPanel.add(new JLabel("Loại xe:"));       typeLabel        = new JLabel(type);        infoPanel.add(typeLabel);
        infoPanel.add(new JLabel("Giá vé:"));        priceLabel       = new JLabel(price);       infoPanel.add(priceLabel);

        content.add(infoPanel);

        // ----- CHỌN CHỖ NGỒI -----
        JPanel seatPanel = new JPanel(new BorderLayout(10,10));
        seatPanel.setBackground(Color.WHITE);
        seatPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)),
            new EmptyBorder(15,15,15,15)
        ));

        // Hướng dẫn
        JLabel instruct = new JLabel("<html>Vui lòng chọn chỗ ngồi mong muốn<br/>(Tối đa 5 ghế)</html>");
        instruct.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        seatPanel.add(instruct, BorderLayout.NORTH);

        // Driver label centered above grid
        JLabel driverLabel = new JLabel("Tài xế", SwingConstants.CENTER);
        driverLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        driverLabel.setOpaque(true);
        driverLabel.setBackground(new Color(238,238,238));
        driverLabel.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        JPanel driverWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        driverWrapper.setBackground(Color.WHITE);
        driverWrapper.add(driverLabel);
        seatPanel.add(driverWrapper, BorderLayout.WEST);

        // Seat grid 4x4
        seatGridPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        seatGridPanel.setBackground(Color.WHITE);
        char row = 'A';
        for (int i = 0; i < 4; i++, row++) {
            for (int c = 1; c <= 4; c++) {
                String id = row + String.valueOf(c);
                JToggleButton btn = new JToggleButton(id);
                btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
                btn.setBackground(Color.LIGHT_GRAY);
                btn.setFocusPainted(false);
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                if (reservedSeats.contains(id)) {
                    btn.setEnabled(false);
                    btn.setBackground(new Color(220, 50, 50));
                }

                btn.addItemListener(e -> {
                    if (btn.isSelected()) {
                        btn.setBackground(new Color(76, 175, 80));
                    } else {
                        btn.setBackground(Color.LIGHT_GRAY);
                    }
                });

                seatButtons.put(id, btn);
                seatGridPanel.add(btn);
            }
        }
        seatPanel.add(seatGridPanel, BorderLayout.CENTER);

        // Legend
        JPanel legend = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        legend.setBackground(Color.WHITE);
        legend.add(makeLegendBox(Color.LIGHT_GRAY, "Còn trống"));
        legend.add(makeLegendBox(new Color(76, 175, 80), "Đã chọn"));
        legend.add(makeLegendBox(new Color(220, 50, 50), "Đã đặt"));
        seatPanel.add(legend, BorderLayout.SOUTH);

        content.add(seatPanel);

        // ===== FOOTER BUTTONS =====
        JPanel action = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        action.setBackground(Color.WHITE);

        cancelButton = new JButton("Hủy");
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setBackground(new Color(220, 50, 50));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        continueButton = new JButton("Tiếp tục");
        continueButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        continueButton.setBackground(new Color(33, 150, 243));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFocusPainted(false);
        continueButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        action.add(cancelButton);
        action.add(continueButton);
        root.add(action, BorderLayout.SOUTH);
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
        return btn;
    }

    private JPanel makeLegendBox(Color color, String text) {
        JPanel box = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        box.setBackground(Color.WHITE);
        JLabel c = new JLabel("  ");
        c.setOpaque(true);
        c.setBackground(color);
        c.setPreferredSize(new Dimension(16, 16));
        JLabel t = new JLabel(text);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        box.add(c);
        box.add(t);
        return box;
    }

    // Getters for controller
    public Set<String> getSelectedSeats() {
        Set<String> sel = new LinkedHashSet<>();
        seatButtons.forEach((id, b) -> {
            if (b.isSelected() && b.isEnabled()) sel.add(id);
        });
        return sel;
    }
    public JButton getCancelButton() { return cancelButton; }
    public JButton getContinueButton() { return continueButton; }

    // Demo
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Set<String> reserved = new HashSet<>(Arrays.asList("B1","B3","C4"));
            new BookingSeatView(
                "Nguyễn Văn A",
                "Hà Nội - Đà Nẵng", "20/06/2023", "07:00", "10 giờ",
                "29A-12345", "Giường nằm 40 chỗ", "350,000 VND/ghế",
                reserved
            );
        });
    }
}
