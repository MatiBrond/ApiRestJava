import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class Proyecto {

    private int id;
    private String nombre;
    private Usuario propietario;

    private List<Incidente> incidente;

    public Proyecto(int id, String nombre, Usuario propietario) {
        this.id = id;
        this.nombre = nombre;
        this.propietario = propietario;
        incidente = new ArrayList<Incidente>();

    }

    public void putIncidente(Incidente i){
        incidente.add(i);
    }


    public Collection<Incidente> getIncidente() {
        return incidente;
    }

    public void setIncidente(List<Incidente> incidente) {
        this.incidente = incidente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }
}
