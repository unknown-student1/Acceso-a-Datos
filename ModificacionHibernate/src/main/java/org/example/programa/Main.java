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
            System.out.println("1. Prestamos (Usuario)");
            System.out.println("2. Salir");
            System.out.println("*********************************");

            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el código del usuario a buscar: ");
                    int codigoUsuario = scanner.nextInt();
                    scanner.nextLine();
                    UsuarioEntity datosUsuario = metodos.obtenerUsuario(codigoUsuario);
                    if (datosUsuario != null) {
                        metodos.obtenerPrestamosUsuario(codigoUsuario);
                    } else {
                        System.out.println("\u001B[31mError: El usuario especificado no existe.\u001B[0m");
                    }
                    break;
                case 2:
                    continuar = false;
                    System.out.println("\u001B[33mPrograma Finalizado\u001B[0m"); break;
                default:
                    System.out.println("\u001B[33mOpción errorea\u001B[0m"); break;
            }
        }
    }
}

