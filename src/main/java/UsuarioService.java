import java.util.Collection;

public interface UsuarioService {


    public void addUsuario(Usuario usuario);
    public Collection<Usuario> getUsuario();
    public Usuario getUsuario(int id);
    public Usuario editUsuario(Usuario usuario) throws UsuarioException;
    public void deleteUsuario(int id);
    public boolean usuarioExist(int id);
    public Usuario usuarioEdit(Usuario usuario);




}
