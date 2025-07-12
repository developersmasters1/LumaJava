package ejemploconecionjdbc;

/**
 * @author David
 */
import java.sql.*;

public class ReadJDBC {

    public static void main(String[] args) {

        String usuario = "root";
        String password = "901019Fn.";
        String url = "jdbc:mysql://localhost:3306/prueba";
        Connection conexion;
        Statement statement;
        ResultSet rs;

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(ReadJDBC.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        System.out.println("Driver cargado correctamente");

        try {
            // Conexión y lectura
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();

            // Consulta para leer todos los registros
            rs = statement.executeQuery("SELECT * FROM libreta");

            System.out.println("Datos de la tabla 'libreta':");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                String telefono = rs.getString("telefono");

                System.out.println(id + " : " + nombre + " - " + domicilio + " - " + telefono);
            }

        } catch (SQLException ex) {
            System.getLogger(ReadJDBC.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }
}

