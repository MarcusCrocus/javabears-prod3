package producto2_065_BearsJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import producto2_065_BearsJava.model.Clientes;
import producto2_065_BearsJava.repository.ClientesRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesServImpl implements ClientesServ {

    @Autowired
    private ClientesRepo clientesRepo;
    @Override
    public List<Clientes> getAllClientes() {
        return clientesRepo.findAll();
    }

    @Override
    public void guardarCliente(Clientes cli) {
        this.clientesRepo.save(cli);
    }

    @Override
    public Clientes getClienteById(int id) {
        Optional <Clientes> optional = clientesRepo.findById(id);
        Clientes cli = null;
        if (optional.isPresent()){
            cli = optional.get();
        }else {
            throw new RuntimeException("Cliente con id" + id + "no existe");
        }
        return cli;
    }

    @Override
    public void removeClienteById(int id) {
        this.clientesRepo.deleteById(id);
    }
}
