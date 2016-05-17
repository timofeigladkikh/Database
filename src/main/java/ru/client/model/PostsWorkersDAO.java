package ru.client.model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class PostsWorkersDAO implements DAO <PostsWorkers, Integer> {
    private static final String TABLE_NAME = "POSTS_WORKERS";
    private final Sql2o sql2o;

    public PostsWorkersDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(PostsWorkers entity) {
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
    public PostsWorkers getById(Integer id) {
        String sql = "SELECT * FROM " + TABLE_NAME + "WHERE id=:id";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(PostsWorkers.class);
        }
    }

    @Override
    public List<PostsWorkers> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(PostsWorkers.class);
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
    public boolean update(PostsWorkers entity) {
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
