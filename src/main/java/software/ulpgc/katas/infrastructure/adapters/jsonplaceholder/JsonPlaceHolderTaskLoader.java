package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

import com.google.gson.JsonParseException;
import software.ulpgc.katas.architecture.domain.model.Task;
import software.ulpgc.katas.architecture.ports.TaskLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JsonPlaceHolderTaskLoader implements TaskLoader {
    private final String baseUrl;
    private final TaskDeserializer deserializer = new TaskDeserializer();
    private final TaskValidator validator = new TaskValidator();
    private final TaskMapper mapper = new TaskMapper();

    public JsonPlaceHolderTaskLoader(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Task loadTask(int taskId) {
        return transformTask(fetchTask(taskId));
    }

    private TaskDto fetchTask(int taskId) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            URI uri = createUri(taskId);
            HttpRequest request = createRequest(uri);
            HttpResponse<InputStream> response = sendRequest(client, request);
            return deserializeResponse(response);
        }
    }

    private URI createUri(int taskId) {
        String separator = baseUrl.endsWith("/") ? "" : "/";
        return URI.create(baseUrl + separator + "todos/" + taskId);
    }

    private HttpRequest createRequest(URI uri) {
        return HttpRequest.newBuilder(uri).GET().build();
    }

    private static HttpResponse<InputStream> sendRequest(HttpClient client, HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Error de comunicación", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Petición interrumpida", e);
        }
    }

    private TaskDto deserializeResponse(HttpResponse<InputStream> response) {
        try (Reader reader = new InputStreamReader(response.body(), StandardCharsets.UTF_8)) {
            checkStatus(response.statusCode());
            return deserializer.deserialize(reader);
        } catch (JsonParseException e) {
            throw new IllegalStateException("Respuesta JSON inválida", e);
        } catch (IOException e) {
            throw new IllegalStateException("Error de lectura", e);
        }
    }

    private void checkStatus(int statusCode) {
        if (statusCode == 404) {
            throw new IllegalStateException("Tarea no encontrada (HTTP 404)");
        }

        if (statusCode != 200) {
            throw new IllegalStateException("Error HTTP: " + statusCode);
        }
    }

    private Task transformTask(TaskDto taskDto) {
        if (!validator.isValid(taskDto)) {
            throw new IllegalStateException("Datos de tarea inválidos");
        }

        return mapper.map(taskDto);
    }
}
