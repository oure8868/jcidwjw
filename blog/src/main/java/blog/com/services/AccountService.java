package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.entity.Account;

@Service
public class AccountService {
//	interface AccountDao  を使えるようにする
	@Autowired
	private AccountDao accountDao;

//	保存処理（登録）処理
//	もしfingByAccountEmail==null 時登録処理
//	saveメソッドを使用して登録処理をする
//	保存ができたらtrue
//	そうでない場合、保存処理失敗false
	public boolean createAccount(String accountEmail, String accountName, String password) {
		if (accountDao.findByAccountEmail(accountEmail) == null) {
			accountDao.save(new Account(accountName, accountEmail, password));
			return true;
		} else {
			return false;
		}
	}

//	login処理
//	もし、emailと passwordがfindByAccountEmailAndPasswordを使用して存在しなかった場合（==nullの場合）
//　その場合は、存在しないNULLであることをコントローラclassにしらせる
//	そうでない場合、loginしている人の情報をコントローラーclassに渡す
	public Account loginCheck(String accountEmail, String password) {
		Account account = accountDao.findByAccountEmailAndPassword(accountEmail, password);
		if (account == null) {
			return null;
		} else {
			return account;
		}
	}

}
