import java.util.HashMap;
import java.util.Collection;


public class UsuarioServiceImp implements UsuarioService {

    private HashMap<Integer, Usuario> usuarioMap;

    private static UsuarioServiceImp usuarioServiceImp;

    public static UsuarioServiceImp getProyectoServiceImp(){
        if(usuarioServiceImp == null){
            usuarioServiceImp = new UsuarioServiceImp();
        }
        return usuarioServiceImp;
    }

    public UsuarioServiceImp clone(){
        try {
            throw new CloneNotSupportedException();
        } catch (CloneNotSupportedException ex) {
            System.out.println("No se puede clonar un objeto UsuarioServiceImp");
        } catch(Exception e){
            System.out.println("Ha ocurrido un problema");
        }
        return null;
    }


    public UsuarioServiceImp(){
        usuarioMap = new HashMap<Integer, Usuario>();
    }

    public void addUsuario(Usuario usuario) throws UsuarioException {

        try{

            Usuario u = usuarioMap.get(usuario.getId());
            if(u != null){
                throw new UsuarioException("Id usuario repetido");
            }
            if(u == null) {
                usuarioMap.put(usuario.getId(), usuario);
            }
        } catch (UsuarioException e){
            System.out.println(e.getMessage());
        }

    }

    public Collection<Usuario> getUsuario() {
        return usuarioMap.values();
    }

    public Usuario getUsuario(int id) {
        return usuarioMap.get(id);
    }

    public Usuario editUsuario(Usuario usuario) throws UsuarioException {

        try {
            if(usuario.getId() == 0){
                throw new UsuarioException("El id del Usuario no puede ser cero");
            }

            Usuario usuarioEditar = usuarioMap.get(usuario.getId());

            if(usuario.getNombre() != null){
                usuarioEditar.setNombre(usuario.getNombre());
            }
            if(usuario.getApellido() != null){
                usuarioEditar.setApellido(usuario.getApellido());
            }
            return usuarioEditar;


        } catch(Exception e){

            throw new UsuarioException(e.getMessage());
        }

    }

    public void deleteUsuario(int id) {
        int r = 1;

        ProyectoServiceImp proyectos = ProyectoServiceImp.getProyectoServiceImp();
        IncidenteServiceImp incidentes = IncidenteServiceImp.getIncidenteServiceImp();

        for(Proyecto p : proyectos.getProyecto()){
            if(p.getId() == id){
                r = 0;
            }
        }

        for(Incidente i : incidentes.getIncidente()){
            if(i.getReportador().getId() == id || i.getResponsable().getId() == id){
                r = 0;
            }
        }

        if (r == 1) {
            usuarioMap.remove(id);
        }
    }

    public boolean usuarioExist(int id) {
        if(usuarioMap.get(id) != null) {
            return true;
        }else{
            return false;
        }
    }

    public Usuario usuarioEdit(Usuario usuario){
        Usuario userEditar = usuarioMap.get(usuario.getId());
        try{
            if(userEditar == null){
                throw new UsuarioException("El usuario no existe");
            }
        }catch (Exception e){
            System.out.println("ERROR AL EDITAR USUARIO");
        }
        return userEditar;
    }
}
