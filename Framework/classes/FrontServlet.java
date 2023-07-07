package etu1851.framework.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.AnnotationEmp;
import etu1851.framework.Mapping;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utilitaire.Utilitaire;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;

    // Methode qui rempli le HashMap
    public void init() throws ServletException {
        String url = getServletContext().getRealPath("/");
        Utilitaire u = new Utilitaire();
        try {
            MappingUrls = u.listeHashMapAllClass(url);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            for (Map.Entry<String, Mapping> entry : MappingUrls.entrySet()) {
                String cle = entry.getKey();
                Mapping mapping = entry.getValue();
                out.print("reussi");
                out.println("</br>");
                out.print("cle :" + cle);
                out.println("</br>");
                out.print("Valeur :" + mapping.getClassName() + " " + mapping.getMethod());
            }

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
