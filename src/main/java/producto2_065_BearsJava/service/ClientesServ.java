package producto2_065_BearsJava.service;

import org.springframework.stereotype.Service;
import producto2_065_BearsJava.model.Clientes;

import java.util.List;
@Service
public interface ClientesServ {
    List<Clientes> getAllClientes();

    void guardarCliente(Clientes cli);
    Clientes getClienteById(int id);
    void removeClienteById(int id);
}
