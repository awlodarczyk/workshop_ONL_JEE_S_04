package pl.coderslab.utils;

import pl.coderslab.domain.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<String> read(String filename){
        Path path = Paths.get(filename);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void save(List<Task> tasks, String filename){
        Path path = Paths.get(filename);
        List<String> lines = new ArrayList<>();
        for(Task task:tasks){
            lines.add(task.toCsv());
        }
        try {
            Files.write(path,lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
