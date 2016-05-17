package ru.client.view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final JFrame owner;
    JTabbedPane tabbedPane;
    private JScrollPane scrollPane;

    public MainPanel(JFrame owner) {
        super(new GridLayout(1, 1));
        this.owner = owner;
        tabbedPane = new JTabbedPane();

        JPanel panel0 = new GeneralPanel(owner);
        panel0.setPreferredSize(getSize());
        /*JPanel panel2 = new Task2Panel(owner);
        panel2.setPreferredSize(getSize());
        JPanel panel3 = new Task3Panel(owner);
        panel3.setPreferredSize(getSize());
        JPanel panel4 = new Task4Panel(owner);
        panel4.setPreferredSize(getSize());
        JPanel panel5 = new Task5Panel(owner);
        panel5.setPreferredSize(getSize());
        JPanel panel6 = new Task6Panel(owner);
        panel6.setPreferredSize(getSize());
        JPanel panel7 = new Task7Panel(owner);
        panel7.setPreferredSize(getSize());*/
        JPanel panel8 = new Task8Panel(owner);
        panel8.setPreferredSize(getSize());
        /*JPanel panel9 = new Task9Panel(owner);
        panel9.setPreferredSize(getSize());
        JPanel panel10 = new Task1Panel(owner);
        panel10.setPreferredSize(getSize());*/

        tabbedPane.addTab("Главная", null, panel0);
        /*tabbedPane.addTab("Запрос № 1", null, panel10);
        tabbedPane.addTab("Запрос № 2", null, panel2);
        tabbedPane.addTab("Запрос № 3", null, panel3);
        tabbedPane.addTab("Запрос № 4", null, panel4);
        tabbedPane.addTab("Запрос № 5", null, panel5);
        tabbedPane.addTab("Запрос № 6", null, panel6);
        tabbedPane.addTab("Запрос № 7", null, panel7);*/
        tabbedPane.addTab("Запрос № 8", null, panel8);
        //tabbedPane.addTab("Запрос № 9", null, panel9);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(new JScrollPane(tabbedPane));
        //add(scrollPane);
    }
}
