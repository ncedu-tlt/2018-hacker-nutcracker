package Servlets;

import program.Person;
import program.PersonDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ShowSpecificServlet")
public class ShowSpecificServlet extends HttpServlet {
    PersonDB persondb = PersonDB.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Person person = persondb.findPerson(request.getParameter("id"));

        request.setAttribute("Person", person);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("personPage.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
