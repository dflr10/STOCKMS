package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static View.UIUtils.*;
import static java.awt.Cursor.*;

//Crea la clase HyperlinkText que extiende de JLabel
public class HyperlinkText extends JLabel {

    //Constructor de la clase HyperlinkTExt
    public HyperlinkText(String hyperlinkText, int xPos, int yPos, Runnable hyperlinkAction) {
        super(hyperlinkText);
        setForeground(COLOR_OUTLINE);
        setFont(FONT_FORGOT_PASSWORD);
        setCursor(getPredefinedCursor(HAND_CURSOR));
        //Establece las acciones a los eventos del mouse
        addMouseListener(new MouseAdapter() {
            //Establece la función run() al presionar el botón
            @Override
            public void mousePressed(MouseEvent e) {
                hyperlinkAction.run();
            }

            //Establece el color del texto de hipervínculo al pasar el mouse sobre él 
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(COLOR_OUTLINE.darker());
            }

            //Establece el color del texto de hipervínculo al pasar el mouse fuera de él 
            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(COLOR_OUTLINE);
            }
        });
        //Establece las dimeciones por defecto y los bordes
        Dimension prefSize = getPreferredSize();
        setBounds(xPos, yPos, prefSize.width, prefSize.height);
    }
}
