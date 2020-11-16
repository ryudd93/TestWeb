package TestWeb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TestWeb.user.model.UserDAO;
import TestWeb.user.model.UserVO;

public class joinServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String ph_number1 = request.getParameter("ph_number1");
		String ph_number2 = request.getParameter("ph_number2");
		String ph_number3 = request.getParameter("ph_number3");
		String ph_number = ph_number1 + ph_number2 + ph_number3;
		String email_id = request.getParameter("email_id");
		String domain = request.getParameter("domain");
		String email = email_id + "@" + domain;
		String addr_basic = request.getParameter("addr-basic");
		String addr_detail = request.getParameter("addr-detail");
		
		System.out.println(id);
		
		UserDAO dao = UserDAO.getInstance();
		int result = dao.checkId(id);
		
//		System.out.println(addr_basic);
//		System.out.println(addr_detail);
		
		if (result == 1) {
			return 1;
		} else {
			UserVO vo = new UserVO(id, password, name, ph_number, email, addr_basic, addr_detail, null);
			dao.join(vo);
			return 0;
		}
		
		
		
		
		
	}

}
