package cryptography;

import java.util.Scanner;

public class Autoclave {
    /**
     * Autoclave Cipher
     * 
     * Encryption: C = (P + K) mod 26
     * Decryption: P = (C - K + 26) mod 26
     * Key: Starts with initial key, then appends plaintext (for encryption) or decrypted text (for decryption)
     */

    // Encrypts using Autoclave Cipher
    public static String encrypt(String plaintext, String key) {
        StringBuilder encrypted = new StringBuilder();
        StringBuilder fullKey = new StringBuilder(key.toLowerCase());
        plaintext = plaintext.toLowerCase();

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                char keyChar = fullKey.charAt(0);
                int pVal = c - 'a';
                int kVal = keyChar - 'a';
                char encryptedChar = (char) ((pVal + kVal) % 26 + 'a');
                encrypted.append(encryptedChar);
                fullKey.append(c); // Append current plaintext to key
                fullKey.deleteCharAt(0); // Maintain proper shifting
            } else {
                encrypted.append(c);
            }
        }

        return encrypted.toString();
    }

    // Decrypts using Autoclave Cipher
    public static String decrypt(String ciphertext, String key) {
        StringBuilder decrypted = new StringBuilder();
        StringBuilder fullKey = new StringBuilder(key.toLowerCase());

        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                char keyChar = fullKey.charAt(0);
                int cVal = c - 'a';
                int kVal = keyChar - 'a';
                char decryptedChar = (char) ((cVal - kVal + 26) % 26 + 'a');
                decrypted.append(decryptedChar);
                fullKey.append(decryptedChar); // Append current decrypted to key
                fullKey.deleteCharAt(0); // Maintain shifting
            } else {
                decrypted.append(c);
            }
        }

        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the initial key: ");
        String key = sc.nextLine();

        System.out.print("Enter the message: ");
        String message = sc.nextLine();

        String encrypted = encrypt(message, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nEncrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);

        sc.close();
    }
}
