import java.io.*;

public class SimpleBlackjack {

    // Integer arrays to contain each of the players' drawn card values.
    static Card[] computerCards = new Card[12];
    static Card[] userCards = new Card[12];

    static int computerCardsCount = 0;
    static int userCardsCount = 0;

    // Create a new deck of cards.
    static Deck deck = new Deck();

    // Variables to keep the score of each player.
    static int computerSum;
    static int userSum;

    public static void main(String[] args) throws InterruptedException {
        // Deal two cards to each player.
        computerCards[0] = deck.drawCard();
        computerCards[1] = deck.drawCard();
        computerCardsCount = 2;
        userCards[0] = deck.drawCard();
        userCards[1] = deck.drawCard();
        userCardsCount = 2;

        // Variables to keep the score of each player.
        computerSum = computerCards[0].value + computerCards[1].value;
        userSum = userCards[0].value + userCards[1].value;

        displayCards(false);  // Display the cards with one of the Computer's hidden from the User.

        // Construction site...
        // Create BufferedReader to handle user input.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Declare and Initialise string & booleans to store input & manage program flow control.
        String userInput = "";
        boolean userDraws = true;
        boolean askUser = true;
        boolean gameOver = false;

        // User's turn to play.
        System.out.println("\nUSER'S TURN");

        // While loop to check user hasn't gone over 21 and whether we shall continue to ask them.
        while ((userSum < 21) && (askUser == true)) {

            // While loop to determine whether to keep asking user for a card.
            while (askUser) {

                // Do-while loop to validate user input.
                do {
                    System.out.print("Draw new card (Y/N): ");
                    try {
                        userInput = br.readLine();
                    } catch (IOException e) {
                        System.out.println("IOException thrown, please check input.");
                    }
                } while (!(userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("n"))); // While the user input does not equal y/n (case ignored).

                // Boolean userDraws determines whether to draw another card for user.
                if (userInput.equalsIgnoreCase("y")) {
                    // Exit out of askUser while-loop, and proceed to draw a card.
                    askUser = false;
                    userDraws = true;
                } else {
                    // Exit out of askUser while-loop and proceed to computer's turn.
                    askUser = false;
                    userDraws = false;
                    System.out.println("User stays.");
                }
            }

            // Block runs when user decides to draw a card.
            while (userDraws) {

                // Draw a new card and increment user's card count.
                userCards[userCardsCount] = deck.drawCard();
                userCardsCount++;

                // State what card the user drew and update the cards sum.
                System.out.printf("User drew a/an [%s]%n", userCards[userCardsCount - 1].value);
                userSum += userCards[userCardsCount - 1].value;

                displayCards(false);

                if (userSum > 21 || userCardsCount > 11) {
                    // User has too many, so break out of original do-while loop and proceed to game ending.
                    userDraws = false;
                    askUser = false;
                    gameOver = true;
                } else {
                    // Exit from this nested while loop, and continue to ask user again.
                    userDraws = false;
                    askUser = true;
                }
            }
        }

        // Check if game has ended (boolean gameOver) - if so then skip to game report.
        while (!gameOver) {

            // Computer's turn to play.
            System.out.println("\nCOMPUTER'S TURN");
            while (computerSum < 18) {
                // Draw a new card and increment computer's card count.
                computerCards[computerCardsCount] = deck.drawCard();
                computerCardsCount++;

                // State what card the computer drew and update the cards sum.
                System.out.printf("Computer drew a/an [%s]%n", computerCards[computerCardsCount - 1].value);
                computerSum += computerCards[computerCardsCount - 1].value;

                displayCards(true);
            }

            // Determine whether computer has too many, or whether it has stayed.
            if (computerSum > 21) {
                // Computer has too many, so break out of while-loop and proceed to end game.
                gameOver = true;
            } else {
                // Computer does not have too many, therefore has stayed.
                System.out.println("Computer stays.");
                gameOver = true;
            }
        }

        // Report on the outcome of the game.
        System.out.println("\nEND");
        displayCards(true);

        if (computerSum > 21) {
            // When the computer goes over 21.
            System.out.println("Computer went over 21. USER WINS!");
        } else if (userSum > 21) {
            // When the user goes over 21.
            System.out.println("User went over 21. COMPUTER WINS!");
        } else if (computerSum == userSum) {
            // When the computer and user have the same sum.
            System.out.println("COMPUTER WINS!");
        } else if ((userSum > computerSum) && (userSum <= 21)) {
            // When user has more than computer AND has up to 21.
            System.out.println("USER WINS!");
        } else if ((computerSum > userSum) && (computerSum <= 21)) {
            // When computer has more than user AND has up to 21.
            System.out.println("COMPUTER WINS!");
        }
    }

    public static void displayCards(boolean showHidden) {
        if (showHidden) {
            System.out.print("Computer's cards: [" + computerCards[0].name + "]");
        } else {
            System.out.print("Computer's cards: [X]");
        }
        for (int i = 1; i < computerCardsCount; i++) {
            System.out.print("[" + computerCards[i].name + "]");
        }
        if (showHidden) {
            System.out.print(" (sum: " + computerSum + ")");
        }

        System.out.print("\nUser's cards: ");
        for (int i = 0; i < userCardsCount; i++) {
            System.out.print("[" + userCards[i].name + "]");
        }
        System.out.print(" (sum: " + userSum + ")");

        System.out.print("\n");
    }
}
