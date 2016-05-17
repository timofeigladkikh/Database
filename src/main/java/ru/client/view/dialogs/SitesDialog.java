package ru.client.view.dialogs;

import ru.client.model.Sites;
import ru.client.model.Workshops;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class SitesDialog extends JDialog {
    private static final String TITLE = "Новый участок";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private final Frame owner;
    private final DialogHandler<Sites> handler;

    private JTextField name;
    private JComboBox workshop;
    private JButton addButton;

    public SitesDialog(Frame owner, DialogHandler<Sites> handler, Map<String, Integer> workshopsName) {
        super(owner);
        setTitle(TITLE);
        this.owner = owner;
        this.handler = handler;
        setSize(new Dimension(WIDTH, HEIGHT));
        JPanel container = new JPanel(new GridLayout(8, 2));
        container.setBorder(new EmptyBorder(10, 10, 10, 10));
        String[] workshops = workshopsName.keySet().stream().toArray(String[]::new);

        name = new JTextField();
        workshop = new JComboBox(workshops);

        addButton = new JButton("Добавить");
        addButton.addActionListener(e -> {
            String siteName = name.getText();
            int workshopId = workshopsName.get(String.valueOf(workshop.getSelectedItem()));

            Sites site = new Sites(siteName, workshopId);
            handler.handle(site);
            setVisible(false);
        });

        container.add(new JLabel("Имя:"));
        container.add(name);

        container.add(new JLabel("Цех:"));
        container.add(workshop);

        container.add(addButton);
        add(container);
        setVisible(true);
    }
}
