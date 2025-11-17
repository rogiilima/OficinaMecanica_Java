package DB;

import java.sql.*;

public class ConexaoComBanco {
    private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/oficina";
    private static final String USER = "root";
    private static final String PASS = "";

    // metodo de conexão com o banco de dados
    public static Connection getConnection(){
        try {
            Class.forName(DRIVE);

            return DriverManager.getConnection(URL,USER,PASS);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro na conexão do Banco De Dados: ",e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão do Banco De Dados: ",e);
        }
    }

    public static void fechaConexao(Connection connection){
        try{
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não fechou o banco de dados: ", e);
        }
    }


    public static void fechaConexao(Connection connection, PreparedStatement ps){

        fechaConexao(connection);
        try{
            if (ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não fechou o banco de dados: ", e);
        }
    }
    // uso de sobrecarga para tratar os diversos tipos de fechamentos
    public static void fechaConexao(Connection connection, PreparedStatement ps, ResultSet rs){

        fechaConexao(connection, ps, rs);
        try{
            if (rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não fechou o banco de dados: ", e);
        }
    }

}
