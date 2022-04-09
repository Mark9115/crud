package crud.model;

import crud.model.DAO.WorkersDAO;
import crud.model.objects.Projects;
import crud.model.objects.Workers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOWorkers implements WorkersDAO {
    @Override
    public Workers find(String id) throws SQLException {
        String name = "", last_name = "";
        int id_worker = 0, id_position = 0;

        String sql = "SELECT * FROM users.users.workers WHERE id=?";
        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, Integer.parseInt(id));

        ResultSet rows = statement.executeQuery();

        if (rows.next()) {
            id_worker = rows.getInt("id");
            name = rows.getString("name");
            last_name = rows.getString("last_name");
            id_position = rows.getInt("id_position");
        }

        rows.close();
        statement.close();
        connection.close();

        return new Workers(id_worker, id_position, name, last_name);
    }

    @Override
    public List<Workers> findAll() throws SQLException {
        String name, last_name, name_position;
        int id_worker, id_position;

        List<Workers> list = new ArrayList<>();

        String sql = "SELECT * FROM users.users.workers LEFT JOIN users.users.positions p ON p.id = workers.id_position ORDER BY workers.id";

        Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet rows = statement.executeQuery(sql);

        while (rows.next()) {
            id_worker = rows.getInt("id");
            name = rows.getString("name");
            last_name = rows.getString("last_name");
            id_position = rows.getInt("id_position");
            name_position = rows.getString("name_position");

            Workers worker = new Workers(id_worker, id_position, name_position, name, last_name);
            list.add(worker);
        }
        rows.close();
        statement.close();
        connection.close();

        return list;
    }

    @Override
    public boolean save(Workers o) throws SQLException {

        String sql = "INSERT INTO users.users.workers (name, last_name, id_position)  VALUES (?, ?, ?)";

        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, o.getName());
        statement.setString(2, o.getLast_name());
        statement.setInt(3, o.getId_position());

        boolean updated = statement.executeUpdate() > 0;

        statement.close();
        connection.close();

        return updated;
    }

    public void saveReturnVal(Workers worker, Projects project) throws SQLException {
        Connection connection = connect();
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql = "INSERT INTO users.users.workers (name, last_name, id_position)  VALUES (?, ?, ?) RETURNING id";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, worker.getName());
            statement.setString(2, worker.getLast_name());
            statement.setInt(3, worker.getId_position());

            int rowsAffected = statement.executeUpdate();
            long id_user;
            if (rowsAffected > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        id_user = rs.getLong(1);
                        worker.setId((int) id_user);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            sql = "INSERT INTO users.users.projects_to_workers (id_project, id_user)  VALUES  (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, project.getId());
            statement.setInt(2, worker.getId());

            statement.executeUpdate();

            statement.close();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        } finally {
            connection.close();
        }

    }

    @Override
    public boolean update(Workers o) throws SQLException {

        String sql = "UPDATE users.users.workers SET name = ?, last_name = ?, id_position =?  WHERE id = ?";

        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, o.getName());
        statement.setString(2, o.getLast_name());
        statement.setInt(3, o.getId_position());
        statement.setInt(4, o.getId());

        boolean updated = statement.executeUpdate() > 0;

        statement.close();
        connection.close();

        return updated;
    }

    @Override
    public boolean delete(Workers o) throws SQLException {
        String sql = "DELETE FROM users.users.workers WHERE id = ?";

        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, o.getId());

        boolean updated = statement.executeUpdate() > 0;

        statement.close();
        connection.close();

        return updated;
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
