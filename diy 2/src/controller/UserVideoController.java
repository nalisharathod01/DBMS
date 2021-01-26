package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VideoDao;
import model.Video;

/**
 * Servlet implementation class UserVideoController
 */
@WebServlet("/user-videos")
public class UserVideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VideoDao videoDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserVideoController() {
        super();
        // TODO Auto-generated constructor stub
        videoDao = new VideoDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int user_id=Integer.parseInt(request.getParameter("user-id"));
		List<Video> userVideos = videoDao.userVideos(user_id);
		System.out.println(userVideos.size());
		request.setAttribute("userVideos", userVideos);
		request.getRequestDispatcher("user-videos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
