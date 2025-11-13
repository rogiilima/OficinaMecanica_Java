package Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class MudarTela {

    // static consegue acessar só chamando a classe o metodo, exceção de Input e Output
    public static void trocarJanela(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(MudarTela.class.getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
