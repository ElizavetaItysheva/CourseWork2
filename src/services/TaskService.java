package services;

import exceptions.IncorrectArgumentException;
import exceptions.TaskNotFoundException;
import tasks.Task;

import java.time.LocalDate;
import java.util.*;

public class TaskService {
    // создала отдельную статическую переменную, которая по сути равна id задачи(не работает, если добавить в метод add id задачи)
    private static int key;

    private final Map<Integer, Task> taskMap = new HashMap<>();
   private final List<Task> removedTasks = new ArrayList<>();

   public void add(Task task){
       if(!(task == null)) {
           taskMap.put(key, task);
           key += 1;
       } else {
           throw new TaskNotFoundException("Задача не существует.");
       }
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
       List<Task> currentTasks = new ArrayList<>();
       for(Map.Entry<Integer, Task> integerTaskEntry : taskMap.entrySet()){
           if(localDate.isEqual(integerTaskEntry.getValue().getDateTime().toLocalDate())){
               currentTasks.add(integerTaskEntry.getValue());
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
