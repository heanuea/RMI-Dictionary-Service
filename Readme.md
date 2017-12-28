

# **_About Me_**

My name is Alan Heanue I am a 4th year software development Studying in the Galway-Mayo Intitute of Technology
This is one of 5 modules i am Working on this semester it focuses mainly on Distributed Systems with Java.  


## **_OverView Of Application_**
required to use the Servlet/JSP and Java RMI frameworks to develop a remote, asynchronous dictionary
lookup service. A JSP page should provide users with the ability to specify a string which will be checked
against the dictionary. The HTML form information should be dispatched to a servlet that adds the client request
to an in-queue and then returns a job ID to the web client. The web client should poll the web server periodically
(every 10 seconds) and query if the request has been processed. Client requests in the inQueue should
be periodically removed and processed (every 10 seconds).

The processing of a client request will require a RMI method invocation to a remote object which implements
an interface called DictionaryService. The remote object which implements DictionaryService should check
if the string received exists in the dictionary, and return the dictionary definition of the string if it does exist in
the dictionary, or “String not found” if it does not exist in the dictionary. 

Once the result of the dictionary lookup has been computed by the remote object, the returned response should be added to the outQueue on the Tomcat
server and returned to the original web client when they next poll the server.


## **_Technologies Used _**
Here we look at the some of the Tech Used in this project 

![alt tag](http://www.codepep.com/wp-content/uploads/2017/01/24.jpg)

**JSP**
   JavaServer Pages (JSP) is a technology that helps developers create dynamically generated webpages using HTML, XML or other document types. In order to run a JSP page, a compatible web server is required. For example Apache Tomcat.

**Java Servlets**
   A Java servlet is a Java program that extends the capabilities of a server. Java servelets most commonly implement applications hosted on web servers. Servlets are often used with the HTTP protocol. Servlets can be generated automatically from JavaServer Pages by the JavaServer Pages compiler. The difference between servlets and JSP is that servlets typically embed HTML inside Java code, while JSPs embed Java code in HTML.

**RMI** 
   Remote Method Invocation (RMI) is a mechanism that allows an object residing in one JVM to access/invoke an object running on another JVM.
   RMI is used to build Distributed Applications, providing Remote Communication between Java programs.
   It's accessed in the java.rmi package.


## **_Requirements Needed_**

- Eclipse java EE
- TomCat Server 9 

## **_Features I Used in App_**



- RMI classes 

```Java
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


```

- Unicast Class


```Java
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

```

```Java
package ie.gmit.sw.server;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ServletPath")
public class DictionaryServiceServer extends HttpServlet {
	private static final long serialVersionUID = 777L;
	private InQueue r = null;

//Called by the server (via the service method) to allow a servlet to handle a GET request.
	protected void doGet(HttpServletRequest request, HttpServletResponse response, String res)
			throws ServletException, IOException
	
	{
		// TODO Auto-generated method stub
		response.getWriter().append(res);

		response.sendRedirect("Home.jsp?message=" + res);
	}

	
	//   Called by the server (via the service method) to allow a servlet to handle a POST request.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		if (r == null) 
		
		{
			r = new InQueue(request.getParameter("word")); // new instance of the thread
			new Thread(r).start(); // start the thread
		} 
		
		else 
		{
			r.addToQueue(request.getParameter("word"));
		}//end else
		
		try
		
		{
			Thread.sleep(10); // thread time 
			String res = r.getResponse(); 
			doGet(request, response, res); 
		} catch (InterruptedException e)
		
		
		{
			e.printStackTrace();
		} // try catch

	}// post

}

```


## **_To run this app_**

- Open command line 

- run java -jar dictionary-service.jar


### **_Conclusion_**
Creating this app was frustrating at times i did learn and got a better understanding how RMI works and how servlets worked o wish i had started ealier so i could have added more features but overall i found this module interesting. 

### **_References_**
 - http://www.ejbtutorial.com/java-rmi/a-step-by-step-implementation-tutorial-for-java-rmi
 - https://docs.oracle.com/javase/tutorial/rmi/index.html
 - https://www.tutorialspoint.com/servlets/
 - https://tomcat.apache.org/tomcat-5.5-doc/servletapi/javax/servlet/http/HttpServlet.html
 -
