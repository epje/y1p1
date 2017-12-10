public class SimpleBlackjack
{
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
    
    public static void main(String[] args)
    {
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
    
        

    }
    
    
    public static void displayCards(boolean showHidden)
    {
        if(showHidden) {
            System.out.print("Computer's cards: [" + computerCards[0].name + "]");
        } else {
            System.out.print("Computer's cards: [X]");
        }
        for(int i = 1; i < computerCardsCount; i++) {
            System.out.print("[" + computerCards[i].name + "]");
        }
        if(showHidden) {
            System.out.print(" (sum: " + computerSum + ")");
        }
        
        System.out.print("\nUser's cards: ");
        for(int i = 0; i < userCardsCount; i++) {
            System.out.print("[" + userCards[i].name + "]");
        }
        System.out.print(" (sum: " + userSum + ")");

        System.out.print("\n");
    }
}
