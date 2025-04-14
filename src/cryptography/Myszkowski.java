package cryptography;

import java.util.*;

public class Myszkowski {
    /**
     * Myszkowski Cipher
     * 
     * Encryption: C = The plaintext is divided into a block, which is filled with columns based on the order of the 
     * letters of the key. After that, the columns are transposed according to the key order, and the ciphertext 
     * is read column by column.
     * 
     * Decryption: P = To decrypt, we reverse the transposition step by reordering the columns based on the 
     * key, then read the block row by row to reconstruct the original plaintext.
     * 
     * Key: The key is used to determine the order in which the columns are transposed. 
     * Each character in the key corresponds to a column of the block.
    */


    public static String encrypt(String key, String plaintext) {
        // Generate the column order based on the sorted key
        Integer[] columnOrder = new Integer[key.length()];
        for (int i = 0; i < key.length(); i++) {
            columnOrder[i] = i;
        }

        // Sort the column order based on the key's characters
        Arrays.sort(columnOrder, Comparator.comparingInt(i -> key.charAt(i)));

        int rows = (int) Math.ceil((double) plaintext.length() / key.length());
        char[][] block = new char[rows][key.length()];
        int index = 0;

        // Fill the block with characters from the plaintext
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                if (index < plaintext.length()) {
                    block[i][j] = plaintext.charAt(index++);
                } else {
                    block[i][j] = 'X'; // Padding with 'X' if required
                }
            }
        }

        // Transpose columns according to the sorted key
        char[][] transposed = new char[rows][key.length()];
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < rows; j++) {
                transposed[j][i] = block[j][columnOrder[i]];
            }
        }

        // Read the ciphertext column by column
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < rows; j++) {
                ciphertext.append(transposed[j][i]);
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String key, String ciphertext) {
        // Generate the column order based on the sorted key
        Integer[] columnOrder = new Integer[key.length()];
        for (int i = 0; i < key.length(); i++) {
            columnOrder[i] = i;
        }

        // Sort the column order based on the key's characters
        Arrays.sort(columnOrder, Comparator.comparingInt(i -> key.charAt(i)));

        int rows = (int) Math.ceil((double) ciphertext.length() / key.length());
        char[][] block = new char[rows][key.length()];
        int index = 0;

        // Fill the block with characters from the ciphertext
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < rows; j++) {
                if (index < ciphertext.length()) {
                    block[j][columnOrder[i]] = ciphertext.charAt(index++);
                }
            }
        }

        // Read the plaintext row by row
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                plaintext.append(block[i][j]);
            }
        }

        // Remove padding 'X' if present
        int paddingIndex = plaintext.indexOf("X");
        if (paddingIndex != -1) {
            plaintext.setLength(paddingIndex);
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter key: ");
        String key = sc.nextLine();

        System.out.print("Enter text to encode: ");
        String plaintext = sc.nextLine();

        String encodedText = encrypt(key, plaintext);
        System.out.println("Encrypted Text: " + encodedText);

        String decodedText = decrypt(key, encodedText);
        System.out.println("Decrypted Text: " + decodedText);

        sc.close();
    }
}
