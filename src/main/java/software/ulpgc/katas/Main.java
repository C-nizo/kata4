package software.ulpgc.katas;

import software.ulpgc.katas.architecture.ports.PostLoader;
import software.ulpgc.katas.infrastructure.adapters.console.PostConsole;
import software.ulpgc.katas.infrastructure.adapters.jsonplaceholder.JsonPlaceHolderPostLoader;

public class Main {
    static void main() {
        String baseUrl = "https://jsonplaceholder.typicode.com";

        PostLoader loader = new JsonPlaceHolderPostLoader(baseUrl);
        PostConsole console = new PostConsole(loader);

        console.run();
    }
}
