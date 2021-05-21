package pl.coderslab.service;

import pl.coderslab.domain.Task;
import pl.coderslab.repository.TaskRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskService {

    private TaskRepository repository;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    public boolean createNewTask(String desc, String date, boolean important) throws RuntimeException{
        try{
           Date date1 = simpleDateFormat.parse(date);
           if(date1.before(new Date())){
               throw new RuntimeException("date is in the past");
           }
            Task task = repository.create(desc,date,important);
            return task!=null;
        } catch (ParseException e) {
            throw new RuntimeException("cannot parse date");
        }
    }

    public boolean updateTask(int index, String desc, String date, boolean important) throws RuntimeException{
        try{
            Date date1 = simpleDateFormat.parse(date);
            if(date1.before(new Date())){
                throw new RuntimeException("date is in the past");
            }
            simpleDateFormat.parse(date);
            Task task = repository.update(index,desc,date,important);
            return task!=null;
        } catch (ParseException e) {
            throw new RuntimeException("cannot parse date");
        }
    }

    public boolean deleteTask(int index){
        return repository.delete(index);
    }

    public List<String> readAllTasks(){
        List<Task> tasks = repository.findAll();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            list.add(String.format("%s || %s || %s || %s",
                    i,
                    task.getDescription(),
                    task.getDate(),
                    task.isImportant()?"true":"false"));
        }
        return list;
    }

    public void save(){
        repository.save();
    }
}
