package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

import com.google.gson.JsonParseException;
import software.ulpgc.katas.architecture.domain.model.Post;
import software.ulpgc.katas.architecture.ports.PostLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JsonPlaceHolderPostLoader implements PostLoader {
    private final String baseUrl;
    private final PostDeserializer deserializer = new PostDeserializer();
    private final PostValidator validator = new PostValidator();
    private final PostMapper mapper = new PostMapper();

    public JsonPlaceHolderPostLoader(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Post loadPost(int postId) {
        return transformPost(fetchPost(postId));
    }

    private PostDto fetchPost(int postId) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            URI uri = createUri(postId);
            HttpRequest request = createRequest(uri);
            HttpResponse<InputStream> response = sendRequest(client, request);
            return deserializeResponse(response);
        }
    }

    private URI createUri(int postId) {
        String separator = baseUrl.endsWith("/") ? "" : "/";
        return URI.create(baseUrl + separator + "posts/" + postId);
    }

    private HttpRequest createRequest(URI uri) {
        return HttpRequest.newBuilder(uri).GET().build();
    }

    private HttpResponse<InputStream> sendRequest(HttpClient client, HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Error de comunicación", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Petición interrumpida", e);
        }
    }

    private PostDto deserializeResponse(HttpResponse<InputStream> response) {
        try (Reader reader = new InputStreamReader(
                response.body(), StandardCharsets.UTF_8)) {
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
            throw new IllegalStateException("Publicación no encontrada (HTTP 404)");
        }

        if (statusCode != 200) {
            throw new IllegalStateException("Error HTTP: " + statusCode);
        }
    }

    private Post transformPost(PostDto dto) {
        if (!validator.isValid(dto)) {
            throw new IllegalStateException("Datos de publicación inválidos");
        }

            return mapper.map(dto);
    }
}
