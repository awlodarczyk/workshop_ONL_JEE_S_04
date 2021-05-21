package pl.coderslab.domain;

public class Task {
    private String description;
    private String date;
    private boolean important;

    public Task(String description, String date, boolean important) {
        this.description = description;
        this.date = date;
        this.important = important;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public boolean isImportant() {
        return important;
    }

    public void update(String description, String date, boolean important){
        this.description = description;
        this.date = date;
        this.important = important;
    }

    public String toCsv(){
        return String.format("%s, %s, %s",this.description,this.date,this.important?"true":"false");
    }
    //builder
    public static Task build(String string){
        String[] parts = string.split(",");
        if(parts.length==3) {
            return new Task(parts[0], parts[1], parts[2].equals("true"));
        }
        return null;
    }

}
