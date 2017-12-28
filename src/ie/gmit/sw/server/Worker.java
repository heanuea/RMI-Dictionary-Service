package ie.gmit.sw.server;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class Worker {

	public static void main(String[] args) throws Exception {

		DictionaryService stub = new DictionaryImpe("dictionary.txt"); // load the dictionary 
		LocateRegistry.createRegistry(1099); // set port
		Naming.rebind("dictionaryService", stub); // add the dictionary service to the lookup table
	
		System.out.println("Server " + stub + " Ready");
		
	}// main
	
}// class

