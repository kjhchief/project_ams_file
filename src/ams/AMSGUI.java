package ams;

import java.io.IOException;

/**
 * @Author 김재훈
 * @Date 2023. 1. 21.
 * 
 */
public class AMSGUI {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 깃 테스트1
		AccountRepository repository = new ObjectAccountRepository();
		AccountFrame frame = new AccountFrame("HOONI-BANK AMS", repository);
		// 가상데이터 등록
//		repository.addAccount(new Account("1111-2222", "김재훈", 1111, 100000));
//		Calendar today = Calendar.getInstance();
//		repository.addAccount(new MinusAccount("1111-3333", "김대출", 1111, 10000, 10000000, today));

		frame.initLayout();
		frame.addEventListener();
		frame.setSize(590, 460);
		frame.setVisible(true);
		frame.rentMoneyTF.setEditable(false);
		frame.nameTF.setText("홍길동");
		frame.accountNumTF.setText("1111-4444-5555");
		frame.pwdTF.setText("1234");
		frame.InputMoneyTF.setText("10000");
//		frame.rentMoneyTF.setText("5000");
		// 깃 푸시푸시푸시해줘~

	}

}
