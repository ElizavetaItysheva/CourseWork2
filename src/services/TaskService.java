package services;
import exceptions.TaskNotFoundException;
import tasks.OneTimeTask;
import tasks.Task;
import tasks.type.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskService {
    private final Map<Integer, Task> taskMap = new HashMap<>();
   private final List<Task> removedTasks = new ArrayList<>();

   public void add(Task task){
           taskMap.put(task.getId(), task);
   }
   public Task remove(int id){
           for (Map.Entry<Integer, Task> integerTaskEntry : taskMap.entrySet()) {
               if (integerTaskEntry.getValue().getId() == id) {
                   removedTasks.add(integerTaskEntry.getValue());
               }
           }
           return taskMap.remove(id);
       }
    public List<Task> getRemovedTasks() {
       if(!(removedTasks.isEmpty())) {
           return removedTasks;
       }else {
           throw new TaskNotFoundException("Удалённых задач нет!");
       }
    }
    public Map<LocalDate, List<Task>> getAllGroupByDate(){
       // создала мапу темпорари, которую потом верну
       Map<LocalDate, List<Task>> temp = new HashMap<>();
       // прохожусь по мапе задач
       for(Map.Entry<Integer, Task> integerTaskEntry : taskMap.entrySet()){
           //складываю в темпорари в ключ - время создания первой задачи, в значение - метод, который вернёт список задач нужной даты
           temp.put(integerTaskEntry.getValue().getDateTime().toLocalDate(), getAllByDate(integerTaskEntry.getValue().getDateTime().toLocalDate()));
       }
       return temp;
    }
    public Task updateDescription(int id, String newDescription){
       //создаю темпорари задачу, которую потом верну
       Task temp = new OneTimeTask("1","1", Type.PERSONAL, LocalDateTime.now());
        //прохожусь снова по мапе, в поисках нужной задачи
        for(Map.Entry<Integer, Task> taskEntry : taskMap.entrySet()){
            // и если такая задача есть, то
            if(taskEntry.getValue().getId() == id){
                // у этой задачи вызываю сеттер и вставляю туда новое описание
                taskEntry.getValue().setDescription(newDescription);
                // приравниваю задаче-болванке значения нужной задачи
                temp = taskEntry.getValue();
                // а если задачи с таким айди не существует, то выброшу ошибку
            }
        }
        return temp;
    }
    public List<Task> getAllByDate( LocalDate localDate ){
       //создаем лист куда будем бережно складывать задачи нужной даты
       List<Task> currentTasks = new ArrayList<>();
       //проходимся по мапе
       for(Map.Entry<Integer, Task> integerTaskEntry : taskMap.entrySet()){
           //если задача появится в аргументе ИЛИ если дата задачи равна аргументу
           if(integerTaskEntry.getValue().appearsIn(localDate) || integerTaskEntry.getValue().getDateTime().toLocalDate().equals(localDate)){
               //то добавляем эту задачу в лист
               currentTasks.add(integerTaskEntry.getValue());
           }
           // если список пуст, то выкидываем ошибку
           if(currentTasks.isEmpty()){
               throw new TaskNotFoundException("Задача не найдена!");
           }
       }
       return currentTasks;
   }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Task> integerTaskEntry : taskMap.entrySet()){
            sb.append(integerTaskEntry.getValue());
            sb.append('\n');
            sb.append('\n');
        }
        sb.append('\n');
        return String.valueOf(sb);
    }
}
