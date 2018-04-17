package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML
    private void search(KeyEvent event) {
        System.out.println("Akcja wykonana z wyszukiwarki :D");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
