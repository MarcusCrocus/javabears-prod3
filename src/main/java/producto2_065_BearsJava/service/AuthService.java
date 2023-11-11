package producto2_065_BearsJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import producto2_065_BearsJava.model.Users;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;

    public Long autenticarUsuario(String username, String password) {
        List<Users> users = usersService.getAllUsers();

        for (Users user : users) {
            if (verificarContraseña(password, user.getPassword())) {
                return user.getId();
            }
        }
        return null;
    }

    private boolean verificarContraseña(String contraseñaIngresada, String contraseñaAlmacenada) {
        return contraseñaIngresada.equals(contraseñaAlmacenada);
    }
}
