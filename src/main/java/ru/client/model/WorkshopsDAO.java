package ru.client.model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class WorkshopsDAO implements DAO <Workshops, Integer> {
    private static final String TABLE_NAME = "WORKSHOPS";
    private final Sql2o sql2o;

    public WorkshopsDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(Workshops entity) {
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
    public Workshops getById(Integer id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=:id";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetchFirst(Workshops.class);
        }
    }

    @Override
    public List<Workshops> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(Workshops.class);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM " + TABLE_NAME +
                " WHERE id = :p1";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("p1", id)
                    .executeUpdate();
            return true;
        }
    }

    @Override
    public boolean update(Workshops entity) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET name = :p1 " +
                " WHERE id = :p2 ";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("p1", entity.getName())
                    .addParameter("p2", entity.getId())
                    .executeUpdate();
            return true;
        }
    }
}
