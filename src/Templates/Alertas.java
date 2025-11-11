package Templates;

import javafx.scene.control.Alert;

public class Alertas {

    public void mostrarConfirmacao(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Operação realizado com sucesso!" );
        alert.setContentText("Login realizado com sucesso");
        alert.showAndWait();
    }

    public void mostrarErro(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no banco de dados");
        alert.setHeaderText("Esse erro ocorreu na execução do banco de dados");
        alert.setContentText("Erro");
        alert.showAndWait();
    }

    public void mostrarErro(String erro){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no email");
        alert.setHeaderText("Erro de digitação, por favor, repita!");
        alert.setContentText(erro);
        alert.showAndWait();
    }
}
