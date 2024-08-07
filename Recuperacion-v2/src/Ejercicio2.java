import java.sql.*;
import java.util.Scanner;

public class Ejercicio2 {
    /**
     * 2. Realiza un programa que conecte con la BD y realice los siguientes métodos, utilizando sentencias preparadas.
     * a. Crea un método que reciba como parámetro una especie y te devuelva el  numero de mascotas de esa especie. Comprueba su funcionamiento.
     * b. Método que actualice la edad de una Mascota. Tanto el id como la edad se  envían como parámetros. Se deberá comprobar que existe ese id, enviando en  caso contrario el mensaje correspondiente.
     */

    private static final String URL = "jdbc:mysql://localhost/animales";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Obtener número de mascotas por especie");
            System.out.println("2. Actualizar edad de una mascota por ID");
            System.out.println("3. Salir");

            System.out.print("Seleccione una operación: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la especie: ");
                    scanner.nextLine(); // Consumir el salto de línea pendiente
                    String especieConsulta = scanner.nextLine();
                    int numeroMascotasConsulta = obtenerNumeroMascotasPorEspecie(especieConsulta);
                    System.out.println("Número de mascotas de la especie " + especieConsulta + ": " + numeroMascotasConsulta);
                    break;

                case 2:
                    System.out.print("Ingrese el ID de la mascota: ");
                    int idMascotaActualizar = scanner.nextInt();
                    System.out.print("Ingrese la nueva edad: ");
                    int nuevaEdadMascota = scanner.nextInt();
                    actualizarEdadMascota(idMascotaActualizar, nuevaEdadMascota);
                    break;

                case 3:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    // Método para obtener el número de mascotas de una especie
    public static int obtenerNumeroMascotasPorEspecie(String especie) {
        int numeroMascotas = 0;

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA)) {
            String consulta = "SELECT COUNT(*) FROM mascotas WHERE especie = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(consulta)) {
                preparedStatement.setString(1, especie);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        numeroMascotas = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numeroMascotas;
    }

    // Método para actualizar la edad de una mascota por su ID
    public static void actualizarEdadMascota(int id, int nuevaEdad) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA)) {
            // Verificar si el ID de la mascota existe
            if (existeMascotaPorID(id, connection)) {
                String consulta = "UPDATE mascotas SET edad = ? WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(consulta)) {
                    preparedStatement.setInt(1, nuevaEdad);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();

                    System.out.println("Edad actualizada correctamente.");
                }
            } else {
                System.out.println("No se encontró una mascota con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para verificar si una mascota existe por su ID
    private static boolean existeMascotaPorID(int id, Connection connection) throws SQLException {

        String consulta = "SELECT COUNT(*) FROM mascotas WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(consulta)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }
}
