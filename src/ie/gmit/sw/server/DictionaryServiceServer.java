package ie.gmit.sw.server;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class DictionaryServiceServer {
	public static void main(String[] args) throws Exception {

		// Create an instance of a FileServiceImper. As FileServiceImpe implements the
		// FileService
		DictionaryImpe ds = new DictionaryImpe();

		// Start the RMI registry on port 1099
		// It creates Registry in JVM in default port 1099, and then rebind the instance
		// of the remote object implementation to exports it.
		LocateRegistry.createRegistry(1099);

		// Bind our remote object to the registry with the human-readable name
		// "fileService"
		Naming.rebind("dictionaryImpe", ds);

		// Print a message to standard output
		System.out.println("Server ready.");
	// testing server
		//ds.search("dog"m);
	}//end main6
}//end class