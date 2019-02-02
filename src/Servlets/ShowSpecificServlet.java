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

@WebServlet(name = "ShowSpecificServlet")
public class ShowSpecificServlet extends HttpServlet {
    PersonDB persondb = PersonDB.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Person person = persondb.findPerson(request.getParameter("id"));
        PrintWriter printWriter = response.getWriter();


        try {
            printWriter.println("<h1 align=\"center\">Person was finded.</h1>");
            printWriter.println("<p align=\"center\">id: " + person.getId() + "</p>");
            printWriter.println("<p align=\"center\">Name: " + person.getName() + "</p>");
            printWriter.println("<p align=\"center\">way: " + person.getWay() + "</p>");
            printWriter.println("<p align=\"center\">usd: " + person.getUSD() + "</p>");

            printWriter.println("<form action=\"index.jsp\">\n" +
                    "    <button type=\"submit\">Home Page</button>\n" +
                    "</form>");
        }
        finally {
            printWriter.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
