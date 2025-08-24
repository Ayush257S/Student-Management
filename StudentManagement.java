import java.util.*;

public class StudentManagement {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("\nWelcome to Student Management System (Step 3: Update + Delete)\n");
        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    private static void printMenu() {
        System.out.println("=== Student Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
    }

    // --- Utility input helpers ---
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readNonEmptyLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("Input cannot be empty. Try again.");
        }
    }

    // --- Step 2: Add + View ---
    private static void addStudent() {
        int id = readInt("Enter Student ID: ");

        // Check duplicate ID
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("❌ A student with this ID already exists!\n");
                return;
            }
        }

        String name = readNonEmptyLine("Enter Name: ");
        int age = readInt("Enter Age: ");

        students.add(new Student(id, name, age));
        System.out.println("✅ Student Added Successfully!\n");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!\n");
        } else {
            System.out.println("--- Student List ---");
            for (Student s : students) {
                System.out.println(s);
            }
            System.out.println();
        }
    }

    // --- Step 3: Update + Delete ---
    private static void updateStudent() {
        int id = readInt("Enter ID of student to update: ");
        for (Student s : students) {
            if (s.getId() == id) {
                String newName = readNonEmptyLine("Enter new name: ");
                int newAge = readInt("Enter new age: ");
                s.setName(newName);
                s.setAge(newAge);
                System.out.println("✅ Student updated successfully!\n");
                return;
            }
        }
        System.out.println("❌ Student with ID " + id + " not found!\n");
    }

    private static void deleteStudent() {
        int id = readInt("Enter ID of student to delete: ");
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId() == id) {
                iterator.remove();
                System.out.println("🗑️ Student deleted successfully!\n");
                return;
            }
        }
        System.out.println("❌ Student with ID " + id + " not found!\n");
    }
}

class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age;
    }
}
