package producto2_065_BearsJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import producto2_065_BearsJava.model.Vehiculo;

public interface VehiculosRepo extends JpaRepository <Vehiculo, Long>{
    // se puede agregar métodos personalizados si es necesario
}
