package TestWeb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TestWeb.board.model.BoardDAO;
import TestWeb.board.model.BoardVO;

public class contentServiceImpl implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String bno = request.getParameter("bno");
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = dao.getContent(bno);
		
//		System.out.println(bno);
//		System.out.println(vo.getBno());
		request.setAttribute("vo", vo);
		
		
	}

}
