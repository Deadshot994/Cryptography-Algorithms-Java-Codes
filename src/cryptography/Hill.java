package cryptography;

import java.util.Scanner;

public class Hill {
   /**
     * Hill Cipher
     *
     * Encryption: C = (K * P) mod 26
     * Decryption: P = (K^(-1) * C) mod 26
     * 
     * Key: A square matrix (size n x n) used for encryption and decryption. It must be invertible modulo 26.
     * The plaintext is divided into n-sized vectors, multiplied by the key matrix (mod 26) to get ciphertext.
     * Decryption is done by multiplying the ciphertext by the inverse of the key matrix (mod 26).
    */

    // Encrypts the message using the key matrix
    public String encrypt(String message, int[][] keyMatrix) {
        message = message.toUpperCase().replaceAll("[^A-Z]", "");
        int matrixSize = keyMatrix.length;
        validateDeterminant(keyMatrix, matrixSize);

        StringBuilder cipherText = new StringBuilder();
        int[] messageVector = new int[matrixSize];
        int[] cipherVector = new int[matrixSize];
        int index = 0;

        while (index < message.length()) {
            for (int i = 0; i < matrixSize; i++) {
                if (index < message.length()) {
                    messageVector[i] = message.charAt(index++) - 'A';
                } else {
                    messageVector[i] = 'X' - 'A'; // Padding
                }
            }

            for (int i = 0; i < matrixSize; i++) {
                cipherVector[i] = 0;
                for (int j = 0; j < matrixSize; j++) {
                    cipherVector[i] += keyMatrix[i][j] * messageVector[j];
                }
                cipherVector[i] = mod26(cipherVector[i]);
                cipherText.append((char) (cipherVector[i] + 'A'));
            }
        }

        return cipherText.toString();
    }

    // Decrypts the message using the original key matrix
    public String decrypt(String message, int[][] keyMatrix) {
        int[][] inverseKeyMatrix = inverseMatrix(keyMatrix);
        return encrypt(message, inverseKeyMatrix); // Decryption is same as encryption with inverse matrix
    }

    // Calculates modular inverse of the matrix
    private int[][] inverseMatrix(int[][] matrix) {
        int size = matrix.length;
        int det = mod26(determinant(matrix, size));
        int detInv = modInverse(det, 26);

        if (det == 0 || detInv == -1) {
            throw new IllegalArgumentException("Matrix is not invertible modulo 26.");
        }

        int[][] adj = adjugate(matrix);
        int[][] inverse = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inverse[i][j] = mod26(adj[i][j] * detInv);
            }
        }
        return inverse;
    }

    // Adjugate of matrix (transpose of cofactor matrix)
    private int[][] adjugate(int[][] matrix) {
        int n = matrix.length;
        int[][] adj = new int[n][n];
        int sign;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[][] subMatrix = getSubMatrix(matrix, i, j);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adj[j][i] = mod26(sign * determinant(subMatrix, n - 1));
            }
        }

        return adj;
    }

    // Get submatrix by excluding row p and column q
    private int[][] getSubMatrix(int[][] matrix, int p, int q) {
        int n = matrix.length;
        int[][] subMatrix = new int[n - 1][n - 1];
        int row = 0;

        for (int i = 0; i < n; i++) {
            if (i == p) continue;
            int col = 0;
            for (int j = 0; j < n; j++) {
                if (j == q) continue;
                subMatrix[row][col++] = matrix[i][j];
            }
            row++;
        }

        return subMatrix;
    }

    // Computes the determinant recursively
    private int determinant(int[][] matrix, int n) {
        int det = 0;
        if (n == 1) return matrix[0][0];

        int[][] subMatrix;
        int sign = 1;

        for (int f = 0; f < n; f++) {
            subMatrix = getSubMatrix(matrix, 0, f);
            det += sign * matrix[0][f] * determinant(subMatrix, n - 1);
            sign = -sign;
        }

        return det;
    }

    // Modular inverse of number under modulo m
    private int modInverse(int a, int m) {
        a = mod26(a);
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1;
    }

    // Fixes negative modulo issues
    private int mod26(int n) {
        return (n % 26 + 26) % 26;
    }

    // Validates that the matrix is invertible
    private void validateDeterminant(int[][] matrix, int n) {
        int det = mod26(determinant(matrix, n));
        if (det == 0 || modInverse(det, 26) == -1) {
            throw new IllegalArgumentException("Invalid key matrix. Not invertible modulo 26.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hill hill = new Hill();

        int[][] keyMatrix = {
            {6, 24, 1},
            {13, 16, 10},
            {20, 17, 15}
        };

        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine();

        String encrypted = hill.encrypt(message, keyMatrix);
        String decrypted = hill.decrypt(encrypted, keyMatrix);

        System.out.println("\nOriginal : " + message.toUpperCase().replaceAll("[^A-Z]", ""));
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }
}
