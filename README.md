# F1 Top Trumps

A two-player, terminal-based Top Trumps game built in Java, featuring 30 Formula 1 drivers from the current grid and F1 history.

## About the Project

This project started during my junior year of high school, when I was first learning Java and object-oriented programming. My family had gotten me the real F1 Top Trumps game, and I was determined to recreate it myself. I outlined the code and built the game over the course of a few weeks.

Unfortunately, after graduating, I lost the original code. Recently, while looking through my high school work, I rediscovered my outline and decided to use AI to help me rebuild the project and refine it along the way.

Working on this project gave me a chance to refresh my Java skills, revisit object-oriented programming, and strengthen my skills in AI-assisted coding and prompt engineering. It was also a fun way to revisit an old project and see how I could improve on my original idea with what I've learned since then.

## Features

- 30 unique driver cards
- 8 comparison categories, including Racecraft, Qualifying, and Tire Management
- Shuffled deck split between two players
- Winner chooses the category for the next round
- Tied rounds carry cards into a pot that the next winner collects

## How to Play

1. Start the game and receive your cards.
2. The current player views their top card and chooses a category.
3. The drivers' ratings are compared.
4. The player with the higher rating wins the round and collects the cards.
5. The winner chooses the category for the next round.
6. The game continues until one player runs out of cards.

## Built With

- Java
- Object-oriented programming
- Java collections (`ArrayList`)

## How to Run

Make sure you have the Java Development Kit (JDK) installed.

1. Clone or download this repository.
2. Open a terminal in the project folder.
3. Compile the Java files:

   ```bash
   javac *.java
   ```

4. Run the game:

   ```bash
   java Main
   ```

## Project Structure

- `Main.java` — Runs the game and manages the rounds.
- `Card.java` — Stores driver information and category ratings.
- `Hand.java` — Manages each player's cards.
- `Player.java` — Represents each player.
- `Round.java` — Handles round comparisons and determines the winner.

## AI Tools & Process

I used ChatGPT (GPT-6) as an AI-assisted coding tool to help reconstruct and refine the project based on my original outline. I guided the design decisions and used iterative prompting to improve the implementation and game mechanics.
