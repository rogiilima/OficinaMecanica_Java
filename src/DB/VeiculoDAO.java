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
                    "INSERT INTO `veiculo` (placa, modelo, ano, id_cliente) VALUES (?,?,?,?)"
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

    public static ObservableList<Veiculo> listarVeiculos() {
        ObservableList<Veiculo> listaVeiculos = FXCollections.observableArrayList();
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement(
                    "SELECT v.id_veiculo, v.placa, v.modelo, v.ano, v.id_cliente, " +
                            "c.nome_cliente, c.cpf_cliente " +
                            "FROM `veiculo` v " +
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

    public static ObservableList<Veiculo> buscarVeiculos(String busca) {
        ObservableList<Veiculo> listaVeiculos = FXCollections.observableArrayList();
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement(
                    "SELECT v.id_veiculo, v.placa, v.modelo, v.ano, v.id_cliente, " +
                            "c.nome_cliente, c.cpf_cliente " +
                            "FROM `veiculo` v " +
                            "INNER JOIN cliente c ON v.id_cliente = c.id_cliente " +
                            "WHERE v.placa LIKE ? OR v.modelo LIKE ? " +
                            "ORDER BY v.placa"
            );
            String buscaParam = "%" + busca + "%";
            stmt.setString(1, buscaParam);
            stmt.setString(2, buscaParam);
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
            System.out.println("Erro ao buscar veículos: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return listaVeiculos;
    }

    public static boolean atualizarVeiculo(String id, String placa, String modelo, String ano, String idCliente) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement(
                    "UPDATE `veiculo` SET placa=?, modelo=?, ano=?, id_cliente=? WHERE id_veiculo=?"
            );
            stmt.setString(1, placa);
            stmt.setString(2, modelo);
            stmt.setString(3, ano);
            stmt.setInt(4, Integer.parseInt(idCliente));
            stmt.setInt(5, Integer.parseInt(id));

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veículo: " + e.getMessage());
            return false;
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }
    }


    public static boolean deletarVeiculo(String id) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE FROM `veiculo` WHERE id_veiculo=?");
            stmt.setInt(1, Integer.parseInt(id));

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar veículo: " + e.getMessage());
            return false;
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }
    }

    // Listar veículos de um cliente específico
    public static ObservableList<Veiculo> listarVeiculosPorCliente(String idCliente) {
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
                            "WHERE v.id_cliente = ? " +
                            "ORDER BY v.placa"
            );
            stmt.setInt(1, Integer.parseInt(idCliente));
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
            System.out.println("Erro ao listar veículos do cliente: " + e.getMessage());
            e.printStackTrace(); // Adiciona stack trace para debug
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter ID do cliente: " + idCliente);
            e.printStackTrace();
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return listaVeiculos;
    }
}