package ie.gmit.sw.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryHashMap {

	// This creates dictionary of text (string):
	HashMap<String, ArrayList<String>> dictMap = new HashMap<String, ArrayList<String>>();

	public void csvRead() throws Exception {

		// csv dictionary file

		String dictFile = "./dictionary.csv";
		String line = null;
		String key = null;
		String charCheck = null;

		// declare and initialise the bufferedReader to parse dictionary file
		BufferedReader br = new BufferedReader(new FileReader(dictFile));

		// reading in line by line from file
		while ((line = br.readLine()) != null) {
			String[] keyString = line.split(",", 2);

			if (line.equals("")) {
				continue; 
			}

			charCheck = keyString[0].substring(0, 1);
			keyString[0] = keyString[0].replace("\"", "");

			if ("\"".equals(charCheck)) {
				key = keyString[0];
								addDefinition(key.toUpperCase(), line);
			} else {
						addDefinition(key.toUpperCase(), line);
			}

		} // while

		br.close();

	}// endloop

	
	// method here to add defition to dictionary
	private void addDefinition(String key, String definition) {
		if (dictMap.get(key) == null) {
			dictMap.put(key, new ArrayList<String>());
		}
		dictMap.get(key).add("\n" + definition);
	}

	
	// Return the hashmap when requested in another class
	public HashMap<String, ArrayList<String>> getDictionary() {
		return this.dictMap;
	}

}// end class
