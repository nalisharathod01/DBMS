package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FavoriteDao;
import dao.QuestionDao;
import dao.ReviewDao;
import dao.VideoDao;
import model.Question;
import model.Review;
import model.Video;

/**
 * Servlet implementation class WatchVideoController
 */
@WebServlet("/video")
public class WatchVideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionDao questionDao;
	private ReviewDao reviewDao;
	private VideoDao videoDao;
	private FavoriteDao favoriteDao;
	public void init() {
		questionDao = new QuestionDao();
		videoDao= new VideoDao();
		favoriteDao = new FavoriteDao();
		reviewDao= new ReviewDao();
	} 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WatchVideoController() {
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
			int q_id = Integer.parseInt(request.getParameter("q_id"));
			boolean isFavorite = favoriteDao.isFavorite(y_id, (int)session.getAttribute("user_id"));
			boolean isReview = reviewDao.hasMadeReview((int)session.getAttribute("user_id"), y_id);
			Video video =videoDao.singleVideo(y_id);
			int userPosted = video.getUser_id();
			Question question = questionDao.getQuestion(q_id);
			session.setAttribute("userPosted", userPosted);
			request.setAttribute("isReview", isReview);
			request.setAttribute("isFavorite",isFavorite);
			request.setAttribute("video",video);
			request.setAttribute("question", question);
;			request.getRequestDispatcher("video.jsp").forward(request, response);
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
