package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.Account;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;
@Controller
public class BlogListController {
	
//	Sessionが使えるように宣言
	@Autowired
	private HttpSession session;

//	class BlogServiceが使えるように宣言
	@Autowired
	private BlogService blogService;

//	商品一覧画面を表示する
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
//		セッションからログインしている人の情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");
		
//もし、account=null login画面にリダイレクトする
//そうでない場合
//loginしている人の名前の情報を画面に渡して商品一覧のhtmlを表示
		if (account == null) {
			return "redirect:/account/login";
		} else {
//			商品の情報を取得する
			List<Blog> blogList = blogService.selectAllBlogList(account.getAccountId());

			model.addAttribute("accountName", account.getAccountName());

			model.addAttribute("blogList", blogList);
			return "blog_list.html";

		}
	}
}
