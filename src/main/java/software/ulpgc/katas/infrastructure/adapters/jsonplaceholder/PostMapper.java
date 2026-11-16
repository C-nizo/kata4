package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

import software.ulpgc.katas.architecture.domain.model.Post;

public class PostMapper {
    public Post map(PostDto dto) {
        return new Post(
                dto.userId(),
                dto.id(),
                dto.title(),
                dto.body()
        );
    }
}
