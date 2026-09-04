package repository;

import database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Tarefa;

public class TarefaRepository {
    public void salvar (Tarefa tarefa) throws SQLException {
        String sql = """
                INSERT INTO tarefas (id, titulo, descricao, concluida)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexao = Conexao.conectar();
            PreparedStatement statement = conexao.prepareStatement(sql)) {
        
            statement.setInt(1, tarefa.getId());
            statement.setString(2, tarefa.getTitulo());
            statement.setString(3, tarefa.getDescricao());
            statement.setInt(4, tarefa.isConcluida() ? 1 : 0);
            
            statement.executeUpdate();
        }
    }
}
