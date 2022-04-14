package crud.controller;

import crud.entity.Positions;
import crud.entity.Projects;
import crud.entity.Workers;
import crud.DAO.impl.PositionsDAOImpl;
import crud.DAO.impl.ProjectsDAOImpl;
import crud.DAO.impl.WorkersDAOImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Single main controller
 */
@WebServlet({"/", "/new", "/edit", "/insert", "/delete", "/update", "/main", "/newWithProject", "/newWithProjectForm"})
public class WorkersController extends HttpServlet {
    WorkersDAOImpl workersDAOImpl = new WorkersDAOImpl();
    PositionsDAOImpl positionsDAOImpl = new PositionsDAOImpl();
    ProjectsDAOImpl projectsDAOImpl = new ProjectsDAOImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * All requests work with doGet method
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();
        switch (action) {
            case "/":
                index(request, response);
                break;
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
                editForm(request, response);
                break;
            case "/delete":
                deleteWorker(request, response);
                break;
            case "/insert":
                insertWorker(request, response);
                break;
            case "/update":
                updateWorker(request, response);
                break;
            case "/main":
                openMainPage(request, response);
                break;
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("resources/views/index.jsp");
        requestDispatcher.forward(request, response);
        response.getWriter().write("Success");
    }

    /**
     * Connects projects with workers
     */
    private void addToProjectsToUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String last_name = request.getParameter("last_name");
        int id_position = Integer.parseInt(request.getParameter("id_position"));
        int id_project = Integer.parseInt(request.getParameter("id_project"));

        Positions position = new Positions();
        position.setId(id_position);

        Projects project = new Projects();
        project.setId(id_project);

        Workers worker = new Workers(position, name, last_name);

        workersDAOImpl.saveReturnVal(worker, project);

        response.getWriter().write("Success");
        response.sendRedirect("main");

    }

    /**
     * Creates a form, inserts information in the inputs from DB
     */
    private void editForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        Workers worker = workersDAOImpl.find(id);

        List<Positions> positionsList = positionsDAOImpl.findAll();
        assert positionsList != null;
        Positions firstPosition = new Positions();
        Iterator<Positions> iterator = positionsList.iterator();
        while (iterator.hasNext()) {
            Positions pos = iterator.next();
            if (pos.getId() == worker.getPosition().getId()) {
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
        request.setAttribute("id_position", worker.getPosition().getId());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("resources/views/editForm.jsp");
        requestDispatcher.forward(request, response);
        response.getWriter().write("Success");

    }

    /**
     * Creates a form by dint of you can add a worker with a project
     */
    private void openNewFormWithProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Projects> projectsList = projectsDAOImpl.findAll();
        List<Positions> positionsList = positionsDAOImpl.findAll();
        request.setAttribute("positionsList", positionsList);
        request.setAttribute("projectsList", projectsList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("resources/views/newWithProjectForm.jsp");
        requestDispatcher.forward(request, response);
        response.getWriter().write("Success");
    }

    /**
     * Creates a form by dint of you can add a worker
     */
    private void openNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Positions> positionsList = positionsDAOImpl.findAll();
        request.setAttribute("positionsList", positionsList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("resources/views/newForm.jsp");
        requestDispatcher.forward(request, response);
        response.getWriter().write("Success");
    }

    /**
     * Deletes worker by id
     */
    private void deleteWorker(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Workers worker = new Workers();
        worker.setId(id);
        workersDAOImpl.delete(worker);

        response.getWriter().write("Success");
        response.sendRedirect("main");
    }

    /**
     * Updates worker by id
     */
    private void updateWorker(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String last_name = request.getParameter("last_name");
        Positions position = new Positions();
        position.setId(Integer.parseInt(request.getParameter("id_position")));

        Workers worker = new Workers(id, position, name, last_name);
        workersDAOImpl.update(worker);

        response.getWriter().write("Success");
        response.sendRedirect("main");
    }

    /**
     * Creates the way to main table with all users
     */
    private void openMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Workers> list = workersDAOImpl.findAll();
        request.setAttribute("list", list);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("resources/views/main.jsp");
        requestDispatcher.forward(request, response);
        response.getWriter().write("Success");
    }

    /**
     * Adds new Worker in DB
     */
    private void insertWorker(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String last_name = request.getParameter("last_name");
        Positions position = new Positions();
        position.setId(Integer.parseInt(request.getParameter("id_position")));

        workersDAOImpl.save(new Workers(position,name,last_name));

        response.getWriter().write("Success");
        response.sendRedirect("main");
    }
}
