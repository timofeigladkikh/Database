package ru.client.model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class ProductsDAO implements DAO<Products, Integer> {
    private static final String TABLE_NAME = "PRODUCTS";
    private final Sql2o sql2o;

    public ProductsDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(Products entity) {
        String sql = "INSERT INTO " + TABLE_NAME +
                "(type, name) " +
                " VALUES(:name, :type)";

        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql, true)
                    .addParameter("type", entity.getType())
                    .addParameter("name", entity.getName())
                    .executeUpdate();
        }
    }

    @Override
    public Products getById(Integer id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = :id";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Products.class);
        }
    }

    @Override
    public List<Products> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(Products.class);
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
    public boolean update(Products entity) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET type              = :p1 ," +
                "     name                 = :p2 " +
                " WHERE id = :p3 ";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("p1", entity.getType())
                    .addParameter("p2", entity.getName())
                    .addParameter("p3", entity.getId())
                    .executeUpdate();
            return true;
        }
    }
}
