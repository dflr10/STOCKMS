
package Model;

/**
 * 
 * @author Daniel Felipe Lozada Ramirez Dev <felipe_lozada04102@elpoli.edu.co>
 */
//Crea la clase User
public class User {

    //Declaración de variables globales de User
    private int idUsuario;
    private String username;
    private String password;
    private int idTipoUsuario;
    private String nombre_tipo_usuario;

    //Constructor vacío de la clase User
    public User() {
    }

    //Constructor de la clase User
    public User(int idUsuario, String username, String password,  int idTipoUsuario) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.idTipoUsuario = idTipoUsuario;
    }

    //Setters y Getters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombre_tipo_usuario() {
        return nombre_tipo_usuario;
    }

    public void setNombre_tipo_usuario(String nombre_tipo_usuario) {
        this.nombre_tipo_usuario = nombre_tipo_usuario;
    }
}
