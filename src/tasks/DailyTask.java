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
    public boolean appearsIn(LocalDate localDate) {
        // если аргумент позже даты задачи И если день года аргумента больше дня создания задачи
        return  (localDate.isAfter(getDateTime().toLocalDate()) && localDate.getDayOfYear() > getDateTime().getDayOfYear()) ||
                // или если год аргумента больше года задачи И если день года меньше или равен дню года задачи
                (localDate.getYear() > getDateTime().getYear() && localDate.getDayOfYear()<=getDateTime().getDayOfYear());
    }
    @Override
    public String toString() {
        return super.toString() + "\n" + "Вид: ежедневная";
    }
}
