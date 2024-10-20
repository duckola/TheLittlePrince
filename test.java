import java.util.Scanner;

public class test {
    private static volatile boolean skillActivated = false;

    public static void main(String[] args) {
        // Start a thread to listen for user input
        Thread inputThread = new Thread(() -> {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    if (scanner.hasNext()) {
                        String input = scanner.next();
                        // Check if the input matches the activation command
                        if (input.equalsIgnoreCase("activate")) {
                            skillActivated = true;
                            System.out.println("Skill activated!");
                        }
                    }
                }
            }
        });

        inputThread.start();

        // Simulating the game loop
        while (true) {
            if (skillActivated) {
                // Implement the skill cooldown logic here
                skillActivated = false; // Reset after activation
                System.out.println("Using skill...");
                cooldown();
            }
            // Sleep to simulate time passing
            try {
                Thread.sleep(100); // Adjust the sleep time as needed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void cooldown() {
        System.out.println("Skill cooldown started for 10 seconds.");
        try {
            Thread.sleep(10000); // Cooldown period
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Cooldown complete!");
    }
}
