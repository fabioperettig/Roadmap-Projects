import fabioperettig.tt.model.Task;
import fabioperettig.tt.service.TaskManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        /// Cash the paycheck;
        /// Return an overdue book;
        /// Get Gary's autograph;
        /// Cast your ballot;
        /// Buy some milk;
        /// Get a Christmas Tree;

        TaskManager tManager = TaskManager.build();

        Task tsk1 = new Task("Cash the paycheck");
        Task tsk2 = new Task("Return an overdue book");
        Task tsk3 = new Task("Get Gary's autograph");
        Task tsk4 = new Task("Cast your ballot");
        Task tsk5 = new Task("Buy some milk");
        Task tsk6 = new Task("Get a Christmas Tree");

        tManager.saveTask(tsk1, tsk2, tsk3);
        tManager.saveTask(tsk4);
        tManager.saveTask(tsk5, tsk6);

//        tManager.deleteByID(2);


    }
}
