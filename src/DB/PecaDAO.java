package DB;

import Model.Peca;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class PecaDAO {


    public static ObservableList<Peca> listarPecas() {
        ObservableList<Peca> listaPecas = FXCollections.observableArrayList();
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement("SELECT * FROM peca ORDER BY nome_peca");
            rs = stmt.executeQuery();

            DecimalFormat df = new DecimalFormat("#,##0.00");

            while (rs.next()) {
                Peca peca = new Peca(
                        rs.getString("id_peca"),
                        rs.getString("nome_peca"),
                        df.format(rs.getDouble("preco_unitario")),
                        rs.getString("quantidade_estoque")
                );
                listaPecas.add(peca);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar peças: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return listaPecas;
    }



    public static Peca buscarPorId(String idPeca) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement("SELECT * FROM peca WHERE id_peca = ?");
            stmt.setInt(1, Integer.parseInt(idPeca));
            rs = stmt.executeQuery();

            DecimalFormat df = new DecimalFormat("#,##0.00");

            if (rs.next()) {
                return new Peca(
                        rs.getString("id_peca"),
                        rs.getString("nome_peca"),
                        df.format(rs.getDouble("preco_unitario")),
                        rs.getString("quantidade_estoque")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar peça: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }
        return null;
    }

    public static boolean adicionarPeca(String nome, double preco, int quantidade) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(
                "INSERT INTO peca (nome_peca, preco_unitario, quantidade_estoque) VALUES (?,?,?)"
            );
            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, quantidade);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar peça: " + e.getMessage());
            return false;
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }
    }

    public static boolean deletarPeca(String id){
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE FROM peca WHERE id_peca=?");
            stmt.setInt(1, Integer.parseInt(id));

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar peça: " + e.getMessage());
            return false;
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }
    }

    public static double calcularValorTotalEstoque() {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double total = 0;

        try {
            stmt = conexao.prepareStatement(
                    "SELECT SUM(preco_unitario * quantidade_estoque) as total FROM peca"
            );
            rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao calcular valor do estoque: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return total;
    }

    // Listar peças com estoque baixo (menos de 10 unidades)
    public static ObservableList<Peca> listarEstoqueBaixo() {
        ObservableList<Peca> listaPecas = FXCollections.observableArrayList();
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement(
                    "SELECT * FROM peca WHERE quantidade_estoque < 10 ORDER BY quantidade_estoque"
            );
            rs = stmt.executeQuery();

            DecimalFormat df = new DecimalFormat("#,##0.00");

            while (rs.next()) {
                Peca peca = new Peca(
                        rs.getString("id_peca"),
                        rs.getString("nome_peca"),
                        df.format(rs.getDouble("preco_unitario")),
                        rs.getString("quantidade_estoque")
                );
                listaPecas.add(peca);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar estoque baixo: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return listaPecas;
    }
}
