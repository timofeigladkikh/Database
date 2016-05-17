package ru.client.view.tablemodel;

import ru.client.Observable;
import ru.client.Observer;
import ru.client.model.Brigades;
import ru.client.model.BrigadesDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class BrigadesTableModel implements TableModel, Observable {
    private List<Brigades> brigadesList;
    private final BrigadesDAO brigadesDAO;
    private Map<Integer, String> sitesMap;
    private List<Observer> observers;

    public BrigadesTableModel(List<Brigades> brigadesList, BrigadesDAO brigadesDAO, Map<Integer, String> sitesMap) {
        this.brigadesList = brigadesList;
        this.brigadesDAO = brigadesDAO;
        this.sitesMap = sitesMap;
        observers = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return brigadesList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "Название";
            case 2:
                return "Бригада";
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
            case 2:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Brigades brigades = brigadesList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return brigades.getId();
            case 1:
                return brigades.getName();
            case 2:
                int sitesId = brigades.getSites();
                return sitesMap.get(sitesId);
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Brigades brigades = brigadesList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String name = (String) aValue;
                brigades.setName(name);
                brigadesDAO.update(brigades);
                observers.stream().forEach(Observer::update);
                break;
            case 2:
                String workshops = (String) aValue;
                int sitesId = 0;
                for (Map.Entry<Integer, String> e : sitesMap.entrySet()) {
                    if (workshops.equals(e.getValue())) {
                        sitesId = e.getKey();
                    }
                }
                brigades.setSites(sitesId);
                brigadesDAO.update(brigades);
                break;
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    @Override
    public void addListener(Observer observer) {

    }

    @Override
    public void removeListener(Observer observer) {

    }
}
