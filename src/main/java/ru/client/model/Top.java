package ru.client.model;

public class Top {
    private int id;
    private String name;

    public Top() {
    }

    public Top(String name) {
        this.name = name;
    }


    public Top(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Top top = new Top(this.id, this.name);
        return top;
    }

    @Override
    public String toString() {
        return "Top{" +
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
