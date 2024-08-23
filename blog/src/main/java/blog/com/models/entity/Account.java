package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
//	account_id設定
	@Id
//	自動追加id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;
	
//	account_name
	private String accountName;

//	account_email
	private String accountEmail;
	
//	account_password
	private String password;

//	空のコンストラクタ
	public Account() {
	}

//	コンストラクタ
	public Account(String accountName, String accountEmail, String password) {
		this.accountName = accountName;
		this.accountEmail = accountEmail;
		this.password = password;
	}

	
//	ゲッターとセッター
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
