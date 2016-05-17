package ru.client.view.dialogs;

import ru.client.model.Brigades;
import ru.client.model.Sites;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class BrigadesDialog extends JDialog {
    private static final String TITLE = "Новая бригада";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private final Frame owner;
    private final DialogHandler<Brigades> handler;

    private JTextField name;
    private JComboBox site;
    private JButton addButton;

    public BrigadesDialog(Frame owner, DialogHandler<Brigades> handler, Map<String, Integer> sitesName) {
        super(owner);
        setTitle(TITLE);
        this.owner = owner;
        this.handler = handler;
        setSize(new Dimension(WIDTH, HEIGHT));
        JPanel container = new JPanel(new GridLayout(8, 2));
        container.setBorder(new EmptyBorder(10, 10, 10, 10));
        String[] sites = sitesName.keySet().stream().toArray(String[]::new);

        name = new JTextField();
        site = new JComboBox(sites);

        addButton = new JButton("Добавить");
        addButton.addActionListener(e -> {
            String brigadeName = name.getText();
            int siteId = sitesName.get(String.valueOf(site.getSelectedItem()));

            Brigades brigades = new Brigades(brigadeName, siteId);
            handler.handle(brigades);
            setVisible(false);
        });

        container.add(new JLabel("Название:"));
        container.add(name);

        container.add(new JLabel("Участок:"));
        container.add(site);

        container.add(addButton);
        add(container);
        setVisible(true);
    }
}
