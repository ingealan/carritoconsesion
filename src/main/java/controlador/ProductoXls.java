package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Producto;
import services.LoginService;
import services.LoginServiceImplement;
import services.ProductosServiceImplement;
import services.ProductosServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

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
        LoginService auth= new LoginServiceImplement();
        Optional<String> usernameOptional = auth.getUsername(req);

        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            // Agregar el bloque de estilos similares al archivo HTML inicial
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Lista de productos</title>");
            out.println("<style>");
            out.println("/* Estilos generales */");
            out.println("body { font-family: 'Arial', sans-serif; background: #2c3e50; color: #ecf0f1; margin: 0; padding: 0; text-align: center; }");
            out.println("h1 { font-size: 48px; margin-top: 50px; color: #ecf0f1; text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.3); }");
            out.println("a { text-decoration: none; font-size: 22px; color: #3498db; padding: 15px 30px; border-radius: 30px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); transition: all 0.3s ease; }");
            out.println("a:hover { background-color: #3498db; color: #ecf0f1; transform: scale(1.1); box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); }");
            out.println("table { margin-top: 30px; width: 80%; margin-left: auto; margin-right: auto; border-collapse: collapse; background-color: #34495e; }");
            out.println("th, td { padding: 15px; text-align: center; border: 1px solid #ddd; color: #ecf0f1; }");
            out.println("th { background-color: #3498db; }");
            out.println("td { background-color: #2c3e50; }");
            out.println("body { background: linear-gradient(to right, #2980b9, #8e44ad, #16a085); background-size: 400% 400%; animation: gradientAnimation 10s ease infinite; }");
            out.println("@keyframes gradientAnimation { 0% { background-position: 0% 50%; } 50% { background-position: 100% 50%; } 100% { background-position: 0% 50%; } }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de productos</h1>");
            if (usernameOptional.isPresent()) {
                out.println("<p>Hola " +usernameOptional+"</p>");
            }
            out.println("<p><a href=\"" + req.getContextPath() + "/producto.xls" + "\">Descargar xls</a></p>");

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
            out.println("</body>");
            out.println("</html>");
        }
    }
}
