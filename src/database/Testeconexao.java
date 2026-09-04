package database;

import java.sql.Connection;
import java.sql.SQLException;

public class Testeconexao {

    public static void main(String[] args) {
        try (Connection conexao = Conexao.conectar()) {
            System.out.println("Conexão realizada com sucesso");
        } catch (SQLException erro) {
            System.out.println("Erro aoconectar com o banco de dados");
            erro.printStackTrace();
        }
    } 
}
