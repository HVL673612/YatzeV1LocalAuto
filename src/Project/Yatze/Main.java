package Project.Yatze;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Spiller spiller = new Spiller();

        while (true) {
            // If all categories have been used, break out
            if (spiller.getCurrentCategory() >= 15) {
                System.out.println("All categories used.\nTotal Score: " + spiller.getTotalScore());
                break;
            }
            spiller.playRound();
        }
    }
}