package crud.model;

import crud.model.DAO.ProjectsDAO;
import crud.model.objects.Projects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOProjects implements ProjectsDAO {
    @Override
    public Projects find(String s) throws SQLException {
        return null;
    }

    @Override
    public List<Projects> findAll() throws SQLException {
        String description;
        int id;

        List<Projects> list = new ArrayList<>();

        String sql = "SELECT * FROM users.users.projects";

        Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet rows = statement.executeQuery(sql);

        while (rows.next()) {
            id = rows.getInt("id");
            description = rows.getString("description");

            Projects projects = new Projects(id,description);
            list.add(projects);
        }
        rows.close();
        statement.close();
        connection.close();

        return list;
    }

    @Override
    public boolean save(Projects o) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Projects o) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Projects o) throws SQLException {
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
