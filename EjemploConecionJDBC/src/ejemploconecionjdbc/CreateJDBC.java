package ejemploconecionjdbc;
/**
 *
 * @author David
 */
// Importación de librerías necesarias para manejar bases de datos y logs
import java.sql.*; // Importa todas las clases de JDBC necesarias para la conexión a MySQL
import java.util.logging.Level;
import java.util.logging.Logger;

// Definición de la clase principal
public class CreateJDBC {

    // Método principal que ejecuta el programa
    public static void main(String[] args) throws SQLException {
        
        // Credenciales de acceso a la base de datos
        String usuario = "root"; // Usuario de MySQL
        String password = "901019Fn."; // Contraseña del usuario de MySQL
        String url = "jdbc:mysql://localhost:3306/prueba"; // URL de conexión a la base de datos

        // Variables necesarias para la conexión y ejecución de consultas
        Connection conexion; // Objeto que representa la conexión a la base de datos
        Statement statement; // Objeto para ejecutar sentencias SQL
        ResultSet rs; // Objeto para almacenar los resultados de una consulta SQL

        try {
            // Cargar el controlador JDBC de MySQL en tiempo de ejecución
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            // Registrar un mensaje de error si el driver no se encuentra
            Logger.getLogger(CreateJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Mensaje en consola indicando que el driver se ha cargado correctamente
        System.out.println("Driver cargado correctamente.");

        try {
            // Establecer conexión con la base de datos usando las credenciales
            conexion = DriverManager.getConnection(url, usuario, password);
            
            // Crear un objeto Statement para ejecutar consultas SQL
            statement = conexion.createStatement();

            // Ejecutar una sentencia SQL para insertar un nuevo registro en la tabla "libreta"
            statement.executeUpdate("INSERT INTO prueba.libreta(nombre, domicilio, telefono) VALUES('Paco', 'Av 72 #123', '3163163165')");

            // Ejecutar una consulta SQL para obtener todos los registros de la tabla "libreta"
            rs = statement.executeQuery("SELECT * FROM prueba.libreta");

            // Mueve el cursor del ResultSet al primer resultado
            rs.next(); 

            // Iterar sobre los resultados de la consulta y mostrarlos en consola
            do {
                // Imprime los datos del usuario obtenidos de la base de datos
                System.out.println(rs.getInt("id") + " : " + rs.getString("Nombre") + " : " + rs.getString("Domicilio") + rs.getString("telefono"));
            } while (rs.next()); // Continúa recorriendo el ResultSet mientras haya más registros

        } catch (SQLException sQLException) {
            // Registrar un mensaje de error si ocurre un problema en la ejecución de la consulta
            Logger.getLogger(CreateJDBC.class.getName()).log(Level.SEVERE, null, sQLException);
        }
    }
}