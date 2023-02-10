package ams;

import java.util.Calendar;

/**
 * Account 클래스를 상속받는 마이너스 계좌
 * 
 * @author 김재훈
 * @date 2023. 1. 4.
 */
public class MinusAccount extends Account {
	// 새롭게 추가된 속성
	private long rentMoney;
	private Calendar rentDate;

	public MinusAccount() {
		super(); // 부모에 자동 할당?? 되어야 하니까??? 부모 디폴트 생성자도 만들어야 하니까.

	}

	public MinusAccount(String number, String owner, int passwd, long money, long rentMoney, Calendar rentDate) {
		super(number, owner, passwd, money); // 부모의 값 먼저 호출
		this.rentMoney = rentMoney;
		this.rentDate = rentDate;
	}

	public long getRentMoney() {
		return rentMoney;
	}

	public void setRentMoney(long rentMoney) {
		this.rentMoney = rentMoney;
	}
	

	public Calendar getRentDate() {
		return rentDate;
	}

	public void setRentDate(Calendar rentDate) {
		this.rentDate = rentDate;
	}

	// 잔액(restMoney - borrowMoney) 재정의(Overriding)
	public long getRestMoney() {
		return super.getRestMoney() - rentMoney;
	}
	
	@Override
	public String toString() {

		return super.toString() + " " + rentMoney + " " + String.format("%1$tF %1$tT", rentDate);
		
	}

}
