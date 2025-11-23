package Controller;

import DB.ClienteDAO;
import DB.OrdemServicoDAO;
import DB.PecaDAO;
import DB.VeiculoDAO;
import Model.*;
import Templates.Alertas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CriarOrdemServicoController implements Initializable {

    @FXML
    private TableColumn<ItemPeca, String> colPecaNome;

    @FXML
    private TableColumn<ItemPeca, String> colPecaPreco;

    @FXML
    private TableColumn<ItemPeca, String> colPecaQtd;

    @FXML
    private TableColumn<ItemPeca, String> colPecaTotal;

    @FXML
    private ComboBox<Cliente> comboCliente;

    @FXML
    private ComboBox<Peca> comboPeca;

    @FXML
    private ComboBox<String> comboStatus;

    @FXML
    private ComboBox<Veiculo> comboVeiculo;

    @FXML
    private Label lblMaoObra;

    @FXML
    private Label lblPecas;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<ItemPeca> tabelaPecas;

    @FXML
    private TextArea txtDescricao;

    @FXML
    private TextField txtMaoObra;

    @FXML
    private TextField txtQuantidade;

    private ObservableList<ItemPeca> pecasAdicionadas = FXCollections.observableArrayList();
    private Alertas alertas = new Alertas();
    private DecimalFormat df = new DecimalFormat("R$ #,##0.00");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboCliente.setItems(ClienteDAO.listarClientes());
        configurarComboCliente();


        comboPeca.setItems(PecaDAO.listarPecas());
        configurarComboPeca();

        comboStatus.setItems(FXCollections.observableArrayList(
                "Em Serviço", "Aguardando Peças", "Pronto para Entrega", "Finalizado"
        ));
        comboStatus.setValue("Em Serviço");

        colPecaNome.setCellValueFactory(data -> data.getValue().nomePecaProperty());
        colPecaQtd.setCellValueFactory(data -> data.getValue().quantidadeProperty());
        colPecaPreco.setCellValueFactory(data -> data.getValue().precoUnitarioProperty());
        colPecaTotal.setCellValueFactory(data -> data.getValue().totalProperty());
        tabelaPecas.setItems(pecasAdicionadas);
    }


    private void configurarComboCliente() {
        comboCliente.setButtonCell(new ListCell<Cliente>() {
            @Override
            protected void updateItem(Cliente item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome());
            }
        });
        comboCliente.setCellFactory(lv -> new ListCell<Cliente>() {
            @Override
            protected void updateItem(Cliente item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " - CPF: " + item.getCpf());
            }
        });
    }

    private void configurarComboPeca() {
        comboPeca.setButtonCell(new ListCell<Peca>() {
            @Override
            protected void updateItem(Peca item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNomePeca());
            }
        });
        comboPeca.setCellFactory(lv -> new ListCell<Peca>() {
            @Override
            protected void updateItem(Peca item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNomePeca() + " - R$ " + item.getPrecoUnitario());
            }
        });
    }

    @FXML
    void carregarVeiculos(ActionEvent event) {
        Cliente clienteSelecionado = comboCliente.getValue();
        if (clienteSelecionado != null) {
            comboVeiculo.setItems(VeiculoDAO.listarVeiculosPorCliente(clienteSelecionado.getId()));

            comboVeiculo.setButtonCell(new ListCell<Veiculo>() {
                @Override
                protected void updateItem(Veiculo item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getPlaca() + " - " + item.getModelo());
                }
            });
            comboVeiculo.setCellFactory(lv -> new ListCell<Veiculo>() {
                @Override
                protected void updateItem(Veiculo item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getPlaca() + " - " + item.getModelo() + " (" + item.getAno() + ")");
                }
            });
        }
    }

    @FXML
    void adicionarPeca(ActionEvent event) {
        Peca pecaSelecionada = comboPeca.getValue();
        String qtdTexto = txtQuantidade.getText().trim();

        if (pecaSelecionada == null || qtdTexto.isEmpty()) {
            alertas.mostrarErro("Selecione uma peça e informe a quantidade!");
            return;
        }

        try {
            int quantidade = Integer.parseInt(qtdTexto);
            double precoUnit = Double.parseDouble(pecaSelecionada.getPrecoUnitario().replace(",", "."));
            double total = quantidade * precoUnit;

            ItemPeca item = new ItemPeca(
                    pecaSelecionada.getIdPeca(),
                    pecaSelecionada.getNomePeca(),
                    String.valueOf(quantidade),
                    df.format(precoUnit),
                    df.format(total)
            );

            pecasAdicionadas.add(item);
            comboPeca.setValue(null);
            txtQuantidade.clear();
            atualizarResumo();

        } catch (NumberFormatException e) {
            alertas.mostrarErro("Quantidade inválida!");
        }
    }

    private void atualizarResumo() {
        try {
            double maoObra = txtMaoObra.getText().isEmpty() ? 0 : Double.parseDouble(txtMaoObra.getText().replace(",", "."));
            lblMaoObra.setText(df.format(maoObra));

            double totalPecas = 0;
            for (ItemPeca item : pecasAdicionadas) {
                String totalStr = item.getTotal().replace("R$", "").replace(".", "").replace(",", ".").trim();
                totalPecas += Double.parseDouble(totalStr);
            }
            lblPecas.setText(df.format(totalPecas));
            lblTotal.setText(df.format(maoObra + totalPecas));

        } catch (Exception e) {
            lblMaoObra.setText("R$ 0,00");
        }
    }


    @FXML
    void criarOrdem(ActionEvent event) {

        if (comboVeiculo.getValue() == null) {
            alertas.mostrarErro("Selecione um veículo!");
            return;
        }
        if (txtDescricao.getText().trim().isEmpty()) {
            alertas.mostrarErro("Informe a descrição do serviço!");
            return;
        }

        try {
            double maoObra = txtMaoObra.getText().isEmpty() ? 0 : Double.parseDouble(txtMaoObra.getText().replace(",", "."));

            // Criar ordem
            int idOrdem = OrdemServicoDAO.criarOrdem(
                    comboVeiculo.getValue().getId(),
                    txtDescricao.getText(),
                    maoObra,
                    comboStatus.getValue()
            );

            if (idOrdem > 0) {
                // Adicionar peças
                for (ItemPeca item : pecasAdicionadas) {
                    double precoUnit = Double.parseDouble(item.getPrecoUnitario().replace("R$", "").replace(".", "").replace(",", ".").trim());
                    OrdemServicoDAO.adicionarPecaNaOrdem(
                            idOrdem,
                            item.getIdPeca(),
                            Integer.parseInt(item.getQuantidade()),
                            precoUnit
                    );
                }

                alertas.mostrarConfirmacao("Ordem de serviço #" + idOrdem + " criada com sucesso!");
                voltar(event);
            } else {
                alertas.mostrarErro("Erro ao criar ordem de serviço!");
            }

        } catch (Exception e) {
            alertas.mostrarErro("Erro: " + e.getMessage());
        }
    }


    @FXML
    void removerPeca(ActionEvent event) {
        ItemPeca itemSelecionado = tabelaPecas.getSelectionModel().getSelectedItem();
        if (itemSelecionado != null) {
            pecasAdicionadas.remove(itemSelecionado);
            atualizarResumo();
        }
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/PainelOrdensServico.fxml");
    }

}
