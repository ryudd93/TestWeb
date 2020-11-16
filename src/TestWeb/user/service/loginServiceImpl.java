package TestWeb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import TestWeb.user.model.UserDAO;
import TestWeb.user.model.UserVO;

public class loginServiceImpl implements UserService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserDAO dao = UserDAO.getInstance();
		UserVO user = dao.login(id, password); 
		
//		System.out.println(id);
//		System.out.println(password);
		
		
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return 1;
		} else {
			return 0;
		}
		
	}
	

}
