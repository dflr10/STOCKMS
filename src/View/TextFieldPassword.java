package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

//Crea la claase TextFieldPassword
public class TextFieldPassword extends JPasswordField {

    //Declaración de las variables y constantes de la clase TextFieldPassword
    private Shape shape;
    private Color borderColor = UIUtils.COLOR_OUTLINE;

    //Constructor de la clase TextFieldPassword
    public TextFieldPassword() {
        setOpaque(false);
        setBackground(UIUtils.COLOR_BACKGROUND);
        setForeground(Color.white);
        setCaretColor(Color.white);
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setMargin(new Insets(2, 10, 2, 2));
        setHorizontalAlignment(SwingConstants.LEFT);
        setFont(UIUtils.FONT_GENERAL_UI);
    }

    //Se encarga de pintar el componente del campo de texto de contraseña
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = UIUtils.get2dGraphics(g);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);
        super.paintComponent(g2);
    }

    //Se encarga de pintar el borde del componente del campo de texto de contraseña
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = UIUtils.get2dGraphics(g);
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);
    }

    //Se encarga de pintar el contenido del campo de texto de contraseña
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);
        }
        return shape.contains(x, y);
    }

    //Establece el color del borde del campo de texto de contraseña
    public void setBorderColor(Color color) {
        borderColor = color;
        repaint();
    }
}
