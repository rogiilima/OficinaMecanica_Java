package Controller;

import DB.OrdemServicoDAO;
import Model.ItemPeca;
import Model.MudarTela;
import Model.OrdemDeServico;
import Templates.Alertas;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class DetalhesOrdemServicoController {

    @FXML
    private TableColumn<ItemPeca, String> colPecaNome;

    @FXML
    private TableColumn<ItemPeca, String> colPrecoUnit;

    @FXML
    private TableColumn<ItemPeca, String> colQuantidade;

    @FXML
    private TableColumn<ItemPeca, String> colTotal;

    @FXML
    private ComboBox<String> comboStatus;

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
    private TableView<ItemPeca> tabelaPecas;

    private OrdemDeServico ordemAtual;
    private Alertas alertas = new Alertas();

    public void carregarDadosOrdem(OrdemDeServico ordem) {
        this.ordemAtual = ordem;

        lblTitulo.setText("Detalhes da Ordem de Serviço #" + ordem.getIdOrdem());

        lblVeiculo.setText(ordem.getVeiculoPlaca());
        lblCliente.setText(ordem.getClienteNome());
        lblDataAbertura.setText(ordem.getDataAbertura());
        lblDataFinalizacao.setText(ordem.getDataFinalizacao());
        lblDescricao.setText(ordem.getDescricao());

        comboStatus.setItems(FXCollections.observableArrayList(
                "Em Serviço", "Aguardando Peças", "Pronto para Entrega", "Finalizado"
        ));
        comboStatus.setValue(ordem.getStatus());

        colPecaNome.setCellValueFactory(data -> data.getValue().nomePecaProperty());
        colQuantidade.setCellValueFactory(data -> data.getValue().quantidadeProperty());
        colPrecoUnit.setCellValueFactory(data -> data.getValue().precoUnitarioProperty());
        colTotal.setCellValueFactory(data -> data.getValue().totalProperty());

        tabelaPecas.setItems(OrdemServicoDAO.listarPecasDaOrdem(ordem.getIdOrdem()));
    }

    @FXML
    void atualizarStatus(ActionEvent event) {
        String novoStatus = comboStatus.getValue();

        if (novoStatus == null) {
            alertas.mostrarErro("Selecione um status!");
            return;
        }


          boolean sucesso = OrdemServicoDAO.atualizarStatus(ordemAtual.getIdOrdem(), novoStatus);

            if (sucesso) {
                alertas.mostrarConfirmacao("Status atualizado com sucesso!");
                ordemAtual.setStatus(novoStatus);

                // Se finalizou, atualizar data
                if (novoStatus.equals("Finalizado")) {
                    lblDataFinalizacao.setText(java.time.LocalDateTime.now().toString());
                }
            } else {
                alertas.mostrarErro("Erro ao atualizar status!");
            }
    }
    @FXML
    void realizarPagamento(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Pagamento.fxml"));
        Parent root = loader.load();

        PagamentoController controller = loader.getController();
        controller.carregarDados(ordemAtual);

        Stage stage = new Stage();
        stage.setTitle("Pagamento");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        carregarDadosOrdem(ordemAtual);
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
            MudarTela.trocarJanela(event, "/View/PainelOrdensServico.fxml");
    }

}
