package ru.client.view.dialogs;

import ru.client.model.PostsItp;
import ru.client.model.Top;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TOPDialog extends JDialog{
    private static final String TITLE = "Новый вид изделия";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private final Frame owner;
    private final DialogHandler<Top> handler;

    private JTextField name;
    private JButton addButton;

    public TOPDialog(Frame owner, DialogHandler<Top> handler) {
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

            String postName = name.getText();

            Top top = new Top(postName);
            handler.handle(top);
            setVisible(false);
        });

        container.add(new JLabel("Название:"));
        container.add(name);

        container.add(addButton);
        add(container);
        setVisible(true);
    }
}
