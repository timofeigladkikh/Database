package ru.client.model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class SitesDAO implements DAO<Sites, Integer> {
    private static final String TABLE_NAME = "SITES";
    private final Sql2o sql2o;

    public SitesDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(Sites entity) {
        String sql = "INSERT INTO " + TABLE_NAME +
                "(name, workshops_id) " +
                " VALUES(:name, :workshop)";

        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql, true)
                    .addParameter("name", entity.getName())
                    .addParameter("workshop", entity.getWorkshops())
                    .executeUpdate();
        }
    }

    @Override
    public Sites getById(Integer id) {
        String sql = "SELECT * FROM " + TABLE_NAME + "WHERE id = :id";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .addColumnMapping("name", "name")
                    .addColumnMapping("workshops", "workshops_id")
                    .executeAndFetchFirst(Sites.class);
        }
    }

    @Override
    public List<Sites> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addColumnMapping("workshops_id", "workshops")
                    .executeAndFetch(Sites.class);
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
    public boolean update(Sites entity) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET name              = :p1 ," +
                "    workshops_id                 = :p2 " +
                "WHERE id = :p3 ";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("p1", entity.getName())
                    .addParameter("p2", entity.getWorkshops())
                    .addParameter("p3", entity.getId())
                    .executeUpdate();
            return true;
        }
    }
}
