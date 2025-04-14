package cryptography;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NGramOperations {
    /**
     * N-gram Operations
     * 
     * Purpose: N-gram operations split text into sequences of 'n' consecutive units (words or characters). 
     * These N-grams are used in NLP tasks like language modeling and text prediction.
     * 
     * N-gram Generation: The text is divided into 'n' consecutive units (unigrams, bigrams, trigrams, etc.).
     * Example:
     * - Input: "I love programming"
     * - Unigrams (1-grams): ["I", "love", "programming"]
     * - Bigrams (2-grams): ["I love", "love programming"]
     * - Trigrams (3-grams): ["I love programming"]
     * 
     * Usage: N-grams are used for text analysis, language modeling, and predictive tasks.
    */

    // Method to generate n-grams from a given input text
    public static List<String> generateNGrams(String input, int n) {
        List<String> nGrams = new ArrayList<>();
        
        // Split the input text into words
        String[] words = input.split(" ");
        
        // Ensure that the length of the text is sufficient to form the desired n-grams
        if (words.length < n) {
            System.out.println("Text is too short to generate " + n + "-grams");
            return nGrams;
        }

        // Loop through the words and generate n-grams
        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder nGram = new StringBuilder();
            
            // Combine n consecutive words into a single n-gram
            for (int j = 0; j < n; j++) {
                nGram.append(words[i + j]).append(" ");
            }
            
            // Remove trailing space and add the n-gram to the list
            nGrams.add(nGram.toString().trim());
        }

        return nGrams;
    }

    // Method to display the generated n-grams
    public static void displayNGrams(List<String> nGrams) {
        System.out.println("\nGenerated N-grams:");
        for (String nGram : nGrams) {
            System.out.println(nGram);
        }
    }

    // Main function to demonstrate the usage of the N-gram operations
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Take user input for the text
        System.out.println("Enter the text:");
        String text = sc.nextLine();

        // Take user input for the size of the N-gram
        System.out.println("Enter the size of the N-gram (e.g., 1 for unigrams, 2 for bigrams, etc.):");
        int n = sc.nextInt();
        
        // Generate and display unigrams (1-grams), bigrams (2-grams), and trigrams (3-grams)
        System.out.println("\nGenerating N-grams...");
        List<String> nGrams = generateNGrams(text, n);
        displayNGrams(nGrams);
        
        // Close the scanner
        sc.close();
    }
}
