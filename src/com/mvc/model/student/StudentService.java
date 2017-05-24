package com.mvc.model.student;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mvc.mybatis.ConfigManager;

import common.exception.RegistFailException;

/**
 * @author user1
 *	���� ������ �������� ������, �� ��Ʈ�� ���� ��ü�� ���� ��Ű�� �����ڱ� �𵨰�ü
 *�� ��ü�� ���翡 ����, ��Ʈ�ѷ��� ������ ������ ���� �� ������ �� �ִ�.
 *service�� ���̴�.!
 */
public class StudentService {
	ConfigManager manager=ConfigManager.getInstance();
	//SqlSession session;//��������� �����ϸ� �ٸ��޼ҵ忡���� �����ع����� �ȴ�.
	StudentDAO studentDAO;
	PhysicalDAO physicalDAO;
	
	public StudentService() {
		//session=manager.getSession();
		//studentDAO=new StudentDAO(session);
		//physicalDAO=new PhysicalDAO(session);
		studentDAO=new StudentDAO();
		physicalDAO=new PhysicalDAO();//�ѹ��� �޸𸮿� �ø��� sesssion�� ��� setter�� ���餸.
	}
	
	//�л� ���(Student+Physical)
	public int regist(Student student, Physical physical) throws RegistFailException{//ȣ���ڿ��� å���� ���´�.(������ �˷��ش�.)
		int result=0;
		SqlSession session=manager.getSession();
		
		//DAO�� ������ �����ϱ� ���� ���� ������ �ο�����!!
		//�׷��� ���� Ŀ�ؼ� ���� �� ���� �������� ���δ�.
		//Ʈ������� ���� ���ǳ������� ��ȿ�ϱ� �����̴�.
		studentDAO.setSession(session);//injection
		physicalDAO.setSession(session);//injection
		
		
		try{//�����ڰ� �ֵ��ؼ� runtime���ܸ� ����� Ʈ����� ó���Ѵ�.
			int seq=studentDAO.insert(student);//��������
			physical.setStudent_id(seq);
			result=physicalDAO.insert(physical);//��ü�� ����
			session.commit();
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
			throw new RegistFailException("ȸ����Ͽ� �����߽��ϴ�.");//�̿��ܸ� Ŭ���̾�Ʈ���� �������� �Ѵ�. �������ϸ� tomcat�� �׳� clientȭ�鿡 �ѷ�������.
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
