import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < numStudents; i++) {
            System.out.printf("Enter details for student #%d:%n", (i + 1));
            System.out.print("Name: ");
            String name = scanner.nextLine();
            
            Student student = new Student(name);

            System.out.println("Enter marks for 5 subjects:");
            for (int j = 0; j < 5; j++) {
                System.out.printf("Subject %d mark: ", (j + 1));
                double mark = scanner.nextDouble();
                student.addMark(mark);
            }
            scanner.nextLine(); // Consume newline

            student.calculateAverage();
            student.determineGrade();
            students.add(student);
            System.out.println(); // Add a blank line for readability
        }

        System.out.println("--- Student Results ---");
        double highestAverage = -1;
        double lowestAverage = 101; // Assuming marks are out of 100

        for (Student student : students) {
            student.displayDetails();
            
            // Update highest and lowest averages
            if (student.getAverage() > highestAverage) {
                highestAverage = student.getAverage();
            }
            if (student.getAverage() < lowestAverage) {
                lowestAverage = student.getAverage();
            }
        }
        
        System.out.println("\n--- Summary ---");
        System.out.printf("Highest Average Marks: %.2f%n", highestAverage);
        System.out.printf("Lowest Average Marks: %.2f%n", lowestAverage);

        scanner.close();
    }
}