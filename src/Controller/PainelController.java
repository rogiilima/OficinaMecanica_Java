package Controller;


import DB.ClienteDAO;
import Model.Cliente;
import Model.MudarTela;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PainelController implements Initializable {


    @FXML
    private TableColumn<Cliente, String> colCpf;

    @FXML
    private TableColumn<Cliente, String> colId;

    @FXML
    private TableColumn<Cliente, String> colNome;

    @FXML
    private TableColumn<Cliente, String> colTelefone;

    @FXML
    private Label lblUsuario;

    @FXML
    private TableView<Cliente> tabelaDados;

    @FXML
    private TextField txtBusca;

    Alertas alertas = new Alertas();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colNome.setCellValueFactory(data-> data.getValue().nomeProperty());
        colCpf.setCellValueFactory( data->data.getValue().cpfProperty());
        colTelefone.setCellValueFactory( data->data.getValue().telefoneProperty());

        carregarClientes();
    }

    private void carregarClientes(){
        tabelaDados.setItems(ClienteDAO.listarClientes());
    }

    @FXML
    void irParaAdicionar(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/view/AdicionarClientes.fxml");
    }

    @FXML
    void irParaEditar(ActionEvent event) throws IOException {
        Cliente clienteSelecionado = tabelaDados.getSelectionModel().getSelectedItem();

        if (clienteSelecionado == null){
            alertas.mostrarErro("Selecione um cliente para editar");
        }

        // Não podemos usar a classe mudar de tela pq precisamos carregar dados do cliente antes
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MudarClientes.fxml"));
        Parent root = loader.load();

        MudarClientesController controller = loader.getController();
        controller.carregarDadosCliente(clienteSelecionado);


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    void excluirCliente(ActionEvent event){
        // pega o item que tu seleciona da tabela, é metodo do JavaFX também
        Cliente clienteSelecionado = tabelaDados.getSelectionModel().getSelectedItem();

        if (clienteSelecionado == null){
            alertas.mostrarErro("Selecione algum cliente");
        }

        boolean clienteExcluido = ClienteDAO.deletarCliente(clienteSelecionado.getId());

        if (clienteExcluido){
            alertas.mostrarConfirmacao("cliente deletado com SUCESSO");
            // para atualizar a tabela
            carregarClientes();
        }else {
            alertas.mostrarErro("Não foi possível deletar o cliente");
        }

    }

    @FXML
    void irParaOrdem(ActionEvent event) throws IOException {
    MudarTela.trocarJanela(event,"/View/PainelOrdensServico.fxml" );
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
    void irParaVeiculos(ActionEvent event) throws IOException{
    MudarTela.trocarJanela(event, "/View/Veiculos.fxml");
    }



}
