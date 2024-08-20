package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Account;
@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
//	保存處理和更新處理 insert和update
	Account save(Account account);
	
//	select * from account where accoutn_email = ?
//	用途：管理者登陸處理時，碰到同一個郵件時不能登錄
//	只能取得一行字符
	
	Account findByAccountEmail(String accountEmail);
	
//	select * from account where accoutn_email = ? and password = ?
//	用途：login時使用，填寫的郵件和密碼一致時才能讀取數據
	Account findByAccountEmailAndPassword(String accountEmail, String password);

}
