import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Stack;

// the interface that
// defines the remote operations implemented by your remote service
public interface Sorter extends Remote {

    // Return a stack
    Stack<Integer> getStack() throws RemoteException;

    // This method will take val and push it on to the top of the stack
    void pushValue(int val) throws RemoteException;

    // This method will push a string containing an operator to the stack
    void pushOperator(String operator) throws RemoteException;

    // This method will pop the top of the stack and return it to the client
    int pop() throws RemoteException;

    // This method will return true if the stack is empty, false otherwise
    boolean isEmpty() throws RemoteException;

    // This method will wait milis miliseconds before
    // carrying out the pop operation as above
    int delayPop(int millis) throws RemoteException;
}
