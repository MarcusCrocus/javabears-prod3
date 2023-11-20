package producto2_065_BearsJava.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rental")
public class Rental implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Otros campos relacionados con el alquiler, como fechas, detalles, etc.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehiculo vehiculo;

    // Otros atributos y métodos de la clase Rental

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters y setters para otros campos de Rental

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo Vehiculo) {
        this.vehiculo = Vehiculo;
    }
}
