package tasks;
import tasks.type.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    public MonthlyTask( String title, String description, Type type, LocalDateTime dateTime ) {
        super(title, description, type, dateTime);
    }
    @Override
    public boolean appearsIn( LocalDate localDate ) {
        // если дата аргумента после даты создания задачи И если день месяца аргумента равен дню месяца от даты создания
        // то есть должно повторяться каждый одинаковый день месяца(не учитываются всякие феврали)
        return  localDate.isAfter(getDateTime().toLocalDate()) && localDate.getDayOfMonth() == getDateTime().getDayOfMonth();
    }
    @Override
    public String toString() {
        return super.toString() + "\n" + "Вид: ежемесячная";
    }
}
