package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;
@Controller
public class BlogDeleteController {
	
//	class BlogService が使えるように宣言
	@Autowired
	private BlogService blogService;
	
//	Sessionが使えるように宣言
	@Autowired
	private HttpSession session;
	
	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
//		セッションからloginしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
//		もし、account==null login画面にリダイレクトする
		if (account == null) {
			return "redirect:/account/login";
		} else {
//			もし、deleteBlogの結果がtrueだったら
			if(blogService.deleteBlog(blogId)) {
//				商品の一覧ページにリダイレクト
				return "redirect:/blog/list";
			} else {
//				そうでない場合
//				編集画面にリダイレクトする
				return "redirect:/blog/edit" + blogId;
			}
					}
	}
}
