package ru.client.model;

public class Sites {
    private int id;
    private String name;
    private  int workshops;

    public Sites() {
    }

    public Sites(String name, int workshops) {
        this.name = name;
        this.workshops = workshops;
    }


    public Sites(int id, String name, int workshops) {
        this.id = id;
        this.name = name;
        this.workshops = workshops;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Sites sites = new Sites(this.id, this.name, this.workshops);
        return sites;
    }

    @Override
    public String toString() {
        return "Sites{" +
                "id=" + id +
                ", name=" + name +
                ", workshops=" + workshops +
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

    public int getWorkshops() {
        return workshops;
    }

    public void setWorkshops(int workshops) {
        this.workshops = workshops;
    }
}
