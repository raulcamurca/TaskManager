package app;
import controller.TaskManager;
import exceptions.TarefaException;
import model.Tarefa;
import model.TarefaPrioritaria;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        TaskManager<Tarefa> manager = new TaskManager<>();
        Scanner leitor = new Scanner(System.in);

        int opcao;

        while(true) {
            System.out.println("===== GERENCIADOR DE TAREFAS =====");
            System.out.println("1. Criar nova tarefa");
            System.out.println("2. Listar tarefas");
            System.out.println("3. Marcar tarefa como concluída");
            System.out.println("4. Remover tarefa");
            System.out.println("5. Sair");
            System.out.println("Escolha uma opção: ");

            opcao = leitor.nextInt();

            switch(opcao) {
                case 1:
                    System.out.println("Digite o id da tarefa: ");
                    int id = leitor.nextInt();

                    leitor.nextLine();

                    System.out.println("Digite o título da tarefa: ");
                    String titulo = leitor.nextLine();

                    System.out.println("Digite a descrição da tarefa: ");
                    String descricao = leitor.nextLine();

                    System.out.println("É uma tarefa prioritária? (S/N): ");
                    String prioritaria = leitor.nextLine();
                    
                    Tarefa novaTarefa;

                    if (prioritaria.equalsIgnoreCase("S")) {
                        System.out.println("Digite a prioridade (Alta, Média ou Baixa): ");
                        String prioridade = leitor.nextLine();
                        novaTarefa = new TarefaPrioritaria(id, titulo, descricao, prioridade);
                    } else {
                        novaTarefa = new Tarefa(id, titulo, descricao);
                    }

                    try {
                        manager.adicionarTarefa(novaTarefa);
                        System.out.println("Operação finalizada com sucesso.");
                    } catch (TarefaException e) {
                        System.out.println(e.getMessage());
                        
                    }
                    break;
                case 2:
                    manager.listarTarefas();
                    break;
                case 3:
                    System.out.println("Digite o id da tarefa que deseja concluir: ");
                    int idConclusao = leitor.nextInt();
                    manager.concluirTarefa(idConclusao);
                    break;
                case 4:
                    System.out.println("Digite o id da tarefa que deseja remover: ");
                    int idRemover = leitor.nextInt();
                    manager.removerTarefa(idRemover);
                    break;
                case 5:
                    System.out.println("Saindo do sistema.");
                    System.exit(0);
                    leitor.close();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
}
