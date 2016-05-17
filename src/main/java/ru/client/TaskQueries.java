package ru.client;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.client.model.tasks.Task8;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskQueries {

    private final Sql2o sql2o;

    public TaskQueries(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<Task8> task8Query(int id) {
        String sql = "SELECT DISTINCT W.SURNAME, W.NAME, B.NAME " +
                "FROM BRIGADES B, WORKERS W, PRODUCTION P, PRODUCTS PR " +
                "WHERE P.PRODUCTS_ID = ?  " +
                "AND P.PRODUCTS_ID = PR.ID " +
                "AND W.BRIGADE = B.ID " +
                "ORDER BY B.NAME";
        try (Connection connection = sql2o.open()) {
            try {
                PreparedStatement statement = connection.getJdbcConnection().prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                List<Task8> result = new ArrayList<>();
                while (resultSet.next()) {
                    String surname = resultSet.getString(1);
                    String name = resultSet.getString(2);
                    String b = resultSet.getString(3);
                    result.add(new Task8(surname, name, b));
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
