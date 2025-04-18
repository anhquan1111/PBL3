//package View.CustomerView;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.text.MaskFormatter;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.text.ParseException;
//import java.util.Collections;
//import java.util.List;
//
//public class PaymentView extends JFrame {
//    private JPanel headerPanel, sidePanel;
//    private JLabel systemLabel, userLabel;
//
//    private JTable infoTable; // not used
//    private JPanel seatsPanel;
//    private JLabel priceLabel, serviceLabel, totalLabel;
//
//    private ButtonGroup methodGroup;
//    private JToggleButton vnpayButton, momoButton, transferButton, cardButton;
//    private JButton backButton, payButton;
//
//    public PaymentView(String userName, ArrayList<String> seats, double ticketPrice, double serviceFee) {
//        super("Payment");
//        initLookAndFeel();
//        initComponents(userName, seats, ticketPrice, serviceFee);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setSize(1000, 650);
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    private void initLookAndFeel() {
//        try {
//            for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (Exception ignored) {}
//    }
//
//    private void initComponents(String userName, ArrayList<String> seats, double ticketPrice, double serviceFee) {
//        setLayout(new BorderLayout());
//
//        // HEADER
//        headerPanel = new JPanel(new BorderLayout());
//        headerPanel.setBackground(new Color(76, 175, 80));
//        headerPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
//        headerPanel.setPreferredSize(new Dimension(0, 60));
//        systemLabel = new JLabel("Hệ thống xe buýt");
//        systemLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
//        systemLabel.setForeground(Color.WHITE);
//        headerPanel.add(systemLabel, BorderLayout.WEST);
//        userLabel = new JLabel(userName);
//        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        userLabel.setForeground(Color.WHITE);
//        headerPanel.add(userLabel, BorderLayout.EAST);
//        add(headerPanel, BorderLayout.NORTH);
//
//        // SIDEBAR
//        sidePanel = new JPanel();
//        sidePanel.setBackground(new Color(33, 37, 41));
//        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
//        sidePanel.setBorder(new EmptyBorder(20, 10, 10, 10));
//        sidePanel.setPreferredSize(new Dimension(200, 0));
//        String[] menu = {"Đặt vé","Lịch sử đặt vé","Thanh toán","Đăng xuất"};
//        for (String m: menu) {
//            boolean sel = m.equals("Thanh toán");
//            sidePanel.add(makeSideButton(m, sel));
//        }
//        add(sidePanel, BorderLayout.WEST);
//
//        // MAIN
//        JPanel main = new JPanel(new BorderLayout(20,20));
//        main.setBackground(Color.WHITE);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
//        add(main, BorderLayout.CENTER);
//
//        // Title
//        JLabel title = new JLabel("Thanh toán vé xe");
//        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
//        main.add(title, BorderLayout.NORTH);
//
//        // Content split
//        JPanel content = new JPanel(new GridLayout(1,2,20,0));
//        content.setBackground(Color.WHITE);
//        main.add(content, BorderLayout.CENTER);
//
//        // LEFT PANEL: Booking Info
//        JPanel left = new JPanel(new BorderLayout(0,20));
//        left.setBackground(Color.WHITE);
//        // Section title
//        JLabel infoTitle = new JLabel("Thông tin đặt vé");
//        infoTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
//        left.add(infoTitle, BorderLayout.NORTH);
//        // Seats
//        seatsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
//        seatsPanel.setBackground(Color.WHITE);
//        for (String s: seats) {
//            JButton b = new JButton(s);
//            b.setBackground(new Color(33, 150, 243)); b.setForeground(Color.WHITE);
//            b.setEnabled(false);
//            seatsPanel.add(b);
//        }
//        left.add(seatsPanel, BorderLayout.CENTER);
//        // Price breakdown
//        JPanel pricePanel = new JPanel();
//        pricePanel.setBackground(Color.WHITE);
//        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));
//        pricePanel.setBorder(new EmptyBorder(20,0,0,0));
//        int count = seats.size();
//        priceLabel = new JLabel(String.format("Giá vé (%d vé x %,.0fđ): %,.0fđ", count, ticketPrice, count*ticketPrice));
//        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        serviceLabel = new JLabel(String.format("Phí dịch vụ: %,.0fđ", serviceFee));
//        serviceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        totalLabel = new JLabel(String.format("Tổng cộng: %,.0fđ", count*ticketPrice+serviceFee));
//        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
//        totalLabel.setForeground(Color.RED);
//        pricePanel.add(priceLabel);
//        pricePanel.add(serviceLabel);
//        pricePanel.add(Box.createRigidArea(new Dimension(0,10)));
//        pricePanel.add(totalLabel);
//        left.add(pricePanel, BorderLayout.SOUTH);
//        content.add(left);
//
//        // RIGHT PANEL: Payment Methods
//        JPanel right = new JPanel(new BorderLayout(0,20));
//        right.setBackground(Color.WHITE);
//        JLabel methodTitle = new JLabel("Phương thức thanh toán");
//        methodTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
//        right.add(methodTitle, BorderLayout.NORTH);
//        JPanel methods = new JPanel();
//        methods.setBackground(Color.WHITE);
//        methods.setLayout(new BoxLayout(methods, BoxLayout.Y_AXIS));
//        methodGroup = new ButtonGroup();
//
//        vnpayButton = makeMethodButton("Ví điện tử VNPAY");
//        momoButton = makeMethodButton("Ví điện tử Momo");
//        transferButton = makeMethodButton("Chuyển khoản ngân hàng");
//        cardButton = makeMethodButton("Thẻ tín dụng/Thẻ ghi nợ");
//        methods.add(vnpayButton);
//        methods.add(Box.createVerticalStrut(10));
//        methods.add(momoButton);
//        methods.add(Box.createVerticalStrut(10));
//        methods.add(transferButton);
//        methods.add(Box.createVerticalStrut(10));
//        methods.add(cardButton);
//        right.add(methods, BorderLayout.CENTER);
//
//        // Buttons
//        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,15,0));
//        btnPanel.setBackground(Color.WHITE);
//        backButton = new JButton("Quay lại"); backButton.setEnabled(false);
//        backButton.setBackground(Color.LIGHT_GRAY);
//        payButton = new JButton("Thanh toán"); payButton.setBackground(Color.WHITE);
//        payButton.setBorder(BorderFactory.createLineBorder(new Color(76,175,80),2));
//        btnPanel.add(backButton); btnPanel.add(payButton);
//        right.add(btnPanel, BorderLayout.SOUTH);
//
//        content.add(right);
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
//    private JToggleButton makeMethodButton(String label) {
//        JToggleButton btn = new JToggleButton(label);
//        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        btn.setHorizontalAlignment(SwingConstants.LEFT);
//        btn.setBackground(new Color(240,240,240));
//        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
//        btn.setFocusPainted(false);
//        methodGroup.add(btn);
//        btn.addActionListener(e -> updateMethodSelection(btn));
//        return btn;
//    }
//
//    private void updateMethodSelection(JToggleButton selected) {
//        for (AbstractButton b : Collections.list(methodGroup.getElements())) {
//            b.setBackground(b==selected?new Color(227,242,253):new Color(240,240,240));
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new PaymentView(
//            "Nguyễn Văn A",
//            new ArrayList<> (Arrays.asList("B2","B4"),
//            150000,
//            10000
//        )));
//    }
//}


package View.CustomerView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PaymentView extends JFrame {
    private JPanel headerPanel, sidePanel;
    private JLabel systemLabel, userLabel;

    private JTable infoTable; // not used
    private JPanel seatsPanel;
    private JLabel priceLabel, serviceLabel, totalLabel;

    private ButtonGroup methodGroup;
    private JToggleButton vnpayButton, momoButton, transferButton, cardButton;
    private JButton backButton, payButton;

    public PaymentView(String userName, List<String> seats, double ticketPrice, double serviceFee) {
        super("Payment");
        initLookAndFeel();
        initComponents(userName, seats, ticketPrice, serviceFee);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    private void initComponents(String userName, List<String> seats, double ticketPrice, double serviceFee) {
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
        userLabel = new JLabel(userName);
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
        String[] menu = {"Bảng điều khiển", "Đặt vé","Lịch sử đặt vé","Đăng xuất"};
        for (String m: menu) {
            boolean sel = m.equals("Đặt vé");
            sidePanel.add(makeSideButton(m, sel));
        }
        add(sidePanel, BorderLayout.WEST);

        // MAIN
        JPanel main = new JPanel(new BorderLayout(20,20));
        main.setBackground(Color.WHITE);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(main, BorderLayout.CENTER);

        // Title
        JLabel title = new JLabel("Thanh toán vé xe");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        main.add(title, BorderLayout.NORTH);

        // Content split
        JPanel content = new JPanel(new GridLayout(1,2,20,0));
        content.setBackground(Color.WHITE);
        main.add(content, BorderLayout.CENTER);

        // LEFT PANEL: Booking Info
        JPanel left = new JPanel(new BorderLayout(0,20));
        left.setBackground(Color.WHITE);
        // Section title
        JLabel infoTitle = new JLabel("Thông tin đặt vé");
        infoTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        left.add(infoTitle, BorderLayout.NORTH);
        // Seats
        seatsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        seatsPanel.setBackground(Color.WHITE);
        for (String s: seats) {
            JButton b = new JButton(s);
            b.setBackground(new Color(33, 150, 243)); b.setForeground(Color.WHITE);
            b.setEnabled(false);
            seatsPanel.add(b);
        }
        left.add(seatsPanel, BorderLayout.CENTER);
        // Price breakdown
        JPanel pricePanel = new JPanel();
        pricePanel.setBackground(Color.WHITE);
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));
        pricePanel.setBorder(new EmptyBorder(20,0,0,0));
        int count = seats.size();
        priceLabel = new JLabel(String.format("Giá vé (%d vé x %,.0fđ): %,.0fđ", count, ticketPrice, count*ticketPrice));
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        serviceLabel = new JLabel(String.format("Phí dịch vụ: %,.0fđ", serviceFee));
        serviceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        totalLabel = new JLabel(String.format("Tổng cộng: %,.0fđ", count*ticketPrice+serviceFee));
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalLabel.setForeground(Color.RED);
        pricePanel.add(priceLabel);
        pricePanel.add(serviceLabel);
        pricePanel.add(Box.createRigidArea(new Dimension(0,10)));
        pricePanel.add(totalLabel);
        left.add(pricePanel, BorderLayout.SOUTH);
        content.add(left);

        // RIGHT PANEL: Payment Methods
        JPanel right = new JPanel(new BorderLayout(0,20));
        right.setBackground(Color.WHITE);
        JLabel methodTitle = new JLabel("Phương thức thanh toán");
        methodTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        right.add(methodTitle, BorderLayout.NORTH);
        JPanel methods = new JPanel();
        methods.setBackground(Color.WHITE);
        methods.setLayout(new BoxLayout(methods, BoxLayout.Y_AXIS));
        methodGroup = new ButtonGroup();

        vnpayButton = makeMethodButton("Ví điện tử VNPAY");
        momoButton = makeMethodButton("Ví điện tử Momo");
        transferButton = makeMethodButton("Chuyển khoản ngân hàng");
        cardButton = makeMethodButton("Thẻ tín dụng/Thẻ ghi nợ");
        methods.add(vnpayButton);
        methods.add(Box.createVerticalStrut(10));
        methods.add(momoButton);
        methods.add(Box.createVerticalStrut(10));
        methods.add(transferButton);
        methods.add(Box.createVerticalStrut(10));
        methods.add(cardButton);
        right.add(methods, BorderLayout.CENTER);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,15,0));
        btnPanel.setBackground(Color.WHITE);
        backButton = new JButton("Quay lại"); backButton.setEnabled(false);
        backButton.setBackground(Color.LIGHT_GRAY);
        payButton = new JButton("Thanh toán"); payButton.setBackground(Color.WHITE);
        payButton.setBorder(BorderFactory.createLineBorder(new Color(76,175,80),2));
        btnPanel.add(backButton); btnPanel.add(payButton);
        right.add(btnPanel, BorderLayout.SOUTH);

        content.add(right);
    }

    private JButton makeSideButton(String text, boolean selected) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", selected?Font.BOLD:Font.PLAIN,14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(selected?new Color(76,175,80):new Color(33,37,41));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE,40)); btn.setFocusPainted(false);
        return btn;
    }

    private JToggleButton makeMethodButton(String label) {
        JToggleButton btn = new JToggleButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBackground(new Color(240,240,240));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setFocusPainted(false);
        methodGroup.add(btn);
        btn.addActionListener(e -> updateMethodSelection(btn));
        return btn;
    }

    private void updateMethodSelection(JToggleButton selected) {
        for (AbstractButton b : Collections.list(methodGroup.getElements())) {
            b.setBackground(b==selected?new Color(227,242,253):new Color(240,240,240));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentView(
            "Nguyễn Văn A",
            Arrays.asList("B2","B4"),
            150000,
            10000
        ));
    }
}
