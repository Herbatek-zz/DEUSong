package dontKnowHotToNameItXD;

import javax.swing.*;
import java.awt.*;

public class Project {



        public static void showOnScreen ( int screen, JFrame frame )
        {
            GraphicsEnvironment ge = GraphicsEnvironment
                    .getLocalGraphicsEnvironment();
            GraphicsDevice[] gs = ge.getScreenDevices();
            if( screen > -1 && screen < gs.length )
            {
                gs[screen].setFullScreenWindow( frame );
            }
            else if( gs.length > 0 )
            {
                gs[0].setFullScreenWindow( frame );
            }
            else
            {
                throw new RuntimeException( "No Screens Found" );
            }
        }






    public static void main(String[] args) {
        JFrame test = new JFrame();
        ImageIcon icon = new ImageIcon("piesni//iksde.jpg");

        test.add(new JLabel(icon));
        test.pack();
        test.setVisible(true);
        showOnScreen(0,test);



    }
}
