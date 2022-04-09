package crud.model;

import crud.model.DAO.PositionsDAO;
import crud.model.objects.Positions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOPositions implements PositionsDAO {
    @Override
    public Positions find(String s) throws SQLException {
        return null;
    }

    @Override
    public List<Positions> findAll() throws SQLException {
        String name_position;
        int id;

        List<Positions> list = new ArrayList<>();

        String sql = "SELECT * FROM users.users.positions";

        Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet rows = statement.executeQuery(sql);

        while (rows.next()) {
            id = rows.getInt("id");
            name_position = rows.getString("name_position");

            Positions positions = new Positions(id,name_position);
            list.add(positions);
        }
        rows.close();
        statement.close();
        connection.close();

        return list;
    }

    @Override
    public boolean save(Positions o) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Positions o) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Positions o) throws SQLException {
        return false;
    }

    private Connection connect() throws SQLException {
        try {
            Class.forName(DbUtil.driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        return DriverManager.getConnection(DbUtil.url, DbUtil.user, DbUtil.password);
    }
}
