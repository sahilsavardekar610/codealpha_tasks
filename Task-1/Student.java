import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Double> marks;
    private double average;
    private char grade;

    public Student(String name) {
        this.name = name;
        this.marks = new ArrayList<>();
    }

    // Method to add marks for a subject
    public void addMark(double mark) {
        this.marks.add(mark);
    }

    // Method to calculate the average marks
    public void calculateAverage() {
        double sum = 0;
        for (double mark : marks) {
            sum += mark;
        }
        this.average = sum / marks.size();
    }

    // Method to determine the grade based on the average
    public void determineGrade() {
        if (average >= 90) {
            this.grade = 'A';
        } else if (average >= 80) {
            this.grade = 'B';
        } else if (average >= 70) {
            this.grade = 'C';
        } else if (average >= 60) {
            this.grade = 'D';
        } else {
            this.grade = 'F';
        }
    }
    
    // Getter methods to access the calculated values
    public String getName() {
        return name;
    }

    public double getAverage() {
        return average;
    }

    public char getGrade() {
        return grade;
    }

    // Method to display student information
    public void displayDetails() {
        System.out.printf("Student Name: %s, Average Marks: %.2f, Grade: %c%n", name, average, grade);
    }
}