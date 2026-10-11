import java.util.ArrayList;
import java.util.List;

/**
 * Resolves one round. Tied cards go into a pot and the next round's
 * winner takes the whole pot.
 */
public class Round {
    private final Player player1;
    private final Player player2;
    private final String category;
    private final List<Card> pot;

    public Round(Player player1, Player player2, String category, List<Card> existingPot) {
        this.player1 = player1;
        this.player2 = player2;
        this.category = category;
        this.pot = new ArrayList<>(existingPot);
    }

    public List<Card> getPot() {
        return new ArrayList<>(pot);
    }

    public void addToPot(Card card) {
        pot.add(card);
    }

    public String getCategory() {
        return category;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int compareTo(Card card1, Card card2) {
        return Integer.compare(card1.getRating(category), card2.getRating(category));
    }

    public Player getRoundWinner() {
        if (player1.getHand().isEmpty() || player2.getHand().isEmpty()) {
            return null;
        }
        Card card1 = player1.getHand().peekTopCard();
        Card card2 = player2.getHand().peekTopCard();
        int comparison = compareTo(card1, card2);
        if (comparison > 0) return player1;
        if (comparison < 0) return player2;
        return null;
    }

    public List<Card> resolve() {
        Card card1 = player1.getHand().drawTopCard();
        Card card2 = player2.getHand().drawTopCard();
        pot.add(card1);
        pot.add(card2);

        int comparison = compareTo(card1, card2);
        if (comparison > 0) {
            player1.getHand().addAllToBottom(pot);
            player1.addRoundWin();
            return new ArrayList<>();
        } else if (comparison < 0) {
            player2.getHand().addAllToBottom(pot);
            player2.addRoundWin();
            return new ArrayList<>();
        }
        return getPot();
    }
}
