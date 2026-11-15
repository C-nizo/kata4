package software.ulpgc.katas.architecture.ports;

import software.ulpgc.katas.architecture.domain.model.User;

public interface UserProvider {
    User fetchUser(int id);
}
