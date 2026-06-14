package fabioperettig.tt.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Task {
    private static int idCounter = 0;
    private int id;
    private String title;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static DateTimeFormatter formater =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Task(String title) {
        this.id = ++idCounter;
        this.title = title;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void updateTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public void taskStarted() {
        this.status = Status.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }

    public void taskDone() {
        this.status = Status.DONE;
        this.updatedAt = LocalDateTime.now();
    }

    public String toJson() {
        String result;
        if (updatedAt == null){
            result = "{\"id\":\"" + id + "\", \"task\":\""
                    + title.strip() + "\", \"status\":\""
                    + status.toString()
                    + "\", \"createdAt\":\""
                    + createdAt.format(formater) + "\"}";
        } else {
            result = "{\"id\":\"" + id + "\", \"task\":\""
                    + title.strip() + "\", \"status\":\""
                    + status.toString()
                    + "\", \"createdAt\":\""
                    + createdAt.format(formater)
                    + "\", \"updatedAt\":\""
                    + updatedAt.format(formater) + "\"}";
        }
        return result;
    }

    public static Task fromJson(String json) {
        Map<String, String> fields = parseJsonFields(json);

        int id = Integer.parseInt(fields.get("id"));
        String title = fields.get("task");
        Status status = Status.valueOf(fields.get("status"));
        LocalDateTime createdAt = LocalDateTime.parse(fields.get("createdAt"), formater);

        Task task = new Task(title);
        task.id = id;
        task.status = status;
        task.createdAt = createdAt;

        String updatedAt = fields.get("updatedAt");

        if (updatedAt != null && !updatedAt.isBlank()) {
            task.updatedAt = LocalDateTime.parse(updatedAt, formater);
        }

        if (id > idCounter) {
            idCounter = id;
        }

        return task;
    }

    private static Map<String, String> parseJsonFields(String json) {
        Map<String, String> fields = new HashMap<>();

        json = json.strip();
        json = json.substring(1, json.length() -1);

        String[] pairs = json.split(", (?=\\\")");

        for (String pair:pairs) {
            String[] keyValue = pair.split(":", 2);

            String key = removeQuotes(keyValue[0].strip());
            String value = removeQuotes(keyValue[1].strip());

            fields.put(key, value);
        }

        return fields;
    }

    private static String removeQuotes(String text) {
        if (text.startsWith("\"") && text.endsWith("\"")) {
            return text.substring(1, text.length() -1);
        }
        return text;
    }

    @Override
    public String toString() {
        return "id: " + id +
        ", title: '" + title.strip() +
        ", status: " + status.toString() +
        ", createdAt: " + createdAt +
        ", updatedAt: " + updatedAt;
    }
}
