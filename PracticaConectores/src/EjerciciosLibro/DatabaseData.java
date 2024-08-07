package EjerciciosLibro;

import java.sql.*;

public class DatabaseData {

    public static void main(String[] args) {
        // Valores Base Datos MySQL
        String url = "jdbc:mysql://localhost:3306/unidad2";
        String usuario = "unidad2";
        String contraseña = "unidad2";

        try {
            // Establecer la conexión con la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Obtenemos los datos a traves del objeto DatabaseMetaData
            DatabaseMetaData metaData = conexion.getMetaData();

            // Imprimir información sobre la base de datos
            System.out.println("Información de la base de datos:");
            System.out.println("Nombre de la base de datos: " + metaData.getDatabaseProductName());
            System.out.println("Versión de la base de datos: " + metaData.getDatabaseProductVersion());
            System.out.println("Controlador JDBC utilizado: " + metaData.getDriverName());
            System.out.println("Versión del controlador JDBC: " + metaData.getDriverVersion());
            System.out.println("--------------------------------");

            // Mostrar información sobre el esquema y las tablas
            System.out.println("\nInformación del esquema y las tablas:");

            // Obtener el nombre del esquema actual
            String schema = conexion.getSchema();
            System.out.println("Esquema actual: " + schema);

            // Obtener las tablas en el esquema actual
            try (ResultSet tables = metaData.getTables(null, schema, "%", null)) {
                while (tables.next()) {
                    // Obtenemos la información de las tablas
                    String tableName = tables.getString("TABLE_NAME");
                    String esquema = tables.getString("TABLE_SCHEM");
                    String catalogo = tables.getString("TABLE_CAT");
                    String tipo = tables.getString("TABLE_TYPE");

                    System.out.println("___________________TABLE_INFO___________________");
                    // Imprimimos la información de las tablas
                    System.out.println("\nTabla: " + tableName);
                    System.out.println("Esquema: " + esquema);
                    System.out.println("Catalogo: " + catalogo);
                    System.out.println("Tipo: " + tipo);

                    // Obtener columnas de las tablas
                    ResultSet columnas = metaData.getColumns(catalogo, null, tableName, "%");
                    System.out.println("___________________COLUM_INFO: " + tableName + "___________________");
                    while (columnas.next()) {
                        // Obtenemos la información de las columnas
                        String columnName = columnas.getString("COLUMN_NAME");
                        String columntType = columnas.getString("TYPE_NAME");
                        String columnSize = columnas.getString("COLUMN_SIZE");

                        // Imprimir la información de las columnas
                        System.out.println("\nColumn Name: " + columnName);
                        System.out.println("Tipo: " + columntType);
                        System.out.println("Tamaño: " + columnSize);
                    }
                }
            }
            // Cerrar la conexión
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}