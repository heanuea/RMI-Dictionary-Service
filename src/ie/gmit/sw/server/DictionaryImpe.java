package ie.gmit.sw.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public  class DictionaryImpe extends UnicastRemoteObject implements DictionaryService {

	private static final long serialVersionUID = 666L;
	private Map<String, String> dict;
	private BufferedReader br;
	private File file;

	public DictionaryImpe(String filename) throws RemoteException {
		dict = new HashMap<>();
		file = new File(filename);
	}// construct

	public void populate() throws RemoteException, IOException 
	{
		
		br = new BufferedReader(new FileReader(file)); // read in the file
		String line = null;

		
		while ((line = br.readLine()) != null)	
		{
			String[] el = line.split(","); 
			dict.put(el[0].toUpperCase(), el[1].toUpperCase()); // put the elements into the dictionary
		} // while
		
	}// end  

	public String wordSearch(String word) throws RemoteException, IOException {
		String result = ""; // the result 

		populate();

		if (dict.containsKey(word.toUpperCase()))
			
		{ // check if the dictionary contains our word
			result = "Word: " + word + "<br>Definition: " + dict.get(word);
			;// set the response
		} // if
		
		else 
		{
			result = "no definition found for " + word + " :(";
		}//else
		
		return result; // send the response back to InQueue.dispatch()
	}//end wordSearch

	@Override
	public String search(String Word) throws RemoteException, IOException {
		// TODO Auto-generated method stub
		return null;
	}



}// class
