## ApiRest Java V1

# Matias Brond

## Parametros
| Parametro | Descripción |
| ------ | ------ |
| {id_usuario} | ID de Usuario |
| {id_incidente} | ID de Incidente |
| {id_proyecto} | ID de proyecto |


## GET
- Obtener usuarios: /usuarios
- Obtener un usuario: /usuario/{id_usuario}
- Obtener incidentes: /incidente
- Obtener un incidente: /incidente/{id_incidente}
- Obtener incidentes creados por un usuario: /incidente/reportador/{id_usuario}
- Obtener incidentes asignados a un usuario: /incidente/responsable/{id_usuario}
- Obtener incidentes de un proyecto: /proyecto/{id_proyecto}/incidente
- Obtener incidentes abiertos: /incidentes/abiertos
- Obtener incidentes resueltos: /incidentes/resueltos
- Obtener proyectos: /proyecto
- Obtener proyectos de un usuario: /proyecto/usuario/{id_usuario}
- Obtener un proyecto: /proyecto/{id_proyecto}

## POST
- Agregar un usuario: /usuario
- Agregar incidente a proyecto: /proyecto/{id_proyecto}/incidente
- Agregar proyecto: /proyecto

## PUT
- Modificar incidente: /incidente/{id_incidente}
- Modificar usuario: /usuario/{id_usuario}


