package ejemploconecionjdbc;

/**
 * @author David
 */
import java.sql.*;

public class DeleteJDBC {

    public static void main(String[] args) {

        String usuario = "root";
        String password = "901019Fn.";
        String url = "jdbc:mysql://localhost:3306/prueba";
        Connection conexion;
        Statement statement;
        ResultSet rs;
        int idEliminar = 12; // Cambia este valor por el ID que quieres eliminar

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(DeleteJDBC.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        System.out.println("Driver cargado correctamente");
        

        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();

           //Eliminar registro por ID
            String sqlDelete = "DELETE FROM prueba.libreta WHERE id = " + idEliminar;
            statement.executeUpdate(sqlDelete);
            System.out.println("Registro eliminado: " + idEliminar);
            
            //Consultar todos los registros
            rs = statement.executeQuery("SELECT * FROM prueba.libreta");
            System.out.println("Registros actuales en la tabla:");
            rs.next();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                String telefono = rs.getString("telefono");

                System.out.println(id + " : " + nombre + " - " + domicilio + " - " + telefono);
            }

        } catch (SQLException ex) {
            System.getLogger(DeleteJDBC.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}