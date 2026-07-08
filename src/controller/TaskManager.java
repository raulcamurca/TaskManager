package controller;
import java.util.ArrayList;
import model.Tarefa;

public class TaskManager {
    private ArrayList<Tarefa> tarefas;

    public TaskManager() {
        tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);

        System.out.println("Tarefa adicionada com sucesso.");
    }

    public void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");

            return;
        }

        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa); // colocar um \n talvez
        }
    }

    // public void removerTarefa(Tarefa tarefa) {
    //     if (!tarefas.isEmpty()) {
    //         System.out.println("Nenhuma tarefa para remover.");

    //         return;
    //     }
    //     tarefas.remove(tarefa);

    //     System.out.println("Tarefa removida com sucesso.");
    // }
}
