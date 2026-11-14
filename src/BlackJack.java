import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;








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

//player
ArrayList<Card> playerHand;
int playerSum;
int playerAceCount;

//window for the game

int boardWidth = 600;
int boardHeight = boardWidth;
int cardWidth = 110;  //ration should be 1/1.4
int cardHeight = 154; 

JFrame frame = new JFrame ("Black Jack");
JPanel gamePanel = new JPanel();
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //draw hidden card
        Image hiddenCardImg = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
        g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);
    }
JPanel buttonPanel = new JPanel();
JButton hitButton = new JButton("Hit");
JButton standButton = new JButton("Stand");

BlackJack() {
    startGame();

    frame.setVisible(true);
    frame.setSize(boardWidth, boardHeight);
    frame.setLocationRelativeTo(null); //prevents the frame from starting on the corner
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    gamePanel.setLayout(new BorderLayout());
    gamePanel.setBackground(new Color(50, 205, 114));
    frame.add(gamePanel);

    hitButton.setFocusable(false);
    buttonPanel.add(hitButton);
    standButton.setFocusable(false);
    buttonPanel.add(standButton);
    frame.add(buttonPanel, BorderLayout.SOUTH);

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




    //gives 2 cards to the playerr
    playerHand = new ArrayList<Card>();
    playerSum = 0;
    playerAceCount = 0;

    for (int i = 0; i < 2; i++) {
        card = deck.remove(deck.size()-1);
        playerSum += card.getValue();
        playerAceCount += card.isAce() ? 1:0;
        playerHand.add(card);
//finally figured out that for is a loop
    }
    System.out.println("PLAYER: ");
    System.out.println(playerHand);
    System.out.println(playerSum);
    System.out.println(playerAceCount);


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