
import java.util.Date;


public class Incidente {

    private int id;
    private Clasification clasificacion;

    private String descripcion;
    private Usuario reportador;
    private Usuario responsable;
    private Status estado;
    private Date creacion;
    private Date solucion;


    //Fecha solucion podria ser null(?
    public Incidente(int id, Clasification clasificacion, String descripcion, Usuario reportador, Usuario responsable ) {
        this.id = id;
        this.clasificacion = clasificacion;
        this.descripcion = descripcion;
        this.reportador = reportador;
        this.responsable = responsable;
        this.estado = Status.ASIGNADO;
        this.creacion = new Date();
    }


    //Metodo que permite cambiar estado
    public void changeStatus(){
        if(estado == Status.ASIGNADO){
            estado = Status.RESUELTO;


        }else{
            estado = Status.ASIGNADO;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clasification getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasification clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getReportador() {
        return reportador;
    }

    public void setReportador(Usuario reportador) {
        this.reportador = reportador;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    public Status getEstado() {
        return estado;
    }

    public void setEstado(Status estado) {
        this.estado = estado;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public Date getSolucion() {
        return solucion;
    }

    public void setSolucion() {
        this.solucion = new Date();
    }
}
