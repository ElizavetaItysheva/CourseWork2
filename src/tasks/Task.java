package tasks;
import tasks.type.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    //заголовок
    private String title;
    // описание
    private String description;
    // тип задачи
    private final Type type;
    //айди
    private final int id;
    // генератор айди
    private final static int idGenerator = 0;
    private final LocalDateTime dateTime;

    public Task( String title, String description, Type type, LocalDateTime dateTime ) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.dateTime = dateTime;
        this.id = idGenerator + 1;
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
        this.title = title;
    }

    public void setDescription( String description ) {
        this.description = description;
    }
    public abstract boolean appearsIn(LocalDate localDate);
    public abstract LocalDateTime nextAppear();
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
