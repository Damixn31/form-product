package org.olmedo.servlet.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet({"/crear", ""})
public class RegistroProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");

        String nombre = req.getParameter("nombre");

        String fabricante = req.getParameter("fabricante");

        Integer precio = null;

        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {

        }

        Integer categoria = null;
        try {
            categoria = Integer.valueOf(req.getParameter("categoria"));
        } catch (NumberFormatException e) {

        }


        Map<String, String> errores = new HashMap<>();

        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre del producto debe ser requerido!");
        }
        if (fabricante == null || fabricante.isBlank()) {
            errores.put("fabricante", "El fabricante no puede ser vacio!");
        } else if(!(fabricante.length() >=4) || !(fabricante.length() <=10)) {
            errores.put("fabricante", "El fabricante tiene que tener entre 4 y 10 caracteres!");
        }

        if(precio == null || precio < 1) {
            errores.put("precio", "El precio es requerido y debe ser un entereo positivo");
        }

        if (categoria == null || categoria < 1) {
            errores.put("categoria", "debe seleccionar una categoria");
        }
        if (errores.isEmpty()) {
            try (PrintWriter out = res.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("      <head>");
                out.println("            <meta charset=\"UTF-8\">");
                out.println("            <title>Resultado form</title>");
                out.println("     </head>");
                out.println("     <body>");
                out.println("           <h1>Resultado form</h1>");
                out.println("              <ul>");
                out.println("                  <li>Producto: " + nombre + "</li>");
                out.println("                  <li>precio: " + precio + "</li>");
                out.println("                  <li>fabricante: " + fabricante + "</li>");
                out.println("                  <li>categoria id: " + categoria + "</li>");

                out.println("             </ul>");
                out.println("      </body>");
                out.println("</html>");
            }
        } else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, res);
        }
    }
}
