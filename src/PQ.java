/**
 * PQ (Priority Queue) class implementing a max-heap based on the number of vowels in a string.
 * In this implementation, strings with a higher number of vowels have higher priority.
 * If two strings have the same number of vowels, they are ordered alphabetically.
 */
class PQ {

    // An ArrayList to store heap elements.
    final private ArrayList<String> heap;

    // Constructor initializes the heap.
    public PQ() {
        heap = new ArrayList<>();
    }

    /**
     * Counts the number of vowels in a given word.
     *
     * @param word The string in which vowels are to be counted.
     * @return The count of vowels in the given word.
     */
    private int countVowels(String word) {
        int count = 0;
        for (char ch : word.toCharArray()) {
            if ("AEIOUaeiou".indexOf(ch) != -1) {
                count++;
            }
        }
        return count;
    }

    /**
     * Compares two words based on the number of vowels and alphabetical order.
     *
     * @param word1 First word to compare.
     * @param word2 Second word to compare.
     * @return A negative integer, zero, or a positive integer as the first word
     *         has fewer, equal, or more vowels than the second word.
     */
    private int compare(String word1, String word2) {
        int vowels1 = countVowels(word1);
        int vowels2 = countVowels(word2);
        if (vowels1 != vowels2) {
            return vowels1 - vowels2; // More vowels have higher priority
        }
        return word2.compareTo(word1); // Alphabetical order
    }

    /**
     * Restores the heap property moving up the tree starting from a given index.
     *
     * @param index The index of the heap element to be heapified up.
     */
    private void heapifyUp(int index) {
        // Loop until the index reaches the root (index 0).
        while (index > 0) {
            // Calculate the index of the parent node.
            int parentIndex = (index - 1) / 2;
            // Compare the current node with its parent. If the current node has a lower priority (fewer vowels),
            // or is correctly ordered alphabetically, then the heap is correctly ordered.
            if (compare(heap.get(index), heap.get(parentIndex)) < 0) {
                break; // Correct order, stop heapifying up.
            }
            // If the current node has a higher priority, swap it with its parent.
            swap(index, parentIndex);
            // Move up to the parent index and continue heapifying up.
            index = parentIndex;
        }
    }

    /**
     * Restores the heap property moving down the tree starting from a given index.
     *
     * @param index The index of the heap element to be heapified down.
     */
    private void heapifyDown(int index) {
        int size = heap.size();
        // Loop until the index exceeds the size of the heap.
        while (index < size) {
            // Calculate the indices of the left and right children of the current node.
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            // Start with the assumption that the current index holds the largest value.
            int largestIndex = index;
    
            // Check if the left child exists and has a greater priority than the current node.
            if (leftChildIndex < size && compare(heap.get(leftChildIndex), heap.get(largestIndex)) > 0) {
                largestIndex = leftChildIndex;
            }
    
            // Check if the right child exists and has a greater priority than the current largest.
            if (rightChildIndex < size && compare(heap.get(rightChildIndex), heap.get(largestIndex)) > 0) {
                largestIndex = rightChildIndex;
            }
    
            // If the largest value is not at the current index, swap and continue down the heap.
            if (largestIndex != index) {
                swap(index, largestIndex);
                index = largestIndex;
            } else {
                // If the largest value is at the current index, the heap is correctly ordered.
                break;
            }
        }
    }

    /**
     * Swaps two elements in the heap.
     *
     * @param i First element's index to swap.
     * @param j Second element's index to swap.
     */
    private void swap(int i, int j) {
        String temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Inserts a new word into the priority queue.
     *
     * @param word The word to be inserted.
     */
    public void insert(String word) {
        heap.add(word);
        heapifyUp(heap.size() - 1);
    }

    /**
     * Removes and returns the element with the highest priority (most vowels) from the priority queue.
     *
     * @return The word with the highest priority, or null if the queue is empty.
     */
    public String remove() {
        // Check if the heap is empty. If it is, return null as there is nothing to remove.
        if (heap.isEmpty()) {
            return null;
        }
        // Store the element with the highest priority (at the root of the heap) in 'result'.
        String result = heap.get(0);
        // Move the last element in the heap to the root position.
        heap.set(0, heap.get(heap.size() - 1));
        // Remove the last element (since it is now at the root).
        heap.remove(heap.size() - 1);
        // Restore the heap property starting from the root.
        heapifyDown(0);
        // Return the word that had the highest priority.
        return result;
    }

    public String peek() {
        // Return the element at the root of the heap (highest priority) if the heap is not empty.
        // If the heap is empty, return null.
        return heap.isEmpty() ? null : heap.get(0);
    }
}
