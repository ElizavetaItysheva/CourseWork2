package services;
import exceptions.IncorrectArgumentException;
import exceptions.TaskNotFoundException;
import tasks.Task;
import java.time.LocalDate;
import java.util.*;

public class TaskService {
    private final Map<Integer, Task> taskMap = new HashMap<>();
   private final List<Task> removedTasks = new ArrayList<>();

   public void add(Task task){
           taskMap.put(task.getId(), task);
   }
   public Task remove(int id){
       if (id <= 0){
           throw new IncorrectArgumentException("Некорректный аргумент");
       } else {
           for (Map.Entry<Integer, Task> integerTaskEntry : taskMap.entrySet()) {
               if (integerTaskEntry.getValue().getId() == id) {
                   removedTasks.add(integerTaskEntry.getValue());

               }
           }
           return taskMap.remove(id);
       }
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
        return String.valueOf(sb);
    }
}
