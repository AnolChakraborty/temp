// Vigenere Cipher in java

public final class App {
    static String generateKey(String str, String key) {
        int x = str.length();

        for (int i = 0;; i++) {
            if (x == i)
                i = 0;
            if (key.length() == str.length())
                break;
            key += (key.charAt(i));
        }
        return key;
    }

    static String cipherText(String str, String key) {
        String cipher_text = "";

        for (int i = 0; i < str.length(); i++) {
            int x = (str.charAt(i) + key.charAt(i)) % 26;

            x += 'A';

            cipher_text += (char) (x);
        }
        return cipher_text;
    }

    static String originalText(String cipher_text, String key) {
        String orig_text = "";

        for (int i = 0; i < cipher_text.length() &&
                i < key.length(); i++) {
            // converting in range 0-25
            int x = (cipher_text.charAt(i) -
                    key.charAt(i) + 26) % 26;

            // convert into alphabets(ASCII)
            x += 'A';
            orig_text += (char) (x);
        }
        return orig_text;
    }

    static String LowerToUpper(String s) {
        StringBuffer str = new StringBuffer(s);
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                str.setCharAt(i, Character.toUpperCase(s.charAt(i)));
            }
        }
        s = str.toString();
        return s;
    }

    public static void main(String[] args) throws Exception {

        String Str = "Hello World";
        String Keyword = "tiger";

        String str = LowerToUpper(Str);
        String keyword = LowerToUpper(Keyword);

        String key = generateKey(str, keyword);
        System.err.println("Plain Text: " + str);
        System.out.println("Key: " + keyword);
        String cipher_text = cipherText(str, key);
        System.err.println("Cipher Text: " + cipher_text);
        String orig_text = originalText(cipher_text, key);
        System.out.println("Original Text: " + orig_text);
    }
}
