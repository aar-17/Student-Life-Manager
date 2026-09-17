import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static final Scanner sc = new Scanner(System.in);
    static final ArrayList<Task> tasks = new ArrayList<>();
    static final ArrayList<Expense> expenses = new ArrayList<>();

    static final String[] MENU = {
        "Add Task", "View Tasks", "Update Task", "Delete Task", "Search Task",
        "Add Expense", "View Expenses", "Delete Expense", "View Report"
    };

    public static void main(String[] args) {
        FileManager.loadTasks(tasks);
        FileManager.loadExpenses(expenses);

        System.out.println("================================");
        System.out.println("     STUDENT LIFE MANAGER");
        System.out.println("================================");

        Student student = new Student(ask("Enter your name: "), ask("Enter your email: "));

        int choice;
        do {
            System.out.println("\nWelcome " + student.getName());
            System.out.println("----------------------------");
            for (int i = 0; i < MENU.length; i++) {
                System.out.println((i + 1) + ". " + MENU[i]);
            }
            System.out.println("0. Exit");

            choice = askInt("Enter choice: ");

            switch (choice) {
                case 1: addTask(); break;
                case 2: viewTasks(); break;
                case 3: updateTask(); break;
                case 4: deleteTask(); break;
                case 5: searchTask(); break;
                case 6: addExpense(); break;
                case 7: viewExpenses(); break;
                case 8: deleteExpense(); break;
                case 9: report(); break;
                case 0:
                    FileManager.saveTasks(tasks);
                    FileManager.saveExpenses(expenses);
                    System.out.println("Data saved. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // --- tasks ---

    static void addTask() {
        System.out.println("\n--- Add Task ---");
        String title = ask("Enter task title: ");
        String description = ask("Enter description: ");
        String date = ask("Enter due date (YYYY-MM-DD): ");

        if (title.isEmpty() || date.isEmpty()) {
            System.out.println("Title and date cannot be empty.");
            return;
        }

        tasks.add(new Task(nextTaskId(), title, description, date, "Pending"));
        System.out.println("Task added successfully.");
    }

    static void viewTasks() {
        System.out.println("\n--- Tasks ---");
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (Task t : tasks) System.out.println(t);
    }

    static void updateTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        int id = askInt("Enter task ID: ");
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setStatus(ask("Enter new status (Pending/Completed): "));
                System.out.println("Task updated successfully.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    static void deleteTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        int id = askInt("Enter task ID: ");
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        System.out.println(removed ? "Task deleted successfully." : "Task not found.");
    }

    static void searchTask() {
        String keyword = ask("Enter keyword: ").toLowerCase();
        boolean found = false;

        for (Task t : tasks) {
            if (t.getTitle().toLowerCase().contains(keyword)) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("No task found.");
    }

    // --- expenses ---

    static void addExpense() {
        System.out.println("\n--- Add Expense ---");
        String category = ask("Enter category: ");

        double amount;
        try {
            amount = Double.parseDouble(ask("Enter amount: "));
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        String date = ask("Enter date: ");
        String note = ask("Enter note: ");

        expenses.add(new Expense(nextExpenseId(), category, amount, date, note));
        System.out.println("Expense added successfully.");
    }

    static void viewExpenses() {
        System.out.println("\n--- Expenses ---");
        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
            return;
        }
        for (Expense e : expenses) System.out.println(e);
    }

    static void deleteExpense() {
        viewExpenses();
        if (expenses.isEmpty()) return;

        int id = askInt("Enter expense ID: ");
        boolean removed = expenses.removeIf(e -> e.getId() == id);
        System.out.println(removed ? "Expense deleted successfully." : "Expense not found.");
    }

    // --- report ---

    static void report() {
        int completed = 0;
        for (Task t : tasks) {
            if ("Completed".equalsIgnoreCase(t.getStatus())) completed++;
        }

        double total = 0;
        for (Expense e : expenses) total += e.getAmount();

        System.out.println("\n========== REPORT ==========");
        System.out.println("Total Tasks     : " + tasks.size());
        System.out.println("Pending Tasks   : " + (tasks.size() - completed));
        System.out.println("Completed Tasks : " + completed);
        System.out.printf ("Total Expenses  : \u20b9%.2f%n", total);
        System.out.println("============================");
    }

    // --- helpers ---

    static String ask(String label) {
        System.out.print(label);
        return sc.nextLine().trim();
    }

    static int askInt(String label) {
        try {
            return Integer.parseInt(ask(label));
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return -1;
        }
    }

    /** Highest existing ID + 1, so deleting a row never causes a duplicate ID. */
    static int nextTaskId() {
        int max = 0;
        for (Task t : tasks) max = Math.max(max, t.getId());
        return max + 1;
    }

    static int nextExpenseId() {
        int max = 0;
        for (Expense e : expenses) max = Math.max(max, e.getId());
        return max + 1;
    }
}
public class Task {

    private final int id;
    private final String title;
    private final String description;
    private final String dueDate;
    private String status;

    public Task(int id, String title, String description, String dueDate, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getId()             { return id; }
    public String getTitle()       { return title; }
    public String getDescription() { return description; }
    public String getDueDate()     { return dueDate; }
    public String getStatus()      { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Due: %s | Status: %s | %s",
                id, title, dueDate, status, description);
    }
}
public class Expense {

    private final int id;
    private final String category;
    private final double amount;
    private final String date;
    private final String note;

    public Expense(int id, String category, double amount, String date, String note) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    public int getId()          { return id; }
    public String getCategory() { return category; }
    public double getAmount()   { return amount; }
    public String getDate()     { return date; }
    public String getNote()     { return note; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | \u20b9%.2f | %s | %s",
                id, category, amount, date, note);
    }
}
public class Student {

    private final String name;
    private final String email;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName()  { return name; }
    public String getEmail() { return email; }

    public void display() {
        System.out.println("Name  : " + name);
        System.out.println("Email : " + email);
    }
}
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String DIR = "data";
    private static final String TASK_FILE = DIR + "/tasks.txt";
    private static final String EXPENSE_FILE = DIR + "/expenses.txt";

    public static void saveTasks(ArrayList<Task> tasks) {
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(join(t.getId(), t.getTitle(), t.getDescription(), t.getDueDate(), t.getStatus()));
        }
        write(TASK_FILE, lines, "tasks");
    }

    public static void loadTasks(ArrayList<Task> tasks) {
        for (String[] f : read(TASK_FILE, "tasks")) {
            tasks.add(new Task(Integer.parseInt(f[0]), f[1], f[2], f[3], f[4]));
        }
    }

    public static void saveExpenses(ArrayList<Expense> expenses) {
        List<String> lines = new ArrayList<>();
        for (Expense e : expenses) {
            lines.add(join(e.getId(), e.getCategory(), e.getAmount(), e.getDate(), e.getNote()));
        }
        write(EXPENSE_FILE, lines, "expenses");
    }

    public static void loadExpenses(ArrayList<Expense> expenses) {
        for (String[] f : read(EXPENSE_FILE, "expenses")) {
            expenses.add(new Expense(Integer.parseInt(f[0]), f[1], Double.parseDouble(f[2]), f[3], f[4]));
        }
    }

    // --- helpers ---

    private static String join(Object... parts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) sb.append('|');
            sb.append(parts[i]);
        }
        return sb.toString();
    }

    private static void write(String path, List<String> lines, String label) {
        try {
            Files.createDirectories(Paths.get(DIR));
            Files.write(Paths.get(path), lines);
        } catch (IOException e) {
            System.out.println("Error saving " + label + ".");
        }
    }

    private static List<String[]> read(String path, String label) {
        List<String[]> rows = new ArrayList<>();
        if (!Files.exists(Paths.get(path))) return rows;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (fields.length == 5) rows.add(fields);
            }
        } catch (Exception e) {
            System.out.println("Error loading " + label + ".");
        }
        return rows;
    }
}
