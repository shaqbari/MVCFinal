package com.mvc.model.student;

import org.apache.ibatis.session.SqlSession;

import com.mvc.mybatis.ConfigManager;

public class PhysicalDAO {
	//ConfigManager manager=ConfigManager.getInstance();
	SqlSession session;//���񽺰� ������ �ذ��� ����. ��? �׷��� �ٸ� DAO��ü��� �ϳ��� Ŀ�ؼ����� ���ϼ� �־� Ʈ������� �̿��� �� �ֱ� ������
	
	/*public PhysicalDAO(SqlSession session) {
		this.session=session;
	}*/
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	public int insert(Physical physical){
		int result=0;
		//SqlSession session=manager.getSession();//session�� Service�� ������ tranjectionó���� �� �� �ִ�.
		result=session.insert("Physical.insert", physical);
		//session.commit();//session���� commit�Ѵ�.
		//manager.close(session);//session���� ����.
		return result;
		
	}	
	
}
