package producto2_065_BearsJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import producto2_065_BearsJava.model.TipoVehiculo;

// define los metodos comunes para acceso a DDBB (entety,tipe)
public interface TipoVehiculosRepo extends JpaRepository<TipoVehiculo,Integer> {

    TipoVehiculo findByName(String name);
}
