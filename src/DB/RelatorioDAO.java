package DB;

import java.sql.*;

public class RelatorioDAO {

    public static double obterFaturamentoTotal() {
        Connection conexao = ConexaoComBanco.getConnection();
        double total = 0;
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT SUM(valor) as total FROM pagamento");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) total = rs.getDouble("total");
        } catch (SQLException e) { e.printStackTrace(); }
        return total;
    }

    public static int obterTotalOrdensFinalizadas() {
        Connection conexao = ConexaoComBanco.getConnection();
        int total = 0;
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT COUNT(*) as total FROM ordem_servico WHERE status = 'Finalizado'");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) total = rs.getInt("total");
        } catch (SQLException e) { e.printStackTrace(); }
        return total;
    }
}