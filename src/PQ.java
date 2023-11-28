import java.util.ArrayList;

class PQ {
    private ArrayList<String> heap;

    public PQ() {
        heap = new ArrayList<>();
    }

    private int countVowels(String word) {
        int count = 0;
        for (char ch : word.toCharArray()) {
            if ("AEIOUaeiou".indexOf(ch) != -1) {
                count++;
            }
        }
        return count;
    }

    private int compare(String word1, String word2) {
        int vowels1 = countVowels(word1);
        int vowels2 = countVowels(word2);
        if (vowels1 != vowels2) {
            return vowels1 - vowels2; // More vowels have higher priority
        }
        return word2.compareTo(word1); // Alphabetical order
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parentIndex)) < 0) {
                break; // Correct order
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int largestIndex = index;

            if (leftChildIndex < size && compare(heap.get(leftChildIndex), heap.get(largestIndex)) > 0) {
                largestIndex = leftChildIndex;
            }

            if (rightChildIndex < size && compare(heap.get(rightChildIndex), heap.get(largestIndex)) > 0) {
                largestIndex = rightChildIndex;
            }

            if (largestIndex != index) {
                swap(index, largestIndex);
                index = largestIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        String temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(String word) {
        heap.add(word);
        heapifyUp(heap.size() - 1);
    }

    public String remove() {
        if (heap.isEmpty()) {
            return null;
        }
        String result = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(0);
        return result;
    }

    public String peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }
}
