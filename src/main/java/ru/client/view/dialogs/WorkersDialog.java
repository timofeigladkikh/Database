package ru.client.view.dialogs;

import ru.client.model.Itp;
import ru.client.model.Workers;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class WorkersDialog extends JDialog {
    private static final String TITLE = "Новый рабочий";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private final Frame owner;
    private final DialogHandler<Workers> handler;

    private JTextField surname;
    private JTextField name;
    private JComboBox brigades;
    private JTextField isBrigadier;
    private JComboBox post;

    private JButton addButton;

    public WorkersDialog(Frame owner, DialogHandler<Workers> handler, Map<String, Integer> brigadesName,
                         Map<String, Integer> postsWorkersName) {
        super(owner);
        setTitle(TITLE);
        this.owner = owner;
        this.handler = handler;
        setSize(new Dimension(WIDTH, HEIGHT));
        JPanel container = new JPanel(new GridLayout(8, 2));
        container.setBorder(new EmptyBorder(10, 10, 10, 10));
        String[] brigadess = brigadesName.keySet().stream().toArray(String[]::new);
        String[] posts = postsWorkersName.keySet().stream().toArray(String[]::new);

        surname = new JTextField();
        name = new JTextField();
        brigades = new JComboBox(brigadess);
        isBrigadier = new JTextField();
        post = new JComboBox(posts);

        addButton = new JButton("Добавить");
        addButton.addActionListener(e -> {

            String workersSurname = surname.getText();
            String workersName = name.getText();
            int workersBrigadeId = brigadesName.get(String.valueOf(brigades.getSelectedItem()));
            int workersIsBrigadiers = Integer.parseInt(isBrigadier.getText());
            int workersPost = postsWorkersName.get(String.valueOf(post.getSelectedItem()));

            Workers workers = new Workers(workersSurname, workersName, workersBrigadeId, workersIsBrigadiers, workersPost);
            handler.handle(workers);
            setVisible(false);
        });

        container.add(new JLabel("Фамилия:"));
        container.add(surname);

        container.add(new JLabel("Имя:"));
        container.add(name);

        container.add(new JLabel("Бригада:"));
        container.add(brigades);

        container.add(new JLabel("Бригадир ли:"));
        container.add(isBrigadier);

        container.add(new JLabel("Должность:"));
        container.add(post);

        container.add(addButton);
        add(container);
        setVisible(true);
    }
}
