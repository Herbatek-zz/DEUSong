package dontKnowHotToNameItXD;


import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Project {

    private Stage stage;
    ImageView imageView;
    Image image;

    public Project() {

        ObservableList<Screen> screens = Screen.getScreens();
        Screen projector;
        if (screens.size() > 1)
            projector = screens.get(1);
        else
            projector = screens.get(0);


        stage = new Stage();
        stage.setX(projector.getVisualBounds().getMinX());
        stage.setY(projector.getVisualBounds().getMinY());
        stage.setWidth(projector.getBounds().getWidth());
        stage.setHeight(projector.getBounds().getHeight());
        stage.setFullScreen(true);

        image = new Image("/obrazy/default.jpg");

        imageView = new ImageView(image);
        imageView.setFitHeight(Screen.getPrimary().getBounds().getHeight());
        imageView.setFitWidth(Screen.getPrimary().getBounds().getWidth());
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);
        imageView.setCache(true);

        StackPane stackPane = new StackPane(imageView);
        stackPane.setStyle("-fx-background-color: #000000;");
        Scene scene = new Scene(stackPane);

        scene.setOnKeyPressed(event -> {
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
        });

        stage.setScene(scene);

    }

    public void loadImage(Image image) {
        imageView.setImage(image);
    }

    public void show() {
        stage.show();
    }

    public void close() {
        stage.close();
    }

    public void loadBG(Image image) {
        imageView.setImage(image);
    }


}

