package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.entity.Account;

@Service
public class AccountService {
	@Autowired
	private AccountDao accountDao;
	
//	保存處理（登陸處理
//	如果accountEmail)==null 時登陸處理
//	使用save方法登陸處理
//	能保存時true
//	不能保存時 保存處理失敗false
	public boolean createAccount(String accountEmail, String accountName, String password) {
		if(accountDao.findByAccountEmail(accountEmail)==null) {
			accountDao.save(new Account(accountEmail, accountName, password));
			return true;
		} else {
			return false;
		}
	}
	
//	login
//	如果email password使用findByAccountEmailAndPassword不存在的場合==null
//上述場合，控制台class輸出不存在null的通知
//	非上述場合 login人的信息傳遞給控制台class
	public Account loginCheck(String accountEmail, String password) {
		Account account = accountDao.findByAccountEmailAndPassword(accountEmail, password);
		if(account == null) {
			return null;
		} else {
			return account;
		}
	}


}
