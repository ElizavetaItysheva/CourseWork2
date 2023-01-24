package tasks;
import tasks.type.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    public OneTimeTask( String title, String description, Type type, LocalDateTime dateTime ) {
        super(title, description, type, dateTime);
    }
    @Override
    public boolean appearsIn( LocalDate localDate ) {
        // если дата аргумента равна дате создания задачи
        return localDate.isEqual(getDateTime().toLocalDate());

    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Вид: одноразовая";
    }
}
