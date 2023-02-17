package ams;

import java.awt.Choice;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.IOException;
import java.util.List;

//import oop.Account;

/**
 * 은행 계좌 관리(등록, 수정, 삭제, 검색)를 위한 명세(규약)
 * @Author 김재훈
 * @Date 2023. 1. 9.
 */
public interface AccountRepository {
	/** 전체 계좌 개수 반환 */
	public int getCount();
	
	/** 계좌 등록 */
	public void addAccount(Account account) throws IOException;
	
	/** 전체 계좌 조회*/
	public List<Account> getAccounts() throws IOException; 
	
	/** 계좌번호로 계좌 검색 */
	public Account findByNumber(String number) throws IOException;
	
	/** 이름으로 계좌 검색 */
	public List<Account> findByName(String name) throws IOException;
	
	/** 계좌번호로 계좌 삭제*/
	public boolean removeAccount(String number) throws IOException;

}
