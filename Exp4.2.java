import java.util.*;

class Card {
    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    public String getSuit() {
        return suit;
    }
}

public class CardCollectionSystem {
    private List<Card> cardList;
    private Map<String, List<Card>> cardMap;

    public CardCollectionSystem() {
        cardList = new ArrayList<>();
        cardMap = new HashMap<>();
    }

    public void addCard(String suit, String value) {
        Card newCard = new Card(suit, value);
        if (cardList.contains(newCard)) {
            System.out.println("Error: Card \"" + newCard + "\" already exists.");
            return;
        }
        cardList.add(newCard);
        cardMap.computeIfAbsent(suit, k -> new ArrayList<>()).add(newCard);
        System.out.println("Card added: " + newCard);
    }

    public void findCardsBySuit(String suit) {
        List<Card> cards = cardMap.get(suit);
        if (cards == null || cards.isEmpty()) {
            System.out.println("No cards found for " + suit + ".");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }

    public void displayAllCards() {
        if (cardList.isEmpty()) {
            System.out.println("No cards found.");
        } else {
            for (Card card : cardList) {
                System.out.println(card);
            }
        }
    }

    public void removeCard(String suit, String value) {
        Card cardToRemove = new Card(suit, value);
        if (cardList.remove(cardToRemove)) {
            cardMap.get(suit).remove(cardToRemove);
            System.out.println("Card removed: " + cardToRemove);
        } else {
            System.out.println("Error: Card \"" + cardToRemove + "\" not found.");
        }
    }

    public static void main(String[] args) {
        CardCollectionSystem cardSystem = new CardCollectionSystem();
        cardSystem.displayAllCards();
        cardSystem.addCard("Spades", "Ace");
        cardSystem.addCard("Hearts", "King");
        cardSystem.addCard("Diamonds", "10");
        cardSystem.addCard("Clubs", "5");
        cardSystem.findCardsBySuit("Hearts");
        cardSystem.findCardsBySuit("Diamonds");
        cardSystem.displayAllCards();
        cardSystem.addCard("Hearts", "King");
        cardSystem.removeCard("Diamonds", "10");
    }
}
