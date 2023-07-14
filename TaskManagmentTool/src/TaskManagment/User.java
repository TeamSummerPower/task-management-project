package TaskManagment;

import java.util.List;

public record User(String fistName, String lastName, String email, String password, Role role, List<Task> taskList) {
}
