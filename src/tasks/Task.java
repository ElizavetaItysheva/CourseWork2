package tasks;
import exceptions.IncorrectArgumentException;
import tasks.type.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String title;
    private String description;
    private final Type type;
    private final int id;
    private static int idGenerator = 0;
    private final LocalDateTime dateTime;

    public Task( String title, String description, Type type, LocalDateTime dateTime ) {
         this.title = checkTitleAndDescription(title);
        this.description = checkTitleAndDescription(description);

        if (type == null){
            throw new IncorrectArgumentException("Не введен тип задачи!");
        } else {
            this.type = type;
        }

        if(dateTime == null){
            throw new IncorrectArgumentException("Не введена дата создания или не верная дата!");
        }else {
            this.dateTime = dateTime;
        }
        this.id = idGenerator;
        idGenerator++;
    }
    private String checkTitleAndDescription(String text)throws IncorrectArgumentException{
        if(text == null || text.isEmpty() || text.isBlank()){
            throw new IncorrectArgumentException("Не введён текст!");
        } else {
            return text;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setTitle( String title ) {
        this.title = checkTitleAndDescription(title);
    }

    public void setDescription( String description ) {
        this.description = checkTitleAndDescription(description);
    }
    public abstract boolean appearsIn(LocalDate localDate);
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals( Object obj ) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Задача: " + title
                + "\n"
                + "Описание: " + description
                + "\n"
                + "Тип: " + type.getTypeOf()
                + "\n"
                + "Дата выполнения: " + dateTime.format(DateTimeFormatter.ISO_DATE);
    }
}
