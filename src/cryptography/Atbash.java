package cryptography;

import java.util.Scanner;

public class Atbash {
    /**
	 * Atbash Cipher
	 * 
     * Encrypt: (P + K) mod 26
     * Decrypt: (C - K) mod 26
     * where K = 1
	 */   

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String inputText) {
        inputText = inputText.toLowerCase(); 
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < inputText.length(); i++) {
            char currentChar = inputText.charAt(i);
            int charPosition = ALPHABET.indexOf(currentChar);

            if (charPosition == -1) {
                cipherText.append(currentChar); 
            } else {
                // Reverse the alphabet using Atbash
                char replaceVal = ALPHABET.charAt(ALPHABET.length() - 1 - charPosition);
                cipherText.append(replaceVal);
            }
        }
        return cipherText.toString();
    }

    public static String decrypt(String cipherText) {
        cipherText = cipherText.toLowerCase(); 
        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            char currentChar = cipherText.charAt(i);
            int charPosition = ALPHABET.indexOf(currentChar);

            if (charPosition == -1) {
                plainText.append(currentChar);
            } else {
                // Reverse the alphabet using Atbash 
                char replaceVal = ALPHABET.charAt(ALPHABET.length() - 1 - charPosition);
                plainText.append(replaceVal);
            }
        }
        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the string for encryption/decryption:");
        String message = sc.nextLine();

        String encrypted = encrypt(message);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}
