package software.ulpgc.katas;

import software.ulpgc.katas.architecture.ports.TaskLoader;
import software.ulpgc.katas.infrastructure.adapters.console.TaskConsole;
import software.ulpgc.katas.infrastructure.adapters.jsonplaceholder.JsonPlaceHolderTaskLoader;

public class Main {
    static void main() {
        String baseUrl = "https://jsonplaceholder.typicode.com";

        TaskLoader loader = new JsonPlaceHolderTaskLoader(baseUrl);
        TaskConsole console = new TaskConsole(loader);

        console.run();
    }
}
