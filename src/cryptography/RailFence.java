package cryptography;

import java.util.Scanner;

public class RailFence {
    /**
     * Rail Fence Cipher
     * 
     * Encryption: C = Rail fence cipher encrypts the plaintext by writing it in a zigzag pattern across multiple rows 
     * and then reading the rows in a sequential manner. The number of rows is specified by the key.
     * 
     * Decryption: P = To decrypt, we reconstruct the zigzag pattern from the cipher text and read it vertically 
     * to recover the original plaintext.
     * 
     * Key: The key is the number of rows used to form the zigzag pattern. 
     * It determines the number of rows the plaintext will be written across.
    */

    private static void encryption(String text, int key) {
        String encrypted_text = "";
        // Changing the direction to down or up of the zigzag pattern to put the values.
        boolean direction = false;

        int column = text.length();
        int j = 0;

        // Initializing the char array to store the zigzag formation.
        char[][] a = new char[key][column];

        // The for loop which puts the character of the text to railfence form.
        for (int i = 0; i < column; i++) {
            if (j == 0 || j == key - 1) {
                direction = !direction;
            }
            a[j][i] = text.charAt(i);
            if (direction) {
                j++;
            } else {
                j--;
            }
        }

        for (int i = 0; i < key; i++) {
            for (int k = 0; k < column; k++) {
                if (a[i][k] != 0) {
                    encrypted_text += a[i][k];
                }
            }
        }

        System.out.println("Encrypted text: " + encrypted_text);
        decryption(encrypted_text, key);
    }

    private static void decryption(String encrypted_text, int key) {
        String decrypted_text = "";

        // Changing the direction to down or up to put the values.
        boolean direction = false;

        int column = encrypted_text.length();
        int j = 0;

        // Initializing the char array to store the zigzag formation.
        char[][] a = new char[key][column];

        for (int i = 0; i < column; i++) {
            if (j == 0 || j == key - 1) {
                direction = !direction;
            }
            a[j][i] = '*';
            if (direction) {
                j++;
            } else {
                j--;
            }
        }

        direction = false;

        int index = 0;

        for (int i = 0; i < key; i++) {
            for (int k = 0; k < column; k++) {
                if (a[i][k] == '*' && index < column) {
                    a[i][k] = encrypted_text.charAt(index++);
                }
            }
        }

        j = 0;
        for (int i = 0; i < column; i++) {
            if (j == 0 || j == key - 1) {
                direction = !direction;
            }
            decrypted_text += a[j][i];

            if (direction) {
                j++;
            } else {
                j--;
            }
        }

        System.out.println("Decrypted text: " + decrypted_text);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = sc.nextLine();

        System.out.print("Enter the key value: ");
        int key = sc.nextInt();

        encryption(text, key);

        sc.close();
    }
}
