package EjerciciosLibro;

import org.sqlite.SQLiteConnection;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Scanner;

public class TestSQLite {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Insertar Datos");
        System.out.println("2 - Cancelar Operación");
        System.out.print("Introduzca opción: ");
        int userinput = scanner.nextInt();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:C:/Users/Luis/IdeaProjects/PracticaConectores/UNIDAD2.db");


        switch (userinput){
            case 1:
                llenarTablasSQLite(dataSource);
                break;
            case 2:
                System.out.println("Operación Cancelada");
                break;
            default:
                System.out.println("La opción introducida es inválida");
        }
    }

    private static void llenarTablasSQLite(SQLiteDataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            // Llenar la tabla CLIENTES
            String insertClientes = "INSERT INTO CLIENTES VALUES (?, ?, ?, ?, ?, ?)";
            int filasInsertadasClientes = llenarTablaClientes((SQLiteConnection) connection, insertClientes);
            System.out.println("Se han insertado " + filasInsertadasClientes + " filas en la tabla CLIENTES");

            // Llenar la tabla PRODUCTOS
            String insertProductos = "INSERT INTO PRODUCTOS VALUES (?, ?, ?, ?, ?)";
            int filasInsertadasProductos = llenarTablaProductos((SQLiteConnection) connection, insertProductos);
            System.out.println("Se han insertado " + filasInsertadasProductos + " filas en la tabla PRODUCTOS");
        } catch (SQLException e) {
            // Establecer el color del error
            String resetColor = "\u001B[0m";
            String redColor = "\u001B[31m";

            System.out.println(redColor + "Error: No ha sido posible establecer la conexión con la base de datos" + resetColor);
            System.out.println("\u001B[31mError: No se ha podido imprimir los datos insertados\u001B[0m");
        }
    }

    private static int llenarTablaClientes(SQLiteConnection connection, String insertQuery) throws SQLException {
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

    private static int llenarTablaProductos(SQLiteConnection connection, String insertQuery) throws SQLException {
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
            preparedStatement.setString(2, "Ratón óptico inalámbrico Logitech");
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

    public static void imprimirClientesInsertados (SQLiteConnection connection, int[] idClientes) throws SQLException{
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
            // Establecer el color del error
            String resetColor = "\u001B[0m";
            String redColor = "\u001B[31m";

            System.out.println(redColor + "Error: No ha sido posible establecer la conexión con la base de datos" + resetColor);
        }
    }

    public static void imprimirProductosInsertados (SQLiteConnection connection, int[] idProductos) throws SQLException{
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