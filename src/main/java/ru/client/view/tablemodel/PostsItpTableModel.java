package ru.client.view.tablemodel;

import ru.client.Observable;
import ru.client.Observer;
import ru.client.model.PostsItp;
import ru.client.model.PostsItpDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class PostsItpTableModel implements TableModel, Observable {
    private List<PostsItp> postsItpList;
    private final PostsItpDAO postsItpDAO;
    private List<Observer> observers;

    public PostsItpTableModel(List<PostsItp> postsItpList, PostsItpDAO postsItpDAO) {
        this.postsItpList = postsItpList;
        this.postsItpDAO = postsItpDAO;
        observers = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return postsItpList.size();
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
        PostsItp postsItp = postsItpList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return postsItp.getId();
            case 1:
                return postsItp.getName();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PostsItp postsItp = postsItpList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String name = (String) aValue;
                postsItp.setName(name);
                postsItpDAO.update(postsItp);
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

