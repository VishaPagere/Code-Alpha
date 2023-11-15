//Task 3

import java.util.Scanner;

public class OnlineQuizPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Quiz questions and answers
        String[] questions = {
                "What is the capital of France?",
                "What is the largest planet in our solar system?",
                "Who wrote 'Romeo and Juliet'?",
        };
        String[] answers = {"Paris", "Jupiter", "William Shakespeare"};

        // User's score
        int score = 0;

        // Display and process quiz questions
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            if (userAnswer.equalsIgnoreCase(answers[i])) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + answers[i] + "\n");
            }
        }

        // Display final score
        System.out.println("Quiz complete! Your final score: " + score + " out of " + questions.length);

        // Close the scanner
        scanner.close();
    }
}

