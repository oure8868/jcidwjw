package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogRegisterController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	@GetMapping("/blog/register")
	public String getBlogRegisterPage(Model model) {
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if (account == null) {
			return "redirect:/account/login";
		} else {
			model.addAttribute("accountName", account.getAccountName());
			return "blog_register.html";
		}
	}

	@PostMapping("/blog/register/process")
	public String blogRegisterProcess(
			@RequestParam String blogTitle, 
			@RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, 
			@RequestParam String article) {
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if (account == null) {
			return "redirect:/account/login";
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();

			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (blogService.createBlog(blogTitle, categoryName, fileName, article,
					account.getAccountId())) {
				return "redirect:/blog/list";
			} else {
				return "blog_register.html";
			}
		}
	}
}
