package ru.client.model;

public class PostsItp {
    private int id;
    private String name;

    public PostsItp() {
    }

    public PostsItp(String name) {
        this.name = name;
    }


    public PostsItp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        PostsItp postsItp = new PostsItp(this.id, this.name);
        return postsItp;
    }

    @Override
    public String toString() {
        return "PostsItp{" +
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

