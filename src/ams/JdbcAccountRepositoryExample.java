package ams;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class JdbcAccountRepositoryExample {

	public static void main(String[] args) {
		try {
			AccountRepository repository = new JdbcAccountRepository();
			int count = repository.getCount();
			System.out.println("등록된 계좌 수 : " + count);

			repository.addAccount(new Account("1111-2222-3333", "김재훈", 1111, 70000));
			repository.addAccount(new Account("2222-3333-4444", "김말쑥", 1111, 50000));
//			Date rentDate = new Date(System.currentTimeMillis());
//			repository.addAccount(new MinusAccount("3333-4444-5555", "김철수", 1111, 50000, 10000, rentDate));
			
			
			// 계좌 이름으로 검색
			System.out.println("----- 계좌 이름으로 검색 -----");
			repository.findByName("철수");
			
			// 계좌번호로 검색
			System.out.println("----- 계좌 번호로 검색 -----");
			repository.findByNumber("2222");
			
			// 계좌 전체 목록
			System.out.println("----- 전체 계좌 목록 -----");
			repository.getAccounts();
			
			// 계좌번호로 삭제
			System.out.println("----- 삭제된 계좌 -----");
			boolean removeOrNot = repository.removeAccount("3333-4444-5555");
			if(removeOrNot) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제할 계좌가 없습니다. 게좌번호를 끝까지 입력하거나 해당 계좌가 존재하는지 확인해주세요.");
			}
			
			// 오라클 데이터베이스에 접속 종료
			((JdbcAccountRepository) repository).disConnect();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
