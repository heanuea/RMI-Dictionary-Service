package ie.gmit.sw.server;

	import java.rmi.RemoteException;
	import java.rmi.server.UnicastRemoteObject;
	import java.util.ArrayList;
	import java.util.HashMap;

	public class DictionaryImpe  extends UnicastRemoteObject implements DictionaryService{
		
		// serial version ID
		private static final long serialVersionUID = 1L;
		DictionaryHashMap ap;
		HashMap<String, ArrayList<String>> HashMap;
		
	
		//method 
		public DictionaryImpe() throws Exception, RemoteException{
			super();
			ap = new DictionaryHashMap();
			ap.csvRead();
			HashMap = ap.getDictionary();
		}
		
		
		//search for the word in dictionary map. RemoteException is thrown as this method will be used in RMI
		public void search(String Word) throws RemoteException{		
			if(HashMap.containsKey(Word.toUpperCase())){			
				System.out.println(Word.toUpperCase()+" --> "+HashMap.get(Word.toUpperCase()).toString());			
			}	
			else{
				System.out.println(Word.toUpperCase()+" ");
			}//end else
			
		}//end search

	}//end class 

