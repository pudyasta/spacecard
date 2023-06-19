package org.space.helper;
import org.space.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class CustomAlert extends JDialog {
    public CustomAlert(String message) {
        super();
        setTitle("Alert");
        setModal(true);
        setUndecorated(true);


        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                Image backgroundImage = new ImageIcon("src/main/java/org/space/drawable/Alert.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };


        setContentPane(contentPane);


        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setFont(messageLabel.getFont().deriveFont(20f));


        messageLabel.setForeground(Color.WHITE);
        JButton closeButton = new JButton("Finish");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

                org.space.Menu program = new Menu();
                program.run(); // Start the new frame
            }
        });


        contentPane.setLayout(new BorderLayout());
        contentPane.add(messageLabel, BorderLayout.CENTER);
        contentPane.add(closeButton, BorderLayout.SOUTH);


        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}

