package pl.coderslab.repository;

import pl.coderslab.domain.Task;
import pl.coderslab.utils.CsvUtil;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private String filename;
    List<Task> list;

    public TaskRepository(String filename) {
        this.filename = filename;
        this.list = new ArrayList<Task>();
        //todo read csv and push items to list
        List<String> lines = CsvUtil.read(this.filename);
        for(String line : lines){
           Task task = Task.build(line);
           if(task!=null) {
               list.add(task);
           }
        }
    }

    //READ
    public List<Task> findAll(){
        return this.list;
    }

    public Task findById(int index){
        if(index<list.size()) {
            return list.get(index);
        }
        return null;
    }

    //CREATE
    public Task create(String desc, String date, boolean important){
        Task task = new Task(desc,date,important);
        list.add(task);
        return task;
    }

    //UPDATE
    public Task update(int index, String desc, String date, boolean important){
        Task task = findById(index);
        if(task!=null){
            task.update(desc,date,important);
            list.set(index,task);
            return task;
        }
        return null;
    }

    //DELETE
    public boolean delete(int index){
        Task task = findById(index);
        if(task!=null){
           return list.remove(task);
        }
        return false;
    }

    public void save() {
        CsvUtil.save(this.list,this.filename);
    }
}
