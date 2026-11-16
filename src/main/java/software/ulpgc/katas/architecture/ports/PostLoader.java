package software.ulpgc.katas.architecture.ports;

import software.ulpgc.katas.architecture.domain.model.Post;

public interface PostLoader {
    Post loadPost(int postId);
}
