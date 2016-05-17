package ru.client.model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class ItpDAO implements DAO<Itp, Integer> {
    private static final String TABLE_NAME = "ITP";
    private final Sql2o sql2o;

    public ItpDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(Itp entity) {
        String sql = "INSERT INTO " + TABLE_NAME +
                "(surname, name, workshop, site, post, is_chief_of_workshop, is_chief_of_site) " +
                " VALUES(:surname, :name, :workshop, :site, :post, :is_chief_of_workshop, :is_chief_of_site)";

        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql, true)
                    .addParameter("surname", entity.getName())
                    .addParameter("name", entity.getName())
                    .addParameter("workshop", entity.getWorkshop())
                    .addParameter("site", entity.getSite())
                    .addParameter("post", entity.getPost())
                    .addParameter("is_chief_of_workshop", entity.getIs_chief_of_workshop())
                    .addParameter("is_chief_of_site", entity.getIs_chief_of_site())
                    .executeUpdate();
        }
    }

    @Override
    public Itp getById(Integer id) {
        String sql = "SELECT * FROM ITP WHERE id = :id";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Itp.class);
        }
    }

    @Override
    public List<Itp> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(Itp.class);
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
    public boolean update(Itp entity) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET surname              = :p1 ," +
                "    name                 = :p2 ," +
                "    workshop             = :p3 ," +
                "    site                 = :p4 ," +
                "    post                 = :p5 ," +
                "    is_chief_of_workshop = :p6 ," +
                "    is_chief_of_site     = :p7 " +
                "WHERE id = :p8 ";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("p1", entity.getSurname())
                    .addParameter("p2", entity.getName())
                    .addParameter("p3", entity.getWorkshop())
                    .addParameter("p4", entity.getSite())
                    .addParameter("p5", entity.getPost())
                    .addParameter("p6", entity.getIs_chief_of_workshop())
                    .addParameter("p7", entity.getIs_chief_of_site())
                    .addParameter("p8", entity.getId())
                    .executeUpdate();
            return true;
        }
    }
}
