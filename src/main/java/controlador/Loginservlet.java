package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.LoginService;
import services.LoginServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/loginservlet","/login.html"})
public class Loginservlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        //busco en tódo el arreglo si existe la cookie solicitada y la convierto
        //en string
        Optional<String> cookieOptional= Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                //convertimos a string el valor encontrado
                .map(Cookie::getValue)
                .findAny();

         */
        LoginService auth = new LoginServiceImplement();
        Optional<String> usernameOptional = auth.getUsername(req);
        if (usernameOptional.isPresent()) {
            //Creo la plantilla html
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bienvenido "+usernameOptional.get()+" ya iniciaste sesión</h1>");
            out.println("<a href='"+req.getContextPath()+"/index.html'>Volver al inicio</a>");
            out.println("</body>");
            out.println("</html>");

        }else{
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            //Creamos la cookie
            /*Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);
            //Creo la plantilla html
            try (PrintWriter out = resp.getWriter();) {
                resp.setContentType("text/html; charset=UTF-8");
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Servlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Manejo Cabeceros</h1>");
                out.println("</body>");
                out.println("</html>");
                resp.sendRedirect(req.getContextPath()+"/index.html");
            }*/
            //Creo la sesion
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath()+"/index.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no tiene acceso");
        }
    }
}
