package Controller;

import DB.PagamentoDAO;
import Model.OrdemDeServico;
import Templates.Alertas;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PagamentoController {

    @FXML private ComboBox<String> comboFormaPagamento;
    @FXML private Label lblValorTotal;

    private OrdemDeServico ordemAtual;
    private Alertas alertas = new Alertas();

    public void carregarDados(OrdemDeServico ordem) {
        this.ordemAtual = ordem;
        lblValorTotal.setText(ordem.getValorTotal());

        comboFormaPagamento.setItems(FXCollections.observableArrayList(
                "Dinheiro", "Pix", "Cartão de Crédito", "Cartão de Débito"
        ));
    }

    @FXML
    void confirmarPagamento(ActionEvent event) {
        if (comboFormaPagamento.getValue() == null) {
            alertas.mostrarErro("Selecione uma forma de pagamento!");
            return;
        }

        String valorStr = ordemAtual.getValorTotal()
                .replace("R$", "")
                .replace(".", "")
                .replace(",", ".")
                .trim();
        double valor = Double.parseDouble(valorStr);

        boolean sucesso = PagamentoDAO.registrarPagamento(
                Integer.parseInt(ordemAtual.getIdOrdem()),
                valor,
                comboFormaPagamento.getValue()
        );

        if (sucesso) {
            alertas.mostrarConfirmacao("Pagamento registrado com sucesso!");
            fecharJanela();
        } else {
            alertas.mostrarErro("Erro ao registrar pagamento.");
        }
    }

    private void fecharJanela() {
        Stage stage = (Stage) lblValorTotal.getScene().getWindow();
        stage.close();
    }
}