import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

public class task4 {
    private List<Student> students;
    private static final String FILE_NAME = "students.dat";
    private Scanner scanner;

    public task4() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadStudents();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
        System.out.println("Student added successfully.");
    }

    public void removeStudent(String rollNumber) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getRollNumber().equals(rollNumber)) {
                iterator.remove();
                saveStudents();
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
            System.out.println("Students loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting with an empty list.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
            System.out.println("Students saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public void start() {
        int choice;
        do {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudentFromInput();
                    break;
                case 2:
                    removeStudentByRollNumber();
                    break;
                case 3:
                    searchStudentByRollNumber();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting Student Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void addStudentFromInput() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student roll number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter student grade: ");
        String grade = scanner.nextLine();

        Student newStudent = new Student(name, rollNumber, grade);
        addStudent(newStudent);
    }

    private void removeStudentByRollNumber() {
        System.out.print("Enter roll number of student to remove: ");
        String rollNumber = scanner.nextLine();
        removeStudent(rollNumber);
    }

    private void searchStudentByRollNumber() {
        System.out.print("Enter roll number of student to search: ");
        String rollNumber = scanner.nextLine();
        Student foundStudent = searchStudent(rollNumber);
        if (foundStudent != null) {
            System.out.println("Student found:");
            System.out.println(foundStudent);
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void main(String[] args) {
        task4 sms = new task4();
        sms.start();
    }
}
