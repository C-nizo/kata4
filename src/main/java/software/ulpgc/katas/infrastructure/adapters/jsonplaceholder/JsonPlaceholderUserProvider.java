
package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

import com.google.gson.JsonParseException;
import software.ulpgc.katas.architecture.domain.model.User;
import software.ulpgc.katas.architecture.ports.UserProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JsonPlaceholderUserProvider implements UserProvider {
    private final String baseUrl;
    private final UserParser parser = new UserParser();
    private final UserValidator validator = new UserValidator();
    private final UserMapper mapper = new UserMapper();

    public JsonPlaceholderUserProvider(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public User fetchUser(int id) {
        return transformUser(requestUser(id));
    }

    private UserDto requestUser(int id) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            URI uri = createUri(id);
            HttpRequest request = createRequest(uri);
            HttpResponse<InputStream> response = sendRequest(client, request);
            return parseRequest(response);
        }
    }

    private URI createUri(int id) {
        String separator = baseUrl.endsWith("/") ? "" : "/";
        return URI.create(baseUrl + separator + "users/" + id);
    }

    private static HttpRequest createRequest(URI uri) {
        return HttpRequest.newBuilder(uri).GET().build();
    }

    private static HttpResponse<InputStream> sendRequest(
            HttpClient client, HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Error de comunicación", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Petición interrumpida", e);
        }
    }

    private UserDto parseRequest(HttpResponse<InputStream> response) {
        try (Reader reader = new InputStreamReader(
                response.body(), StandardCharsets.UTF_8)) {

            checkStatus(response.statusCode());
            return parser.parse(reader);

        } catch (JsonParseException e) {
            throw new IllegalStateException("Respuesta JSON inválida", e);
        } catch (IOException e) {
            throw new IllegalStateException("Error de lectura", e);
        }
    }

    private static void checkStatus(int statusCode) {
        if (statusCode == 404) {
            throw new IllegalStateException("Usuario no encontrado (HTTP 404)");
        }
        if (statusCode != 200) {
            throw new IllegalStateException("Error HTTP: " + statusCode);
        }
    }

    private User transformUser(UserDto dto) {
        if (!validator.isValid(dto)) {
            throw new IllegalStateException("Datos de usuario inválidos");
        }
        return mapper.map(dto);
    }
}
