package View;

import java.awt.*;
import java.util.HashMap;

//Crea la clase UIUtils
public class UIUtils {

    //Declaración de constantes y objetos de la clase UIUtils
    public static final Font FONT_GENERAL_UI = new Font("Segoe UI", Font.PLAIN, 20);
    public static final Font FONT_FORGOT_PASSWORD = new Font("Segoe UI", Font.PLAIN, 12);

    public static final Color COLOR_OUTLINE = new Color(103, 112, 120);
    public static final Color COLOR_BACKGROUND = new Color(0,0,0);
    public static final Color WHITHE= new Color(255,255,255);
    public static final Color COLOR_INTERACTIVE = new Color(24, 119, 242);
    public static final Color COLOR_INTERACTIVE_DARKER = new Color(4,59,202);
    public static final Color COLOR_INTERACTIVE2 = new Color(252, 87, 118);
    public static final Color COLOR_INTERACTIVE_DARKER2 = new Color(231, 42, 87);
    public static final Color OFFWHITE = new Color(229, 229, 229);

    public static final String BUTTON_TEXT_LOGIN = "Iniciar sesión";
    public static final String BUTTON_TEXT_EXIT = "Salir";
    public static final String BUTTON_TEXT_FORGOT_PASS = "¿Olvidó su contraseña?";
    public static final String BUTTON_TEXT_REGISTER = "Registrarse";

    public static final String PLACEHOLDER_TEXT_USERNAME = "Nombre de usuario";
    public static final String PLACEHOLDER_TEXT_PASSWORD = "contraseña";

    public static final int ROUNDNESS = 8;

    //Obtiene las propiedades gráficas de un componente gráfico
    public static Graphics2D get2dGraphics(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>() {
            {
                put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            }
        });
        return g2;
    }
}
