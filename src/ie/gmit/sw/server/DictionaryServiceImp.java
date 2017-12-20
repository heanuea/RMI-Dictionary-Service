package ie.gmit.sw.server;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.rmi.RemoteException;
	import java.rmi.server.UnicastRemoteObject;
	
	public class DictionaryServiceImp  extends UnicastRemoteObject implements DictionaryService {
//DictionaryService
		private static final long serialVersionUID = 1L;
		
		Dictionary Map;
		HashMap<String, ArrayList<String>> dHashMap;
		
		//Set up the dictionary during construction of this class. Declare Throwable exceptions.
		public DictionaryServiceImp() throws Exception, RemoteException{
			Map = new Dictionary();
			Map.csvRead();
			dHashMap = Map.getDictionary();
		}
		
		
		//search for the word in dictionary map. RemoteException is thrown as this method will be used in RMI
		public void search(String Word) throws RemoteException{		
			if(dHashMap.containsKey(Word.toUpperCase())){			
				System.out.println(Word.toUpperCase()+" --> "+dHashMap.get(Word.toUpperCase()).toString());			
			}	
			else{
				System.out.println(Word.toUpperCase()+" --> This word is undefined in this dictionary!");
			}
		}//end of search


		@Override
		public String wordSearch(String Word) throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

	}
		
		
	
