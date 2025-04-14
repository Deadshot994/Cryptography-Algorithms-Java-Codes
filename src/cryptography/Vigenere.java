package cryptography;

import java.util.Scanner;

public class Vigenere {
    /**
	 * Vignere Cipher
	 * 
     * Encrypt: C = (P + K) mod 26 
     * Decrypt: P = (C - K) mod 26
	 */ 

    // Encrypts using Vigenere Cipher
    public static String encrypt(String plaintext, String key) {
        StringBuilder encrypted = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char base = isUpper ? 'A' : 'a';
                int plainVal = c - base;
                int keyVal = key.charAt(keyIndex % key.length()) - 'a';
                char encryptedChar = (char) ((plainVal + keyVal) % 26 + base);
                encrypted.append(encryptedChar);
                keyIndex++;
            } 
            else {
                encrypted.append(c); 
            }
        }

        return encrypted.toString();
    }

    // Decrypts using Vigenere Cipher
    public static String decrypt(String ciphertext, String key) {
        StringBuilder decrypted = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;

        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char base = isUpper ? 'A' : 'a';
                int cipherVal = c - base;
                int keyVal = key.charAt(keyIndex % key.length()) - 'a';
                char decryptedChar = (char) ((cipherVal - keyVal + 26) % 26 + base);
                decrypted.append(decryptedChar);
                keyIndex++;
            } 
            else {
                decrypted.append(c);
            }
        }

        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the key: ");
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
