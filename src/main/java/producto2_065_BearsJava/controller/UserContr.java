package producto2_065_BearsJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import producto2_065_BearsJava.model.MarcaVehiculo;
import producto2_065_BearsJava.model.TipoVehiculo;
import producto2_065_BearsJava.model.User;
import producto2_065_BearsJava.model.Vehiculo;
import producto2_065_BearsJava.repository.MarcaVehiculoRepo;
import producto2_065_BearsJava.repository.RoleRepo;
import producto2_065_BearsJava.repository.TipoVehiculosRepo;
import producto2_065_BearsJava.repository.VehiculosRepo;
import producto2_065_BearsJava.service.UserServ;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UserContr {
    @Autowired
    UserServ userServ;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    VehiculosRepo vehiculosRepo;

    @Autowired
    private TipoVehiculosRepo repo;
    @Autowired
    private MarcaVehiculoRepo marcaVehiculoRepo;

    @GetMapping({"/", "/login"})
    public String index(){
        return "index";
    }
    @GetMapping("/userForm")
    public String userForm(Model model){
            //User controller tab
        model.addAttribute("userForm", new User());
        model.addAttribute("userList", userServ.getAllUsers());
        model.addAttribute("roles",roleRepo.findAll());
        model.addAttribute("listTab", "active");

            //Vehiculos List controller tab
        List<Vehiculo> listaVehiculos = vehiculosRepo.findAll();
        model.addAttribute("listaVehiculos", listaVehiculos);

            //Tipo de Vehiculos List controller tab
        List<TipoVehiculo> listTipoVehiculos = repo.findAll();
        model.addAttribute("listTipoVehiculos", listTipoVehiculos);

            //Marca de Vehiculos List controller tab
        List<MarcaVehiculo> listMarcas = marcaVehiculoRepo.findAll();
        model.addAttribute("listMarcas", listMarcas);

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
        return "redirect:/userForm";

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

    @PostMapping("/vehiculos/assign/{id}")
    public String assignVehicleToUser(@PathVariable Long id, @RequestParam Long userId) {
        try {
            Vehiculo vehiculo = vehiculosRepo.findById(id).orElseThrow(() -> new Exception("Vehicle not found"));
            User user = userServ.getUserById(userId);

            // Assign the vehicle to the user
            vehiculo.setUsuarioPropietario(user);
            vehiculosRepo.save(vehiculo);

            // You might want to redirect to the vehicle list or user form after the assignment
            return "redirect:/userForm";
        } catch (Exception e) {
            // Handle the exception (e.g., log it or show an error message)
            return "redirect:/userForm?error=" + e.getMessage();
        }
    }
}