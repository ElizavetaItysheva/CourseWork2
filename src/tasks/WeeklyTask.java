package tasks;
import tasks.type.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask( String title, String description, Type type, LocalDateTime dateTime ) {
        super(title, description, type, dateTime);
    }
    @Override
    public boolean appearsIn( LocalDate localDate ) {
        // если дата аргумента позже даты создания задачи И если день недели аргумента равен дню недели задачи
        // то есть должно повторяться каждый ТАКОЙ же день недели, который ПОСЛЕ создания задачи.
       return  localDate.isAfter(getDateTime().toLocalDate()) && localDate.getDayOfWeek() == getDateTime().getDayOfWeek();
    }
    @Override
    public String toString() {
        return super.toString() + "\n" + "Вид: еженедельная";
    }
}
