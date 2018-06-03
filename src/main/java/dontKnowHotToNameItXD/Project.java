package dontKnowHotToNameItXD;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static javafx.stage.Screen.getPrimary;

public class Project implements KeyListener {

    private Stage stage;
    private StackPane stackPane;
    private Scene scene;
    ImageView imageView;
    Image image;

    public Project(SlideShow slideShow) {
        stage = new Stage();


        image = new Image("/obrazy/default.jpg");
        imageView = new ImageView(image);
        imageView.setFitHeight(getPrimary().getBounds().getHeight());
        imageView.setFitWidth(getPrimary().getBounds().getWidth());
        stackPane = new StackPane(imageView);

        scene = new Scene(stackPane);

        stage.setFullScreen(true);

        stage.setScene(scene);
    }

    public void loadImage(BufferedImage img) {
        image = SwingFXUtils.toFXImage(img, null);
        imageView.setImage(image);
    }

    public void show() {
        stage.show();
    }

    public void close() {
        stage.close();
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

