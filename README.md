# Huffman Encoding Algorithm in Java

This Java project implements the Huffman encoding algorithm, a popular technique for lossless data compression. Huffman coding is particularly effective for variable-length codes, providing a compact representation by assigning shorter codes to frequently occurring characters.

## Table of Contents

- [Overview](#overview)
- [Usage](#usage)
- [Implementation Details](#implementation-details)
- [Example](#example)

## Overview

The project includes a Java implementation of the Huffman encoding algorithm, organized in the `HuffmanEncoder` class. This class provides methods for compressing and decompressing data, as well as a `main` method for testing.

## Usage

To use the Huffman encoding algorithm in your Java project, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/huffman-encoding-java.git
    cd huffman-encoding-java
    ```

2. Import the `HuffmanEncoder` class into your project.

3. Create an instance of `HuffmanEncoder` and use the `compress` method to encode your data.

4. Use the `decompress` method to decode the encoded data when needed.

Refer to the [example](#example) section for a simple demonstration.

## Implementation Details

### `HuffmanEncoder` Class

The core functionality is encapsulated in the `HuffmanEncoder` class, which includes methods for compressing and decompressing data. The `Node` class is a nested class representing nodes in the Huffman tree.
The resulting encoded data is stored in the `HuffmanEncoderResult` class.

### Example

```java
public static void main(String[] args) {
    final String test = "abcdeffg";
    final HuffmanEncoder encoder = new HuffmanEncoder();
    final HuffmanEncoder.HuffmanEncodedResult result = encoder.compress(test);
    System.out.println("Original: " + test);
    System.out.println("Encoded: " + result.getEncodedData());
    System.out.println("Decoded: " + encoder.decompress(result));
}

