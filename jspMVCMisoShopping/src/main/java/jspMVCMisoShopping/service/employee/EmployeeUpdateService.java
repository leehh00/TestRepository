package jspMVCMisoShopping.service.employee;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jspMVCMisoShopping.model.dao.EmployeeDAO;
import jspMVCMisoShopping.model.dto.AuthInfoDTO;
import jspMVCMisoShopping.model.dto.EmployeeDTO;

public class EmployeeUpdateService {
	public int execute(HttpServletRequest request) {
		String empNum = request.getParameter("empNum");
		HttpSession session = request.getSession(); 
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		EmployeeDAO dao = new EmployeeDAO();
		if(empNum == null) {
			empNum = dao.empNumSelect(auth.getUserId());
		}
		try {
			// 한글 깨짐 방지
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {}
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpAddr(request.getParameter("empAddr"));
		dto.setEmpAddrDetail(request.getParameter("empAddrDetail"));
		dto.setEmpEmail(request.getParameter("empEmail"));
		dto.setEmpId(request.getParameter("empId"));
		dto.setEmpName(request.getParameter("empName"));
		dto.setEmpNum(empNum);
		dto.setEmpPhone(request.getParameter("empPhone"));
		dto.setEmpPost(request.getParameter("empPost"));
		dto.setEmpPw(request.getParameter("empPw"));
		
		String hireDate = request.getParameter("empHireDate");
		// 문자열 날짜로 형변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date empHireDate = null;
		try {
			empHireDate = sdf.parse(hireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dto.setEmpHireDate(empHireDate);
		int i = 0;
		if(auth.getUserPw().equals(request.getParameter("empPw"))) {
			dao.employeeUpdate(dto);
			i = 1;
		}else {
			request.setAttribute("errPw", "비밀번호가 틀렸습니다.");
		}
		return i;
	}
}
