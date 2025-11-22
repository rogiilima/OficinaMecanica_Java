package Controller;

import DB.ClienteDAO;
import Model.Cliente;
import Model.MudarTela;
import Model.Validacoes;
import Templates.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MudarClientesController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Label lblMensagem;

    private String idCliente;
    private Validacoes validacoes = new Validacoes();
    private Alertas alertas = new Alertas();


    public void carregarDadosCliente(Cliente cliente) {
        this.idCliente = cliente.getId();
        txtNome.setText(cliente.getNome());
        txtCpf.setText(cliente.getCpf());
        txtTelefone.setText(cliente.getTelefone());
    }

    @FXML
    void salvarCliente(ActionEvent event) {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String telefone = txtTelefone.getText().trim();

        if (!validacoes.validarCPF(cpf)) {
            lblMensagem.setText("CPF inválido!");
            return;
        }

        if (!validacoes.validarTelefone(telefone)) {
            lblMensagem.setText("Telefone inválido! Deve ter 11 dígitos.");
            return;
        }


        String cpfLimpo = cpf.replaceAll("\\D+", "");
        String telefoneLimpo = telefone.replaceAll("\\D+", "");


        boolean sucesso = ClienteDAO.atualizarCliente(idCliente, nome, cpfLimpo, telefoneLimpo, false);

        if (sucesso) {
            alertas.mostrarConfirmacao("Cliente atualizado com sucesso!");
            try {
                voltarParaPainel(event);
            } catch (IOException e) {
                lblMensagem.setText("Erro ao voltar para o painel!");
            }
        } else {
            lblMensagem.setText("Erro ao atualizar cliente!");
        }
    }

    @FXML
    void voltarParaPainel(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event,"/View/PainelAdministrativo.fxml");
    }
}
