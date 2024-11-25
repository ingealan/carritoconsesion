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
        //Implementamos el objeto de la sesion
        LoginService auth = new LoginServiceImplement();
        /*creamos una variable opcionale n la cual guardamos el nombre obtenido de la clase
        * getUsername*/
        Optional<String> usernameOptional = auth.getUsername(req);
        //Si el username esta presente quiero que me muestre la informacion del login exitoso
        if (usernameOptional.isPresent()) {
            //Creo la plantilla html
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Bienvenido</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" crossorigin=\"anonymous\">");
            out.println("<style>");
            out.println("body {");
            out.println("    font-family: 'Arial', sans-serif;");
            out.println("    background: linear-gradient(45deg, #ff0000, #ff7300, #fffb00, #00ff00, #0000ff, #8a00ff, blue);");
            out.println("    background-size: 400% 400%;");
            out.println("    animation: gradientAnimation 10s ease infinite;");
            out.println("    color: #ecf0f1;");
            out.println("    height: 100vh;");
            out.println("    display: flex;");
            out.println("    flex-direction: column;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    text-align: center;");
            out.println("}");
            out.println("@keyframes gradientAnimation {");
            out.println("    0% { background-position: 0% 50%; }");
            out.println("    50% { background-position: 100% 50%; }");
            out.println("    100% { background-position: 0% 50%; }");
            out.println("}");
            out.println(".container {");
            out.println("    background-color: rgba(0, 0, 0, 0.7);");
            out.println("    padding: 30px;");
            out.println("    border-radius: 15px;");
            out.println("    width: 80%;");
            out.println("    max-width: 600px;");
            out.println("}");
            out.println("h1 {");
            out.println("    font-size: 32px;");
            out.println("    margin-bottom: 20px;");
            out.println("    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3);");
            out.println("}");
            out.println(".btn-primary {");
            out.println("    background-color: #3498db;");
            out.println("    border: none;");
            out.println("    padding: 10px 20px;");
            out.println("    font-size: 18px;");
            out.println("    border-radius: 5px;");
            out.println("    transition: all 0.3s ease;");
            out.println("}");
            out.println(".btn-primary:hover {");
            out.println("    background-color: #2980b9;");
            out.println("    cursor: pointer;");
            out.println("    transform: scale(1.05);");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            out.println("<h1>Bienvenido, " + usernameOptional.get() + ", ya iniciaste sesión</h1>");
            out.println("<a href='" + req.getContextPath() + "/index.html' class=\"btn btn-primary\">Volver al inicio</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");


        }else{
            /*Caso contrario muestre un error de datos y retorne a el login*/
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
