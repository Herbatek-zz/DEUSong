import controllers.SearchController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Deus Songs");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        SearchController controller = loader.getController();
        controller.fakeInit();
        //ECIU EDIT



//
    }


    public static void main(String[] args) {
        launch(args);
    }
}
