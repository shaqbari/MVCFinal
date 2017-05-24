package com.mvc.model.student;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mvc.mybatis.ConfigManager;

public class StudentDAO {
	//ConfigManager manager=ConfigManager.getInstance();
	SqlSession session;
	
	/*public StudentDAO(SqlSession session) {
		this.session=session;
	}*/
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	//���
	public int insert(Student student){
		int seq=0;
		//SqlSession session=manager.getSession();
		session.insert("Student.insert", student);
		seq=student.getStudent_id();//dto�� ��� ��� �Էµ� seq�� �����´�.
		//session.commit();
		//manager.close(session);
		return seq;
	}
	
	public List selectJoin(){
		List list=null;
		list=session.selectList("Student.selectJoin");		
		return list;
	}
	
}
