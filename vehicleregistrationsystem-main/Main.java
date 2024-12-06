import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("VRMS v1.0");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/bg.png"));
                backgroundImage.paintIcon(this, g, 0, 0);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 800, 800);

        JLabel title = new JLabel("VEHICLE REGISTRATION SYSTEM");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        title.setForeground(Color.WHITE);

        JButton register = createStyledButton("Register Your Vehicle", 250, 300);
        JButton check = createStyledButton("Check Vehicle Details", 250, 400);

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registervehicle otherFrame = new registervehicle();
                otherFrame.setVisible(true);
            }
        });

        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the ShowDetails frame or perform any other action
                ShowDetails detailsFrame = new ShowDetails();
                detailsFrame.setVisible(true);
            }
        });

        backgroundPanel.add(title);
        backgroundPanel.add(register);
        backgroundPanel.add(check);

        title.setBounds(0, 50, 800, 100);
        register.setBounds(250, 300, 300, 60);
        check.setBounds(250, 400, 300, 60);

        frame.add(backgroundPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private static JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(34, 93, 114));
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(103, 155, 171));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(34, 93, 114));
            }
        });

        button.setBounds(x, y, 300, 60);
        return button;
    }
}
