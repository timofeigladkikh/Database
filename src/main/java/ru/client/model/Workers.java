package ru.client.model;

public class Workers {
    private int id;
    private String surname;
    private String name;
    private int brigade;
    private int is_brigadier;
    private int post;

    public Workers() {
    }

    public Workers(String surname, String name, int brigade, int is_brigadier, int post) {
        this.surname = surname;
        this.name = name;
        this.brigade = brigade;
        this.is_brigadier = is_brigadier;
        this.post = post;
    }

    public Workers(int id, String surname, String name, int brigade, int is_brigadier, int post) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.brigade = brigade;
        this.is_brigadier = is_brigadier;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBrigade() {
        return brigade;
    }

    public void setBrigade(int brigade) {
        this.brigade = brigade;
    }

    public int getIs_brigadier() {
        return is_brigadier;
    }

    public void setIs_brigadier(int is_brigadier) {
        this.is_brigadier = is_brigadier;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Workers workers = new Workers(this.id, this.surname, this.name, this.brigade, this.is_brigadier, this.post);
        return workers;
    }

    @Override
    public String toString() {
        return "Workers{" +
                "id=" + id +
                ", surname=" + surname +
                ", name=" + name +
                ", brigade=" + brigade +
                ", is_brigadier=" + is_brigadier +
                ", post=" + post +
                '}';
    }
}
