package ru.client.model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class BrigadesDAO implements DAO<Brigades, Integer> {
    private static final String TABLE_NAME = "BRIGADES";
    private final Sql2o sql2o;

    public BrigadesDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(Brigades entity) {
        String sql = "INSERT INTO " + TABLE_NAME +
                "(name, sites_id) " +
                " VALUES(:name, :site)";

        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql, true)
                    .addParameter("name", entity.getName())
                    .addParameter("site", entity.getSites())
                    .executeUpdate();
        }
    }

    @Override
    public Brigades getById(Integer id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = :id";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetchFirst(Brigades.class);
        }
    }

    @Override
    public List<Brigades> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addColumnMapping("sites_id", "sites")
                    .executeAndFetch(Brigades.class);
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
    public boolean update(Brigades entity) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET name = :p1 ," +
                " workshops_id = :p2 " +
                " WHERE id = :p3 ";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("p1", entity.getName())
                    .addParameter("p2", entity.getSites())
                    .addParameter("p3", entity.getId())
                    .executeUpdate();
            return true;
        }
    }
}
