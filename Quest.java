// Quest Class

public class Quest {

    private String description;
    private boolean isCompleted;

    public Quest(String description) {
        this.description = description;
        this.isCompleted = false; // Quest starts as not completed
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void completeQuest() {
        isCompleted = true; // Mark the quest as completed
    }
}
