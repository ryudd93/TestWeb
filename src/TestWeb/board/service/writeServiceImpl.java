package TestWeb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import TestWeb.board.model.BoardDAO;
import TestWeb.board.model.BoardVO;

public class writeServiceImpl implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		BoardDAO dao = BoardDAO.getInstance();
		dao.write(writer, title, content);
		
		
//		System.out.println("write : " + result);
//		System.out.println(writer);
//		System.out.println(title);
//		System.out.println(content);
		
		
	}

}
