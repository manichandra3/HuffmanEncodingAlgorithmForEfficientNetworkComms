package com.encode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoder {

    private static final int ALPHABET_SIZE = 256;

    public HuffmanEncodedResult compress(final String data) {
        final int[] frequency = buildFrequencyTable(data);
        final Node root = buildHuffmanTree(frequency);
        final Map<Character, String> lookupTable = buildLookupTable(root);
        return new HuffmanEncodedResult(generateEncodedData(data, lookupTable), root);
    }

    private static int[] buildFrequencyTable(final String data){
        final int[] freq = new int [ALPHABET_SIZE];
        for(final char character : data.toCharArray()){
            freq[character]++;
        }
        return freq;
    }

    private static String generateEncodedData(String data, Map<Character, String> lookupTable) {
        StringBuilder builder = new StringBuilder();
        for (char character : data.toCharArray()) {
            builder.append(lookupTable.get(character));
        }
        return builder.toString();
    }

    private static Map<Character, String> buildLookupTable(final Node root) {
        Map<Character, String> lookupTable = new HashMap<>();
        buildLookupTable(root, "", lookupTable);
        return lookupTable;
    }

    private static void buildLookupTable(Node node, String str, Map<Character, String> lookupTable) {
        if (!node.isLeaf()) {
            buildLookupTable(node.leftChild, str + '0', lookupTable);
            buildLookupTable(node.rightChild, str + '1', lookupTable);
        } else {
            lookupTable.put(node.character, str);
        }
    }

    static class Node implements Comparable<Node> {
        private final char character;
        private final int frequency;
        private final Node leftChild;
        private final Node rightChild;

        private Node(char character, int frequency, Node leftChild, Node rightChild) {
            this.character = character;
            this.frequency = frequency;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        boolean isLeaf() {
            return (this.leftChild == null && this.rightChild == null);
        }

        @Override
        public int compareTo(Node that) {
            final int frequencyComparison = Integer.compare(this.frequency, that.frequency);
            if (frequencyComparison != 0) {
                return frequencyComparison;
            }
            return Integer.compare(this.character, that.character);
        }
    }

    public static Node buildHuffmanTree(final int[] frequency) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (char i = 0; i < ALPHABET_SIZE; i++) {
            if (frequency[i] > 0)
                priorityQueue.add(new Node(i, frequency[i], null, null));
        }
        if (priorityQueue.size() == 1) {
            priorityQueue.add(new Node('\0', 1, null, null));
        }
        while (priorityQueue.size() > 1) {
            final Node left = priorityQueue.poll();
            final Node right = priorityQueue.poll();
            assert right != null;
            final Node parent = new Node('\0', left.frequency + right.frequency, left, right);
            priorityQueue.add(parent);
        }
        return priorityQueue.poll();
    }

    public String decompress(final HuffmanEncodedResult result) {
        StringBuilder builder = new StringBuilder();
        Node currentNode = result.getRoot();
        int i = 0;
        while (i < result.getEncodedData().length()) {
            char bit = result.getEncodedData().charAt(i);
            if (bit == '1') {
                currentNode = currentNode.rightChild;
            } else if (bit == '0') {
                currentNode = currentNode.leftChild;
            } else {
                throw new IllegalArgumentException("Invalid bit encountered: " + bit);
            }

            if (currentNode.isLeaf()) {
                builder.append(currentNode.character);
                currentNode = result.getRoot();
            }
            i++;
        }
        return builder.toString();
    }

    public static class HuffmanEncodedResult {
        final Node root;
        final String encodedData;

        public HuffmanEncodedResult(String encodedData, Node root) {
            this.root = root;
            this.encodedData = encodedData;
        }

        public Node getRoot() {
            return this.root;
        }

        public String getEncodedData() {
            return encodedData;
        }
    }

    public static void main(String[] args) {
        final String test = "abcdeffg";
        final HuffmanEncoder encoder = new HuffmanEncoder();
        final HuffmanEncodedResult result = encoder.compress(test);
        System.out.println("Original: " + test);
        System.out.println("Encoded: " + result.getEncodedData());
        System.out.println("Decoded: " + encoder.decompress(result));
    }
}
