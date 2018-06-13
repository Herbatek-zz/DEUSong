package dontKnowHotToNameItXD;


import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.stage.StageStyle.UNDECORATED;

public class Project {

    private Stage stage;
    private ImageView imageView;
    private final String BACKGROUND_PATH = "/obrazy/background.jpg";

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

        imageView = new ImageView(new Image(BACKGROUND_PATH));
        imageView.setFitHeight(projector.getBounds().getHeight());
        imageView.setFitWidth(projector.getBounds().getWidth());
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);
        imageView.setCache(true);

        StackPane stackPane = new StackPane(imageView);
        stackPane.setStyle("-fx-background-color: #000000;");
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.initStyle(UNDECORATED);
        stage.setAlwaysOnTop(true);
    }

    public void loadImage(Image image) {
        imageView.setImage(image);
    }

    public void show() {
        stage.show();
    }


}

