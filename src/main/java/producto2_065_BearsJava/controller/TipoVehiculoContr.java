package producto2_065_BearsJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import producto2_065_BearsJava.model.TipoVehiculo;
import producto2_065_BearsJava.repository.TipoVehiculosRepo;
@Controller
public class TipoVehiculoContr {
    @Autowired
    private TipoVehiculosRepo repo;

//    @GetMapping("/tipovehiculos")
//    public String listTipoVehiculos(Model model) {
//        List<TipoVehiculo> listTipoVehiculos = repo.findAll();
//        model.addAttribute("listTipoVehiculos", listTipoVehiculos);
//        return "vehiculos/tipovehiculos";
//    }

    @GetMapping("/tipovehiculos/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String showTipovehiculosNewFrom(Model model) {
        model.addAttribute("tipovehiculo", new TipoVehiculo());
        return "vehiculos/tipovehiculo_form";
    }

    @PostMapping("/userForm/tipodevehiculos/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveTipoVehiculo(@ModelAttribute("tipovehiculo") TipoVehiculo vh) {
        if (vh.getId() > 0) {
            // case para editar y guardar si existe
            TipoVehiculo existingTipoVehiculo = repo.findById(vh.getId()).orElse(null);
            if (existingTipoVehiculo != null) {
                //actualizar tipo que existe
                existingTipoVehiculo.setName(vh.getName());
                repo.save(existingTipoVehiculo);
            }
        } else {
            // creacion de nuevo tipo
            repo.save(vh);
        }
        return "redirect:/userForm";
    }

    @GetMapping("/tipovehiculos/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditTipoVehiculoForm(@PathVariable("id") int id, Model model) {
        TipoVehiculo tipoVehiculo = repo.findById(id).orElse(null);
        if (tipoVehiculo != null) {
            model.addAttribute("tipovehiculo", tipoVehiculo);
        }
        return "vehiculos/tipovehiculo_form";
    }

    @GetMapping("/tipovehiculos/remove/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String removeTipoVehiculo(@PathVariable("id") int id){
        repo.deleteById(id);
        return "redirect:/userForm";
    }
}

