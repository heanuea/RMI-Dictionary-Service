package ie.gmit.sw.server;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.nio.charset.MalformedInputException;

public class Server {

	public static void main(String args[]) 
								throws Exception, MalformedInputException, NotBoundException{
		
		DictionaryServiceImp ds = new DictionaryServiceImp();
		//create Registry in JVM in default port 1099
		LocateRegistry.createRegistry(1099);
		//rebind the instance of the remote object implementation
		Naming.rebind("dictionaryService", ds);
		System.out.println("Dictionary ready!");
		
	}//end main
}//end class
