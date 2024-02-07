import java.util.*;

public class PairProbability {

    /*
    The main function which calls the simulateProbability function which takes a parameter of the number of trails you
    want to do, for the assignment we were told to do 1000 and 10000. Which is exactly what's happening below.
     */
    public static void main(String[] args){
        simulateProbability(1000);
        simulateProbability(10000);
        simulateProbability(100000);

    }

    /*This is the getShufledDeck function which does what it's name implies it takes no parameters and returns an array
        of 52 cards numbered 1-13, this function uses the Fisher Yates shuffle which makes sure the array is uniformly
        random. The function is used every time one trail is done to ensure a fresh poker draw.
     */
    public static int[] getShuffledDeck(){

        int[] deckOfCards = new int[52];
        int temp;

        for(int i=0; i < deckOfCards.length;i++){
            deckOfCards[i] = (i%13) + 1;
        }
        //Fisher Yates Algorithm. Start by using a decrementing for loop which starts at the end of the array of cards.
        for(int i = deckOfCards.length-1; i>0; i--) {

            //find a random card using Math.random in the array.
            int randomCard = (int) (Math.random() * (i + 1));

            //store the randomly picked card in a temporary variable.
            temp = deckOfCards[randomCard];

            //we now swap the randomly selected card with the last index in the array
            deckOfCards[randomCard] = deckOfCards[i];
            deckOfCards[i] = temp;
        }
        return deckOfCards;
    }

    /*
    This function picks the first five cards from the shuffled deck and if there is only one pair then it returns true,
    this is done by first using a HashSet since it cant have duplicate values we use it increment count every time it
    receives one. Then if cont is only one meaning it one has one pair we return true for this draw.
     */
    public static boolean drawPokerHand(int[] shuffledDeck){
        HashSet<Integer> handOfCards = new HashSet<Integer>();
        int count = 0;
        for(int i=0;i<5;i++){
            if(!handOfCards.add(shuffledDeck[i])) {
                count++;
            }
        }
        return count == 1;
    }

    /*
    The simulateProbability takes a parameter of the number of trails to be performed, and prints the probability and
    the amount of times one pair was drawn in a poker hand.
     */
    public static void simulateProbability(int numberOfTrials){
        int timesPairDrawn = 0;
        for(int i=0;i<numberOfTrials;i++){
            int[] shuffledDeck = getShuffledDeck();
            if(drawPokerHand(shuffledDeck)){
                timesPairDrawn++;
            }
        }

        double probability = ((double)timesPairDrawn/numberOfTrials)*100;
        System.out.println("-".repeat(100));
        System.out.println("Trails Done: "+ numberOfTrials + "\n\nTimes One Pair Successfully Drawn: "+timesPairDrawn);
        System.out.println("\nFinal Probability is " + String.format("%.2f", probability) + "%");
        System.out.println("-".repeat(100));
    }
}