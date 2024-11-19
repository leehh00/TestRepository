package jspMVCMisoShopping.service.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jspMVCMisoShopping.model.dao.EmployeeDAO;
import jspMVCMisoShopping.model.dto.AuthInfoDTO;

public class EmployeePasswordService {
	public int execute(HttpServletRequest request) {
		String oldPw = request.getParameter("oldPw");
		String newPw = request.getParameter("newPw");
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		int i = 0;
		if(auth.getUserPw().equals(oldPw)) {
			EmployeeDAO dao = new EmployeeDAO(); 
			dao.empPwUpdate(newPw, auth.getUserId());
			auth.setUserPw(newPw);
			i = 1;
		}else {
			request.setAttribute("errPw", "비밀번호가 틀렸습니다.");
		}
		return i;
	}
}
