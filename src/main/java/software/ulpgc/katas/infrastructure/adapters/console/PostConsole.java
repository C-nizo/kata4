package software.ulpgc.katas.infrastructure.adapters.console;

import software.ulpgc.katas.architecture.domain.model.Post;
import software.ulpgc.katas.architecture.ports.PostLoader;

import java.util.Scanner;

public class PostConsole {
    private final PostLoader loader;

    public PostConsole(PostLoader loader) {
        this.loader = loader;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Introduce el ID de la publicación: ");
            int id = Integer.parseInt(scanner.nextLine());

            Post post = loader.loadPost(id);

            System.out.println("Autor (ID): " + post.userId());
            System.out.println("Publicación: " + post.postId());
            System.out.println("Título: " + post.title());
            System.out.println("Contenido: " + post.body());
        } catch (NumberFormatException e) {
            System.out.println("El ID debe ser un número entero.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
