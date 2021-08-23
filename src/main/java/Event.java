import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final LocalDateTime datetime;

    public Event(String name, String at) throws DukeException {
        super(name, TaskType.D);
        try {
            this.datetime = LocalDateTime.parse(at.trim(), DateTimeFormatCreator.format());
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect form of date input. Please try yyyy-mm-dd HH:mm instead.");
        }
    }

    private String happeningWhen() {
        return " (at: "
                + this.datetime.toLocalDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.datetime.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")";
    }

    @Override
    public String details() {
        return super.details() + happeningWhen();
    }
}
