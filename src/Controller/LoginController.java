package Controller;

import DB.ConexaoComBanco;
import Model.Administrador;
import Model.MudarTela;
import Model.Validacoes;
import Templates.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {

        @FXML
        private Button botaoLogin;

        @FXML
        private TextField emailDoLogin;
        @FXML
        private PasswordField senhaDoLogin;

        private Administrador administrador;

    @FXML
    void fazerLogin(ActionEvent event) {
        Validacoes email = new Validacoes();
        Alertas alerta = new Alertas();

        // usando a instância criada e passando os textos como parâmetros, conseguindo usar o MODEL, junto com o controller
        administrador = new Administrador(emailDoLogin.getText(), senhaDoLogin.getText());

        boolean isValidEmail = email.validarEmail(administrador.getEmail());

        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet resultadoDaQuery = null;
        if (isValidEmail) {
            try {
                stmt = conexao.prepareStatement("SELECT * FROM administrador WHERE email = ? AND senha = ?"); // previne de SQL injection
                stmt.setString(1, administrador.getEmail());
                stmt.setString(2, administrador.getSenha());

                // Query pode ser usado para select, Update para inserir, update e delete
                // e execute, é para todos
                // ResultSet, verifica o resultado da query, e atribui a execução da query
                resultadoDaQuery = stmt.executeQuery();

                if (resultadoDaQuery.next()){
                    alerta.mostrarConfirmacao();
                    MudarTela.trocarJanela(event, "/View/PainelAdministrativo.fxml");
                }
                else {
                    alerta.mostrarErro("Erro, email ou senha inválido, tente novamente!");
                }
                } catch (SQLException | IOException e) {
                alerta.mostrarErro();
                System.out.println(e);
            }finally {
                ConexaoComBanco.fechaConexao(conexao, stmt, resultadoDaQuery);
            }
        }else{
            alerta.mostrarErro("Email inválido, tente novamente!");
        }


    }

}
