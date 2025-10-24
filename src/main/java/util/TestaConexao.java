package util;
import java.sql.Connection;

public class TestaConexao {
    public static void main(String[] args) {

        try {
            Connection conn = Conexao.getConnection();
            if (conn != null) {
                System.out.println("Conectado ao banco de dados");
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
        
    }
}