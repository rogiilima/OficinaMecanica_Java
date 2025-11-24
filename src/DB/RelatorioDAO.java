package DB;

import Model.OrdemDeServico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.DecimalFormat;

public class RelatorioDAO {

    public static double obterFaturamentoTotal() {
        Connection conexao = ConexaoComBanco.getConnection();
        double total = 0;
        try {
            PreparedStatement stmt = conexao.prepareStatement(
                    "SELECT SUM(valor_total) as total FROM pagamento WHERE status_pagamento = 'Pago'"
            );
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter faturamento: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao);
        }
        return total;
    }

    public static int obterTotalOrdensFinalizadas() {
        Connection conexao = ConexaoComBanco.getConnection();
        int total = 0;
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT COUNT(*) as total FROM ordem_servico WHERE status = 'Finalizado'");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) total = rs.getInt("total");
        } catch (SQLException e) {
            System.out.println("Erro ao contar ordens: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao);
        }
        return total;
    }

    public static ObservableList<OrdemDeServico> listarOrdensParaRelatorio() {
        ObservableList<OrdemDeServico> lista = FXCollections.observableArrayList();
        Connection conexao = ConexaoComBanco.getConnection();

        try {
            String sql = "SELECT o.id_ordem, o.id_veiculo, o.descricao, o.valor_mao_obra, o.status, " +
                    "o.data_abertura, o.data_finalizacao, v.placa, c.nome_cliente, " +
                    "(SELECT COALESCE(SUM(valor_total), 0) FROM pagamento p WHERE p.id_ordem = o.id_ordem) as valor_total " +
                    "FROM ordem_servico o " +
                    "INNER JOIN veiculo v ON o.id_veiculo = v.id_veiculo " +
                    "INNER JOIN cliente c ON v.id_cliente = c.id_cliente " +
                    "WHERE o.status = 'Finalizado' " +
                    "ORDER BY o.data_finalizacao DESC";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            DecimalFormat df = new DecimalFormat("R$ #,##0.00");

            while (rs.next()) {
                OrdemDeServico ordem = new OrdemDeServico(
                        rs.getString("id_ordem"),
                        rs.getString("id_veiculo"),
                        rs.getString("descricao"),
                        df.format(rs.getDouble("valor_mao_obra")),
                        rs.getString("status"),
                        rs.getString("data_abertura"),
                        rs.getString("data_finalizacao"),
                        df.format(rs.getDouble("valor_total")),
                        rs.getString("placa"),
                        rs.getString("nome_cliente")
                );
                lista.add(ordem);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar dados para relatório: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao);
        }
        return lista;
    }
}