package Controller;


import DB.ClienteDAO;
import Model.Cliente;
import Model.MudarTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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


    @Override
    public void initialize(URL location, ResourceBundle resources){
        colId.setCellValueFactory(data -> data.getValue().getId());
        colNome.setCellValueFactory(data-> data.getValue().getNome());
        colCpf.setCellValueFactory( data->data.getValue().getCpf());
        colTelefone.setCellValueFactory( data->data.getValue().getCpf());

    }

    private void carregarClientes(){
        tabelaDados.setItems(ClienteDAO.);
    }

    @FXML
    void irParaAdicionar(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/view/AdicionarClientes.fxml");
    }

}
