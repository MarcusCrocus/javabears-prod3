package producto2_065_BearsJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import producto2_065_BearsJava.model.Rental;
import producto2_065_BearsJava.model.User;
import producto2_065_BearsJava.model.Vehiculo;
import producto2_065_BearsJava.repository.RentalRepo;
import producto2_065_BearsJava.repository.UserRepo;
import producto2_065_BearsJava.repository.VehiculosRepo;

@Service
public class RentalService {

    private final RentalRepo rentalRepository;
    private final UserRepo userRepository;
    private final VehiculosRepo vehicleRepository;

    @Autowired
    public RentalService(RentalRepo rentalRepository, UserRepo userRepository, VehiculosRepo vehicleRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Rental createRental(Long userId, Long vehicleId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        Vehiculo vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new Exception("Vehicle not found"));

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setVehiculo(vehicle);

        return rentalRepository.save(rental);
    }

    public Rental getRentalById(Long rentalId) throws Exception {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new Exception("Rental not found"));
    }

    public Rental updateRental(Rental updatedRental) throws Exception {
        Rental rental = getRentalById(updatedRental.getId());
        return rentalRepository.save(rental);
    }

    public void deleteRental(Long rentalId) throws Exception {
        Rental rental = getRentalById(rentalId);
        rentalRepository.delete(rental);
    }

    // Otras operaciones relacionadas con alquileres
}
