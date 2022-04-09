package crud.controller;

import crud.model.objects.Positions;
import crud.model.objects.Projects;
import crud.model.objects.Workers;
import crud.model.DAOPositions;
import crud.model.DAOProjects;
import crud.model.DAOWorkers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@WebServlet({"/", "/new", "/edit", "/insert", "/delete", "/update", "/main", "/newWithProject", "/newWithProjectForm"})
public class WorkersController extends HttpServlet {
    DAOWorkers daoWorkers = new DAOWorkers();
    DAOPositions daoPositions = new DAOPositions();
    DAOProjects daoProjects = new DAOProjects();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();
        switch (action) {
            case "/new":
                openNewForm(request, response);
                break;
            case "/newWithProjectForm":
                openNewFormWithProject(request, response);
                break;
            case "/newWithProject":
                addToProjectsToUsers(request, response);
                break;
            case "/edit":
                try {
                    editForm(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/delete":
                try {
                    deleteWorker(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/insert":
                try {
                    insertWorker(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/update":
                try {
                    updateWorker(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/main":
                openMainPage(request, response);
                break;

        }

    }

    private void addToProjectsToUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String last_name = request.getParameter("last_name");
        int id_position = Integer.parseInt(request.getParameter("id_position"));
        int id_project = Integer.parseInt(request.getParameter("id_project"));

        Projects project = new Projects();
        project.setId(id_project);
        Workers worker = new Workers(id_position, name, last_name);

        try {
            daoWorkers.saveReturnVal(worker, project);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("main");

    }

    private void editForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Workers worker = daoWorkers.find(String.valueOf(id));

        List<Positions> positionsList = null;
        try {
            positionsList = daoPositions.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assert positionsList != null;
        Positions firstPosition = new Positions();
        Iterator<Positions> iterator = positionsList.iterator();
        while (iterator.hasNext()) {
            Positions pos = iterator.next();
            if (pos.getId() == worker.getId_position()) {
                firstPosition.setName_position(pos.getName_position());
                firstPosition.setId(pos.getId());
                iterator.remove();
            }
        }

        positionsList.add(0, firstPosition);

        request.setAttribute("positionsList", positionsList);

        request.setAttribute("id", worker.getId());
        request.setAttribute("name", worker.getName().trim());
        request.setAttribute("last_name", worker.getLast_name().trim());
        request.setAttribute("id_position", worker.getId_position());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("editForm.jsp");
        requestDispatcher.forward(request, response);
    }

    private void openNewFormWithProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Projects> projectsList = null;
        List<Positions> positionsList = null;
        try {
            projectsList = daoProjects.findAll();
            positionsList = daoPositions.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        request.setAttribute("positionsList", positionsList);
        request.setAttribute("projectsList", projectsList);

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/newWithProjectForm.jsp");
        requestDispatcher.forward(request, response);
    }

    private void openNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Positions> positionsList = null;
        try {
            positionsList = daoPositions.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("positionsList", positionsList);

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/newForm.jsp");
        requestDispatcher.forward(request, response);
    }

    private void deleteWorker(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Workers worker = new Workers(id);
        daoWorkers.delete(worker);
        response.sendRedirect("main");
    }

    private void updateWorker(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String last_name = request.getParameter("last_name");
        int id_position = Integer.parseInt(request.getParameter("id_position"));

        Workers worker = new Workers(id, id_position, name, last_name);
        daoWorkers.update(worker);
        response.sendRedirect("main");
    }


    private void openMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Workers> list = daoWorkers.findAll();
            request.setAttribute("list", list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("main.jsp");
        requestDispatcher.forward(request, response);
    }

    private void insertWorker(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String name = request.getParameter("name");
        String last_name = request.getParameter("last_name");
        int id_position = Integer.parseInt(request.getParameter("id_position"));

        Workers worker = new Workers(0, id_position, name, last_name);
        daoWorkers.save(worker);

        response.sendRedirect("main");
    }
}
