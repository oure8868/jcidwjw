package blog.com.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.AccountService;

@Controller
public class AccountRegisterController {
	@Autowired
	private AccountService accountService;

	@GetMapping("/account/register")
	public String getAccountRegisterPage() {
		
		return "account_register.html";
	}

	@PostMapping("/account/register/process")
	public String accountRegisterProcess(
			@RequestParam String accountName, 
			@RequestParam String accountEmail,
			@RequestParam String password) {
		
//		如果createAccount是true 向account_login.html遷移
//		如果不是，向account_register.html遷移
		if (accountService.createAccount(accountEmail, accountName, password)) {
			return "account_login.html";
		} else {
			return "account_register.html";
		}
	}
}
