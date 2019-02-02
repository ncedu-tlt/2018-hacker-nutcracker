package Servlets;

import program.Person;
import program.PersonDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {
    PersonDB persondb = PersonDB.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PersonDB personDB = PersonDB.getInstance();

        personDB.deletePerson(request.getParameter("id"));

        ArrayList<Person> people = persondb.selectALL();

        request.setAttribute("Persons",people);

        getServletContext().getRequestDispatcher("/showPersons.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
