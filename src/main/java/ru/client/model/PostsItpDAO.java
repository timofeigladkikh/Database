package ru.client.model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class PostsItpDAO implements DAO <PostsItp, Integer>  {
    private static final String TABLE_NAME = "POSTS_ITP";
    private final Sql2o sql2o;

    public PostsItpDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(PostsItp entity) {
        String sql = "INSERT INTO " + TABLE_NAME +
                "(name) " +
                " VALUES(:name)";

        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql, true)
                    .addParameter("name", entity.getName())
                    .executeUpdate();
        }
    }

    @Override
    public PostsItp getById(Integer id) {
        String sql = "SELECT * FROM " + TABLE_NAME + "WHERE id=:id";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .addColumnMapping("name", "name")
                    .executeAndFetchFirst(PostsItp.class);
        }
    }

    @Override
    public List<PostsItp> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(PostsItp.class);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM " + TABLE_NAME + " " +
                "WHERE id = :p1";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("p1", id)
                    .executeUpdate();
            return true;
        }
    }

    @Override
    public boolean update(PostsItp entity) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET name = :p1 " +
                "WHERE id = :p2 ";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("p1", entity.getName())
                    .addParameter("p2", entity.getId())
                    .executeUpdate();
            return true;
        }
    }
}
