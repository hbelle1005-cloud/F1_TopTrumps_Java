public class Player {
    private final String name;
    private final Hand hand;
    private int roundsWon;

    public Player(String name, Hand hand) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be blank.");
        }
        if (hand == null) {
            throw new IllegalArgumentException("A player must have a hand.");
        }
        this.name = name.trim();
        this.hand = hand;
        this.roundsWon = 0;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void addRoundWin() {
        roundsWon++;
    }

    @Override
    public String toString() {
        return name + " | Cards: " + hand.size() + " | Rounds won: " + roundsWon;
    }
}
