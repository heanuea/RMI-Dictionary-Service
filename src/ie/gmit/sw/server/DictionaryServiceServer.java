package ie.gmit.sw.server;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DictionaryServlet
 */
@WebServlet("/ServletPath")
public class DictionaryServiceServer extends HttpServlet {
	private static final long serialVersionUID = 777L;
	private InQueue r = null;

	/**
	 * Default constructor.
	 */
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response, String res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append(res);

		response.sendRedirect("index.jsp?message=" + res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (r == null) {
			r = new InQueue(request.getParameter("word")); // new instance of the thread
			new Thread(r).start(); // start the thread
		} else {
			r.addToQueue(request.getParameter("word"));
		}
		// // THE ABOVE WILL USE A SINGLE THREAD! BELOW THREAD Per TRANSACTION
		// InQueue r = new InQueue(request.getParameter("word"));
		// new Thread(r).start();

		try {
			Thread.sleep(1000); // some time for the thread to do its thing
			String res = r.getResponse(); // get the response from our thread
			doGet(request, response, res); // call the get
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // try catch

	}// post

}
