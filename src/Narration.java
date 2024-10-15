public class Narration {
    // Array to hold different narration strings
    private String[] dialogues = {
        "In a universe governed by a brutal game called the Cosmic Challenge, the main character must navigate a series of encounters with flawed characters while learning the true meaning of love, friendship, and survival. Each character he meets offers valuable lessons but also poses a potential threat as he strives to go back to Earth and confront the dark forces threatening the cosmos. Will he rise above the challenges and find a way to save not only himself but also the stars that guide him? The journey ahead is filled with dangers, but the strength of the heart can light the way."
    };

    // Method to get a dialogue by index
    public String getDialogue(int index) {
        if (index >= 0 && index < dialogues.length) {
            return dialogues[index];
        }
        return "Dialogue not found.";
    }

    // Method to get the total number of dialogues
    public int getDialogueCount() {
        return dialogues.length;
    }
}
