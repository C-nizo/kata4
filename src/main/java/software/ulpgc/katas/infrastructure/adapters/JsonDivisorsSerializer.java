package software.ulpgc.katas.infrastructure.adapters;

import com.google.gson.Gson;
import software.ulpgc.katas.architecture.domain.DivisorsResult;
import software.ulpgc.katas.architecture.ports.DivisorsSerializer;

public class JsonDivisorsSerializer implements DivisorsSerializer {
    private final Gson gson = new Gson();

    @Override
    public String serialize(DivisorsResult result) {
        return gson.toJson(result);
    }
}