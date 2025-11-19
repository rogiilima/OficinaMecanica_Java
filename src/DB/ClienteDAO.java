package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    // função testada funcionando normalmente
    public static void adicionarCliente (String nome, String cpf, String telefone){
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO cliente (nome_cliente, cpf_cliente, telefone) VALUES (?,?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);

            stmt.executeUpdate();

        } catch (SQLException e) {
        System.out.println("Erro no banco de dados" + e);
        }finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }

    }

    public static void mostrarClientes(){
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
    }

    public static void deletarClientes(){

    }

    public static void updadeClientes(){

    }


    public static void main(String[] args) {
    }
}
