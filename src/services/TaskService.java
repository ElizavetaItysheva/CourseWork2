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
        //если в мапе задач нет, то значит и в основной мапе тоже нет задач, следовательно, выкинем ошибку
       if (temp.isEmpty()){
           throw new TaskNotFoundException("Задач не нашлось...");
       }
       return temp;
    }

    public Task updateDescription(int id, String newDescription){
       //создаю темпорари задачу, которую потом верну
       Task temp = new OneTimeTask("1","1", Type.PERSONAL, LocalDateTime.now());
        // создам вторую болванку, которую приравняю к первой, это надо для проверки
        Task temp2 = temp;
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
        return checkTask(temp, temp2);
    }


    // вынесла проверку на наличие задачи в отдельный метод, надеюсь что такое избегание повтора кода не навредит архитектуре курсовой
    private Task checkTask(Task task1, Task task2) throws TaskNotFoundException{
        // если нужная задача нашлась, то она имеет другое значение(и не будет равна второй), а значит, нужно ее вернуть
        if (!(task1.equals(task2))) {
            return task1;
        } else {
            throw new TaskNotFoundException("Задачи с таким id не нашлось...");
        }
    }

    public Task updateTitle(int id, String newTitle){
        Task temp1 = new OneTimeTask("1","1", Type.PERSONAL, LocalDateTime.now());
        Task temp2 = temp1;
        for(Map.Entry<Integer, Task> taskEntry : taskMap.entrySet()){
            if(taskEntry.getValue().getId() == id){
                taskEntry.getValue().setTitle(newTitle);
                temp1 = taskEntry.getValue();
            }
        }
        return checkTask(temp1, temp2);
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
