package ru.client.model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Workshops_TopDAO implements DAO<Workshops_Top, Integer>{
    private static final String TABLE_NAME = "WORKSHOPS_TYPES";
    private final Sql2o sql2o;

    public Workshops_TopDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(Workshops_Top entity) {
        String sql = "INSERT INTO " + TABLE_NAME +
                "(workshops_id, types_id) " +
                " VALUES(:workshopsId, :typesId)";

        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql, true)
                    .addParameter("workshopsId", entity.getWorkshopsId())
                    .addParameter("typesId", entity.getTypesId())
                    .executeUpdate();
        }
    }

    @Override
    public Workshops_Top getById(Integer id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = :id";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetchFirst(Workshops_Top.class);
        }
    }

    @Override
    public List<Workshops_Top> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(Workshops_Top.class);
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
    public boolean update(Workshops_Top entity) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET workshops_id = :p1 ," +
                " types_id = :p2 ";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("p1", entity.getWorkshopsId())
                    .addParameter("p2", entity.getTypesId())
                    .executeUpdate();
            return true;
        }
    }
}
