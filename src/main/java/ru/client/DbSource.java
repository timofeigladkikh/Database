package ru.client;

import org.sql2o.Sql2o;

public class DbSource {

    private static Sql2o sql2o;

    static {
        sql2o = new Sql2o("jdbc:oracle:thin:@//10.4.0.119:1521/xe", "gladkikh13204", "gladkikh13204");
    }

    public static Sql2o getSql2o() {
        return sql2o;
    }
}
