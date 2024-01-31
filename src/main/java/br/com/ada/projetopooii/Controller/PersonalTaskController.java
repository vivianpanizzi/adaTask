package br.com.ada.projetopooii.Controller;

import br.com.ada.projetopooii.Domain.PersonalTask;
import br.com.ada.projetopooii.Repository.TaskRepository;

import java.util.List;
import java.util.Scanner;

public class PersonalTaskController {

    private TaskRepository<PersonalTask> taskRepository;
    Scanner scanner = new Scanner(System.in);

    public PersonalTaskController(TaskRepository<PersonalTask> taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void menuPersonalTask () {

        boolean sair = false;

        while (!sair) {
            System.out.println("\n"+"---------- Personal Task ---------- \n" +
                    "Escolha a ação: \n" +
                    "1- Adicionar Tarefa \n" +
                    "2- Alterar Tarefa \n" +
                    "3- Visualizar Tarefas \n" +
                    "4- Deletar Tarefa \n" +
                    "5- Sair \n" +
                    "Digite a ação escolhida: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    addTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    viewTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public void addTask() {
        scanner.nextLine();
        System.out.println("\n"+"------ Criando Tarefa Pessoal ------ \n" +
                            "Digite o nome da tarefa");
        String nome = scanner.nextLine();

        System.out.println("Digite a descrição da tarefa");
        String descricao = scanner.nextLine();

        System.out.println("Em quantos dias a tarefa deverá ser cumprida?");
        Integer prazoEmDias = scanner.nextInt();

        int nextId = taskRepository.getAllTasks().size() + 1;
        PersonalTask task = new PersonalTask(nextId, nome, descricao, prazoEmDias);
        taskRepository.getAllTasks().add(task);

    }

    public void editTask() {

    }

    public void viewTask() {
        List<PersonalTask> tasks = taskRepository.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa disponível.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                PersonalTask task = tasks.get(i);
                System.out.println((i + 1) + ". " + task.toString());
            }
        }

    }

    public void deleteTask(){
        scanner.nextLine();
        System.out.println("------ Deletando Tarefa Pessoal ------ \n" +
                "Digite o id da tarefa para excluir:");
        int id = scanner.nextInt();
        taskRepository.deleteTask(id);

    }

}
