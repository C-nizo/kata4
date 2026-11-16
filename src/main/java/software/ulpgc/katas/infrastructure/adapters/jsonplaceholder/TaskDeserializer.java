package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

import com.google.gson.Gson;

import java.io.Reader;

public class TaskDeserializer {
    private final Gson gson = new Gson();

    public TaskDto deserialize(Reader reader) {
        return gson.fromJson(reader, TaskDto.class);
    }
}
