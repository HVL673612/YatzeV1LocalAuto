package Project.Yatze;

public class CalcScore {
    public static int scoreCategory(int[] dice, int category) {
        switch (category) {
            case 0: return sumOfValue(dice, 1); // Ones
            case 1: return sumOfValue(dice, 2); // Twos
            case 2: return sumOfValue(dice, 3); // Threes
            case 3: return sumOfValue(dice, 4); // Fours
            case 4: return sumOfValue(dice, 5); // Fives
            case 5: return sumOfValue(dice, 6); // Sixes
            case 6: return scoreOnePair(dice);
            case 7: return scoreTwoPairs(dice);
            case 8: return scoreThreeOfAKind(dice);
            case 9: return scoreFourOfAKind(dice);
            case 10: return scoreSmallStraight(dice);
            case 11: return scoreLargeStraight(dice);
            case 12: return scoreFullHouse(dice);
            case 13: return sumAllDice(dice); // Chance
            case 14: return scoreYatzy(dice);
            default: return 0;
        }
    }

    public static int scoreYatzy(int[] dice) {
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