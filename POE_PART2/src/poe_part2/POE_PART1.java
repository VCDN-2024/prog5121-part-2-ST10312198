package poe_part2;

import javax.swing.JOptionPane;

public class POE_PART1 {
    private static Login loginInstance;
    private static boolean loggedIn = false;

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            // Display menu options
            String[] options = {"Register", "Login", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Login System", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: // Register
                    registerUser();
                    break;
                case 1: // Login
                    loginUser();
                    break;
                case 2: // Exit
                    exit = true;
                    JOptionPane.showMessageDialog(null, "Exiting the program.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice.");
            }

            if (loggedIn) {
                displayMainMenu();
            }
        }
    }

    private static void registerUser() {
        String regUsername = readInput("Enter username:");
        String regPassword = readInput("Enter password:");

        Registration registration = new Registration(regUsername, regPassword);
        String registrationStatus = registration.registerUser();
        JOptionPane.showMessageDialog(null, "Registration status: " + registrationStatus);
    }

    private static void loginUser() {
        String enteredUsername = readInput("Enter username:");
        String enteredPassword = readInput("Enter password:");

        // Create a Login object with the entered username and password
        loginInstance = new Login(enteredUsername, enteredPassword);
        boolean loginResult = loginInstance.verifyLogin(enteredUsername, enteredPassword);
        String loginStatus = loginInstance.returnLoginStatus(loginResult);
        JOptionPane.showMessageDialog(null, loginStatus);

        loggedIn = loginResult;
    }

    private static void displayMainMenu() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        boolean quit = false;
        while (!quit) {
            String[] options = {"Add tasks", "Show report", "Quit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Main Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: // Add tasks
                    addTasks();
                    break;
                case 1: // Show report
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;
                case 2: // Quit
                    quit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice.");
            }
        }
    }

    private static void addTasks() {
        int numTasks = Integer.parseInt(readInput("How many tasks do you want to enter?"));
        Task[] tasks = new Task[numTasks];

        for (int i = 0; i < numTasks; i++) {
            String taskName = readInput("Enter task name:");
            String taskDescription = readInput("Enter task description:");
            String developerDetails = readInput("Enter developer details:");
            int taskDuration = Integer.parseInt(readInput("Enter task duration in hours:"));
            String[] statusOptions = {"To Do", "Done", "Doing"};
            int statusChoice = JOptionPane.showOptionDialog(null, "Select task status:", "Task Status", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, statusOptions, statusOptions[0]);
            String taskStatus = statusOptions[statusChoice];

            Task task = new Task(taskName, i, taskDescription, developerDetails, taskDuration, taskStatus);
            tasks[i] = task;

            if (task.checkTaskDescription()) {
                JOptionPane.showMessageDialog(null, "Task successfully captured");
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
            }

            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }

        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getTaskDuration();
        }

        JOptionPane.showMessageDialog(null, "Total hours across all tasks: " + totalHours);
    }

    // Helper method to read input using JOptionPane
    private static String readInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
}
