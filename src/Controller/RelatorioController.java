package Controller;

import DB.RelatorioDAO;
import Model.GeradorPDF;
import Model.MudarTela;
import Model.OrdemDeServico;
import Templates.Alertas;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class RelatorioController implements Initializable {

    @FXML private Label lblFaturamento;
    @FXML private Label lblOrdensFinalizadas;

    private Alertas alertas = new Alertas();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarDados();
    }

    private void carregarDados() {
        DecimalFormat df = new DecimalFormat("R$ #,##0.00");
        lblFaturamento.setText(df.format(RelatorioDAO.obterFaturamentoTotal()));
        lblOrdensFinalizadas.setText(String.valueOf(RelatorioDAO.obterTotalOrdensFinalizadas()));
    }

    @FXML
    void gerarPDF(ActionEvent event) {
        try {
            ObservableList<OrdemDeServico> ordens = RelatorioDAO.listarOrdensParaRelatorio();

            if (ordens.isEmpty()) {
                alertas.mostrarErro("Não há ordens finalizadas para gerar o relatório.");
                return;
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvar Relatório");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos PDF", "*.pdf"));
            fileChooser.setInitialFileName("Relatorio_Oficina_" + System.currentTimeMillis() + ".pdf");


            Stage stage = (Stage) lblFaturamento.getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {

                GeradorPDF gerador = new GeradorPDF();
                gerador.gerarRelatorioFinanceiro(ordens, file.getAbsolutePath());
                alertas.mostrarConfirmacao("Relatório gerado com sucesso em:\n" + file.getAbsolutePath());
            }

        } catch (Exception e) {
            e.printStackTrace();
            alertas.mostrarErro("Erro ao gerar PDF: " + e.getMessage());
        }
    }

    @FXML
    void voltarParaPainel(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/PainelAdministrativo.fxml");
    }
}