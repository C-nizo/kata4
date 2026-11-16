package software.ulpgc.katas.architecture.domain.model;

public record Task(int userId, int taskId, String title, boolean completed) {
}
