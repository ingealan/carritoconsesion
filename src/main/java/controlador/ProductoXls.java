package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Producto;
import services.ProductosServiceImplement;
import services.ProductosServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//Anotacion
@WebServlet({"/producto.xls", "/productohtml"})
public class ProductoXls extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductosServices servicios = new ProductosServiceImplement();
        List<Producto> productos = servicios.listar();
        //Obtener el server path
        String servletPath = req.getServletPath();
        //Creo una variable de tipo booleano para verificar que path estoy obteniendo
        boolean xls = servletPath.endsWith(".xls");
        if (xls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-disposition", "attachment; filename=productos.xls");
        }
        //Creo la plantilla html
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            if (!xls) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Lista de productos</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Listado de productos</h1>");
                out.println("<p><a href=\"" + req.getContextPath() + "/producto.xls" + "\">Descargar xls" + "</a></p>");
            }
                out.println("<table>");
                out.println("<tr>");

                out.println("<th>ID</th>");
                out.println("<th>Nombre</th>");
                out.println("<th>Categoria</th>");
                out.println("<th>Precio</th>");
                out.println("</tr>");

                productos.forEach(p -> {
                    out.println("<tr>");
                    out.println("<td>" + p.getIdProducto() + "</td>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>" + p.getCategoria() + "</td>");
                    out.println("<td>" + p.getPrecio() + "</td>");

                    out.println("</tr>");
                });
                out.println("</table>");

            if (!xls) {
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
