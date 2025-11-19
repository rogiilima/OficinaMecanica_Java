package Controller;

import DB.ConexaoComBanco;
import Model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PainelController {

    @FXML
    private TableColumn<?, ?> colCpf;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNome;

    @FXML
    private TableColumn<?, ?> colTelefone;

    @FXML
    private Label dataHora;

    @FXML
    private Label lblUsuario;

    @FXML
    private TableView<?> tabelaDados;

    @FXML
    private TextField txtBusca;


    public void mostrarClientes(Cliente cliente){
    }

}