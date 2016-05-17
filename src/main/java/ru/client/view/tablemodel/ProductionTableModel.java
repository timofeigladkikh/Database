package ru.client.view.tablemodel;

import ru.client.model.Production;
import ru.client.model.ProductionDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Map;

public class ProductionTableModel implements TableModel{
    private List<Production> productionList;
    private final ProductionDAO productionDAO;
    private Map<Integer, String> productsIdMap;
    private Map<Integer, String> brigadesMap;

    public ProductionTableModel(List<Production> productionList, ProductionDAO productionDAO, Map<Integer, String> productsIdMap,
                                Map<Integer, String> brigadesMap) {
        this.productionList = productionList;
        this.productionDAO = productionDAO;
        this.productsIdMap = productsIdMap;
        this.brigadesMap = brigadesMap;
    }

    @Override
    public int getRowCount() {
        return productionList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "id Продукта";
            case 2:
                return "Начало сборки";
            case 3:
                return "Конец сборки";
            case 4:
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
            case 3:
                return String.class;
            case 4:
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
                return true;
            default:
                return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Production production = productionList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return production.getId();
            case 1:
                int productsId = production.getProductsId();
                return productsIdMap.get(productsId);
            case 2:
                return production.getStartOfWork();
            case 3:
                return production.getEndOfWork();
            case 4:
                int brigadeId = production.getBrigadesId();
                return brigadesMap.get(brigadeId);
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Production production = productionList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String product = (String) aValue;
                int productId = 0;
                for (Map.Entry<Integer, String> e : productsIdMap.entrySet()) {
                    if (product.equals(e.getValue())) {
                        productId = e.getKey();
                    }
                }
                production.setId(productId);
                productionDAO.update(production);
                break;
            case 2:
                String start = (String) aValue;
                production.setStartOfWork(start);
                productionDAO.update(production);
                break;
            case 3:
                String end = (String) aValue;
                production.setEndOfWork(end);
                productionDAO.update(production);
                break;
            case 4:
                String brigade = (String) aValue;
                int brigadeId = 0;
                for (Map.Entry<Integer, String> e : brigadesMap.entrySet()) {
                    if (brigade.equals(e.getValue())) {
                        brigadeId = e.getKey();
                    }
                }
                production.setId(brigadeId);
                productionDAO.update(production);
                break;
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {}

    @Override
    public void removeTableModelListener(TableModelListener l) {}
}
