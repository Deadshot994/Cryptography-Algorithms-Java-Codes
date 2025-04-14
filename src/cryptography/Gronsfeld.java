package cryptography;

import java.util.Scanner;

public class Gronsfeld {
    /**
	 * Gronsfeld Cipher
	 * 
     * Encrypt: C = (P + K) mod 26 
     * Decrypt: P = (C - K) mod 26
     * where K => digits from 0 - 9
	 */ 

    // Encrypts using Gronsfeld Cipher
    public static String encrypt(String plaintext, String key) {
        StringBuilder encrypted = new StringBuilder();
        int keyIndex = 0;

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char base = isUpper ? 'A' : 'a';
                int shift = Character.getNumericValue(key.charAt(keyIndex % key.length()));
                int newChar = (c - base + shift) % 26 + base;
                encrypted.append((char) newChar);
                keyIndex++;
            } else {
                encrypted.append(c); // Preserve spaces and punctuation
            }
        }

        return encrypted.toString();
    }

    // Decrypts using Gronsfeld Cipher
    public static String decrypt(String ciphertext, String key) {
        StringBuilder decrypted = new StringBuilder();
        int keyIndex = 0;

        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char base = isUpper ? 'A' : 'a';
                int shift = Character.getNumericValue(key.charAt(keyIndex % key.length()));
                int newChar = (c - base - shift + 26) % 26 + base;
                decrypted.append((char) newChar);
                keyIndex++;
            } else {
                decrypted.append(c); // Preserve spaces and punctuation
            }
        }

        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the numerical key (digits only 0-9): ");
        String key = sc.nextLine();

        // Validate key
        if (!key.matches("[0-9]+")) {
            System.out.println("Invalid key. Only digits 0-9 are allowed.");
            sc.close();
            return ;
        }

        System.out.print("Enter the message: ");
        String message = sc.nextLine();

        String encrypted = encrypt(message, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nEncrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);

        sc.close();
    }
}
