import controllers.SearchController;
import dontKnowHotToNameItXD.Project;
import dontKnowHotToNameItXD.SlideShow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;


public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Deus Songs");
        primaryStage.setScene(new Scene(root));
         primaryStage.show();
        //ECIU EDIT

//
    }


    public static void main(String[] args) {
        launch(args);
    }
}
