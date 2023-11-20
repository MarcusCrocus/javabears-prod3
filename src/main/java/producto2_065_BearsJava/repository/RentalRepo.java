package producto2_065_BearsJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import producto2_065_BearsJava.model.Rental;

@Repository
public interface RentalRepo extends JpaRepository<Rental, Long> {
    // Puedes agregar métodos adicionales según las consultas específicas que
    // necesites
    // JpaRepository ya ofrece métodos como save, findById, findAll, etc., de manera
    // predeterminada
}
