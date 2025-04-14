package cryptography;

import java.util.Scanner;

public class Affine {
    /**
	 * Affine Cipher
	 * 
     * Encrypt: f = Ax + B
     * Decrypt: x = A' * (y-B) mod 26
	 */    


    // Function to compute modular inverse of a under modulo m
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int i = 1; i < m; i++) {
            if ((a * i) % m == 1)
                return i;
        }
        return -1; // Inverse doesn't exist
    }

    // Encrypts plaintext using Affine Cipher formula: (a * x + b) % 26
    public static String encrypt(String plaintext, int a, int b) {
        StringBuilder cipher = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char base = isUpper ? 'A' : 'a';
                int x = c - base;
                int encryptedChar = (a * x + b) % 26;
                cipher.append((char) (encryptedChar + base));
            } 
            else {
                cipher.append(c); // Non-alphabetic characters are unchanged
            }
        }
        return cipher.toString();
    }

    // Decrypts ciphertext using: a_inv * (y - b) mod 26
    public static String decrypt(String ciphertext, int a, int b) {
        StringBuilder plain = new StringBuilder();
        int a_inv = modInverse(a, 26);
        if (a_inv == -1) {
            return "Modular inverse of A doesn't exist. Decryption not possible.";
        }

        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char base = isUpper ? 'A' : 'a';
                int y = c - base;
                int decryptedChar = (a_inv * (y - b + 26)) % 26;
                plain.append((char) (decryptedChar + base));
            } else {
                plain.append(c); // Non-alphabetic characters are unchanged
            }
        }
        return plain.toString();
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the multiplier A (must be coprime with 26): ");
        int a = sc.nextInt();

        System.out.print("Enter the additive shift B: ");
        int b = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter the plaintext message: ");
        String plaintext = sc.nextLine();

        String encrypted = encrypt(plaintext, a, b);
        String decrypted = decrypt(encrypted, a, b);

        System.out.println("\nEncrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);

        sc.close();
    }
}
