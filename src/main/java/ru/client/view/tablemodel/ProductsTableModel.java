package ru.client.view.tablemodel;

import ru.client.Observable;
import ru.client.Observer;
import ru.client.model.Products;
import ru.client.model.ProductsDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductsTableModel implements TableModel, Observable {
    private List<Products> productsList;
    private final ProductsDAO productsDAO;
    private Map<Integer, String> typesMap;
    private List<Observer> observers;

    public ProductsTableModel(List<Products> productsList, ProductsDAO productsDAO, Map<Integer, String> typesMap) {
        this.productsList = productsList;
        this.productsDAO = productsDAO;
        this.typesMap = typesMap;
        observers = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return productsList.size();
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
                return "Вид";
            case 2:
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
        Products products = productsList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return products.getId();
            case 1:
                int typesId = products.getType();
                return typesMap.get(typesId);
            case 2:
                return products.getName();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Products products = productsList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String type = (String) aValue;
                int typeId = 0;
                for (Map.Entry<Integer, String> e : typesMap.entrySet()) {
                    if (type.equals(e.getValue())) {
                        typeId = e.getKey();
                    }
                }
                products.setType(typeId);
                productsDAO.update(products);
                break;
            case 2:
                String name = (String) aValue;
                products.setName(name);
                productsDAO.update(products);
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

    }

    @Override
    public void removeListener(Observer observer) {

    }
}
