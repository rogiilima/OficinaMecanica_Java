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

}
