import java.util.Scanner;
public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int target = (int) (Math.random() * 100) + 1;
        int attempts = 0;
        int guess = 0;
        System.out.println("\nWelcome to the Number Guessing Game!");
        System.out.println("I have picked a number between 1 and 100. Can you guess it?\n");

        while (guess != target) {
            System.out.print("Enter your guess: ");
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid whole number!\n");
                sc.next();
                continue;
            }
            guess = sc.nextInt();
            attempts++;
            if (guess < 1 || guess > 100) {
                System.out.println("Your guess should be between 1 and 100.\n");
            } else if (guess < target) {
                System.out.println("Too low! Try a bigger number.\n");
            } else if (guess > target) {
                System.out.println("Too high! Try a smaller number.\n");
            } else {
                System.out.println("Correct! The number was " + target + ".");
                System.out.print("You guessed it in " + attempts + " attempts.");
            }
        }

        sc.close();
    }
}
