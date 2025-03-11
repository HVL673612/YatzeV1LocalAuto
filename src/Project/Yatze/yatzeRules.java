package Project.Yatze;

public class yatzeRules {
    public static boolean isYatzy(int[] dice) {
        int first = dice[0];
        for (int d : dice) {
            if (d != first) {
                return false;
            }
        }
        return true;
    }
}
