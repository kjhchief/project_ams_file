package ams;

/**
 * 잔액이 부족합니다. 
 * @Author 김재훈
 * @Date 2023. 1. 10.
 */
public class NotSufficientBalanceException extends Exception{
	// String message;
	private int errorCode;
	
	public NotSufficientBalanceException() {
		this("예기치 않은 오류가 발생했습니다.", 0);
	}
	
	public NotSufficientBalanceException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
	
	@Override
	public String toString() {
		return "오류코드: "+errorCode+", 오류메세지: " + getMessage();
	}
	
}
