package ams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class ObjectAccountRepository implements AccountRepository {
	private static final String FILE_PATH = "accounts.ser";
	
	private Map<String, Account> map;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ObjectAccountRepository() throws IOException, ClassNotFoundException {
		File file = new File(FILE_PATH);
		// 기존에 파일이 없는 경우..
		if (!file.exists()) {
			map = new Hashtable<String, Account>();
		} else {
			// 기존 파일이 존재할 경우..
			in = new ObjectInputStream(new FileInputStream(file));
			map = (Map<String, Account>) in.readObject();
			in.close();
		}
	}

	@Override
	public int getCount() {
		return map.size();
	}

	@Override
	public void addAccount(Account account) throws IOException{
		// 기존에 등록된 계좌인지 검증
		if(map.containsKey(account.getAccountNumber())){
			JOptionPane.showMessageDialog(null, "이미 등록된 계좌입니다", "등록에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 맵에 계좌 저장
		map.put(account.getAccountNumber(), account);
	}
	
	public void saveFile() throws IOException  {
		// 맵을 파일에 저장
		out = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
		out.writeObject(map);
		out.flush();
		out.close();
	}

	@Override
	public List<Account> getAccounts() throws IOException {
		List<Account> allList = new ArrayList<Account>(map.values());
		return allList;
	}

	@Override
	public Account findByNumber(String number) throws IOException {
		return map.get(number);
	}

	@Override
	public boolean removeAccount(String number) throws IOException {
		Account account = map.remove(number);
		return account != null ? true : false;
	}

	@Override
	public List<Account> findByName(String name) throws IOException {
		List<Account> findAccounts = new ArrayList<Account>();
		Collection<Account> collection = map.values();
		for (Account account : collection) {
			if(account.getAccountOwner().equals(name)){
				findAccounts.add(account);
			}			
		}
		return findAccounts;
	}


}
