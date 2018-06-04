package dontKnowHotToNameItXD;

import javafx.scene.control.Alert;

public class AlertFactory {

    public static Alert createError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }
}
