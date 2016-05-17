package ru.client.model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class ProductionDAO implements DAO<Production, Integer> {
    private static final String TABLE_NAME = "PRODUCTION";
    private final Sql2o sql2o;

    public ProductionDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(Production entity) {
        String sql = "INSERT INTO " + TABLE_NAME +
                "(products_id, start_of_work, end_of_work, brigade) " +
                " VALUES(:products_id, startOfWork, endOfWork, brigades_id )";

        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql, true)
                    .addParameter("products_id", entity.getProductsId())
                    .addParameter("startOfWork", entity.getStartOfWork())
                    .addParameter("endOfWork", entity.getEndOfWork())
                    .addParameter("brigades_id", entity.getBrigadesId())
                    .executeUpdate();
        }
    }

    @Override
    public Production getById(Integer id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=:id";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetchFirst(Production.class);
        }
    }

    @Override
    public List<Production> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(Production.class);
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
    public boolean update(Production entity) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET products_id = :p1 " +
                " START_OF_WORK = :p2" +
                " END_OF_WORK = :p3" +
                " BRIGADE = :p4" +
                " WHERE id = :p5 ";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("p1", entity.getProductsId())
                    .addParameter("p2", entity.getStartOfWork())
                    .addParameter("p3", entity.getEndOfWork())
                    .addParameter("p4", entity.getBrigadesId())
                    .addParameter("p5", entity.getId())
                    .executeUpdate();
            return true;
        }
    }
}
