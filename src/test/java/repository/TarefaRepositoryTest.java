package repository;

import database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Tarefa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TarefaRepositoryTest {

    private Connection conexao;
    private TarefaRepository repository;

    @Before
    public void prepararBanco()
            throws SQLException {

        conexao =
                Conexao.conectarTeste();

        String sql = """
                CREATE TABLE tarefas (
                    id INTEGER PRIMARY KEY,
                    titulo TEXT NOT NULL,
                    descricao TEXT,
                    concluida INTEGER NOT NULL
                )
                """;

        try (PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.executeUpdate();
        }

        repository =
                new TarefaRepository(conexao);
    }

    @After
    public void fecharBanco()
            throws SQLException {

        if (conexao != null
                && !conexao.isClosed()) {

            conexao.close();
        }
    }

    @Test
    public void deveSalvarTarefa()
            throws SQLException {

        Tarefa tarefa =
                new Tarefa(
                        1,
                        "Estudar JUnit",
                        "Criar testes automatizados"
                );

        repository.salvar(tarefa);

        ArrayList<Tarefa> tarefas =
                repository.listar();

        assertEquals(
                1,
                tarefas.size()
        );

        assertEquals(
                "Estudar JUnit",
                tarefas.get(0).getTitulo()
        );
    }

    @Test
    public void deveListarTarefas()
            throws SQLException {

        repository.salvar(
                new Tarefa(
                        1,
                        "Tarefa 1",
                        "Primeira tarefa"
                )
        );

        repository.salvar(
                new Tarefa(
                        2,
                        "Tarefa 2",
                        "Segunda tarefa"
                )
        );

        ArrayList<Tarefa> tarefas =
                repository.listar();

        assertEquals(
                2,
                tarefas.size()
        );

        assertEquals(
                "Tarefa 1",
                tarefas.get(0).getTitulo()
        );

        assertEquals(
                "Tarefa 2",
                tarefas.get(1).getTitulo()
        );
    }

    @Test
    public void deveAtualizarTarefa()
            throws SQLException {

        Tarefa tarefa =
                new Tarefa(
                        1,
                        "Estudar Java",
                        "Estudar Java básico"
                );

        repository.salvar(tarefa);

        tarefa.setTitulo(
                "Estudar JavaFX"
        );

        tarefa.setDescricao(
                "Estudar interface gráfica"
        );

        tarefa.setConcluida(true);

        repository.atualizar(tarefa);

        ArrayList<Tarefa> tarefas =
                repository.listar();

        assertEquals(
                1,
                tarefas.size()
        );

        assertEquals(
                "Estudar JavaFX",
                tarefas.get(0).getTitulo()
        );

        assertEquals(
                "Estudar interface gráfica",
                tarefas.get(0).getDescricao()
        );

        assertTrue(
                tarefas.get(0).isConcluida()
        );
    }

    @Test
    public void deveExcluirTarefa()
            throws SQLException {

        Tarefa tarefa =
                new Tarefa(
                        1,
                        "Tarefa para excluir",
                        "Teste de exclusão"
                );

        repository.salvar(tarefa);

        repository.excluir(1);

        ArrayList<Tarefa> tarefas =
                repository.listar();

        assertTrue(
                tarefas.isEmpty()
        );
    }
}