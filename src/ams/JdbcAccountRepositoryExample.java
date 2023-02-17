package ams;

import java.sql.SQLException;

public class JdbcAccountRepositoryExample {

	public static void main(String[] args) {
		try {
			AccountRepository repository = new JdbcAccountRepository();
			int count = repository.getCount();
			System.out.println("등록된 계좌 수 : " + count);
			
			Account account = new Account("4444-2222-3333", "김후니", 1111, 50000);
//			repository.addAccount(account);
			
			//계좌 검색
			String findNumber = "4444";
			Account account2 = repository.findByNumber(findNumber);
			System.out.println(account2.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
