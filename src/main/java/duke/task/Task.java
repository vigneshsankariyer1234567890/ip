package duke.task;

import duke.exception.DukeException;
import duke.exception.TimedTaskDateInputException;

public abstract class Task {
    public static final EmptyTask EMPTY_TASK = EmptyTask.empty();
    private final String name;
    private final boolean isCompleted;
    private final TaskType taskType;

    Task(String name, TaskType taskType, boolean isCompleted) {
        this.taskType = taskType;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public static Task emptyTask() {
        return Task.EMPTY_TASK;
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompleted() {
        return this.isCompleted;
    }

    public abstract Task updateName(String input) throws DukeException;

    public abstract Task updateDateTime(String dateTime) throws DukeException;

    public abstract Task complete() throws TimedTaskDateInputException;

    public abstract String getDetails();

    public abstract String getLabel();

    public String getTaskTypeString() {
        return "[" + this.taskType.toString() + "]";
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public String getCheckBox() {
        return "[" + (getCompleted() ? "X" : " ") + "]";
    }

    @Override
    public String toString() {
        return getDetails();
    }

    public static final class EmptyTask extends Task {

        EmptyTask() {
            super("", TaskType.EMPTY, false);
        }

        public static EmptyTask empty() {
            return new EmptyTask();
        }

        @Override
        public EmptyTask updateName(String input) {
            return this;
        }

        @Override
        public Task updateDateTime(String dateTime) {
            return this;
        }

        @Override
        public EmptyTask complete() {
            return this;
        }

        @Override
        public String getDetails() {
            return "Empty";
        }

        @Override
        public String getLabel() {
            return "Empty";
        }
    }

}
