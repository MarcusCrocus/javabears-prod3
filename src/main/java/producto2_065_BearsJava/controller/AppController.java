package producto2_065_BearsJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping({"/", "/login"})
    public String ViewHomePage(){
        return "login.html";
    }
    @GetMapping("/menu")
    public String menu() {
        return "roles/menu";
    }
    @GetMapping("/user")
    public String user(){
        return "roles/user";
    }
    @GetMapping("/admin")
    public String admin(){
        return "user-form/user-view";
    }
}
