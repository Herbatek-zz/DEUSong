package dontKnowHotToNameItXD;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Project {



        public static void showOnScreen ( int screen, JFrame frame ) {
            GraphicsEnvironment ge = GraphicsEnvironment
                    .getLocalGraphicsEnvironment();
            GraphicsDevice[] gs = ge.getScreenDevices();
            if (screen > -1 && screen < gs.length) {
                gs[screen].setFullScreenWindow(frame);
            } else if (gs.length > 0) {
                gs[0].setFullScreenWindow(frame);
            } else {
                throw new RuntimeException("No Screens Found");
            }


        }




    public static void loadImage (BufferedImage img) {
        JFrame f = new JFrame();
       f.getContentPane().add(new JLabel(new ImageIcon(img)));


        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showOnScreen(0,f);


        }
    }

