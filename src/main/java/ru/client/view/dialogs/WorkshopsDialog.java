package ru.client.view.dialogs;

import ru.client.model.Workshops;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WorkshopsDialog extends JDialog {
    private static final String TITLE = "Новый цех";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private final Frame owner;
    private final DialogHandler<Workshops> handler;

    private JTextField name;
    private JButton addButton;

    public WorkshopsDialog(Frame owner, DialogHandler<Workshops> handler) {
        super(owner);
        setTitle(TITLE);
        this.owner = owner;
        this.handler = handler;
        setSize(new Dimension(WIDTH, HEIGHT));
        JPanel container = new JPanel(new GridLayout(8, 2));
        container.setBorder(new EmptyBorder(10, 10, 10, 10));

        name = new JTextField();

        addButton = new JButton("Добавить");
        addButton.addActionListener(e -> {

            String workshopName = name.getText();

            Workshops workshops = new Workshops(workshopName);
            handler.handle(workshops);
            setVisible(false);
        });

        container.add(new JLabel("Имя:"));
        container.add(name);

        container.add(addButton);
        add(container);
        setVisible(true);
    }
}
