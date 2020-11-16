package TestWeb.main.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TestWeb.board.model.BoardVO;
import TestWeb.main.model.MainDAO;

public class mainListServiceImpl implements MainService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		MainDAO dao = MainDAO.getInstance();
		ArrayList<BoardVO> vo = dao.getMainList();
		
		request.setAttribute("list", vo);

	}

}
