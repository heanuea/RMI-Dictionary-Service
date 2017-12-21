package ie.gmit.sw.client;

public class RequestCall {
	//variables 
	private String word;
	private String descript;
	private int taskNo;
	
	//constructor
	public RequestCall(String word, String descript, int taskNo) {
		this.word = word;
		this.descript = descript;
		this.taskNo = taskNo;
	}//end constructor
	

	public RequestCall(int giveID, String parameter) {
		// TODO Auto-generated constructor stub
	}


	//source gets and sets
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(int taskNo) {
		this.taskNo = taskNo;
	}


	

}//end class
