package View.Overall;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;
import java.net.URL;

public class MainView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblImage;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainView frame = new MainView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 700);
        setLocationRelativeTo(null);
        setTitle("Đặt Vé Xe Khách");

        // Tạo contentPane và set layout
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null); // Sử dụng layout null để custom vị trí dễ hơn
        setContentPane(contentPane);

        // Tạo MenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuHoTro = new JMenu("Hỗ Trợ");
        menuBar.add(menuHoTro);

        JMenuItem menuItemTroGiup = new JMenuItem("Trợ Giúp");
        menuHoTro.add(menuItemTroGiup);

        JMenuItem menuItemLienHe = new JMenuItem("Liên Hệ");
        menuHoTro.add(menuItemLienHe);

        // Panel cho nút Đăng Nhập và Đăng Ký
        JPanel menuPanel = new JPanel();
        menuBar.add(menuPanel);
        menuPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton menuButtonDangNhap = new JButton("Đăng Nhập");
        menuPanel.add(menuButtonDangNhap);

        JButton menuButtonDangKy = new JButton("Đăng Ký");
        menuPanel.add(menuButtonDangKy);

        // Tạo JLabel để hiển thị ảnh và đặt vị trí, kích thước
        lblImage = new JLabel();
        lblImage.setBounds(10, 10, 1076, 283); // Đặt vị trí cho ảnh
        contentPane.add(lblImage);
        
        JPanel panel = new JPanel();
        panel.setBounds(79, 331, 937, 203);
        contentPane.add(panel);

        // Đọc ảnh từ resources (ảnh phải nằm trong thư mục src)
        URL url = MainView.class.getResource("/anhNenXe.jpg");
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage();
            // Đặt ảnh cover toàn bộ JLabel
            Image imgScale = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            lblImage.setIcon(scaledIcon);
        } else {
            System.out.println("Không tìm thấy ảnh! Đảm bảo ảnh ở đúng thư mục.");
        }
    }
}
