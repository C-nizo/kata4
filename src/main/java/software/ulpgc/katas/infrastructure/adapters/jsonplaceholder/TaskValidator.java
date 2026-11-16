package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

public class TaskValidator {
    public boolean isValid(TaskDto dto) {
        return dto != null
                && dto.userId() > 0
                && dto.id() > 0
                && hasText(dto.title())
                && dto.completed() != null;
    }

    private boolean hasText(String text) {
        return text != null
                && !text.isBlank();
    }
}
