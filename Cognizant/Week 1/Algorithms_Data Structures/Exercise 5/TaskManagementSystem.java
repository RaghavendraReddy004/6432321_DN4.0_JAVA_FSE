class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }

    public String toString() {
        return "Task ID: " + taskId + " | Name: " + taskName + " | Status: " + status;
    }
}

public class TaskManagementSystem {
    private Task head;

    public void addTask(int id, String name, String status) {
        Task newTask = new Task(id, name, status);
        if (head == null) {
            head = newTask;
        } else {
            Task current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTask;
        }
        System.out.println("Added: " + newTask);
    }

    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        System.out.println("All Tasks:");
        Task current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    public void searchTask(int id) {
        Task current = head;
        while (current != null) {
            if (current.taskId == id) {
                System.out.println("Found: " + current);
                return;
            }
            current = current.next;
        }
        System.out.println("Task with ID " + id + " not found.");
    }

    public void deleteTask(int id) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        if (head.taskId == id) {
            System.out.println("Deleted: " + head);
            head = head.next;
            return;
        }

        Task current = head;
        while (current.next != null) {
            if (current.next.taskId == id) {
                System.out.println("Deleted: " + current.next);
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }

        System.out.println("Task with ID " + id + " not found.");
    }

    public static void main(String[] args) {
        TaskManagementSystem taskManager = new TaskManagementSystem();

        System.out.println("Adding Tasks...");
        taskManager.addTask(1, "Design UI", "Pending");
        taskManager.addTask(2, "Implement Login", "In Progress");
        taskManager.addTask(3, "Database Setup", "Completed");
        taskManager.addTask(4, "Testing", "Pending");

        System.out.println("\nTraversing Tasks:");
        taskManager.displayTasks();

        System.out.println("\nSearching Task with ID 3:");
        taskManager.searchTask(3);

        System.out.println("\nDeleting Task with ID 2:");
        taskManager.deleteTask(2);

        System.out.println("\nTraversing Tasks after Deletion:");
        taskManager.displayTasks();
    }
}