import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio2 {

    private static final String URL = "jdbc:mysql://localhost/motos";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Obtener número de motos por marca");
            System.out.println("2. Actualizar modelo de una moto por ID");
            System.out.println("3. Eliminar moto por ID");
            System.out.println("4. Salir");

            System.out.print("Seleccione una operación: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la marca: ");
                    scanner.nextLine(); // Consumir el salto de línea pendiente
                    String marcaConsulta = scanner.nextLine();
                    int numeroMotosConsulta = obtenerNumeroMotosPorMarca(marcaConsulta);
                    System.out.println("Número de motos de la marca " + marcaConsulta + ": " + numeroMotosConsulta + "\n");
                    break;

                case 2:
                    System.out.print("Ingrese el NUMERO de la MOTO: ");
                    int numeroMoto = scanner.nextInt();
                    System.out.print("Ingrese la marca: ");
                    String nuevaMarca = scanner.next();
                    actualizarMarcaMoto(numeroMoto, nuevaMarca);
                    break;

                case 3:
                    System.out.print("Ingrese el NUMERO de la MOTO: ");
                    numeroMoto = scanner.nextInt();
                    eliminarMoto(numeroMoto);
                    break;

                case 4:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    // Método para obtener el número de motos de una marca
    public static int obtenerNumeroMotosPorMarca(String marca) {
        int numeroMotos = 0;

        try (Connection connection = (Connection) DriverManager.getConnection(URL, USUARIO, CONTRASEÑA)) {
            String consulta = "SELECT COUNT(*) FROM motos WHERE marca = ?";
            try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(consulta)) {
                preparedStatement.setString(1, marca);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        numeroMotos = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numeroMotos;
    }

    // Método para actualizar la marca de una moto por su ID
    public static void actualizarMarcaMoto(int numero, String nuevaMarca) {
        try (Connection connection = (Connection) DriverManager.getConnection(URL, USUARIO, CONTRASEÑA)) {
            // Verificar si el ID de la mascota existe
            if (existeMotoPorID(numero, connection)) {
                String consulta = "UPDATE motos SET marca = ? WHERE numero = ?";
                try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(consulta)) {
                    preparedStatement.setString(1, nuevaMarca);
                    preparedStatement.setInt(2, numero);
                    preparedStatement.executeUpdate();

                    System.out.println("La marca de la moto ha sido actualizada correctamente\n");
                }
            } else {
                System.out.println("No se encontró una moto con el NUMERO proporcionado.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar la marca de una moto por su ID
    public static void eliminarMoto(int numero) {
        try (Connection connection = (Connection) DriverManager.getConnection(URL, USUARIO, CONTRASEÑA)) {
            // Verificar si el ID de la mascota existe
            if (existeMotoPorID(numero, connection)) {
                String consulta = "DELETE FROM motos WHERE numero = ?";

                try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(consulta)) {
                    preparedStatement.setInt(1, numero);
                    preparedStatement.executeUpdate();

                    System.out.println("La moto ha sido eliminada correctamente\n");
                }
            } else {
                System.out.println("No se encontró una moto con el NUMERO proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para verificar si una moto existe por su ID
    private static boolean existeMotoPorID(int id, Connection connection) throws SQLException {

        String consulta = "SELECT COUNT(*) FROM motos WHERE numero = ?";
        try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(consulta)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }
}
