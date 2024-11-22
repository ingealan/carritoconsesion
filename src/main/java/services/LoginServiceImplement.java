package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceImplement implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        //Obtengo la sesion
        HttpSession session = request.getSession();
        //Convierto el objeto de tipo sesion a string
        String username = (String) session.getAttribute("username");
        /*Creo una condicional en la cual valido si el valor obtenido es diferente de nulo
        * obtengo el userndame caso contrario retorno la sesion vacia*/
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
