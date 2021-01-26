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
import dao.VideoDao;
import model.Question;
import model.Video;

/**
 * Servlet implementation class ShowVideosController
 */
@WebServlet("/question")
public class ShowVideosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionDao questionDao;
	private VideoDao videoDao;
	public void init() {
		questionDao = new QuestionDao();
		videoDao= new VideoDao();
	} 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowVideosController() {
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
			int question_id=Integer.parseInt(request.getParameter("id"));
			Question question = questionDao.getQuestion(question_id);
			
			List<Video> videos=videoDao.selectAllVideosById(question_id);
			request.setAttribute("videos", videos);
			request.setAttribute("question", question);
			request.getRequestDispatcher("question.jsp").forward(request, response);
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
