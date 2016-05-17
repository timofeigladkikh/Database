package ru.client.view.tablemodel;

import ru.client.Observable;
import ru.client.Observer;
import ru.client.model.Top;
import ru.client.model.TopDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class TopTableModel implements TableModel, Observable {
    private List<Top> topList;
    private final TopDAO topDAO;
    private List<Observer> observers;

    public TopTableModel(List<Top> topList, TopDAO topDAO) {
        this.topList = topList;
        this.topDAO = topDAO;
        observers = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return topList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "Название";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 1:
                return true;
            default:
                return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Top top = topList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return top.getId();
            case 1:
                return top.getName();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Top top = topList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String name = (String) aValue;
                top.setName(name);
                topDAO.update(top);
                observers.stream().forEach(Observer::update);
                break;
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {}

    @Override
    public void removeTableModelListener(TableModelListener l) {}

    @Override
    public void addListener(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeListener(Observer observer) {
        observers.remove(observer);
    }
}
