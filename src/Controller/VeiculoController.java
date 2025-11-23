package Controller;

import DB.VeiculoDAO;
import Model.MudarTela;
import Model.Veiculo;
import Templates.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

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

    Alertas alertas = new Alertas();

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
    void verHistorico(ActionEvent event) throws IOException {
        Veiculo veiculoSelecionado = tabelaVeiculos.getSelectionModel().getSelectedItem();

        if (veiculoSelecionado == null) {
            alertas.mostrarErro("Selecione um veículo para ver o histórico!");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HistoricoVeiculo.fxml"));
        Parent root = loader.load();

        HistoricoVeiculoController controller = loader.getController();
        controller.carregarDadosVeiculo(veiculoSelecionado);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    void irParaAdicionar(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/AdicionarVeiculo.fxml");
    }

    @FXML
    void irParaEditar(ActionEvent event) {

    }

    @FXML
    void irParaOrdens(ActionEvent event) throws IOException{
    MudarTela.trocarJanela(event, "/View/PainelOrdensServicos.fxml");
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
