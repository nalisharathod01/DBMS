package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDao;
import dao.VideoDao;
import model.Video;

/**
 * Servlet implementation class AddVideoController
 */
@WebServlet("/add-video")
public class AddVideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VideoDao videoDao;
    
    public void init() {
		videoDao = new VideoDao();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVideoController() {
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
			request.getRequestDispatcher("add-video.jsp").forward(request, response);
		}	
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("video-title");
		String url = request.getParameter("url");
		String description= request.getParameter("video-description");
		int q_id = Integer.parseInt(request.getParameter("question"));
		HttpSession session = request.getSession(false);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;
		String currentDate = format.format(date);
		Video video= new Video(currentDate, (int)session.getAttribute("user_id"),url, title, description, q_id);

		int a=videoDao.dailyVideo((int) session.getAttribute("user_id"),currentDate);
		if (a<3) {
			videoDao.addVideo(video);
			response.sendRedirect(request.getContextPath()+"/question?id="+q_id);
		}
		else {
			request.setAttribute("error","Sorry you cannot upload more than three videos in a day");
			request.getRequestDispatcher("add-video.jsp").forward(request, response);
		}
		
	}

}
