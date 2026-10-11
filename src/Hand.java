import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A player's cards, with the next card stored at index 0.
 */
public class Hand {
    private final ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(List<Card> startingCards) {
        cards = new ArrayList<>(startingCards);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card peekTopCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.get(0);
    }

    public Card drawTopCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public void addToBottom(Card card) {
        if (card != null) {
            cards.add(card);
        }
    }

    public void addAllToBottom(List<Card> newCards) {
        cards.addAll(newCards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
