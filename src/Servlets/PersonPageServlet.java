package Servlets;

import program.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PersonPageServlet")
public class PersonPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person(Integer.parseInt(request.getParameter("id")),
                request.getParameter("name"),request.getParameter("way"),Integer.parseInt(request.getParameter("usd")));

        request.setAttribute("Person", person);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("personPage.jsp");
        requestDispatcher.forward(request, response);
    }
}
