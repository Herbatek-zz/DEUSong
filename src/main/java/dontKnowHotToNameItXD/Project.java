package dontKnowHotToNameItXD;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Project implements KeyListener {

    private JFrame frame;
    private Dimension screenSize = new Dimension();

    public Project(SlideShow slideShow) {
        GraphicsDevice[] gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        this.screenSize.width = gd[0].getDisplayMode().getWidth();
        this.screenSize.height = gd[0].getDisplayMode().getHeight();
        frame = new JFrame();
        frame.setBackground(Color.black);

        loadBG();
        showOnScreen(1, frame);
        frame.setVisible(true);

    }

    public static void showOnScreen(int screen, JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        if (screen > -1 && screen < gs.length) {
            gs[screen].setFullScreenWindow(frame);


        } else if (gs.length == 0) {
            gs[0].setFullScreenWindow(frame);

        } else {
            throw new RuntimeException("No Screens Found");
        }


    }


    public BufferedImage resizeImage(BufferedImage img) {
        System.out.println(screenSize.height + "+" + screenSize.width);

        BufferedImage newImage = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImage.createGraphics();
        g.drawImage(img, 0, 0, screenSize.height * 5 / 4, screenSize.height, null);
        g.dispose();
        return newImage;
    }


    public void loadImage(BufferedImage img) {

        frame.getContentPane().removeAll();
        //frame.repaint();
        frame.getContentPane().add(new JLabel(new ImageIcon(resizeImage(img))));

        frame.pack();
       // frame.setLocationRelativeTo(null);
       // frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);


    }

    public void loadBG() {

        BufferedImage img;
        try {
            img = ImageIO.read(getClass().getResource("/obrazy/default.jpg"));
            loadImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setState(Frame.MAXIMIZED_BOTH);
    }

public void fix()
{
    this.frame.setState(Frame.MAXIMIZED_BOTH);
}


    private void slideControl(){

    }



    @Override
    public void keyTyped(KeyEvent e) {
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_NUMPAD1: {
//                slide.prevSlide(this);
//            }
//            break;
//
//            case KeyEvent.VK_NUMPAD3: {
//                slide.nextSlide(this);
//            }
//            break;
//            case KeyEvent.VK_NUMPAD7: {
//                if (isProjecting) slide.open(filename);
//                else slide.end();
//            }
//            break;
//
//            case KeyEvent.VK_NUMPAD9: {
//                //next in queue
//            }



    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}

