import fabioperettig.tt.model.Task;
import fabioperettig.tt.service.TaskManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        /// Cash the paycheck;
        /// Return a overdue book;
        /// Get Gary's autograph;
        /// Cast your ballot;
        /// Buy some milk;
        /// Get a Christmas Tree;

        TaskManager tManager = TaskManager.build();

        Task tsk1 = new Task("Get a Christmas Tree");
        Task tsk2 = new Task("Buy some milk");
        Task tsk3 = new Task("Get Gary's autograph");

        tManager.saveTask(tsk1);
        tManager.saveTask(tsk2);
        tManager.saveTask(tsk3);



    }
}
