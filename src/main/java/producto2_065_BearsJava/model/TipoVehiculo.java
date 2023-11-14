package producto2_065_BearsJava.model;


import javax.persistence.*;

@Entity
@Table(name = "tipovehiculo")
public class TipoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 15, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "marca")
    private MarcaVehiculo marca;

    public int getId() {

        return id;
    }

    public void setId(int id)  {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MarcaVehiculo getMarca() {
        return marca;
    }

    public void setMarca(MarcaVehiculo marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return name;
    }
}
