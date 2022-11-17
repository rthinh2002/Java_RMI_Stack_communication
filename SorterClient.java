import java.util.Scanner;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SorterClient {

  // Method to print out the stack
  public static void printStack(Stack<Integer> s) {
    System.out.print("The current stack from top to bottom: ");

    Stack<Integer> s1 = new Stack<>();

    // Until stack is empty
    while (!s.isEmpty()) {
      s1.push(s.peek());

      System.out.print(s1.peek() + " ");
      s.pop();
    }
    System.out.println("");
  }

  // Main method
  public static void main(String[] args) {
    if (args.length > 0) {
      try {
        Registry registry = LocateRegistry.getRegistry(null);
        Sorter stub = (Sorter) registry.lookup("Sorter"); // Connect to the server with the name sorter binding to

        // Reading the file from command line
        FileInputStream fis = new FileInputStream(args[0]);
        Scanner sc = new Scanner(fis);

        // While loop handling until end of file
        while (sc.hasNextLine()) {
          // Convert line into array
          String[] command = (sc.nextLine()).split(" ");

          // Push the value into stack
          if (command[0].equals("pushValue")) {
            stub.pushValue(Integer.parseInt(command[1]));
            printStack(stub.getStack());
          } else if (command[0].equals("pushOperator")) { // Push the operators
            stub.pushOperator(command[1]);
            printStack(stub.getStack());
          } else if (command[0].equals("pop")) {
            if (stub.isEmpty())
              System.out.println("Unable to pop! Stack empty!");
            else {
              int num = stub.pop();
              System.out.print("Pop " + num + " out of the stack! ");
              printStack(stub.getStack());
            }
          } else if (command[0].equals("isEmpty")) {
            if (stub.isEmpty())
              System.out.println("Stack is empty!");
            else
              System.out.println("Stack is not empty");
          } else if (command[0].equals("delayPop")) {
            if (stub.isEmpty())
              System.out.println("Unable to delay pop! Stack empty!");
            else {
              int num = stub.delayPop(Integer.parseInt(command[1]));
              System.out.print("Delay pop " + num + " out of the stack! ");
              printStack(stub.getStack());
            }
          } else if (command[0].equals("wait")) { // This is only for testing multiple clients
            Thread.sleep(2000);
          }

        }

        System.out.println("\n\nThe final stack is: ");
        printStack(stub.getStack());
        System.out.println("\n");
        sc.close();
      } catch (Exception e) {
        System.err.println("Client exception: " + e.toString());
        e.printStackTrace();
      }
    }
  }
}
