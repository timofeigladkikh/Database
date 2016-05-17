package ru.client.model;

import java.util.Date;

public class Production {
    private int id;
    private int productsId;
    private String startOfWork;
    private String endOfWork;
    private int brigadesId;

    public Production() {
    }

    public Production(int productsId, String startOfWork, String endOfWork, int brigadesId) {
        this.productsId = productsId;
        this.startOfWork = startOfWork;
        this.endOfWork = endOfWork;
        this.brigadesId = brigadesId;
    }

    public Production(int id, int productsId, String startOfWork, String endOfWork, int brigadesId) {
        this.id = id;
        this.productsId = productsId;
        this.startOfWork = startOfWork;
        this.endOfWork = endOfWork;
        this.brigadesId = brigadesId;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Production production = new Production(this.productsId, this.startOfWork, this.endOfWork, this.brigadesId);
        return production;
    }

    @Override
    public String toString() {
        return "Production{" +
                "productsId=" + productsId +
                ", startOfWork=" + startOfWork +
                ", endOfWork=" + endOfWork +
                ", brigadesId=" + brigadesId +
                '}';
    }

    public int getProductsId() {
        return productsId;
    }

    public void setProductsId(int productsId) {
        this.productsId = productsId;
    }

    public String getStartOfWork() {
        return startOfWork;
    }

    public void setStartOfWork(String startOfWork) {
        this.startOfWork = startOfWork;
    }

    public String getEndOfWork() {
        return endOfWork;
    }

    public void setEndOfWork(String endOfWork) {
        this.endOfWork = endOfWork;
    }

    public int getBrigadesId() {
        return brigadesId;
    }

    public void setBrigadesId(int brigadesId) {
        this.brigadesId = brigadesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
