package Controller;

import Model.Conexion;
import Model.User;
import Model.UserDAO;
import View.Home;
import View.LoginUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Felipe Lozada Ramirez Dev <felipe_lozada04102@elpoli.edu.co>
 */
public class LoginController implements ActionListener {

    private final LoginUI log;
    UserDAO udao = new UserDAO();
    User u;

    public LoginController(LoginUI log) {
        this.log = log;

        this.log.loginButton.addActionListener(this);
        this.log.exitButton.addActionListener(this);
        this.log.txtPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    validateLogin();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == log.loginButton) {
            validateLogin();
        }
        if (e.getSource() == log.exitButton) {
            turnOffDB();
            closeApp();
        }
    }

    //CONFIRMAR ACCIÓN DE SALIR
    public void closeApp() {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(null, "¿Realmente desea salir de la aplicación?", "Saliendo de STOCKMS",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
        }
    }

    //Desconecta la base de datos
    public void turnOffDB() {
        Conexion con = new Conexion();
        Connection access;
        access = (Connection) con.disconnect();
    }

    //valida el usuario y contraseña ingresados con la base de datos
    public void validateLogin() {
        String uf = log.getUsernameField();
        String pf = log.getPasswordField();

        if (uf.equals("") || pf.equals("") || uf.equals("Nombre de Usuario") || pf.equals("contraseña")) {
            log.loginErrorEventHandler();
            log.txtUsernameField.requestFocus();

        } else {
            u = udao.userValidator(uf, pf);

            if (u.getUsername() != null && u.getPassword() != null) {
                System.out.println("Validado");
                Home home = new Home();

                home.setVisible(true);
                home.lblUsername.setText(uf);
                switch (u.getIdTipoUsuario()) {
                    case 1:
                        home.lblUserType.setText("Administrador");
                        break;
                    case 2:
                        home.lblUserType.setText("Empleado");
                        break;
                    default: {
                    }
                }

                log.dispose();

            } else {
                log.dataErrorEventHandler();
                log.txtPasswordField.requestFocus();
            }

        }
    }
}
