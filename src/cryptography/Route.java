package cryptography;

import java.util.Scanner;

public class Route {

    // Encrypts the plaintext using route cipher
    public static String encrypt(String plainText, int rowCount, int columnCount) {
        char[][] matrix = new char[rowCount][columnCount];
        int position = 0;

        // Fill the matrix row-wise
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (position < plainText.length()) {
                    matrix[i][j] = plainText.charAt(position++);
                } else {
                    matrix[i][j] = 'X'; // Padding if needed
                }
            }
        }

        // Read column-wise to create ciphertext
        StringBuilder cipherText = new StringBuilder();
        for (int j = 0; j < columnCount; j++) {
            for (int i = 0; i < rowCount; i++) {
                cipherText.append(matrix[i][j]);
            }
        }

        return cipherText.toString();
    }

    // Decrypts the ciphertext using route cipher
    public static String decrypt(String cipherText, int rowCount, int columnCount) {
        char[][] matrix = new char[rowCount][columnCount];
        int position = 0;

        // Fill the matrix column-wise
        for (int j = 0; j < columnCount; j++) {
            for (int i = 0; i < rowCount; i++) {
                if (position < cipherText.length()) {
                    matrix[i][j] = cipherText.charAt(position++);
                } else {
                    matrix[i][j] = 'X';
                }
            }
        }

        // Read row-wise to get plaintext
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                plainText.append(matrix[i][j]);
            }
        }

        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the plaintext (no spaces): ");
        String plainText = sc.next().toUpperCase();

        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int columns = sc.nextInt();

        String cipherText = encrypt(plainText, rows, columns);
        System.out.println("Encrypted Text: " + cipherText);

        String decryptedText = decrypt(cipherText, rows, columns);
        System.out.println("Decrypted Text: " + decryptedText);

        sc.close();
    }
}
