package controller;

import java.util.ArrayList;
import model.Tarefa;
import model.TarefaPrioritaria;
import repository.TarefaRepository;
import exceptions.TarefaException;
import java.sql.SQLException;

public class TaskManager<T extends Tarefa> {
    
    private TarefaRepository repository;

    public TaskManager() {
        repository = new TarefaRepository();
    }

    public void adicionarTarefa(T tarefa) throws TarefaException {
        try {
            repository.salvar(tarefa);
            System.out.println("Tarefa adicionada com sucesso");
        } catch (SQLException erro) {
            System.out.println("Erro ao salvar a tarefa no DB");
            erro.printStackTrace();
        }
    }

    public void listarTarefas() {
        try {
            ArrayList<Tarefa> tarefas = repository.listar();

            if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");

        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
                System.out.println("=======================");
            }
        }

        } catch (SQLException erro) {
            System.out.println("Erro ao listar tarefas");
            erro.printStackTrace();
        }
    }

    public void concluirTarefa(int id) {
        try {
           ArrayList<Tarefa> tarefas = repository.listar(); 
            for (Tarefa tarefa : tarefas) {
                if (tarefa.getId() == id) {
                    tarefa.setConcluida(true);
                    repository.atualizar(tarefa);
                    System.out.println("Tarefa concluída com sucesso.");
                    return;
            }
        }
        } catch (SQLException erro) {
            System.out.println("Erro ao concluir tarefa");
            erro.printStackTrace();
        }
    }

    public void editarTarefa (int id, String titulo, String descricao) {
        try {
            ArrayList<Tarefa> tarefas = repository.listar();

            for (Tarefa tarefa : tarefas) {
                if (tarefa.getId() == id) {
                    tarefa.setId(id);
                    tarefa.setTitulo(titulo);
                    tarefa.setDescricao(descricao);
                    repository.atualizar(tarefa);
                    System.out.println("Tarefa atualizada com sucesso");
                    break;
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar tarefa");
            erro.printStackTrace();
        }
    }


    public void removerTarefa(int id) {
        try {
            ArrayList<Tarefa> tarefas = repository.listar();

            Tarefa tarefaRemover = null;

            for (Tarefa tarefa : tarefas) {
                if (tarefa.getId() == id) {
                    tarefaRemover = tarefa;
                    break;
                } 
            }

            if (tarefaRemover != null) { 
                repository.excluir(tarefaRemover.getId());
                System.out.println("Tarefa removida com sucesso.");
                System.out.println("=======================");
            } else {
                System.out.println("Tarefa não encontrada.");
            }

        } catch (Exception erro) {
            System.out.println("Erro ao remover a tarefa.");
            erro.printStackTrace();
        }
    }
}
