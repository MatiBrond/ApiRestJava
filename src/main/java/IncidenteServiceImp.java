import java.util.HashMap;
import java.util.Collection;

public class IncidenteServiceImp implements IncidenteService {

    private HashMap<Integer, Incidente> incidente;


    private static IncidenteServiceImp incidenteServiceImp;

    public static IncidenteServiceImp getIncidenteServiceImp(){
        if(incidenteServiceImp == null){
            incidenteServiceImp = new IncidenteServiceImp();
        }
        return incidenteServiceImp;
    }

    public IncidenteServiceImp clone(){
        try {
            throw new CloneNotSupportedException();
        } catch (CloneNotSupportedException ex) {
            System.out.println("No se puede clonar un objeto IncidenteServiceImp");
        } catch(Exception e){
            System.out.println("Ha ocurrido un problema");
        }
        return null;
    }


    public IncidenteServiceImp(){
        incidente = new HashMap<Integer, Incidente>();
    }





    @Override
    public void addIncidente(Incidente incidente) {
        this.incidente.put(incidente.getId(), incidente);
    }

    @Override
    public Collection<Incidente> getIncidente() {
        return incidente.values();
    }

    @Override
    public Incidente getIncidente(int id) {
        return incidente.get(id);
    }

    @Override
    public Incidente changeStatus(Incidente incidente) {

        Incidente incidenteEditar = this.incidente.get(incidente.getId());

            if (incidente.getEstado() != null) {

                if (incidente.getEstado() == Status.ASIGNADO) {
                    incidenteEditar.setEstado(Status.RESUELTO);
                    incidenteEditar.setSolucion();
                    incidenteEditar.setDescripcion(incidente.getDescripcion());
                }
            }
        return incidenteEditar;
    }

    @Override
    public Collection<Incidente> getIncidentes(Usuario usuario) {
        return null;
    }


    @Override
    public Collection<Incidente> getIncidenteDeUser(int id) {

        HashMap<Integer, Incidente> i = new HashMap<Integer, Incidente>();
        incidente.forEach((k,v) -> {
            if(v.getReportador().getId() == id){
                i.put(k,v);
            }
        });

        return i.values();
    }

    @Override
    public Collection<Incidente> getIncidentesParaUser(int id) {
        HashMap<Integer, Incidente> i = new HashMap<Integer, Incidente>();
        incidente.forEach((k,v) -> {
            if(v.getResponsable().getId() == id){
                i.put(k,v);
            }
        });

        return i.values();
    }


    @Override
    public Collection<Incidente> getIncidentesAbiertos() {
        HashMap<Integer, Incidente> i = new HashMap<Integer, Incidente>();
        incidente.forEach((k,v) -> {
            if (v.getEstado() == Status.ASIGNADO){
                i.put(k,v);
            }
        });
        return i.values();
    }

    @Override
    public Collection<Incidente> getIncidentesResueltos() {
        HashMap<Integer, Incidente> i = new HashMap<Integer, Incidente>();
        incidente.forEach((k,v) -> {
            if (v.getEstado() == Status.RESUELTO){
                i.put(k,v);
            }
        });
        return i.values();
    }


    @Override
    public Incidente incidenteEdit(Incidente incidente) throws UsuarioException {


        try {
            if(incidente.getId() == 0){
                throw new UsuarioException("El Id no puede ser cero");
            }
            Incidente incidenteEditar = this.incidente.get(incidente.getId());
            if(incidente.getEstado() == Status.ASIGNADO){
                incidenteEditar.changeStatus();
                incidenteEditar.setSolucion();
            }
            if(incidente.getDescripcion() != null){
                incidenteEditar.setDescripcion(incidente.getDescripcion());
            }
            return incidenteEditar;



        } catch (Exception e){
            throw new UsuarioException(e.getMessage());
        }
        }

    }

