package TaskManagment;

public record Task(String title, String description, boolean isRec, Alert alert, User user, Priority priority) {
}
