package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
//Crea la clase TextFieldUsername

public class TextFieldUsername extends JTextField {

    //Declaración de las variables y objetos de la clase TextFieldUsername
    private Shape shape;
    private Color borderColor = UIUtils.WHITHE;

    //Constructor de la clase TextFieldUsername
    public TextFieldUsername() {
        setOpaque(false);
        setBackground(UIUtils.COLOR_BACKGROUND);
        setForeground(Color.white);
        setCaretColor(Color.white);
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setMargin(new Insets(2, 10, 2, 2));
        setHorizontalAlignment(SwingConstants.LEFT);
        setFont(UIUtils.FONT_GENERAL_UI);
    }

    //Se encarga de pintar el componente del campo de texto de nombre de usuario
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = UIUtils.get2dGraphics(g);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);
        super.paintComponent(g2);
    }

    //Se encarga de pintar el borde del componente del campo de texto de nombre de usuario
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = UIUtils.get2dGraphics(g);
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);
    }

    //Se encarga de pintar el contenido del campo de texto de nombre de usuairo
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);
        }
        return shape.contains(x, y);
    }

    //Establece el color del borde del campo de texto de nombre de usuario
    public void setBorderColor(Color color) {
        borderColor = color;
        repaint();
    }
}
