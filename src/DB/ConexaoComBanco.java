package DB;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;
public class ConexaoComBanco {

    private static final Dotenv dotenv = Dotenv.load();

    private static final String DRIVE = "com.mysql.cj.jdbc.Driver";
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASS = dotenv.get("DB_PASSWORD");

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

        fechaConexao(connection, ps);
        try{
            if (rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não fechou o banco de dados: ", e);
        }
    }

}
