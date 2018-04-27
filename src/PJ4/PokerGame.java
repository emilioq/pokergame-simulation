package PJ4;
import java.util.*;

public class PokerGame {

    // default constant values
    private static final int startingBalance=100;
    private static final int numberOfCards=5;

    // default constant payout value and playerHand types
    private static final int[] multipliers={1,2,3,5,6,9,25,50,250};
    private static final String[] goodHandTypes={ 
	  "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	  "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

    // must use only one deck
    private static final Decks oneDeck = new Decks(1);

    // holding current poker 5-card hand, balance, bet    
    private List<Card> playerHand;
    private int playerBalance;
    private int playerBet;

    /** default constructor */
    public PokerGame()
    {
        this(startingBalance);
    }

    /** constructor */
    public PokerGame(int balance)
    {
	this.playerBalance= balance;
    }

    /** This displays the payout table based on multipliers and goodHandTypes arrays */
    private void showPayoutTable()
    { 
	System.out.println("\n\n");
	System.out.println("Payout Table   	      Multiplier   ");
	System.out.println("=======================================");
	int size = multipliers.length;
	for (int i=size-1; i >= 0; i--) {
		System.out.println(goodHandTypes[i]+"\t|\t"+multipliers[i]);
	}
	System.out.println("\n\n");
    }

    /** Check current playerHand using multipliers and goodHandTypes arrays
     *  Prints yourHandType (default is "Sorry, you lost") at the end of function.
     */
    private void checkHands()
    {
        int result = -1;
                
        if(isPair(playerHand)){
            result = -1;
        }
        if(isRoyalPair(playerHand)){
            result = 0;
        }
        if(isTwoPair(playerHand)){
            result = 1;
        }
        if(isThreeOfAKind(playerHand)){
            result = 2;
        }
        if(isStraight(playerHand)){
            result = 3;
        }
        if(isFlush(playerHand)){
            result = 4;
        }
        if(isFullHouse(playerHand)){
            result = 5;
        }
        if(isFourOfAKind(playerHand)){
            result = 6;
        }
        if(isStraightFlush(playerHand)){
            result = 7;
        }
        if(isRoyalFlush(playerHand)){
            result = 8;
        }
        
        switch (result){
            case -1:
                System.out.println("Sorry, you lost.");
                System.out.println();
                System.out.println("Balance         : " + playerBalance);
                System.out.println("_________________________");
                break;
            default:
                System.out.println(goodHandTypes[result]);
                this.playerBalance += (this.playerBet * multipliers[result]);
                System.out.println();
                System.out.println("Balance         : " + playerBalance);
                System.out.println("_________________________");
        }
        
    }
    
    private void sortCopy(List copyHand) {
        Collections.sort(copyHand, new Comparator<Card>() {
            public int compare(Card a, Card b) {
                if (a.getRank() > b.getRank()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }
    
    private boolean isPair(List playerHand){
        List<Card> copyHand = new ArrayList<Card>(playerHand);
        sortCopy(copyHand);
        
        int counter = 0;
        for(int i = 0; i < playerHand.size()-1; i++){
                Card a = (Card)copyHand.get(i);
                Card b = (Card)copyHand.get(i+1);
                if(a.getRank() == b.getRank()){
                    counter++;
                }
        }
        return counter > 0;
    }
    
    private boolean isRoyalPair(List playerHand){
        List<Card> copyHand = new ArrayList<Card>(playerHand);
        sortCopy(copyHand);
        
        int counter = 0;
        for(int i = 0; i < playerHand.size()-1; i++){
                Card a = (Card)copyHand.get(i);
                Card b = (Card)copyHand.get(i+1);
                if((a.getRank() == b.getRank()) && a.getRank() > 10){
                    counter++;
                }
        }
        return counter > 0;
    }
    
    private boolean isTwoPair(List playerHand){
        List<Card> copyHand = new ArrayList<Card>(playerHand);
        sortCopy(copyHand);
        
        int counter = 0;
        for(int i = 0; i < playerHand.size()-1; i++){
                Card a = (Card)copyHand.get(i);
                Card b = (Card)copyHand.get(i+1);
                if(a.getRank() == b.getRank()){
                    counter++;
                    i++;
                }
        }
        return counter == 2;
    }
    
    private boolean isThreeOfAKind(List playerHand){
        List<Card> copyHand = new ArrayList<Card>(playerHand);
        sortCopy(copyHand);
        
        int counter = 0;
        for(int i = 0; i < playerHand.size()-2; i++){
                Card a = (Card)copyHand.get(i);
                Card b = (Card)copyHand.get(i+1);
                Card c = (Card)copyHand.get(i+2);
                if(a.getRank() == b.getRank() && b.getRank() == c.getRank()){
                    counter++;
                }
        }
        return counter >= 1;
    }
    
    private boolean isStraight(List playerHand){
        List<Card> copyHand = new ArrayList<Card>(playerHand);
        sortCopy(copyHand);

        Card a = (Card) copyHand.get(0);
        Card b = (Card) copyHand.get(1);
        Card c = (Card) copyHand.get(2);
        Card d = (Card) copyHand.get(3);
        Card e = (Card) copyHand.get(4);

        return (a.getRank() == b.getRank() - 1 && b.getRank() == c.getRank() - 1 && c.getRank() == d.getRank() - 1 && d.getRank() == e.getRank() - 1) ||
               (b.getRank() == c.getRank() - 1 && c.getRank() == d.getRank() - 1 && d.getRank() == e.getRank() - 1 && e.getRank() == a.getRank() + 12);        
    }
    
    private boolean isFlush(List playerHand){
        
        Card a = (Card) playerHand.get(0);
        Card b = (Card) playerHand.get(1);
        Card c = (Card) playerHand.get(2);
        Card d = (Card) playerHand.get(3);
        Card e = (Card) playerHand.get(4);
        
        return (a.getSuit() == b.getSuit() && b.getSuit() == c.getSuit() && c.getSuit() == d.getSuit() && d.getSuit() == e.getSuit());
    }
    
    private boolean isFullHouse(List playerHand){
        List<Card> copyHand = new ArrayList<Card>(playerHand);
        sortCopy(copyHand);

        Card a = (Card) copyHand.get(0);
        Card b = (Card) copyHand.get(1);
        Card c = (Card) copyHand.get(2);
        Card d = (Card) copyHand.get(3);
        Card e = (Card) copyHand.get(4);
        
        return (a.getRank() == b.getRank() && b.getRank() == c.getRank() && d.getRank() == e.getRank()) ||
               (a.getRank() == b.getRank() && c.getRank() == d.getRank() && d.getRank() == e.getRank()); 
    }
    
    private boolean isFourOfAKind(List playerHand){
        List<Card> copyHand = new ArrayList<Card>(playerHand);
        sortCopy(copyHand);

        Card a = (Card) copyHand.get(0);
        Card b = (Card) copyHand.get(1);
        Card c = (Card) copyHand.get(2);
        Card d = (Card) copyHand.get(3);
        Card e = (Card) copyHand.get(4);
        
        return (a.getRank() == b.getRank() && b.getRank() == c.getRank() && c.getRank() == d.getRank()) || 
               (b.getRank() == c.getRank() && c.getRank() == d.getRank() && d.getRank() == e.getRank());
    }
    
    private boolean isStraightFlush(List playerHand){
        return (isStraight(playerHand) && isFlush(playerHand));
    }
    
    private boolean isRoyalFlush(List playerHand){
        List<Card> copyHand = new ArrayList<Card>(playerHand);
        sortCopy(copyHand);
        
        Card a = (Card) copyHand.get(0);
        Card b = (Card) copyHand.get(1);
        
        return (isStraightFlush(playerHand) && a.getRank() == 1 && b.getRank() == 10);
    }
    
    private void intro(){
        System.out.println("Balance         : " + playerBalance);
        boolean betting = true;
        
        while (betting) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter your bet  : ");

            try {
                playerBet = input.nextInt();
                betting = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid bet.");
                betting = true;
            } catch (InputMismatchException x) {
                System.out.println("Invalid bet.");
                betting = true;
            }

            if (playerBet < 0 || playerBet > playerBalance) {
                System.out.println("Invalid bet. ");
                betting = true;
            }
        }

        playerBalance -= playerBet;
        System.out.println("_________________________");
        oneDeck.reset();
        oneDeck.shuffle();

        try {
            playerHand = oneDeck.deal(numberOfCards);
        } catch (PlayingCardException x) {
            System.out.println(x.getMessage());
        }
    }
    
    private void displayHand(){
        System.out.println("Hand | " +playerHand);
    }
    
    private void updateHand(List playerHand){
        int process = 0;
        String keep = "";

        process++;

        while (process == 1) {

            Scanner input = new Scanner(System.in);
            System.out.print("Enter position of cards to keep (e.g. 1 4 5) : ");

            try {
                keep = input.nextLine();
                process++;
            } catch (NumberFormatException x) {
                System.out.println("Invalid input.");
                process = 1;
            }

            while (process == 2) {
                if (!keep.isEmpty()) {
                    List<Card> newHand = new ArrayList<Card>();
                    List<String> str = Arrays.asList(keep.split(" "));

                    if (str.size() > 5 || str.size() < 1) {                                             //checks if user inputed too much positions or left a space.
                        System.out.println("Invalid input.");
                        process = 0;
                    }

                    for (int i = 0; i < str.size(); i++) {
                        if (Integer.parseInt(str.get(i)) < 1 || Integer.parseInt(str.get(i)) > 5) {     //checks if the positions are between 1 and 5
                            System.out.println("Invalid card position.");
                            process = 0;
                        }
                    }

                    process++;

                    while (process == 3) {
                        try {                                                                           //obtains new cards
                            newHand = oneDeck.deal(5 - str.size());
                        } catch (PlayingCardException x) {
                            System.out.println(x.getMessage());
                        }

                        for (int i = 0; i < str.size(); i++) {                                          //adds kept cards
                            newHand.add((Card) playerHand.get(Integer.parseInt(str.get(i)) - 1));
                        }

                        playerHand.clear();
                        playerHand.addAll(newHand);
                        process = 0;
                    }
                }

                if (keep.isEmpty()) {
                    List<Card> newHand = new ArrayList<Card>();
                    try {
                        newHand = oneDeck.deal(5);
                    } catch (PlayingCardException x) {
                        System.out.println(x.getMessage());
                    }

                    playerHand.clear();
                    playerHand.addAll(newHand);

                    process = 0;
                }
            }
        }
        System.out.println();
    }
    
    public void play() 
    {
    /** The main algorithm for single player poker game */
        showPayoutTable();
        boolean playing = true;

        while (playing) {
            boolean ending = true;
            intro();
            displayHand();
            updateHand(playerHand);
            displayHand();
            checkHands();
            if (playerBalance <= 0) {
                playing = false;
                ending = false;
            }
            while (ending) {
                System.out.println();
                System.out.print("  Continue?         (y/n) : ");
                Scanner input = new Scanner(System.in);
                char x = input.next().charAt(0);

                switch (x) {
                    case 'y':
                        System.out.print("  See Payout Table? (y/n) : ");
                        Scanner input2 = new Scanner(System.in);
                        char z = input2.next().charAt(0);
                        if (z == 'y'){showPayoutTable();}
                        ending = false;
                        playing = true;
                        System.out.println();
                        break;
                        
                    case 'n':
                        ending = false;
                        playing = false;
                        break;
                        
                    default:
                        System.out.println("  Invalid input.");
                        break;

                }
            }
        }
    }

    private void testCheckHands()
    {
      	try {
    		playerHand = new ArrayList<Card>();

		// set Royal Flush
		playerHand.add(new Card(1,4));
		playerHand.add(new Card(10,4));
		playerHand.add(new Card(12,4));
		playerHand.add(new Card(11,4));
		playerHand.add(new Card(13,4));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight Flush
		playerHand.set(0,new Card(9,4));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight
		playerHand.set(4, new Card(8,2));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Flush 
		playerHand.set(4, new Card(5,4));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	 	// "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

		// set Four of a Kind
		playerHand.clear();
		playerHand.add(new Card(8,4));
		playerHand.add(new Card(8,1));
		playerHand.add(new Card(12,4));
		playerHand.add(new Card(8,2));
		playerHand.add(new Card(8,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Three of a Kind
		playerHand.set(4, new Card(11,4));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Full House
		playerHand.set(2, new Card(11,2));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Two Pairs
		playerHand.set(1, new Card(9,2));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Royal Pair
		playerHand.set(0, new Card(3,2));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// non Royal Pair
		playerHand.set(2, new Card(3,4));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");
      	}
      	catch (Exception e)
      	{
		System.out.println(e.getMessage());
      	}
    }

    public static void main(String args[]) 
    {
	PokerGame pokergame = new PokerGame();
	pokergame.testCheckHands();
    }
}
