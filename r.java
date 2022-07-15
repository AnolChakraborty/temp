// Rail Fence cipher in java


public final class App {

    public static class RailFenceCipher {
        int numRails;

        public RailFenceCipher(int numRails) {
            this.numRails = numRails;
        }

        String getDecryptedData(String data) {
            char[] decrypted = new char[data.length()];
            int n = 0;
            for (int k = 0; k < numRails; k++) {
                int index = k;
                boolean down = true;
                while (index < data.length()) {
                    decrypted[index] = data.charAt(n++);

                    if (k == 0 || k == numRails - 1) {
                        index = index + 2 * (numRails - 1);
                    } else if (down) {
                        index = index + 2 * (numRails - k - 1);
                        down = !down;
                    } else {
                        index = index + 2 * k;
                        down = !down;
                    }
                }
            }
            return new String(decrypted);
        }

        String getEncryptedData(String data) {
            char[] encrypted = new char[data.length()];
            int n = 0;

            for (int k = 0; k < numRails; k++) {
                int index = k;
                boolean down = true;
                while (index < data.length()) {
                    encrypted[n++] = data.charAt(index);

                    if (k == 0 || k == numRails - 1) {
                        index = index + 2 * (numRails - 1);
                    } else if (down) {
                        index = index + 2 * (numRails - k - 1);
                        down = !down;
                    } else {
                        index = index + 2 * k;
                        down = !down;
                    }
                }
            }
            return new String(encrypted);
        }

        String getEncryptedData2(String data) {
            StringBuffer[] sb = new StringBuffer[numRails];
            for (int i = 0; i < numRails; i++) {
                sb[i] = new StringBuffer();
            }
            int index = 0;
            int direction = 1;
            for (int i = 0; i < data.length(); i++) {
                sb[index].append(data.charAt(i));
                index = index + direction;
                if (index == 0) {
                    direction = 1;
                } else if (index == numRails) {
                    if (index == 2) {
                        index = 0;
                    } else {
                        direction = -1;
                        index = numRails - 2;
                    }
                }
            }
            for (int i = 1; i < numRails; i++) {
                sb[0].append(sb[i].toString());
            }
            return sb[0].toString();
        }

        public static void main(String[] args) throws Exception {

            String data = "Hello World";
            int rails = 4;

            RailFenceCipher railFenceCipher = new RailFenceCipher(rails);

            System.out.println("Plain Text: " + data);
            System.out.println("Key: " + rails);
            System.out.println("Encrypted Text: " + railFenceCipher.getEncryptedData(data));
        }
    }
}
