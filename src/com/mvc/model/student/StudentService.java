package com.mvc.model.student;

/**
 * @author user1
 *	���� ������ �������� ������, �� ��Ʈ�� ���� ��ü�� ���� ��Ű�� �����ڱ� �𵨰�ü
 *�� ��ü�� ���翡 ����, ��Ʈ�ѷ��� ������ ������ ���� �� ������ �� �ִ�.
 *service�� ���̴�.!
 */
public class StudentService {
	StudentDAO studentDAO;
	PhysicalDAO physicalDAO;
	
	public StudentService() {
		studentDAO=new StudentDAO();
		physicalDAO=new PhysicalDAO();
	}
	
	//�л� ���(Student+Physical)
	public int regist(){
		int result=0;
		
		
		return result;		
	}
}
