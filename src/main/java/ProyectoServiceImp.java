import java.util.Collection;
import java.util.HashMap;

public class ProyectoServiceImp implements ProyectoService {

    private HashMap<Integer, Proyecto> proyectoMap;


    private static ProyectoServiceImp proyectoServiceImp;


    public static ProyectoServiceImp getProyectoServiceImp(){

        if(proyectoServiceImp == null){
            proyectoServiceImp = new ProyectoServiceImp();
        }
        return proyectoServiceImp;

    }

    public ProyectoServiceImp clone(){
        try {
            throw new CloneNotSupportedException();
        } catch (CloneNotSupportedException ex) {
            System.out.println("No se puede clonar un objeto ProyectoServiceImp");
        } catch(Exception e){
            System.out.println("Ha ocurrido un problema");
        }
        return null;
    }



    private ProyectoServiceImp(){
        proyectoMap = new HashMap<Integer, Proyecto>();
    }

    @Override
    public void addProyecto(Proyecto proyecto) {
        proyectoMap.put(proyecto.getId(), proyecto);
    }

    @Override
    public Collection<Proyecto> getProyecto() {
        return proyectoMap.values();
    }

    @Override
    public Collection<Proyecto> getProyectos(Usuario usuario) {
        return null;
    }

    @Override
    public Proyecto getProyecto(int id) {
        return proyectoMap.get(id);
    }

    @Override
    public void deleteProyecto(int id) {

        if(proyectoMap.get(id).getIncidente().isEmpty()){
            proyectoMap.remove(id);
        }

    }

    @Override
    public Proyecto proyectoEdit(Proyecto proyecto) {
        return null;
    }


    @Override
    public boolean deleteUsuario(int id) {

        proyectoMap.forEach((k,v) -> {
            if(v.getPropietario().getId() == id){

            }

        });
        return true;

    }

    @Override
    public Collection<Proyecto> getProyectoUser(int id) {
        HashMap<Integer, Proyecto> p = new HashMap<Integer, Proyecto>();
        proyectoMap.forEach((k,v) -> {
            if (v.getPropietario().getId() == id) {
                p.put(k, v);
            }

        });
        return p.values();
    }

    @Override
    public void asignarIncidente(Incidente incidente, int id) {

        proyectoMap.forEach((k,v) ->{
            if(v.getId() == id){
                v.putIncidente(incidente);
            }
        });

    }


    @Override
    public Collection<Incidente> getIncidentes(int id) {

        return proyectoMap.get(id).getIncidente();

    }
}

