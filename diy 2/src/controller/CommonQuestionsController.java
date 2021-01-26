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
import dao.UserDao;
import model.Question;

/**
 * Servlet implementation class CommonQuestionsController
 */
@WebServlet("/commonquestions")
public class CommonQuestionsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao;
    private QuestionDao questionDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommonQuestionsController() {
        super();
        // TODO Auto-generated constructor stub
        userDao = new UserDao();
        questionDao = new QuestionDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		int user1 = (int) session.getAttribute("user1");
		int user2 = (int) session.getAttribute("user2");
		List<Question> commonQuestions = questionDao.commonQuestions(user1, user2);
		
		request.setAttribute("commonQuestions", commonQuestions);
		
		request.getRequestDispatcher("commonquestions.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
