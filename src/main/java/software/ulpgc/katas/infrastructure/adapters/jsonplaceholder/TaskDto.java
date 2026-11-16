package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

public record TaskDto(int userId, int id, String title, Boolean completed) {
}
