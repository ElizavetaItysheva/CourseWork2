package tasks;
import exceptions.IncorrectArgumentException;
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
    private static int idGenerator = 0;
    private final LocalDateTime dateTime;

    public Task( String title, String description, Type type, LocalDateTime dateTime ) {
        // проверка заголовка на нул, на пустоту и на пробел
        if(title == null || title.isEmpty() || title.isBlank()){
            throw new IncorrectArgumentException("Не введён заголовок!");
        } else {
            this.title = title;
        }
        // аналогичная проверка описания
        if(description == null || description.isEmpty() || description.isBlank()){
            throw new IncorrectArgumentException("Не введено описание!");
        } else {
            this.description = description;
        }
        // проверка типа на нул
        if (type == null){
            throw new IncorrectArgumentException("Не введен тип задачи!");
        } else {
            this.type = type;
        }
        // проверка даты на нул
        if(dateTime == null){
            throw new IncorrectArgumentException("Не введена дата создания или не верная дата!");
        }else {
            this.dateTime = dateTime;
        }
        this.id = idGenerator;
        idGenerator++;
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
        if(title == null || title.isEmpty() || title.isBlank()){
            throw new IncorrectArgumentException("Не введён заголовок!");
        } else {
            this.title = title;
        }
    }

    public void setDescription( String description ) {
        if(description == null || description.isEmpty() || description.isBlank()){
            throw new IncorrectArgumentException("Не введено описание!");
        } else {
            this.description = description;
        }
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
