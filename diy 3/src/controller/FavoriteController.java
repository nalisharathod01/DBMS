package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FavoriteDao;

/**
 * Servlet implementation class FavoriteController
 */
@WebServlet("/favorite")
public class FavoriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FavoriteDao favoriteDao;
    
    public void init() {
    	favoriteDao = new FavoriteDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		boolean isFavorite=Boolean.parseBoolean(request.getParameter("isTrue"));
		int q_id = Integer.parseInt(request.getParameter("q_id"));
		int y_id = Integer.parseInt(request.getParameter("y_id"));
		
		if (isFavorite){
			favoriteDao.deleteFavorite(y_id, (int)session.getAttribute("user_id"));
			response.sendRedirect(request.getContextPath()+"/video?y_id="+y_id+"&q_id="+q_id);
		}
		else {
			favoriteDao.insertFavorite(y_id, (int)session.getAttribute("user_id"));
			response.sendRedirect(request.getContextPath()+"/video?y_id="+y_id+"&q_id="+q_id);
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
