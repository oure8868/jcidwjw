package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Account;
@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
//	保存処理と更新処理 insertとupdate
	Account save(Account account);
	
//	select * from account where accoutn_email = ?
//	用途：管理者の登録処理する時に、同じメールアドレスがあったらば登録させないようにする
//	一行だけしかレコードは取得	できない
	Account findByAccountEmail(String accountEmail);
	
//	select * from account where accoutn_email = ? and password = ?
//	用途：login処理に使用，入力したメールアドレスとパスワード一致してる時だけデータを取得する
	Account findByAccountEmailAndPassword(String accountEmail, String password);

}
