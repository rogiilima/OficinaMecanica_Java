package Controller;

import DB.VeiculoDAO;
import Model.MudarTela;
import Model.Veiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VeiculoController implements Initializable {

    @FXML
    private TableColumn<Veiculo, String> colAno;

    @FXML
    private TableColumn<Veiculo, String> colCliente;

    @FXML
    private TableColumn<Veiculo, String> colId;

    @FXML
    private TableColumn<Veiculo, String> colModelo;

    @FXML
    private TableColumn<Veiculo, String> colPlaca;

    @FXML
    private Label lblUsuario;

    @FXML
    private TableView<Veiculo> tabelaVeiculos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colPlaca.setCellValueFactory(data -> data.getValue().placaProperty());
        colModelo.setCellValueFactory(data -> data.getValue().modeloProperty());
        colAno.setCellValueFactory(data -> data.getValue().anoProperty());
        colCliente.setCellValueFactory(data -> data.getValue().nomeClienteProperty());

        carregarVeiculos();
    }

    private void carregarVeiculos() {
        tabelaVeiculos.setItems(VeiculoDAO.listarVeiculos());
    }

    @FXML
    void excluirVeiculo(ActionEvent event) {

    }

    @FXML
    void verHistorico(ActionEvent event) {

    }

    @FXML
    void irParaAdicionar(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/AdicionarVeiculo.fxml");
    }

    @FXML
    void irParaEditar(ActionEvent event) {

    }

    @FXML
    void irParaOrdens(ActionEvent event) {

    }

    @FXML
    void irParaPecas(ActionEvent event) {

    }

    @FXML
    void irParaRelatorios(ActionEvent event) {

    }

    @FXML
    void irParaAgendamentos(ActionEvent event) {

    }

    @FXML
    void voltarParaPainel(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/PainelAdministrativo.fxml");
    }


}
