package com.mvc.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.mvc.controller.Controller;
import com.mvc.model.student.Physical;
import com.mvc.model.student.Student;
import com.mvc.model.student.StudentService;

import common.exception.RegistFailException;

/**
 * @author user1
 *	학생 등록 요청을 처리하는 동생 컨트롤러
 *	컨트롤러의 업무 중 3(일시킨다), 4단계(결과저장) 수행
 *	트랜잭션: 세부업무가 모두 성공해야 db에 commit되는 논리적 업무 단위
 */
public class RegistController implements Controller {
	StudentService service=new StudentService();
	boolean isForward;
	String viewPage;
	
	@Override
	public String excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파라미터 받기
		String id=req.getParameter("id");
		String password=req.getParameter("password");
		String name=req.getParameter("name");

		String blood=req.getParameter("blood");
		String weight=req.getParameter("weight");
		
		Student student=new Student();
		student.setId(id);
		student.setPassword(password);
		student.setName(name);		
		
		Physical physical=new Physical();
		physical.setBlood(blood);
		physical.setWeight(Integer.parseInt(weight));
		
		//3단계: 알맞은 로직 객체에 일 시킨다.
		try {
			service.regist(student, physical);
			viewPage="result/student/regist";
		} catch (RegistFailException e) {
			e.printStackTrace();
			//클라이언트까지 가져가기 위해 에러 객체를 저장해보자
			req.setAttribute("ex", e);//수행되면 forward, 안되면 redirect
			isForward=true;
			viewPage="result/student/error";
		}//런타임 예외라 예외처리 안해도 빨간줄 안간다. 안하면 톰캣이 클라이언트에 내용 그냥 뿌려버린다.
		
		//4단계: 보여줄게 없다.
		
		return viewPage;
	}

	@Override
	public boolean isForward() {
		return isForward;
	}

}
