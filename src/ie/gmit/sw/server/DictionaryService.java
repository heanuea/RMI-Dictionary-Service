package ie.gmit.sw.server;

//imported rmi 
import java.rmi.*;

/**
 * 
 * @author Alan Heanue
 *
 */

//Interface must be public 
public interface DictionaryService extends Remote{

	//all methods described in the 
	public String wordSearch(String Word) throws RemoteException;
}
