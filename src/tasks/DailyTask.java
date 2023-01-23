package tasks;
import exceptions.IncorrectArgumentException;
import tasks.type.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {
    public DailyTask( String title, String description, Type type, LocalDateTime dateTime ) {
        super(title, description, type, dateTime);
    }

    @Override
    public LocalDateTime nextAppear() {
        return this.getDateTime().plusDays(1);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        if (localDate.isAfter(getDateTime().toLocalDate())) {
            return true;
        }else {
            throw new IncorrectArgumentException("Некорректный аргумент.");
        }
    }
    @Override
    public String toString() {
        return super.toString() + "\n" + "Вид: ежедневная";
    }
}
