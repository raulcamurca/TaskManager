package model;

import exceptions.TarefaException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TarefaTest {

    @Test
    public void deveCriarTarefa() {

        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        assertEquals(
                1,
                tarefa.getId()
        );

        assertEquals(
                "Estudar Java",
                tarefa.getTitulo()
        );

        assertEquals(
                "Estudar JUnit",
                tarefa.getDescricao()
        );

        assertFalse(
                tarefa.isConcluida()
        );
    }

    @Test
    public void deveAlterarDadosDaTarefa() {

        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        tarefa.setTitulo(
                "Estudar JavaFX"
        );

        tarefa.setDescricao(
                "Estudar interface gráfica"
        );

        tarefa.setConcluida(true);

        assertEquals(
                "Estudar JavaFX",
                tarefa.getTitulo()
        );

        assertEquals(
                "Estudar interface gráfica",
                tarefa.getDescricao()
        );

        assertTrue(
                tarefa.isConcluida()
        );
    }

    @Test
    public void deveAlterarIdDaTarefa() {

        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        tarefa.setId(10);

        assertEquals(
                10,
                tarefa.getId()
        );
    }

    @Test
    public void deveRepresentarTarefaComoTexto() {

        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        String resultado =
                tarefa.toString();

        assertTrue(
                resultado.contains(
                        "Estudar Java"
                )
        );

        assertTrue(
                resultado.contains(
                        "Estudar JUnit"
                )
        );

        assertTrue(
                resultado.contains(
                        "Pendente"
                )
        );
    }

    @Test
    public void deveMostrarTarefaConcluidaNoTexto() {

        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        tarefa.setConcluida(true);

        String resultado =
                tarefa.toString();

        assertTrue(
                resultado.contains(
                        "Concluída"
                )
        );
    }

    @Test(expected = TarefaException.class)
    public void deveRejeitarTarefaSemTitulo()
            throws TarefaException {

        Tarefa tarefa = new Tarefa(
                1,
                "",
                "Descrição da tarefa"
        );

        tarefa.validar();
    }

    @Test
    public void deveAceitarTarefaComTitulo()
            throws TarefaException {

        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        tarefa.validar();
    }
}