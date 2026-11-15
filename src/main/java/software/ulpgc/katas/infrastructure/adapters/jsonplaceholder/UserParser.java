package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

import com.google.gson.Gson;

import java.io.Reader;

public class UserParser {
    private final Gson gson = new Gson();

    public UserDto parse(Reader reader) {
        return gson.fromJson(reader, UserDto.class);
    }
}
