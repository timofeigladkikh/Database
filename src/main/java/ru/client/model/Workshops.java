package ru.client.model;

public class Workshops {
    private int id;
    private String name;

    public Workshops() {
    }

    public Workshops(String name) {
        this.name = name;
    }


    public Workshops(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Workshops workshops = new Workshops(this.id, this.name);
        return workshops;
    }

    @Override
    public String toString() {
        return "Workshops{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
