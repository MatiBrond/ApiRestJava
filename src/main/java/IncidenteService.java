import java.util.Collection;
public interface IncidenteService {


    public void addIncidente(Incidente incidente);

    public Collection<Incidente> getIncidente();

    public Incidente getIncidente(int id);

    public Incidente changeStatus(Incidente incidente) throws UsuarioException;

    public Collection<Incidente> getIncidentes(Usuario usuario);

    public Collection<Incidente> getIncidenteDeUser(int id);

    public Collection<Incidente> getIncidentesParaUser(int id);

    public Collection<Incidente> getIncidentesAbiertos();

    public Collection<Incidente> getIncidentesResueltos();

    public Incidente incidenteEdit(Incidente incidente) throws UsuarioException;
}
