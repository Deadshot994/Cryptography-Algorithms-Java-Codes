package cryptography;

import java.util.Scanner;

public class August {
    /**
	 * August Cipher
	 * 
     * Encrypt: (P + K) mod 26
     * Decrypt: (C - K) mod 26
     * where k = 1
	 */   

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String plainText, int shiftKey) {
        plainText = plainText.toLowerCase();
        String cipherText = "";

        for (int i = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);
            int charPosition = ALPHABET.indexOf(currentChar);

            if (charPosition == -1) {
                cipherText += currentChar;
            } else {
                int keyVal = (shiftKey + charPosition) % 26;
                char replaceVal = ALPHABET.charAt(keyVal);
                cipherText += replaceVal;
            }
        }
        return cipherText;
    }

    public static String decrypt(String cipherText, int shiftKey) {
        cipherText = cipherText.toLowerCase();
        String plainText = "";

        for (int i = 0; i < cipherText.length(); i++) {
            char currentChar = cipherText.charAt(i);
            int charPosition = ALPHABET.indexOf(currentChar);

            if (charPosition == -1) {
                plainText += currentChar;
            } else {
                int keyVal = (charPosition - shiftKey) % 26;
                if (keyVal < 0) {
                    keyVal = ALPHABET.length() + keyVal;
                }
                char replaceVal = ALPHABET.charAt(keyVal);
                plainText += replaceVal;
            }
        }
        return plainText;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the string for encryption:");
        String message = sc.nextLine();

        int shiftKey = 1; 

        String encrypted = encrypt(message, shiftKey);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, shiftKey);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}
