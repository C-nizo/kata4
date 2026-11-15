package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

public class UserValidator {
    public boolean isValid(UserDto dto) {
        return dto != null
                && dto.id() > 0
                && hasText(dto.name())
                && hasText(dto.email())
                && hasText(dto.phone());
    }

    private boolean hasText(String text) {
        return text != null
                && !text.isBlank();
    }
}
