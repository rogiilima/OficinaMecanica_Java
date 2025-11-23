package Controller;

import DB.PecaDAO;
import Model.Peca;
import Templates.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class PecasController implements Initializable {

    @FXML
    private TableColumn<Peca, String> colEstoque;

    @FXML
    private TableColumn<Peca, String> colId;

    @FXML
    private TableColumn<Peca, String> colNome;

    @FXML
    private TableColumn<Peca, String> colPreco;

    @FXML
    private Label lblAlertas;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblValorEstoque;

    @FXML
    private TableView<Peca> tabelaPecas;

    @FXML
    private TextField txtBusca;

    private Alertas alertas = new Alertas();
    private DecimalFormat df = new DecimalFormat("R$ #,##0.00");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(data -> data.getValue().idPecaProperty());
        colNome.setCellValueFactory(data -> data.getValue().nomePecaProperty());
        colPreco.setCellValueFactory(data -> data.getValue().precoUnitarioProperty());
        colEstoque.setCellValueFactory(data -> data.getValue().quantidadeEstoqueProperty());

        carregarPecas();
        verificarEstoqueBaixo();
    }

    private void carregarPecas() {
        tabelaPecas.setItems(PecaDAO.listarPecas());
        atualizarRodape();
    }

    private void atualizarRodape() {
        lblTotal.setText(String.valueOf(tabelaPecas.getItems().size()));
        lblValorEstoque.setText(df.format(PecaDAO.calcularValorTotalEstoque()));
    }
    private void verificarEstoqueBaixo() {
        var estoqueBaixo = PecaDAO.listarEstoqueBaixo();
        if (estoqueBaixo.isEmpty()) {
            lblAlertas.setText("Nenhum alerta de estoque");
        } else {
            lblAlertas.setText(estoqueBaixo.size() + " peça(s) com estoque baixo (menos de 10 unidades)");
        }
    }

    @FXML
    void ajustarEstoque(ActionEvent event) {

    }

    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    void excluirPeca(ActionEvent event) {

    }

    @FXML
    void irParaAdicionar(ActionEvent event) {

    }

    @FXML
    void irParaEditar(ActionEvent event) {

    }

    @FXML
    void irParaOrdens(ActionEvent event) {

    }

    @FXML
    void irParaVeiculos(ActionEvent event) {

    }

    @FXML
    void limparBusca(ActionEvent event) {

    }

    @FXML
    void voltarParaPainel(ActionEvent event) {

    }

}
