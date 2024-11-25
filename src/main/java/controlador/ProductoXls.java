package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Producto;
import services.ProductosServiceImplement;
import services.ProductosServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/productohtml")
public class ProductoXls extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener la lista de productos
        ProductosServices servicios = new ProductosServiceImplement();
        List<Producto> productos = servicios.listar();

        // Obtener el carrito de la sesión
        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        // Verificar si hay sesión (usuario autenticado)
        String username = (String) req.getSession().getAttribute("username");
        boolean isAuthenticated = username != null;

        // Calcular el total del carrito
        double totalCarrito = 0;
        for (Producto p : carrito) {
            totalCarrito += p.getPrecio();  // Sumar el precio de cada producto al total
        }

        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Lista de Productos</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" crossorigin=\"anonymous\">");

            // Estilos CSS para el gradiente
            out.println("<style>");
            out.println("body {");
            out.println("    background: linear-gradient(45deg, #ff0000, #ff7300, #fffb00, #00ff00, #0000ff, #8a00ff, blue);");
            out.println("    background-size: 400% 400%;");
            out.println("    animation: gradientAnimation 10s ease infinite;");
            out.println("    font-family: 'Arial', sans-serif;");
            out.println("    color: #ecf0f1;");
            out.println("    height: 100vh;");
            out.println("    display: flex;");
            out.println("    align-items: center;");
            out.println("    justify-content: center;");
            out.println("}");

            out.println("@keyframes gradientAnimation {");
            out.println("    0% { background-position: 0% 50%; }");
            out.println("    50% { background-position: 100% 50%; }");
            out.println("    100% { background-position: 0% 50%; }");
            out.println("}");
            out.println("</style>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class=\"container mt-5\">");
            out.println("<h1 class=\"text-center mb-4\">Lista de Productos</h1>");

            // Si hay un usuario autenticado, mostrar un saludo
            if (isAuthenticated) {
                out.println("<p class=\"text-center\">Hola, " + username + "!</p>");
            }

            // Mostrar la lista de productos
            out.println("<div class=\"row\">");

            for (Producto p : productos) {
                out.println("<div class=\"col-md-4 mb-4\">");
                out.println("<div class=\"card bg-secondary text-white\">");
                out.println("<div class=\"card-body\">");
                out.println("<h5 class=\"card-title\">" + p.getNombre() + "</h5>");
                out.println("<p class=\"card-text\">Categoría: " + p.getCategoria() + "</p>");
                out.println("<p class=\"card-text\">Precio: $" + p.getPrecio() + "</p>");

                // Mostrar el enlace para agregar al carrito solo si el usuario está autenticado
                if (isAuthenticated) {
                    out.println("<a href=\"" + req.getContextPath() + "/agregarCarrito?productoId=" + p.getIdProducto() + "\" class=\"btn btn-success\">Agregar al carrito</a>");
                } else {
                    out.println("<p class=\"text-danger\">Inicia sesión para agregar al carrito.</p>");
                }

                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
            }

            out.println("</div>"); // Cierra la fila de productos

            // Mostrar los productos en el carrito
            if (!carrito.isEmpty()) {
                out.println("<h2 class=\"mt-5\">Tu Carrito</h2>");
                out.println("<div class=\"table-responsive\">");
                out.println("<table class=\"table table-bordered table-striped table-dark\">");
                out.println("<thead>");
                out.println("<tr><th>ID</th><th>Nombre</th><th>Precio</th></tr>");
                out.println("</thead>");
                out.println("<tbody>");
                for (Producto p : carrito) {
                    out.println("<tr>");
                    out.println("<td>" + p.getIdProducto() + "</td>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>$" + p.getPrecio() + "</td>");
                    out.println("</tr>");
                }
                out.println("</tbody>");
                out.println("</table>");

                // Mostrar el total del carrito
                out.println("<h3 class=\"text-center\">Total: $" + totalCarrito + "</h3>");
                out.println("</div>");
            } else {
                out.println("<p>No hay productos en el carrito.</p>");
            }

            // Botón para regresar al inicio
            out.println("<div class=\"text-center mt-4\">");
            out.println("<a href=\"" + req.getContextPath() + "/index.html\" class=\"btn btn-light\">Regresar al inicio</a>");
            out.println("</div>");

            out.println("</div>"); // Cierra el contenedor

            out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" crossorigin=\"anonymous\"></script>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
