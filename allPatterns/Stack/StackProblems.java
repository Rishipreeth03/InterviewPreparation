import java.util.Stack;
import java.util.Arrays;

public class StackProblems {
    
    // 1) Next Greater Element
    // Time Complexity: O(n), Space Complexity: O(n)
    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) { // Traverse from right to left
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop(); // Remove smaller elements as they are useless
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return res;
    }
    
    // 2) Next Smaller Element
    // Time Complexity: O(n), Space Complexity: O(n)
    public static int[] nextSmallerElement(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop(); // Remove larger elements as they are useless
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return res;
    }
    
    // 3) Previous Greater Element
    // Time Complexity: O(n), Space Complexity: O(n)
    public static int[] previousGreaterElement(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) { // Traverse from left to right
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop(); // Remove smaller elements as they are useless
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return res;
    }
    
    // 4) Previous Smaller Element
    // Time Complexity: O(n), Space Complexity: O(n)
    public static int[] previousSmallerElement(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop(); // Remove larger elements as they are useless
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};
        
        System.out.println("Next Greater Element: " + Arrays.toString(nextGreaterElement(arr)));
        System.out.println("Next Smaller Element: " + Arrays.toString(nextSmallerElement(arr)));
        System.out.println("Previous Greater Element: " + Arrays.toString(previousGreaterElement(arr)));
        System.out.println("Previous Smaller Element: " + Arrays.toString(previousSmallerElement(arr)));
    }
}
