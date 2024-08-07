package org.example.programa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entidades.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
            System.out.println("**********(Biblioteca)***********");
            System.out.println("1. Añadir Prestamo");
            System.out.println("2. Modificar Libro");
            System.out.println("3. Datos Libro");
            System.out.println("4. Mostrar Usuario (Libros)");
            System.out.println("5. Prestamos (Libro)");
            System.out.println("6. Salir");
            System.out.println("*********************************");

            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    // Crear un nuevo jugador
                    PrestamoEntity prestamo = new PrestamoEntity();
                    System.out.print("Ingrese el identificador de usuario: ");
                    prestamo.setIdUsuario(scanner.nextInt());
                    System.out.print("Ingrese el identificador del libro: ");
                    prestamo.setIdLibro(scanner.nextInt());
                    System.out.print("Ingrese la fecha del prestamo (24/05/2024): ");
                    String fechaObtenida = scanner.next();
                    Date fechaPrestamo = new Date(fechaObtenida);

                    // Formatear Fecha
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String fechaFormateada = dateFormat.format(fechaPrestamo);

                    // Establecer Fecha
                    prestamo.setFecha(java.sql.Date.valueOf(fechaFormateada));

                    if (!metodos.validarExistenciaUsuario(prestamo.getIdUsuario())) {
                        System.out.println("\u001B[31mError: El usuario especificado no existe.\u001B[0m");
                    } else if (!metodos.validarExistenciaLibro(prestamo.getIdLibro())) {
                        System.out.println("\u001B[31mError: El libro especificado no existe.\u001B[0m");
                    } else {
                        metodos.añadirPrestamo(prestamo);;
                        System.out.println("Prestamo creado con éxito.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el código del libro a actualizar: ");
                    int codigoActualizar = scanner.nextInt();
                    scanner.nextLine();
                    LibroEntity libroActualizar = metodos.obtenerLibro(codigoActualizar);
                    if (libroActualizar != null) {
                        System.out.print("Ingrese el numero ISBN: ");
                        libroActualizar.setIsbn(scanner.nextLine());
                        System.out.print("Ingrese el titulo del libro: ");
                        libroActualizar.setTitulo(scanner.nextLine());
                        System.out.print("Ingrese la cantidad de ejemplares: ");
                        libroActualizar.setNumeroEjemplares(scanner.nextByte());
                        System.out.print("Ingrese el identificador del autor: ");
                        libroActualizar.setIdAutor(scanner.nextInt());
                        scanner.nextLine();
                        if (!metodos.validarExistenciaAutor(libroActualizar.getIdAutor())) {
                            System.out.println("\u001B[31mError: El autor especificado no existe.\u001B[0m");
                        } else {
                            metodos.actualizarLibro(libroActualizar);
                            System.out.println("Libro actualizado con éxito.");
                        }
                    } else {
                        System.out.println("\u001B[31mError: El libro especificado no existe.\u001B[0m");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el código del libro a buscar: ");
                    int codigoLibro = scanner.nextInt();
                    scanner.nextLine();
                    LibroEntity DatosLibro = metodos.obtenerLibro(codigoLibro);
                    if (DatosLibro != null) {
                        System.out.println("Nombre Libro: " + DatosLibro.getTitulo());
                        List<PrestamoEntity> prestamos = metodos.obtenerPrestamosLibro(codigoLibro);
                        if (prestamos != null){
                            System.out.println("**********(Prestamos)***********");
                            for (PrestamoEntity prestamoslibro : prestamos) {
                                System.out.println(prestamoslibro.toString());
                            }
                        }else {
                            System.out.println("\u001B[31mError: No existen prestamos para el libro especificado.\u001B[0m");
                        }

                    } else {
                        System.out.println("\u001B[31mError: El libro especificado no existe.\u001B[0m");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el código del usuario a buscar: ");
                    int codigoUsuario = scanner.nextInt();
                    scanner.nextLine();
                    UsuarioEntity datosUsuario = metodos.obtenerUsuario(codigoUsuario);
                    if (datosUsuario != null) {
                        System.out.println("Nombre Usuario: " + datosUsuario.getNombreCompleto());
                        List<PrestamoEntity> prestamos = metodos.obtenerPrestamosUsuario(codigoUsuario);
                        if (prestamos != null){
                            System.out.println("**********(Prestamos)***********");
                            for (PrestamoEntity prestamoslibro : prestamos) {
                                System.out.println(prestamoslibro.toString());
                            }
                        }else {
                            System.out.println("\u001B[31mError: No existen prestamos para el usuario especificado.\u001B[0m");
                        }
                    } else {
                        System.out.println("\u001B[31mError: El libro especificado no existe.\u001B[0m");
                    }
                    break;
                case 5:
                    System.out.print("Ingrese el código del libro a buscar: ");
                    int codigoLibro2 = scanner.nextInt();
                    scanner.nextLine();
                    LibroEntity DatosLibro2 = metodos.obtenerLibro(codigoLibro2);
                    if (DatosLibro2 != null) {
                        System.out.println("Nombre Libro: " + DatosLibro2.getTitulo());
                        List<PrestamoEntity> prestamos = metodos.obtenerPrestamosLibro2(codigoLibro2);
                        if (prestamos != null){
                            System.out.println("**********(Prestamos)***********");
                            for (PrestamoEntity prestamoslibro : prestamos) {
                                System.out.println(prestamoslibro.toString());
                            }
                        }else {
                            System.out.println("\u001B[31mError: No existen prestamos para el libro especificado.\u001B[0m");
                        }

                    } else {
                        System.out.println("\u001B[31mError: El libro especificado no existe.\u001B[0m");
                    }
                    break;
                case 6:
                    continuar = false;
                    System.out.println("\u001B[33mPrograma Finalizado\u001B[0m"); break;
                default:
                    System.out.println("\u001B[33mPrograma Finalizado\u001B[0m"); break;
            }
        }
    }
}
