package common.exception;

/**
 * @author user1
 * Throwable�� ���� ��ü���� �ֻ��� ��ü�̴�. �׷��Ƿ� ��� ���ܸ� �� ���� �� �ִ�.
 * �޸𸮿� �ö󰡴� ���� ���ܸ� ����Ų��.
 */
public class RegistFailException extends RuntimeException{

	public RegistFailException(String message) {
		super(message);//�θ�����ڸ� ���� �޽����� ����.
	}
	
}
