package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DetalhesOrdemServicoController {

    @FXML
    private TableColumn<?, ?> colPecaNome;

    @FXML
    private TableColumn<?, ?> colPrecoUnit;

    @FXML
    private TableColumn<?, ?> colQuantidade;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private ComboBox<?> comboStatus;

    @FXML
    private Label lblCliente;

    @FXML
    private Label lblDataAbertura;

    @FXML
    private Label lblDataFinalizacao;

    @FXML
    private Label lblDescricao;

    @FXML
    private Label lblMaoObra;

    @FXML
    private Label lblTitulo;

    @FXML
    private Label lblTotalPecas;

    @FXML
    private Label lblValorTotal;

    @FXML
    private Label lblVeiculo;

    @FXML
    private TableView<?> tabelaPecas;

    @FXML
    void atualizarStatus(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {

    }

}
