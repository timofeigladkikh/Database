package ru.client.model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class WorkersDAO implements DAO<Workers, Integer> {
    private static final String TABLE_NAME = "WORKERS";
    private final Sql2o sql2o;

    public WorkersDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(Workers entity) {
        String sql = "INSERT INTO " + TABLE_NAME +
                "(surname, name, brigade, is_brigadier, post) " +
                " VALUES(:surname, :name, :brigade, :is_brigadier, :post)";

        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql, true)
                    .addParameter("surname", entity.getSurname())
                    .addParameter("name", entity.getName())
                    .addParameter("brigade", entity.getBrigade())
                    .addParameter("is_brigadier", entity.getIs_brigadier())
                    .addParameter("post", entity.getPost())
                    .executeUpdate();
        }
    }

    @Override
    public Workers getById(Integer id) {
        String sql = "SELECT * FROM " + TABLE_NAME +" WHERE id = :id";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Workers.class);
        }
    }

    @Override
    public List<Workers> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(Workers.class);
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
    public boolean update(Workers entity) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET surname              = :p1 ," +
                "    name                 = :p2 ," +
                "    brigade             = :p3 ," +
                "    is_brigadier                 = :p4 ," +
                "    post                 = :p5 " +
                " WHERE id = :p6 ";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("p1", entity.getSurname())
                    .addParameter("p2", entity.getName())
                    .addParameter("p3", entity.getBrigade())
                    .addParameter("p4", entity.getIs_brigadier())
                    .addParameter("p5", entity.getPost())
                    .addParameter("p6", entity.getId())
                    .executeUpdate();
            return true;
        }
    }
}
