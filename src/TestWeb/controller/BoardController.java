package TestWeb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TestWeb.board.service.BoardService;
import TestWeb.board.service.contentServiceImpl;
import TestWeb.board.service.deleteServiceImpl;
import TestWeb.board.service.getListServiceImpl;
import TestWeb.board.service.getMyListBoardServiceImp;
import TestWeb.board.service.updateServiceImpl;
import TestWeb.board.service.writeServiceImpl;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
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
		
		BoardService service;
		
		if (command.equals("/board/bbs.board")) {
			service = new getListServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs.jsp").forward(request, response);
			
		} else if (command.equals("/board/bbs_write.board")) {
			request.getRequestDispatcher("bbs_write.jsp").forward(request, response);
		
		} else if (command.equals("/board/write.board")) {
			service = new writeServiceImpl();
			service.execute(request, response);
						
			response.sendRedirect("bbs.board");
			
		} else if (command.equals("/board/bbs_content.board")) {
			service = new contentServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs_content.jsp").forward(request, response);
		
		} else if (command.equals("/board/bbs_modify.board")) {
			service = new contentServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs_modify.jsp").forward(request, response);
			
		} else if (command.equals("/board/update.board")) {
			service = new updateServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("bbs_content.board?bno=" + request.getParameter("bno"));
			
		} else if (command.equals("/board/delete.board")) {
			service = new deleteServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("bbs.board");
			
		} 
		
	
	}
}

