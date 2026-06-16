package fabioperettig.tt.service;
import fabioperettig.tt.model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TaskManager {

    private TaskManager() {
    }

    public static TaskManager build(){
        return new TaskManager();
    }

    private static final Path FILE = Path.of("myTasks.json");

    public void saveTask (Task task) throws IOException {
        Files.writeString(FILE, task.toJson() +
                        System.lineSeparator(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
    }

}
