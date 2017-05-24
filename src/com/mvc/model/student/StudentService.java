package com.mvc.model.student;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mvc.mybatis.ConfigManager;

import common.exception.RegistFailException;

/**
 * @author user1
 *	직접 로직을 수행하지 않으며, 모델 파트의 여러 객체에 일을 시키는 관리자급 모델객체
 *이 객체의 존재에 의해, 컨트롤러가 본연의 역할을 더욱 잘 수행할 수 있다.
 *service는 모델이다.!
 */
public class StudentService {
	ConfigManager manager=ConfigManager.getInstance();
	//SqlSession session;//멤버변수로 선언하면 다른메소드에서도 공유해버리게 된다.
	StudentDAO studentDAO;
	PhysicalDAO physicalDAO;
	
	public StudentService() {
		//session=manager.getSession();
		//studentDAO=new StudentDAO(session);
		//physicalDAO=new PhysicalDAO(session);
		studentDAO=new StudentDAO();
		physicalDAO=new PhysicalDAO();//한번만 메모리에 올리고 sesssion을 얻는 setter를 만들ㅈ.
	}
	
	//학생 등록(Student+Physical)
	public int regist(Student student, Physical physical) throws RegistFailException{//호출자에게 책임을 묻는다.(에러를 알려준다.)
		int result=0;
		SqlSession session=manager.getSession();
		
		//DAO가 업무를 수행하기 전에 공통 세션을 부여하자!!
		//그래야 같은 커넥션 영역 즉 같은 세션으로 묶인다.
		//트랜잭션은 같은 세션내에서만 유효하기 때문이다.
		studentDAO.setSession(session);//injection
		physicalDAO.setSession(session);//injection
		
		
		try{//개발자가 주도해서 runtime예외를 만들어 트랜잭션 처리한다.
			int seq=studentDAO.insert(student);//개인정보
			physical.setStudent_id(seq);
			result=physicalDAO.insert(physical);//신체적 정보
			session.commit();
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
			throw new RegistFailException("회원등록에 실패했습니다.");//이예외를 클라이언트까지 가져갸야 한다. 전가안하면 tomcat이 그냥 client화면에 뿌려버린다.
		}finally {
			manager.close(session);
		}
		
		return result;		
	}
	
	public List getList(){
		SqlSession session=manager.getSession();
		studentDAO.setSession(session);
		List list=studentDAO.selectJoin();
		manager.close(session);
		return list;
	}
}
