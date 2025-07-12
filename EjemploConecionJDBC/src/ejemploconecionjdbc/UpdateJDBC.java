/*package ejemploconecionjdbc;

/**
 *
 * @author David
 */
/*import java.sql.*;

public class UpdateJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/prueba";  // Nombre de la base de datos
        String usuario = "root";
        String contraseña = "901019Fn.";

        String nuevoNombre = "Carlos Ramírez";
        String nuevoDomicilio = "Cra 5A";
        String nuevoTelefono = "315 7241772";
        int idRegistro = 10;

       
         // Sentencia para actualizar
        String sqlUpdate = "UPDATE libreta SET nombre = ?, domicilio = ?, telefono = ? WHERE id = ?";

        // Sentencia para mostrar el registro actualizado
        String sqlSelect = "SELECT id, nombre, domicilio, telefono FROM libreta WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {

            // Actualizar registro
            try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                stmtUpdate.setString(1, nuevoNombre);
                stmtUpdate.setString(2, nuevoDomicilio);
                stmtUpdate.setString(3, nuevoTelefono);
                stmtUpdate.setInt(4, idRegistro);
                int filas = stmtUpdate.executeUpdate();
                System.out.println("Registros actualizados: " + filas);
            }

            // Mostrar datos actualizados
            try (PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
                stmtSelect.setInt(1, idRegistro);
                ResultSet resultado = stmtSelect.executeQuery();

                if (resultado.next()) {
                    System.out.println("Datos actualizados:");
                    System.out.println("Id: " + resultado.getString("id"));
                    System.out.println("Nombre: " + resultado.getString("nombre"));
                    System.out.println("Domicilio: " + resultado.getString("domicilio"));
                    System.out.println("Telefono: " + resultado.getString("telefono"));
                } else {
                    System.out.println("No se encontró el registro con ID: " + idRegistro);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/


package ejemploconecionjdbc;

/**
 *
 * @author David
 */
import java.sql.*;

public class UpdateJDBC {

    public static void main(String[] args) {
        
        String usuario = "root";
        String password = "901019Fn.";
        String url = "jdbc:mysql://localhost:3306/prueba";
        Connection conexion;
        Statement statement;
        ResultSet rs;

        int idActualizar = 5; //  Acá se cambia este valor al ID del registro que quieres actualizar
        String nuevoNombre = "Ana Gonzalez";
        String nuevoDomicilio = "Carrera 45 #22";
        String nuevoTelefono = "3201234567";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(CreateJDBC.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        System.out.println("Driver cargado correctamente");
        
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();

            //  Sentencia UPDATE para modificar los tres campos
            String updateSQL = String.format(
                "UPDATE libreta SET nombre = '%s', domicilio = '%s', telefono = '%s' WHERE id = %d",
                nuevoNombre, nuevoDomicilio, nuevoTelefono, idActualizar
            );
            int filas = statement.executeUpdate(updateSQL);
            System.out.println("Filas actualizadas: " + filas);

            // Muestra el registro actualizado
            rs = statement.executeQuery("SELECT * FROM prueba.libreta WHERE id = " + idActualizar);
            if (rs.next()) {
                System.out.println("Registro actualizado:");
                System.out.println(rs.getInt("id") + " : " + rs.getString("nombre") + " - " + rs.getString("domicilio") + " - " + rs.getString("telefono"));
            } else {
                System.out.println("No se encontró el registro con ID " + idActualizar);
            }
            
        } catch (SQLException ex) {
            System.getLogger(CreateJDBC.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }
}