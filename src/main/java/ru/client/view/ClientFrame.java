package ru.client.view;

import javax.swing.*;

public class ClientFrame extends JFrame {
    private static final String FRAME_TITLE = "Система автомобилестроительного предприятия";
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 600;
    private JPanel container;


    public ClientFrame() {
        initUI();
    }

    private void initUI() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle(FRAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = new MainPanel(this);
        add(container);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ClientFrame clientFrame = new ClientFrame();
            clientFrame.setVisible(true);
        });
    }
}

