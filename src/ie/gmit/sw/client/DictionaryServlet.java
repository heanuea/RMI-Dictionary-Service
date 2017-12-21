package ie.gmit.sw.client;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  implementation class DictionaryServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DictionaryServlet" })
public class DictionaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BlockingQueue<RequestCall> inQueue = new ArrayBlockingQueue<RequestCall>(10);
    private int giveID; 
    private String search = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DictionaryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		giveID++;
		RequestCall j = new RequestCall(giveID, request.getParameter("word"));
		
		try
		{
			inQueue.put(j);
		} 
		
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.out.println(giveID);
		String word = request.getParameter("word");
		try
		{
			DictionaryService ds = (DictionaryService) Naming.lookup("rmi://127.0.0.1:1099/dictionaryService");
			search = ds.search(word);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("word", word);
		request.setAttribute("definition", search);
		javax.servlet.RequestDispatcher rd=request.getRequestDispatcher("/response.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}