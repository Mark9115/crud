package tests;

import crud.controller.WorkersController;
import crud.model.DAOWorkers;
import crud.model.objects.Workers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AllTheTests extends Mockito {

    @Mock
    DAOWorkers dao;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher requestDispatcher;

    @Test
    public void givenIdProjectAndIdPositions_WhenCreatingChainOfWorker_ThenWaitingForAnswer() throws IOException, ServletException {
        String name = "John";
        String last_name = "Johnson";
        String id_position = "1";
        String id_project = "1";
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("last_name")).thenReturn(last_name);
        when(request.getParameter("id_position")).thenReturn(id_position);
        when(request.getParameter("id_project")).thenReturn(id_project);
        when(request.getServletPath()).thenReturn("/newWithProject");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        new WorkersController().doPost(request, response);

        String result = sw.getBuffer().toString().trim();
        assertEquals("Success", result);
    }

    @Test
    public void givenEditForm_WhenCreatingPositions_ThenReceiveStatus() throws ServletException, IOException {
        String id = "9999999";

        when(request.getParameter("id")).thenReturn(id);

        when(request.getServletPath()).thenReturn("/edit");
        when(request.getRequestDispatcher("editForm.jsp")).thenReturn(requestDispatcher);


        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);
        new WorkersController().doGet(request, response);

        verify(requestDispatcher).forward(request, response);
        String result = sw.getBuffer().toString().trim();
        assertEquals("Success", result);
    }

    @Test
    public void givenTheFormWithProject_WhenCreatingListProjectAndPositionsInServletMethod_ThenWaitingForAnswer() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/newWithProjectForm");
        when(request.getRequestDispatcher("/newWithProjectForm.jsp")).thenReturn(requestDispatcher);


        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);
        new WorkersController().doGet(request, response);

        verify(requestDispatcher).forward(request, response);
        String result = sw.getBuffer().toString().trim();
        assertEquals("Success", result);
    }

    @Test
    public void givenTheForm_WhenCreatingListInServletMethod_ThenWaitingForAnswer() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/new");
        when(request.getRequestDispatcher("/newForm.jsp")).thenReturn(requestDispatcher);


        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);
        new WorkersController().doGet(request, response);

        verify(requestDispatcher).forward(request, response);
        String result = sw.getBuffer().toString().trim();
        assertEquals("Success", result);
    }

    @Test
    public void givenTheWorkerToDelete_WhenDeletingWorkerWeWantById_ThenWaitingForSuccessLine() throws IOException, ServletException {
        String id = "9999999";

        when(request.getParameter("id")).thenReturn(id);
        when(request.getServletPath()).thenReturn("/delete");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        new WorkersController().doPost(request, response);

        String result = sw.getBuffer().toString().trim();
        assertEquals("Success", result);
    }

    @Test
    public void givenTheWorkerToUpdate_WhenUpdatingWorkerWeWantById_ThenWaitingForSuccessLine() throws IOException, ServletException {
        String id = "9999999";
        String name = "John";
        String last_name = "Johnson";
        String id_position = "1";
        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("last_name")).thenReturn(last_name);
        when(request.getParameter("id_position")).thenReturn(id_position);
        when(request.getServletPath()).thenReturn("/update");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        new WorkersController().doPost(request, response);

        String result = sw.getBuffer().toString().trim();
        assertEquals("Success", result);
    }

    @Test
    public void givenMainPage_WhenFindAllWorkers_ThenMakeSureThatTryBlockPassed() throws SQLException, ServletException, IOException {

        List<Workers> list = new ArrayList<>();
        Workers firstWorker = new Workers(1, 1, "John", "Johnson");
        Workers secondWorker = new Workers(2, 2, "Name", "LastName");
        Workers thirdWorker = new Workers(3, 3, "Waugh", "Alie");

        list.add(firstWorker);
        list.add(secondWorker);
        list.add(thirdWorker);

        when(dao.findAll()).thenReturn(list);

        List<Workers> empList = dao.findAll();

        when(request.getServletPath()).thenReturn("/main");
        when(request.getRequestDispatcher("main.jsp")).thenReturn(requestDispatcher);
        when(request.getAttribute("list")).thenReturn(empList);


        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);


        new WorkersController().doGet(request, response);

        verify(requestDispatcher).forward(request, response);

        String result = sw.getBuffer().toString().trim();

        assertEquals("Success", result);

    }

    @Test
    public void givenNewWorker_WhenSaveTheWorker_ThenWritersEquals() throws IOException, ServletException {
        String name = "John";
        String last_name = "Johnson";
        String id_position = "1";
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("last_name")).thenReturn(last_name);
        when(request.getParameter("id_position")).thenReturn(id_position);
        when(request.getServletPath()).thenReturn("/insert");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        new WorkersController().doPost(request, response);

        String result = sw.getBuffer().toString().trim();
        assertEquals("Success", result);
    }
}
