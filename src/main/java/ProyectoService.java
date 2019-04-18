import java.util.Collection;
public interface ProyectoService {


    public void addProyecto(Proyecto proyecto);
    public Collection<Proyecto> getProyecto();
    public Proyecto getProyecto(int id);
    public Collection<Proyecto> getProyectos(Usuario usuario);
    public void deleteProyecto(int id);
    public Proyecto proyectoEdit(Proyecto proyecto);
    public Collection<Proyecto> getProyectoUser(int id);

    public void asignarIncidente(Incidente incidente, int id);

    public Collection<Incidente> getIncidentes(int id);

    public boolean deleteUsuario(int id);

}
