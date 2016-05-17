package ru.client.view.tablemodel;

import ru.client.model.tasks.Task8;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class Task8Model implements TableModel {

    private final List<Task8> task8List;

    public Task8Model(List<Task8> task8List) {
        this.task8List = task8List;
    }

    @Override
    public int getRowCount() {
        return task8List.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Фамилия";
            case 1:
                return "Имя";
            case 2:
                return "Бригада";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            default:
                return null;
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task8 task8 = task8List.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return task8.getSurname();
            case 1:
                return task8.getName();
            case 2:
                return task8.getBrigadeName();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
