package tasks;
import tasks.type.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {
    public DailyTask( String title, String description, Type type, LocalDateTime dateTime ) {
        super(title, description, type, dateTime);
    }



    @Override
    public boolean appearsIn(LocalDate localDate) {
        // если аргумент позже даты задачи И всё -_-
        // разве что во вчера она не появится, потому что вчера это перед, а не после :D
        return  localDate.isAfter(getDateTime().toLocalDate());
    }
    @Override
    public String toString() {
        return super.toString() + "\n" + "Вид: ежедневная";
    }
}
