package fabioperettig.tt.service;
import fabioperettig.tt.model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskManager {

    private TaskManager() {
    }

    public static TaskManager build() {
        return new TaskManager();
    }

    private static final Path FILE = Path.of("myTasks.json");

    public void saveTask (Task... newTasks) throws IOException {
        List<Task> tasks = loadTasks();
        Collections.addAll(tasks, newTasks);
        Files.writeString(FILE, tasksToJson(tasks));
    }

    public String tasksToJson(List<Task> tasks) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for(int i = 0; i < tasks.size(); i++) {
            json.append("  ");
            json.append(tasks.get(i).toJson());

            if (i < tasks.size() -1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]");

        return json.toString();
    }

    private List<Task> loadTasks() throws IOException {
        if (!Files.exists(FILE) || Files.readString(FILE).isBlank()) {
            return new ArrayList<>();
        }

        String json = Files.readString(FILE).strip();
        json = json.substring(1, json.length() -1).strip();

        List<Task> tasks = new ArrayList<>();
        if (json.isBlank()) {
            return tasks;
        }

        String[] taskJsons = json.split("(?<=\\}),\\s*(?=\\{)");

        for (String taskJson : taskJsons) {
            tasks.add(Task.fromJson(taskJson));
        }
        return tasks;
    }

    public void findByID(int id) throws IOException {
        List<Task> tasks = loadTasks();

        System.out.println(tasks.stream().filter(task -> task.getId() == id).findFirst());
    }

    public boolean deleteByID(int id) throws IOException {
        List<Task> tasks = loadTasks();

        boolean removed = tasks.removeIf(task -> task.getId() == id);

        if (removed) {
            Files.writeString(FILE, tasksToJson(tasks));
        }

        return removed;
    }


}
