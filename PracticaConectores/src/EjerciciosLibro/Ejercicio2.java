package EjerciciosLibro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ejercicio2 {

    public static void main(String[] args) {
        if (args.length != 7) {
            System.out.println("Por favor, proporciona los siguientes argumentos: EMP_NO APELLIDO OFICIO DIR SALARIO COMISION DEPT_NO");
            return;
        }

        int empNo = Integer.parseInt(args[0]);
        String apellido = args[1];
        String oficio = args[2];
        int dir = Integer.parseInt(args[3]);
        float salario = Float.parseFloat(args[4]);
        float comision = Float.parseFloat(args[5]);
        int deptNo = Integer.parseInt(args[6]);

        try {
            // Establecer conexión a la base de datos
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "root");

            String errores = "";


            // Comprobar si el departamento existe
            if (!existeDepartamento(connection, deptNo)) {
                errores += "Error: El departamento no existe en la tabla departamentos.\n";
            }

            // Comprobar si el número de empleado ya existe
            if (existeEmpleado(connection, empNo)) {
                errores += "Error: El número de empleado ya existe en la tabla empleados.\n";
            }

            // Comprobar si el salario es mayor que 0
            if (salario <= 0) {
                errores += "Error: El salario debe ser mayor que 0.\n";
            }

            // Comprobar si el director existe en la tabla empleados
            if (!existeEmpleado(connection, dir)) {
                errores += "Error: El director no existe en la tabla empleados.\n";
            }

            // Comprobar si APELLIDO y OFICIO no son nulos
            if (apellido == null || oficio == null) {
                errores += "Error: APELLIDO y OFICIO no pueden ser nulos.\n";
            }

            if (!errores.isEmpty()) {
                System.out.println(errores);
                return;
            }

            // Obtener la fecha actual
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaAlta = dateFormat.format(new Date());

            // Preparar la sentencia SQL para la inserción
            String sql = "INSERT INTO empleados (emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, empNo);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, oficio);
            preparedStatement.setInt(4, dir);
            preparedStatement.setString(5, fechaAlta);
            preparedStatement.setFloat(6, salario);
            preparedStatement.setFloat(7, comision);
            preparedStatement.setInt(8, deptNo);

            // Ejecutar la inserción
            preparedStatement.executeUpdate();

            // Cerrar la conexión
            connection.close();

            System.out.println("Empleado insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean existeDepartamento(Connection connection, int deptNo) throws SQLException {
        String sql = "SELECT * FROM departamentos WHERE dept_no = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deptNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    private static boolean existeEmpleado(Connection connection, int empNo) throws SQLException {
        String sql = "SELECT * FROM empleados WHERE emp_no = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, empNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}
