package Project.Yatze;

import java.util.Arrays;

public class Spiller {
    private int currentCategory = 0;
    private int totalScore = 0;
    private static final int MAX_CATEGORIES = 15;

    public void playRound() {
        if (currentCategory >= MAX_CATEGORIES) {
            System.out.println("All categories used.\nTotal Score: " + totalScore);
            return;
        }
        int[] dice = Roll5Dice();
        System.out.println("Dice: " + Arrays.toString(dice));

        if (yatzeRules.isYatzy(dice)) {
            System.out.println("Yatzy! Skipping category " + currentCategory);
            totalScore += CalcScore.scoreYatzy(dice);
        } else {
            int points = CalcScore.scoreCategory(dice, currentCategory);
            totalScore += points;
            System.out.println("Category " + currentCategory + " score: " + points);
            currentCategory++;
        }
    }

    public int getCurrentCategory() {
        return currentCategory;
    }

    public int getTotalScore() {
        return totalScore;
    }

    private int[] Roll5Dice() {
        int[] dice = new int[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = (int) (Math.random() * 6) + 1;
        }
        return dice;
    }
}