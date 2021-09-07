package duke.tasklist;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * A class which stores the list of tasks used by Duke
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * A constructor method which initialises the TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return the number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the Task at the nth position
     * @param taskId the id of the task
     * @return The task in that position
     * @throws DukeException if an invalid index is input
     */
    public Task get(int taskId) throws DukeException {
        try {
            return this.tasks.get(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Wrong index input.");
        }

    }

    /**
     * Adds a task to the list
     * @param task task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task with the taskId from the list and returns it.
     * @param taskId The task to be removed.
     * @return The task which was removed.
     * @throws DukeException if the taskId is invalid.
     */
    public Task deleteTask(int taskId) throws DukeException {
        try {
            return this.tasks.remove(taskId - 1);
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new DukeException("That task does not exist.");
        }
    }

    public Task updateTask(Task task, int taskId) throws DukeException {
        Task taskRetrieved;
        try {
            taskRetrieved = tasks.get(taskId - 1);
            tasks.set(taskId - 1, task);
            return taskRetrieved;
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new DukeException("That task does not exist.");
        }
    }

    /**
     * Marks and returns a completed task.
     * @param taskId of the task that is to be completed.
     * @return The task which was completed.
     * @throws DukeException if the taskId is invalid.
     */
    public Task markAsCompleted(int taskId) throws DukeException {
        try {
            Task currentTask = this.tasks.get(taskId - 1);
            Task completedTask = currentTask.complete();
            this.tasks.set(taskId - 1, completedTask);
            return completedTask;
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new DukeException("That task does not exist.");
        }
    }

    /**
     * Returns a filtered TaskList based on a String input.
     * @param input the parameter to filter the list on.
     * @return A TaskList which contains only the filtered tasks.
     */
    public TaskList filter(String input) {
        TaskList taskList = new TaskList();
        for (int i = 0; i < this.size(); i++) {
            Task task = this.tasks.get(i);
            String nameOfTask = task.getName();
            if (nameOfTask.contains(input)) {
                taskList.addTask(task);
            }
        }
        return taskList;
    }

    /**
     * Returns the string representation of the TaskList.
     * @return a string which contains the numbered tasks.
     */
    @Override()
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int j = 1; j <= this.size(); j++) {
            String line = "";
            try {
                line = j + "." + this.get(j).toString();
            } catch (DukeException e) {
                e.printStackTrace();
            }
            result.append(line);
            if (j < this.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }

}
