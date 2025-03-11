package Project.Yatze;

import java.util.Arrays;

public class AutoSpiller {
    private int currentCategory = 0;
    private int totalScore = 0;
    private static final int MAX_CATEGORIES = 15;
    private static final int MAX_ROLLS = 3;

    public void playRound() {
        if (currentCategory >= MAX_CATEGORIES) {
            System.out.println("All categories used.\nTotal Score: " + totalScore);
            return;
        }
        int[] dice = new int[5];
        // First roll
        for (int i = 0; i < dice.length; i++) {
            dice[i] = (int) (Math.random() * 6) + 1;
        }
        System.out.println("First roll: " + Arrays.toString(dice));

        // Up to two more rolls
        int rollsCount = 1;
        while (rollsCount < MAX_ROLLS) {
            // Decide which dice to hold automatically
            boolean[] hold = decideHolds(dice, currentCategory);

            // Re-roll unheld dice
            for (int i = 0; i < dice.length; i++) {
                if (!hold[i]) {
                    dice[i] = (int) (Math.random() * 6) + 1;
                }
            }
            rollsCount++;
            System.out.println("Roll " + rollsCount + ": " + Arrays.toString(dice));

            // If we already have Yatzy, break early
            if (yatzeRules.isYatzy(dice)) {
                System.out.println("Yatzy on roll " + rollsCount + "!");
                break;
            }
        }

        // Check final dice
        if (yatzeRules.isYatzy(dice)) {
            System.out.println("Skipping category " + currentCategory + " due to Yatzy!");
            totalScore += CalcScore.scoreYatzy();
        } else {
            int points = CalcScore.scoreCategory(dice, currentCategory);
            totalScore += points;
            System.out.println("Category " + currentCategory + " score: " + points);
            currentCategory++;
        }
    }

    // Decide which dice to hold for the given category
    private boolean[] decideHolds(int[] dice, int cat) {
        boolean[] hold = new boolean[dice.length];
        int[] counts = countOccurrences(dice);

        // Always hold 3\+ of a kind
        for (int face = 1; face <= 6; face++) {
            if (counts[face] >= 3) {
                for (int i = 0; i < dice.length; i++) {
                    if (dice[i] == face) {
                        hold[i] = true;
                    }
                }
            }
        }

        // Hold dice matching the category's face (e.g.\ cat=0 means hold the 1s)
        if (cat >= 0 && cat <= 5) {
            int faceVal = cat + 1;
            for (int i = 0; i < dice.length; i++) {
                if (dice[i] == faceVal) {
                    hold[i] = true;
                }
            }
        }
        return hold;
    }

    private int[] countOccurrences(int[] dice) {
        int[] counts = new int[7];
        for (int d : dice) {
            counts[d]++;
        }
        return counts;
    }

    public int getCurrentCategory() {
        return currentCategory;
    }

    public int getTotalScore() {
        return totalScore;
    }
}