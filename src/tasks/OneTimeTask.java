package tasks;
import exceptions.IncorrectArgumentException;
import tasks.type.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    public OneTimeTask( String title, String description, Type type, LocalDateTime dateTime ) {
        super(title, description, type, dateTime);
    }

    @Override
    public LocalDateTime nextAppear() {
        throw new NullPointerException("Не повторяется!");
    }

    @Override
    public boolean appearsIn( LocalDate localDate ) {
        if(localDate.isEqual(getDateTime().toLocalDate())) {
            return true;
        } else {
            throw new IncorrectArgumentException("Неправильный аргумент.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Вид: одноразовая";
    }
}
