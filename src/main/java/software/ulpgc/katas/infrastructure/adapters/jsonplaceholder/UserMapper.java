package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

import software.ulpgc.katas.architecture.domain.model.User;

public class UserMapper {
    public User map(UserDto dto) {
        return new User(
                dto.id(),
                dto.name(),
                dto.email(),
                dto.phone()
        );
    }
}
