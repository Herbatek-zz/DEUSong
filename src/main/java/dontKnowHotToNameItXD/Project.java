package dontKnowHotToNameItXD;


import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Project implements KeyListener {

    private Stage stage;
    private StackPane stackPane;
    private Scene scene;
    ImageView imageView;
    Image image;
    ObservableList<Screen> screens;

    public Project() {
        screens = Screen.getScreens();

        stage = new Stage();
        stage.setFullScreen(true);

        image = new Image("/obrazy/default.jpg");

        imageView = new ImageView(image);
//        odkomentować to poniżej jeśli wielkość > jakość
        imageView.setFitHeight(Screen.getPrimary().getBounds().getHeight());
        imageView.setFitWidth(Screen.getPrimary().getBounds().getWidth());
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);
        imageView.setCache(true);

        stackPane = new StackPane(imageView);
        stackPane.setStyle("-fx-background-color: #000000;");
        scene = new Scene(stackPane);

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

    public void loadBG()
    {
        image= new Image("/obrazy/default.jpg");
        imageView.setImage(image);
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

