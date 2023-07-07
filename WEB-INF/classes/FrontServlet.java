package etu1851.framework.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etu1851.framework.Mapping;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import utilitaire.Utilitaire;
import java.io.PrintWriter;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>EH</title>");
            out.println("</head>");
            out.println("<body>");

            Utilitaire utilitaire = new Utilitaire();
            String annotation = utilitaire.getAnnotation(request.getRequestURL().toString());
            out.print(annotation);
        }

        catch (Exception e) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
