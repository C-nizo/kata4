package software.ulpgc.katas.infrastructure.adapters.console;

import software.ulpgc.katas.architecture.domain.model.Task;
import software.ulpgc.katas.architecture.ports.TaskLoader;

import java.util.Scanner;

public class TaskConsole {
    private final TaskLoader loader;

    public TaskConsole(TaskLoader loader) {
        this.loader = loader;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Introduce el ID de la tarea: ");
            int taskId = Integer.parseInt(scanner.nextLine());

            Task task = loader.loadTask(taskId);

            System.out.println("Tarea (ID): " + task.taskId());
            System.out.println("Usuario (ID): " + task.userId());
            System.out.println("Título: " + task.title());
            System.out.println("Estado: " + getStateString(task));

        } catch (NumberFormatException e) {
            System.out.println("El ID debe ser un número entero.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getStateString(Task task) {
        return task.completed() ? "Completada" : "Pendiente";
    }
}
