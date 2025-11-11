package Controller;

import DB.ConexaoComBanco;
import Model.Validacoes;
import Templates.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    @FXML
    void fazerLogin(ActionEvent event) {
        Validacoes email = new Validacoes();
        Alertas alerta = new Alertas();
        String email_valido = emailDoLogin.getText();
        String senha = senhaDoLogin.getText();
        boolean isValidEmail = email.validarEmail(email_valido);

        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        if (isValidEmail) {
            try {
                stmt = conexao.prepareStatement("SELECT * FROM administrador WHERE email = ? AND senha = ?"); // previne de SQL injection
                stmt.setString(1, email_valido);
                stmt.setString(2, senha);

                // Query pode ser usado para select, Update para inserir, update e delete
                // e execute, é para todos
                // ResultSet, compara um resultado da query, e atribui a execução da query
                ResultSet resultadoDaQuery = stmt.executeQuery();

                if (resultadoDaQuery.next()){
                    alerta.mostrarConfirmacao();
                }
                else {
                    alerta.mostrarErro("Erro de digitação, tente novamente!");
                }
                } catch (SQLException e) {
                alerta.mostrarErro();
                System.out.println(e);
            }finally {
                ConexaoComBanco.fechaConexao(conexao, stmt);
            }
        }else{
            alerta.mostrarErro("Email inválido, tente novamente!");
        }


    }

}
