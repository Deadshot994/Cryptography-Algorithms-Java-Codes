package cryptography;

import java.util.Scanner;

public class Beaufort {
    /**
     * Beaufort Cipher
     * 
     * Encrypt/Decrypt: C = (K - P + 26) mod 26
     * Reciprocal cipher: Same function is used for both encryption and decryption
     */

    // Encrypts/Decrypts using Beaufort Cipher
    public static String beaufortCipher(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char base = isUpper ? 'A' : 'a';
                int textVal = c - base;
                int keyVal = key.charAt(keyIndex % key.length()) - 'a';
                char cipherChar = (char) ((keyVal - textVal + 26) % 26 + base);
                result.append(cipherChar);
                keyIndex++;
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the key: ");
        String key = sc.nextLine();

        System.out.print("Enter the message: ");
        String message = sc.nextLine();

        String encrypted = beaufortCipher(message, key);
        String decrypted = beaufortCipher(encrypted, key); // Same function

        System.out.println("\nEncrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);

        sc.close();
    }
}
