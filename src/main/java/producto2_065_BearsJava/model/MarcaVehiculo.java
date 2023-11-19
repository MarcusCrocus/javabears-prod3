package producto2_065_BearsJava.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marca_vehiculo")

public class MarcaVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 125, nullable = false, unique = true)
    private String name;
    @OneToMany
    @JoinColumn(name = "marca_id")
    private List<TipoVehiculo> tipoVehiculoList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TipoVehiculo> getTipoVehiculoList() {
        return tipoVehiculoList;
    }

    public void setTipoVehiculoList(List<TipoVehiculo> tipoVehiculoList) {
        this.tipoVehiculoList = tipoVehiculoList;
    }
}
