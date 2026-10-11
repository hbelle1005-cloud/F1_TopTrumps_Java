# F1 Top Trumps: Grid War

A two-player, terminal-based Java card game inspired by Top Trumps. It uses an original set of driver cards, extra categories, and a simple tie-pot rule.

## Requirements

- Java JDK 17 or newer recommended
- A terminal / command prompt
- Git (optional, for GitHub)

## Run locally

From the project folder:

```bash
javac -d out src/*.java
java -cp out Main
```

On Windows PowerShell, those commands work as written if Java is on your PATH. Check with:

```bash
java -version
javac -version
```

## How to play

1. Start the game and enter two player names.
2. The 30-card deck is shuffled and split into 15 cards per player.
3. The active player chooses one category from their own top card.
4. Both top-card ratings are compared. The higher rating wins both cards and any cards in the tie pot.
5. A tie puts both cards into a pot. The next round's winner takes the pot too.
6. Players alternate choosing categories.
7. The game ends when one player runs out of cards.

## Categories

All ratings are 1–100 game-balance scores:

- Racecraft
- Qualifying
- Consistency
- Overtaking
- Wet Weather
- Tire Management
- Adaptability
- Peak Performance

These are subjective gameplay ratings, not official FIA statistics. They are intended to create different strengths and interesting matchups.

## Project structure

- `Card.java` — driver name and category ratings
- `Hand.java` — holds cards and manages the top/bottom of a hand
- `Player.java` — player name, hand, and rounds won
- `Round.java` — round comparison and tie-pot support
- `Main.java` — deck setup, terminal prompts, and game loop

The `Round` class is kept as a separate class to match the class organization in the original project outline.

## Quick test checklist

- [ ] Both players can enter names.
- [ ] A blank name is rejected.
- [ ] Deck has exactly 30 cards and is split 15/15.
- [ ] Entering an invalid category choice (such as `0` or `abc`) asks again.
- [ ] A win transfers both cards to the winner's hand.
- [ ] A tie carries both cards into the next round's pot.
- [ ] Players alternate category selection.
- [ ] The game ends when a player has no cards.

## Upload to GitHub

1. Sign in to GitHub and create a new repository, for example `f1-top-trumps-java`.
2. Do not add a README on GitHub if you already have this local README (or choose to merge them if you do).
3. Open a terminal in this project folder.
4. Run the following commands, replacing `YOUR-USERNAME` with your GitHub username:

```bash
git init
git add .
git commit -m "Add F1 Top Trumps terminal game"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/f1-top-trumps-java.git
git push -u origin main
```

If Git says your identity is not configured, set it once:

```bash
git config --global user.name "Your Name"
git config --global user.email "your-email@example.com"
```

If `origin` already exists, check it with `git remote -v`; update it with `git remote set-url origin https://github.com/YOUR-USERNAME/f1-top-trumps-java.git`.

### Update GitHub after later changes

```bash
git add .
git commit -m "Describe your change"
git push
```

## Notes

The current-driver selection follows the published 2026 F1 grid. The eight legacy drivers are included to add variety. The stats are custom balancing values for this game, rather than claims about exact performance measurements.
