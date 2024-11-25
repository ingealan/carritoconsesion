package services;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/agregarCarrito")
public class AgregarCarritoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener el ID del producto que se desea agregar al carrito
        String productoId = req.getParameter("productoId");

        if (productoId == null) {
            resp.sendRedirect(req.getContextPath() + "/productohtml");
            return;
        }

        // Obtener el producto de la lista de productos
        ProductosServices productosServices = new ProductosServiceImplement();
        Producto producto = productosServices.listar().stream()
                .filter(p -> p.getIdProducto() == Integer.parseInt(productoId))
                .findFirst()
                .orElse(null);

        if (producto != null) {
            // Obtener la sesión del usuario
            HttpSession session = req.getSession();

            // Obtener el carrito de la sesión (si no existe, crear uno nuevo)
            List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
            if (carrito == null) {
                carrito = new ArrayList<>();
                session.setAttribute("carrito", carrito);
            }

            // Agregar el producto al carrito
            carrito.add(producto);
        }

        // Redirigir a la página de productos
        resp.sendRedirect(req.getContextPath() + "/productohtml");
    }
}
