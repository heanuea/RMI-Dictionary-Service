package ie.gmit.sw.server;
import java.rmi.Naming;
import java.util.LinkedList;
import java.util.Queue;

public class InQueue implements Runnable {

	private Queue<String> q; // Q for incoming requests - manages order of requests if single thread
	private Queue<String> responQ; 
	private String response;

	public InQueue(String word) {
		q = new LinkedList<>();
		responQ = new LinkedList<>();
		q.add(word);
	} 

	public void dispatch() {
		System.out.println("running. . . " + this.hashCode());
		try {
			System.out.println(" wait");
			if (q.peek() != null) {
				System.out.println("Pulling request from Queue. . .");
				DictionaryService ds = (DictionaryService) Naming.lookup("rmi://127.0.0.1:1099/dictionaryService"); 
				
			
				
				this.response = ds.search(q.poll().toUpperCase()); 
				responQ.add(response); 
				System.out.println(response); 
			} 
			
			else
			{
				System.out.println("Que empty... ");
		}//end else
			
			
		}//end dispatch
		
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end catch

	}// end dispatch method

	public String getResponse() {
		return responQ.poll();
	}

	public void run() {
		dispatch();
	}
	

	public void addToQueue(String parameter) {
		q.add(parameter);
		dispatch();
	}//end queue
	
}// end main 
