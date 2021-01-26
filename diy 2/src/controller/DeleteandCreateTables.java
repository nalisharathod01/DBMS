package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DropandCreate;

/**
 * Servlet implementation class DeleteandCreateTables
 */
@WebServlet("/initialize-database")
public class DeleteandCreateTables extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DropandCreate dropandCreate;
    public void init() {
    	dropandCreate = new DropandCreate();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteandCreateTables() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
        try {
			Thread.sleep(10000);
			response.sendRedirect(request.getContextPath()+"/");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
