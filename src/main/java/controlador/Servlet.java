package controlador;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//Necesitamos un path una llave o una key para acceder a el servlet
@WebServlet("/servlet")
public class Servlet extends HttpServlet{
    //Sobreescribimos el metodo get
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        //el cabecero que devuelve el servidor
        resp.setContentType("text/html; charset=UTF-8");
        String metodoHttp=req.getMethod();
        String requestURI = req.getRequestURI();
        String requestURL = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ip = req.getLocalAddr();
        int port = req.getServerPort();
        String scheme = req.getScheme();
        String host = req.getHeader("Host");
        String url1="://"+host+port+contextPath+servletPath;


        //Creo la plantilla html
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Manejo Cabeceros</h1>");
        out.println("<ul>");
        out.println("<li>Método utilizado para la petición: "+metodoHttp+"</li>");
        out.println("<li>URI: "+requestURI+"</li>");
        out.println("<li>URL: "+requestURL+"</li>");
        out.println("<li>ContextPath: "+contextPath+"</li>");
        out.println("<li>ServletPath: "+servletPath+"</li>");
        out.println("<li>IP: "+ip+"</li>");
        out.println("<li>Puerto: "+port+"</li>");
        out.println("<li>Esquema: "+scheme+"</li>");
        out.println("<li>Host: "+host+"</li>");
        out.println("<li>Url Creada: "+url1+"</li>");


        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");


    }
}
