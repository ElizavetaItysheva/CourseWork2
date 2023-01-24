package tasks;
import tasks.type.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask( String title, String description, Type type, LocalDateTime dateTime ) {
        super(title, description, type, dateTime);
    }
    @Override
    public boolean appearsIn( LocalDate localDate ) {
        // если дата аргумента после создания задачи И если день года аргумента равен дню года от даты создания
        // то есть должно повторяться каждый одинаковый день в году(раз в год)
        return localDate.isAfter(getDateTime().toLocalDate()) && localDate.getDayOfYear() == getDateTime().getDayOfYear();
    }
    @Override
    public String toString() {
        return super.toString() + "\n" + "Вид: ежегодная";
    }
}
