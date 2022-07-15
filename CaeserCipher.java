public final class App {

    private static String CaeserCipher(String input, int key) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c)) {
                output += (char) ((c + key - 'A') % 26 + 'A');
            } else if (Character.isLowerCase(c)) {
                output += (char) ((c + key - 'a') % 26 + 'a');
            } else {
                output += c;
            }
        }
        return output;
    }

    public static void main(String[] args) {

        String input = "Hello World";
        int key = 3;
        System.out.println("Plain Text: " + input);
        System.out.println("Key: " + key);
        System.out.println("Cipher Text: " + CaeserCipher(input, key));

    }
}
