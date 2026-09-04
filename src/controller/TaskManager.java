package controller;
import java.util.ArrayList;
import model.Tarefa;
import model.TarefaPrioritaria;
import exceptions.TarefaException;

public class TaskManager<T extends Tarefa> {
    private ArrayList<T> tarefas;

    public TaskManager() {
        tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(T tarefa) throws TarefaException {
        // verifica se ja existe uma tarefa com o id digitado pelo usuário e impede adição
        for (T t : tarefas) {
            if (t.getId() == tarefa.getId()) {
                throw new TarefaException("Erro: já existe uma tarefa com este id, escolha outro.");
            }
        }

        if (tarefa instanceof TarefaPrioritaria) {
            TarefaPrioritaria tp = (TarefaPrioritaria) tarefa;
            if (tp.getPrioridade() == null || tp.getPrioridade().trim().isEmpty()) {
                throw new TarefaException("Erro: Uma tarefa prioritária precisa do nível de prioridade.");
            }
        }

        tarefas.add(tarefa);
        System.out.println("Tarefa adicionada com sucesso.");
    }

    public void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");

            return;
        }

        for (T tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }

    public void concluirTarefa(int id) {
        boolean encontrada = false;

        for (T tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.setConcluida(true);
                encontrada = true;
                System.out.println("Tarefa concluída com sucesso.");
                break;
            }
        }

        if (!encontrada) {
            System.out.println("Nenhuma tarefa com este id foi encontrada.");
        }

    }

    public void removerTarefa(int id) {
        T tarefaRemover = null;

        for (T tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefaRemover = tarefa;
                break;
            } 
        }

        if (tarefaRemover != null) { 
            tarefas.remove(tarefaRemover);
            System.out.println("Tarefa removida com sucesso.");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }
}
