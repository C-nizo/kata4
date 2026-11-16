package software.ulpgc.katas.architecture.ports;

import software.ulpgc.katas.architecture.domain.model.Task;

public interface TaskLoader {
    Task loadTask(int taskId);
}
