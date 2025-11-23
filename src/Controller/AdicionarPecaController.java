package Controller;

import Model.MudarTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdicionarPecaController {

    @FXML
    private Label lblMensagem;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtQuantidade;

    @FXML
    void limparCampos(ActionEvent event) {

    }

    @FXML
    void salvarPeca(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/PainelPecas.fxml");
    }

}
