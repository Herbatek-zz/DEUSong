package dontKnowHotToNameItXD;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Project {

    private JFrame frame;
    private Dimension screenSize = new Dimension();

    public Project()
    {
        GraphicsDevice[] gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        this.screenSize.width=gd[0].getDisplayMode().getWidth();
        this.screenSize.height=gd[0].getDisplayMode().getHeight();
        frame=new JFrame();
        frame.setBackground(Color.black);
        loadBG();
        showOnScreen(0,frame);
    }

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





    public BufferedImage resizeImage(BufferedImage img){
        System.out.println(screenSize.height+"+"+screenSize.width);

        BufferedImage newImage = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImage.createGraphics();
        g.drawImage(img, 0, 0, screenSize.height *5/4,screenSize.height, null);
        g.dispose();
        return newImage;
    }


    public  void loadImage (BufferedImage img) {

        frame.getContentPane().removeAll();
        //frame.repaint();
        frame.getContentPane().add(new JLabel(new ImageIcon(resizeImage(img))));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);



    }

    public void loadBG(){

        BufferedImage img;
        try {
            img = ImageIO.read(getClass().getResource("/obrazy/default.jpg"));
            loadImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

