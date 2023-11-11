package producto2_065_BearsJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import producto2_065_BearsJava.model.Clientes;
import producto2_065_BearsJava.service.ClientesServ;

@Controller
public class ClientesContr {

    @Autowired
    private ClientesServ clientesServ;
    // mostrar lista de clientes
    @GetMapping("/clientTable")
    public String viewHomePage(Model model) {
        model.addAttribute("listaClientes", clientesServ.getAllClientes());
        return "clientes/client_table";
    }
    @GetMapping("/showNewClientForm")
    public String showNewClientForm(Model model){
        // creamos atributo de modelo para vincular datos de formulario
        Clientes cli = new Clientes();
        model.addAttribute("cliente", cli);
        return "clientes/new_cliente";
    }
@PostMapping("/saveCliente")
    //creamos meta-handler
    public String saveCliente(@ModelAttribute("cliente") Clientes cli ){
        //guardamos clientes en DDBB
        clientesServ.guardarCliente(cli);
        return "redirect:/clientTable"; //viewHomePage
}
@GetMapping("/showFormForUpdate/{id}")
public String showFormForUpdate(@PathVariable (value = "id") int id, Model model){
    Clientes cli = clientesServ.getClienteById(id);
    model.addAttribute("cliente", cli);
    return "clientes/update_cliente";
}
@GetMapping("/removeCliente/{id}")
    public String removeCliente(@PathVariable(value = "id") int id){
    this.clientesServ.removeClienteById(id);
    return "redirect:/clientTable";
}
}
