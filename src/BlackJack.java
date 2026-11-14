import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;








public class BlackJack {
    private class Card {
        String value;
        String type;
    


        Card(String value, String type) {
            this.value = value;
            this.type = type;
        }


        public String toString() { //this method tells how the card object would look when i print it
            return value + "-" + type;

        }

        public int getValue() {
            if ("AJQK".contains(value) ) { //A J Q K
                if (value == "A") {
                return 11;
                }
                return 10;
            }
            return Integer.parseInt(value); //2-10
        }   


        public boolean isAce() {
            return value == "A";
        }




}


ArrayList<Card> deck; // ArrayList to hold the deck of cards
Random random = new Random(); //this shuffles the deck

//section for the dealer =
Card hiddenCard;
ArrayList<Card> dealerHand;
int dealerSum; //Keeps track of the dealers card
int dealerAceCount; //dealer aces





BlackJack() {
    startGame();
}

public void startGame() {
   createDeck();
    //deck for the game
    shuffleDeck(); 
    //shuffles deck
    //in order to shuffle, we need to randomize it which we do in the code above

    //cahnge the value of ace 11 to 1
    dealerHand = new ArrayList<Card>();
    dealerSum = 0;
    dealerAceCount = 0;

    // starts the dealer off with one card

    hiddenCard = deck.remove(deck.size()-1); // removes card at last index
    dealerSum += hiddenCard.getValue();
    dealerAceCount += hiddenCard.isAce() ? 1 : 0; //chcecks if dealer card first is ace

    Card card = deck.remove(deck.size()-1); //draws out the visible card
    dealerSum += card.getValue();
    dealerAceCount += card.isAce () ? 1: 0;
    dealerHand.add(card);


    System.out.println("DEALER:");
    System.out.println(hiddenCard);
    System.out.println(dealerHand);
    System.out.println(dealerSum);
    System.out.println(dealerAceCount);
}

    






public void createDeck() {
        deck = new ArrayList<Card>();
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; // Card values 
        String[] types = {"C", "D", "H", "S"}; // Clubs, Diamonds, Hearts, Spades

        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < values.length; j++) {
                Card card = new Card (values[j], types[i]);
                deck.add(card);
            }
        }

        System.out.println("Building deck of cards...");
        System.out.println(deck);

    }



    public void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
            
        }
        System.out.println("AFTER SHUFFLE");
        System.out.println(deck);
    }
}