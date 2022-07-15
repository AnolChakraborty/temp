// playfair cipher in java

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

class Playfair {
    String key;
    String plainText;
    char[][] matrix = new char[5][5];

    public Playfair(String key, String plainText) {
        this.key = key.toLowerCase();

        this.plainText = plainText.toLowerCase();
    }

    public void cleanPlayFairKey() {
        LinkedHashSet<Character> set = new LinkedHashSet<Character>();

        String newKey = "";

        for (int i = 0; i < key.length(); i++)
            set.add(key.charAt(i));

        Iterator<Character> it = set.iterator();

        while (it.hasNext())
            newKey += (Character) it.next();

        key = newKey;
    }

    public void generateCipherKey() {
        Set<Character> set = new HashSet<Character>();

        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == 'j')
                continue;
            set.add(key.charAt(i));
        }

        String tempKey = new String(key);

        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 97);
            if (ch == 'j')
                continue;

            if (!set.contains(ch))
                tempKey += ch;
        }

        for (int i = 0, idx = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = tempKey.charAt(idx++);
    }

    public String formatPlainText() {
        String message = "";
        int len = plainText.length();

        for (int i = 0; i < len; i++) {
            if (plainText.charAt(i) == 'j')
                message += 'i';
            else
                message += plainText.charAt(i);
        }

        for (int i = 0; i < message.length(); i += 2) {
            if (message.charAt(i) == message.charAt(i + 1))
                message = message.substring(0, i + 1) + 'x'
                        + message.substring(i + 1);
        }

        if (len % 2 == 1)
            message += 'x'; // dummy character

        return message;
    }

    public String[] formPairs(String message) {
        int len = message.length();
        String[] pairs = new String[len / 2];

        for (int i = 0, cnt = 0; i < len / 2; i++)
            pairs[i] = message.substring(cnt, cnt += 2);

        return pairs;
    }

    public int[] getCharPos(char ch) {
        int[] keyPos = new int[2];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (matrix[i][j] == ch) {
                    keyPos[0] = i;
                    keyPos[1] = j;
                    break;
                }
            }
        }
        return keyPos;
    }

    public String encryptMessage() {
        String message = formatPlainText();
        String[] msgPairs = formPairs(message);
        String encText = "";

        for (int i = 0; i < msgPairs.length; i++) {
            char ch1 = msgPairs[i].charAt(0);
            char ch2 = msgPairs[i].charAt(1);
            int[] ch1Pos = getCharPos(ch1);
            int[] ch2Pos = getCharPos(ch2);

            if (ch1Pos[0] == ch2Pos[0]) {
                ch1Pos[1] = (ch1Pos[1] + 1) % 5;
                ch2Pos[1] = (ch2Pos[1] + 1) % 5;
            }

            else if (ch1Pos[1] == ch2Pos[1]) {
                ch1Pos[0] = (ch1Pos[0] + 1) % 5;
                ch2Pos[0] = (ch2Pos[0] + 1) % 5;
            }

            else {
                int temp = ch1Pos[1];
                ch1Pos[1] = ch2Pos[1];
                ch2Pos[1] = temp;
            }

            encText = encText + matrix[ch1Pos[0]][ch1Pos[1]]
                    + matrix[ch2Pos[0]][ch2Pos[1]];
        }

        return encText;
    }
}

public final class App {

    public static void main(String[] args) {

        String key = "Problem";
        String plainText = "Playfair";

        System.out.println("Key: " + key);
        System.out.println("PlainText: " + plainText);

        Playfair pfc1 = new Playfair(key, plainText);
        pfc1.cleanPlayFairKey();
        pfc1.generateCipherKey();
        String encText1 = pfc1.encryptMessage();

        System.out.println("Cipher Text is: " + encText1);
    }
}
