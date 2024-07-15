import java.util.Random;
import java.util.Scanner;

public class taskc {
    public static void main(String[] args) {
        // Create an instance of the Random class
        Random random = new Random();
        
        // Define the range
        int min = 1;
        int max = 100;
        
        // Generate a random number within the range
        int randomNumber = random.nextInt(max - min + 1) + min;
        
        //  user input
        Scanner scanner = new Scanner(System.in);
        
        int userGuess = 0;
        
        // guess
        System.out.println("Guess the number between " + min + " and " + max + ": ");
        
        while (userGuess != randomNumber) {
            // Read the user's guess
            userGuess = scanner.nextInt();
            
            // Check if the user's guess is too low, too high, or correct
            if (userGuess < randomNumber) {
                System.out.println("Too low! Try again: ");
            } else if (userGuess > randomNumber) {
                System.out.println("Too high! Try again: ");
            } else {
                System.out.println("Congratulations! You guessed the correct number: " + randomNumber);
            }
        }
        
        // Close the scanner
        scanner.close();
    }
}

