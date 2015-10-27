import java.util.Random;

public class Main {

    static enum CharType {
        none, kons, vokal
    }

    final static int MIN_LENGTH = 4;
    final static int MAX_LENGTH = 8;
    final static int NUMBER_OF_SUGGESTIONS = 60;

    static final char vokal[] = { 'a', 'e', 'i', 'o', 'u', 'y' };
    static final char kons[] = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v',
            'w', 'x', 'z' };
    static final int noOfChars = kons.length + vokal.length;

    static CharType lastChar = CharType.none;
    static CharType lastLastChar = CharType.none;

    static Random random = new Random();

    public static void main(String args[]) {
        StringBuilder sb = new StringBuilder(7 * NUMBER_OF_SUGGESTIONS);
        for (int i = 1; i <= NUMBER_OF_SUGGESTIONS; i++) {
            sb.append(String.format("%-10s", genName()));
            if (i % 4 == 0) {
                sb.append('\n');
            }
        }

        System.out.println(sb.toString());
    }

    static String genName() {
        int length = MIN_LENGTH + random.nextInt(MAX_LENGTH - MIN_LENGTH + 1);
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(getChar());
        }
        return sb.toString();
    }

    static char getChar() {
        char returnChar;
        if (CharType.vokal == lastChar) {
            returnChar = getCharFromType(CharType.kons);
        } else if (CharType.kons == lastLastChar && CharType.kons == lastChar) {
            returnChar = getCharFromType(CharType.vokal);
        } else if (CharType.none == lastLastChar && CharType.kons == lastChar) {
            returnChar = getCharFromType(CharType.vokal);
        } else {
            int number = random.nextInt(noOfChars);
            if (number < vokal.length) {
                returnChar = getCharFromType(CharType.vokal);
            } else {
                returnChar = getCharFromType(CharType.kons);
            }
        }

        lastLastChar = lastChar;
        lastChar = getCharType(returnChar);
        return returnChar;
    }

    static char getCharFromType(CharType aCharType) {
        if (aCharType == CharType.kons) {
            return kons[random.nextInt(kons.length)];
        } else {
            return vokal[random.nextInt(vokal.length)];
        }
    }

    static CharType getCharType(char aChar) {
        for (int i = 0; i < kons.length; i++) {
            if (kons[i] == aChar) {
                return CharType.kons;
            }
        }
        for (int i = 0; i < vokal.length; i++) {
            if (vokal[i] == aChar) {
                return CharType.vokal;
            }
        }
        return CharType.none;
    }

}