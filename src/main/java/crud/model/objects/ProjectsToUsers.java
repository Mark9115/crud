package crud.model.objects;

public class ProjectsToUsers {
    int id;
    int id_project;
    int id_user;

    public ProjectsToUsers() {
    }

    public ProjectsToUsers(int id_project, int id_user) {
        this.id_project = id_project;
        this.id_user = id_user;
    }

    public ProjectsToUsers(int id, int id_project, int id_user) {
        this.id = id;
        this.id_project = id_project;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
