package ru.client.view.tablemodel;

import ru.client.Observable;
import ru.client.Observer;
import ru.client.model.Sites;
import ru.client.model.SitesDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SitesTableModel implements TableModel, Observable {

    private List<Sites> sitesList;
    private final SitesDAO sitesDAO;
    private Map<Integer, String> workshopMap;
    private List<Observer> observers;

    public SitesTableModel(List<Sites> sitesList, SitesDAO sitesDAO, Map<Integer, String> workshopMap) {
        this.sitesList = sitesList;
        this.sitesDAO = sitesDAO;
        this.workshopMap = workshopMap;
        observers = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return sitesList.size();
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
                return "Цех";
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
        Sites sites = sitesList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sites.getId();
            case 1:
                return sites.getName();
            case 2:
                int workshopId = sites.getWorkshops();
                return workshopMap.get(workshopId);
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Sites sites = sitesList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String name = (String) aValue;
                sites.setName(name);
                sitesDAO.update(sites);
                observers.stream().forEach(Observer::update);
                break;
            case 2:
                String workshops = (String) aValue;
                int workshopsId = 0;
                for (Map.Entry<Integer, String> e : workshopMap.entrySet()) {
                    if (workshops.equals(e.getValue())) {
                        workshopsId = e.getKey();
                    }
                }
                sites.setWorkshops(workshopsId);
                sitesDAO.update(sites);
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
