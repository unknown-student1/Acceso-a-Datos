package EjerciciosLibro;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.connector.OracleConnectionManager;
import org.sqlite.SQLiteConnection;

import java.sql.*;

public class Ejercicio5 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Por favor, proporciona un argumento válido: 1 para MySQL, 2 para Oracle, 3 para SQLite");
            return;
        }

        int opcion = Integer.parseInt(args[0]);

        switch (opcion) {
            case 1:
                llenarTablasMySQL();
                break;
            case 2:
                llenarTablasOracle();
                break;
            case 3:
                llenarTablasSQLite();
                break;
            default:
                System.out.println("Opción no válida. Proporciona 1 para MySQL, 2 para Oracle, 3 para SQLite");
        }
    }

    private static void llenarTablasMySQL() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unidad2",
                    "unidad2", "unidad2");

            // Llenar la tabla CLIENTES
            String insertClientes = "INSERT INTO CLIENTES VALUES (?, ?, ?, ?, ?, ?)";
            int filasInsertadasClientes = llenarTablaClientes(connection, insertClientes);
            System.out.println("Se han insertado " + filasInsertadasClientes + " filas en la tabla CLIENTES");

            // Llenar la tabla PRODUCTOS
            String insertProductos = "INSERT INTO PRODUCTOS VALUES (?, ?, ?, ?, ?)";
            int filasInsertadasProducto = llenarTablaProductos(connection, insertProductos);
            System.out.println("Se han insertado " + filasInsertadasProducto + " filas en la tabla PRODUCTOS");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void llenarTablasOracle() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "unidad2", "unidad2");

            // Llenar la tabla CLIENTES
            String insertClientes = "INSERT INTO CLIENTES VALUES (?, ?, ?, ?, ?, ?)";
            int filasInsertadasClientes = llenarTablaClientes(connection, insertClientes);
            System.out.println("Se han insertado " + filasInsertadasClientes + " filas en la tabla CLIENTES");

            // Llenar la tabla PRODUCTOS
            String insertProductos = "INSERT INTO PRODUCTOS VALUES (?, ?, ?, ?, ?)";
            int filasInsertadasProducto = llenarTablaProductos(connection, insertProductos);
            System.out.println("Se han insertado " + filasInsertadasProducto + " filas en la tabla PRODUCTOS");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void llenarTablasSQLite() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:C:/Users/Luis/IdeaProjects/PracticaConectores/UNIDAD2.db");

            // Llenar la tabla CLIENTES
            String insertClientes = "INSERT INTO CLIENTES VALUES (?, ?, ?, ?, ?, ?)";
            int filasInsertadasClientes = llenarTablaClientes(connection, insertClientes);
            System.out.println("Se han insertado " + filasInsertadasClientes + " filas en la tabla CLIENTES");

            // Llenar la tabla PRODUCTOS
            String insertProductos = "INSERT INTO PRODUCTOS VALUES (?, ?, ?, ?, ?)";
            int filasInsertadasProducto = llenarTablaProductos(connection, insertProductos);
            System.out.println("Se han insertado " + filasInsertadasProducto + " filas en la tabla PRODUCTOS");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int llenarTablaClientes(Connection connection, String insertQuery) throws SQLException {
        int filasInsertadas = 0;
        int[] codigosClientes = {1,2,3,4};

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // Inserción 1
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "MARIA SERRANO");
            preparedStatement.setString(3, "C/Las Flores 23");
            preparedStatement.setString(4, "Guadalajara");
            preparedStatement.setString(5, "949876655");
            preparedStatement.setString(6, "34343434L");
            filasInsertadas += preparedStatement.executeUpdate();

            // Inserción 2
            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, "PEDRO BRAVO");
            preparedStatement.setString(3, "C/Galiano 6");
            preparedStatement.setString(4, "Guadalajara");
            preparedStatement.setString(5, "949256376");
            preparedStatement.setString(6, "2256880E");
            filasInsertadas += preparedStatement.executeUpdate();

            // Inserción 3
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "MANUEL SERRA");
            preparedStatement.setString(3, "Av Atance 24");
            preparedStatement.setString(4, "Guadalajara");
            preparedStatement.setString(5, "949800090");
            preparedStatement.setString(6, "1234567E");
            filasInsertadas += preparedStatement.executeUpdate();

            // Inserción 4
            preparedStatement.setInt(1, 4);
            preparedStatement.setString(2, "ALICIA PÉREZ");
            preparedStatement.setString(3, "C/La Azucena 123");
            preparedStatement.setString(4, "Talavera");
            preparedStatement.setString(5, "925678090");
            preparedStatement.setString(6, "56564564J");
            filasInsertadas += preparedStatement.executeUpdate();

        }

        imprimirClientesInsertados(connection,codigosClientes);
        return filasInsertadas;
    }

    private static int llenarTablaProductos(Connection connection, String insertQuery) throws SQLException {
        int filasInsertadas = 0;
        int[] codigosProductos = {4,5,6,7,8};

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Inserción 1
            preparedStatement.setInt(1, 4);
            preparedStatement.setString(2, "Diccionario Maria Moliner 2 tomos");
            preparedStatement.setInt(3, 55);
            preparedStatement.setInt(4, 5);
            preparedStatement.setDouble(5, 43.00);
            filasInsertadas += preparedStatement.executeUpdate();

            // Inserción 2
            preparedStatement.setInt(1, 5);
            preparedStatement.setString(2, "Impresora HP Deskjet F370");
            preparedStatement.setInt(3, 10);
            preparedStatement.setInt(4, 1);
            preparedStatement.setDouble(5, 30.65);
            filasInsertadas += preparedStatement.executeUpdate();

            // Inserción 3
            preparedStatement.setInt(1, 6);
            preparedStatement.setString(2, "Pen Drive 8 Gigas");
            preparedStatement.setInt(3, 52);
            preparedStatement.setInt(4, 5);
            preparedStatement.setDouble(5, 7.00);
            filasInsertadas += preparedStatement.executeUpdate();

            // Inserción 4
            preparedStatement.setInt(1, 7);
            preparedStatement.setString(2, "Ratón óptico inalámbrico Logitecht");
            preparedStatement.setInt(3, 14);
            preparedStatement.setInt(4, 2);
            preparedStatement.setDouble(5, 15.00);
            filasInsertadas += preparedStatement.executeUpdate();

            // Inserción 5
            preparedStatement.setInt(1, 8);
            preparedStatement.setString(2, "El señor de los anillos, 3 DVDs");
            preparedStatement.setInt(3, 8);
            preparedStatement.setInt(4, 2);
            preparedStatement.setDouble(5, 25.00);
            filasInsertadas += preparedStatement.executeUpdate();
        }

        imprimirProductosInsertados(connection,codigosProductos);
        return filasInsertadas;
    }

    public static void imprimirClientesInsertados (Connection connection, int[] idClientes) throws SQLException{
        // Creamos un string con la sentencia
        String sql = "SELECT * FROM CLIENTES WHERE id = ?";
        System.out.println("\n---------------CLIENTES INSERTADOS---------------");

        try {
            // Recorrerremos la matriz con los id de lso clientes
            for (int i = 0; i < idClientes.length; i++) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idClientes[i]);
                ResultSet resultSet = preparedStatement.executeQuery();

                // Imprimir los datos de cada fila
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String direccion = resultSet.getString("direccion");
                    String poblacion = resultSet.getString("poblacion");
                    String telefono = resultSet.getString("telef");
                    String nif = resultSet.getString("nif");

                    System.out.println("ID: " + id);
                    System.out.println("NOMBRE: " + nombre);
                    System.out.println("DIRECCION: " + direccion);
                    System.out.println("POBLACION: " + poblacion);
                    System.out.println("TELEFONO: " + telefono);
                    System.out.println("NIF: " + nif);
                    System.out.println();
                }

                // Cerrar el ResultSet y el PreparedStatement
                resultSet.close();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            // Captura la excepción y maneja el error
            System.out.println("\u001BError: No se ha podido imprimir los datos insertados\u001B[0m");
        }
    }

    public static void imprimirProductosInsertados (Connection connection, int[] idProductos) throws SQLException{
        // Creamos un string con la sentencia
        String sql = "SELECT * FROM PRODUCTOS WHERE id = ?";
        System.out.println("\n---------------PRODUCTOS INSERTADOS---------------");

        try {
            // Recorrerremos la matriz con los id de los productos
            for (int i = 0; i < idProductos.length; i++) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idProductos[i]);
                ResultSet resultSet = preparedStatement.executeQuery();

                // Imprimir los datos de cada fila
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String descripcion = resultSet.getString("descripcion");
                    int stockActual = resultSet.getInt("stockactual");
                    int stockMinimo = resultSet.getInt("stockminimo");
                    double pvp = resultSet.getDouble("pvp");

                    System.out.println("ID: " + id);
                    System.out.println("DESCRIPCION: " + descripcion);
                    System.out.println("STOCK_ACTUAL: " + stockActual);
                    System.out.println("STOCK_MINIMO: " + stockMinimo);
                    System.out.println("PVP: " + pvp);
                    System.out.println();
                }

                // Cerrar el ResultSet y el PreparedStatement
                resultSet.close();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            // Captura la excepción y maneja el error
            System.out.println("\u001BError: No se ha podido imprimir los datos insertados\u001B[0m");
        }
    }
}

