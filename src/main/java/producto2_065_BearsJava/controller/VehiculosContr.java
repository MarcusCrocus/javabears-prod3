package producto2_065_BearsJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import producto2_065_BearsJava.model.TipoVehiculo;
import producto2_065_BearsJava.model.Vehiculo;
import producto2_065_BearsJava.repository.TipoVehiculosRepo;
import producto2_065_BearsJava.repository.VehiculosRepo;

import java.util.List;

@Controller

public class VehiculosContr {
    @Autowired
    private TipoVehiculosRepo tipoVehiculosRepo;
    @Autowired
    private VehiculosRepo vehiculosRepo;

    @GetMapping("/vehiculos/new")
    public String showVehiculoNewFrom(Model model) {
        List<TipoVehiculo> listaVehiculos =  tipoVehiculosRepo.findAll();

        model.addAttribute("vehiculo", new Vehiculo());
        model.addAttribute("listaVehiculos", listaVehiculos);
        return "vehiculos/new_vehiculo_form";
    }

    @PostMapping("/vehiculos/save")
    public String saveVehiculo(Vehiculo vh){
        vehiculosRepo.save(vh);
        return "redirect:/userForm";
    }

//    @GetMapping("/vehiculos")
//    public String listaVehiculos(Model model){
//        List<Vehiculo> listaVehiculos = vehiculosRepo.findAll();
//        model.addAttribute("listaVehiculos", listaVehiculos);
//        return "vehiculos/listavehiculos_form";
//    }

    @GetMapping("vehiculos/edit/{id}")
    public String showEditVehiculoForm(@PathVariable("id") Long id, Model model){
        Vehiculo vehiculo = vehiculosRepo.findById(id).get();
        model.addAttribute("vehiculo", vehiculo);

        //activamos la habilidad de editar el campo de tipoVehiculo
        List<TipoVehiculo> listaVehiculos =  tipoVehiculosRepo.findAll();
        model.addAttribute("listaVehiculos", listaVehiculos);

        return "vehiculos/new_vehiculo_form";
    }
    @GetMapping("/vehiculos/remove/{id}")
    public String removeVehiculo(@PathVariable("id") Long id) {
        // Elimina el Vehículo por su ID directamente del repositorio
        vehiculosRepo.deleteById(id);

        return "redirect:/vehiculos";
    }

}
