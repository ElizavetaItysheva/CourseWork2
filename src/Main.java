import services.TaskService;
import tasks.*;
import tasks.type.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main( String[] args ) {
        TaskService taskService = new TaskService();
        Task daily1 = new DailyTask("Чистка зубов", "почистить зубы", Type.PERSONAL, LocalDateTime.of(2023, 1, 23, 9, 0));
        Task oneTime1 = new OneTimeTask("Купить пылесос", " помощнее, чтобы надолго хватило", Type.PERSONAL, LocalDateTime.now());
        Task daily2 = new DailyTask("зарядка", " сделать зарядку", Type.PERSONAL, LocalDateTime.of(2023, 12, 31, 8, 0));
        Task monthly1 = new MonthlyTask("плата по счетам", "заплатить за интернет", Type.PERSONAL, LocalDateTime.of(2023, 2, 1, 18, 0));
        Task weekly1 = new WeeklyTask("Сдать курсовую", "успеть до дедлайна!", Type.WORK, LocalDateTime.of(2023, 1, 27, 16, 25));

        taskService.add(daily1);
        taskService.add(daily2);
        taskService.add(oneTime1);
        taskService.add(monthly1);
        taskService.add(weekly1);

        System.out.println(taskService.getAllGroupByDate());
        System.out.println("______________");
        System.out.println(daily2.appearsIn(LocalDate.of(2024,1,1)));
    }
}