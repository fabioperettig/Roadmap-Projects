![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Static Badge](https://img.shields.io/badge/Roadmap.sh-%238401DD?style=for-the-badge&logo=roadmapdotsh)


# Task Tracker CLI

This project is my first result of the roadmap.sh [**Task Tracker**](https://roadmap.sh/projects/task-tracker) project.

The main goal of this project was to understand how data persistence and JSON serialization work behind the scenes by implementing them manually, **without relying on external libraries such as Jackson or Gson**.

## Requirements 📖

"The application should run from the command line, accept user actions and inputs as arguments, and store the tasks in a JSON file."

## Project Structure 🏛️
The project was designed with a clear separation of responsibilities, making the code easier to understand and maintain.
<details>
  <summary>Estrutura completa do projeto</summary>

    task-tracker-CLI
    ├── model
    │   ├── Status [E]
    │   └── Task
    ├── service
    │   └── TaskManager
    ├── Main
    └── myTasks.json
    
</details>

## Features 🚀

The project contains the default procedures for this kind of project, besides some extra features which increase the flexibility and safety of the project.

* Create tasks;
* Save and Load tasks to/from a JSON file;
* Find tasks by ID;
* Delete tasks by ID;
* Single and multiple tasks creation;
* Instance Factory method for Task Manager.

## Project in a nutshell 

The **Task Class** has highlights features like a default attributes Constructor, manual Json manipulation with update check, besides de classic but usefull toString().

<details>
  <summary>CTOR</summary>

```java
public Task(String title) {
    this.id = ++idCounter;
    this.title = title;
    this.status = Status.TODO;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = null;
}
```
</details>

```java
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
```

<details>
  <summary>toString()</summary>
  
```java
@Override
public String toString() {
    return "id: " + id +
    ", title: '" + title.strip() +
    ", status: " + status.toString() +
    ", createdAt: " + createdAt +
    ", updatedAt: " + updatedAt;
}
```
</details>

<br>

The **Task Manager Class** was implemented to be the most robust part of the project, with features and Patterns which allow a wide usabilty without compromising protection.

```java
/// Intance in Factory Method pattern at the Main Class
TaskManager tManager = TaskManager.build();

- - -

private TaskManager() {}

public static TaskManager build() {
    return new TaskManager();
}
```
```java
/// Save method allowing single or multiple tasks as param
public void saveTask (Task... newTasks) throws IOException {
    List<Task> tasks = loadTasks();
    Collections.addAll(tasks, newTasks);
    Files.writeString(FILE, tasksToJson(tasks));
}
```
```java
/// Appending tasks to Json throught StringBuilder method
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
````

