package org.example.programa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entidades.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        App metodos = new App(entityManager);
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("**********(Jugadores)***********");
            System.out.println("1. Crear Jugador");
            System.out.println("6. Actualizar Jugador");
            System.out.println("10. Eliminar Jugador");
            System.out.println("5. Obtener Estadísticas de un Jugador");
            System.out.println("15. Consultar todos los Jugadores");
            System.out.println("********************************");

            System.out.println("***********(Equipos)************");
            System.out.println("2. Crear Equipo");
            System.out.println("7. Actualizar Equipo");
            System.out.println("11. Eliminar Equipo");
            System.out.println("14. Consultar Jugadores de un Equipo");
            System.out.println("16. Consultar Partidos de un Equipo");
            System.out.println("18. Actualizar Nombre Equipo");
            System.out.println("********************************");

            System.out.println("***********(Partidos)***********");
            System.out.println("4. Añadir Partido");
            System.out.println("9. Actualizar Partido");
            System.out.println("13. Eliminar Partido");
            System.out.println("17. Consultar todos los Partidos");
            System.out.println("********************************");

            System.out.println("*********(Estadisticas)*********");
            System.out.println("3. Añadir Estadísticas");
            System.out.println("8. Actualizar Estadísticas");
            System.out.println("12. Eliminar Estadísticas");
            System.out.println("********************************");
            System.out.println("19. Salir");

            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    // Crear un nuevo jugador
                    JugadoresEntity jugador = new JugadoresEntity();
                    System.out.print("Ingrese el nombre del jugador: ");
                    jugador.setNombre(scanner.nextLine());
                    System.out.print("Ingrese la procedencia del jugador: ");
                    jugador.setProcedencia(scanner.nextLine());
                    System.out.print("Ingrese la altura del jugador: ");
                    jugador.setAltura(scanner.nextLine());
                    System.out.print("Ingrese el peso del jugador: ");
                    jugador.setPeso(scanner.nextInt());
                    scanner.nextLine(); // Consumir nueva línea
                    System.out.print("Ingrese la posición del jugador: ");
                    jugador.setPosicion(scanner.nextLine());
                    System.out.print("Ingrese el nombre del equipo del jugador: ");
                    jugador.setNombreEquipo(scanner.nextLine());

                    if (!metodos.validarExistenciaEquipo(jugador.getNombreEquipo())) {
                        System.out.println("\u001B[31mError: El equipo especificado no existe.\u001B[0m");
                    } else {
                        metodos.añadirJugador(jugador);
                        System.out.println("Jugador creado con éxito.");
                    }
                    break;
                case 2:
                    // Crear un nuevo equipo
                    EquiposEntity equipo = new EquiposEntity();
                    System.out.print("Ingrese el nombre del equipo: ");
                    equipo.setNombre(scanner.nextLine());
                    System.out.print("Ingrese la ciudad del equipo: ");
                    equipo.setCiudad(scanner.nextLine());
                    System.out.print("Ingrese la conferencia del equipo: ");
                    equipo.setConferencia(scanner.nextLine());
                    System.out.print("Ingrese la división del equipo: ");
                    equipo.setDivision(scanner.nextLine());

                    if (metodos.validarExistenciaEquipo(equipo.getNombre())) {
                        System.out.println("\u001B[31mError: El equipo ya existe.\u001B[0m");
                    } else {
                        metodos.añadirEquipo(equipo);
                        System.out.println("Equipo creado con éxito.");
                    }
                    break;
                case 3:
                    // Crear estadísticas
                    EstadisticasEntity estadisticas = new EstadisticasEntity();
                    System.out.print("Ingrese la temporada: ");
                    String temporada = scanner.next();
                    System.out.print("Ingrese el código del jugador: ");
                    int codigoJugador = scanner.nextInt();

                    if (!metodos.validarExistenciaJugador(codigoJugador)) {
                        System.out.println("\u001B[31mError: El jugador especificado no existe.\u001B[0m");
                    } else if (metodos.validarExistenciaEstadisticas(temporada, codigoJugador)) {
                        System.out.println("\u001B[31mError: Las estadísticas ya existen para ese jugador en esa temporada.\u001B[0m");
                    } else {
                        estadisticas.setTemporada(temporada);
                        estadisticas.setJugador(codigoJugador);
                        System.out.print("Ingrese los puntos por partido: ");
                        estadisticas.setPuntosPorPartido(scanner.nextDouble());
                        System.out.print("Ingrese las asistencias por partido: ");
                        estadisticas.setAsistenciasPorPartido(scanner.nextDouble());
                        System.out.print("Ingrese los tapones por partido: ");
                        estadisticas.setTaponesPorPartido(scanner.nextDouble());
                        System.out.print("Ingrese los rebotes por partido: ");
                        estadisticas.setRebotesPorPartido(scanner.nextDouble());

                        metodos.añadirEstadisticas(estadisticas);
                        System.out.println("Estadísticas creadas con éxito.");
                    }
                    break;
                case 4:
                    // Crear partido
                    PartidosEntity partido = new PartidosEntity();
                    System.out.print("Ingrese el equipo local: ");
                    String equipoLocal = scanner.nextLine();
                    System.out.print("Ingrese el equipo visitante: ");
                    String equipoVisitante = scanner.nextLine();
                    System.out.print("Ingrese la temporada del partido (23/24): ");
                    String fechaPartido = scanner.next();
                    System.out.print("Ingrese los puntos del equipo local: ");
                    int puntosLocal = scanner.nextInt();
                    System.out.print("Ingrese los puntos del equipo visitante: ");
                    int puntosVisitante = scanner.nextInt();

                    if (!metodos.validarExistenciaEquipo(equipoLocal)) {
                        System.out.println("\u001B[31mError: El equipo local no existe.\u001B[0m");
                    } else if (!metodos.validarExistenciaEquipo(equipoVisitante)) {
                        System.out.println("\u001B[31mError: El equipo visitante no existe.\u001B[0m");
                    } else {
                        partido.setEquipoLocal(equipoLocal);
                        partido.setEquipoVisitante(equipoVisitante);
                        partido.setTemporada(fechaPartido);
                        partido.setPuntosLocal(puntosLocal);
                        partido.setPuntosVisitante(puntosVisitante);

                        metodos.añadirPartido(partido);
                        System.out.println("Partido creado con éxito.");
                    }
                    break;
                case 5:
                    // Obtener estadísticas de un jugador
                    System.out.print("Ingrese la temporada: ");
                    temporada = scanner.next();
                    System.out.print("Ingrese el código del jugador: ");
                    codigoJugador = scanner.nextInt();
                    estadisticas = metodos.obtenerEstadisticas(temporada, codigoJugador);
                    if (estadisticas != null) {
                        System.out.println("Estadísticas del jugador " + codigoJugador + " en la temporada " + temporada + ":");
                        System.out.println("Puntos por partido: " + estadisticas.getPuntosPorPartido());
                        System.out.println("Asistencias por partido: " + estadisticas.getAsistenciasPorPartido());
                        System.out.println("Tapones por partido: " + estadisticas.getTaponesPorPartido());
                        System.out.println("Rebotes por partido: " + estadisticas.getRebotesPorPartido());
                    } else {
                        System.out.println("\u001B[31mNo se encontraron estadísticas para el jugador y la temporada proporcionados.\u001B[0m");
                    }
                    break;
                case 6:
                    // Actualizar jugador
                    System.out.print("Ingrese el código del jugador a actualizar: ");
                    int codigoActualizar = scanner.nextInt();
                    scanner.nextLine();
                    JugadoresEntity jugadorActualizar = metodos.obtenerJugador(codigoActualizar);
                    if (jugadorActualizar != null) {
                        System.out.print("Ingrese el nuevo nombre del jugador: ");
                        jugadorActualizar.setNombre(scanner.nextLine());
                        System.out.print("Ingrese la nueva procedencia del jugador: ");
                        jugadorActualizar.setProcedencia(scanner.nextLine());
                        System.out.print("Ingrese la nueva altura del jugador: ");
                        jugadorActualizar.setAltura(scanner.nextLine());
                        System.out.print("Ingrese el nuevo peso del jugador: ");
                        jugadorActualizar.setPeso(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Ingrese la nueva posición del jugador: ");
                        jugadorActualizar.setPosicion(scanner.nextLine());
                        System.out.print("Ingrese el nuevo nombre del equipo del jugador: ");
                        jugadorActualizar.setNombreEquipo(scanner.nextLine());

                        if (!metodos.validarExistenciaEquipo(jugadorActualizar.getNombreEquipo())) {
                            System.out.println("\u001B[31mError: El equipo especificado no existe.\u001B[0m");
                        } else {
                            metodos.actualizarJugador(jugadorActualizar);
                            System.out.println("Jugador actualizado con éxito.");
                        }
                    } else {
                        System.out.println("\u001B[31mError: El jugador especificado no existe.\u001B[0m");
                    }
                    break;
                case 7:
                    // Actualizar equipo
                    System.out.print("Ingrese el nombre del equipo a actualizar: ");
                    String nombreEquipoActualizar = scanner.nextLine();
                    EquiposEntity equipoActualizar = metodos.obtenerEquipo(nombreEquipoActualizar);
                    if (equipoActualizar != null) {
                        System.out.print("Ingrese la nueva ciudad del equipo: ");
                        equipoActualizar.setCiudad(scanner.nextLine());
                        System.out.print("Ingrese la nueva conferencia del equipo: ");
                        equipoActualizar.setConferencia(scanner.nextLine());
                        System.out.print("Ingrese la nueva división del equipo: ");
                        equipoActualizar.setDivision(scanner.nextLine());

                        metodos.actualizarEquipo(equipoActualizar);
                        System.out.println("Equipo actualizado con éxito.");
                    } else {
                        System.out.println("\u001B[31mError: El equipo especificado no existe.\u001B[0m");
                    }
                    break;
                case 8:
                    // Actualizar estadísticas
                    System.out.print("Ingrese la temporada de las estadísticas a actualizar: ");
                    temporada = scanner.next();
                    System.out.print("Ingrese el código del jugador de las estadísticas: ");
                    codigoJugador = scanner.nextInt();
                    estadisticas = metodos.obtenerEstadisticas(temporada, codigoJugador);
                    if (estadisticas != null) {
                        System.out.print("Ingrese los nuevos puntos por partido: ");
                        estadisticas.setPuntosPorPartido(scanner.nextDouble());
                        System.out.print("Ingrese las nuevas asistencias por partido: ");
                        estadisticas.setAsistenciasPorPartido(scanner.nextDouble());
                        System.out.print("Ingrese los nuevos tapones por partido: ");
                        estadisticas.setTaponesPorPartido(scanner.nextDouble());
                        System.out.print("Ingrese los nuevos rebotes por partido: ");
                        estadisticas.setRebotesPorPartido(scanner.nextDouble());

                        metodos.actualizarEstadisticas(estadisticas);
                        System.out.println("Estadísticas actualizadas con éxito.");
                    } else {
                        System.out.println("\u001B[31mError: Las estadísticas especificadas no existen.\u001B[0m");
                    }
                    break;
                case 9:
                    // Actualizar partido
                    System.out.print("Ingrese el código del partido a actualizar: ");
                    int codigoPartido = scanner.nextInt();
                    scanner.nextLine();
                    PartidosEntity partidoActualizar = metodos.obtenerPartido(codigoPartido);
                    if (partidoActualizar != null) {
                        System.out.print("Ingrese el nuevo equipo local: ");
                        partidoActualizar.setEquipoLocal(scanner.nextLine());
                        System.out.print("Ingrese el nuevo equipo visitante: ");
                        partidoActualizar.setEquipoVisitante(scanner.nextLine());
                        System.out.print("Ingrese la temporada del partido (23/24): ");
                        partidoActualizar.setTemporada(scanner.next());
                        System.out.print("Ingrese los nuevos puntos del equipo local: ");
                        partidoActualizar.setPuntosLocal(scanner.nextInt());
                        System.out.print("Ingrese los nuevos puntos del equipo visitante: ");
                        partidoActualizar.setPuntosVisitante(scanner.nextInt());

                        if (!metodos.validarExistenciaEquipo(partidoActualizar.getEquipoLocal())) {
                            System.out.println("\u001B[31mError: El equipo local no existe.\u001B[0m");
                        } else if (!metodos.validarExistenciaEquipo(partidoActualizar.getEquipoVisitante())) {
                            System.out.println("\u001B[31mError: El equipo visitante no existe.\u001B[0m");
                        } else {
                            metodos.actualizarPartido(partidoActualizar);
                            System.out.println("Partido actualizado con éxito.");
                        }
                    } else {
                        System.out.println("\u001B[31mError: El partido especificado no existe.\u001B[0m");
                    }
                    break;
                case 10:
                    // Eliminar jugador
                    System.out.print("Ingrese el código del jugador a eliminar: ");
                    codigoJugador = scanner.nextInt();
                    if (metodos.validarExistenciaJugador(codigoJugador)) {
                        metodos.eliminarJugador(codigoJugador);
                        System.out.println("Jugador eliminado con éxito.");
                    } else {
                        System.out.println("\u001B[31mError: El jugador especificado no existe.\u001B[0m");
                    }
                    break;
                case 11:
                    // Eliminar equipo
                    System.out.print("Ingrese el nombre del equipo a eliminar: ");
                    nombreEquipoActualizar = scanner.nextLine();
                    if (metodos.validarExistenciaEquipo(nombreEquipoActualizar)) {
                        metodos.eliminarEquipo(nombreEquipoActualizar);
                        System.out.println("Equipo eliminado con éxito.");
                    } else {
                        System.out.println("\u001B[31mError: El equipo especificado no existe.\u001B[0m");
                    }
                    break;
                case 12:
                    // Eliminar estadísticas
                    System.out.print("Ingrese la temporada de las estadísticas a eliminar: ");
                    temporada = scanner.next();
                    System.out.print("Ingrese el código del jugador de las estadísticas: ");
                    codigoJugador = scanner.nextInt();
                    if (metodos.validarExistenciaEstadisticas(temporada, codigoJugador)) {
                        metodos.eliminarEstadisticas(temporada, codigoJugador);
                        System.out.println("Estadísticas eliminadas con éxito.");
                    } else {
                        System.out.println("\u001B[31mError: Las estadísticas especificadas no existen.\u001B[0m");
                    }
                    break;
                case 13:
                    // Eliminar partido
                    System.out.print("Ingrese el código del partido a eliminar: ");
                    codigoPartido = scanner.nextInt();
                    if (metodos.validarExistenciaPartido(codigoPartido)) {
                        metodos.eliminarPartido(codigoPartido);
                        System.out.println("Partido eliminado con éxito.");
                    } else {
                        System.out.println("\u001B[31mError: El partido especificado no existe.\u001B[0m");
                    }
                    break;
                case 14:
                    // Consultar jugadores de un equipo
                    System.out.print("Ingrese el nombre del equipo: ");
                    String nombreEquipo = scanner.nextLine();
                    if (metodos.validarExistenciaEquipo(nombreEquipo)) {
                        List<JugadoresEntity> jugadores = metodos.obtenerJugadoresDeEquipo(nombreEquipo);
                        System.out.println("Jugadores del equipo " + nombreEquipo + ":");
                        for (JugadoresEntity jugadorDeEquipo : jugadores) {
                            System.out.println(jugadorDeEquipo);
                        }
                    } else {
                        System.out.println("\u001B[31mError: El equipo especificado no existe.\u001B[0m");
                    }
                    break;
                case 15:
                    // Consultar todos los jugadores
                    List<JugadoresEntity> todosLosJugadores = metodos.obtenerTodosLosJugadores();
                    System.out.println("Todos los jugadores:");
                    for (JugadoresEntity jugadorDeEquipo : todosLosJugadores) {
                        System.out.println(jugadorDeEquipo);
                    }
                    break;
                case 16:
                    // Consultar partidos de un equipo
                    System.out.print("Ingrese el nombre del equipo: ");
                    nombreEquipo = scanner.nextLine();
                    if (metodos.validarExistenciaEquipo(nombreEquipo)) {
                        List<PartidosEntity> partidos = metodos.obtenerPartidosDeEquipo(nombreEquipo);
                        System.out.println("Partidos del equipo " + nombreEquipo + ":");
                        for (PartidosEntity partidoDeEquipo : partidos) {
                            System.out.println(partidoDeEquipo);
                        }
                    } else {
                        System.out.println("\u001B[31mError: El equipo especificado no existe.\u001B[0m");
                    }
                    break;
                case 17:
                    // Consultar todos los partidos
                    List<PartidosEntity> todosLosPartidos = metodos.obtenerTodosLosPartidos();
                    System.out.println("Todos los partidos:");
                    for (PartidosEntity partidoDeEquipo : todosLosPartidos) {
                        System.out.println(partidoDeEquipo);
                    }
                    break;
                case 18:
                    // Actualizar equipo
                    System.out.print("Ingrese el nombre del equipo a actualizar: ");
                    String nombreActualEquipo = scanner.nextLine();
                    EquiposEntity equipoActualizarNombre = metodos.obtenerEquipo(nombreActualEquipo);
                    if (equipoActualizarNombre != null) {
                        System.out.print("Ingrese el nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingrese la nueva ciudad del equipo: ");
                        String nuevaCiudad = scanner.nextLine();
                        System.out.print("Ingrese la nueva conferencia del equipo: ");
                        String nuevaConferencia = scanner.nextLine();
                        System.out.print("Ingrese la nueva división del equipo: ");
                        String nuevaDivision = scanner.nextLine();

                        metodos.actualizarNombreEquipo(nombreActualEquipo,nuevoNombre,nuevaCiudad,nuevaConferencia,nuevaDivision);
                    } else {
                        System.out.println("\u001B[31mError: El equipo especificado no existe.\u001B[0m");
                    }
                    break;
                case 19:
                    // Salir
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}