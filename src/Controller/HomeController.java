package Controller;

import Model.Conexion;
import View.FormProduct;
import View.Home;
import View.LoginUI;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class HomeController implements ActionListener {

    //Declaración de variables
    private final Home h;
    private FormProduct fp;
    Conexion con = new Conexion();
    Connection access;

    //Constructor de HomeController
    public HomeController(Home h) {
        this.h = h;

        this.h.btnHome.addActionListener(this);
        this.h.btnUsuario.addActionListener(this);
        this.h.btnProducto.addActionListener(this);
        this.h.btnInventario.addActionListener(this);
        this.h.btnEntradas.addActionListener(this);
        this.h.btnSalidas.addActionListener(this);
        this.h.btnConfig.addActionListener(this);
        this.h.btnCerrar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == h.btnHome) {

        }
        if (e.getSource() == h.btnUsuario) {

        }
        if (e.getSource() == h.btnProducto) {
            fp = new FormProduct();
            fp.setVisible(true);
            windowCentered(fp);
        }
        if (e.getSource() == h.btnInventario) {

        }
        if (e.getSource() == h.btnEntradas) {

        }
        if (e.getSource() == h.btnSalidas) {

        }
        if (e.getSource() == h.btnConfig) {

        }
        if (e.getSource() == h.btnCerrar) {
            System.out.println("Cerrar");
            close();
        }

    }

    public void windowCentered(JInternalFrame frame) {
        h.jDesktopPane1.add(frame);

        Dimension screenSize = h.jDesktopPane1.getSize();
        Dimension formP = frame.getSize();
        frame.setLocation((screenSize.width - formP.width) / 2, ((screenSize.height) - formP.height) / 2);
        frame.show();
    }

    public void close() {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(null, "¿Realmente desea salir de la aplicación?", "Saliendo de RUIPI",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
             access = (Connection) con.disconnect();
            h.dispose();
            LoginUI lg = new LoginUI();
        } else {
        }
    }

}
