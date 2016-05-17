package ru.client.view.dialogs;

import ru.client.model.Itp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class ItpDialog extends JDialog {
    private static final String TITLE = "Новый член ИТП";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private final Frame owner;
    private final DialogHandler<Itp> handler;

    private JTextField surname;
    private JTextField name;
    private JComboBox workshop;
    private JComboBox site;
    private JComboBox post;
    private JTextField is_chief_of_workshop;
    private JTextField is_chief_of_site;
    private JButton addButton;

    public ItpDialog(Frame owner, DialogHandler<Itp> handler, Map<String, Integer> workshopsName, Map<String,
                     Integer> sitesName, Map<String, Integer> postsName) {
        super(owner);
        setTitle(TITLE);
        this.owner = owner;
        this.handler = handler;
        setSize(new Dimension(WIDTH, HEIGHT));
        JPanel container = new JPanel(new GridLayout(8, 2));
        container.setBorder(new EmptyBorder(10, 10, 10, 10));
        String[] workshops = workshopsName.keySet().stream().toArray(String[]::new);
        String[] sites = sitesName.keySet().stream().toArray(String[]::new);
        String[] posts = postsName.keySet().stream().toArray(String[]::new);

        surname = new JTextField();
        name = new JTextField();
        workshop = new JComboBox(workshops);
        site = new JComboBox(sites);
        post = new JComboBox(posts);
        is_chief_of_workshop = new JTextField();
        is_chief_of_site = new JTextField();

        addButton = new JButton("Добавить");
        addButton.addActionListener(e -> {

            String itpSurname = surname.getText();
            String itpName = name.getText();
            int workshopId = workshopsName.get(String.valueOf(workshop.getSelectedItem()));
            int itpSite = sitesName.get(String.valueOf(site.getSelectedItem()));
            int itpPost = postsName.get(String.valueOf(post.getSelectedItem()));
            int itpIsChiefOfWorkshop = Integer.parseInt(is_chief_of_workshop.getText());
            int itpIsChiefOfSite = Integer.parseInt(is_chief_of_site.getText());

            Itp itp = new Itp(itpSurname, itpName, workshopId, itpSite, itpPost, itpIsChiefOfWorkshop, itpIsChiefOfSite);
            handler.handle(itp);
            setVisible(false);
        });

        container.add(new JLabel("Фамилия:"));
        container.add(surname);

        container.add(new JLabel("Имя:"));
        container.add(name);

        container.add(new JLabel("Цех:"));
        container.add(workshop);

        container.add(new JLabel("Участок:"));
        container.add(site);

        container.add(new JLabel("Должность:"));
        container.add(post);

        container.add(new JLabel("Нач_цеха:"));
        container.add(is_chief_of_workshop);

        container.add(new JLabel("Нач_участка:"));
        container.add(is_chief_of_site);

        container.add(addButton);
        add(container);
        setVisible(true);
    }

}
