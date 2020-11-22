package View;

import Controller.LoginController;
import Model.Conexion;
//import Model.UserDAO;
import Model.User;
import Model.UserDAO;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;

public class LoginUI extends JFrame {

    //Variables globales
    Conexion con = new Conexion();
    Connection access;

    UserDAO udao = new UserDAO();
    User u ;
    private final Toaster toaster;

    public String username = "";
    public String password = "";
    public JButton loginButton;
    public JButton exitButton;

    public TextFieldUsername txtUsernameField;
    public TextFieldPassword txtPasswordField;

    //Constructor de Login
    public LoginUI() {

        //Establecemos el icono del proyecto en la barra de tareas y superior del Frame     
        this.setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
        this.setTitle("STOCKMS Inicio de Sesión");
        JPanel mainJPanel = getMainJPanel();

        addLogo(mainJPanel);
        addSeparator(mainJPanel);
        addUsernameTextField(mainJPanel);
        addPasswordTextField(mainJPanel);
        addLoginButton(mainJPanel);
        addExitButton(mainJPanel);
        addForgotPasswordButton(mainJPanel);

        this.add(mainJPanel);
        this.pack();
        this.setVisible(true);
        this.toFront();

        //Establecemos la ubicación del Panel 
        setLocationRelativeTo(null);
        toaster = new Toaster(mainJPanel);
        LoginController lc =new LoginController(this);
    }

    //Obtiene el texto del campo de Nombre de usuario
    public String getUsernameField() {
        username = txtUsernameField.getText();

        return username;
    }

    //Obtiene el texto del campo de texto de COntraseña
    public String getPasswordField() {
        password = String.valueOf(txtPasswordField.getPassword());

        return password;
    }

    //OBTIENE EL PANE PRINCIPAL
    private JPanel getMainJPanel() {
        this.setUndecorated(false);
        this.setResizable(false);

        Dimension size = new Dimension(800, 400);

        JPanel panel1 = new JPanel();
        panel1.setSize(size);
        panel1.setPreferredSize(size);
        panel1.setBackground(UIUtils.COLOR_BACKGROUND);
        panel1.setLayout(null);

        MouseAdapter ma = new MouseAdapter() {
            int lastX, lastY;

            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getXOnScreen();
                lastY = e.getYOnScreen();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(getLocationOnScreen().x + x - lastX, getLocationOnScreen().y + y - lastY);
                lastX = x;
                lastY = y;
            }
        };

        panel1.addMouseListener(ma);
        panel1.addMouseMotionListener(ma);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                access = (Connection) con.disconnect();
                System.exit(0);
                //close();
            }
        });

        return panel1;
    }

    //AGREGA EL SEPARADOR CENTRAL
    private void addSeparator(JPanel panel1) {
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.VERTICAL);
        separator1.setForeground(UIUtils.COLOR_OUTLINE);
        panel1.add(separator1);
        separator1.setBounds(400, 45, 1, 308);
    }


    //AGREGA EL LOGO DE STOCKMS
    private void addLogo(JPanel panel1) {
        JLabel label1 = new JLabel();
        label1.setFocusable(false);
       // label1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("ruipi2.png")).getFile()));
        label1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockms300px.png")));
        panel1.add(label1);
        label1.setBounds(50, 50, 300, 300);
    }

    //CAMPO DE TEXTO DE NOMBRE DE USUARIO
    private void addUsernameTextField(JPanel panel1) {
        txtUsernameField = new TextFieldUsername();

        txtUsernameField.setBounds(475, 66, 250, 44);
        txtUsernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUsernameField.getText().equals(UIUtils.PLACEHOLDER_TEXT_USERNAME)) {
                    txtUsernameField.setText("");
                }
                txtUsernameField.setForeground(Color.white);
                txtUsernameField.setBorderColor(UIUtils.WHITHE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtUsernameField.getText().isEmpty()) {
                    txtUsernameField.setText(UIUtils.PLACEHOLDER_TEXT_USERNAME);
                }
                txtUsernameField.setForeground(UIUtils.COLOR_OUTLINE);
                txtUsernameField.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        panel1.add(txtUsernameField);
    }

    //CAMPO DE TEXTO DE CONTRASEÑA
    private void addPasswordTextField(JPanel panel1) {
        txtPasswordField = new TextFieldPassword();
        txtPasswordField.setText(UIUtils.PLACEHOLDER_TEXT_PASSWORD);
        txtPasswordField.setForeground(UIUtils.COLOR_OUTLINE);

        txtPasswordField.setBounds(475, 126, 250, 44);
        txtPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(txtPasswordField.getPassword()).equals(UIUtils.PLACEHOLDER_TEXT_PASSWORD)) {
                    txtPasswordField.setText("");
                }
                txtPasswordField.setForeground(Color.white);
                txtPasswordField.setBorderColor(UIUtils.WHITHE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(txtPasswordField.getPassword()).isEmpty()) {
                    txtPasswordField.setText(UIUtils.PLACEHOLDER_TEXT_PASSWORD);
                }
                txtPasswordField.setForeground(UIUtils.COLOR_OUTLINE);
                txtPasswordField.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        panel1.add(txtPasswordField);
    }

    //BOTÓN DE INICIO DE SESIÓN 
    private void addLoginButton(JPanel panel1) {
        final Color[] loginButtonColors = {UIUtils.COLOR_INTERACTIVE, Color.white};

         loginButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = UIUtils.get2dGraphics(g);
                super.paintComponent(g2);

                Insets insets = getInsets();
                int w = getWidth() - insets.left - insets.right;
                int h = getHeight() - insets.top - insets.bottom;
                g2.setColor(loginButtonColors[0]);
                g2.fillRoundRect(insets.left, insets.top, w, h, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

                FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
                int x2 = (getWidth() - metrics.stringWidth(UIUtils.BUTTON_TEXT_LOGIN)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(UIUtils.FONT_GENERAL_UI);
                g2.setColor(loginButtonColors[1]);
                g2.drawString(UIUtils.BUTTON_TEXT_LOGIN, x2, y2);
            }
        };

        loginButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                loginButtonColors[0] = UIUtils.COLOR_INTERACTIVE_DARKER;
                loginButtonColors[1] = UIUtils.OFFWHITE;
                loginButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButtonColors[0] = UIUtils.COLOR_INTERACTIVE;
                loginButtonColors[1] = Color.white;
                loginButton.repaint();
            }

        });

        loginButton.setBackground(UIUtils.COLOR_BACKGROUND);
        loginButton.setBounds(475, 227, 250, 44);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(loginButton);
    }

    // BOTON DE SALIR
    private void addExitButton(JPanel panel1) {
        final Color[] exitButtonColors = {UIUtils.COLOR_INTERACTIVE2, Color.white};

         exitButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = UIUtils.get2dGraphics(g);
                super.paintComponent(g2);

                Insets insets = getInsets();
                int w = getWidth() - insets.left - insets.right;
                int h = getHeight() - insets.top - insets.bottom;
                g2.setColor(exitButtonColors[0]);
                g2.fillRoundRect(insets.left, insets.top, w, h, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

                FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
                int x2 = (getWidth() - metrics.stringWidth(UIUtils.BUTTON_TEXT_EXIT)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(UIUtils.FONT_GENERAL_UI);
                g2.setColor(exitButtonColors[1]);
                g2.drawString(UIUtils.BUTTON_TEXT_EXIT, x2, y2);
            }
        };

        exitButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                exitButtonColors[0] = UIUtils.COLOR_INTERACTIVE_DARKER2;
                exitButtonColors[1] = UIUtils.OFFWHITE;
                exitButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButtonColors[0] = UIUtils.COLOR_INTERACTIVE2;
                exitButtonColors[1] = Color.white;
                exitButton.repaint();
            }

        });

        exitButton.setBackground(UIUtils.COLOR_BACKGROUND);
        exitButton.setBounds(475, 287, 250, 44);
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(exitButton);
    }

    
    //ALERTAS
    private void addForgotPasswordButton(JPanel panel1) {
        panel1.add(new HyperlinkText(UIUtils.BUTTON_TEXT_FORGOT_PASS, 475, 177, () -> {
            toaster.warn("Reestablecer contraseña");
        }));
    }

    public void loginErrorEventHandler() {
        toaster.error("¡Hay campos vacíos!");
    }

    public void dataErrorEventHandler() {
        toaster.error("¡Datos inválidos!"); 
    }

}
