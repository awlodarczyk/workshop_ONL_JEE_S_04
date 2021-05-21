package pl.coderslab.controller;

import pl.coderslab.service.TaskService;
import pl.coderslab.utils.ConsoleColors;

import java.text.ParseException;
import java.util.Scanner;

public class TaskController {
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    public void displayList(){
        System.out.println(ConsoleColors.RESET+"TASKS:");
       for(String line: service.readAllTasks()){
           System.out.println(line);
       }

        System.out.println(ConsoleColors.RESET+" ================ ");
    }


    public void createTask(Scanner scanner){
        String desc = getInput(scanner,ConsoleColors.YELLOW+"Give me description:");
        String date = getInput(scanner,ConsoleColors.YELLOW+"Give me some due date (yyyy-mm-dd):");
        String isImportant = getInput(scanner,ConsoleColors.YELLOW+"Is important (t/f):");
        if(isImportant.equals("t") || isImportant.equals("f")){
            try {
                if(service.createNewTask(desc,date,isImportant.equals("t"))){
                    System.out.println(ConsoleColors.GREEN+"Task created");
                    System.out.println(" ================ ");
                }else{
                    System.out.println(ConsoleColors.RED+"Ooops :(");
                    System.out.println(" ================ ");
                }
            } catch (RuntimeException e) {
                handleException(e);
            }
        }else{
            System.out.println(ConsoleColors.RED+"Bad data input");
            System.out.println(" ================ ");
        }
    }
    public void updateTask(Scanner scanner){
        int id = getIntInput(scanner,ConsoleColors.YELLOW+"Give me id:");
        String desc = getInput(scanner,ConsoleColors.YELLOW+"Give me description:");
        String date = getInput(scanner,ConsoleColors.YELLOW+"Give me some due date (yyyy-mm-dd):");
        String isImportant = getInput(scanner,ConsoleColors.YELLOW+"Is important (t/f):");
        if(isImportant.equals("t") || isImportant.equals("f")){
            try {
                if(service.updateTask(id, desc,date,isImportant.equals("t"))){
                    System.out.println(ConsoleColors.GREEN+"Task updated");
                    System.out.println(" ================ ");
                }else{
                    System.out.println(ConsoleColors.RED+"Ooops :(");
                    System.out.println(" ================ ");
                }
            } catch (RuntimeException e) {
               handleException(e);
            }
        }else{
            System.out.println(ConsoleColors.RED+"Bad data input");
            System.out.println(" ================ ");
        }
    }
    public void removeTask(Scanner scanner){
        int id = getIntInput(scanner,ConsoleColors.YELLOW+"Give me id:");
        if(service.deleteTask(id)){
            System.out.println(ConsoleColors.GREEN+"Task removed");
            System.out.println(" ================ ");
        }else{
            System.out.println(ConsoleColors.RED+"Ooops :(");
            System.out.println(" ================ ");
        }
    }

    private String  getInput(Scanner scanner, String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
    private int  getIntInput(Scanner scanner, String prompt){
        System.out.println(prompt);
        while (!scanner.hasNextInt()){
            scanner.nextLine();
            System.out.println(ConsoleColors.RED_BOLD+"invalid input type");
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    public void save() {
        service.save();
    }

    private void handleException(RuntimeException e){
        if(e.getMessage().equals("cannot parse date")) {
            System.out.println(ConsoleColors.RED + "Date has invalid format");
            System.out.println(" ================ ");
        }else if(e.getMessage().equals("date is in the past")){
            System.out.println(ConsoleColors.RED + "You tried to put date from past");
            System.out.println(" ================ ");
        }else{
            System.out.println(ConsoleColors.RED + e.getMessage());
            System.out.println(" ================ ");
        }
    }
}
