package blog.com.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.AccountService;

@Controller
public class AccountRegisterController {
	
//	AccountService class を使えるようにする
	@Autowired
	private AccountService accountService;

//	登録画面の表示
	@GetMapping("/account/register")
	public String getAccountRegisterPage() {
		
		return "account_register.html";
	}

//	登録処理
	@PostMapping("/account/register/process")
	public String accountRegisterProcess(
			@RequestParam String accountName, 
			@RequestParam String accountEmail,
			@RequestParam String password) {
		
//		もし、createAccountがtrue account_login.htmlに遷移
//		そうでない場合、account_register.htmlにとどまります
		if (accountService.createAccount(accountEmail, accountName, password)) {
			return "account_login.html";
		} else {
			return "account_register.html";
		}
	}
}
