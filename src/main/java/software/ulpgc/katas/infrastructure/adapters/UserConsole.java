package software.ulpgc.katas.infrastructure.adapters;

import software.ulpgc.katas.architecture.domain.model.User;
import software.ulpgc.katas.architecture.ports.UserProvider;

import java.util.Scanner;

public class UserConsole {
    private final UserProvider provider;

    public UserConsole(UserProvider provider) {
        this.provider = provider;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Introduce el ID del usuario: ");
            int id = Integer.parseInt(scanner.nextLine());

            User user = provider.fetchUser(id);

            System.out.println("ID: " + user.id());
            System.out.println("Nombre: " + user.name());
            System.out.println("Email: " + user.email());
            System.out.println("Teléfono: " + user.phone());

        } catch (NumberFormatException e) {
            System.out.println("El ID debe ser un número entero.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
