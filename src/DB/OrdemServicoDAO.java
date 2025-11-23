package DB;

import Model.OrdemDeServico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.DecimalFormat;

public class OrdemServicoDAO {

    public static boolean deletarOrdem(String idOrdem) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE FROM ordem_servico WHERE id_ordem=?");
            stmt.setInt(1, Integer.parseInt(idOrdem));

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar ordem: " + e.getMessage());
            return false;
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }
    }

    public static ObservableList<OrdemDeServico> listarOrdensPorVeiculo(String idVeiculo) {
        ObservableList<OrdemDeServico> listaOrdens = FXCollections.observableArrayList();
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT o.id_ordem, o.id_veiculo, o.descricao, o.valor_mao_obra, o.status, " +
                    "o.data_abertura, o.data_finalizacao, " +
                    "(o.valor_mao_obra + COALESCE((SELECT SUM(op.quantidade * op.preco_unitario) " +
                    "FROM ordem_peca op WHERE op.id_ordem = o.id_ordem), 0)) as valor_total " +
                    "FROM ordem_servico o " +
                    "WHERE o.id_veiculo = ? " +
                    "ORDER BY o.data_abertura DESC";

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idVeiculo));
            rs = stmt.executeQuery();

            DecimalFormat df = new DecimalFormat("R$ #,##0.00");

            while (rs.next()) {
                String dataFinal = rs.getString("data_finalizacao");
                if (dataFinal == null) {
                    dataFinal = "-";
                }

                OrdemDeServico ordem = new OrdemDeServico(
                        rs.getString("id_ordem"),
                        rs.getString("id_veiculo"),
                        rs.getString("descricao"),
                        df.format(rs.getDouble("valor_mao_obra")),
                        rs.getString("status"),
                        rs.getString("data_abertura"),
                        dataFinal,
                        df.format(rs.getDouble("valor_total"))
                );
                listaOrdens.add(ordem);
            }


        } catch (SQLException e) {
            System.out.println("Erro ao listar ordens: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return listaOrdens;
    }

    public static double calcularValorTotalVeiculo(String idVeiculo) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double total = 0;

        try {
            // faz o calculo da quantidade vezes o preço unitário
            String sql = "SELECT SUM(o.valor_mao_obra + COALESCE((SELECT SUM(op.quantidade * op.preco_unitario) " +
                    "FROM ordem_peca op WHERE op.id_ordem = o.id_ordem), 0)) as total " +
                    "FROM ordem_servico o " +
                    "WHERE o.id_veiculo = ?";

            stmt = conexao.prepareStatement(sql);
            // passa para inteiro a String
            stmt.setInt(1, Integer.parseInt(idVeiculo));
            rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao calcular total: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

     return total;
    }

    public static ObservableList<OrdemDeServico> listarTodasOrdens() {
        ObservableList<OrdemDeServico> listaOrdens = FXCollections.observableArrayList();
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT o.id_ordem, o.id_veiculo, o.descricao, o.valor_mao_obra, o.status, " +
                    "o.data_abertura, o.data_finalizacao, v.placa, c.nome_cliente, " +
                    "(o.valor_mao_obra + COALESCE((SELECT SUM(op.quantidade * op.preco_unitario) " +
                    "FROM ordem_peca op WHERE op.id_ordem = o.id_ordem), 0)) as valor_total " +
                    "FROM ordem_servico o " +
                    "INNER JOIN veiculo v ON o.id_veiculo = v.id_veiculo " +
                    "INNER JOIN cliente c ON v.id_cliente = c.id_cliente " +
                    "ORDER BY o.data_abertura DESC";

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            DecimalFormat df = new DecimalFormat("R$ #,##0.00");

            while (rs.next()) {
                String dataFinal = rs.getString("data_finalizacao");
                if (dataFinal == null) {
                    dataFinal = "-";
                }

                OrdemDeServico ordem = new OrdemDeServico(
                        rs.getString("id_ordem"),
                        rs.getString("id_veiculo"),
                        rs.getString("descricao"),
                        df.format(rs.getDouble("valor_mao_obra")),
                        rs.getString("status"),
                        rs.getString("data_abertura"),
                        dataFinal,
                        df.format(rs.getDouble("valor_total")),
                        rs.getString("placa"),
                        rs.getString("nome_cliente")
                );
                listaOrdens.add(ordem);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar ordens: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return listaOrdens;
    }

    // cria ordem e vai retornar um id gerado
    public static int criarOrdem(String idVeiculo, String descricao, double valorMaoObra, String status) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idOrdemGerado = -1;

        try {
            stmt = conexao.prepareStatement(
                    "INSERT INTO ordem_servico (id_veiculo, descricao, valor_mao_obra, status) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, Integer.parseInt(idVeiculo));
            stmt.setString(2, descricao);
            stmt.setDouble(3, valorMaoObra);
            stmt.setString(4, status);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    idOrdemGerado = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao criar ordem: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return idOrdemGerado;
    }


}



