package DB;

import Model.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    // função testada funcionando normalmente
    public static void adicionarCliente(String nome, String cpf, String telefone){
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO cliente (nome_cliente, cpf_cliente, telefone) VALUES (?,?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);

            stmt.executeUpdate();

        } catch (SQLException e) {
        System.out.println("Erro no banco de dados: " + e);
        }finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }

    }
    public static boolean deletarCliente(String id){
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        // gambiarra primeiro passando para inteiro
        int idTransformado = Integer.valueOf(id);
        try {
            stmt = conexao.prepareStatement("DELETE FROM cliente WHERE id_cliente=?");
            stmt.setInt(1, idTransformado);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados: " + e);
            return false;
        }finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }

    }
    // observablelist é uma estrutura especifíca do JAVAFX
    public static ObservableList<Cliente> listarClientes(){
        ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conexao.prepareStatement("SELECT * FROM cliente ORDER BY id_cliente");
            rs = stmt.executeQuery();

            // enquanto tiver linha para ler no banco
            while (rs.next()){
                Cliente cliente = new Cliente(
                        // realiza a leituras
                        rs.getString("id_cliente"),
                        rs.getString("nome_cliente"),
                        rs.getString("cpf_cliente"),
                        rs.getString("telefone"),
                        rs.getBoolean("isVip")
                );
                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro no banco de dados: " + e);
        }
        return listaClientes;
    }

    public static void main(String[] args) {
    }

    public static boolean atualizarCliente(String idCliente, String nome, String cpfLimpo, String telefoneLimpo, boolean b) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE cliente SET nome_cliente = ?, cpf_cliente = ?, telefone = ? WHERE id_cliente = ?");
            stmt.setString(1, nome);
            stmt.setString(2, cpfLimpo);
            stmt.setString(3, telefoneLimpo);
            stmt.setString(4, idCliente);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados: " + e);
            return false;
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }
    }
}
