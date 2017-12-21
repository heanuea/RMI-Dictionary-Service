package ie.gmit.sw.client;

import java.rmi.*;

//In this we created interface  and method search()
//remote interface have to extend the interface java.rmi.Remote 
public interface DictionaryService extends Remote {
	
	public String search(String word) throws RemoteException;

}
