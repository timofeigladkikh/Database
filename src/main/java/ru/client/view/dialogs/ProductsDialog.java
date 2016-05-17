package ru.client.view.dialogs;

import ru.client.model.Production;
import ru.client.model.Products;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class ProductsDialog extends  JDialog {
    private static final String TITLE = "Новый продукт";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private final Frame owner;
    private final DialogHandler<Products> handler;

    private JComboBox top;
    private JTextField name;


    private JButton addButton;

    public ProductsDialog(Frame owner, DialogHandler<Products> handler, Map<String, Integer> topName) {
        super(owner);
        setTitle(TITLE);
        this.owner = owner;
        this.handler = handler;
        setSize(new Dimension(WIDTH, HEIGHT));
        JPanel container = new JPanel(new GridLayout(8, 2));
        container.setBorder(new EmptyBorder(10, 10, 10, 10));
        String[] topNames = topName.keySet().stream().toArray(String[]::new);

        top = new JComboBox(topNames);
        name = new JTextField();

        addButton = new JButton("Добавить");
        addButton.addActionListener(e -> {

            String productsName = name.getText();
            int productsTop = topName.get(String.valueOf(top.getSelectedItem()));

            Products products = new Products(productsTop, productsName);
            handler.handle(products);
            setVisible(false);
        });

        container.add(new JLabel("Вид продукта:"));
        container.add(top);

        container.add(new JLabel("Название:"));
        container.add(name);

        container.add(addButton);
        add(container);
        setVisible(true);
    }
}
