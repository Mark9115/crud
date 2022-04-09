package crud.model;

import crud.model.DAO.ProjectsToUsersDAO;
import crud.model.objects.ProjectsToUsers;

import java.sql.*;
import java.util.List;

public class DAOProjectsToUsers implements ProjectsToUsersDAO {
    @Override
    public ProjectsToUsers find(String s) throws SQLException {
        return null;
    }

    @Override
    public List<ProjectsToUsers> findAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(ProjectsToUsers o) throws SQLException {
        Connection connection = connect();

        String sql = "INSERT INTO users.users.projects_to_workers (id_project, id_user)  VALUES  (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, o.getId_project());
        statement.setInt(2, o.getId_user());

        boolean updated = statement.executeUpdate() > 0;

        statement.close();
        connection.close();

        return updated;
    }

    @Override
    public boolean update(ProjectsToUsers o) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(ProjectsToUsers o) throws SQLException {
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
