import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("       F1 TOP TRUMPS: GRID WAR");
        System.out.println("====================================");

        System.out.print("Player 1, enter your name: ");
        String name1 = readNonBlankName();
        System.out.print("Player 2, enter your name: ");
        String name2 = readNonBlankName();

        List<Card> deck = createDeck();
        Collections.shuffle(deck);

        List<Card> cards1 = new ArrayList<>(deck.subList(0, 15));
        List<Card> cards2 = new ArrayList<>(deck.subList(15, 30));

        Player player1 = new Player(name1, new Hand(cards1));
        Player player2 = new Player(name2, new Hand(cards2));

        System.out.println("\nThe deck has been shuffled and split.");
        System.out.println(player1.getName() + " starts with 15 cards.");
        System.out.println(player2.getName() + " starts with 15 cards.");
        System.out.println("\nRules:");
        System.out.println("- The active player chooses a category from their top card.");
        System.out.println("- The higher rating wins both cards and any tied-card pot.");
        System.out.println("- A tie carries the cards into the next round.");
        System.out.println("- The player that wins the previous round chooses the category.");
        System.out.println("- The game ends when a player has no cards left.\n");
        pause();

        List<Card> pot = new ArrayList<>();
        int roundNumber = 1;
        Player chooser = player1;

        while (!player1.getHand().isEmpty() && !player2.getHand().isEmpty()) {
            clearLine();
            System.out.println("--------------- ROUND " + roundNumber + " ---------------");
            System.out.println(player1);
            System.out.println(player2);
            if (!pot.isEmpty()) {
                System.out.println("Tied-card pot: " + pot.size() + " card(s)");
            }

            Card top1 = player1.getHand().peekTopCard();
            Card top2 = player2.getHand().peekTopCard();

            if (chooser.getHand().isEmpty()) {
                chooser = chooser == player1 ? player2 : player1;
            }

            // Only show the active player's card before the choice.
            Card chooserTopCard = chooser == player1 ? top1 : top2;

            System.out.println("\n" + chooser.getName() + "'s top card:");
            System.out.println(chooserTopCard);

            if (chooser.getHand().isEmpty()) {
                chooser = chooser == player1 ? player2 : player1;
            }
            System.out.println(chooser.getName() + ", choose a category:");
            String[] categories = Card.getCategories();
            for (int i = 0; i < categories.length; i++) {
                System.out.printf("%d. %s%n", i + 1, categories[i]);
            }
            int choice = readIntInRange(1, categories.length);
            String category = categories[choice - 1];

                        
            System.out.println("\nCards revealed!");
            System.out.println(player1.getName() + "'s card: "
                    + top1.getName());
            System.out.println(player2.getName() + "'s card: "
                    + top2.getName());

            System.out.println("\nCategory: " + category);
            System.out.println(player1.getName() + ": "
                    + top1.getRating(category));
            System.out.println(player2.getName() + ": "
                    + top2.getRating(category));


            Round round = new Round(player1, player2, category, pot);
            int comparison = round.compareTo(top1, top2);
            pot.add(player1.getHand().drawTopCard());
            pot.add(player2.getHand().drawTopCard());

            if (comparison > 0) {
                player1.getHand().addAllToBottom(pot);
                System.out.println("\n" + player1.getName()
                        + " wins the round and takes "
                        + pot.size() + " card(s)!");

                player1.addRoundWin();
                chooser = player1;
                pot = new ArrayList<>();

            } else if (comparison < 0) {
                player2.getHand().addAllToBottom(pot);
                System.out.println("\n" + player2.getName()
                        + " wins the round and takes "
                        + pot.size() + " card(s)!");

                player2.addRoundWin();
                chooser = player2;
                pot = new ArrayList<>();

            } else {
                System.out.println("\nIt's a tie! Those cards stay in the pot.");
                System.out.println(chooser.getName()
                        + " gets to choose again next round.");
            }


            System.out.println("\nCards now: " + player1.getName() + " = "
                    + player1.getHand().size() + " | " + player2.getName() + " = "
                    + player2.getHand().size());
            roundNumber++;

            if (!player1.getHand().isEmpty() && !player2.getHand().isEmpty()) {
                pause();
            }
        }

        clearLine();
        System.out.println("\n=============== GAME OVER ===============");
        System.out.println(player1);
        System.out.println(player2);
        if (player1.getHand().isEmpty() && player2.getHand().isEmpty()) {
            System.out.println("It's a draw! Both players ran out of cards.");
        } else if (player1.getHand().isEmpty()) {
            System.out.println(player2.getName() + " wins the game!");
        } else {
            System.out.println(player1.getName() + " wins the game!");
        }
        System.out.println("Thanks for playing F1 Top Trumps: Grid War.");
    }

    private static String readNonBlankName() {
        while (true) {
            String name = INPUT.nextLine().trim();
            if (!name.isEmpty()) return name;
            System.out.print("Name cannot be blank. Try again: ");
        }
    }

    private static int readIntInRange(int min, int max) {
        while (true) {
            System.out.print("> ");
            String line = INPUT.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {
                // Show the same helpful message below.
            }
            System.out.println("Enter a whole number from " + min + " to " + max + ".");
        }
    }

    private static void pause() {
        System.out.print("\nPress Enter to continue...");
        INPUT.nextLine();
    }

    private static void clearLine() {
        // Portable terminal-friendly separator instead of an OS-specific clear command.
        System.out.println("\n\n");
    }

    public static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        // 2026 grid: ratings are intentionally designed for game balance, not official statistics.
        deck.add(new Card("Lando Norris", "McLaren - 2026", 91, 94, 90, 88, 87, 88, 91, 94));
        deck.add(new Card("Oscar Piastri", "McLaren - 2026", 90, 92, 91, 89, 86, 90, 92, 93));
        deck.add(new Card("Max Verstappen", "Red Bull - 2026", 98, 96, 96, 97, 96, 93, 95, 99));
        deck.add(new Card("Isack Hadjar", "Red Bull - 2026", 80, 82, 76, 81, 75, 78, 84, 83));
        deck.add(new Card("George Russell", "Mercedes - 2026", 89, 93, 89, 85, 86, 88, 91, 91));
        deck.add(new Card("Kimi Antonelli", "Mercedes - 2026", 82, 87, 78, 84, 79, 81, 88, 87));
        deck.add(new Card("Charles Leclerc", "Ferrari - 2026", 91, 98, 85, 91, 89, 82, 90, 96));
        deck.add(new Card("Lewis Hamilton", "Ferrari - 2026", 96, 92, 94, 96, 99, 97, 98, 97));
        deck.add(new Card("Carlos Sainz", "Williams - 2026", 90, 89, 93, 88, 86, 94, 94, 90));
        deck.add(new Card("Alexander Albon", "Williams - 2026", 86, 87, 88, 87, 85, 89, 91, 87));
        deck.add(new Card("Fernando Alonso", "Aston Martin - 2026", 98, 90, 95, 97, 97, 98, 99, 96));
        deck.add(new Card("Lance Stroll", "Aston Martin - 2026", 78, 80, 76, 79, 78, 79, 80, 81));
        deck.add(new Card("Sergio Perez", "Cadillac - 2026", 89, 85, 88, 90, 86, 96, 88, 89));
        deck.add(new Card("Valtteri Bottas", "Cadillac - 2026", 88, 92, 91, 84, 85, 91, 88, 90));
        deck.add(new Card("Pierre Gasly", "Alpine - 2026", 85, 88, 86, 86, 84, 85, 88, 87));
        deck.add(new Card("Franco Colapinto", "Alpine - 2026", 78, 81, 74, 80, 75, 77, 82, 81));
        deck.add(new Card("Esteban Ocon", "Haas - 2026", 86, 84, 86, 87, 85, 84, 88, 85));
        deck.add(new Card("Oliver Bearman", "Haas - 2026", 82, 85, 78, 84, 80, 82, 87, 86));
        deck.add(new Card("Nico Hulkenberg", "Audi - 2026", 87, 89, 91, 85, 87, 90, 90, 88));
        deck.add(new Card("Gabriel Bortoleto", "Audi - 2026", 79, 83, 77, 80, 78, 81, 85, 83));
        deck.add(new Card("Liam Lawson", "Racing Bulls - 2026", 80, 82, 77, 83, 78, 80, 83, 82));
        deck.add(new Card("Arvid Lindblad", "Racing Bulls - 2026", 76, 82, 72, 80, 74, 77, 85, 82));

        // Eight legacy cards, with strengths spread across categories to keep matchups varied.
        deck.add(new Card("Michael Schumacher", "Legacy - 1991-2006", 98, 96, 96, 95, 94, 97, 96, 99));
        deck.add(new Card("Sebastian Vettel", "Legacy - 2007-2022", 94, 94, 93, 91, 90, 93, 92, 97));
        deck.add(new Card("Ayrton Senna", "Legacy - 1984-1994", 96, 99, 90, 95, 100, 88, 94, 99));
        deck.add(new Card("Alain Prost", "Legacy - 1980-1993", 95, 93, 99, 90, 92, 100, 97, 96));
        deck.add(new Card("Niki Lauda", "Legacy - 1971-1985", 93, 91, 98, 88, 89, 99, 98, 94));
        deck.add(new Card("Jackie Stewart", "Legacy - 1965-1973", 92, 90, 96, 89, 91, 94, 97, 93));
        deck.add(new Card("Kimi Raikkonen", "Legacy - 2001-2021", 91, 94, 88, 91, 87, 95, 90, 95));
        deck.add(new Card("Mika Hakkinen", "Legacy - 1991-2001", 93, 96, 91, 90, 91, 91, 92, 96));
        if (deck.size() != 30) {
            throw new IllegalStateException("The deck must contain exactly 30 cards.");
        }
        return deck;
    }
}
