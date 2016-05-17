package ru.client.view.tablemodel;

import ru.client.model.Workers;
import ru.client.model.WorkersDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Map;

public class WorkersTableModel implements TableModel {
    private List<Workers> workersList;
    private final WorkersDAO workersDAO;
    private Map<Integer, String> brigadesMap;
    private Map<Integer, String> postsWorkersMap;

    public WorkersTableModel(List<Workers> workersList, WorkersDAO workersDAO, Map<Integer, String> brigadesMap,
                             Map<Integer, String> postsWorkersMap) {
        this.workersList = workersList;
        this.workersDAO = workersDAO;
        this.brigadesMap = brigadesMap;
        this.postsWorkersMap = postsWorkersMap;
    }

    @Override
    public int getRowCount() {
        return workersList.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
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
                return "Бригада";
            case 4:
                return "Бригадир ли";
            case 5:
                return "Должность";
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
                return Integer.class;
            case 5:
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
            case 3:
            case 4:
            case 5:
                return true;
            default:
                return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Workers workers = workersList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return workers.getId();
            case 1:
                return workers.getSurname();
            case 2:
                return workers.getName();
            case 3:
                int brigadeId = workers.getBrigade();
                return brigadesMap.get(brigadeId);
            case 4:
                return workers.getIs_brigadier();
            case 5:
                int postId = workers.getPost();
                return postsWorkersMap.get(postId);
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Workers workers = workersList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String surname = (String) aValue;
                workers.setSurname(surname);
                workersDAO.update(workers);
                break;
            case 2:
                String name = (String) aValue;
                workers.setName(name);
                workersDAO.update(workers);
                break;
            case 3:
                String brigade = (String) aValue;
                int brigadeId = 0;
                for (Map.Entry<Integer, String> e : brigadesMap.entrySet()) {
                    if (brigade.equals(e.getValue())) {
                        brigadeId = e.getKey();
                    }
                }
                workers.setBrigade(brigadeId);
                workersDAO.update(workers);
                break;
            case 4:
                int isBrigadier = (int) aValue;
                workers.setIs_brigadier(isBrigadier);
                workersDAO.update(workers);
                break;
            case 5:
                String post = (String) aValue;
                int postId = 0;
                for (Map.Entry<Integer, String> e : postsWorkersMap.entrySet()) {
                    if (post.equals(e.getValue())) {
                        postId = e.getKey();
                    }
                }
                workers.setPost(postId);
                workersDAO.update(workers);
                break;
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {}

    @Override
    public void removeTableModelListener(TableModelListener l) {}
}
