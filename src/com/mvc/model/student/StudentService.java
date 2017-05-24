package com.mvc.model.student;

/**
 * @author user1
 *	직접 로직을 수행하지 않으며, 모델 파트의 여러 객체에 일을 시키는 관리자급 모델객체
 *이 객체의 존재에 의해, 컨트롤러가 본연의 역할을 더욱 잘 수행할 수 있다.
 *service는 모델이다.!
 */
public class StudentService {
	StudentDAO studentDAO;
	PhysicalDAO physicalDAO;
	
	public StudentService() {
		studentDAO=new StudentDAO();
		physicalDAO=new PhysicalDAO();
	}
	
	//학생 등록(Student+Physical)
	public int regist(){
		int result=0;
		
		
		return result;		
	}
}
