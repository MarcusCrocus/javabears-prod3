package producto2_065_BearsJava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

import producto2_065_BearsJava.model.Users;
import producto2_065_BearsJava.service.AuthService;
import producto2_065_BearsJava.service.UsersService;

import java.util.Optional;

@Controller

public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthService authService;

    @GetMapping("/users/lista")
    public String listarClientes(@RequestParam(name = "tipo", required = false) String tipoUsuarioDescripcion,
            Model model) {
        List<Users> users = usersService.getAllUsers();
        model.addAttribute("users", users);

        if (tipoUsuarioDescripcion != null && !tipoUsuarioDescripcion.isEmpty()) {
            model.addAttribute("tipoUsuarioDescripcion", tipoUsuarioDescripcion);
        }
        return "/user/lista";
    }

    @GetMapping("/users/login")
    public String ViewAddUsersPage() {
        return "/user/login";
    }

    @GetMapping("/users/register")
    public String ViewRegisterUsersPage(Model model) {
        model.addAttribute("users", new Users());
        return "/user/register";
    }

    @PostMapping("/users/register")
    public String saveUsers(Users users) {
        String tipoUsuarioDescripcion = "Cliente";
        if (users.getEmail().equals("admin@admin.com")) {
            tipoUsuarioDescripcion = "admin";
        }
        users.setTipoUsuario(tipoUsuarioDescripcion);
        usersService.guardarUser(users);
        return "redirect:/users/login";
    }

    @PostMapping("/loginform")
    public String procesarInicioSesion(@RequestParam("email") String email,
            @RequestParam("password") String password, HttpSession session, Model model) {
        Long userId = authService.autenticarUsuario(email, password);
        Optional<Users> userOptional = usersService.getUserById(userId);

        if (userId != null) {
            Users user = userOptional.get();
            String tipoUsuarioDescripcion = user.getTipoUsuario();
            model.addAttribute("tipoUsuarioDescripcion", tipoUsuarioDescripcion);
            session.setAttribute("userId", userId);
            return "redirect:/users/dashboard?id=" + userId + "&tipo=" +
                    tipoUsuarioDescripcion;
        } else {
            return "redirect:/login-error";
        }
    }

    @GetMapping("/users/dashboard")
    public String dashboard(@RequestParam(name = "tipo", required = false) String tipoUsuarioDescripcion, Model model) {
        List<Users> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        if (tipoUsuarioDescripcion != null && !tipoUsuarioDescripcion.isEmpty()) {
            model.addAttribute("tipoUsuarioDescripcion", tipoUsuarioDescripcion);
        }
        return "/user/dashboard";
    }

}
