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
