package com.mvc.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.controller.Controller;
import com.mvc.model.student.StudentService;

/**
 * @author user1
 *	학생 등록 요청을 처리하는 동생 컨트롤러
 *	컨트롤러의 업무 중 3(일시킨다), 4단계(결과저장) 수행
 *	트랜잭션 세부업무가 모두 성공해야 db에 commit되는 논리적 업무 단위
 */
public class RegistController implements Controller {
	StudentService service=new StudentService();
	
	@Override
	public String excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service.regist();
		
		return null;
	}

	@Override
	public boolean isForward() {
		return false;
	}

}
