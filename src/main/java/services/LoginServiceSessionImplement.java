package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSessionImplement implements LoginService {
    @Override
    public Optional<String> getUsername (HttpServletRequest request) {
        //Obtenemos la sesion
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        }

        return Optional.empty();
    }
}
