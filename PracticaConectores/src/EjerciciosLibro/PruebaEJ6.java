package EjerciciosLibro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PruebaEJ6 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Insertar Venta (MySQL)");
        System.out.println("2 - Insertar Venta (Oracle)");
        System.out.println("3 - Insertar Venta (SQLite)");
        System.out.println("4 - Cancelar");
        System.out.print("Inserte una opción: ");
        int opcion = scanner.nextInt();

        System.out.println("----------------Requisitos (Insertar una venta)----------------");
        System.out.println("\033[3m" +
                "\tEl identificador de venta no debe existir en la tabla VENTAS.\n" +
                "\tEl identificador de cliente debe existir en la tabla CLIENTES.\n" +
                "\tEl identificador de producto debe existir en la tabla PRODUCTOS.\n" +
                "\tLa cantidad debe ser > que 0\033[0m");

        // Inicializar las variables
        int idVenta;
        int idCliente;
        int idProducto;
        int cantidad;

        switch (opcion) {
            case 1:
                // Obtener datos de la venta
                System.out.print("Inserte el número de venta: ");
                idVenta = scanner.nextInt();
                System.out.print("Inserte el ID_CLIENTE: ");
                idCliente = scanner.nextInt();
                System.out.print("Inserte el ID_PRODUCTO: ");
                idProducto = scanner.nextInt();
                System.out.print("Inserte la cantidad de producto: ");
                cantidad = scanner.nextInt();

                // Llamar a la función para insertar la venta
                insertarVentaMySQL(idVenta, idCliente, idProducto, cantidad);
                break;
            case 2:
                // Obtener datos de la venta
                System.out.print("Inserte el número de venta: ");
                idVenta = scanner.nextInt();
                System.out.print("Inserte el ID_CLIENTE: ");
                idCliente = scanner.nextInt();
                System.out.print("Inserte el ID_PRODUCTO: ");
                idProducto = scanner.nextInt();
                System.out.print("Inserte la cantidad de producto: ");
                cantidad = scanner.nextInt();

                // Llamar a la función para insertar la venta
                insertarVentaOracle(idVenta, idCliente, idProducto, cantidad);
                break;
            case 3:
                // Obtener datos de la venta
                System.out.print("Inserte el número de venta: ");
                idVenta = scanner.nextInt();
                System.out.print("Inserte el ID_CLIENTE: ");
                idCliente = scanner.nextInt();
                System.out.print("Inserte el ID_PRODUCTO: ");
                idProducto = scanner.nextInt();
                System.out.print("Inserte la cantidad de producto: ");
                cantidad = scanner.nextInt();

                // Llamar a la función para insertar la venta
                insertarVentaSQLite(idVenta, idCliente, idProducto, cantidad);
                break;
            case 4:
                System.out.println("Operación Cancelada");
            default:
                System.out.println("Opción no válida. Proporciona 1 para MySQL, 2 para Oracle, 3 para SQLite");
        }
    }

    private static void insertarVentaMySQL(int idVenta, int idCliente, int idProducto, int cantidad) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            // Realizar comprobaciones
            if (!validarExistenciaVenta(connection, idVenta) &&
                    !validarExistenciaCliente(connection, idCliente) &&
                    !validarExistenciaProducto(connection, idProducto) &&
                    cantidad > 0) {

                // Insertar la venta
                String insertVenta = "INSERT INTO VENTAS VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertVenta)) {
                    preparedStatement.setInt(1, idVenta);
                    preparedStatement.setString(2, obtenerFechaActual());
                    preparedStatement.setInt(3, idCliente);
                    preparedStatement.setInt(4, idProducto);
                    preparedStatement.setInt(5, cantidad);
                    preparedStatement.executeUpdate();
                    System.out.println("\nVenta insertada correctamente.");
                }
            } else {
                if (validarExistenciaVenta(connection, idVenta)) {
                    System.out.println("\u001B[31mError: El identificador de venta ya existe en la tabla VENTAS.\u001B[0m");
                }
                if (validarExistenciaCliente(connection, idCliente)) {
                    System.out.println("\u001B[31mError: El identificador de cliente no existe en la tabla CLIENTES.\u001B[0m");
                }
                if (validarExistenciaProducto(connection, idProducto)) {
                    System.out.println("\u001B[31mError: El identificador de producto no existe en la tabla PRODUCTOS.\u001B[0m");
                }
                if (cantidad <= 0) {
                    System.out.println("\u001B[31mError: La cantidad debe ser mayor que 0.\u001B[0m");
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertarVentaOracle(int idVenta, int idCliente, int idProducto, int cantidad) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "unidad2", "unidad2");

            // Realizar comprobaciones
            if (!validarExistenciaVenta(connection, idVenta) &&
                    !validarExistenciaCliente(connection, idCliente) &&
                    !validarExistenciaProducto(connection, idProducto) &&
                    cantidad > 0) {

                // Insertar la venta
                String insertVenta = "INSERT INTO VENTAS VALUES (?, TO_DATE(?, 'DD-MM-YYYY'), ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertVenta)) {
                    preparedStatement.setInt(1, idVenta);
                    preparedStatement.setString(2, obtenerFechaOracle());
                    preparedStatement.setInt(3, idCliente);
                    preparedStatement.setInt(4, idProducto);
                    preparedStatement.setInt(5, cantidad);
                    preparedStatement.executeUpdate();
                    System.out.println("\nVenta insertada correctamente.");
                }
            } else {
                if (validarExistenciaVenta(connection, idVenta)) {
                    System.out.println("\u001B[31mError: El identificador de venta ya existe en la tabla VENTAS.\u001B[0m");
                }
                if (validarExistenciaCliente(connection, idCliente)) {
                    System.out.println("\u001B[31mError: El identificador de cliente no existe en la tabla CLIENTES.\u001B[0m");
                }
                if (validarExistenciaProducto(connection, idProducto)) {
                    System.out.println("\u001B[31mError: El identificador de producto no existe en la tabla PRODUCTOS.\u001B[0m");
                }
                if (cantidad <= 0) {
                    System.out.println("\u001B[31mError: La cantidad debe ser mayor que 0.\u001B[0m");
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertarVentaSQLite(int idVenta, int idCliente, int idProducto, int cantidad) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/eclipse/UNI2/UNIDAD2.db");

            // Realizar comprobaciones
            if (!validarExistenciaVenta(connection, idVenta) && !validarExistenciaCliente(connection, idCliente) &&
                    !validarExistenciaProducto(connection, idProducto) && cantidad > 0) {

                // Insertar la venta
                String insertVenta = "INSERT INTO VENTAS VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertVenta)) {
                    preparedStatement.setInt(1, idVenta);
                    preparedStatement.setString(2, obtenerFechaActual());
                    preparedStatement.setInt(3, idCliente);
                    preparedStatement.setInt(4, idProducto);
                    preparedStatement.setInt(5, cantidad);
                    preparedStatement.executeUpdate();
                    System.out.println("\nVenta insertada correctamente.");

                }

            } else {
                if (validarExistenciaVenta(connection, idVenta)) {
                    System.out.println("\u001B[31mError: El identificador de venta ya existe en la tabla VENTAS.\u001B[0m");
                }
                if (validarExistenciaCliente(connection, idCliente)) {
                    System.out.println("\u001B[31mError: El identificador de cliente no existe en la tabla CLIENTES.\u001B[0m");
                }
                if (validarExistenciaProducto(connection, idProducto)) {
                    System.out.println("\u001B[31mError: El identificador de producto no existe en la tabla PRODUCTOS.\u001B[0m");
                }
                if (cantidad <= 0) {
                    System.out.println("\u001B[31mError: La cantidad debe ser mayor que 0.\u001B[0m");
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean validarExistenciaVenta(Connection connection, int idVenta) throws SQLException {
        String selectQuery = "SELECT * FROM VENTAS WHERE IDVENTA = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, idVenta);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
                // Devuelve "true" cuando encuentra un fila con ese ID
            }
        }
    }

    private static boolean validarExistenciaCliente(Connection connection, int idCliente) throws SQLException {
        String selectQuery = "SELECT * FROM CLIENTES WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, idCliente);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return !resultSet.next();
                // Realizamos las negación para que cuando encuntre el ID Devuelva "false"
            }
        }
    }

    private static boolean validarExistenciaProducto(Connection connection, int idProducto) throws SQLException {
        String selectQuery = "SELECT * FROM PRODUCTOS WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, idProducto);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return !resultSet.next();
                // Realizamos las negación para que cuando no encuntre el ID Devuelva "true"
            }
        }
    }

    /**
     * Método para obtener la fecha actual formateada al formato (MySQL)
     * @return Fecha formateda como una cadena de caracteres
     */
    private static String obtenerFechaActual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    /**
     * Método para obtener la fecha actual formateada al formato (Oracle)
     * @return Fecha formateda como una cadena de caracteres
     */
    private static String obtenerFechaOracle() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(new Date());
    }
}
