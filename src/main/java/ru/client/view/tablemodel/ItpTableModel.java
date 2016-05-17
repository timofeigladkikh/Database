package ru.client.view.tablemodel;

import ru.client.Observable;
import ru.client.Observer;
import ru.client.model.Itp;
import ru.client.model.ItpDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Map;

public class ItpTableModel implements TableModel {
    private List<Itp> itpList;
    private final ItpDAO itpDAO;
    private Map<Integer, String> workshopMap;
    private Map<Integer, String> sitesMap;
    private Map<Integer, String> postsMap;
    private List<Observer> observers;

    public ItpTableModel(List<Itp> itpList, ItpDAO itpDAO, Map<Integer, String> workshopMap,
                         Map<Integer, String> sitesMap, Map<Integer, String> postsMap) {
        this.itpList = itpList;
        this.itpDAO = itpDAO;
        this.workshopMap = workshopMap;
        this.sitesMap = sitesMap;
        this.postsMap = postsMap;
    }

    @Override
    public int getRowCount() {
        return itpList.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "Фамилия";
            case 2:
                return "Имя";
            case 3:
                return "Цех";
            case 4:
                return "Участок";
            case 5:
                return "Должность";
            case 6:
                return "Начальник ли цеха";
            case 7:
                return "Начальник ли участка";
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
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return Integer.class;
            case 7:
                return Integer.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return true;
            default:
                return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Itp itp = itpList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return itp.getId();
            case 1:
                return itp.getSurname();
            case 2:
                return itp.getName();
            case 3:
                int workshopId = itp.getWorkshop();
                return workshopMap.get(workshopId);
            case 4:
                int siteId = itp.getSite();
                return sitesMap.get(siteId);
            case 5:
                int postId = itp.getPost();
                return postsMap.get(postId);
            case 6:
                return itp.getIs_chief_of_workshop();
            case 7:
                return itp.getIs_chief_of_site();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Itp itp = itpList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String surname = (String) aValue;
                itp.setSurname(surname);
                itpDAO.update(itp);
                break;
            case 2:
                String name = (String) aValue;
                itp.setName(name);
                itpDAO.update(itp);
                break;
            case 3:
                String workshops = (String) aValue;
                int workshopsId = 0;
                for (Map.Entry<Integer, String> e : workshopMap.entrySet()) {
                    if (workshops.equals(e.getValue())) {
                        workshopsId = e.getKey();
                    }
                }
                itp.setWorkshop(workshopsId);
                itpDAO.update(itp);
                break;
            case 4:
                String sites = (String) aValue;
                int sitesId = 0;
                for (Map.Entry<Integer, String> e : sitesMap.entrySet()) {
                    if (sites.equals(e.getValue())) {
                        sitesId = e.getKey();
                    }
                }
                itp.setSite(sitesId);
                itpDAO.update(itp);
                break;
            case 5:
                String post = (String) aValue;
                int postId = 0;
                for (Map.Entry<Integer, String> e : postsMap.entrySet()) {
                    if (post.equals(e.getValue())) {
                        postId = e.getKey();
                    }
                }
                itp.setPost(postId);
                itpDAO.update(itp);
                break;
            case 6:
                int isChiefOfWorkshop = (int) aValue;
                itp.setIs_chief_of_workshop(isChiefOfWorkshop);
                itpDAO.update(itp);
                break;
            case 7:
                int isChiefOfSite = (int) aValue;
                itp.setIs_chief_of_site(isChiefOfSite);
                itpDAO.update(itp);
                break;
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {}

    @Override
    public void removeTableModelListener(TableModelListener l) {}
}
