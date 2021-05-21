package pl.coderslab;

import pl.coderslab.controller.TaskController;
import pl.coderslab.repository.TaskRepository;
import pl.coderslab.service.TaskService;
import pl.coderslab.utils.ConsoleColors;

import java.util.Scanner;

public class App {

    private static TaskController controller;

    public static void main(String[] args) {
        controller = new TaskController(new TaskService(new TaskRepository("tasks.csv")));

        Scanner scanner = new Scanner(System.in);

        System.out.println(ConsoleColors.RESET+"### Hello in Task Manager app ###");
        while(true) {
            System.out.println(ConsoleColors.RESET+"Please select what do You want?");
            System.out.println(ConsoleColors.GREEN+"- list");
            System.out.println(ConsoleColors.YELLOW+"- add");
            System.out.println(ConsoleColors.BLUE+"- remove");
            System.out.println(ConsoleColors.CYAN+"- edit");
            System.out.println(ConsoleColors.RED+"- exit");
            String input = scanner.nextLine();
            if (input.equals("list")){
                controller.displayList();
            }else if (input.equals("add")){
                controller.createTask(scanner);
            }else if (input.equals("remove")){
                controller.removeTask(scanner);
            }else if (input.equals("edit")){
                controller.updateTask(scanner);
            }else if (input.equals("exit")){
                controller.save();
                break;
            }else {
                System.out.println(ConsoleColors.RED+"invalid input");
            }
        }
    }
}
