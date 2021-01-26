package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReviewDao;
import model.Review;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/review")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReviewDao reviewDao;
    
    public void init() {
    	reviewDao= new ReviewDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session.getAttribute("username")==null) {
			response.sendRedirect(request.getContextPath()+"/login");
		}else {
			int y_id = Integer.parseInt(request.getParameter("y_id"));
			if (request.getParameterMap().containsKey("edit")) {
				Review review=reviewDao.getReview((int)session.getAttribute("user_id"), y_id);
				request.setAttribute("isEditing", true);
				request.setAttribute("y_id",y_id);
				request.setAttribute("review", review);
				request.getRequestDispatcher("review.jsp").forward(request, response);
			}
			else {
				request.setAttribute("isEditing", false);
				request.setAttribute("y_id",y_id);
				request.getRequestDispatcher("review.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		int y_id = Integer.parseInt(request.getParameter("y_id"));
		String score=request.getParameter("score");
		String remark=request.getParameter("remark");
		boolean isReview = reviewDao.hasMadeReview((int)session.getAttribute("user_id"), y_id);
		
		Review review = new Review((int)session.getAttribute("user_id"),y_id, score, remark);
		if(isReview) {
			reviewDao.updateReview((int)session.getAttribute("user_id"), y_id, remark, score);
		}
		else {
			reviewDao.review(review);
		}
		
		response.sendRedirect(request.getContextPath()+"/questions");
	}

}
