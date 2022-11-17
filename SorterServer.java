import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class SorterServer {

    // Constructor
    public SorterServer() {
        try {
            Sorter s = new SorterImplementation();

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Sorter", s);
            System.out.println("Server ready!");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new SorterServer();
    }
}
