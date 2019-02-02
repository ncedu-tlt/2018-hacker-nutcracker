package Servlets;

import program.Person;
import program.PersonDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ChangePersonServlet")
public class ChangePersonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        PersonDB personDB = PersonDB.getInstance();

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String way = request.getParameter("way");
        String usd = request.getParameter("usd");

        Person person = new Person(Integer.parseInt(id),name,way,Integer.parseInt(usd));

        personDB.updatePersonFromDB(person);

        ArrayList<Person> people = personDB.selectALL();

        request.setAttribute("Persons",people);

        getServletContext().getRequestDispatcher("/showPersons.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
