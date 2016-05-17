package ru.client.model;

public class Products {
    private int id;
    private int type;
    private String name;

    public Products() {
    }

    public Products(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public Products(int id, int type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Products products = new Products(this.id, this.type, this.name);
        return products;
    }

    @Override
    public String toString() {
        return "Products{" +
                " id=" + id +
                ", type=" + type +
                ", name=" + name +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
