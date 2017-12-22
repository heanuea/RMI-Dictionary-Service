package ie.gmit.sw.server;

import java.rmi.*;
import java.io.IOException;
import java.rmi.RemoteException;

//In this we created interface  and method search()
//remote interface have to extend the interface java.rmi.Remote 
public interface DictionaryService extends Remote {
	
	public String search(String Word) throws RemoteException, IOException;
	public void populate() throws RemoteException, IOException;

}
