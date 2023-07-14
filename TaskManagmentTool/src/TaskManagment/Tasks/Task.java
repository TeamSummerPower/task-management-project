package TaskManagment.Tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task {
    List<Task> taskList;
    private String name;
    private String Description;
    private int id;

    public Task(String name, String Description, int id){
        this.name = name;
        this.Description = Description;
        this.id = id;
        taskList = new ArrayList<>();
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public void removeTask(Task task){
        taskList.remove(task);
    }

    public List<Task> getAllTasks(){
        return taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(taskList, task.taskList) && Objects.equals(name, task.name) && Objects.equals(Description, task.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList, name, Description, id);
    }
}
