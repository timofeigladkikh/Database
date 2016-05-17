package ru.client.view;

import ru.client.DbSource;
import ru.client.TaskQueries;
import ru.client.model.tasks.Task8;
import ru.client.view.tablemodel.Task8Model;

import javax.swing.*;
import java.util.List;

public class Task8Panel extends JPanel {
    private static JLabel query;
    private static JLabel query1;
    private static JTextField id;
    private static JButton okButton;
    private JTable resultTable;
    private final JFrame owner;

    public Task8Panel(JFrame owner) {
        this.owner = owner;
        query = new JLabel("Получить состав бригад, участвующих в сборке указанного изделия");
        query1 = new JLabel("Введите номер изделия:");
        id = new JTextField();
        resultTable = new JTable();
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        okButton = new JButton("ОК");

        okButton.addActionListener(e -> {
            Integer itpId = Integer.parseInt(id.getText());
            TaskQueries taskQueries = new TaskQueries(DbSource.getSql2o());
            List<Task8> result = taskQueries.task8Query(itpId);
            result.stream()
                    .forEach(System.out::println);
            resultTable.setModel(new Task8Model(result));
            repaint();
        });

         /*
        SELECT DISTINCT W.SURNAME, W.NAME, B.NAME
        FROM BRIGADES B, WORKERS W, PRODUCTION P, PRODUCTS PR
        WHERE P.PRODUCTS_ID = 0
        AND P.PRODUCTS_ID = PR.ID
        AND W.BRIGADE = B.ID
        ORDER BY B.NAME
        */

        container.add(query);
        container.add(query1);
        container.add(id);
        container.add(okButton);
        container.add(resultTable.getTableHeader());
        container.add(resultTable);

        add(container);

        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //add(task8Table.getTableHeader());
        //add(new JScrollPane(task8Table));

        setVisible(true);


    }
}

