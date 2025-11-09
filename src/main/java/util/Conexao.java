package util;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/ocupamais";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(true);
            return conn;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao banco: " + e.getMessage());
        }
    }
}