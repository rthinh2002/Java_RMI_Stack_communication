import java.rmi.RemoteException;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;

public class SorterImplementation extends UnicastRemoteObject implements Sorter {
    private Stack<Integer> stack;

    // Constructor
    public SorterImplementation() throws RemoteException {
        super();
        this.stack = new Stack<>();
    }

    // Getter for stack variable
    public Stack<Integer> getStack() throws RemoteException {
        return this.stack;
    }

    // This method will push a value to the stack
    @Override
    public void pushValue(int val) throws RemoteException {
        stack.push(val);
    }

    // This method will push an operator into the stack and pop all value out to
    // start the operation
    @Override
    public void pushOperator(String operator) throws RemoteException {
        // Ascending operator
        if (operator.equals("ascending")) {
            Object[] arr = this.stack.toArray();
            // Pop all value out of the stack
            this.stack.clear();

            // Sort the array into descending order to start push back to stack
            Arrays.sort(arr, Collections.reverseOrder());

            for (int i = 0; i < arr.length; i++) {
                this.stack.push((Integer) arr[i]);
            }
        } else if (operator.equals("descending")) { // desending operator
            Object[] arr = this.stack.toArray();
            // Pop all value out of the stack
            this.stack.clear();

            // Sort the array into descending order to start push back to stack
            Arrays.sort(arr);

            for (int i = 0; i < arr.length; i++) {
                this.stack.push((Integer) arr[i]);
            }
        } else if (operator.equals("max")) { // operator max
            Object[] arr = this.stack.toArray();
            Integer[] intArr = Arrays.asList(arr).toArray(new Integer[0]); // Convert object array to integer array
            // Pop all value out of the stack
            this.stack.clear();

            // Get the max value out of array
            int max = Collections.max(Arrays.asList(intArr));

            // Count occurrence of max value in stack
            int count = 0;
            for (int i = 0; i < intArr.length; i++) {
                if (intArr[i] == max) {
                    count++;
                }
            }

            for (int i = 1; i <= count; i++) {
                this.stack.push(max);
            }

        } else { // operator min
            Object[] arr = this.stack.toArray();
            Integer[] intArr = Arrays.asList(arr).toArray(new Integer[0]); // Convert object array to integer array
            // Pop all value out of the stack
            this.stack.clear();

            // Get the min value out of array
            int min = Collections.min(Arrays.asList(intArr));
            // Count occurrence of min value in stack
            int count = 0;
            for (int i = 0; i < intArr.length; i++) {
                if (intArr[i] == min) {
                    count++;
                }
            }

            for (int i = 1; i <= count; i++) {
                this.stack.push(min);
            }
        }

    }

    // Pop the top value and return it to the client
    @Override
    public int pop() throws RemoteException {
        int res = stack.pop();
        return res;
    }

    // Check if the stack is empty
    @Override
    public boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }

    // Delay the pop for millisecond
    @Override
    public int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
            int res = this.stack.pop();
            return res;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        return 0;
    }
}
