import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an ArrayList to store student grades
        ArrayList<Integer> grades = new ArrayList<>();

        // Prompt the user to enter grades until they enter a negative value
        System.out.println("Enter student grades (enter a negative value to stop):");
        int grade;
        do {
            System.out.print("Enter grade: ");
            grade = scanner.nextInt();

            // Check if the grade is not negative before adding it to the list
            if (grade >= 0) {
                grades.add(grade);
            }
        } while (grade >= 0);

        // Check if there are grades entered
        if (grades.isEmpty()) {
            System.out.println("No grades entered. Exiting program.");
            System.exit(0);
        }

        // Compute and display the average, highest, and lowest grades
        double average = calculateAverage(grades);
        int highest = findHighest(grades);
        int lowest = findLowest(grades);

        System.out.println("Average Grade: " + average);
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);

        scanner.close();
    }

    // Method to calculate the average of grades
    private static double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    // Method to find the highest grade
    private static int findHighest(ArrayList<Integer> grades) {
        return Collections.max(grades);
    }

    // Method to find the lowest grade
    private static int findLowest(ArrayList<Integer> grades) {
        return Collections.min(grades);
    }
}
