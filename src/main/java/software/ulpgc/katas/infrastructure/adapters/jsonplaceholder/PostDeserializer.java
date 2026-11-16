package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

import com.google.gson.Gson;

import java.io.Reader;

public class PostDeserializer {
    private final Gson gson = new Gson();

    public PostDto deserialize(Reader reader) {
        return gson.fromJson(reader, PostDto.class);
    }
}
