package Ejercicio2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Creamos un objeto Scanner para leer la entrada estandar desde teclado
        Scanner scanner = new Scanner(System.in);
        boolean salir = false; // Variable de estado

        while (!salir){
        System.out.println("1 - Insertar Departamento (Argumentos)");
        System.out.println("2 - Insertar Departamento (Objeto)");
        System.out.println("3 - Listar Departamentos (ArrayList)");
        System.out.println("4 - Lista Empleados (Departamento)");
        System.out.println("5 - Cambiar nombre (Departemento)");
        System.out.println("6 - Actualizar Localidad (Departamento)");
        System.out.println("7 - Aumentar sueldo (Empleados x Departamento)");
        System.out.println("8 - Mostrar Esquema (Base Datos)");
        System.out.println("9 - Salir");

        // Recoger opcion del usuario
        System.out.print("\nInserte una opción: ");
        int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Obtener datos de darpartamento
                    System.out.print("Nº DEPARTAMENTO: ");
                    int deptNo = scanner.nextInt();
                    System.out.print("NOMBRE: ");
                    String dnombre = scanner.next();
                    System.out.print("LOCALIDAD: ");
                    String loc = scanner.next();

                    // Llamada al método para insertar departamento
                    insertarDepartamento(deptNo,dnombre,loc);
                    break;
                case 2:
                    // Obtener datos de darpartamento
                    System.out.print("Nº DEPARTAMENTO: ");
                    deptNo = scanner.nextInt();
                    System.out.print("NOMBRE: ");
                    dnombre = scanner.next();
                    System.out.print("LOCALIDAD: ");
                    loc = scanner.next();

                    // Crear un departamento
                    Departamento departamento = new Departamento(deptNo,dnombre,loc);

                    // Llamada al método para insertar departamento
                    insertarObjetoDepartamento(departamento);
                    break;
                case 3:
                    ArrayList<Departamento> departamentosLista = obtenerDepartamentos();

                    // Imprimir todos los departamentos usando toString
                    for (Departamento dept : departamentosLista) {
                        System.out.println(dept.toString());
                    }
                    break;
                case 4:
                    // Obtener datos de darpartamento
                    System.out.print("Nº DEPARTAMENTO: ");
                    deptNo = scanner.nextInt();

                    ArrayList<Empleado> empleadosLista = ObtenerEmpleadoDept(deptNo);
                    for (Empleado empleado : empleadosLista) {
                        System.out.println(empleado.toString());
                    }
                    break;
                case 5:
                    System.out.print("Nº DEPARTAMENTO: ");
                    deptNo = scanner.nextInt();
                    System.out.print("NOMBRE(NEW): ");
                    dnombre = scanner.next();

                    int filasAfectadas = actualizarNombreDepartamento(deptNo, dnombre);

                    System.out.println("Número de filas afectadas: " + filasAfectadas + "\n");
                    break;
                case 6:
                    // Obtener la lista de departamentos
                    departamentosLista = obtenerDepartamentos();

                    // Obntener numero departamento
                    System.out.print("Nº DEPARTAMENTO: ");
                    deptNo = scanner.nextInt();

                    // Buscar departamento por el DeptNo
                    Departamento departamento1 = obtenerDepartamentoPorID(departamentosLista, deptNo);

                    if (departamento1 != null){
                        // Obtener Localidad
                        System.out.print("LOCALIDAD: ");
                        loc = scanner.next();

                        filasAfectadas = actualizarLocalidadDepartamento(departamento1,loc);
                        System.out.println("Número de filas afectadas: " + filasAfectadas + "\n");
                    }else {
                        System.out.println("Error: No se ha encontrado ningun departamento con ese ID\n");
                    }
                    break;
                case 7:
                    System.out.print("Nº DEPARTAMENTO: ");
                    deptNo = scanner.nextInt();
                    System.out.print("Incremento: ");
                    int incremento = scanner.nextInt();

                    filasAfectadas = incrementarSalarioEmpleadosPorDepartamento(deptNo,incremento);
                    System.out.println("Número de filas afectadas: " + filasAfectadas + "\n");
                    break;
                case 8:
                    imprimirTablasYVistas();
                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida." + "\n");
                    break;
            }
        }
    }

    /**
     * Método para insertar un departamento
     * @param deptNo
     * @param dnombre
     * @param loc
     */
    public static void insertarDepartamento(int deptNo, String dnombre, String loc) {
        String insertDepartamento = "INSERT INTO departamentos (dept_no, dnombre, loc) VALUES (?, ?, ?)";
        try {
            Connection conexion = Conexion.conectar();
            PreparedStatement preparedStatement = conexion.prepareStatement(insertDepartamento);
            preparedStatement.setInt(1, deptNo);
            preparedStatement.setString(2, dnombre);
            preparedStatement.setString(3, loc);
            preparedStatement.executeUpdate();
            System.out.println("Departamento insertado correctamente\n");
        }catch (SQLException e){
            System.out.println("Error al insertar el departamento: " + e.getMessage() + "\n");
        }
    }

    /**
     * Método para insertar un departamento (@Object Departameto)
     * @param departamento
     */
    public static void insertarObjetoDepartamento(Departamento departamento) {
        String insertDepartamento = "INSERT INTO departamentos (dept_no, dnombre, loc) VALUES (?, ?, ?)";
        try {
            Connection conexion = Conexion.conectar();
            PreparedStatement preparedStatement = conexion.prepareStatement(insertDepartamento);
            preparedStatement.setInt(1, departamento.getDeptNo());
            preparedStatement.setString(2, departamento.getDnombre());
            preparedStatement.setString(3, departamento.getLoc());
            preparedStatement.executeUpdate();
            System.out.println("Departamento insertado correctamente\n");
        }catch (SQLException e){
            System.out.println("Error al insertar el departamento: " + e.getMessage() + "\n");
        }
    }

    /**
     * Método para obtener todos los departamentos de la base de datos
     * @return listaDepartamentos ArrayList<Departamento>
     */
    public static ArrayList<Departamento> obtenerDepartamentos(){
        // Variable para almacenar la sentencia SQL
        String sql = "SELECT * FROM departamentos";

        // Crear obejeto ArrayList para alamacenar los departamentos
        ArrayList<Departamento> listaDepartamentos = new ArrayList<>();

        try{
            Connection conexion = Conexion.conectar();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int deptNo = resultSet.getInt("dept_no");
                String dnombre = resultSet.getString("dnombre");
                String loc = resultSet.getString("loc");
                Departamento departamento = new Departamento(deptNo, dnombre, loc);
                listaDepartamentos.add(departamento);
            }
        }catch (SQLException e){
            System.out.println("Error al obtener los departamentos: " + e.getMessage() + "\n");
        }
        return listaDepartamentos;
    }

    /**
     * Método para obtener los empleados por departamento
     * @param deptNo
     * @return listaEmpleados ArrayList<Empleado>
     */
    public static ArrayList<Empleado> ObtenerEmpleadoDept(int deptNo) {
        // Variable para almacenar la sentencia SQL
        String sql = "SELECT * FROM empleados WHERE dept_no = ?";

        // Crear obejeto ArrayList para alamacenar los empleados
        ArrayList<Empleado> empleados = new ArrayList<>();

        try {
            Connection conexion = Conexion.conectar();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, deptNo);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int empNo = resultSet.getInt("emp_no");
                String apellido = resultSet.getString("apellido");
                String oficio = resultSet.getString("oficio");
                Integer dir = (Integer) resultSet.getObject("dir"); // null safe
                Date fechaAlt = new Date(resultSet.getDate("fecha_alt").getTime());
                float salario = resultSet.getFloat("salario");
                Float comision = resultSet.getObject("comision") != null ? resultSet.getFloat("comision") : null;
                Empleado empleado = new Empleado(empNo, apellido, oficio, dir, fechaAlt, salario, comision, deptNo);
                empleados.add(empleado);
            }

        }catch (SQLException e){
            System.out.println("Error al obtener empleados del departamento " + deptNo + ": " + e.getMessage() + "\n");
        }

        return empleados;
    }

    /**
     * Método para buscar el departamento por el DeptNo
     * @param departamentos Lista con todos los departamentos
     * @param deptNo El número departamento para buscar en la lista
     * @return El departamento si ha sido encontrado
     */
    public static Departamento obtenerDepartamentoPorID(ArrayList<Departamento> departamentos, int deptNo){
        for (Departamento departamento : departamentos){
            if (departamento.getDeptNo() == deptNo){
                return departamento;
            }
        }
        return null;
    }

    /**
     * Método para actualizar el nombre de un departamento.
     * @param deptNo El número del departamento que se va a actualizar.
     * @param nuevoNombre El nuevo nombre para el departamento.
     * @return El número de filas afectadas por la operación de actualización.
     */
    public static int actualizarNombreDepartamento(int deptNo, String nuevoNombre) {
        String sql = "UPDATE departamentos SET dnombre = ? WHERE dept_no = ?";
        int filasAfectadas = 0;

        try {
            Connection conexion = Conexion.conectar();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, nuevoNombre);
            preparedStatement.setInt(2, deptNo);

            filasAfectadas = preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Error al actualizar el nombre del departamento: " + e.getMessage() + "\n");
        }

        return filasAfectadas;
    }

    /**
     * Método para actualizar la localidad de un departamento utilizando un procedimiento almacenado.
     * @param departamento El objeto Departamento que contiene los datos a actualizar.
     * @param loc Nueva locaclidad a actualizar.
     * @return El número de filas afectadas por la operación.
     */
    public static int actualizarLocalidadDepartamento(Departamento departamento, String loc) {
        String procedimiento = "{CALL actualizaDept(?, ?)}";
        int filasAfectadas = 0;

        try{
            Connection conexion = Conexion.conectar();
            CallableStatement callableStatement = conexion.prepareCall(procedimiento);

            callableStatement.setInt(1, departamento.getDeptNo());  // Número de departamento
            callableStatement.setString(2, loc);  // Nueva localidad

            filasAfectadas = callableStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error al actualizar la localidad del departamento: " + e.getMessage() + "\n");
        }
        return filasAfectadas;
    }

    /**
     * Método para incrementar el salario de todos los empleados de un departamento
     * @param deptNo El número del departamento cuyos empleados recibirán un aumento de salario.
     * @param incremento La cantidad que se añadirá al salario actual de cada empleado.
     * @return El número de filas afectadas por la operación de actualización.
     */
    public static int incrementarSalarioEmpleadosPorDepartamento(int deptNo, float incremento) {
        String sql = "UPDATE empleados SET salario = salario + ? WHERE dept_no = ?";
        int filasAfectadas = 0;

        try{
            Connection conexion = Conexion.conectar();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setFloat(1, incremento);
            preparedStatement.setInt(2, deptNo);

            filasAfectadas = preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Error al incrementar el salario de los empleados: " + e.getMessage() + "\n");
        }

        return filasAfectadas;
    }

    /**
     * Método que muestra tablas y vistas del esquema actual.
     */
    public static void imprimirTablasYVistas() {


        try {
            Connection conexion = Conexion.conectar();

            // Obtenemos los datos a traves del objeto DatabaseMetaData
            DatabaseMetaData metaData = conexion.getMetaData();

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
            System.out.println("Error al mostrar el esquema: " + e + "\n");
        }
    }
}
