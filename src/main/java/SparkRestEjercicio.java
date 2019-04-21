import com.google.gson.Gson;

import static spark.Spark.*;

public class SparkRestEjercicio {

    private void cargaDatos(){

    }

    public static void main(String[] args) {
        port(8080);

        final UsuarioService usuarioService = new UsuarioServiceImp();
        final IncidenteService incidenteService = new IncidenteServiceImp();

        ProyectoServiceImp proyectoService = ProyectoServiceImp.getProyectoServiceImp();



        Usuario a = new Usuario(1, "Juan", "filardo");
        Usuario b = new Usuario(2, "Juan", "Beresiarte");
        Usuario c = new Usuario(3, "Fede", "Silva");
        Usuario d = new Usuario(4, "Mariana", "Morelli");
        Usuario e = new Usuario(5, "Lionel Andres", "Messi");
        Usuario f = new Usuario(6, "Emanuel David", "Ginobili");
        Usuario g = new Usuario(7, "Hernan", "Cattaneo");


        usuarioService.addUsuario(a);
        usuarioService.addUsuario(b);
        usuarioService.addUsuario(c);
        usuarioService.addUsuario(d);
        usuarioService.addUsuario(e);
        usuarioService.addUsuario(f);
        usuarioService.addUsuario(g);


        Proyecto p1 = new Proyecto(1, "Proyecto1", a);
        Proyecto p2 = new Proyecto(2, "Proyecto2", b);
        Proyecto p3 = new Proyecto(3, "Proyecto3", c);
        Proyecto p4 = new Proyecto(4, "Proyecto4", a);
        Proyecto p5 = new Proyecto(5, "Proyecto5", a);
        Proyecto p6 = new Proyecto(6, "Proyecto6", b);

        proyectoService.addProyecto(p1);
        proyectoService.addProyecto(p2);
        proyectoService.addProyecto(p3);
        proyectoService.addProyecto(p4);
        proyectoService.addProyecto(p5);
        proyectoService.addProyecto(p6);
                                                //Usuario reportador, Usuario responsable
        Incidente a1 = new Incidente(1, Clasification.CRITICO, "Incidente1", a, b );
        Incidente a2 = new Incidente(2, Clasification.MENOR, "Incidente2", a, b );
        Incidente a3 = new Incidente(3, Clasification.NORMAL, "Incidente3", c, a );
        Incidente a4 = new Incidente(4, Clasification.NORMAL, "Incidente4", c, e );
        Incidente a5 = new Incidente(5, Clasification.CRITICO, "Incidente5", b, f );
        Incidente a6 = new Incidente(6, Clasification.MENOR, "Incidente6", f, a );

        incidenteService.addIncidente(a1);
        incidenteService.addIncidente(a2);
        incidenteService.addIncidente(a3);
        incidenteService.addIncidente(a4);
        incidenteService.addIncidente(a5);
        incidenteService.addIncidente(a6);

        proyectoService.asignarIncidente(a1,1);
        proyectoService.asignarIncidente(a2,1);
        proyectoService.asignarIncidente(a3,2);
        proyectoService.asignarIncidente(a4,1);
        proyectoService.asignarIncidente(a5,3);
        proyectoService.asignarIncidente(a6,4);




        //----------------USUARIOS----------------------------
        post("/usuario", (request, response) -> {

            response.type("application/json");
            Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);
            usuarioService.addUsuario(usuario);
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));

        });

        get("/usuario", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(
                            StatusResponse.SUCCESS, new Gson().toJsonTree(
                                    usuarioService.getUsuario())));
        });

        get("/usuario/:id", (request, response) -> {

            response.type("application/json");
            int id = Integer.valueOf(request.params(":id"));
            return new Gson().toJson(new StandardResponse(
                    StatusResponse.SUCCESS,
                    new Gson().toJsonTree(
                            usuarioService.getUsuario(id)
                    )));
        });

        put("/usuario/:id", (request, response) -> {
            response.type("application/json");
            int idUser = Integer.valueOf(request.params(":id"));
            Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);

            Usuario usuarioEditado = usuarioService.editUsuario(usuario);

            if(usuario != null){
                return new Gson().toJson(new StandardResponse(
                        StatusResponse.SUCCESS,
                        new Gson().toJsonTree(
                                usuarioEditado
                        )));
            }else{
                return new Gson().toJson(new StandardResponse(
                        StatusResponse.ERROR,
                        "Error al editar Usuario"
                ));
            }
        });

        delete("usuario/:id" , (request, response) -> {
            response.type("application/json");
            int idUser = Integer.parseInt(request.params(":id"));
            usuarioService.deleteUsuario(idUser);
            return new Gson().toJson(new StandardResponse(
                            StatusResponse.SUCCESS,
                            "Usuario borrado"));

        });


        //-------------------INCIDENTES----------------------------------

        put("/incidente/:id", (request, response) -> {
            response.type("application/json");
            Incidente incidente = new Gson().fromJson(request.body(), Incidente.class);
            int idIncidente = Integer.valueOf(request.params(":id"));
            Incidente incidenteEditar =  incidenteService.incidenteEdit(incidente);
            if(incidenteEditar != null){
               return new Gson().toJson(
                       new StandardResponse(
                               StatusResponse.SUCCESS,
                               new Gson().toJsonTree(
                                       incidenteEditar)));
            }else{
               return new Gson().toJson(
                       new StandardResponse(
                               StatusResponse.ERROR,
                               "ERROR al editar incidente" ));
            }
            });

        get("/incidente", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(
                            StatusResponse.SUCCESS,
                    new Gson().toJsonTree(
                            incidenteService.getIncidente())));
        });

        post("/proyecto/:id/incidente/", (request, response) -> {

            response.type("application/json");
            int idProyecto = Integer.valueOf(request.params(":id"));
            Incidente incidente = new Gson().fromJson(
                    request.body(), Incidente.class);
            incidenteService.addIncidente(incidente);
            proyectoService.asignarIncidente(incidente, idProyecto ); //recorro proyectos y le asigno el incidente
            return new Gson().toJson(new StandardResponse(
                            StatusResponse.SUCCESS));

        });

        //Obtener incidentes creados por un usuario
        get("/incidente/reportador/:id", (request, response) -> {
            response.type("application/json");

            int idUser = Integer.valueOf(request.params(":id"));

            return new Gson().toJson(
                    new StandardResponse(
                            StatusResponse.SUCCESS,
                            new Gson().toJsonTree(
                                    incidenteService.getIncidenteDeUser(idUser))));

        });

        //Obtener todos los incidentes asignados a un usuario
        get("/incidente/responsable/:id", (request, response) -> {
            response.type("application/json");

            int idUser = Integer.valueOf(request.params(":id"));
            return new Gson().toJson(
                    new StandardResponse(
                            StatusResponse.SUCCESS,
                    new Gson().toJsonTree(
                            incidenteService.getIncidentesParaUser(idUser))));

        });

        get("/proyecto/:id/incidente", (request, response) -> {

            response.type("application/json");

            int idProyecto = Integer.valueOf(request.params(":id"));

            return new Gson().toJson(
                    new StandardResponse(
                            StatusResponse.SUCCESS,
                            new Gson().toJsonTree(
                                    proyectoService.getIncidentes(idProyecto))));
        });

        get("/incidente/abiertos", (request, response) -> {

            response.type("aplication/json");

            return new Gson().toJson(
                    new StandardResponse(
                            StatusResponse.SUCCESS,
                            new Gson().toJsonTree(incidenteService.getIncidentesAbiertos())));
        });

        get("/incidente/resueltos", (request, response) -> {

            response.type("aplication/json");

            return new Gson().toJson(
                    new StandardResponse(
                            StatusResponse.SUCCESS,
                            new Gson().toJsonTree(incidenteService.getIncidentesResueltos())));
        });

        //------------------PROYECTOS---------------------------------------
        //POST addProyecto

        post("proyecto", (request, response) -> {
            response.type("application/json");
            Proyecto proyecto = new Gson().fromJson(request.body(), Proyecto.class);
            proyectoService.addProyecto(proyecto);
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));

        });

        get("/proyecto", (request, response) -> {

            response.type("/application/json");
            return new Gson().toJson(
                    new StandardResponse(
                            StatusResponse.SUCCESS,
                            new Gson().toJsonTree(
                            proyectoService.getProyecto())));

        });

        get("/proyectoUser/:id", (request, response) -> {
            response.type("application/json");

            int id = Integer.valueOf(request.params(":id"));

            return new Gson().toJson(
                    new StandardResponse(
                    StatusResponse.SUCCESS,
                    new Gson().toJsonTree(
                            proyectoService.getProyectoUser(id))));

        });

        get("/proyecto/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.valueOf(request.params(":id"));
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS,
                            new Gson().toJsonTree(proyectoService.getProyecto(id))));
        });
    }
}
