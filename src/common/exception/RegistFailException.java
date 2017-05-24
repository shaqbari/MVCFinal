package common.exception;

/**
 * @author user1
 * Throwable이 예외 객체드의 최상위 객체이다. 그러므로 모든 예외를 다 받을 수 있다.
 * 메모리에 올라가는 순간 예외를 일으킨다.
 */
public class RegistFailException extends RuntimeException{

	public RegistFailException(String message) {
		super(message);//부모생성자를 통해 메시지가 들어간다.
	}
	
}
