package Project.Yatze;

public class Main {
    public static void main(String[] args) {
        AutoSpiller spiller = new AutoSpiller();

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