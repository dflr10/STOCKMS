
package Model;

import java.sql.*;

//Crea la clase UserDAO
public class UserDAO {

    //Crea las variables globales
    PreparedStatement ps;
    ResultSet rs;

    Conexion con = new Conexion();
    Connection access;

    //Se encarga de validar que el usuario exista en la base de datos, y carga los campos seleccionados en la query
    //a las variebles de la clase User.java
    //recibe como parámetro el nombre de usuario y contraseña ingresadas en el módulo de login.
    public User userValidator(String username, String password) {

        User user = new User();

        String query = "SELECT username, password, tipo_usuario_idtipo_usuario FROM usuario WHERE password=? and username=?";

        try {
            access = con.getConnection();
            ps = access.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, username);

            rs = ps.executeQuery();

            while (rs.next()) {
                
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setIdTipoUsuario(rs.getInt(3));

            }
        } catch (Exception e) {
        }
        //access=(Connection) con.disconnect();
        return user;

    }

}
