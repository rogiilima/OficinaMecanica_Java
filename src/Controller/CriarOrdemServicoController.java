package Controller;

import Model.MudarTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CriarOrdemServicoController {

    @FXML
    private TableColumn<?, ?> colPecaNome;

    @FXML
    private TableColumn<?, ?> colPecaPreco;

    @FXML
    private TableColumn<?, ?> colPecaQtd;

    @FXML
    private TableColumn<?, ?> colPecaTotal;

    @FXML
    private ComboBox<?> comboCliente;

    @FXML
    private ComboBox<?> comboPeca;

    @FXML
    private ComboBox<?> comboStatus;

    @FXML
    private ComboBox<?> comboVeiculo;

    @FXML
    private Label lblMaoObra;

    @FXML
    private Label lblPecas;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<?> tabelaPecas;

    @FXML
    private TextArea txtDescricao;

    @FXML
    private TextField txtMaoObra;

    @FXML
    private TextField txtQuantidade;

    @FXML
    void adicionarPeca(ActionEvent event) {

    }

    @FXML
    void carregarVeiculos(ActionEvent event) {

    }

    @FXML
    void criarOrdem(ActionEvent event) {

    }

    @FXML
    void removerPeca(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/PainelOrdensServico.fxml");
    }

}
