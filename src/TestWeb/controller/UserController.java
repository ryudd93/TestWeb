package TestWeb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.If;

import TestWeb.board.service.getMyListBoardServiceImp;
import TestWeb.user.service.UserService;
import TestWeb.user.service.deleteServiceImpl;
import TestWeb.user.service.getMyListUserServiceImp;
import TestWeb.user.service.joinServiceImpl;
import TestWeb.user.service.loginServiceImpl;
import TestWeb.user.service.logoutServiceImpl;
import TestWeb.user.service.updateServiceImpl;


@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
	}
	
	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		UserService service;
		
		
		if (command.equals("/user/join.user")) {
			request.getRequestDispatcher("user_join.jsp").forward(request, response);
			
			
		} else if (command.equals("/user/joinForm.user")) {
			service = new joinServiceImpl();
			service.execute(request, response);
			
			
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
		
			
		} else if (command.equals("/user/login.user")) {
			
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
		
			
		} else if (command.equals("/user/loginForm.user")) {
			service = new loginServiceImpl();
			int result = service.execute(request, response);
			
			service = new getMyListUserServiceImp();
			service.execute(request, response);
			
			if (result == 1) {
				request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "아이디와 비밀번호를 확인하세요.");
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
			}
		
		} else if (command.equals("/user/mypage.user")) {
			service = new getMyListUserServiceImp();
			service.execute(request, response);
			
			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
		
		} else if (command.equals("/user/mypageinfo.user")){
			
			request.getRequestDispatcher("user_mypageinfo.jsp").forward(request, response);
			
			
		} else if (command.equals("/user/update.user")) {
			service = new updateServiceImpl();
			int result = service.execute(request, response);
			
			if (result == 1) {
				request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "수정 정보를 확인하세요.");
				request.getRequestDispatcher("user_mypageinfo.jsp").forward(request, response);
			}
			

		} else if (command.equals("/user/delete.user")) {
			service = new deleteServiceImpl();
			int result = service.execute(request, response);
			
			
			if (result == 1) {
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "비밀번호를 확인하세요.");
				request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
			}
			
		} else if (command.equals("/user/logout.user")) {
			service = new logoutServiceImpl();
			int result = service.execute(request, response);
			
			if (result == 1) {
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('권한이 없습니다.');");
				out.println("location.href='/TestWeb/user/login.user';");	//로그인 화면
				out.println("</script>");
			}
			
		} 
		
		
	}

}
