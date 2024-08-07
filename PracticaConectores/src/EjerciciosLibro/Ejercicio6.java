package EjerciciosLibro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ejercicio6 {

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Por favor, proporciona los argumentos necesarios: " +
                    "[1-MySQL/2-Oracle/3-SQLite] [ID_VENTA] [ID_CLIENTE] [ID_PRODUCTO] [CANTIDAD]");
            return;
        }

        int opcion = Integer.parseInt(args[0]);
        int idVenta = Integer.parseInt(args[1]);
        int idCliente = Integer.parseInt(args[2]);
        int idProducto = Integer.parseInt(args[3]);
        int cantidad = Integer.parseInt(args[4]);

        switch (opcion) {
            case 1:
                insertarVentaMySQL(idVenta, idCliente, idProducto, cantidad);
                break;
            case 2:
                insertarVentaOracle(idVenta, idCliente, idProducto, cantidad);
                break;
            case 3:
                insertarVentaSQLite(idVenta, idCliente, idProducto, cantidad);
                break;
            default:
                System.out.println("Opción no válida. Proporciona 1 para MySQL, 2 para Oracle, 3 para SQLite");
        }
    }

    private static void insertarVentaMySQL(int idVenta, int idCliente, int idProducto, int cantidad) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unidad2",
                    "unidad2", "unidad2");

            // Realizar comprobaciones
            if (!validarExistenciaVenta(connection, idVenta) &&
                    !validarExistenciaCliente(connection, idCliente) &&
                    !validarExistenciaProducto(connection, idProducto) &&
                    cantidad <= 0) {

                // Insertar la venta
                String insertVenta = "INSERT INTO VENTAS VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertVenta)) {
                    preparedStatement.setInt(1, idVenta);
                    preparedStatement.setString(2, obtenerFechaActual());
                    preparedStatement.setInt(3, idCliente);
                    preparedStatement.setInt(4, idProducto);
                    preparedStatement.setInt(5, cantidad);
                    preparedStatement.executeUpdate();
                    System.out.println("Venta insertada correctamente.");
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
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "unidad2", "unidad2");

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
                    System.out.println("Venta insertada correctamente.");
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
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:C:/Users/Luis/IdeaProjects/PracticaConectores/UNIDAD2.db");

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
                    System.out.println("Venta insertada correctamente.");
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
                // Realizamos las negación para que cuando no encuntre el ID Devuelva "true"
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
     * Método para obtener la fehca actual formateada al formato (MySQL)
     * @return Fecha formateda como una cadena de caracteres
     */
    private static String obtenerFechaActual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    /**
     * Método para obtener la fehca actual formateada al formato (Oracle)
     * @return Fecha formateda como una cadena de caracteres
     */
    private static String obtenerFechaOracle() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(new Date());
    }
}
