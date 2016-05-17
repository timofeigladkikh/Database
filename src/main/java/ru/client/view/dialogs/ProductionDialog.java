package ru.client.view.dialogs;

import ru.client.model.Production;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class ProductionDialog extends JDialog {
    private static final String TITLE = "Новый этап производства";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private final Frame owner;
    private final DialogHandler<Production> handler;

    private JComboBox productsId;
    private JTextField dateStart;
    private JTextField dateEnd;
    private JComboBox brigadesId;

    private JButton addButton;

    public ProductionDialog(Frame owner, DialogHandler<Production> handler, Map<String, Integer> productsName,
                         Map<String, Integer> brigadesName) {
        super(owner);
        setTitle(TITLE);
        this.owner = owner;
        this.handler = handler;
        setSize(new Dimension(WIDTH, HEIGHT));
        JPanel container = new JPanel(new GridLayout(8, 2));
        container.setBorder(new EmptyBorder(10, 10, 10, 10));
        String[] brigadess = brigadesName.keySet().stream().toArray(String[]::new);
        String[] products = productsName.keySet().stream().toArray(String[]::new);

        productsId = new JComboBox(products);
        dateStart = new JTextField();
        dateEnd = new JTextField();
        brigadesId = new JComboBox(brigadess);

        addButton = new JButton("Добавить");
        addButton.addActionListener(e -> {

            String prdateStart = dateStart.getText();
            String prdateEnd = dateEnd.getText();
            int prProductsId = productsName.get(String.valueOf(productsId.getSelectedItem()));
            int prBrigadesId = brigadesName.get(String.valueOf(brigadesId.getSelectedItem()));

            Production production = new Production(prProductsId, prdateStart, prdateEnd, prBrigadesId);
            handler.handle(production);
            setVisible(false);
        });

        container.add(new JLabel("ID продукта:"));
        container.add(productsId);

        container.add(new JLabel("Начало работ:"));
        container.add(dateStart);

        container.add(new JLabel("Конец работ:"));
        container.add(dateEnd);

        container.add(new JLabel("Бригада:"));
        container.add(brigadesId);

        container.add(addButton);
        add(container);
        setVisible(true);
    }
}
