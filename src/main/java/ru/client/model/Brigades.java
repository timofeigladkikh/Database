package ru.client.model;

public class Brigades {
    private int id;
    private String name;
    private int sites;

    public Brigades() {
    }

    public Brigades(String name, int workshops) {
        this.name = name;
        this.sites = workshops;
    }


    public Brigades(int id, String name, int sites) {
        this.id = id;
        this.name = name;
        this.sites = sites;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Sites sites = new Sites(this.id, this.name, this.sites);
        return sites;
    }

    @Override
    public String toString() {
        return "Sites{" +
                "id=" + id +
                ", name=" + name +
                ", sites=" + sites +
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

    public int getSites() {
        return sites;
    }

    public void setSites(int sites) {
        this.sites = sites;
    }
}
