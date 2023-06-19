package org.space;

import org.space.helper.GameUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JFrame frame;
    private int currentPanelIndex;


    public void run() {
        createMainPanel();
        createPanels();
        createFrame();
        addKeyBindings();
    }

    private void createMainPanel() {
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
    }

    private void createPanels() {
        int panelWidth = 1600;
        int panelHeight = 1200;

        JPanel homePanel = createPanel("src/main/java/org/space/drawable/Menu.png", panelWidth, panelHeight);
        JPanel panel1 = createPanel("src/main/java/org/space/drawable/Earth Collection.png", panelWidth, panelHeight);
        JPanel panel2 = createPanel("src/main/java/org/space/drawable/Mercury Collection.png", panelWidth, panelHeight);
        JPanel panel3 = createPanel("src/main/java/org/space/drawable/Venus Collection.png", panelWidth, panelHeight);
        JPanel panelPlay = new JPanel();
        panelPlay.setPreferredSize(new Dimension(1600, 800));
        panelPlay.add(new Battle());

        mainPanel.add(homePanel, "Home"); // Add home panel with name "Home"
        mainPanel.add(panel1, "Panel 1");
        mainPanel.add(panel2, "Panel 2");
        mainPanel.add(panel3, "Panel 3");
        mainPanel.add(panelPlay, "play");

        currentPanelIndex = 0;
    }

    private JPanel createPanel(final String imagePath, final int width, final int height) {
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File(imagePath));
                    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    int x = (getWidth() - scaledImage.getWidth(this)) / 2;
                    int y = (getHeight() - scaledImage.getHeight(this)) / 2;
                    g.drawImage(scaledImage, x, y, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        GameUtil.playMusic("src/main/java/org/space/drawable/ost.wav");
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }


    private void createFrame() {
        int panelWidth = 1600;
        int panelHeight = 1088;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Space Card Game");

        frame.setSize(panelWidth, panelHeight);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void addKeyBindings() {
        frame.setFocusable(true);

        frame.requestFocusInWindow();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SPACE) {
                    cardLayout.show(mainPanel, "Panel 1");
                    currentPanelIndex = 1;
                } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                    if (currentPanelIndex >= 1 && currentPanelIndex <= 3) {
                        if (keyCode == KeyEvent.VK_LEFT) {
                            currentPanelIndex--;
                            if (currentPanelIndex < 1) {
                                currentPanelIndex = 3;
                            }
                        } else {
                            currentPanelIndex++;
                            if (currentPanelIndex > 3) {
                                currentPanelIndex = 1;
                            }
                        }
                        cardLayout.show(mainPanel, "Panel " + currentPanelIndex);
                    }
                } else if (keyCode == KeyEvent.VK_ESCAPE) {
                    if (currentPanelIndex == 0) {
                        System.exit(0);
                    } else {
                        cardLayout.show(mainPanel, "Home");
                        currentPanelIndex = 0;
                    }
                }else if (keyCode == KeyEvent.VK_ENTER) {

                    if (currentPanelIndex == 0) {
                        cardLayout.show(mainPanel, "play");
                        GameUtil.playOnce("src/main/java/org/space/drawable/startfight.wav");

                        currentPanelIndex = 4;
                    }
                }
            }
        });
    }

}
