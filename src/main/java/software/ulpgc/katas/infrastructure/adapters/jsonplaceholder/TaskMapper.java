package software.ulpgc.katas.infrastructure.adapters.jsonplaceholder;

import software.ulpgc.katas.architecture.domain.model.Task;

public class TaskMapper {
    public Task map(TaskDto dto) {
        return new Task(
                dto.userId(),
                dto.id(),
                dto.title(),
                dto.completed()
        );
    }
}
