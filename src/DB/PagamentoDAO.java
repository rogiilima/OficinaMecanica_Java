package DB;

import Model.Pagamento;
import java.sql.*;

public class PagamentoDAO {

    public static boolean registrarPagamento(int idOrdem, double valor, String formaPagamento) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO pagamento (id_ordem, valor, forma_pagamento, status, data_pagamento) VALUES (?, ?, ?, 'Pago', NOW())";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idOrdem);
            stmt.setDouble(2, valor);
            stmt.setString(3, formaPagamento);

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                // 2. Opcional: Atualizar status da ordem para Finalizado se pago
                OrdemServicoDAO.atualizarStatus(String.valueOf(idOrdem), "Finalizado");
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Erro ao registrar pagamento: " + e.getMessage());
            return false;
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }
    }

    public static Pagamento buscarPagamentoPorOrdem(int idOrdem) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM pagamento WHERE id_ordem = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idOrdem);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Pagamento(
                        String.valueOf(rs.getInt("id_pagamento")),
                        String.valueOf(rs.getInt("id_ordem")),
                        rs.getDouble("valor"),
                        rs.getString("forma_pagamento"),
                        rs.getString("status"),
                        rs.getString("data_pagamento")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pagamento: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }
        return null;
    }
}