package ru.client.model;

public class Itp {
    private int id;
    private String surname;
    private String name;
    private int workshop;
    private int site;
    private int post;
    private int is_chief_of_workshop;
    private int is_chief_of_site;

    public Itp() {
    }

    public Itp(String surname, String name, int workshop, int site, int post,
               int is_chief_of_workshop, int is_chief_of_site) {
        this.surname = surname;
        this.name = name;
        this.workshop = workshop;
        this.site = site;
        this.post = post;
        this.is_chief_of_workshop = is_chief_of_workshop;
        this.is_chief_of_site = is_chief_of_site;
    }


    public Itp(int id, String surname, String name, int workshop, int site, int post,
               int is_chief_of_workshop, int is_chief_of_site) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.workshop = workshop;
        this.site = site;
        this.post = post;
        this.is_chief_of_workshop = is_chief_of_workshop;
        this.is_chief_of_site = is_chief_of_site;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getWorkshop() {
        return workshop;
    }

    public void setWorkshop(int workshop) {
        this.workshop = workshop;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getIs_chief_of_workshop() {
        return is_chief_of_workshop;
    }

    public void setIs_chief_of_workshop(int is_chief_of_workshop) {
        this.is_chief_of_workshop = is_chief_of_workshop;
    }

    public int getIs_chief_of_site() {
        return is_chief_of_site;
    }

    public void setIs_chief_of_site(int is_chief_of_site) {
        this.is_chief_of_site = is_chief_of_site;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Itp itp = new Itp(this.id, this.surname, this.name, this.workshop, this.site, this.post,
                this.is_chief_of_workshop, this.is_chief_of_site);
        return itp;
    }

    @Override
    public String toString() {
        return "Itp{" +
                "id=" + id +
                ", surname=" + surname +
                ", name=" + name +
                ", workshop=" + workshop +
                ", site=" + site +
                ", post=" + post +
                ", is_chief_of_workshop=" + is_chief_of_workshop +
                ", is_chief_of_site=" + is_chief_of_site +
                '}';
    }
}
