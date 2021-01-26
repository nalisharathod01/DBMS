package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDao;
import model.Question;

/**
 * Servlet implementation class PostQuestionController
 */
@WebServlet("/add-question")
public class PostQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QuestionDao questionDao;
	public void init() {
		questionDao = new QuestionDao();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostQuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session==null || session.getAttribute("username")==null) {
			response.sendRedirect(request.getContextPath()+"/login");
		}else {
			request.getRequestDispatcher("add-question.jsp").forward(request, response);
		}	
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Date posted_on = Calendar.getInstance().getTime();  
		String questions = request.getParameter("question");
		String tags = request.getParameter("tags").toLowerCase();
		HttpSession session = request.getSession(false);
		int user_id = (int)session.getAttribute("user_id");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String dateString = dateFormat.format(posted_on);  
		Question question= new Question(questions, tags, user_id, dateString);
		try {
			int a=questionDao.postQuestion(question);
			response.sendRedirect(request.getContextPath()+"/");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
