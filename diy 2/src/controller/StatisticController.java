package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDao;
import dao.ReviewDao;
import dao.TagDao;
import dao.UserDao;
import dao.VideoDao;
import model.Question;
import model.Review;
import model.Tag;
import model.User;
import model.Video;

/**
 * Servlet implementation class StatisticController
 */
@WebServlet("/stats")
public class StatisticController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReviewDao reviewDao;
    private QuestionDao questionDao;
    private VideoDao videoDao;
    private TagDao tagDao;
    private UserDao userDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticController() {
        super();
        // TODO Auto-generated constructor stub
        reviewDao = new ReviewDao();
        questionDao = new QuestionDao();
        videoDao = new VideoDao();
        tagDao = new TagDao();
        userDao = new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// question one
		List<Review> excellentReviews = reviewDao.excellentReviews();
		List<Question> createdToday = questionDao.createdToday();
		List<Video> hotYoutubes = videoDao.hotYoutubes();
		Question topQuestion = questionDao.topQuestion();
		List<Tag> popularTags = tagDao.popularTags();
		// List<Question> commonQuestions = questionDao.commonQuestions(user1, user2)
		List<User> getAllUsers = userDao.getAllUsers();
		User topReviewer = userDao.topReviewer();
		List<User> positiveReviewers = userDao.positiveReviewers();
		List<Question> poorQuestions = questionDao.poorQuestions();
		List<User> inactiveUsers = userDao.inactiveUsers();
//		System.out.println(excellentReviews.size() + "size");
		request.setAttribute("excellentReviews", excellentReviews);
		request.setAttribute("createdToday", createdToday);
		request.setAttribute("hotYoutubes", hotYoutubes);
		request.setAttribute("topQuestion", topQuestion);
		request.setAttribute("popularTags", popularTags);
		request.setAttribute("getAllUsers", getAllUsers);
		request.setAttribute("topReviewer", topReviewer);
		request.setAttribute("positiveReviewers", positiveReviewers);
		request.setAttribute("poorQuestions", poorQuestions);
		request.setAttribute("inactiveUsers", inactiveUsers);
		
		request.getRequestDispatcher("statistics.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId1=Integer.parseInt(request.getParameter("user1"));
		int userId2=Integer.parseInt(request.getParameter("user2"));
		HttpSession session = request.getSession(true);
		session.setAttribute("user1", userId1);
		session.setAttribute("user2", userId2);
		response.sendRedirect(request.getContextPath()+"/commonquestions");
	}

}
