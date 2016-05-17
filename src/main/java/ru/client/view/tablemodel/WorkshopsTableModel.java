package ru.client.view.tablemodel;

import ru.client.Observable;
import ru.client.Observer;
import ru.client.model.Workshops;
import ru.client.model.WorkshopsDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class WorkshopsTableModel implements TableModel, Observable {
    private List<Workshops> workshopsList;
    private final WorkshopsDAO workshopsDAO;
    private List<Observer> observers;

    public WorkshopsTableModel(List<Workshops> workshopsList, WorkshopsDAO workshopsDAO) {
        this.workshopsList = workshopsList;
        this.workshopsDAO = workshopsDAO;
        observers = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return workshopsList.size();
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
        Workshops workshops = workshopsList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return workshops.getId();
            case 1:
                return workshops.getName();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Workshops workshops = workshopsList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String name = (String) aValue;
                workshops.setName(name);
                workshopsDAO.update(workshops);
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
