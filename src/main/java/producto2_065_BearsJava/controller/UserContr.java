package producto2_065_BearsJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import producto2_065_BearsJava.model.User;
import producto2_065_BearsJava.repository.RoleRepo;
import producto2_065_BearsJava.service.UserServ;

import javax.validation.Valid;


@Controller
public class UserContr {
    @Autowired
    UserServ userServ;
    @Autowired
    RoleRepo roleRepo;
    @GetMapping({"/", "/login"})
    public String index(){
        return "index";
    }
    @GetMapping("/userForm")
    public String userForm(Model model){
        model.addAttribute("userForm", new User());
        model.addAttribute("userList", userServ.getAllUsers());
        model.addAttribute("roles",roleRepo.findAll());
        model.addAttribute("listTab", "active");
        return "user-form/user-view";
    }
    @PostMapping("/userForm")
    public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            model.addAttribute("userForm", user);
            model.addAttribute("formTab","active");
        }else {
            try {
                userServ.createUser(user);
                model.addAttribute("userForm", new User());
                model.addAttribute("listTab","active");

            } catch (Exception e) {
                model.addAttribute("formErrorMessage",e.getMessage());
                model.addAttribute("userForm", user);
                model.addAttribute("formTab","active");
                model.addAttribute("userList", userServ.getAllUsers());
                model.addAttribute("roles",roleRepo.findAll());
            }
        }

        model.addAttribute("userList", userServ.getAllUsers());
        model.addAttribute("roles",roleRepo.findAll());
        return "user-form/user-view";
    }

    @GetMapping("/editUser/{id}")
    public String getEditUserForm(Model model, @PathVariable(name ="id")Long id)throws Exception{
        User userToEdit = userServ.getUserById(id);

        model.addAttribute("userForm", userToEdit);
        model.addAttribute("userList", userServ.getAllUsers());
        model.addAttribute("roles",roleRepo.findAll());
        model.addAttribute("formTab","active");
        model.addAttribute("editMode","true");
//        model.addAttribute("passwordForm",new ChangePasswordForm(id));

        return "user-form/user-view";
    }

    @PostMapping("/editUser")
    public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            model.addAttribute("userForm", user);
            model.addAttribute("formTab","active");
            model.addAttribute("editMode","true");
           // model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
        }else {
            try {
                userServ.updateUser(user);
                model.addAttribute("userForm", new User());
                model.addAttribute("listTab","active");
            } catch (Exception e) {
                model.addAttribute("formErrorMessage",e.getMessage());
                model.addAttribute("userForm", user);
                model.addAttribute("formTab","active");
                model.addAttribute("userList", userServ.getAllUsers());
                model.addAttribute("roles",roleRepo.findAll());
                model.addAttribute("editMode","true");
               // model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
            }
        }
        model.addAttribute("userList", userServ.getAllUsers());
        model.addAttribute("roles",roleRepo.findAll());
        return "user-form/user-view";

    }
    @GetMapping("/userForm/cancel")
    public String cancelEditUser(ModelMap model) {
        return "redirect:/userForm";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(Model model, @PathVariable(name="id") Long id) {
        try {
            userServ.deleteUser(id);
        } catch (Exception e) {
            model.addAttribute("listErrorMessage",e.getMessage());
        }
        return userForm(model);
    }
}