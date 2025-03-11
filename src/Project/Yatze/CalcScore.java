package Project.Yatze;

public class CalcScore {
    public static int scoreCategory(int[] dice, int category) {
        return switch (category) {
            case 0 -> sumOfValue(dice, 1); // Ones
            case 1 -> sumOfValue(dice, 2); // Twos
            case 2 -> sumOfValue(dice, 3); // Threes
            case 3 -> sumOfValue(dice, 4); // Fours
            case 4 -> sumOfValue(dice, 5); // Fives
            case 5 -> sumOfValue(dice, 6); // Sixes
            case 6 -> scoreOnePair(dice);
            case 7 -> scoreTwoPairs(dice);
            case 8 -> scoreThreeOfAKind(dice);
            case 9 -> scoreFourOfAKind(dice);
            case 10 -> scoreSmallStraight(dice);
            case 11 -> scoreLargeStraight(dice);
            case 12 -> scoreFullHouse(dice);
            case 13 -> sumAllDice(dice); // Chance
            case 14 -> scoreYatzy();
            default -> 0;
        };
    }

    public static int scoreYatzy() {
        // 50 points for Yatzy
        return 50;
    }

    private static int sumOfValue(int[] dice, int val) {
        int sum = 0;
        for (int d : dice) {
            if (d == val) sum += d;
        }
        return sum;
    }

    private static int sumAllDice(int[] dice) {
        int sum = 0;
        for (int d : dice) {
            sum += d;
        }
        return sum;
    }

    private static int scoreOnePair(int[] dice) {
        int[] counts = countOccurrences(dice);
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 2) {
                return i * 2;
            }
        }
        return 0;
    }

    private static int scoreTwoPairs(int[] dice) {
        int[] counts = countOccurrences(dice);
        int pairsFound = 0;
        int sum = 0;
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 2) {
                pairsFound++;
                sum += i * 2;
                if (pairsFound == 2) break;
            }
        }
        return (pairsFound == 2) ? sum : 0;
    }

    private static int scoreThreeOfAKind(int[] dice) {
        int[] counts = countOccurrences(dice);
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 3) {
                return i * 3;
            }
        }
        return 0;
    }

    private static int scoreFourOfAKind(int[] dice) {
        int[] counts = countOccurrences(dice);
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 4) {
                return i * 4;
            }
        }
        return 0;
    }

    private static int scoreSmallStraight(int[] dice) {
        boolean[] found = new boolean[7];
        for (int d : dice) found[d] = true;
        for (int i = 1; i <= 5; i++) {
            if (!found[i]) return 0;
        }
        return 15;
    }

    private static int scoreLargeStraight(int[] dice) {
        boolean[] found = new boolean[7];
        for (int d : dice) found[d] = true;
        for (int i = 2; i <= 6; i++) {
            if (!found[i]) return 0;
        }
        return 20;
    }

    private static int scoreFullHouse(int[] dice) {
        int[] counts = countOccurrences(dice);
        int pairVal = 0, threeVal = 0;
        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 2) pairVal = i;
            if (counts[i] == 3) threeVal = i;
        }
        if (pairVal > 0 && threeVal > 0) {
            return pairVal * 2 + threeVal * 3;
        }
        return 0;
    }

    private static int[] countOccurrences(int[] dice) {
        int[] counts = new int[7];
        for (int d : dice) {
            counts[d]++;
        }
        return counts;
    }
}