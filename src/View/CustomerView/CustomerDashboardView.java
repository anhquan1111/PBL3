//package View.CustomerView;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//public class CustomerDashboardView extends JFrame {
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
//					CustomerDashboardView frame = new CustomerDashboardView();
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
//	public CustomerDashboardView() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1015, 711);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//	}
//
//}

package View.CustomerView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerDashboardView extends JFrame {
    // Header và sidebar
    private JPanel headerPanel, sidePanel;
    private JLabel systemTitleLabel, userLabel;
    private JButton dashboardBtn, bookTicketBtn, historyBtn, paymentBtn, logoutBtn;

    // Thẻ thông số
    private JLabel totalCountValue, pendingCountValue, paidCountValue;

    // Bảng vé gần đây
    private JTable bookingTable;
    private DefaultTableModel tableModel;

    public CustomerDashboardView() {
        super("Bảng điều khiển khách hàng / Customer Dashboard");
        initLookAndFeel();
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
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

    private void initComponents() {
        // Layout chính: BorderLayout
        getContentPane().setLayout(new BorderLayout());

        // ===== HEADER =====
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(76, 175, 80));
        headerPanel.setPreferredSize(new Dimension(0, 60));
        headerPanel.setBorder(new EmptyBorder(0, 20, 0, 20));

        systemTitleLabel = new JLabel("Hệ thống xe buýt");
        systemTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        systemTitleLabel.setForeground(Color.WHITE);
        headerPanel.add(systemTitleLabel, BorderLayout.WEST);

        userLabel = new JLabel("Nguyễn Văn A  User");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setForeground(Color.WHITE);
        headerPanel.add(userLabel, BorderLayout.EAST);

        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // ===== SIDEBAR =====
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(33, 37, 41));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(180, 0));
        sidePanel.setBorder(new EmptyBorder(20, 10, 10, 10));

        dashboardBtn   = makeSideButton("Bảng điều khiển");
        bookTicketBtn  = makeSideButton("Đặt vé");
        historyBtn     = makeSideButton("Lịch sử đặt vé");
//        paymentBtn     = makeSideButton("Thanh toán");
        logoutBtn      = makeSideButton("Đăng xuất");

        sidePanel.add(dashboardBtn);
        sidePanel.add(bookTicketBtn);
        sidePanel.add(historyBtn);
//        sidePanel.add(paymentBtn);
        sidePanel.add(Box.createVerticalGlue());
        sidePanel.add(logoutBtn);

        getContentPane().add(sidePanel, BorderLayout.WEST);

        // ===== MAIN CONTENT =====
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // --- Thẻ thông số (cards) ---
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cardsPanel.setBackground(Color.WHITE);

        totalCountValue   = makeStatCard(cardsPanel, "Tổng số vé đã đặt", "12");
        pendingCountValue = makeStatCard(cardsPanel, "Vé đang chờ thanh toán", "2");
        paidCountValue    = makeStatCard(cardsPanel, "Vé đã thanh toán", "10");

        mainPanel.add(cardsPanel, BorderLayout.NORTH);

        // --- Bảng vé gần đây ---
        String[] cols = { "Mã vé", "Tuyến đường", "Ngày đi", "Ghế", "Trạng thái", "Hành động" };
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int row, int col) {
                return col == 5; // chỉ cột Hành động editable
            }
        };
        // Thêm dữ liệu mẫu
        tableModel.addRow(new Object[]{ "#B12345", "Hà Nội - Hải Phòng", "15/06/2023", "A3, A4", "Đã xác nhận", null });
        tableModel.addRow(new Object[]{ "#B12346", "Hà Nội - Đà Nẵng", "20/06/2023", "B2",        "Chờ thanh toán", null });
        tableModel.addRow(new Object[]{ "#B12347", "Hà Nội - Nha Trang","25/06/2023", "C1",        "Đã xác nhận", null });

        bookingTable = new JTable(tableModel);
        bookingTable.setRowHeight(30);
        bookingTable.getColumn("Hành động").setCellRenderer(new ButtonRenderer());
        bookingTable.getColumn("Hành động").setCellEditor(new ButtonEditor());

        JScrollPane tableScroll = new JScrollPane(bookingTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Đặt vé gần đây"));
        mainPanel.add(tableScroll, BorderLayout.CENTER);
    }

    // Tạo nút sidebar đồng bộ style
    private JButton makeSideButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(33, 37, 41));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // Tạo card thống kê, trả về giá trị label để có thể update sau này
    private JLabel makeStatCard(JPanel parent, String title, String value) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)),
            new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLabel valueLbl = new JLabel(value);
        valueLbl.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(titleLbl);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(valueLbl);

        parent.add(card);
        return valueLbl;
    }

    // Renderer cho cột nút hành động
    private class ButtonRenderer extends JPanel implements TableCellRenderer {
        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int col) {
            removeAll();
            JButton view = new JButton("Xem");
            JButton cancel = new JButton("Hủy");
            view.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            cancel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            add(view);
            add(cancel);
            return this;
        }
    }

    // Editor cho cột nút hành động (chỉ demo, chưa xử lý logic)
    private class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel;
        public ButtonEditor() {
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            JButton view = new JButton("Xem");
            JButton cancel = new JButton("Hủy");
            view.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            cancel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            panel.add(view);
            panel.add(cancel);
            // TODO: Thêm ActionListener cho view & cancel
            view.addActionListener(e ->
                JOptionPane.showMessageDialog(CustomerDashboardView.this,
                    "Xem chi tiết vé")
            );
            cancel.addActionListener(e ->
                JOptionPane.showMessageDialog(CustomerDashboardView.this,
                    "Hủy vé")
            );
        }
        @Override public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int col) {
            return panel;
        }
        @Override public Object getCellEditorValue() {
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CustomerDashboardView::new);
    }
}
