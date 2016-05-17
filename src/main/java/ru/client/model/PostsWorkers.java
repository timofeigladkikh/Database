package ru.client.model;

public class PostsWorkers {
    private int id;
    private String name;

    public PostsWorkers() {
    }

    public PostsWorkers(String name) {
        this.name = name;
    }


    public PostsWorkers(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        PostsWorkers postsWorkers = new PostsWorkers(this.id, this.name);
        return postsWorkers;
    }

    @Override
    public String toString() {
        return "PostsWorkers{" +
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
