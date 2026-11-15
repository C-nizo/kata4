package software.ulpgc.katas;

import software.ulpgc.katas.architecture.ports.UserProvider;
import software.ulpgc.katas.infrastructure.adapters.jsonplaceholder.JsonPlaceholderUserProvider;
import software.ulpgc.katas.infrastructure.adapters.UserConsole;

public class Main {
    static void main() {
        String baseUrl = "https://jsonplaceholder.typicode.com";

        UserProvider provider = new JsonPlaceholderUserProvider(baseUrl);
        UserConsole console = new UserConsole(provider);

        console.run();
    }
}
