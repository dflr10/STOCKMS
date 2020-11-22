package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

//Crea la clase Toaster
public class Toaster {

    //Declara las constantes de la clase
    private static final int STARTING_Y_POS = 178;
    private static final int SPACER_DISTANCE = 15;
    private static final ArrayList<ToasterBody> toasterBodies = new ArrayList<>();
    private final static AtomicInteger CURRENT_Y_OFFSET = new AtomicInteger();
    private final JPanel panelToToastOn;

    //Constructor de la clase TOaster
    public Toaster(JPanel panelToToastOn) {
        this.panelToToastOn = panelToToastOn;
    }

    //Muestra un toaster de tipo error
    public void error(String... messages) {
        for (String s : messages) {
            toast(s, new Color(181, 59, 86));
        }
    }

    //Muestra un toaster de tipo success
    public void success(String... messages) {
        for (String s : messages) {
            toast(s, new Color(33, 181, 83));
        }
    }

    //muestra un toaster de tipo info
    public void info(String... messages) {
        for (String s : messages) {
            toast(s, new Color(13, 116, 181));
        }
    }

    //muestra un toaster de tipo warm
    public void warn(String... messages) {
        for (String s : messages) {
            toast(s, new Color(181, 147, 10));
        }
    }

    //Se encarga de crear los toasters,
    //recibe como parámetros el mensaje a mostrar en el toaster y el color de fondo del mismo en código RGB
    private void toast(String message, Color bgColor) {
        ToasterBody toasterBody;

        if (toasterBodies.isEmpty()) {
            toasterBody = new ToasterBody(panelToToastOn, message, bgColor, STARTING_Y_POS);
            CURRENT_Y_OFFSET.set(STARTING_Y_POS + toasterBody.getHeightOfToast());
        } else {
            toasterBody = new ToasterBody(panelToToastOn, message, bgColor, CURRENT_Y_OFFSET.get() + SPACER_DISTANCE);
            CURRENT_Y_OFFSET.addAndGet(SPACER_DISTANCE + toasterBody.getHeightOfToast());
        }

        toasterBodies.add(toasterBody);

        new Thread(() -> {
            toasterBody.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    removeToast(toasterBody);
                }
            });

            panelToToastOn.add(toasterBody, 0);
            panelToToastOn.repaint();

            try {
                Thread.sleep(1000);
                removeToast(toasterBody);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //Remueve el toaster del panel de forma automática 
    private synchronized void removeToast(ToasterBody toasterBody) {
        if (!toasterBody.getStopDisplaying()) {
            toasterBody.setStopDisplaying(true);

            toasterBodies.forEach(toasterBody1 -> {
                if (toasterBodies.indexOf(toasterBody1) >= toasterBodies.indexOf(toasterBody)) {
                    toasterBody1.setyPos(toasterBody1.getyPos() - toasterBody.getHeightOfToast() - SPACER_DISTANCE);
                }
            });

            toasterBodies.remove(toasterBody);

            CURRENT_Y_OFFSET.set(CURRENT_Y_OFFSET.get() - SPACER_DISTANCE - toasterBody.getHeightOfToast());

            panelToToastOn.remove(toasterBody);
            panelToToastOn.repaint();
        }
    }
}
