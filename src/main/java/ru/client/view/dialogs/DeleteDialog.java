package ru.client.view.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DeleteDialog extends JDialog {
    private static final int WIDTH = 350;
    private static final int HEIGHT = 75;
    private final Frame owner;
    private final DialogHandler<Integer> handler;

    private JTextField id;
    private JButton deleteButton;

    public DeleteDialog(Frame owner, DialogHandler<Integer> handler, String title) {
        super(owner);
        setTitle(title);
        this.owner = owner;
        this.handler = handler;
        setSize(new Dimension(WIDTH, HEIGHT));
        JPanel container = new JPanel(new GridLayout(1, 2));
        container.setBorder(new EmptyBorder(5, 5, 5, 5));

        id = new JTextField();

        deleteButton = new JButton("ОК");
        deleteButton.addActionListener(e -> {
            Integer itpId = Integer.parseInt(id.getText());
            handler.handle(itpId);
            setVisible(false);
        });

        container.add(new JLabel("id:"));
        container.add(id);

        container.add(deleteButton);
        add(container);
        setVisible(true);
    }

}
