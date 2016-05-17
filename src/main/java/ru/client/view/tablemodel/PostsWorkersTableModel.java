package ru.client.view.tablemodel;

import ru.client.Observable;
import ru.client.Observer;
import ru.client.model.PostsItp;
import ru.client.model.PostsWorkers;
import ru.client.model.PostsWorkersDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class PostsWorkersTableModel implements TableModel, Observable {
    private List<PostsWorkers> postsWorkersList;
    private final PostsWorkersDAO postsWorkersDAO;
    private List<Observer> observers;

    public PostsWorkersTableModel(List<PostsWorkers> postsWorkersList, PostsWorkersDAO postsWorkersDAO) {
        this.postsWorkersList = postsWorkersList;
        this.postsWorkersDAO = postsWorkersDAO;
        observers = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return postsWorkersList.size();
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
        PostsWorkers postsWorkers = postsWorkersList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return postsWorkers.getId();
            case 1:
                return postsWorkers.getName();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PostsWorkers postsWorkers = postsWorkersList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String name = (String) aValue;
                postsWorkers.setName(name);
                postsWorkersDAO.update(postsWorkers);
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
