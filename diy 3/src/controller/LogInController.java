package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class LogInController
 */
@WebServlet("/login")
public class LogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	
	public void init() {
		userDao = new UserDao();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String imageLogo="img/logo.PNG";
		if(session.isNew() || session.getAttribute("username")==null) {
			request.setAttribute("imageLogo",imageLogo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean ifExists = userDao.ifUserExist(email);
		
		if (email.equals("root") && password.equals("pass1234")) {
			
			HttpSession session = request.getSession(true);
			if (session != null) {
				session.invalidate();
		    }
			session=request.getSession(true);
			session.setAttribute("username", "root");
			session.setAttribute("admin", true);
			session.setAttribute("user_id", 0);
			
			
			response.sendRedirect(request.getContextPath()+"/");
		}
		
		else if (ifExists) {
				int a=userDao.validatePassword(email, password);
				if (a==0) {
					request.setAttribute("error", "Invalid password");
					
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				else {

				User user = userDao.selectUser(email);
				HttpSession session = request.getSession(true);
				if (session != null) {
					session.invalidate();
			    }
				session=request.getSession(true);
				session.setAttribute("username", email);
				session.setAttribute("user_id", user.getId());
				
				response.sendRedirect(request.getContextPath()+"/");
			}
		}
		else {
			request.setAttribute("error", "There is no user with that email");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}

}
