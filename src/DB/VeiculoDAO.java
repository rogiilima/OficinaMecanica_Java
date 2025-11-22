package DB;

import Model.Veiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VeiculoDAO {

    public static boolean adicionarVeiculo(String placa, String modelo, String ano, String idCliente) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(
                    "INSERT INTO veiculo (placa, modelo, ano, id_cliente) VALUES (?,?,?,?)"
            );
            stmt.setString(1, placa);
            stmt.setString(2, modelo);
            stmt.setString(3, ano);
            stmt.setInt(4, Integer.parseInt(idCliente));

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar veículo: " + e.getMessage());
            return false;
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }
    }

    // mesma lógica usada no primeiro, única diferença é na query
    public static ObservableList<Veiculo> listarVeiculos(){
            ObservableList<Veiculo> listaVeiculos = FXCollections.observableArrayList();
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement(
                    "SELECT v.id_veiculo, v.placa, v.modelo, v.ano, v.id_cliente, " +
                            "c.nome_cliente, c.cpf_cliente " +
                            "FROM veiculo v " +
                            "INNER JOIN cliente c ON v.id_cliente = c.id_cliente " +
                            "ORDER BY v.placa"
            );
            rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                        rs.getString("id_veiculo"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getString("ano"),
                        rs.getString("id_cliente"),
                        rs.getString("nome_cliente"),
                        rs.getString("cpf_cliente")
                );
                listaVeiculos.add(veiculo);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return listaVeiculos;
    }
}

