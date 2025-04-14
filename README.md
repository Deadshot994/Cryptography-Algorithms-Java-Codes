# Crypto Codes

This project contains 13 cryptography algorithms implemented in Java. The algorithms demonstrate various classical encryption and decryption methods, along with N-gram operations for text processing. This repository provides the full source code for each algorithm.

## Project Structure
```
Crypto Codes 
├── src # Source code 
| └── cryptography # Cryptography algorithm implementations 
|     ├── Affine.java
|     ├── Atbash.java
|     ├── August.java
|     ├── Autoclave.java
|     ├── Beaufort.java
|     ├── Caesar.java
|     ├── Gronsfeld.java
|     ├── Hill.java
|     ├── Myszkowski.java
|     ├── NGramOperations.java # N-gram operations for text processing 
|     ├── RailFence.java
|     ├── Route.java
|     └── Vignere.java
├── Output.txt 
└── Readme.md # Project description
```

## Algorithms

The following cryptographic algorithms are implemented in this project:

1. **Caesar Cipher**  
   A substitution cipher where each letter is shifted by a fixed number.

2. **Atbash Cipher**  
   A simple substitution cipher where the alphabet is reversed.

3. **August Cipher**  
   A polyalphabetic substitution cipher similar to Caesar cipher with shift of 1.

4. **Affine Cipher**  
   A cipher based on linear transformations of the alphabet.

5. **Vigenere Cipher**  
   A polyalphabetic substitution cipher that uses a keyword to determine the shifts.

6. **Gronsfeld Cipher**  
   A variant of the Vigenere cipher using digits (0-9) as the key.

7. **Beaufort Cipher**  
   A cipher similar to the Vigenere cipher but uses a reversed alphabet for encryption.

8. **Autoclave Cipher**  
   Also known as the Running Key cipher, it uses a key of arbitrary length to encrypt the message.

9. **Ngram Operations**  
   Generates N-grams (unigrams, bigrams, trigrams, etc.) from input text for text analysis and prediction.

10. **Hill Cipher**  
    A polygraphic substitution cipher based on matrix multiplication.

11. **Rail Fence Cipher**  
    A transposition cipher that arranges text in a zigzag pattern across multiple rails.

12. **Route Cipher**  
    A transposition cipher where the plaintext is written into a grid and then read in a specific route.

13. **Myszkowski Cipher**  
    A transposition cipher using a keyword to reorder columns and transpose them.

## How to Use

1. Clone or download this repository.
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse, or VSCode).
3. Navigate to the `src/cryptography` folder.
4. Each algorithm is implemented in its own `.java` file. You can run any of the classes directly to test the encryption and decryption functionalities.
   
   Example for running the **Caesar Cipher**:
   - Open `Caesar.java`.
   - Run the `main` method to see the encryption and decryption in action.

5. For N-gram operations, run the `NGramOperations.java` to see how the text can be split into unigrams, bigrams, and trigrams.

## Requirements
- Java Development Kit (JDK) version 8 or higher.
- No additional libraries are required for this project.

## License
This project is open-source and available under the [MIT License](LICENSE).

## Author
Sanjeeth Manikandan
