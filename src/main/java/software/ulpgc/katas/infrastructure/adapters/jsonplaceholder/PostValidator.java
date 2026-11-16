package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

public class PostValidator {
    public boolean isValid(PostDto dto) {
        return dto != null
                && dto.userId() > 0
                && dto.id() > 0
                && hasText(dto.title())
                && hasText(dto.body());
    }

    private boolean hasText(String text) {
        return text != null
                && !text.isBlank();
    }
}
